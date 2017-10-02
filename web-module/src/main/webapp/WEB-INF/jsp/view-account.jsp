<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<h3>
		Hi
		<c:out value="${account.getAccountName()}" />
	</h3>
	<strong>Your Email</strong>:
	<c:out value="${account.getEmail()}" />
	<br> <strong>Additional Info</strong>:
	<c:out value="${account.getAdditionalInfo()}" />
	<br> <img
		src="${pageContext.servletContext.contextPath}/signed/getImage?accId=${account.getAccountId()}"
		height="111px" width="111px" />

	<form:form modelAttribute="account" action="/signed/uploadImage" method="post"
		enctype="multipart/form-data">
		<input type="file" name="image" />
		<br />
		<br />
		<input type="submit" />
		<input type="hidden" name="id"
			value=<c:out value="${account.getAccountId()}"/>>
	</form:form>

	<h3>Available DataSets for current account</h3>
	<div>
		<a type="button" class="btn btn-success"
			href="<c:url value="/signed/add-watchlist?id=${account.getAccountId()}"/>">Add
			New watchlist</a>
		<a type="button" class="btn btn-success"
			href="/signed/search">Search</a>
	</div>
	<hr size="4" color="gray" />

	<table>

		<c:forEach var="theWatchList" items="${watchLists}">
			<tr>
				<td>dataSet Name: ${theWatchList.getWatchListName()}</td>
				<td>dataSet id: ${theWatchList.getWatchListId()}</td>

				<td><a type="button" class="btn btn-primary"
					href="<c:url value="/signed/view-watchlist?id=${theWatchList.watchListId}"/>">View</a></td>
			</tr>

		</c:forEach>

		<!-- todo h make " dataSet name " header of the table "dataset_id" the same or delete it  -->
	</table>
	<br>


</div>
<%@ include file="common/footer.jspf"%>