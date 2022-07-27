<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<%@include file="header.jsp" %>
		<fmt:message bundle="${local}" key="local.sign.email" var="email"/>
		<fmt:message bundle="${local}" key="local.sign.password" var="password"/>
		<fmt:message bundle="${local}" key="local.sign.no.account" var="no_account"/>
		<fmt:message bundle="${local}" key="local.sign.create.account" var="create_account"/>
		<fmt:message bundle="${local}" key="local.sign.in.account" var="sign_in_account"/>
		<fmt:message bundle="${local}" key="local.sign.in.error" var="sign_in_error"/>

		<title>${sign_in_account}</title>
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

            .wrapper .form .input_field {
                margin-bottom: 15px;
                position: relative;
            }

            .wrapper .form .input_field label {
                display: block;
                font-weight: 600;
                margin-bottom: 7px;
                color: #24292e;
            }

            .wrapper .form .input_field .input {
                width: 100%;
                background: #fff;
                border: 1px solid #d1d5da;
                box-shadow: inset 0 1px 2px rgba(27, 31, 35, .075);
                border-radius: 3px;
                color: #24292e;
                font-size: 16px;
                line-height: 20px;
                min-height: 34px;
                padding: 6px 8px;
            }

            .wrapper .form .input_field .input:focus {
                border-color: #2188ff;
                box-shadow: inset 0 1px 2px rgba(27, 31, 35, .075), 0 0 0 2.5px rgba(3, 102, 214, .3);
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

            .wrapper .create_act {
                border: 1px solid #d8dee2;
                border-radius: 5px;
                padding: 15px 20px;
                text-align: center;
                margin-bottom: 40px;
            }

            .wrapper .have_acc a:hover {
                text-decoration: underline;
            }

            .main {
                margin-top: 150px;
            }

		</style>

	</head>
	<body>

		<div class="main">

			<div class="wrapper">
				<div class="title">
					<p>${sign_in_account}</p>
				</div>
				<div class="form">
					<form class="sign-in" action="controller" method="post">
						<input type="hidden" name="command" value="sign_in">
						<div class="input_field">
							<label for="emailId">${email}</label>
							<input type="text" class="input" name="email" id="emailId" required>
						</div>
						<div class="input_field">
							<label for="passwordId">${password}</label>
							<input type="password" class="input" name="password" id="passwordId" required>
						</div>
						<div class="btn">
							<button type="submit" class="sign_btn">${sign_in}</button>
						</div>
					</form>
				</div>
				<div class="create_act">
					<p>${no_account}? <a href="controller?command=get_registration_page">${create_account}</a></p>
				</div>
			</div>

			<c:if test="${param.error != null}">
				<div>
				<span style="color: red; text-align: center">
				<p> ${sign_in_error}</p>
				</span>
				</div>
			</c:if>
		</div>
	</body>
</html>
