package com.eisoo.anysharetest;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidKeyCode;

public class CopyMoveTest extends Functions {
	
	
	@BeforeClass
	public void login() throws Exception
	{
		init();
		assertTrue(loginTest("116.236.224.243","zfy","123123"),"登录失败！");
		enterDest("复制目标");
		delFiles(10); 
		killAS();
	}
	@AfterClass
	public void quit()
	{	
		driver.quit();
	}
	@AfterMethod
	public void sleep() throws Exception
	{    
		  killAS();
	}
	
	//复制个数
	@Test
	public void copyFile() throws Exception
	{
		System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		copy(1,"");
		assertTrue((checkNum()>=1),"文件数小于复制个数！");
		delFiles(10); 
	}
	@Test
	public void copyFileSingle() throws Exception
	{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		copy(1,"single");
		assertTrue((checkNum()>=1),"文件数小于复制个数！");
		delFiles(10); 
	}
	 @Test
	public void copyFiles() throws Exception
	{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		copy(3,"");
		assertTrue((checkNum()>=3),"文件数小于复制个数！");
		delFiles(10); 
	}
	@Test
	public void copyAllFiles() throws Exception
	{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		copy(10,"");
		delFiles(10); 
	}
	
	
	@Test
	public void moveFile() throws Exception
	{  System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		move(1,"");
		assertTrue((checkNum()>=1),"文件数小于移动个数！");
	}
	@Test
	public void moveFileSingle() throws Exception
	{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		move(1,"single");
		assertTrue((checkNum()>=1),"文件数小于移动个数！");
		
	}
	 @Test
	public void moveFiles() throws Exception
	{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		move(3,"");
		assertTrue((checkNum()>=3),"文件数小于移动个数！");
		moveBack();
	}
	@Test
	public void moveAllFiles() throws Exception
	{	System.out.println("CaseId"+(++caseId)+":"+Thread.currentThread().getStackTrace()[1].getMethodName());
		move(10,"");
	//	delFiles(10);
		moveBack();
	}
	
	  
	

	//思路：进入复制目录，选择

/*  public void copyFile() throws Exception {
	  enterDest("复制");
	  manageFile(6,"复制到"); 
	  moveDest("复制目标",1);
	  clickName("复制到此位置");
	  Thread.sleep(1000); 
	  driver.pressKeyCode(4);
	  startAS();
	  clickFile("复制目标");
	  assertTrue((checkNum()>0),"文件数小于复制个数！");
	  delFiles(10); 
  }
  

  public void copyFiles() throws Exception{
	  enterDest("复制");
	  manageFiles(5,"复制到");
	  moveDest("复制目标",1);
	  clickName("复制到此位置");
	  Thread.sleep(1000);
	  driver.pressKeyCode(4);
	  startAS();
	  clickFile("复制目标");// 检查文件数超过5个
	  assertTrue((checkNum()>0),"文件数小于复制个数！");
	  delFiles(10); 
  }*/
 
 
/* @Test
  public void moveFile() throws Exception
  {
	  enterDest("移动目标");
	  startAS();
	 int fileNum1 =checkNum();
	 driver.pressKeyCode(4);
	 clickFile("移动");
	  manageFile(0,"移动到");
	  moveDest("移动目标",1);
	  clickName("移动到此位置");
	  Thread.sleep(2000);
	  driver.pressKeyCode(4);
	  startAS();
	  clickFile("移动目标");
	  startAS();
	  int fileNum2=checkNum();
	  assertTrue((fileNum1+1==fileNum2),"目标位置未新增文件");  
  }*/
 
  /*@Test
  public void moveFiles() throws Exception
  {
	  enterDest("移动目标");
	  startAS();
	  int fileNum1 =checkNum();
	  driver.pressKeyCode(4);
	  clickFile("移动");
	  manageFiles(3,"移动到");
	  moveDest("移动目标",1);
	  clickName("移动到此位置");
	  Thread.sleep(2000);
	  driver.pressKeyCode(4);
	  startAS();
	  clickFile("移动目标");
	
	  int fileNum2=checkNum();
	  assertTrue((fileNum1+3==fileNum2),"目标位置未新增对应文件数");
	  manageFiles(10,"移动到");
	  moveDest("移动",1);
	  clickName("移动到此位置");
	  Thread.sleep(2000);   
  }*/
	
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
  
  //由于弹框不能识别，暂时没有好的方案处理。
  //备用方案，点击坐标，需要计算（风险：不同设备相对坐标是否一致；无法确认弹出的是同名弹框，还是其它；）
  public void sameNameConflic()
  {
	 //  System.out.println("caseId"+(++caseId)+""+Thread.currentThread().getStackTrace()[1].getMethodName());
  }
}
