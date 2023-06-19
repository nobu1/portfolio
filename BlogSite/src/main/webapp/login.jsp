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
<script src="js/form_LoginValidation.js" defer></script>

<jsp:include page="/WEB-INF/jsp/title-meta.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>

	<main>
		<div class="container LoginForm">
			<h1 class="mt-4">
				<img src="img/logo_form_login.svg" alt="login form logo">Login
				Form
			</h1>
			<form class="m-4" action="LoginServlet" method="post">
				<div class="row my-4">
					<div class="col-md-12 text-center fw-bold">
						<c:if test="${not empty notification}">
							<p>
								<c:out value="${notification}" />
							</p>
						</c:if>
					</div>
					<label class="col-md-3 col-form-label text-start"
						for="F_LoginEmail">E-mail address</label>
					<div class="col-md-6">
						<input type="email" class="form-control" id="F_LoginEmail"
							name="email" required> <small id="email_check_login"></small>
					</div>
				</div>
				<div class="row my-4">
					<label class="col-md-3 col-form-label text-start"
						for="F_LoginPassword">Password</label>
					<div class="col-md-6">
						<input type="password" class="form-control" id="F_LoginPassword"
							name="password" minlength="8" maxlength="100" required> <small
							id="password_check_login"></small>
					</div>
				</div>
				<div class="row my-2">
					<div class="col-md-9 my-2">
						<a href="LoginServlet?action=forgot" class="text-end fw-bold">Forgot
							password?</a>
					</div>
					<div class="col-md-9 my-2">
						 <a href="signup.jsp" class="text-end fw-bold">If you don't have an account, please signup.</a>
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
					<div class="col-md-9 text-end submit-btn">
						<button type="submit" class="btn btn-primary" id="loginBtn"
							disabled>Login</button>
					</div>
				</div>
			</form>
		</div>
		<!-- /LoginForm -->
	</main>

	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>

	<!-- Bootstrap Bundle with Popper -->
	<script src="js/bootstrap.bundle.min.js"></script>

</body>

</html>