<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<%@include file="header.jsp" %>
		<fmt:message bundle="${local}" key="local.wrong.request.error.page.name" var="error_page_name"/>
		<fmt:message bundle="${local}" key="local.wrong.request.description" var="error_description"/>

		<title>${error_page_name}</title>
		<link rel="icon" href=
				"../../img/book_logo.png"
			  type="image/x-icon">
		<style>
            .main {
                margin-top: 75px;
            }
		</style>
	</head>
	<body>

		<div class="main">
			<div style="text-align: center; color: red; font-size: 35px">
				<p>
					${error_description}
				</p>
				<p>
					<a href="controller?command=get_main_page">${home_name}</a>
				</p>
			</div>
		</div>
	</body>
</html>
