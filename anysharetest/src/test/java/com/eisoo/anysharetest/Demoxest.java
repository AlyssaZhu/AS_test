package com.eisoo.anysharetest;

import org.testng.annotations.Test;

import io.appium.java_client.NetworkConnectionSetting;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Demoxest extends Functions {

	//WebDriver dr;
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
  public void afterMethod() {
	  System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
  }
/*  @Test
	public void switchWifi( ) throws Exception
	{	
		System.out.println(driver.getNetworkConnection().value);
		Thread.sleep(3000);
		driver.setNetworkConnection(new NetworkConnectionSetting(1));
	  System.setProperty("webdriver.firefox.bin", "	/Applications/Firefox.app/Contents/MacOS/firefox");
	  dr=new FirefoxDriver();
	  Thread.sleep(3000);
	  dr.get("https://www.baidu.com");
	  
	}*/
 // @Test
  public void a_uploadContinueVideo() throws Exception
  {   System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
  	  enterDest("上传");
  	  assertTrue(upload(10,1,"上传视频"),"上传失败") ; 
  	  assertTrue(!pause("上传",1),"未暂停");
  	  clearUploadList();
  }
 @Test 
  public void testmanytimes1() throws Exception
  {
	  
	         a_uploadContinueVideo();
			  killAS();
	  
  }

 @Test 
 public void testmanytimes2() throws Exception
 {
	  
	         a_uploadContinueVideo();
			  killAS();
	  
 }
 @Test 
 public void testmanytimes3() throws Exception
 {
	  
	         a_uploadContinueVideo();
			  killAS();
	  
 }
 @Test 
 public void testmanytimes4() throws Exception
 {
	  
	         a_uploadContinueVideo();
			  killAS();
	  
 }



  @BeforeTest
  public void beforeTest() {
	  System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
  }

  @AfterTest
  public void afterTest() {
	  System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
  }

}
