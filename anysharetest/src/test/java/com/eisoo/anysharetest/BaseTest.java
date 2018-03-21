package com.eisoo.anysharetest;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
	AndroidDriver driver;
	//初始化driver
	@BeforeClass
	public void BeforeClass() throws Exception
	{
	 	DesiredCapabilities cap=new DesiredCapabilities();
		//	cap.setCapability("app", app.getAbsolutePath());
	 	//  cap.setCapability("app","app 路径");
	 	 //	cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "600");// 超时时间600s'
	   	cap.setCapability("deviceName", "testyy"); // deviceName要执行的测试设备名字
		cap.setCapability("platformName", "Android");
		cap.setCapability("MobileCapabilityType.AUTOMATION_NAME", "Appium");
		cap.setCapability("platformVersion", "7.0");
		cap.setCapability("appPackage", "com.eisoo.anyshare");
		cap.setCapability("appActivity", ".AppStartActivity");
		cap.setCapability("unicodeKeyboard", "True");
		cap.setCapability("resetKeyboard", "true");
		cap.setCapability("noSign", "True");
		cap.setCapability("sessionOverride", true); 
		driver =new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//设置隐式等待超时时间2秒
		Thread.sleep(3000);
		System.out.println(this.getClass().getName());
	}
	//进入文件夹
	public void enterFolder(String folderName) throws Exception
	{
		swipefindElement(folderName);
		driver.findElementByName(folderName).click();
	}
	//点击文件，clickFile有滚动查找功能
	public void clickFile(String fileName) throws Exception
	{
		swipefindElement(fileName);
		driver.findElementByName(fileName).click();	
		
	}
	//点击xx名称的元素
	public void clickName(String elementName) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElementByName(elementName).click();	
	}
	public void clickDesc(String elementDesc)
	{
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElementByAccessibilityId(elementDesc).click();
	}
	public void clickId(String elementDesc)
	{
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElementById(elementDesc).click();
	}
	public void killAS() throws Exception 
	{ 
		//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);//设置隐式等待超时时间2秒
		System.out.println("执行1次kill AS");
		driver.pressKeyCode(4);
		driver.pressKeyCode(4);
		driver.pressKeyCode(4);
		if(!exist("爱数 AnyShare" ,1))
			{	driver.pressKeyCode(4);
				driver.pressKeyCode(4);
			}	
		Thread.sleep(1000);
	}
	
	 public void enterAS() throws Exception
	 {
		 if(elementNameExist("爱数 AnyShare"))
			clickName("爱数 AnyShare");
	//	 clickName("vivi");
	 }

	public void startAS() throws Exception 
	{ 		if(!elementNameExist("爱数 AnyShare"))
			driver.pressKeyCode(3);
			clickName("爱数 AnyShare");
	}
	

  	//以元素名称判断元素是否存在，最好用于唯一的名称
  	public boolean exist(String name)
  	{
  		if(driver.getPageSource().contains(name))
  		{	System.out.println("存在标签"+name);
  			return true;}
  
  		else
		 	return false;
  		//	driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"下载完成(1\")");//Fail
  		//	driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"下载完成(1)\")");//pass
  		//	driver.findElementByAndroidUIAutomator("new Uiselector().textStartsWith(\"下载完成(\")");//FAil		
  		//driver.findElementByName("下载完成(\\S)"); //Fail
  		//driver.findElementByName("下载完成\\("+".*");//Fail
  		//driver.getPageSource().contains("下载完成("); //Pass
  		//driver.getPageSource().contains("下载完成\\("+".*");//Pass
  		//driver.getPageSource().contains(name+".*");//pass	
		
  	}
  	
  	//以元素名称判断元素是否存在，时间及名称
  	public boolean exist(String name,int time)
  	{
  		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);//设置隐式等待超时时间2秒
  		if(driver.getPageSource().contains(name))
  		{	System.out.println("存在标签"+name);
  			return true;}
  
  		else
		 	return false;	
  	}
  		
  	
	public void clearCache() throws Exception
	{
		System.out.println("清缓存");
		clickName("我的");
		clickName("清除缓存");
		clickName("确定");	
	}
	
	//this is destinastion path
	public void enterDest(String destName,int permission) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(5000);// 这2句主要是为了应付框架点不中的Bug
		startAS();
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
			clickFile(destName);				
		}
		
	}
	public void enterDest(String destName) throws Exception
	{ 	Thread.sleep(1000);
		driver.pressKeyCode(3);
		driver.pressKeyCode(3);//有时候不灵敏，需要多点次
		clickName("爱数 AnyShare");
		Thread.sleep(3000);//不休眠，还没有进来
		driver.pressKeyCode(3);
		clickName("爱数 AnyShare");
	 //	Thread.sleep(3000);
	/**************20171212.首页改进不需要点击个人文档，而5.0.18.2之前需要******************
	 * 	try
		{clickName("个人文档");}//程序必须先启动，按home再次进来，才能点中个人文档，疑似框架的问题。
		catch(Exception e)
		{	driver.pressKeyCode(3);
			clickName("爱数 AnyShare");
			clickName("个人文档");
		}*/
		try
		{clickName("vivi");}//程序必须先启动，按home再次进来，才能点中个人文档，疑似框架的问题。
		catch(Exception e)
		{	driver.pressKeyCode(3);
			clickName("爱数 AnyShare");
			clickName("vivi");
		}
		clickName(".自动化目录");
		clickName(destName);
		
	}
	
	public void enterNoPermission (String name) throws Exception
	{//	Thread.sleep(2000);
		driver.pressKeyCode(3);
		driver.pressKeyCode(3);//有时候不灵敏，需要多点次
		clickName("爱数 AnyShare");
		Thread.sleep(3000);//不休眠，还没有进来
		driver.pressKeyCode(3);
		clickName("爱数 AnyShare");
	 //	Thread.sleep(3000);
	/**************20171212.首页改进不需要点击个人文档，而5.0.18.2之前需要*******************/
		try
		{clickName("共享文档");}//程序必须先启动，按home再次进来，才能点中个人文档，疑似框架的问题。
		catch(Exception e)
		{	driver.pressKeyCode(3);
			clickName("爱数 AnyShare");
			clickFile("共享文档");
		}
		
		clickFile("zfy1");
		clickName( "只有显示权限文件夹ZFY");	
	}
	
	
	//滑动功能封装
  	public void swipe(String type)
  	{
  		int width=driver.manage().window().getSize().width;
  		int height=driver.manage().window().getSize().height;
  		switch (type)
  		{	case "up":
  				driver.swipe(width/2, height*8/10, width/2, height*2/10, 1000);
  				break;
  			case "down":
  				driver.swipe(width/2, height*8/10, width/2, height*2/10, 1000);
  				break;
  			case "left":
  				driver.swipe(width*4/5, height/2, width*1/5, height/2, 1000);
  				break;
  			case "right":
  				driver.swipe(width*1/5, height/2, width*4/5, height/2, 1000);
  				break;
  			default:
  				System.out.println("不合法参数"+type);
  				break;	
  		}
  	}
  	public void clearUploadList() throws Exception
  	{	clickId("com.eisoo.anyshare:id/rl_multi_select");
  		clickName("全选");
  		clickId("com.eisoo.anyshare:id/ll_transport_clear_all");
  		clickName("确定");
  	}
  	
  	public void swipePage(String type)
  	{
  		int width=driver.manage().window().getSize().width;
  		int height=driver.manage().window().getSize().height;
  		switch (type)
  		{	case "up":
  				driver.swipe(width/2, height*8/10, width/2, height*2/10, 1000);
  				break;
  			case "down":
  				driver.swipe(width/2, height*8/10, width/2, height*2/10, 1000);
  				break;
  			case "left":
  				driver.swipe(width*4/5, height/2, width*1/5, height/2, 1000);
  				break;
  			case "right":
  				driver.swipe(width*1/5, height/2, width*4/5, height/2, 1000);
  				break;
  			default:
  				System.out.println("不合法参数"+type);
  				break;	
  		}
  	}
  	//滑动寻找某个元素,下滑5次找不到，上滑5次继续找
  	public void swipefindElement(String name) throws Exception
  	{
  		for(int i=0;i<5;i++)
  		{
  			Thread.sleep(1000);
  			try
  			{	driver.findElementByName(name);
  				break;
  			}
  			catch(Exception e)
  			{ swipe("down");}
  		}
  		for(int i=0;i<5;i++)
  		{
  			Thread.sleep(1000);
  			try
  			{	driver.findElementByName(name);
  				break;
  			}
  			catch(Exception e)
  			{ 	System.out.println("下滑没找到，执行到上滑查找!");
  				swipe("up");}
  		}
  	}
  	public boolean exist(WebElement e1)
  	{
  		
  		try{
  		e1.getClass();
  		return true;
  		}
  		catch(Exception e)
  		{return false;}
  	}
  	
  	public boolean newElement (String text)
  	{
  			try
  			{	driver.findElement(By.name(text));
  				return true;
  			}
			catch(Exception e)
			{		}
  			try
  			{	driver.findElementsByAccessibilityId(text);
  				return true;}
  				
  			catch(Exception e)
  			{
  				System.out.println("没有这个元素") ;
  				return false;
  		}
  	}
  	
  	public boolean elementNameExist(String text)
  	{
  		
  			try
  			{	driver.findElement(By.name(text));
  				return true;
  			}
			catch(Exception e)
			{ return false;	}
  		
  	}
	public boolean elementDescExist(String text)
	{
		try
			{	driver.findElementByAccessibilityId(text);
				return true;
				}
				
			catch(Exception e)
			{
				System.out.println("没有这个元素") ;
				return false;	
				
			}
	}
 	//截图操作
  	public static void Screemshot(AndroidDriver driver,String Sc)
  	{
  		
  	}
	
  	//date 20171027 已用loginTest取代，减少重复代码，改方法可删除，或者后续参考
  	@Test(enabled=false)
  	public void loginIp() throws Exception 
  	{
		Thread.sleep(10000);
		driver.findElementById("com.eisoo.anyshare:id/et_serverAddress").sendKeys("192.168.184.166");
		WebElement confrim= driver.findElementByName("确定");
		confrim.click();
		Thread.sleep(5000);
		driver.findElementByName("请输入账号").sendKeys("zfy");
		driver.findElementById("com.eisoo.anyshare:id/et_password").sendKeys("123123");
		driver.pressKeyCode(4); 
		driver.findElementByName("登录").click();	
	//	driver.findElementsByAccessibilityId(using);
	//	assertNull(driver.findElementByName("登录"), "没有登录成功！");
		assertNotNull(driver.findElementByName("文档"), "没有登录成功！");	
		Thread.sleep(3000);	 
  }
  //date 20171027 已用loginTest取代，减少重复代码，改方法可删除，或者后续参考
  	@Test(enabled=false)
  	//此方法不能向外提供，涉及个人账号隐私
  	public void loginDomain() throws InterruptedException
  	{
  		Thread.sleep(5000);
		driver.findElementById("com.eisoo.anyshare:id/et_serverAddress").sendKeys("anyshare.eisoo.com");
		WebElement confrim= driver.findElementByName("确定");
		confrim.click();
		Thread.sleep(5000);
		driver.findElementByName("请输入账号").sendKeys("zhu.fengyue@eisoo.com");
		driver.findElementById("com.eisoo.anyshare:id/et_password").sendKeys("zhufy0902~~");
		driver.pressKeyCode(4); 
		driver.findElementByName("登录").click();	
		assertNotNull(driver.findElementByName("文档"), "没有登录成功！");
		Thread.sleep(5000);
		System.out.println("2");
  	}
  
  	//登录方法封装
  	public boolean loginTest(String sevAdress, String account, String passwd) throws Exception
  	{
  		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
  		Thread.sleep(1000);
  	//	driver.pressKeyCode(3);
  		agreePerm(); //首次启动需要初始化权限	
  		try{
  		driver.findElementByName("爱数 AnyShare").click();
  		agreePerm();	}
  		catch(Exception e)
  		{ }
		driver.findElementById("com.eisoo.anyshare:id/et_serverAddress").sendKeys(sevAdress);
		WebElement confrim= driver.findElementByName("确定");
		confrim.click();
		Thread.sleep(1000);
		driver.findElementByName("请输入账号").sendKeys(account);
		driver.findElementById("com.eisoo.anyshare:id/et_password").sendKeys(passwd);
		driver.pressKeyCode(4);
		driver.findElementByName("登录").click();	
		Thread.sleep(3000);
/*		String[] handles=new String[driver.getWindowHandles().size()];
	//	System.out.println(handles.length);
		driver.getWindowHandles().toArray(handles);	//切换到注册窗口
		WebDriver handle1=driver.switchTo().window(handles[1]);*/
		Thread.sleep(3000);
		startAS();
		return exist("常用");

		
  	}
  	
  	
  	
  	
  	
  	

 
  	@Test(enabled= false)
  	public void testLogin() throws Exception
  	{
	//  driver.pressKeyCode(3);
	  System.out.println("2");
	  driver.findElementByName("个人文档").click();
	  Thread.sleep(10000);	  
  	}
  	
  //用于清除app数据，可用范围如登录
  	public void clearData() throws Exception{
		driver.pressKeyCode(3);
		driver.findElementByName("设置").click();
	/*	swipe("down");
		swipe("down");*/
		swipefindElement("应用管理");
		Thread.sleep(1000);
		driver.findElementByName("应用管理").click();
		driver.findElementByName("爱数 AnyShare").click();
		driver.findElementByName("存储").click();
		driver.findElementByName("删除数据").click();
		driver.findElementByName("删除").click();
		driver.pressKeyCode(3);	
		System.out.println("clearData 清除数据");
		
  	}
  	
	//this method is apply to download,upload,backup feature
 
	public void switchWifi(int status) throws Exception
	{	
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.openNotifications();
	//	driver.findElementByName("开关").click();
	
		if(elementNameExist("WLAN")&&status==1)
		{
			driver.findElementByName("WLAN").click();
			System.out.println("Wifi由关闭切换至开启！");
		}
		else if(!elementNameExist("WLAN")&&status==0)
		{	driver.findElementByAccessibilityId("WLAN 已开启,WLAN 信号强度满格。,Eisoo-sh,打开WLAN设置。").click();//Desc已变更
			//driver.findElementByAndroidUIAutomator("new UiSelector().description(\"uploadfile\").instance("+i+")").click();
		//FAil	driver.findElementByAndroidUIAutomator("new UiSelector().class(\"android.widget.Switch\").instance(0)").click();
		// Fail	driver.findElementByAccessibilityId("WLAN 已开启,WLAN 信号强度满格。,Eisoo-sh,打开WLAN设置。").click();//
		//	clickName("Airdream_423D3A"); Other company
			System.out.println("wifi由开启切换至关闭！");
		}
		else
			System.out.println("网络不需要切换");	
		driver.pressKeyCode(4);
		System.out.println("执行返回");
	}
	//该方法实现上传／下载，切换过网络后下、，选择等待Wi-Fi上传
	//this method is apply to the popup wheather waitfor wifi 
	public void onlyWifi(int waitWifi)
	{
		if(elementNameExist("等待Wi-Fi"))	
		{
			if(waitWifi==1)
			{	driver.findElementByName("等待Wi-Fi").click();
				System.out.println("等待wifi传输");}
			else if(waitWifi==0)
			{
				driver.findElementByName("继续传输").click();
				System.out.println("不等待wifi传输");
			}
		}
		else 
			System.out.println("不存在等待WI-FI弹框 ");
	
	}
  	
  
	@AfterClass
	public void afterClass(){
		driver.quit();
		System.out.println("afterclass");
	}
	

   	@Test(groups="login")
  	public void loginDomainTest() throws Exception
  	{	 
 		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
 		assertTrue(loginTest("anyshare.eisoo.com","zhu.fengyue@eisoo.com","zhufy0902##"),"登录失败");
 		clearData();	
  	}
  	
  	public void agreePerm() throws Exception{
  		for(int i=0;i<5;i++)
  		{
  			if(exist("始终允许"))
  				clickName("始终允许");
  		}
  	}
  	
  	//0 暂停 1继续
	public boolean pause(String name,int type) throws Exception
  	{
		if(!exist("正在"+name+"..."))
			Thread.sleep(1000);
		for(int i=0;i<2;i++)
		{	if(!exist("已暂停"))
			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"download\").instance(0)").click();
		}
  			
  		if(type==1)
  			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"download\").instance(0)").click();
  	//	driver.findElementByAndroidUIAutomator("new UiSelector().description(\"uploadfile\").instance("+i+")").click();
  		return(exist("已暂停"));
  	}
	
	public void markFile(int num) throws Exception
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
	public void markListFile(int num) throws Exception
	{	
		WebElement  mulSelected = driver.findElementByAccessibilityId("multiple");
		mulSelected.click();
		if(num>8)
		{	System.out.println("数目较多超过8执行全选 ");
			clickName("全选");}	
		if(num<8)
		{for (int i=0; i<num;i++)
			{ driver.findElementByAndroidUIAutomator("new UiSelector().description(\"file\").instance("+i+")").click();
				}
		}
	}
	//点击文件操作按钮
	//多选模式：➕按钮->选择几个文件-> 执行各种操作
	public void manageFiles(int num ,String type) throws Exception
	{
		markListFile(num);
		clickName("更多操作...");
		clickName(type);			
	}
	//单选模式，勾选第几个按钮，如何获取元素的子元素
	/*//思路：选中父目录再定位子元素，寻找文件名 Xpath
	method2:Uiautomator 存在寻找子元素的方法
*/	public String manageFile(int rank,String type) throws Exception
	{
	  WebElement e2=driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.eisoo.anyshare:id/tv_file_name\").instance("+rank+")");
	  String fileName=e2.getText();
	  System.out.println("准备删除文件2"+e2.getText());
		if(rank<8)
		{
			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"pull\").instance("+rank+")").click();	
		}
		else
		{
			System.out.println("数值过大");
		}
		clickName(type);
		return fileName;
	}

//如何正则匹配,遍历？获取文件文本属性，遍历必须包含关键字
//如何用包含的方式定位元素？1.XPath自带
	public boolean fileContain(String name)
	{
		//String string1="abcd";
		//return string1.contains("ab");
		if(elementDescExist("file"))
		{
			for(int i=0;i<10;i++)
			{
				WebElement e1=driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.eisoo.anyshare:id/tv_file_name\").instance("+i+")");
				String string1=e1.getText();
				if(string1.contains(name))
				{
					return true;
				}
			}
		return false;
		}
		else 
		return false;
	}
	
	public boolean switchInput(int type) throws Exception
	{
		driver.pressKeyCode(3);
		clickName("设置");
		clickFile("高级设置");
		clickName("语言和输入法");
		clickName("默认");
		if(type==0)
		{
			clickName("华为 Swype 输入法");
			return exist("多国文字的输入法 - 华为 Swype 输入法");
		}
		else 
		{
			clickName("Appium Android Input Manager for Unicode");
			return !exist("多国文字的输入法 - 华为 Swype 输入法");
		}
	}
}
