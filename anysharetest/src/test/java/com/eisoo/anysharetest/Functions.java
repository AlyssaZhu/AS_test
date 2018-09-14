package com.eisoo.anysharetest;


import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class Functions extends BaseTest {
	//下载
	public boolean download(int num ,int waitwifi,int permission) throws Exception
	{		
		enterDest("下载",permission);
/*		WebElement  mulSelected = driver.findElementByAccessibilityId("multiple");
		mulSelected.click();*/
//		markFile(num);
//		driver.findElementByName("下载").click(); 由于界面做了修改，此处需要更新。
		manageFiles(num,"下载");
		onlyWifi(waitwifi);
		driver.findElementByName("传输").click();
		Thread.sleep(2000);
		return exist("下载完成(")||exist("正在下载(");//pass 作为下载完成的一个标志，
	}
	
	
	//但是如果上多个文件的话，不够精确；---待改进，方案如 统计具体的下载个数	
	
	//enter dictory =》上传流程=》上传细节验证
	public boolean upload(int num ,int waitwifi,String type) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickDesc("plus");
		clickName(type);
		Thread.sleep(3000);
		if(type=="上传照片")
		{
			driver.pressKeyCode(3);
			clickName("爱数 AnyShare");
			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"uploadimage\").instance(3)").click();
			driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.eisoo.anyshare:id/framelayout\").instance(0)").click();	
			clickId("com.eisoo.anyshare:id/upload_image_btn_nextstep");
		}
		
		else if(type!="拍摄上传")
		{
			if(type=="上传文件")
			{	driver.findElementByAndroidUIAutomator("new UiSelector().description(\"uploadfile\").instance(0)").click();	
			 }
			if(type=="上传视频")
			{
				Thread.sleep(3000); //首次视频加载	过慢
			}
				
			try
			{	
				markFile(num);	
				driver.findElementById("com.eisoo.anyshare:id/upload_btn_nextstep").click();//音频，视频,文件 ID上传都是一样的。		
			}
			catch (Exception e)
			{
				System.out.println("没能选中任何上传文件");
				return false;
			}
		}
		else if(type=="拍摄上传")
		{
			clickDesc("click to capture");	
			clickDesc("确定");
			clickName("开始上传");
		}
		onlyWifi(waitwifi);
		clickName("传输");
		clickName("上传列表");
		Thread.sleep(2000);
		return	exist("正在上传")||exist("上传完成");
		}
	
	 public void search(String keyWs,String type) throws Exception 
	   {
		enterDest("文件类型");
		Thread.sleep(1000);
		WebElement searchText1=driver.findElementById("com.eisoo.anyshare:id/ll_searchView");
		searchText1.click();
		Thread.sleep(2000);
		WebElement searchText2=driver.findElementById("com.eisoo.anyshare:id/et_search_content");
		if(type!="当前目录")
		{
			clickName(type);		
		}
		searchText2.sendKeys(keyWs);//所有目录都需要切换输入法
		if(type=="内链路径"||type=="外链地址")
		{
			switchInput(0);
			startAS();	
			WebElement keyboard=driver.findElementById("com.eisoo.anyshare:id/ll_select_visit_tab");
			int y=keyboard.getSize().getHeight()+keyboard.getLocation().getY()-50;
			/*driver.swipe(990, 1740, 990, 1740, 200);//P9 Ok 的坐标，采取相对坐标
*/			System.out.println(y);
			driver.swipe(990, y, 990, y, 200);
			
		}
		Thread.sleep(3000);
		/****************以下是对结果的判断***********/
		if(type=="外链地址")
		{
			assertTrue(exist(driver.findElementByClassName("android.webkit.WebView")));
		}
		else
		{
			assertTrue(fileContain(keyWs.substring(keyWs.lastIndexOf("/")+1,keyWs.length())),"没有这个文件");
	   }
	   } 
/*	//一页8个
	public void markFile(int num) throws Exception
	{
		int x=num/8;
		int y=num%8 ;
		if(num>24)
		{
			System.out.println("下载数较多执行全选下载 ");
			 clickName("全选");
		
		}	
	
		if(x>0&&num<=30)
		{	{	
			System.out.println("一共有几页："+(x+1));
			for(int j=0;j<x;j++)
		
			{	for (int i=0; i<8;i++)
				{ 		
					driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+i+")").click();
					}
			
			 // 翻屏，遍历查找到第一个没有选中的文件，勾选。
			Thread.sleep(2000);
			swipe("up");
			}
		
		}
		
		//不管是否>0,
			for (int i=y; i>0;i--)
		{	
			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+(7-i)+")").click();
		}	}
		
		//找到一个屏幕上，观察勾选项，如果超过2，说明已经不是一个屏幕；倒序点Y次即可 8 7 6 5 4 （8-i）	
		
	}*/
	

	public void markPic(int num) throws Exception
	{	
		if(num>8)
		{	System.out.println("数目较多超过8执行全选 ");
			clickName("全选");}	
		if(num<8)
		{for (int i=0; i<num;i++)
			{ driver.findElementByAndroidUIAutomator("new UiSelector().description(\"uploadfile\").instance("+i+")").click();
				}
		}
	}

	public void markUploadFile(int num) throws Exception
	{	
		if(num>8)
		{	System.out.println("上传数目较多，超过8执行全选 ");
			clickName("全选");}	
		if(num<8)
		{for (int i=0; i<num;i++)
			{ driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+i+")").click();
				}
		}
	}	

	// 尚未实现
	public boolean transNum() throws Exception
	{
		driver.findElementByName("传输").click();
		Thread.sleep(2000);
	//	int num= driver.findElementsByAccessibilityId("transfile").size();
	//	System.out.println(num);
		return exist("下载完成(")||exist("正在下载(")&&exist("等待Wi-Fi...");	
	}
	
/*	************************************* 以下是手势密码锁*****************************************************/

	public void setOn() throws Exception
	{
		startAS();
		/*clickName("我的");
		clickName("手势密码锁定");*/
		enterSet("手势密码锁定");
		WebElement gesture=driver.findElementByClassName("android.widget.CheckBox");
			gesture.click();
			lockType("setOn");
	}
	
	public void unlock() throws Exception
	{
		startAS();
		startAS();
		if(exist("请您绘制手势密码"))
		{
			lockType("unlock");
		}
		Thread.sleep(1000);
	}
	

	public void setOff() throws Exception 
	{
		unlock();	//测试了解锁
		startAS();
		/*clickName("我的");
		clickName("手势密码锁定");*/
		enterSet("手势密码锁定");
		WebElement gesture=driver.findElementByClassName("android.widget.CheckBox");
		    gesture.click();
		    lockType("setOff");
	}
	

	public void modify() throws Exception 
	{
		unlock();
		startAS();
	/*	clickName("我的");
		clickName("手势密码锁定");*/
		enterSet("手势密码锁定");
		clickName("修改密码锁");
		lockType("modify");
	}
	public void lockType(String type) throws Exception
	{		
		List <WebElement> element=driver.findElementsById("com.eisoo.anyshare:id/gesture_container");		
		WebElement  gelemt=element.get(0);
		int startX=gelemt.getLocation().getX();
		int startY=gelemt.getLocation().getY();
		int stepX=gelemt.getSize().getWidth()/4;
		int stepY=gelemt.getSize().getHeight()/4;
//		System.out.println("startX:"+startX+"startY："+startY+"stepX："+stepX+"stepy："+stepY);
		int beginX=startX+stepX;
		int beginY=startY+stepY*2/3;
		TouchAction ta=new TouchAction(driver);
		Thread.sleep(1000);
/*		if(type=="modify") //修改
		{
			for(int i=0;i<=2;i++)
			{	ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
				Thread.sleep(1000);}
		ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
		Thread.sleep(1000); 
		ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
		Thread.sleep(1000);}
		
	if (type =="setOn")
		{
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
			Thread.sleep(1000);
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
			Thread.sleep(1000);
			
		}
		if(type=="unlock"||type=="setOff")
		{
			ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
			Thread.sleep(1000);
		}
*/
		
		switch(type)
		{	
			case "setOn":
				for(int i=0;i<=1;i++)
				{	ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
					Thread.sleep(1000);}
				break;
			case "modify":
				for(int i=0;i<=2;i++)
				{	ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
					Thread.sleep(1000);}
				break;
			case "unlock":
				 
			case "setOff":
				ta.longPress(beginX,beginY).moveTo(stepX, 0).waitAction(1000).moveTo(stepX, 0).waitAction(1000).moveTo(0,stepY).waitAction(1000).moveTo(0,stepY).waitAction(1000).release().perform();
				Thread.sleep(1000);
				break;
				
			default:
				System.out.println("非法参数！ ");		
		}	
	}
	
/*	****************************收藏功能***************************************/
	public void mark() throws Exception {
		// enterDest("评论");
		 int i=0;
		 while(isMark(i))
		 {	i++;
		 	if(i>6)
		 	{	System.out.println("找不到没有收藏的文件，请检查！");
		 		break;}
		 }
		 manageFile(i,"收藏");
		 assertTrue(isMark(i),"没有收藏成功！"); 
		 WebElement e1 = driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+i+")");
		 String fileName= e1.getText();
		clickName("常用");
		clickName("收藏");
		assertTrue(exist(fileName),"收藏列表不存在！");  
	  }
	  

	  public void unmark() throws Exception
	  {	 //  enterDest("评论");
			 int i=0;
			 while(!isMark(i))
			 {	i++;
			 	if(i>6)
			 	{	System.out.println("找不到已收藏的文件，请检查！");
			 		break;}
			 }
			 WebElement e1 = driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+i+")");
			 String fileName= e1.getText();
			 System.out.println("文件名"+fileName);
			 manageFile(i,"取消收藏");
			 assertTrue(!isMark(i),"没有取消收藏！"); 	 
			clickName("常用");
			clickName("收藏");
			Thread.sleep(3000);
			assertTrue(!isMark(fileName),"收藏列表存在！");  
	  }
	  public boolean isMark(int rank)
	  {
		    try
			  {
				 WebElement e1= driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+rank+")").findElement(By.id("com.eisoo.anyshare:id/iv_file_collect"));
				
				 return true;
			  }
			  catch(Exception e)
			  {
				  return false;
			  }  	 		
		  }
	  //如果收藏文件夹可以用此方法
	  public boolean isMark(int rank, String type)
	  {
		  if(type=="文件")
		  {
			  try
			  {
				 WebElement e1= driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+rank+")").findElement(By.id("com.eisoo.anyshare:id/iv_file_collect"));
				
				 return true;
			  }
			  catch(Exception e)
			  {
				  return false;
			  }  
		  }
		  else if(type=="文件夹")
		  {
			  try
			  {
				  folder(rank).findElement(By.id("com.eisoo.anyshare:id/iv_file_collect")); 
				  return true;
			  }
			  catch(Exception e)
			  {
				  return false;
			  }
		
		  }
		  else 
			  System.out.println("类型错误!");
			  return false;
		
		  
		   
	  }
	  public boolean isMark(String fileName)
	  {
		  try
		  {
			  WebElement e1=driver.findElementByName(fileName).findElement(By.id("com.eisoo.anyshare:id/iv_file_collect"));  
			  return true;
		  }
		  catch(Exception e)
		  {
			  System.out.println("此文件在收藏列表中已不存在");
			  return false;
		  }
	  }
	  
	  public WebElement file(int rank)
	  {
		  return driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+rank+")");
	  }
	  
	  public WebElement folder(int rank)
	  {
		  return driver.findElementByAndroidUIAutomator("new UiSelector().description(\"folder\").instance("+rank+")");
	  }
	  
	 /* ------------------移动复制---------------------------*/
	  public void copy(int num, String type) throws Exception {
		  enterDest("复制");
		  if(type=="single")
		  {  manageFile(num,"复制到");  }
		  else
		  { manageFiles(num,"复制到"); }
		  moveDest("复制目标",1);
		  clickName("复制到此位置");
		  Thread.sleep(1000); 
		  startAS();
		  driver.pressKeyCode(4);
		  clickFile("复制目标");	 
	  }
	  
	  
	  public void move(int num, String type) throws Exception {
		  enterDest("移动");
		  if(type=="single")
		  {  manageFile(num,"移动到");  }
		  else
		  { manageFiles(num,"移动到"); }
		  moveDest("移动目标",1);
		  clickName("移动到此位置");
		  Thread.sleep(1000); 
		  startAS();
		  driver.pressKeyCode(4);
		  clickFile("移动目标");	 
	  }
	  
	  public void moveBack() throws Exception{
		  manageFiles(10,"移动到");
		  moveDest("移动",1);
		  clickName("移动到此位置");
		  Thread.sleep(2000); 
	  }
  
	public  int checkNum() throws Exception
	{
		if(exist("文件夹是空的"))
			return 0;
		else{
			markListFile(10);
			WebElement topTitle=driver.findElementById("com.eisoo.anyshare:id/tv_title");
		   String title=topTitle.getText();
		   String str= "";
		   int len=title.length();
		   for(int i=3;i<len-3;i++)
		   {
			  if(title.charAt(i)>=48 && title.charAt(i)<=57)
				 str+=title.charAt(i) ;	  
		   }
		   int num2=Integer.parseInt(str);
		   driver.pressKeyCode(4);
		   return num2;
		}
	}
  
	
	

  public void moveDest(String destName,int permission) throws Exception
  {
	  if(permission==0)
		{
			clickFile("共享文档");
			clickFile("zfy1");
			clickFile("只有显示权限文件夹ZFY");	
		}
		else
		{
			clickFile("个人文档");
			clickFile("vivi");
			clickFile(".自动化目录");
			if(destName!="根目录")
			clickFile(destName);				
		}	 
  }
/**********************	  评论功能**************************************/
	  
	  public boolean addComment(String content ) throws Exception
	  {
		  System.out.println("*****---------"+Thread.currentThread().getStackTrace()[1].getMethodName());
		 enterDest("评论");
		 manageFile(0,"评论");
		 startAS();// 不重新进入 ，检测不到。
		 WebElement title=driver.findElementById("com.eisoo.anyshare:id/tv_comment_title_top");
		 String title1=title.getText();
		 String str1="";
		 int len1=title1.length();
		 if(len1>2)
			 for(int i=0;i<len1;i++)
			 {
				 if(title1.charAt(i)>=48 && title1.charAt(i)<=57)
				 {
					 str1+=title1.charAt(i);
				 }
			 }
		 int num1=Integer.parseInt(str1);
		 System.out.println(num1);
		 WebElement e1=driver.findElementById("com.eisoo.anyshare:id/et_commment");
		 e1.sendKeys(content);
		 clickName("发送");
		 startAS();
		 String title2 = title.getText();
		 int len2 = title2.length();
		 String str2="";
		 if(len2>2)
			 for(int i=0;i<len1;i++)
			 {
				 if(title2.charAt(i)>=48 && title2.charAt(i)<=57)
				 {
					 str2+=title2.charAt(i);
				 }
			 }
		 int num2=Integer.parseInt(str2);
		 
		 System.out.println(num2);

		if(num2==(num1+1))
		{
			return true;
		}
		else
		{
			return false;
		}	 
	  }
	  
	 // @Test
	  public boolean delComment() throws Exception
	  {
		     System.out.println("*****-------------"+Thread.currentThread().getStackTrace()[1].getMethodName());
			 enterDest("评论");
			 manageFile(0,"评论");
			 startAS();
			 WebElement title=driver.findElementById("com.eisoo.anyshare:id/tv_comment_title_top");
			 int num1=commNum(title.getText());
			 System.out.println(num1);
			 if(num1==0)
			 { System.out.println("当前没有评论，请检查操作条件");
				 return false;}
			 WebElement comm = driver.findElementByAndroidUIAutomator("new UiSelector().description(\"comment\").instance(0)");
			 longPress(comm);
			 try
			 {
				 clickName("删除");
			 }
			 catch (Exception e)
			 {
				 System.out.println("没有删除项");
			 }
			 startAS(); 
			 int num2=commNum(title.getText());
			 System.out.println(num2);
			 if(num1==num2+1)
				 return true;
			 else 
				 return false;
			 
		//	 WebElement e1=driver.findElementById("com.eisoo.anyshare:id/et_commment");		 
	  }
	  
	  public boolean replyComment(String content) throws Exception
	  {
		    System.out.println("*****------------"+Thread.currentThread().getStackTrace()[1].getMethodName());
			 enterDest("评论");
			 manageFile(0,"评论");
			 startAS();
			 WebElement title=driver.findElementById("com.eisoo.anyshare:id/tv_comment_title_top");
			 int num1=commNum(title.getText());
			 System.out.println(num1);
			 if(num1==0)
			 { System.out.println("当前没有评论，请检查操作条件");
				 return false;}
			 WebElement comm = driver.findElementByAndroidUIAutomator("new UiSelector().description(\"comment\").instance(0)");
			 longPress(comm);
			 try
			 {
				 clickName("回复");
				 WebElement e1=driver.findElementById("com.eisoo.anyshare:id/et_commment");
				 e1.sendKeys(content);
				 clickName("发送");
			 }
			 catch (Exception e)
			 {
				 System.out.println("没有评论供删除/回复/拷贝");
			 }
			 startAS(); 
			 int num2=commNum(title.getText());
			 System.out.println(num2);
			 if(num1==num2-1)
				 return true;
			 else 
				 return false;
	  }
	  
	  public boolean copyComment() throws Exception
	  {
		    enterDest("评论");
			 manageFile(0,"评论");
			 startAS();
			 WebElement title=driver.findElementById("com.eisoo.anyshare:id/tv_comment_title_top");
			 int num1=num(title.getText());
			 System.out.println(num1);
			 if(num1==0)
			 { System.out.println("当前没有评论，请检查操作条件");
				 return false;}
			 WebElement comm = driver.findElementByAndroidUIAutomator("new UiSelector().description(\"comment\").instance(0)");
			 longPress(comm);
			 try
			 {
				 clickName("回复");
				 WebElement e1=driver.findElementById("com.eisoo.anyshare:id/et_commment");
				 longPress(e1);
				 e1.sendKeys();
				 clickName("发送");
			 }
			 catch (Exception e)
			 {
				 System.out.println("没有评论供删除/回复/拷贝");
			 }
			 startAS(); 
			 int num2=num(title.getText());
			 System.out.println(num2);
			 if(num1==num2-1)
				 return true;
			 else 
				 return false;
		  
	  }
	
	//0 代表开始， 1代表暂停
		public Boolean pauseBackup(int type) throws Exception
		{	Thread.sleep(1000);
			String backupStatus1=driver.findElementById("com.eisoo.anyshare:id/tv_backup_status").getText();
			System.out.println(backupStatus1);
			if(type==1&&backupStatus1.equals("正在上传...")||type==0&&backupStatus1.equals("已暂停"))
			{clickId("com.eisoo.anyshare:id/iv_backup_state");}
			startAS();
			return exist("已暂停");		
		}
		
		//checked 属性false时候是关闭状态，true 是开启状态
		public boolean checkBackupBt()
		{	WebElement status=driver.findElementById("com.eisoo.anyshare:id/cb_backup_switch");
			String str=status.getAttribute("checked");
			boolean checkStatus1=Boolean.parseBoolean(str);
			return checkStatus1;	  
		}
		
		public boolean isChecked(WebElement e)
		{	  String str=e.getAttribute("checked");
			  boolean status=Boolean.parseBoolean(str);
			  return status;
		}
		
		// 关闭状态要求开启 ，开启要求关闭
		public boolean setBackup(int Type) throws Exception
		{	
			if((!checkBackupBt()&&Type==1)||checkBackupBt()&&Type==0)
			{	
				clickId("com.eisoo.anyshare:id/cb_backup_switch");
				try
				{	clickName("确定");	}
				catch(Exception e)
				{	System.out.println("no confrim!");	}	
			}
			startAS();//不重新进来，检测不到元素状态变化
			return checkBackupBt();
		}
		public void choosePics(String type) throws Exception
		{
			clickName("选择自动备份相册"); 
			startAS();
		try{
		
		//	WebElement e1=driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.eisoo.anyshare:id/upload_image_gv_item_checkbox\").instance(0)");
			WebElement e1=(WebElement) driver.findElementsById("com.eisoo.anyshare:id/upload_image_gv_item_checkbox").get(0);
			if(isChecked(e1))
				e1.click();
			System.out.println("yes2");
		}
		catch(Exception e)
		{
			System.out.println("error4");
		}
			for(int i=1;i<5;i++)
			{
				WebElement e2=driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.eisoo.anyshare:id/upload_image_group_count\").instance("+i+")");
				int num=Integer.parseInt(e2.getText());
				
				if (type.equals("max"))
				{
					if(num>=80)
					{	e2.click();
						clickId("com.eisoo.anyshare:id/select_img_backup_ok");
						break;
					}
					else 
					{
						System.out.println("没有超过80的相册，请检查！");
					}
				}
				else if(type.equals("mini"))
				{
					if(num<10&&num>=1)
					{	e2.click();
						clickId("com.eisoo.anyshare:id/select_img_backup_ok");
						break;
					}
				}
					
		
			}
				driver.pressKeyCode(4);
				driver.pressKeyCode(4);
		}
	  
		  
		//这个方法是实现从字符串中筛选数字
	  public int num(String name)
	  {
			 String str1="";
			 int num1=0;
			 int len1=name.length();
			 if(len1>8)
			 {	 for(int i=0;i<len1;i++)
				 {
					 if(name.charAt(i)>=48 && name.charAt(i)<=57)
					 {	 str1+=name.charAt(i);	 }
				 }
			  num1=Integer.parseInt(str1); }
			 else 
				 num1=0;
			 return num1; 
	  }
	  
		//这个方法是实现从字符串中筛选数字
	  public int commNum(String name)
	  {
			 String str1="";
			 int num1=0;
			 int len1=name.length();
			 if(len1>2)
			 {	 for(int i=0;i<len1;i++)
				 {
					 if(name.charAt(i)>=48 && name.charAt(i)<=57)
					 {	 str1+=name.charAt(i);	 }
				 }
			  num1=Integer.parseInt(str1); }
			 else 
				 num1=0;
			 return num1; 
	  }
	  public void longPress( WebElement e1) throws InterruptedException
	  { 
		  int x=e1.getLocation().x;
		  int y=e1.getLocation().y;
		  int width=e1.getSize().width;
		  int height=e1.getSize().height;
		//  System.out.println(x+width/2+" ,"+(y+height/2));
		  driver.swipe((x+width)/2, 500, (x+width)/2, 500, 2000);
		  Thread.sleep(1000);
	  }

/******排序涉及的几个方法 viewMode clickTitle changeMode compareDate**********/
	//可以判断第一个第2个是否坐标相等
	  public boolean viewMode()
	  {		WebElement e1= (WebElement) driver.findElementsById("com.eisoo.anyshare:id/ll_content").get(0);
			WebElement e2= (WebElement) driver.findElementsById("com.eisoo.anyshare:id/ll_content").get(1);
		if(e1.getLocation().getY()==e2.getLocation().getY()) 
		{	System.out.println("高度是："+e1.getLocation().getY());
			return true;}
		else 
			return false;
	  }
	  
	  public void clickTitle()
	  {
		  clickId("com.eisoo.anyshare:id/tv_title"); 
	  }
	  public void changeMode(String type) throws Exception
	  {
		  clickId("com.eisoo.anyshare:id/tv_title");
		  clickName(type);
	  }
	  
	  public boolean compareDate(String date1,String date2) throws Exception
	  {
		  DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");//YY/MM/DD HH:mm
		  Date dt1= df.parse(date1);
		  Date dt2=  df.parse(date2);
		  System.out.println(date1);
		  System.out.println(date2);
		  System.out.println(dt1.getTime());
		  System.out.println(dt2.getTime());
		  if(dt1.getTime()>=dt2.getTime())
		  {	return true;}
		  else
			 return false;
	  }
	  
	  /* 2018-05-15 该方法目前只针对P9有效，其它手机需要适配
	  思路一：获取最后，内链的控件位置，复制/粘贴，获取复制,由于系统应用每个手机都不一样，可能不够通用   
	  思路二：获取包含内链信息的目标元素，以及其父节点，计算复制的相对坐标*/
	  
	  public void copyLink() throws Exception{
		  driver.pressKeyCode(3);
		  driver.pressKeyCode(3);
		//  driver.launchApp();
		  clickDesc("信息");
		  clickName("‪185 5181 9591‬");
		  //第一个try  没实际意义，只是为了验证API
	  try{
			  WebElement e1=driver.findElement(By.xpath("//android.widget.TextView[@text='123']/parent::*/parent::*/parent::*/parent::*"));
			  System.out.println(e1.getLocation().getY());
		  }
		  catch( Exception e)
		  {
			  System.out.println("error3!");
		  }
		  
	  try{
			  WebElement e1=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'AnyShare://vivi/')]"));
			  WebElement e2=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'AnyShare://vivi/')]/../../../.."));
			  System.out.println(e2.getLocation().getY());
			  System.out.println(e1.getLocation().getX());
			  int x=e1.getLocation().getX()+20;
			  int y=e2.getLocation().getY(); 
			  int x0= e1.getLocation().getX()+e1.getSize().width/2;
			  int y0= e1.getLocation().getY()+e1.getSize().height/2;
			 driver.swipe(x0, y0, x0, y0, 2000); //长按信息中心位置，使弹出菜单选项
			 System.out.println("观察");
			 driver.tap(1, x, y, 200);
			 driver.pressKeyCode(4);
		  }
		  catch( Exception e)
		  {
			  System.out.println("error4!");
		  }
	  }
	  
//以下内容是外链分享部分
		public boolean  setExlink(int type) throws Exception
		 {
			  WebElement status=driver.findElementById("com.eisoo.anyshare:id/tv_share_state");
			  System.out.println(status.getText());
			  if((status.getText().equals("未开启")&&type==1)||(status.getText().equals("已开启")&&type==0))
			  {
				  status.click();
				  clickId("com.eisoo.anyshare:id/cb_share_open_link");
				  Thread.sleep(1000);
				  clickName("保存");
				  Thread.sleep(3000);
			  }
			  return exist("已开启");
		  }

		public void exlinkPaste() throws Exception
		 {		
			  Thread.sleep(2000);
			  clickId("com.eisoo.anyshare:id/ll_searchView");
			  clickName("外链地址");
			  WebElement e1= driver.findElementById("com.eisoo.anyshare:id/et_search_content");
			  TouchAction ta = new TouchAction(driver);
		      ta.longPress(e1).release().perform(); // 长按
		      Thread.sleep(2000);
		      driver.swipe(170, 330, 170, 330, 100);
		      System.out.println("2");
		      Thread.sleep(3000);
		 }
		  
		 
	  public boolean manExlink(String type) throws Exception {
		  setExlink(1);
		  clickName(type);
		  switch(type)
		  {
		  case"复制外链":
			  clickName("复制外链");
			  driver.pressKeyCode(4);
			  Thread.sleep(2000);
			  exlinkPaste();
	          return !exist("搜索外链地址");
			  //搜索外链进行验证;
		  case"短信":
			 driver.findElementById("com.android.mms:id/recipients_editor").sendKeys("17301609232"); ;
			 driver.findElementByAccessibilityId("发送").click();
			 int count=driver.findElementByClassName("android.widget.EditText").getText().length();
			 System.out.println(count);
			 return(count<10);
		  case"微信好友":
			  clickName("Vivi");
			  clickName("分享");
			  clickName("返回爱数AnyShare");
			  return true;
		  case"微信朋友圈":
			  clickName("微信朋友圈");
			  clickName("发表");
			  return true;
		  case"QQ":
			  Thread.sleep(1000);
			  startAS();
			  clickName("move");
			  clickName("发送");
			  clickName("返回爱数 AnyShare");
			  return true;
		  case"邮件":
			  driver.findElementById("com.android.email:id/chips_edit").sendKeys("125426647@qq.com");
			  clickId("android:id/icon2");
			  startAS();
				return exist("新浪微博");
			  
		  case"新浪微博":
			  Thread.sleep(5000);
			  try{
				 driver.findElementByName("发送");
			  }
			  catch(Exception e)
			  {
				  System.out.println("没有发现发送按钮");
				  startAS();
			  }
			  clickName("发送");
			startAS();
			return exist("新浪微博");
		  default: 
				 return false;
		  } 
	  }
	  
	  /*************checkUI*************************/
	  //可继续扩展
	  public void checkInfo()
	   {
	 	  Collection coll=driver.findElementsByClassName("android.widget.TextView");
	 /*	  int num=driver.findElementsByClassName("android.widget.TextView").size();
	 	  System.out.println(num);
	 	  WebElement e1=(WebElement) driver.findElementsByClassName("android.widget.TextView").get(4);
	 	  System.out.println(e1.getText());
	 	  WebElement e2=(WebElement) driver.findElementsByClassName("android.widget.TextView").iterator().next();
	 	  System.out.println(e2.getText());*/
	 	  assertTrue((getE(4).getText().equals("zfy")),"Error:用户名不正确");
	 	  assertTrue((getE(6).getText().equals("vivi")),"Error:显示名不正确");
	 	  
	   }
	  public void checkSets() throws Exception
	  {
	 	 assertTrue((exist("我的消息")&&exist("设置")&&exist("版本信息")),"设置项缺失");
	 	 clickName("设置");
	 	 assertTrue(exist("清除缓存"),"error");
	 	 assertTrue(exist("默认下载位置"),"error");
	 	 
	  }
	  
	  public WebElement getE(int i)
	  {
	 	 WebElement e1=(WebElement) driver.findElementsByClassName("android.widget.TextView").get(i-1);
	 	System.out.println(e1.getText());
	 	 return e1;
	  }
	  /*****************内链共享****************/
		/*内链*/
	  //20180522
	  public void  switchAccount(String name,String psd) throws Exception
	  {
		  logout();
		  Thread.sleep(3000);
		  driver.findElementById("com.eisoo.anyshare:id/et_account").sendKeys(name);
		  driver.findElementById("com.eisoo.anyshare:id/et_password").sendKeys(psd);
		  driver.pressKeyCode(4);
		  clickName("登录");
		  Thread.sleep(3000);
		  startAS();   
	  }
	  
	  public boolean nameExist(String name) throws Exception
		{
		    Thread.sleep(2000);
				if(exist(name))
					return true;
			else
			{
					for(int i=0;i<5;i++)
			  		{  System.out.println("滑动"+i+"次");
			  			swipe("down");
			  			if(exist(name))
			  				return true;
			  		}
					return false;
			}
		}
	  
	  
	  public void delVisitor() throws Exception
	  {
		  clickFile("删除") ;
		  clickName("保存"); 
	  }
	  
	  //通用的方式是找到父节点，在找子节点
	  public void addVisitor() throws Exception
	  {
		  clickName("添加访问者");
		  clickFile("ZZ");
		  clickId("com.eisoo.anyshare:id/rl_checkbox");

		  clickId("com.eisoo.anyshare:id/tv_ok");
		  clickName("保存");
		  Thread.sleep(1000);
	  }
	  

}