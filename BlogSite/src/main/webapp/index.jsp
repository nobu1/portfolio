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
		<div class="container main-picture">
			<div class="row">
				<div style="text-align: center;" class="my-2">
					<div class="col-12">
						<h1>
							<img src="img/pic_main_h1.jpg" alt="Nobu's blog using Java"
								class="img-fluid rounded">
						</h1>
					</div>
				</div>
			</div>
		</div>
		<!-- /main-picture -->

		<div class="container">
			<div class="row">
				<div class="col-md-8 Description">
					<section>
						<h2>Specification</h2>
						<div class="card-deck back my-2">
							<div class="card m-4">
								<div class="card-header text-center">
									<h3 class="card-title mt-2"><img alt="Frontend Icon" src="img/logo_cardtitle_frontend.svg" width="20" height="20" class="mx-2 mb-2">Front end</h3>
								</div>
								<div class="card-body">
									<h4 class="card-text">
										<ul>
											<li>HTML</li>
											<li>CSS</li>
											<li>Javascript</li>
											<li>Bootstrap 5</li>
										</ul>
									</h4>
								</div>
							</div>
							<div class="card m-4">
								<div class="card-header text-center">
									<h3 class="card-title mt-2"><img alt="Backend Icon" src="img/logo_cardtitle_backend.svg" width="22" height="22" class="mx-2 mb-2">Back end</h3>
								</div>
								<div class="card-body">
									<h4 class="card-text">
										<ul>
											<li>Java (Jsp & Servlet)</li>
											<li>MySQL</li>
										</ul>
									</h4>
								</div>
							</div>
						</div>
					</section>
					<!-- /Specification -->

					<section>
						<h2>Portfolio description</h2>
						<dl class="m-4">
							<dt>What type of my portfolio?</dt>
							<dd>It is a blog site using Java. The site does not use a
								framework such as WordPress, Spring and so on.</dd>
							<dt>Functions</dt>
							<dd>There are several functions on my site such as signup,
								login and posting blog articles.</dd>
							<dt>Why choose Java language and does not use framework?</dt>
							<dd>I have certifications in Java SE11 Developer
								professional. So I show my Java development skills using the MVC
								model and design abilities. Since my last job was in cyber
								security, the site is considered to prevent typical cyber
								attacks: XSS, SQL injection, CSRF and so on.</dd>
							<dt>Detailed description</dt>
							<dd>
								There are detailed descriptions on my article pages. For
								example, how to design login functions. Please confirm <a
									href="articles/article.html">article pages</a>.
							</dd>
						</dl>
					</section>
				</div>
				<!-- /Description -->

				<div class="col-md-4 profile">
					<aside>
						<h2>Profile</h2>
						<dl>
							<dt>
								<img src="img/pic_main_profile.jpg" alt="Nobu's profile"
									class="rounded-circle pic_profile d-block mx-auto">
							</dt>
							<dd>My name is Shinji Nobuhara. I am an IT engineer for 4
								years in Japan.</dd>
							<dt>Why do I become an IT engineer?</dt>
							<dd>I noticed in early-2010 that IT technology changed the
								business environment drastically. Riding this transformation is
								a big chance for me. So I decided my career to be an IT engineer
								in 2019.</dd>
							<dt>My career</dt>
							<dd>
								I was a programmer of VB.NET between 2019 and 2021. In 2021, I
								switched occupations from programmer to security engineer. My
								main job is Security Operation Center(SOC) of Microsoft Defender
								for Endpoint and Carbon Black as 2nd tier analyst. <a
									href="https://github.com/nobu1/portfolio/blob/main/CurriculumVitae/CV_main_ShinjiNobuhara.pdf">About
									my details career, please confirm my CV.</a>
							</dd>
							<dt>Certifications</dt>
							<dd>
								<ul>
									<li>Oracle Certified Professional, Java SE 11 Developer</li>
									<li>Certified Ethical Hacker</li>
									<li>AWS Security Specialty</li>
									<li>AWS Solution Architect Professional</li>
									<li>Linux Professional Institute 304 Virtualization and
										High Availability</li>
								</ul>
							</dd>
						</dl>
					</aside>
				</div>
				<!-- /profile -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</main>

	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>

	<!-- Bootstrap Bundle with Popper -->
	<script src="js/bootstrap.bundle.min.js"></script>

</body>

</html>