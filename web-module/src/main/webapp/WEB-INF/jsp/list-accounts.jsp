<%--<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Fintech Application</title>
<link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<script src="/webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>
	<%@ include file="common/header.jspf"%>
	<%@ include file="common/navigation.jspf"%>

	<div class="container">

		<br />
		<c:if test="${param.success eq true}">
			<div class="alert alert-success">changes applied successfully</div>
		</c:if>
		<table class="table table-striped">

			<tr>
				<th>AccountName</th>
				<th>Email</th>
				<th>AdditionalInfo</th>
				<th>CreationDate</th>
				<th>Action</th>
			</tr>
			<div>
				<a type="button" class="btn btn-success" href="/signed/add-account">Add
					New Account</a>
			</div>

			<c:forEach var="currAccount" items="${ACCOUNT_LIST}">

				<tr>
					<td>${currAccount.getAccountName()}</td>
					<td>${currAccount.getEmail()}</td>
					<td>${currAccount.getAdditionalInfo()}</td>
					<td id="data">${currAccount.getCreationDate()}</td>
					<td><a type="button" class="btn btn-primary"
						href="<c:url value="/signed/view-account?id=${currAccount.getAccountId()}"/>">View</a>

						<a type="button" class="btn btn-primary"
						href="<c:url value="/signed/edit-account?id=${currAccount.getAccountId()}"/>">Edit</a>

						<a type="button" class="btn btn-warning"
						href="<c:url value="/signed/delete-account?id=${currAccount.getAccountId()}"/>"
						onclick="if (!(confirm('Are you sure you want to delete this account?')))
                 return false">
							Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>


</body>
</html>

<%--<%@ include file="common/footer.jspf" %>--%>

