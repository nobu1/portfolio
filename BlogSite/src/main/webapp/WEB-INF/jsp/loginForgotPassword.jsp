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

<!-- CSS -->
<link rel="stylesheet" href="css/blog.css" type="text/css">

<!-- Javascript -->
<script src="js/form_LoginForgotValidation.js" defer></script>

<jsp:include page="/WEB-INF/jsp/title-meta.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>

	<main>
		<div class="container LoginForgotPasswordForm">
			<h1 class="mt-4">
				<img src="img/logo_form_login.svg" alt="login form logo"
					width="50px" height="50px" class="mb-3">Password Reset
			</h1>
			<form class="m-4" action="LoginServlet?action=change" method="post">
				<div class="row my-4">
					<label class="col-md-3 col-form-label text-start"
						for="F_LoginForgotEmail">E-mail address</label>
					<div class="col-md-6">
						<input type="email" class="form-control" id="F_LoginForgotEmail"
							name="email" required> <small id="email_check_forgot"></small>
					</div>
				</div>
				<div class="row my-4">
					<label class="col-md-3 col-form-label text-start"
						for="F_ResetNewPassword">New password</label>
					<div class="col-md-6">
						<input type="password" class="form-control"
							id="F_ResetNewPassword" name="password" minlength="8"
							maxlength="100" required> <small
							id="password_check_login"></small>
					</div>
				</div>
				<div class="row my-4">
					<label class="col-md-3 col-form-label text-start"
						for="F_ReEnterPassword">Confirm the new password</label>
					<div class="col-md-6">
						<input type="password" class="form-control" id="F_ReEnterPassword"
							name="ReEnterPassword" minlength="8" maxlength="100" required>
						<small>Please include uppercase, symbol("." , "?" , "/" ,
							"-") and number and input at least 8 characters.</small><br> <small
							id="password_check_forgot"></small>
					</div>
				</div>
				<div class="row my-2">
					<div class="col-md-12 text-center text-danger fw-bold">
						<c:if test="${not empty errorMsg}">
							<p>
								<c:out value="${errorMsg}" />
							</p>
						</c:if>
					</div>
					<div class="col-md-9 text-end d-flex justify-content-between">
						<a href="login.jsp" class="btn btn-light"
							role="button">Cancel</a>
						<button type="submit" class="btn btn-primary" id="changeBtn"
							disabled>Change</button>
					</div>
				</div>
			</form>
		</div>
		<!-- /LoginForgotPasswordForm -->
	</main>

	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>

	<!-- Bootstrap Bundle with Popper -->
	<script src="js/bootstrap.bundle.min.js"></script>

</body>

</html>