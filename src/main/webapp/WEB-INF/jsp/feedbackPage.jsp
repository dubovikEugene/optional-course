<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<fmt:message bundle="${local}" key="local.feedback.give.your.feedback" var="give_your_feedback"/>
<fmt:message bundle="${local}" key="local.feedback.name" var="feedback_name"/>
<fmt:message bundle="${local}" key="local.feedback.mark.name" var="mark_name"/>
<fmt:message bundle="${local}" key="local.feedback.add" var="add_feedback"/>

<html>

	<head>
		<title>${feedback_name}</title>
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
		div class="main">

		<div class="wrapper">
			<div class="title">
				<p>${give_your_feedback}</p>
			</div>
			<div class="form">
				<form class="sign-in" action="controller" method="post">
					<input type="hidden" name="command" value="add_feedback">
					<input type="hidden" name="courseRunId" value="${requestScope.courseRunId}">
					<input type="hidden" name="studentId" value="${requestScope.userId}">
					<div class="input_field">
						<label for="mark">${mark_name}</label>
						<select class="input" name="mark" id="mark">
							<option value="1"> 1</option>
							<option value="1"> 2</option>
							<option value="1"> 3</option>
							<option value="1"> 4</option>
							<option value="1"> 5</option>
							<option value="1"> 6</option>
							<option value="1"> 7</option>
							<option value="1"> 8</option>
							<option value="1"> 9</option>
							<option value="1"> 10</option>
						</select>

					</div>
					<div class="input_field">
						<label for="feedback">${feedback_name}</label>
						<textarea name="feedback" id="feedback" cols="30"
								  rows="10" style="width: 100%" placeholder="${give_your_feedback}"></textarea>
					</div>
					<div class="btn">
						<button type="submit" class="sign_btn">${add_feedback}</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
