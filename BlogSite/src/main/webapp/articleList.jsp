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

	<div class="container">
		<main>
			<div class="table-responsive m-5 Edit-Delete">
				<table class="table table-hover text-nowrap">
					<thead class="colum-color">
						<tr>
							<th scope="col">Article Title</th>
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
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="row">
				<div class="col-md-12">
					<nav aria-label="Page navigation">
						<ul class="pagination justify-content-center">
							<c:choose>
								<c:when test="${currentPage == 1}">
									<li class="page-item disabled">
										<a class="page-link" href="#">Previous</a>
									</li>
									<li class="page-item" name="currentPage">
										<a class="page-link" href="ArticleServlet">
										<c:out value="${currentPage}" /> / <c:out value="${paginationRange}" />
										</a>
									</li>
								</c:when>
								<c:when test="${currentPage != 1}">
									<li class="page-item">
										<a class="page-link" href="ArticleServlet?action=prevnext&currentpage=${currentPage - 1}">Previous</a>
									</li>
									<li class="page-item" name="currentPage">
										<a class="page-link" href="ArticleServlet">
										<c:out value="${currentPage}" /> / <c:out value="${paginationRange}" />
										</a>
									</li>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${paginationRange == 1}">
									<li class="page-item disabled">
										<a class="page-link" href="#">Next</a>
									</li>
								</c:when>
								<c:when test="${paginationRange != 1 and paginationRange != currentPage}">
									<li class="page-item">
										<a class="page-link" href="ArticleServlet?action=prevnext&currentpage=${currentPage + 1}">Next</a>
									</li>
								</c:when>
								<c:when test="${paginationRange != 1 and paginationRange == currentPage}">
									<li class="page-item disabled">
										<a class="page-link" href="#">Next</a>
									</li>
								</c:when>
							</c:choose>
						</ul>
					</nav>
				</div>
			</div>
		</main>
	</div>



	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>

	<!-- Bootstrap Bundle with Popper -->
	<script src="js/bootstrap.bundle.min.js"></script>

</body>

</html>