package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FullFuctionTest extends Functions {
	
/*	oooo
	上传
	下载
	文件列表
	打开 
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
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传视频"),"上传失败") ;  
	  	  clearUploadList();
	  }
	  @Test 
	  public void a_uploadAudio() throws Exception
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传音频"),"上传失败");
	  	  clearUploadList();  
	  	  switchWifi(1); 
	  }
	  @Test 
	  public void a_uploadFile() throws Exception
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传文件"),"上传失败");
	  	  clearUploadList();  
	  }

	  	  
	  @Test 
	  public void a_uploadTakePhoto() throws Exception
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"拍摄上传"),"上传失败");
	  	  clearUploadList();  
	  }
	    
	  @Test 
	  public void a_uploadPics() throws Exception
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传照片"),"上传失败");
	  	  clearUploadList();  
	  }

	  @Test //2017-12-15
	  public void a_uploadWaitWifi() throws Exception
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  switchWifi(0);
	  	  enterDest("上传");
	  	  assertTrue(upload(1,1,"上传视频"),"上传失败");
	  	  assertTrue(exist("等待Wi-Fi..."),"不存在等待Wi-Fi标记");
	  	  clearUploadList(); 
	  	  killAS();
	  	  switchWifi(1);
	  }

	  @Test //2017-12-17
	  public void a_uploadNoWifi() throws Exception
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	      switchWifi(1);
	      startAS();
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
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(6,1,"上传视频"),"上传失败") ;  
	  	  clearUploadList();
	  }

	  @Test
	  public void a_uploadAllVideo() throws Exception
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(10,1,"上传视频"),"上传失败") ;  
	  	  clearUploadList();
	  }

	  @Test
	  public void a_uploadPauseVideo() throws Exception
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  switchWifi(1);
	  	  enterDest("上传");
	  	  assertTrue(upload(10,1,"上传视频"),"上传失败") ;  
	  	  assertTrue(pause("上传",0),"未暂停");
	  	  clearUploadList();
	  }
	  @Test
	  public void a_uploadContinueVideo() throws Exception
	  {   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  	  enterDest("上传");
	  	  assertTrue(upload(10,1,"上传视频"),"上传失败") ; 
	  	  assertTrue(!pause("上传",1),"未暂停");
	  	  clearUploadList();
	  }
  	  
	  @Test
	  public void a_uploadNoPermission() throws Exception
	  {   	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
	  		enterNoPermission("");
	  		assertTrue(!upload(10,1,"上传视频"),"上传失败") ;  
	  	//  clearUploadList();
	  }
	  
	  //20180525
	   @Test
	  public void a_pauseAll() throws Exception
	  {
		  enterDest("上传");
		  assertTrue(upload(10,1,"上传音频"),"上传失败") ; 
		  assertTrue(exist("全部暂停"),"不存在全部暂停按钮");
		  clickName("全部暂停");
		  assertTrue(!exist("正在上传..."),"存在正在上传");  
		  clickName("全部开始");
		  assertTrue(exist("正在上传..."),"不存在正在上传"); 
		  assertTrue(!exist("已暂停"),"存在已暂停"); 
		  clearUploadList(); 
	  }
	
	 /* ----------------下载-----------------------------*/
	//普通下载 开启仅在Wi-Fi&&WiFi环境
		@Test(enabled=true) 
		public void  b_downldAFile() throws Exception
		{
			System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			switchWifi(1);
			assertTrue(download(1,1,1),"下载失败！");
			assertTrue(exist("下载完成(1)",15),"下载失败！");
		}
			
		@Test(enabled=true) 
		public void b_downldManyFile() throws Exception
		{
			System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			switchWifi(1);
			startAS();
			assertTrue(download(10,1,1),"下载失败！");
		}
		
		
		@Test(enabled=true) 
		public void b_downldMultiFile() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			assertTrue(download(6,1,1),"下载失败！");
			clearCache();
		}

		@Test(enabled=true) 
		public void b_downldAllFile() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			assertTrue(download(30,1,1),"下载失败！");
			clearCache();
		//	killAS();
		}
		
		@Test //case:开启仅在Wi-Fi&&WiFi-> 切换为4G的环境（选“继续传输”）
		public void b1_downldNoWifi() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			switchWifi(1); 
			Thread.sleep(2000);
			switchWifi(0);
			assertTrue(download(1,0,1),"下载失败！");
			assertTrue(!exist("等待Wi-Fi..."),"存在等待Wi-Fi标记");
			clearCache();
			killAS();
			switchWifi(1);
		}
		
		//之前在应用外尝试切网，发现没有反应，因此调整切换Wi-Fi的方法顺序
		//case:开启仅在Wi-Fi&&WiFi-> 切换为4G的环境（选“仅在Wi-Fi”）
		@Test 
		public void b2_downldWaitWfi() throws Exception
		{
			System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			switchWifi(1);
			Thread.sleep(2000);
			switchWifi(0);
			assertTrue(download(2,1,1),"下载失败！");
			assertTrue(exist("等待Wi-Fi..."),"不存在等待Wi-Fi标记");
			clearCache();
			killAS();
			switchWifi(1);
		}
		
		//普通下载 开启仅在Wi-Fi&&WiFi环境
		@Test
		public void  b2_downldNoPermisison() throws Exception
		{
			System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			assertTrue(!download(1,1,0),"没有权限的文件能够下载成功！");
			clearCache();
		}
		
		//文件列表
		@Test
		  public void c_delFile() throws Exception{
			System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  enterDest("上传");
			  String fileName=manageFile(1,"删除");
			  clickName("确定");
			  assertTrue(!exist(fileName),"已删除!");   
		  }
			@Test
		 	public void c_delFiles() throws Exception
		 	{System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		    	enterDest("上传");
		 		manageFiles(2,"删除");
			  clickName("确定");
		 	}
			@Test
			public void c_renameFile() throws Exception
			{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				enterDest("文件列表");
			 	manageFile(2,"重命名");
			 	WebElement e1= driver.findElementByClassName("android.widget.EditText");
			 	String fname1=e1.getText();
			 //	e1.sendKeys(fname1+"1"); 数值增加太快
			 	e1.sendKeys("0A"); 
			 	clickName("确定"); 
			  }

			@Test
		   public void c_searchKws() throws Exception
		   {	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  search("04","当前目录");
		   }
			@Test
		   public void c_searchAlldir() throws Exception
		   {	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  search("04","所有目录");
		   }
		   @Test
		   public void c_asearchInlink() throws Exception
		   {	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			   search("AnyShare://vivi/.自动化目录/文件类型/word/Android自动化测试.doc","内链路径");
			   switchInput(1);
			   startAS();
			   killAS();  
		   }
		   
		   @Test
		   public void c_searchExlink() throws Exception
		   {  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  search("http://116.236.224.243:80/link/7F353C66D9131673ED85316ADA566591 有效期限：2018-02-10 ","外链地址");
			  switchInput(1);
			  startAS();
			  killAS();
		   }
		

	//打开
		  @Test
		  public void d_openVideo() throws Exception {
			System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			enterDest("文件类型");
			clickFile("视频");
			clickFile(1);
			Thread.sleep(15000);
			String progress=driver.findElementById("com.eisoo.anyshare:id/video_time_current").getText();
			System.out.println("当前进度为："+progress);
			assertTrue(!progress.equals("00:00"),"进度为0");
			killAS();
		  }
		 @Test
		  public void d_openAudio() throws Exception {
			System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
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
		  {	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  enterDest("文件类型");
			  clickFile("word");
			  clickFile(1);
			  Thread.sleep(10000);
			  clickName("用第三方应用打开");
			  clickName("WPS Office");
			  Thread.sleep(6000);
			  assertTrue(exist("编辑"),"文件未打开!");  
			  killAS();
		  }
		  
		  @Test
		  public void d_openPic() throws Exception
		  {
			  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  enterDest("文件类型");
			  clickFile("图片");
			  clickFile(1);
			  Thread.sleep(6000);
			  WebElement e1=driver.findElementById("com.eisoo.anyshare:id/iv_photo");
			  assertTrue(exist(e1),"文件未打开!");  
			  killAS();
		  }
		  @Test//按钮打开
		  public void d_openExcel() throws Exception {
			  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  enterDest("文件类型");
			  clickFile("excel");
			  manageFile(0,"打开");
			  Thread.sleep(10000);
			  clickName("WPS Office");	
			  Thread.sleep(12000);
			  try{
				  driver.findElementByName("编辑");
			  }
			  catch (Exception e)
			  {
				  Thread.sleep(5000);  
			  }
			  if(exist("不保存"))  //new
			  { clickName("不保存");}
			  Thread.sleep(1000);
			  assertTrue(exist("编辑"),"文件未打开!");  
			  killAS();
			  Thread.sleep(1000);
			  if(exist("不保存"))  //new
			  { clickName("不保存");}
			  killAS();
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
				Thread.sleep(2000);
				killAS();
			}
			@Test
			public void e_copyAllFiles() throws Exception
			{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				enterDest("复制目标");
				delFiles(10);
				killAS();
				copy(10,"");
				delFiles(10); 
			}
				
			@Test
			public void e_moveFile() throws Exception
			{ enterDest("移动目标");
				startAS();
				if(!exist("文件夹是空的"))
				{moveBack();}
				killAS();
				startAS();
				System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
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
				Thread.sleep(2000);
				killAS();
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
	  {	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  String content="123456788ABCabc 评论！"  ;
		  assertTrue(addComment(content),"没有添加成功");
		  assertTrue(exist(content),"没有找到评论内容");
		  
	  }
	  @Test
	  public void f_addcomm_overlimit() throws Exception
	  {	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
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
		  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  assertTrue(addComment("1234ass我来了就看看还好手机健康快乐健康 回家看见慷慨解囊阿哈哈哈哈哈是谁 收拾收拾收拾收拾事实上收拾收拾家就看见空间看看"),"没有添加成功");
	  }
	  
	  @Test
	  public void f_delcommt() throws Exception
	  {	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  assertTrue(delComment(),"没有删除成功");
		  killAS();
	  }
	  @Test
	  public void f_replycommt() throws Exception
	  {	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		  assertTrue(replyComment("回复评论ZZ！"),"");
	  }
	  
	  //收藏
	  @Test
		public void g_markAnyFile() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			enterDest("评论");
			mark();
		}
		@Test
		public void g_unmarkAnyFile() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			enterDest("评论");
			unmark();
		}
		
		//手势密码锁
		@Test
		public void h1_setOnTest() throws Exception
		{   System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
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
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			startAS();
			assertTrue(exist("请您绘制手势密码"),"密码锁未开启");
			unlock();	
		}
		
		@Test
		public void h2_modifyTest() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			modify();
			killAS();
		}
		
		@Test
		public void h3_setOffTest() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			setOff();
			killAS();//20180529
		}
		//语言
		
		@Test
		public void i_setbCh() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			startAS();
			langSet("简体中文");
		}
		@Test
		public void i_setaTraditional() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			startAS();
			langSet("繁體中文");
		}
		@Test
		public void i_setaEn() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			Thread.sleep(1000);//20180529
			startAS();
			langSet("English");
		}
		
		@Test 
		public void i_setcChHome() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			startAS();
			langSetHome("简体中文");
			log_agin();
			killAS();
		}
		@Test 
		public void i_setbTraditionalHome() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			startAS();
			langSetHome("繁體中文");
		}
		@Test 
		public void i_setbEnHome() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			startAS();
			langSetHome("English");
		}
	
		//backup
		@Test
		public void j1_checkdefaultUI() throws Exception
		{
			 System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			 startAS();
			 clickName("传输");
			 clickName("上传列表");
			 assertTrue(!exist("自动备份任务"),"error1：默认是开启状态!");
			/* clickName("我的");
			 clickName("备份设置");	*/ 
			 enterSet("备份设置");
			 assertTrue(!checkBackupBt(),"error2:默认是开启状态！"); 
		}
		
		@Test
		public void j2_startBackup() throws Exception
		{
			System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			enterSet("备份设置");
			assertTrue(setBackup(1),"error1：按钮未开启");
			driver.pressKeyCode(4);
			Thread.sleep(2000);
			driver.pressKeyCode(4);
			clickName("传输");
			clickName("上传列表");
			assertTrue(exist("自动备份任务"),"error2：开启后上传列表没有备份任务!");
			WebElement e=driver.findElementById("com.eisoo.anyshare:id/tv_backup_title");
			int num1=num(e.getText());
			System.out.println("剩余备份数"+num1);	
			Thread.sleep(15000);
			int num2=num(e.getText());
			System.out.println("剩余备份数"+num2);	
			assertTrue((num1>num2||num1==0),"error3:没有进行备份！");
		}
		
		/*测试暂停备份，继续备份*/
		@Test
		public void j3_pauseBackup() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			clickName("传输");
			clickName("上传列表");
			if(!exist("自动备份任务"))
			{
				enterSet("备份设置");
				setBackup(1);
				driver.pressKeyCode(4);
				Thread.sleep(2000);
				driver.pressKeyCode(4);
				clickName("传输");
				clickName("上传列表");
			}
			//新增判断，备份完成时，切换相册继续备份
			if(exist("照片自动备份完成"))
			{ enterSet("备份设置");
			  choosePics("max");
			  Thread.sleep(2000);
				driver.pressKeyCode(4);
				clickName("传输");
				clickName("上传列表");
				}
			assertTrue(pauseBackup(1),"error1:没有暂停");
			Thread.sleep(2000);
			assertTrue(!pauseBackup(0),"error2:暂停了");
		}
		
		@Test
		public void j4_wifiBackup() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			clickName("传输");
			clickName("上传列表");
			if(!exist("自动备份任务"))
			{
				enterSet("备份设置");
				setBackup(1);
				driver.pressKeyCode(4);
				Thread.sleep(2000);
				driver.pressKeyCode(4);
				clickName("传输");
				clickName("上传列表");
			}
			//新增判断，备份完成时，切换相册继续备份
			if(exist("照片自动备份完成"))
			{ enterSet("备份设置");
			  choosePics("max");
			  Thread.sleep(2000);
				driver.pressKeyCode(4);
				clickName("传输");
				clickName("上传列表");
				}
			switchWifi(0);
			Thread.sleep(1000);
			startAS();	
			assertTrue(exist("等待Wi-Fi..."),"error:非wifi 下没有等待");
			switchWifi(1);
			Thread.sleep(1000);
			startAS();	
			assertTrue(exist("正在上传...")||exist("照片自动备份完成"),"error:wifi 下没有继续");
		}
		
		/*最好选择数目比较小的相册进行备份已方便快速备份完成
		取消原来的相册，选择数目较少的相册*/
		@Test
		public void j5_chooseDest() throws Exception
		{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			enterSet("备份设置");
			assertTrue(setBackup(1),"error1：按钮未开启");
			choosePics("mini");
			Thread.sleep(2000);
			driver.pressKeyCode(4);
			clickName("传输");
			clickName("上传列表");
			Thread.sleep(30000);	
			assertTrue(exist("照片自动备份完成"),"30s内备份不到9张照片！");
		}
		@Test
		public void j6_stopBackup() throws Exception
		{	
			 System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			startAS();
			enterSet("备份设置");
			assertTrue(!setBackup(0),"error1：按钮仍然开启");
		}

		
		@Test
		public void k_timeOrder() throws Exception 
		{	 System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			enterDest("根目录");
		//	clickName("vivi");
			 changeMode("按时间倒序排序"); //获取页面时间标签，逐个比较
			 for(int i=0;i<5;i++)
			 {	WebElement e1=(WebElement) driver.findElementsById("com.eisoo.anyshare:id/tv_file_time").get(i);
			 	WebElement e2=(WebElement) driver.findElementsById("com.eisoo.anyshare:id/tv_file_time").get(i+1);
			 	System.out.println("比较第"+i+"次！");
			 	assertTrue(compareDate(e1.getText(),e2.getText()),i+"时间顺序有误！");
			 }
			changeMode("按文件名称排序");
		  }

		 @Test
		 public void k_thumbnail_View() throws Exception
		  {
			 System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			 enterDest("根目录");
			 changeMode("缩略图视图");
			 startAS();
			 assertTrue(viewMode(),"非缩略图视图模式");
			 changeMode("列表视图");
			 assertTrue(!viewMode(),"非列表视图模式");
		  }
		
		 @Test
		  public void l_cancelLink() throws Exception {
			 System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			 Thread.sleep(3000);
			 copyLink();
			 driver.pressKeyCode(3); //new
			 clickName("爱数 AnyShare");
			 Thread.sleep(3000);
			 try{
				 driver.findElementByName("查看");
			 }
			 catch(Exception e){
				 driver.pressKeyCode(3); //new
				 clickName("爱数 AnyShare");
			 }
			  assertTrue(exist("查看"),"不存在定位弹框！");
			  clickName("取消");  
		  }
		  
		  @Test
		  public void l_openLink() throws Exception
		  {
			  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  Thread.sleep(3000);
			  copyLink();
			  driver.pressKeyCode(3); //new
				 clickName("爱数 AnyShare");
				 Thread.sleep(3000);
				 try{
					 driver.findElementByName("查看");
				 }
				 catch(Exception e){
					 driver.pressKeyCode(3); //new
					 clickName("爱数 AnyShare");
				 }
			  assertTrue(exist("查看"),"不存在定位弹框！");
			  clickName("查看"); 
			  Thread.sleep(2000);
			  assertTrue(exist(driver.findElement(By.name("共享"))),"不存在此元素！");
		  }
		  
		  //放到最后主要是为了防止有弹框出现时，进行取消
		   @Test
		  public void l_znolink() throws Exception
		  {
			 System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			
			  Thread.sleep(3000);
			  startAS();
			  if(exist("取消"))
			  {
				  clickName("取消");
				  System.out.println("error: 内链弹框显示");
			  }
			  enterDest("共享");
			  manageFile(0,"内链共享");
			  clickName("复制内链");
			  startAS();
			  assertTrue(!exist("查看"),"存在定位弹框！");
		  }
		   
		   @Test
		   public void m_shareMMS() throws Exception
		   {		
		 	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		 	  enterDest("共享");
		 	  manageFile(0,"外链分享");
		 	  assertTrue(manExlink("短信"),"未完成短信分享");  
		 	  killAS();
		   }
		   
		   @Test
		   public void m_shareWechat() throws Exception
		   {
		 	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		 	  enterDest("共享");
		 	  manageFile(0,"外链分享");
		 	  assertTrue(manExlink("微信好友"),"未完成微信分享");  
		   }
		   
		   @Test
		   public void m_shareQQ() throws Exception
		   {
		 	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		 	  enterDest("共享");
		 	  manageFile(0,"外链分享");
		 	  assertTrue(manExlink("QQ"),"未完成QQ分享");  
		   }
		   
		   @Test
		   public void m_shareWeibo() throws Exception
		   {
		 	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		 	  enterDest("共享");
		 	  manageFile(0,"外链分享");
		 	  assertTrue(manExlink("新浪微博"),"未完成微博分享");  
		   }
		   
		   @Test
		   public void m_shareEmail() throws Exception
		   {
		 	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		 	  enterDest("共享");
		 	  manageFile(0,"外链分享");
		 	  assertTrue(manExlink("邮件"),"未完成邮件分享");  
		   }
		   
		   @Test
		   public void m_copyExlink() throws Exception
		   {
		 	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		 	  enterDest("共享");
		 	  manageFile(0,"外链分享");
		 	  assertTrue(manExlink("复制外链"),"未完成复制外链");  
		 	  killAS(); //20180529
		   }
		  
		   @Test
		   public void m_closeExlink() throws Exception
		   {
			  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		 	  enterDest("共享");
		 	  manageFile(1,"外链分享");
		 	  assertTrue(setExlink(1),"未开启");
		 	  driver.pressKeyCode(4);
		 	  manageFile(1,"外链分享");
		 	  assertTrue(!setExlink(0),"未关闭");  
		   }
		@Test
		public void a_checkUI() throws Exception {
			  startAS();
			  assertTrue(exist("文档"),  "no 文档!");
			  assertTrue(exist("常用"),  "no 常用!");
			  assertTrue(exist("传输"),  "no 传输!");
			  clickName("我的");
			  clickId("com.eisoo.anyshare:id/ll_personal_userinfo");
			  checkInfo();
			  driver.pressKeyCode(4);
			  Thread.sleep(2000);
			  checkSets();  
		  }
		

		  @Test
		  public void n1_addOne() throws Exception{
			  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  enterDest("共享");  
			  manageFile(2,"内链共享");
			  WebElement e1= driver.findElementByName("保存");
			  assertTrue(!e1.isEnabled(),"当前保存按钮为可用状态，未编辑应该不可用！");
			  if(!nameExist("王十一"))
			  { addVisitor();
			  	Thread.sleep(2000);
				manageFile(2,"内链共享");
				assertTrue(nameExist("王十一"),"不存在此用户名"); 
				}
			  killAS();
		  }
		
		  @Test
		  public void n1_checkAdd() throws Exception
		  {	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  startAS();
			  switchAccount("王十一","123123");
			  clickName("共享文档");
			  assertTrue(exist("vivi"),"不存在被分享的文件");
			  clickName("我的");
			  clickName("我的消息");
			  startAS();
			  WebElement e1= (WebElement) driver.findElementsById("com.eisoo.anyshare:id/tv_file_send").get(0);
			  assertTrue(e1.getText().contains("vivi给您共享了"),"第一条记录不是共享");   
		  }
		  
		  @Test
		  public void n2_delOne() throws Exception{
			  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  Thread.sleep(2000);
			  startAS(); 
			  switchAccount("zfy","123123");
			  enterDest("共享");  
			  manageFile(2,"内链共享");
			  WebElement e1= driver.findElementByName("保存");
			  assertTrue(!e1.isEnabled(),"当前保存按钮为可用状态，未编辑应该不可用！");
			  if(nameExist("王十一"))
			  { delVisitor();
			     Thread.sleep(2000);
			     manageFile(2,"内链共享");
				 assertTrue(!nameExist("王十一"),"不存在此用户名");
			  }
			  killAS();
		  }
		
		  @Test
		  public void n3_checkdel() throws Exception
		  {	  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  startAS();
			  switchAccount("王十一","123123");
			  startAS();
			//  clickName("共享文档");
			//  assertTrue(exist("vivi"),"不存在被分享的文件");
			  clickName("我的");
			  clickName("我的消息");
			  startAS();
			  WebElement e1= (WebElement) driver.findElementsById("com.eisoo.anyshare:id/tv_file_send").get(0);
			  assertTrue(e1.getText().contains("vivi给您取消共享"),"第一条记录不是共享");   
		  }
		  
		  // 目的是为了将账号切回来继续测试，检查保存按钮默认状态
		  @Test
		  public void n4_share() throws Exception {
			  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
			  startAS();
			  switchAccount("zfy","123123");
			  enterDest("共享");  
			  manageFile(1,"内链共享");
			  WebElement e1= driver.findElementByName("保存");
			  assertTrue(!e1.isEnabled(),"当前保存按钮为可用状态，未编辑应该不可用！");
			  if(!nameExist("王十一"))
			  { addVisitor();
			  	}
			  else 
				  driver.pressKeyCode(4);
			 Thread.sleep(2000);
			 manageFile(1,"内链共享");
			 assertTrue(nameExist("王十一"),"不存在此用户名");   
			 delVisitor();
			 Thread.sleep(2000);
			 manageFile(1,"内链共享");
			 assertTrue(!nameExist("王十一"),"存在此用户名"); 
		  }
	//	  @Test
			public void z_loginDomainTest() throws Exception
			{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
				Thread.sleep(3000);
				driver.pressKeyCode(4);
				driver.pressKeyCode(4);
				clearData();
				startAS();
				assertTrue(loginTest("anyshare.eisoo.com","xxx","xxxx"),"登录失败");
			}
		/*  
		  @Test
		  public void o_cancelLink() throws Exception {
			  Thread.sleep(3000);
			  copyLink();
			  startAS();
			  Thread.sleep(2000);
			  assertTrue(exist("查看"),"不存在定位弹框！");
			  clickName("取消");  
		  }
		  
		  @Test
		  public void o_openLink() throws Exception
		  {
			  Thread.sleep(3000);
			  copyLink();
			  startAS();
			//  startAS();
			  Thread.sleep(2000);
			  assertTrue(exist("查看"),"不存在定位弹框！");
			  clickName("查看"); 
			  Thread.sleep(2000);
			  assertTrue(exist(driver.findElement(By.name("共享"))),"不存在此元素！");
		  }
		  
		   @Test
		  public void o_znolink() throws Exception
		  {  Thread.sleep(3000);
			   startAS();
			   if(exist("取消")){
				   clickName("取消");   
			   }
			  enterDest("共享");
			  manageFile(0,"内链共享");
			  clickName("复制内链");
			  startAS();
			  assertTrue(!exist("查看"),"存在定位弹框！");
		  }*/
		  
}
