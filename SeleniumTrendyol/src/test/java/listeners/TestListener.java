package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

public class TestListener extends BaseTest implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test Started.Method : "+ getTestMethodName(iTestResult));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test Success. Method: "+ getTestMethodName(iTestResult));
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Failure. Method: "+ getTestMethodName(iTestResult));
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test skipped. Method: "+ getTestMethodName(iTestResult));
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but within success percentage. Method: "+getTestMethodName(iTestResult));
    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("Test Start. Method: "+ iTestContext.getName());
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("Test Finished. Method: "+ iTestContext.getName());
    }
    public static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}
