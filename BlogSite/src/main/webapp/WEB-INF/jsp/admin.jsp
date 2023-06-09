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
<script src="js/form_AdminModifyElements.js" defer></script>

<jsp:include page="/WEB-INF/jsp/title-meta.jsp"></jsp:include>
</head>

<body>
	<main>
		<div class="row m-2">
			<div class="col-md-11 text-end fw-bold">
				<c:if test="${not empty loginData.nickName}">
					<p name="nickname">
						<c:out value="Login user: ${loginData.nickName}" />
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
					<li class="nav-item"><a href="AdminServlet?action=editdelete&nickName=${loginData.nickName}"
						class="nav-link">Article Edit/Delete Page</a></li>
				</ul>
			</div>
		</div>
		<hr>

		<div class="container CreateForm">
			<form class="m-4" id="Create Form" action="AdminServlet"
				method="post" enctype="multipart/form-data">
				<div class="row">
					<div class="col-md-12 text-center text-danger fw-bold">
						<c:if test="${not empty message}">
							<p>
								<c:out value="${message}" />
							</p>
						</c:if>
					</div>
				</div>
				<input type="text" name="nickname"
					value="<c:out value="${loginData.nickName}" />" hidden>
				<p class="text-center lead" style="font-weight: bold;">Required
					contents</p>
				<div class="row my-2">
					<label class="col-md-3 col-form-label text-start" for="F_Title">Blog
						Title*</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="F_Title"
							name="blogTitle" maxlength="100" required>
					</div>
				</div>
				<div class="row my-2">
					<label class="col-md-3 col-form-label text-start" for="F_ImgPath">Image
						file*</label>
					<div class="col-md-9">
						<input type="file" class="form-control" id="F_ImgPath"
							accept=".jpg" name="imageMain" required>
					</div>
					<label class="col-md-3 col-form-label text-start fw-bold">*
						means input required.</label>
				</div>
				<hr>
				<p class="text-center lead" style="font-weight: bold;">Contents</p>
				<div class="row mb-4">
					<label class="col-md-3 col-form-label text-start"
						for="F_SummaryText">Blog summary</label>
					<div class="col-md-9">
						<textarea rows="3" class="form-control" id="F_SummaryText"
							placeholder="If this summary is empty, blog summary will be first 140 words of first description."
							name="summary"></textarea>
					</div>
				</div>
				<div class="row my-2">
					<label class="col-md-3 col-form-label text-start" for="F_Chapter">Chapter</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="F_Chapter"
							name="chapter0" maxlength="100">
					</div>
				</div>
				<div class="row my-2">
					<label class="col-md-3 col-form-label text-start" for="F_Section">Section</label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="F_Section"
							name="section0" maxlength="100">
					</div>
				</div>
				<div class="row my-2">
					<label class="col-md-3 col-form-label text-start" for="F_ImgPath">Image
						file</label>
					<div class="col-md-9">
						<input type="file" class="form-control" id="F_ImgPath"
							accept=".jpg" name="image0">
					</div>
				</div>
				<div class="row my-2" id="ModifyPoint">
					<label class="col-md-3 col-form-label text-start"
						for="F_Description">Description</label>
					<div class="col-md-9">
						<textarea rows="6" class="form-control" id="F_Description"
							name="description0"></textarea>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 text-end create-btn">
						<button type="submit" class="btn btn-primary">Create</button>
					</div>
				</div>
			</form>
			<div class="row">
				<div class="col-md-7 text-center FormModify1">
					<input type="image" src="img/logo_form_plus.svg" alt="Insert"
						class="Insert" data-bs-toggle="tooltip" data-bs-placement="top"
						title="Append Items">
				</div>
				<div class="col-md-4 text-center FormModify2">
					<input type="image" src="img/logo_form_minus.svg" alt="Delete"
						class="Delete" data-bs-toggle="tooltip" data-bs-placement="top"
						title="Delete Items">
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>

	<!-- Bootstrap Bundle with Popper -->
	<script src="js/bootstrap.bundle.min.js"></script>

</body>

</html>