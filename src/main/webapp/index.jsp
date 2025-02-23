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
	        <option value="QAT1" <%="QAT1".equals(environment) ? "selected" : "" %> >QAT1</option>
	        <option value="QAT2" <%="QAT2".equals(environment) ? "selected" : "" %> >QAT2</option>
	    </select><br><br>
        <label for="Aggregator">Select a POS:</label>
	    <select id="aggregator" name="aggregator">
	        <option value="LendingTree" <%="LendingTree".equals(aggregator) ? "selected" : "" %> >LendingTree</option>
	        <option value="Enterprise"  <%="Enterprise".equals(aggregator) ? "selected" : "" %> >Enterprise</option>
	        <option value="DealerTrack" <%="DealerTrack".equals(aggregator) ? "selected" : "" %> >DealerTrack</option>
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

		 <input type="submit" value="Submit" data-submit-value="Processing Please wait">
    </form>
    <br></br>
    <br></br>
    <br></br>
    <%
        String ApplicationId = (String) request.getAttribute("ApplicationId");
        if (ApplicationId != null) {
            out.println(" Tests are running with Application ID : " + ApplicationId);
        }


    %>
    <script type="text/javascript">

            var aggregator = document.getElementById('aggregator');
            appType = document.getElementById('appType');

    		    aggregator.addEventListener('change', function() {
                appType.innerHTML = '';
                option1 = document.createElement('option');
                option1.innerHTML = "Individual";
                option1.value="Individual"
                appType.appendChild(option1);

                if(aggregator.value != "DealerTrack"){
                	option2 = document.createElement('option');
                	option2.innerHTML = "Joint";
               		option2.value="Joint"
                    appType.appendChild(option2);
                }




            });

        </script>

</body>
</html>