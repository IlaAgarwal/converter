<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html>

	<head>
		<title>User History</title>
		<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css"/>
	</head>

<sec:authorize access="hasRole('ROLE_USER')">
	<body>
		<c:set var = "now" value = "<%=new java.util.Date()%>" />
		<%-- <c:url value="/logout" var="logoutUrl" />
		<a href="${logoutUrl}">Log Out</a> --%>
				
		  <form id="logoutForm" method="POST" action="${contextPath}/j_spring_security_logout">
		  <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
        </form>
		
        <h4 align="right"><a onclick="document.forms['logoutForm'].submit()">Logout</a></h4> 
         
 		<form:form action="convertCurrency"  modelAttribute="currency" method="POST">
 		<c:if test="${not empty errorMessage}">
				 		<div class=error>${errorMessage}</div>
						</c:if>
 				<table>
 					<tbody>
 					<tr><th colspan=6 align="left">Currency Converter</th></tr>
 						<tr>
				 			<td><label>Date:</label></td>
 							<td><form:input path="queryDate" /></td>
				 			<td><input type="submit" value="convert" name="convertCurrency"></td>
				 		</tr>
				 		
				 	
				 		 <c:forEach var="exchangeRate" items="${currency.exchangeRateSet}">
				 		<tr>
				 		<td>${exchangeRate.currencyType}</td>
				 		<td>${exchangeRate.exchangeAmount}</td>
				 		</tr>
				 		</c:forEach>
 				</tbody>
 			</table>
 			<form:errors path="queryDate" cssClass="error"/>
 	</form:form>
	
	
	<table>
	<tr><th colspan=6 align="left">Past Exchnage Rate Queries</th></tr>
		<tr>
				<th>Date</th>
				<th>AED</th>
				<th>EUR</th>
				<th>GBP</th>
				<th>INR</th>
				<th>USD</th>
				
		</tr>
		<c:forEach var="userHistory" items="${history}">
			<tr>
				<td><fmt:formatDate pattern = "yyyy-MM-dd" value="${userHistory.queryDate}"/></td>
			<c:forEach var="exchangeRatePastData" items="${userHistory.exchangeRateSet}">
				  				  	<td>${exchangeRatePastData.exchangeAmount}</td>
				  </c:forEach>  
			</tr>
		</c:forEach>
	</table>	

	</body>
</sec:authorize>

</html>









