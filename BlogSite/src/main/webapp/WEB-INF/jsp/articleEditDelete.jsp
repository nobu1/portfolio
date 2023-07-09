<%@page import="model.LoginData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- CSS-->
<link rel="stylesheet" href="css/blog.css" type="text/css">

<!-- Javascript -->
<script src="js/form_AdminEditeDelete.js" defer></script>

<jsp:include page="/WEB-INF/jsp/title-meta.jsp"></jsp:include>
</head>

<body>
	<main>
		<div class="row m-2">
			<div class="col-md-11 text-end fw-bold">
				<c:if test="${not empty nickName}">
					<p name="nickname">
						Login user:
						<data class="nickName"> <c:out value="${nickName}" /></data>
					</p>
				</c:if>
			</div>
			<div class="col-md-11 text-end">
				<a href="AdminServlet?action=logout" class="btn btn-primary"
					role="button">Logout</a>
			</div>
			<div class="col-md-12 text-center">
				<h1>Administrator Page</h1>
			</div>
			<div class="col-md-12 d-flex justify-content-around">
				<ul class="nav nav-tabs flex-column flex-sm-row bg-light">
					<li class="nav-item"><a href="AdminServlet" class="nav-link">Create
							Article Page</a></li>
					<li class="nav-item"><a href="AdminServlet?action=editdelete"
						class="nav-link">Article Edit/Delete Page</a></li>
				</ul>
			</div>
		</div>
		<hr>

		<div class="row">
			<div class="col-md-12 text-center fw-bold">
				<c:if test="${not empty messageEdit}">
					<p>
						<c:out value="${messageEdit}" />
					</p>
				</c:if>
			</div>
		</div>
		<div class="container CreateForm">
			<form class="m-4" id="Create Form" action="AdminServlet"
				method="post" enctype="multipart/form-data"></form>
		</div>
		<div class="table-responsive m-5 Edit-Delete">
			<table class="table table-hover text-nowrap">
				<thead class="colum-color">
					<tr>
						<th scope="col">Title</th>
						<th scope="col">Edit</th>
						<th scope="col">Delete</th>
					</tr>
				</thead>
				<!-- Get contents from login user -->
				<tbody>
					<c:forEach var="article" items="${articles}">
						<tr>
							<td><a class="title"
								href="<%=request.getContextPath()%><c:out value="${article.getValue()}"/>">
									<c:out value="${article.getKey()}" />
							</a></td>
							<td>
								<button class="btn edit_btn" data-bs-toggle="tooltip"
									data-bs-placement="top" title="Edit"
									name="<%=request.getContextPath()%><c:out value="${article.getValue()}"/>">
									<img src="img/logo_tbl_edit.svg" class="edit-btn"
										alt="Edit Button">
								</button>
								<p hidden class="EditServlet"
									id="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/EditServlet"></p>

							</td>
							<td><button class="btn delete_btn"
									id="<c:out value="${article.getKey()}" />"
									data-bs-toggle="tooltip" title="Delete">
									<img src="img/logo_tbl_delete.svg" class="delete-btn"
										alt="Delete Button">
								</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- Edit-Delete -->
	</main>

	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>

	<!-- Bootstrap Bundle with Popper -->
	<script src="js/bootstrap.bundle.min.js"></script>

</body>

</html>