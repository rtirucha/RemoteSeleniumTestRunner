<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Run Selenium Automation Remotely</title>
</head>
<body>
<h1>Welcome to My JSP Page</h1>
     <%
            String environment = (String) request.getAttribute("environment");
            String aggregator = (String) request.getAttribute("aggregator");
            String appType = (String) request.getAttribute("appType");
            String testDataRow = (String) request.getAttribute("testDataRow");
        %>
    <form action="RemoteSeleniumRunner" method="post">

        <label for="Environment">Select an Environment</label>
        <select id="environment" name="environment">
	        <option value="Test1" <%="Test1".equals(environment) ? "selected" : "" %> >Test1</option>
	        <option value="Test2" <%="Test2".equals(environment) ? "selected" : "" %> >Test2</option>
	    </select><br><br>
        <label for="Aggregator">Select a POS:</label>
	    <select id="aggregator" name="aggregator">
	        <option value="DT" <%="DT".equals(aggregator) ? "selected" : "" %> >DT</option>
	        <option value="LT" <%="LT".equals(aggregator) ? "selected" : "" %> >LT</option>
	        <option value="EP"  <%="EP".equals(aggregator) ? "selected" : "" %> >EP</option>
	    </select><br><br>
	    <label for="AppType">Select an Application</label>
	    <select id="appType" name="appType">
	        <option value="Individual" <%="Individual".equals(appType) ? "selected" : "" %> >Individual</option>
	        <option value="Joint" <%="Joint".equals(appType) ? "selected" : "" %> >Joint</option>
	    </select><br><br>
	    <tr>
			<td>Excel Row Number</td>
			<td><input type="text" id="testDataRow" name="testDataRow" value= '<%=testDataRow!=null ?testDataRow : "" %>' /></td>
		</tr><br><br>

          <tr>
          <td><input type="checkbox" name="IE"
          value="IE" /></td>
          <td>Internet Explorer</td>
          </tr>
          <tr>
          <td><input type="checkbox" name="Chrome"
          value="Chrome" /></td>
          <td>Chrome Browswer</td>
          </tr>
          <tr>
          <td><input type="checkbox" name="Edge"
          value="Edge" /></td>
          <td>Micrsoft Edge</td>
          </tr>
		 <input type="submit" id="submit" value="Submit" data-submit-value="Processing Please wait">
    </form>
    <br></br>
    <br></br>
    <br></br>
    <%
        String testResponse = (String) request.getAttribute("testResponse");
        if (testResponse != null) {
           // out.println(" Application ID for "+aggregator+ " : " + ApplicationId);
            out.println(testResponse);
        }


    %>
    <script type="text/javascript">

            var aggregator = document.getElementById('aggregator');
            appType = document.getElementById('appType');

    		    aggregator.addEventListener('change', function() {
                appType.innerHTML = '';
                option1 = document.createElement('option');
                option1.innerHTML = "Individual";
                option1.value="Individual";
                appType.appendChild(option1);

                if(aggregator.value != "LT"){
                	option2 = document.createElement('option');
                	option2.innerHTML = "Joint";
               		option2.value="Joint"
                    appType.appendChild(option2);
                }

            });

            document.getElementById("submit").addEventListener("click", testExecProgressStatus);

             function testExecProgressStatus() {
                console.log("logging the submit");
                document.getElementById("submit").value = "Processing please wait .. ";
                //document.getElementById("submit").disabled = true;
             }

        </script>

</body>
</html>