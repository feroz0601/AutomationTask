package Util;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtil {
    private static ExtentReports extent;
    public static ExtentTest test;

    public static void initializeReport(String reportPath) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static void startTest(String testName) {
        test = extent.createTest(testName);
    }

    public static void endTest() {
        extent.flush();
    }
}
