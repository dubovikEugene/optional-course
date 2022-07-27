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
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                list-style: none;
                text-decoration: none;
                outline: none;
                /*font-family: 'Open Sans', sans-serif;*/
            }

            body {
                font-size: 14px;
                background-color: #f9f9f9;
            }

            a {
                color: #0366d6;
            }

            .wrapper {
                max-width: 310px;
                width: 100%;
                height: auto;
                margin: 40px auto;
            }

            .wrapper .logo img {
                display: block;
                width: 48px;
                height: 48px;
                margin: 0 auto 25px;
            }

            .wrapper .title p {
                margin-bottom: 15px;
                font-size: 24px;
                text-align: center;
                color: #333;
            }

            .wrapper .form {
                margin-bottom: 15px;
                background-color: #fff;
                border: 1px solid #d8dee2;
                border-radius: 5px;
                padding: 20px;
            }


            .wrapper .form .sign_btn {
                margin-top: 20px;
                background-color: #28a745;
                border: 1px solid rgba(27, 31, 35, .2);
                width: 100%;
                color: #fff;
                font-weight: 600;
                cursor: pointer;
                padding: 6px 12px;
                border-radius: 2px;
            }

            .wrapper .form .sign_btn:hover {
                background-color: #279f43;
            }

            .wrapper .have_acc a:hover {
                text-decoration: underline;
            }

            .main {
                margin-top: 75px;
                margin-bottom: 75px;
            }
		</style>
	</head>
	<body>

		<div class="main">
			<h2 style="text-align: center">${profile_welcome}, ${sessionScope.user.firstName}</h2>

			<div class="wrapper">
				<div class="title">
					<p>${menu}</p>
				</div>
				<div class="form">
					<form class="sign-in" action="controller" method="post">
						<form action="controller" method="post">
							<input type="hidden" name="command" value="get_teacher_courses_page">
							<input type="submit" value="${course_list}" class="sign_btn">
						</form>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="get_create_course_run_page">
							<input type="submit" value="${create_course}" class="sign_btn">
						</form>
					</form>
				</div>
			</div>


		</div>
	</body>
</html>
