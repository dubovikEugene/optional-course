<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<%@include file="header.jsp"%>

		<fmt:message bundle="${local}" key="local.sign.up.enter.your.details" var="enter_details"/>
		<fmt:message bundle="${local}" key="local.sign.up.first.name" var="first_name"/>
		<fmt:message bundle="${local}" key="local.sign.up.last.name" var="last_name"/>
		<fmt:message bundle="${local}" key="local.sign.up.birthdate" var="birtdate"/>
		<fmt:message bundle="${local}" key="local.sign.email" var="email"/>
		<fmt:message bundle="${local}" key="local.sign.password" var="password"/>
		<fmt:message bundle="${local}" key="local.sign.up.gender" var="gender"/>
		<fmt:message bundle="${local}" key="local.sign.up.gender.male" var="gender_male"/>
		<fmt:message bundle="${local}" key="local.sign.up.gender.female" var="gender_female"/>
		<fmt:message bundle="${local}" key="local.sign.up.button.name" var="sign_up_button"/>
		<fmt:message bundle="${local}" key="local.sign.up.have.account" var="have_account"/>
		<fmt:message bundle="${local}" key="local.sign.in.account" var="sign_in_account"/>

		<title>${sign_up}</title>
		<link rel = "icon" href =
				"../../img/book_logo.png"
			  type = "image/x-icon">
		<style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                list-style: none;
                text-decoration: none;
                outline: none;
                /*font-family: 'Open Sans', sans-serif;*/
            }

            body{
                font-size: 14px;
                background-color: #f9f9f9;
            }

            a{
                color: #0366d6;
            }

            .wrapper{
                max-width: 440px;
                width: 100%;
                height: auto;
                margin: 40px auto;
            }

            .wrapper .title p{
                margin-bottom: 15px;
                font-size: 24px;
                text-align: center;
                color: #333;
            }

            .wrapper .form{
                margin-bottom: 15px;
                background-color: #fff;
                border: 1px solid #d8dee2;
                border-radius: 5px;
                padding: 20px;
            }

            .wrapper .form .input_field{
                margin-bottom: 15px;
                position: relative;
            }

            .wrapper .form .input_field label{
                display: block;
                font-weight: 600;
                margin-bottom: 7px;
                color: #24292e;
            }

            .wrapper .form .input_field .input{
                width: 100%;
                background: #fff;
                border: 1px solid #d1d5da;
                box-shadow: inset 0 1px 2px rgba(27,31,35,.075);
                border-radius: 3px;
                color: #24292e;
                font-size: 16px;
                line-height: 20px;
                min-height: 34px;
                padding: 6px 8px;
            }

            .wrapper .form .input_field .input:focus{
                border-color: #2188ff;
                box-shadow: inset 0 1px 2px rgba(27,31,35,.075), 0 0 0 2.5px rgba(3,102,214,.3);
            }

            .wrapper .form .sign_btn{
                margin-top: 20px;
                background-color: #28a745;
                border: 1px solid rgba(27,31,35,.2);
                width: 100%;
                color: #fff;
                font-weight: 600;
                cursor: pointer;
                padding: 6px 12px;
                border-radius: 2px;
            }

            .wrapper .form .sign_btn:hover{
                background-color: #279f43;
            }

            .wrapper .have_acc{
                border: 1px solid #d8dee2;
                border-radius: 5px;
                padding: 15px 20px;
                text-align: center;
                margin-bottom: 40px;
            }

            .wrapper .have_acc a:hover{
                text-decoration: underline;
            }

            .main {
                margin-top: 75px;
            }

		</style>
	</head>
	<body>

		<div class="main">

			<div class="wrapper">
				<div class="title">
					<p>${enter_details}</p>
				</div>
				<div class="form">
					<form action="controller" method="post">
						<input type="hidden" class="input" name="command" value="registration">
						<div class="input_field">
							<label for="firstName">${first_name}</label>
							<input type="text" required class="input"  name="firstName" id="firstName">
						</div>
						<div class="input_field">
							<label for="lastName">${last_name}</label>
							<input type="text" required class="input"  name="lastName" id="lastName">
						</div>
						<div class="input_field">
							<label for="birthday">${birtdate}</label>
							<input type="date" required class="input"  name="birthday" id="birthday">
						</div>
						<div class="input_field">
							<label for="email">${email}</label>
							<input type="text" required class="input"  name="email" id="email">
						</div>
						<div class="input_field">
							<label for="password">${password}</label>
							<input type="password" required class="input"  name="password" id="password">
						</div>
						<div class="input_field">
							<label for="gender"> ${gender}</label>
							<select class="input" name="gender" id="gender">
								<option value="Male">${gender_male}</option>
								<option value="Female">${gender_female}</option>
							</select>
						</div>
						<div class="btn">
							<button type="submit" class="sign_btn">${sign_up_button}</button>
						</div>
					</form>
				</div>
				<div class="have_acc">
					<p>${have_account}? <a href="controller?command=get_sign_in_page">${sign_in_account}</a></p>
				</div>
			</div>


		</div>
	</body>
</html>
