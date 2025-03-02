package com.sample;

import com.utils.ApplicationIdExtractor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.utils.CucumberTestFeatureSelector;

/**
 * Servlet implementation class MysServlet
 */
@WebServlet("/RemoteSeleniumRunner")
public class RemoteSeleniumRunner extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AtomicInteger numberOfConcurentTestsRunning = new AtomicInteger(0);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoteSeleniumRunner() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String testResponse = null;
		String environment = request.getParameter("environment");
		String aggregator = request.getParameter("aggregator");
		String appType = request.getParameter("appType");
		String testDataRow = request.getParameter("testDataRow");
		System.out.println(
				String.format("Test input %s - %s - %s - %s: ", environment, aggregator, appType, testDataRow));
		int yourTestPos = numberOfConcurentTestsRunning.incrementAndGet();
		if(yourTestPos > 3) {
			// Send error
			testResponse = String.format("%d tests are running in parallel, please try after few minutes",3);
		}
		else {
			//execute tests
			System.out.println("Your Test Position is " + yourTestPos);
			testResponse = executeCucumberTests(aggregator, appType, testDataRow);
		}

		numberOfConcurentTestsRunning.decrementAndGet();
		request.setAttribute("testResponse", testResponse);
		request.setAttribute("environment",environment);
		request.setAttribute("aggregator",aggregator);
		request.setAttribute("appType",appType);
		request.setAttribute("testDataRow",testDataRow);

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	private static String executeCucumberTests(String aggregator, String appType, String testDataRow) {
		String testResponse = null;
		String cucumberTestFeatureFile = new CucumberTestFeatureSelector().selectCucumberTestFeatureFile(aggregator,
				appType);

		System.out.println("Cucumber test Featurefile selected :" + cucumberTestFeatureFile);

		String mvnPath= "C:\\softwares\\apache-maven-3.9.9-bin\\apache-maven-3.9.9\\bin\\mvn";

		try {
			String dirCommand = String.format("cmd.exe /c dir %s",
					"C:\\Users\\rtiru\\eclipse-projects\\selenium-cucumber-java-master");
			String mvnCommand = String.format(
					"cmd.exe /c %s test -Dtest2runparam=%s -f \"%s\" -Dcucumber.options=\"%s\"",
					mvnPath, testDataRow, "C:\\Users\\rtiru\\eclipse-projects\\selenium-cucumber-java-master",
					cucumberTestFeatureFile);
			System.out.println(mvnCommand);
			Thread.sleep(10000);
			/*
		    Process process = Runtime.getRuntime().exec(mvnCommand);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		       System.out.println(line);
			   if(applicationId == null) {
				   applicationId = ApplicationIdExtractor.extractAppId(line);
			   }
		    }
		   process.waitFor();
			 */

			String consoleOutput = "DT Generated reference ID: 675679";
			testResponse = ApplicationIdExtractor.extractAppId(consoleOutput);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return testResponse;
	}

}
