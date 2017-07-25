<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
		<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css"/>
 <script language="javascript" type="text/javascript">  
      var xmlHttp  
      var xmlHttp
      function loadSates(country){
      if (typeof XMLHttpRequest != "undefined"){
      xmlHttp= new XMLHttpRequest();
      }
      else if (window.ActiveXObject){
      xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");
      }
      if (xmlHttp==null){
      alert("Browser does not support XMLHTTP Request")
      return;
      } 
      var url="http://localhost:8081/CurrencyConverter/register/stateList";
      url +="?countryName="+country;
     
      xmlHttp.onreadystatechange = stateChange;
      xmlHttp.open("GET", url, true);
      xmlHttp.send(null);
      }

      function stateChange(){   
      if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){   
    	  var result= xmlHttp.responseText;
    	  result=JSON.parse(result);
    	   document.getElementById("state").innerHTML=createStateDropDown(result);
      }   
      }
      
      function createStateDropDown(result)
      {
    	  var html = '<option value="">-----Select-----</option>';
			var len = result.length;
						
			for ( var i = 0; i < len; i++) {
					html += '<option value="' + result[i].stateName + '">'
						+ result[i].stateName + '</option>';
			}
			html += '</option>';
			return html;
      }
      </script>
</head>
<body>
	<div align="center">
	<div id="login-box">
		<h2>Register Here</h2>
		<table border="0" width="90%">
		<form:form action="processRegisteration" modelAttribute="userDetails" method="POST">
			<tr>
					<td align="left" width="20%">First Name: </td>
					<td align="left" width="40%"><form:input path="firstName" size="30" autofocus="true"/></td>
					 <td align="left"><form:errors path="firstName" cssClass="error"/></td>
				</tr>
				<tr>
					<td align="left" width="20%">Last Name: </td>
					<td align="left" width="40%"><form:input path="lastName" size="30" /></td>
					<td align="left"><form:errors path="lastName" cssClass="error"/></td>
				</tr>
				<tr>
					<td align="left" width="20%">Date Of Birth: </td>
					<td align="left" width="40%"><form:input path="dateOfBirth" size="30" placeholder="DD-MM-YYYY" /></td>
					<td align="left"><form:errors path="dateOfBirth" cssClass="error"/></td>
				</tr>
				<tr>
					<td align="left" width="20%">Email: </td>
					<td align="left" width="40%"><form:input path="email" size="30" /></td>
					<td align="left"><form:errors path="email" cssClass="error"/></td>
				</tr>
				
				<tr>
					<td align="left" width="20%">Password: </td>
					<td  align="left" width="40%"><form:password path="password" size="30"/></td>
					<td  align="left" width="40%"><form:errors path="password" cssClass="error"/></td>
				</tr>
				<tr>
					<td align="left" width="20%">Confirm Password: </td>
					<td  align="left" width="40%"><form:password path="confirmPassword" size="30"/></td>
					<td  align="left" width="40%"><form:errors path="confirmPassword" cssClass="error"/></td>
				</tr>
				
					<tr>
					<th align="left" width="20%" colspan="3">Address Details </th>
					<tr>
					<td align="left" width="20%">Country: </td>
					<td  align="left" width="40%"><form:select path="userAddress.countryCode"  onchange="loadSates(this.value)">
					<form:option value="">Country</form:option>
					<form:options items="${theCountryOptions}"/>
					</form:select>
					</td>
					<td  align="left" width="40%"><form:errors path="userAddress.countryCode" cssClass="error"/></td>
					</tr>
					<tr>
					<td align="left" width="20%">State: </td>
					<td  align="left" width="40%"><form:select id="state" path="userAddress.stateName">-----Select-----</form:select>
					<td  align="left" width="40%"><form:errors path="userAddress.stateName" cssClass="error"/></td>
					</tr> 
					<tr>
					
					<td align="left" width="20%">Street Line1: </td>
					<td  align="left" width="40%"><form:input path="userAddress.streetLine1" size="30"/></td>
					<td  align="left" width="40%"><form:errors path="userAddress.streetLine1" cssClass="error"/></td>
					</tr>
					<tr>
					<td align="left" width="20%">Street Line2: </td>
					<td  align="left" width="40%"><form:input path="userAddress.streetLine2" size="30"/></td>
					<td  align="left" width="40%"><form:errors path="userAddress.streetLine2" cssClass="error"/></td>
					</tr>
					<tr>
					<td align="left" width="20%">City: </td>
					<td  align="left" width="40%"><form:input path="userAddress.city" size="30"/></td>
					<td  align="left" width="40%"><form:errors path="userAddress.city" cssClass="error"/></td>
					</tr>
					<tr>
					<td align="left" width="20%">Zip Code: </td>
					<td  align="left" width="40%"><form:input path="userAddress.zipCode" size="30"/></td>
					<td  align="left" width="40%"><form:errors path="userAddress.zipCode" cssClass="error"/></td>
					</tr>
					
					
				
				<tr>
					
					<td align="center" colspan="3"><input type="submit" value="Register"/></td>
					
				</tr>
		</form:form>
		</table>
	</div>
	</div>
</body>
</html>
