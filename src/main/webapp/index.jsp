<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="WEB-INF/jsp/header.jsp" %>
<fmt:message bundle="${local}" key="local.index.welcome.phrase" var="welcome_phrase"/>
<fmt:message bundle="${local}" key="local.index.choose.action" var="choose_one"/>
<fmt:message bundle="${local}" key="local.index.view.course.list" var="view_course_list"/>
<fmt:message bundle="${local}" key="local.index.register.new.account" var="register_new_account"/>
<fmt:message bundle="${local}" key="local.index.sign.in.account" var="sign_in_account"/>
<fmt:message bundle="${local}" key="local.option.course.name" var="optional_courses"/>
<!DOCTYPE html>
<html>
	<head>
		<title>${optional_courses}</title>
		<link rel="icon" href=
				"img/book_logo.png"
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
                margin: 20px auto;
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
			img {
                display: block;
                margin-left: auto;
                margin-right: auto;
				width: 20%;
				padding: 15px 5px 0px 5px;
			}
		</style>
	</head>
	<body>


		<div class="main">
			<h1 style="text-align: center">${welcome_phrase}</h1>
			<img src="img/Logo%20without%20background%20or%20margins.png" alt="Logo">
			<div class="wrapper">
				<div class="title">
					<p>${choose_one}</p>
				</div>
				<div class="form">
					<form class="sign-in" action="controller" method="post">
						<form action="controller" method="post">
							<input type="hidden" name="command" value="get_courses_page">
							<input type="submit" value="${view_course_list}" class="sign_btn">
						</form>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="get_registration_page">
							<input type="submit" value="${register_new_account}" class="sign_btn">
						</form>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="get_sign_in_page">
							<input type="submit" value="${sign_in_account}" class="sign_btn">
						</form>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>