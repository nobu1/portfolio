<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">

<!-- CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/blog.css"
	type="text/css">

<jsp:include page="/WEB-INF/jsp/title-meta.jsp"></jsp:include>
</head>

<body>
	<header>
		<p class="main-theme m-2">Nobu's blog</p>
		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
			<div class="container-fluid">
				<button type="button" class="navbar-toggler"
					data-bs-toggle="collapse" data-bs-target="#Navber"
					aria-controls="Navber" aria-expanded="false"
					aria-label="switch navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="Navber">
					<ul class="navbar-nav me-auto mb-2 mb-md-0">
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="https://github.com/nobu1/portfolio"> <img
								src="<%=request.getContextPath()%>/img/logo_nav_github.svg"
								alt="github logo">GitHub
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/articles/articleList.jsp">Articles</a>
					</ul>
				</div>
				<!-- /collapse navbar-collapse -->
			</div>
			<!-- /container-fluid -->
		</nav>
	</header>