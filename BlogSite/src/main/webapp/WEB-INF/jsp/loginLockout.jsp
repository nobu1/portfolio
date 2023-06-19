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
		<div class="container LoginLockoutForm">
			<h1 class="mt-4">
				<img src="img/logo_form_login.svg" alt="login form logo" width="50px" height="50px" class="mb-3">Login
				Lockout
			</h1>
			<form class="m-4">
				<div class="row">
					<div class="col-md-12 text-center fw-bold">
						<p class="lead">Several login attemp is obserbed. Please wait until lockout process is solved.</p>
					</div>
					<div class="col-md-12 text-center">
						<a href="LoginServlet?action=lockout">Return</a>
					</div>
				</div>
			</form>
		</div>
		<!-- /LoginLockoutForm -->
	</main>

	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>

	<!-- Bootstrap Bundle with Popper -->
	<script src="js/bootstrap.bundle.min.js"></script>

</body>

</html>