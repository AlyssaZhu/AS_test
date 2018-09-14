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

import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
	protected static AndroidDriver driver;
	static int moduleId=0;
	static int caseId=0;
	//初始化driver
/*//	@BeforeClass
	  public void beforeClass() throws Exception {  
			System.out.println("base beforeclass");
			init();
			loginTest("116.236.224.243","zfy","123123");
		}*/
			
		
	
	public void init() throws Exception
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
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//设置隐式等待超时时间3秒
		//Thread.sleep(3000);
		System.out.println("Module"+(++moduleId)+":"+this.getClass().getName());
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
	{ 		 if(!elementNameExist("爱数 AnyShare"))
				driver.pressKeyCode(3);
			clickName("爱数 AnyShare");
			Thread.sleep(1000);
	}
	

  	//以元素名称判断元素是否存在，最好用于唯一的名称
  	public boolean exist(String name)
  	{
  		if(driver.getPageSource().contains(name))
  		{	System.out.println("页面存在元素："+name);
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
		enterSet("清除缓存");
		clickName("确定");	
	}
	
	//this is destinastion path
	public void enterDest(String destName,int permission) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(1000);// 这2句主要是为了应付框架点不中的Bug
		startAS();
		if(permission==0)
		{
			clickFile("共享文档");
			clickFile("zfy1");
			clickFile("只有显示权限文件夹ZFY");	
		}
		else
		{
		//	clickFile("个人文档");
			clickFile("vivi");
			clickFile(".自动化目录");
			if(destName!="根目录")
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
		Thread.sleep(1000);
		clickName(".自动化目录");
		if(destName!="根目录")
		{clickFile(destName);}
		
	}
	
	public void enterNoPermission (String name) throws Exception
	{//	Thread.sleep(2000);
		startAS();
	 	Thread.sleep(1000);
	/**************20171212.首页改进不需要点击个人文档，而5.0.18.2之前需要*******************/
		try
		{clickName("共享文档");}//程序必须先启动，按home再次进来，才能点中个人文档，疑似框架的问题。
		catch(Exception e)
		{	killAS();
			startAS();
			clickFile("共享文档");
		}
		clickFile("zfy1");
		clickFile( "只有显示权限文件夹ZFY");	
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
  

  	
  	public boolean log_agin() throws Exception
  	{
		driver.findElementById("com.eisoo.anyshare:id/et_password").sendKeys("123123");
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
		try{
		Thread.sleep(2000);
		driver.findElementByName("爱数 AnyShare").click();}
		catch(Exception e)
		{
			Thread.sleep(3000);
			driver.findElementByName("爱数 AnyShare").click();	
		}
		driver.findElementByName("存储").click();
		driver.findElementByName("删除数据").click();
		try{
			driver.findElementByName("删除").click();
		}
		catch(Exception e)
		{
			driver.findElementByName("确定").click();//P10适配
		}
		
		driver.pressKeyCode(3);	
		System.out.println("clearData 清除数据");
  	}
  	
	//this method is apply to download,upload,backup feature
 
	public void switchWifi(int status) throws Exception
	{	
	/*	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.openNotifications();
	
		if(elementNameExist("WLAN")&&status==1)
		{
			driver.findElementByName("WLAN").click();
			System.out.println("Wifi由关闭切换至开启！");
		}
		else if(!elementNameExist("WLAN")&&status==0)
		{	driver.findElementByAccessibilityId("WLAN 已开启,WLAN 信号强度满格。,Eisoo-sh,打开WLAN设置。").click();//Desc已变更
			System.out.println("wifi由开启切换至关闭！");
		}
		else
			System.out.println("网络不需要切换");	
		driver.pressKeyCode(4);
		System.out.println("执行返回");*/
		//method 2
		if(status==1)
		{	
			driver.setNetworkConnection(new NetworkConnectionSetting(6));
			int current1=driver.getNetworkConnection().value;
			System.out.println(current1);
			if(current1!=6)
			{
				driver.openNotifications();
				driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"android:id/icon\").instance(0)").click();	
			}
		}
		else if(status==0)
		{
			int current2=driver.getNetworkConnection().value;
			
			if(current2!=4)
			{
				driver.openNotifications();
				driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"android:id/icon\").instance(0)").click();
			}
		}
		
	/*	int current=driver.getNetworkConnection().value;
		if((status==1&&current!=6)||(status==0&&current!=4))
		{	

				driver.openNotifications();
				driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"android:id/icon\").instance(0)").click();	
			}
		else 
			System.out.println("no need!");*/
		
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
	

  // 	@Test(groups="login")
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
		for(int i=0;i<3;i++)
		{	if(exist("已暂停"))
			{ break;}
			else
			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"download\").instance(0)").click();
		}		
  		if(type==1)
  		{   Thread.sleep(1000);
  			driver.findElementByAndroidUIAutomator("new UiSelector().description(\"download\").instance(0)").click();}
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
	{	Thread.sleep(1500);
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
	  System.out.println("准备操作文件:"+e2.getText());
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
	
	public void delFiles(int num) throws Exception
	{
		startAS();
		if(exist("文件夹是空的"))
		{
			System.out.println("空文件夹！");		
		}
		else 
		{
			manageFiles(num,"删除");
			clickName("确定");		
		}
	}
	
	//只能1到8
	  public String clickFile(int rank)
	  {
		
		  WebElement e2=driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.eisoo.anyshare:id/tv_file_name\").instance("+rank+")");
		  String fileName=e2.getText();
		  System.out.println("准备操作文件:"+e2.getText());
		  e2.click();
		  return fileName;
		  
	  }
	  
	  public String language()
		{
			try{
				if(exist(driver.findElementById("com.eisoo.anyshare:id/tv_languageSetting")))//是否在首页
				{
					if(exist("语言设置"))
						return "简体中文";
						else if(exist("語言設置"))
						return "繁體中文";
						else if (exist("Language Settings"))
						return "English";
						else	
							return "error";	
				}
				
			}
			
			catch(Exception e)
			{
				if(exist("传输"))
					return "简体中文";
					else if(exist("傳輸"))
					return "繁體中文";
					else if (exist("Transfer"))
					return "English";
					else	
						return "error";	
				
			}
			return null;
			
		}
		
		public boolean isSame(String lang) throws Exception
		{
			Thread.sleep(3000);
			startAS();//目的是为了刷新一下界面，
			String currentlang=language();
			System.out.println("当前APP界面上的语言*********"+currentlang);
			if(lang.equals(currentlang))
			{
				return true;
			}
			else 
				return false;
		}
		
		public void langSet(String lang) throws Exception
			{
				Thread.sleep(1000);
				clickId("com.eisoo.anyshare:id/rl_setting");
				clickId("com.eisoo.anyshare:id/rl_setting");
				driver.findElementById("com.eisoo.anyshare:id/rl_language_set").click();
				clickName(lang);
				WebElement e1=driver.findElementById("com.eisoo.anyshare:id/tv_language_set_save");
				if(e1.isEnabled())
				{	e1.click();
					assertTrue(isSame(lang),"语言没有切换!");}
				else 
					System.out.println("语言没作修改，没必要保存");
			
		}
		
		public void  langSetHome(String lang) throws Exception
		{
			logout();
			clickId("com.eisoo.anyshare:id/tv_languageSetting");
			WebElement e1=driver.findElementById("com.eisoo.anyshare:id/tv_language_set_save");
			clickName(lang);
			if(e1.isEnabled())
			{	e1.click();
				assertTrue(isSame(lang),"语言没有切换!");}
			else 
				System.out.println("语言没作修改，没必要保存");
			
		}
		
		public void logout() throws Exception
		{
			try{
				exist(driver.findElementById("com.eisoo.anyshare:id/tv_languageSetting"));
			
				System.out.println("已注销状态");
				}
		
			catch(Exception e)
			{
				clickId("com.eisoo.anyshare:id/rl_setting");
				swipe("down");
				clickId("com.eisoo.anyshare:id/tv_outapp");
				clickId("com.eisoo.anyshare:id/positiveButton");
				Thread.sleep(1000);
			}
		}
		
		  public void rename(int rank) throws Exception
		  {
			  manageFile(rank,"重命名");
			  WebElement e1= driver.findElementByClassName("android.widget.EditText");
			 	String fname1=e1.getText();
			 	e1.sendKeys(fname1+"1");
			 	clickName("确定");   
		  }	
		
		  public void removeSign() throws Exception
		  {
			  
			  for(int i=0;i<4;i++)
			  {		 
				  try{
					  if(exist(driver.findElementByAccessibilityId("guide_view")))
						 {
							  Thread.sleep(1000);
							 driver.findElementByAccessibilityId("guide_view").click();
							 System.out.println("取消第"+i+"次");
								 }
				  }
				  catch(Exception e)
			   {  System.out.println("error :no sign "+i); }
			  }
			 
			  Thread.sleep(2000);
			//  clickFile("vivi");
			  manageFile(0,"打开");
			  Thread.sleep(1000);
			  try{
				  if(exist(driver.findElementByAccessibilityId("guide_view")))
					 {
						  Thread.sleep(1000);
						 driver.findElementByAccessibilityId("guide_view").click();
							 }
			  }
			  catch(Exception e)
			   { 
				  System.out.println("error:no sign 5");
			   }
			 
			  driver.pressKeyCode(4);
			  
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
				Thread.sleep(5000);
		/*		String[] handles=new String[driver.getWindowHandles().size()];
			//	System.out.println(handles.length);
				driver.getWindowHandles().toArray(handles);	//切换到注册窗口
				WebDriver handle1=driver.switchTo().window(handles[1]);*/
				startAS();
				
				removeSign();
				return exist("常用");	
		  	}
		  	
		    /* 备份复制*/
			public void enterSet(String name) throws Exception
			{
				clickName("我的");
				clickName("设置");
				clickName(name);
			}
}
