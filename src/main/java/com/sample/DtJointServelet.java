package com.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Servlet implementation class MysServlet
 */
@WebServlet("/DtJointServelet")
public class DtJointServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DtJointServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dt_joint = request.getParameter("dt_joint");
		System.out.println("Test inout:"+dt_joint );
		
		
		try {
            String dirCommand = String.format("cmd.exe /c dir %s", "C:\\Users\\rtiru\\eclipse-projects\\selenium-cucumber-java-master");
            String mvnCommand = String.format("cmd.exe /c C:\\softwares\\apache-maven-3.9.9-bin\\apache-maven-3.9.9\\bin\\mvn test -Dtest2runparam=%s -f %s",dt_joint, "C:\\Users\\rtiru\\eclipse-projects\\selenium-cucumber-java-master");
            System.out.println(dirCommand);
            Process process = Runtime.getRuntime().exec(mvnCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
		//Process process = Runtime.getRuntime()
			 //     .exec(String.format("cmd.exe /c dir %s", "C:\\Users\\rtiru\\eclipse-projects\\selenium-cucumber-java-master"));
		//.exec(String.format("cmd.exe /c C:\\softwares\\apache-maven-3.9.9-bin\\apache-maven-3.9.9\\bin\\mvn test -Dtest2runparam=1000", "C:\\Users\\rtiru\\eclipse-projects\\selenium-cucumber-java-master"));
		
		
		 
		doGet(request, response);
	}

}
