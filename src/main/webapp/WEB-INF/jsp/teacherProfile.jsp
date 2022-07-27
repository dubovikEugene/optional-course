<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<%@include file="header.jsp" %>
		<fmt:message bundle="${local}" key="local.teacher.profile.menu" var="menu"/>
		<fmt:message bundle="${local}" key="local.profile.welcome" var="profile_welcome"/>
		<fmt:message bundle="${local}" key="local.teacher.profile.create.course" var="create_course"/>
		<fmt:message bundle="${local}" key="local.teacher.profile.course.list" var="course_list"/>
		<title>${profile}</title>
		<
		<link rel="icon" href=
				"../../img/book_logo.png"
			  type="image/x-icon">
		<style>
            .main {
                margin-top: 75px;
                margin-bottom: 75px;
            }


            .menu {
                margin: auto;
                text-align: center;
            }
		</style>
	</head>
	<body>

		<div class="main">
			<div style="text-align: center">
				<h2>${profile_welcome}, ${sessionScope.user.firstName}</h2>


				<div class="menu">
					<h3 style="text-align: center">
						${menu}
					</h3>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="get_teacher_courses_page">
						<input type="submit" value="${course_list}" class="submit-button">
					</form>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="get_create_course_run_page">
						<input type="submit" value="${create_course}" class="submit-button">
					</form>
				</div>
			</div>


		</div>
	</body>
</html>
