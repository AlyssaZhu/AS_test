package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FullFuction extends Functions {
	
/*	
	上传
	下载
	文件列表
	打开 d
	移动
	复制
	评论
	手势密码
	语言设置
	登录*/
	
	@BeforeClass
	  public void beforeClass() throws Exception {
		  init();
		  loginTest("116.236.224.243","zfy","123123");
	  }
	  
	  @AfterClass
	  public void afterClass() {
		  System.out.println("AfterClass");
		  driver.quit();
	  }
	  @BeforeMethod
	  public void beforeMethod() throws Exception {	
		  startAS();
	  }

	  @AfterMethod
	  public void afterMethod() throws Exception {
		  killAS();
	  }
	  
 /* ***************************************************************/
	  @Test
	  public void a_uploadVideo() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传视频"),"上传失败") ;  
	  	  clearUploadList();
	  }
	  @Test 
	  public void a_uploadAudio() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传音频"),"上传失败");
	  	  clearUploadList();  
	  }
	  @Test 
	  public void a_uploadFile() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传文件"),"上传失败");
	  	  clearUploadList();  
	  }

	  	  
	  @Test 
	  public void a_uploadTakePhoto() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"拍摄上传"),"上传失败");
	  	  clearUploadList();  
	  }
	    
	  	  
	  @Test 
	  public void a_uploadPics() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传照片"),"上传失败");
	  	  clearUploadList();  
	  }

	  @Test //2017-12-15
	  public void a_uploadWaitWifi() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  startAS();
	  	  driver.pressKeyCode(3);
	  	  switchWifi(0);
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传视频"),"上传失败");
	  	  assertTrue(exist("等待Wi-Fi..."),"不存在等待Wi-Fi标记");
	  	  clearUploadList(); 
	  	  switchWifi(1);
	  }

	  @Test //2017-12-17
	  public void a_uploadNoWifi() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  switchWifi(0);
	  	  enterDest("上传");
	  	  assertTrue(upload(1,0,"上传视频"),"上传失败");
	  	  assertTrue(!exist("等待Wi-Fi..."),"存在等待Wi-Fi标记");
	  	  clearUploadList(); 
	  	  switchWifi(1);
	  }

	  @Test
	  //多选只针对视频／音频／文件有效
	  public void a_uploadMulVideo() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(6,1,"上传视频"),"上传失败") ;  
	  	  clearUploadList();
	  }

	  @Test
	  public void a_uploadAllVideo() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(10,1,"上传视频"),"上传失败") ;  
	  	  clearUploadList();
	  }

	  @Test
	  public void a_uploadPauseVideo() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(10,1,"上传视频"),"上传失败") ;  
	  	  assertTrue(pause("上传",0),"未暂停");
	  	  clearUploadList();
	  }
	  @Test
	  public void a_uploadContinueVideo() throws Exception
	  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(10,1,"上传视频"),"上传失败") ; 
	  	  assertTrue(!pause("上传",1),"未暂停");
	  	  clearUploadList();
	  }
  	  
	  @Test
	  public void a_uploadNoPermission() throws Exception
	  {   	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	  		enterNoPermission("");
	  		assertTrue(!upload(10,1,"上传视频"),"上传失败") ;  
	  	//  clearUploadList();
	  }
	  
	
	/*  ----------------下载-----------------------------*/
	//普通下载 开启仅在Wi-Fi&&WiFi环境
		@Test(enabled=true) 
		public void  b_downldAFile() throws Exception
		{
			System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());
		//	switchWifi(1);
			assertTrue(download(1,1,1),"下载失败！");
			assertTrue(exist("下载完成(1)",15),"下载失败！");
		}
			
		@Test(enabled=true) 
		public void b_downldManyFile() throws Exception
		{
			System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());	
			switchWifi(1);
			startAS();
			assertTrue(download(10,1,1),"下载失败！");
		}
		
		
		@Test(enabled=true) 
		public void b_downldMultiFile() throws Exception
		{
			startAS();
			assertTrue(download(6,1,1),"下载失败！");
			clearCache();
		}

		@Test(enabled=true) 
		public void b_downldAllFile() throws Exception
		{
			System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			assertTrue(download(30,1,1),"下载失败！");
			clearCache();
		//	killAS();
		}
		
		@Test(enabled=true,priority=1) //case:开启仅在Wi-Fi&&WiFi-> 切换为4G的环境（选“继续传输”）
		public void b1_downldNoWifi() throws Exception
		{
			System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			switchWifi(1);
			switchWifi(0);
			startAS();
			assertTrue(download(1,0,1),"下载失败！");
			assertTrue(!exist("等待Wi-Fi..."),"存在等待Wi-Fi标记");
			clearCache();
			switchWifi(1);
		}
		
		//之前在应用外尝试切网，发现没有反应，因此调整切换Wi-Fi的方法顺序
		//case:开启仅在Wi-Fi&&WiFi-> 切换为4G的环境（选“仅在Wi-Fi”）
		@Test 
		public void b2_downldWaitWfi() throws Exception
		{
			System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			switchWifi(1);
			Thread.sleep(2000);
			switchWifi(0);
			assertTrue(download(2,1,1),"下载失败！");
			assertTrue(exist("等待Wi-Fi..."),"不存在等待Wi-Fi标记");
			clearCache();
			switchWifi(1);
		}
		
		//普通下载 开启仅在Wi-Fi&&WiFi环境
		@Test
		public void  b2_downldNoPermisison() throws Exception
		{
			System.out.println("Begin************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			assertTrue(!download(1,1,0),"没有权限的文件能够下载成功！");
			clearCache();
		}
		
		//文件列表
		@Test
		  public void c_delFile() throws Exception{
			  System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  enterDest("文件列表");
			  String fileName=manageFile(2,"删除");
			  clickName("确定");
			  assertTrue(!exist(fileName),"已删除!");   
		  }
			@Test
		 	public void c_delFiles() throws Exception
		 	{System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  enterDest("文件列表");
			  manageFiles(2,"删除");
			  clickName("确定");
		 	}
			@Test
			public void c_renameFile() throws Exception
			{	System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
				enterDest("文件列表");
			 	manageFile(2,"重命名");
			 	WebElement e1= driver.findElementByClassName("android.widget.EditText");
			 	String fname1=e1.getText();
			 	e1.sendKeys(fname1+"1");
			 	clickName("确定"); 
			  }

			@Test
		   public void c_searchKws() throws Exception
		   {System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  search("04","当前目录");
		   }
			@Test
		   public void c_searchAlldir() throws Exception
		   {	System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  search("04","所有目录");
		   }
		   @Test
		   public void c_asearchInlink() throws Exception
		   {	System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			   search("AnyShare://vivi/.自动化目录/文件类型/word/Android自动化测试.doc","内链路径");
			   switchInput(1);
			   startAS();
			   killAS();  
		   }
		   
		   @Test
		   public void c_searchExlink() throws Exception
		   {	
			   System.out.println("*************"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  search("http://116.236.224.243:80/link/7F353C66D9131673ED85316ADA566591 有效期限：2018-02-10 ","外链地址");
			  switchInput(1);
			  startAS();
			  killAS();
		   }
		

	//打开
		  @Test
		  public void d_openVideo() throws Exception {
			enterDest("文件类型");
			clickFile("视频");
			clickFile(1);
			Thread.sleep(15000);
			String progress=driver.findElementById("com.eisoo.anyshare:id/video_time_current").getText();
			System.out.println("当前进度为："+progress);
			assertTrue(!progress.equals("00:00"),"进度为0");  
		  }
		 @Test
		  public void d_openAudio() throws Exception {
			enterDest("文件类型");
			clickFile("音频");
			clickFile(1);
			Thread.sleep(15000);
			String progress=driver.findElementById("com.eisoo.anyshare:id/tv_currentPosition").getText();
			System.out.println("当前进度为："+progress);
			assertTrue(!progress.equals("00:00"),"进度为0");  
		  }
		  
		  @Test
		  public void d_openFile() throws Exception
		  {
			  enterDest("文件类型");
			  clickFile("word");
			  clickFile(1);
			  Thread.sleep(10000);
			  clickName("用第三方应用打开");
			  clickName("WPS Office");
			  Thread.sleep(3000);
			  assertTrue(exist("编辑"),"文件未打开!");  
		  }
		  
		  @Test
		  public void d_openPic() throws Exception
		  {
			  enterDest("文件类型");
			  clickFile("图片");
			  clickFile(1);
			  Thread.sleep(6000);
			  WebElement e1=driver.findElementById("com.eisoo.anyshare:id/iv_photo");
			  assertTrue(exist(e1),"文件未打开!");  
		  }
		  
	//移动复制
		//复制个数
			@Test
			public void e_copyFile() throws Exception
			{
				System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				copy(1,"");
				assertTrue((checkNum()>=1),"文件数小于复制个数！");
				delFiles(10); 
			}
			@Test
			public void e_copyFileSingle() throws Exception
			{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				copy(1,"single");
				assertTrue((checkNum()>=1),"文件数小于复制个数！");
				delFiles(10); 
			}
			 @Test
			public void e_copyFiles() throws Exception
			{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				copy(3,"");
				assertTrue((checkNum()>=3),"文件数小于复制个数！");
				delFiles(10); 
			}
			@Test
			public void e_copyAllFiles() throws Exception
			{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				copy(10,"");
				delFiles(10); 
			}
				
			@Test
			public void e_moveFile() throws Exception
			{  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				move(1,"");
				assertTrue((checkNum()>=1),"文件数小于移动个数！");
			}
			@Test
			public void e_moveFileSingle() throws Exception
			{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				move(1,"single");
				assertTrue((checkNum()>=1),"文件数小于移动个数！");
				
			}
			 @Test
			public void e_moveFiles() throws Exception
			{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				move(3,"");
				assertTrue((checkNum()>=3),"文件数小于移动个数！");
				moveBack();
			}
			@Test
			public void e_moveAllFiles() throws Exception
			{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				move(10,"");
			//	delFiles(10);
				moveBack();
			}
			
	  //评论,先统计评论数
	  @Test
	  public void f_addcomm_diffChar() throws Exception
	  {
		  String content="123456788ABCabc 评论！"  ;
		  assertTrue(addComment(content),"没有添加成功");
		  assertTrue(exist(content),"没有找到评论内容");
		  
	  }
	  @Test
	  public void f_addcomm_overlimit() throws Exception
	  {
		 
		  System.out.println("Case:"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  String content="hghhjkjkjkkkjjjjhxjjjjhghggfgffffffffdffff"
			  		+ "gggggghghhhhjjjkjgsggssghhjksjsjlskkskskssjksjhshjsjhkhgjg"
			  		+ "fgfhfffghgfhghgjhjjhkjjhjhhjhggfffffff1234ass我来了就看看hghhj"
			  		+ "kjkjkkkjjjjhxjjjjhghggfgffffffffdffffgggggghghhhhjjjkjgsggss"
			  		+ "gfgffffffffdffffgggggghghhhhjjjkjgsggssghhjksjsjlskk"
			  		+ "skskssjksjhshjsjhghhjkjkjkkkjjjjhxjjjjhghggfgffffffffdffffggggg"
			  		+ "ghghhhhjjjkjgsggssghhjksjsjlskkskskssjksjhshjsjhghhjkjkjkkkjjjj";
		  assertTrue(!addComment(content),"没有添加成功");
	  }
	  @Test
	  public void f_addcomm_inlimit() throws Exception
	  {
		  System.out.println("Case:"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  assertTrue(addComment("1234ass我来了就看看还好手机健康快乐健康 回家看见慷慨解囊阿哈哈哈哈哈是谁 收拾收拾收拾收拾事实上收拾收拾家就看见空间看看"),"没有添加成功");
	  }
	  
	  @Test
	  public void f_delcommt() throws Exception
	  {
		  assertTrue(delComment(),"没有删除成功");
	  }
	  @Test
	  public void f_replycommt() throws Exception
	  {
		  assertTrue(replyComment("回复评论ZZ！"),"");
	  }
	  
	  //收藏
	  @Test
		public void g_markAnyFile() throws Exception
		{
			enterDest("评论");
			mark();
		}
		@Test
		public void g_unmarkAnyFile() throws Exception
		{
			enterDest("评论");
			unmark();
		}
		
		//手势密码锁
		@Test
		public void h1_setOnTest() throws Exception
		{  
			setOn();
			driver.pressKeyCode(3);
			Thread.sleep(30000);
			driver.pressKeyCode(3);
			Thread.sleep(30000);
			startAS();
			assertTrue(exist("请您绘制手势密码"),"密码锁未开启");	
		}
		
		@Test
		public void h2_unlockTest() throws Exception
		{
			startAS();
			startAS();
			assertTrue(exist("请您绘制手势密码"),"密码锁未开启");
			unlock();	
		}
		
		@Test
		public void h2_modifyTest() throws Exception
		{
			modify();
		}
		
		@Test
		public void h3_setOffTest() throws Exception
		{
			setOff();
		}
		//语言
		
		@Test
		public void i_setbCh() throws Exception
		{
			startAS();
			startAS();
			langSet("简体中文");
		}
		@Test
		public void i_setaTraditional() throws Exception
		{
			startAS();
			startAS();
			langSet("繁體中文");
		}
		@Test
		public void i_setaEn() throws Exception
		{
			startAS();
			startAS();
			langSet("English");
		}
			
		@Test 
		public void i_setcChHome() throws Exception
		{
			startAS();
			startAS();
			langSetHome("简体中文");
			killAS();
		}
		@Test 
		public void i_setbTraditionalHome() throws Exception
		{
			startAS();
			startAS();
			langSetHome("繁體中文");
		}
		@Test 
		public void i_setbEnHome() throws Exception
		{
			startAS();
			startAS();
			langSetHome("English");
		}

		@Test(groups="login")
		public void z_loginDomainTest() throws Exception
		{	clearData();
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			assertTrue(loginTest("anyshare.eisoo.com","zhu.fengyue@eisoo.com","zhufy0902##"),"登录失败");
		}
}
