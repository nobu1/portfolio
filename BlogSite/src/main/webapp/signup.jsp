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
<script src="js/form_SignupValidation.js" defer></script>

<jsp:include page="/WEB-INF/jsp/title-meta.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>

	<main>
		<div class="container SignupForm">
			<h1 class="mt-4">
				<img src="img/logo_form_signup.svg" alt="login form logo">Signup
				Form
			</h1>
			<form class="m-4" action="SignupServlet" method="post">
				<div class="row my-4">
					<label class="col-md-3 col-form-label text-start"
						for="F_NicknameSignup">Nick name</label>
					<div class="col-md-6">
						<input type="text" class="form-control" id="F_NicknameSignup"
							name="nickname" maxlength="100" required> <small
							id="nickname_check_signup"></small>
					</div>
				</div>
				<div class="row my-4">
					<label class="col-md-3 col-form-label text-start"
						for="F_EmailSignup">E-mail address</label>
					<div class="col-md-6">
						<input type="email" class="form-control" id="F_EmailSignup"
							name="email" required> <small id="email_check_signup"></small>
					</div>
				</div>
				<div class="row my-4">
					<label class="col-md-3 col-form-label text-start"
						for="F_PasswordSignup">Password</label>
					<div class="col-md-6">
						<input type="password" class="form-control" id="F_PasswordSignup"
							name="password" minlength="8" maxlength="100" required>
					</div>
				</div>
				<div class="row my-2">
					<label class="col-md-3 col-form-label text-start"
						for="F_RetypePasswordSignup">Re-type Password</label>
					<div class="col-md-6">
						<input type="password" class="form-control"
							id="F_RetypePasswordSignup" name="retype-password" minlength="8"
							maxlength="100" required> <small>Please include
							uppercase, symbol("." , "?" , "/" , "-") and number and input at
							least 8 characters.</small><br> <small id="password_check_signup"></small>
					</div>
				</div>
				<div class="row my-2">
					<div class="col-md-9 my-2">
						<a href="login.jsp" class="text-end fw-bold">Go to Login Form</a>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 text-center text-danger fw-bold">
						<c:if test="${not empty errorMsg}">
							<p>
								<c:out value="${errorMsg}" />
							</p>
						</c:if>
					</div>
					<div class="col-md-9 text-end">
						<button type="submit" class="btn btn-primary" id="signupBtn" disabled>Signup</button>
					</div>
				</div>
			</form>
		</div>
		<!-- /SignupForm -->
	</main>

	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>

	<!-- Bootstrap Bundle with Popper -->
	<script src="js/bootstrap.bundle.min.js"></script>

</body>

</html>