<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Optional courses</title>
		<link rel = "icon" href =
				"img/book_logo.png"
			  type = "image/x-icon">
		<style>
			.main {
				margin-top: 75px;
			}
		</style>
	</head>
	<body>
		<%@include file="WEB-INF/jsp/header.jsp"%>

		<div class="main">
			<h1><%= "Hello World!" %>
			</h1>
			<br/>


			>
			<a href="controller?command=get_courses_page">List Courses</a> <br>
			<br>

			>
			<a href="controller?command=get_registration_page">Registration</a> <br>
			>
			<a href="controller?command=get_sign_in_page">Sign in</a>
		</div>
	</body>
</html>