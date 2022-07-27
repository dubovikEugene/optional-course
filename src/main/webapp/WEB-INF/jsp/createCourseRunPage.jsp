<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<%@include file="header.jsp" %>
		<fmt:message bundle="${local}" key="local.course.create.select.course" var="select_course_create"/>
		<fmt:message bundle="${local}" key="local.start.date.name" var="start_date"/>
		<fmt:message bundle="${local}" key="local.end.date.name" var="end_date"/>
		<fmt:message bundle="${local}" key="local.course.program.name" var="course_program"/>
		<fmt:message bundle="${local}" key="local.teacher.profile.create.course" var="create_course_run"/>
		<fmt:message bundle="${local}" key="local.course.create.write.course.program" var="write_course_program"/>
		<fmt:message bundle="${local}" key="local.create.course.enter.course.details" var="enter_course_details"/>
		<title>Title</title>
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
					<p>${enter_course_details}</p>
				</div>
				<div class="form">
					<form action="controller" method="post">
						<input type="hidden" class="input" name="command" value="create_course_run">
						<div class="input_field">
							<label for="courseId">${select_course_create}</label>

							<select class="input" name="courseId" id="courseId">
								<c:forEach var="course" items="${requestScope.courses}">
								<option value="${course.id}">${course.courseName}</option>
								</c:forEach>
							</select>

						</div>
						<div class="input_field">
							<label for="startDate">${start_date}</label>
							<input type="date" required class="input"  name="startDate" id="startDate">
						</div>
						<div class="input_field">
							<label for="endDate">${end_date}</label>
							<input type="date" required class="input"  name="endDate" id="endDate">
						</div>
						<div class="input_field">
							<label for="courseProgram">${course_program}</label>
							<textarea name="courseProgram" id="courseProgram" cols="30"
									  rows="10" style="width: 100%" placeholder="${write_course_program}"></textarea>
						</div>
						<div class="btn">
							<button type="submit" class="sign_btn">${create_course_run}</button>
						</div>
					</form>
				</div>
			</div>


		</div>
	</body>
</html>
