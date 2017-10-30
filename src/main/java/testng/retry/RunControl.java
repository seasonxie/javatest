package testng.retry;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;



public class RunControl {

	private static String testCaseName;

	private static String[] tests;

	private static boolean reRun = false;

	private static int threadCount = 1;

	public void setReRun(boolean reRun) {
		this.reRun = reRun;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	private void getTestsName() {
		tests = testCaseName.split(",");
	}

	private static List<XmlSuite> xmlSuites() {
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		XmlSuite suite = xmlSuite();
		suites.add(suite);
		return suites;
	}

	private static XmlSuite xmlSuite() {
		XmlSuite suite = new XmlSuite();
		suite.setName("UI Automation Suite");
		List<XmlTest> xmlTests = new ArrayList<XmlTest>();
		for (String testname : tests) {
			xmlTests.add(xmlTest(suite, testname, xmlClass()));
		}
		if (threadCount > 1) {
			suite.setParallel("tests");
			suite.setThreadCount(threadCount);
		}
		// List<String> listListeners = new ArrayList<String>();
		// listListeners.add("com.test.util.ResultListener");
		// listListeners.add("com.test.util.NewReporter");
		// suite.setListeners(listListeners);
		// System.out.println(suite.toXml());
		return suite;
	}

	private static XmlTest xmlTest(XmlSuite suite, String name, List<XmlClass> classes) {
		XmlTest test = new XmlTest(suite);
		test.setName(name);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("testCase", name);
		test.setParameters(parameters);
		test.setClasses(classes);
		return test;
	}

	private static List<XmlClass> xmlClass() {
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("com.test.testcase.TestUI"));
		return classes;
	}

	private static XmlSuite failedXmlSuite(String reportDir) {
		XmlSuite suite = new XmlSuite();
		List<String> files = new ArrayList<String>();
		//System.out.println(reportDir + "/testng-failed.xml");
		files.add(reportDir + "/testng.xml");
		System.out.println(reportDir + "/testng.xml");
		suite.setSuiteFiles(files);
		return suite;
	}



	public static void run3() {
		TestNG testng = new TestNG();
		List<XmlSuite> Suites = xmlSuites();
		testng.setXmlSuites(Suites);
		testng.run();
	}

	public static void run2() {
		TestNG testng = new TestNG();
		List<XmlSuite> failedSuites = new ArrayList<XmlSuite>();
		failedSuites.add(failedXmlSuite(testng.getOutputDirectory()));
		testng.setXmlSuites(failedSuites);
		testng.run();
	}

	public static void run1(){
		TestNG testng = new TestNG();
		List<String> suites = new ArrayList<String>();
		suites.add(System.getProperty("user.dir")+"/testng.xml");
		testng.setTestSuites(suites);
		//setListener(runner);
		testng.run();
	}
	


	public static void main(String[] args) {
		run1();
		run2();
	}
	
	
}
