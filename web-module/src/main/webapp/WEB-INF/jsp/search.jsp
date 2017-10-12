<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: denisigoshev
  Date: 6/15/2017
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Search data</title>
<link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<h1>Let's make some search !</h1>
<body>
	<form action="/home" method="get">
		<input type="submit" value="back home">
	</form>
	<br>
	<form id="search-form">
		<div class="container">
			<div class="form-group">
				<div class="col-md-10">
					<div class="input-group">
						<input id="searchParam" type="text" name="searchParam"
							class="form control" size="44"
							placeholder="Type instrument id or first chars of id with '%' symbol"
							autocomplete="off">
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-search"></span> Search
						</button>

					</div>
				</div>
			</div>
			<br> <br>
			<table class="table table-bordered table-striped" style="width: 70%">
				<tr>
					<th style="width: 15%">instrument id</th>
					<%--  <th style="width: 15%">watchlist (data set) name</th>
          <th style="width: 15%">records number</th>--%>
					<c:forEach var="tempSearchRecord" items="${THE_SEARCH_RESULT_LIST}">

						<tr>
							<td>${tempSearchRecord}</td>
							<%--<td> ${tempSearchRecord.getValue1()} </td>
          <td> ${tempSearchRecord.getValue2()} </td>--%>
						</tr>
					</c:forEach>
				</tr>
			</table>
		</div>
	</form>
	<script>
		jQuery(document).ready(function($) {

			$("#search-form").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				console.log("submit data");
				searchViaAjax();

			});

		});

		function searchViaAjax() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var search = {}
			search["searchParam"] = $("#searchParam").val();
			console.log(search);
			$.ajax({
				type : "GET",
				contentType : "application/json",
				action : "DataController",
				url : "/signed/DataControllerSearch",
				data : search,
				headers: { header: token}, 
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					display(data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				},
				done : function(e) {
					console.log("DONE");
					enableSearchButton(true);
				}
			});

		}

		function enableSearchButton(flag) {
			$("#btn-search").prop("disabled", flag);
		}

		function display(data) {
			var json = "<h4>Ajax Response</h4><pre>"
					+ JSON.stringify(data, null, 4) + "</pre>";
			$('#feedback').html(json);
		}
	</script>
</body>
</html>
