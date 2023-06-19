<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				Finished
			</h1>
			<form class="m-4">
				<div class="row">
					<div class="col-md-12 text-center fw-bold">
						<p class="lead">User signup is done. Please login using signup
							information.</p>
					</div>
					<div class="col-md-12 text-center">
						<a href="login.jsp">Go to Login Form</a>
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