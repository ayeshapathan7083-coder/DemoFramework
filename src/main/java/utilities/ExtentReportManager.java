package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener  {
	public ExtentSparkReporter sparkReporter; //=>Ui of the report
	public ExtentReports extent;//=>populating common information on the report
	public ExtentTest test;//==>creating test case entries in the report and upadate status of the test methods
	String repName;
	 
	public  void onStart(ITestContext context) {
		
		//SimpleDateFormat df = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss");
		//Date dt=new Date();
		//String currentdatetimestamp=df.format(dt);
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH,mm,ss").format(new Date());
		repName="Test-Report"+timeStamp+".html";
		
		
		sparkReporter=new ExtentSparkReporter(".\\reports"+repName);
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Fuctional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application Name", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Custmers");
		extent.setSystemInfo("user name", System.getProperty("user.name"));
		extent.setSystemInfo("Envioroment Name", "QA");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
		extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
	  }
	public void onTestStart(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got successfully executed");
	}
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
          test.log(Status.FAIL, result.getName()+"got failed");
          test.log(Status.INFO,result.getThrowable().getMessage());
          try
          {
        	  String imgPath=new BaseClass().captureScreen(result.getName());
        	  test.addScreenCaptureFromPath(imgPath);
          }
          catch(IOException e1)
          {
        	  e1.printStackTrace();
          }
          
		  }
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
		  }
	public void onFinish(ITestContext context) {

		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports"+repName;
		File extentReport=new File(pathOfExtentReport);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	

}
