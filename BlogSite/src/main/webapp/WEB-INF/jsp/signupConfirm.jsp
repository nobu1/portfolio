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

<jsp:include page="/WEB-INF/jsp/title-meta.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>

	<main>
		<div class="container SignupForm">
			<h1 class="mt-4">
				<img src="img/logo_form_signup.svg" alt="login form logo">Signup
				Confirmation Form
			</h1>
			<form class="m-4" action="SignupServlet" method="post">
				<div class="row my-4">
					<label class="col-md-3 col-form-label text-start"
						for="F_NIcknameSignup">Nick name</label>
					<div class="col-md-6">
						<c:if test="${not empty signupData.nickname}">
							<p class="form-control">
								<c:out  value="${signupData.nickname}"  />
							</p>
						</c:if>
					</div>
				</div>
				<div class="row my-4">
					<label class="col-md-3 col-form-label text-start"
						for="F_EmailSignup">E-mail address</label>
					<div class="col-md-6">
						<c:if test="${not empty signupData.email}">
							<p class="form-control">
								<c:out value="${signupData.email}" />
							</p>
						</c:if>
					</div>
				</div>
				<div class="row">
					<div class="col-md-9 d-flex justify-content-between">
						<a href="SignupServlet?action=cancel" class="btn btn-light" role="button">Cancel</a>
						<a href="SignupServlet?action=register" class="btn btn-primary"
							role="button">Register</a>
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