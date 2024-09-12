package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtil {
    private static ExtentReports extent;
    private static ExtentTest test;

    // Initialize ExtentReports
    public static void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-reports/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add system info
        extent.setSystemInfo("Tester", "Shwetal Gadge");
        extent.setSystemInfo("Environment", "Automation Exercise");
    }

    // Start a new test in the report
    public static void startTest(String testName) {
        test = extent.createTest(testName);
    }

    // Add a screenshot to the report
    public static void addScreenshotToReport(String screenshotPath) {
        test.addScreenCaptureFromPath(screenshotPath);
    }

    // Finalize the report
    public static void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }
}
