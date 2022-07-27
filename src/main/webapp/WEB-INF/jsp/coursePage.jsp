<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>

	<head>
		<%@include file="header.jsp" %>
		<fmt:message bundle="${local}" key="local.start.date.name" var="start_date"/>
		<fmt:message bundle="${local}" key="local.end.date.name" var="end_date"/>
		<fmt:message bundle="${local}" key="local.teacher.name" var="teacher"/>
		<fmt:message bundle="${local}" key="local.course.program.name" var="course_program"/>
		<fmt:message bundle="${local}" key="local.course.register.name" var="register"/>
		<fmt:message bundle="${local}" key="local.sign.up.to.the.courseRun" var="course_sign_up_desc"/>
		<fmt:message bundle="${local}" key="local.course.sign.in" var="course_sign_in"/>
		<fmt:message bundle="${local}" key="local.course.alredy.sign.up.course" var="alredy_sign_up"/>
		<fmt:message bundle="${local}" key="local.course.your.are.teacher" var="your_are_teacher"/>
		<fmt:message bundle="${local}" key="local.courseRun.edit.course" var="edit_course"/>
		<fmt:message bundle="${local}" key="local.teachear.profile.list.of.course.students" var="list_students"/>


		<title>${requestScope.course.courseName}</title>
		<link rel="icon" href=
				"../../img/book_logo.png"
			  type="image/x-icon">

		<style>
            .main {
                margin-top: 75px;
            }

            .submit-button {
                color: #fff;
                font-weight: 600;
                border: 1px solid rgba(27, 31, 35, .2);
                background-color: #28a745;
                width: 20%;
                cursor: pointer;
                padding: 6px 12px;
                border-radius: 2px;
                margin-bottom: 50px;
            }

            .submit-button:hover {
                background-color: #279f43;
            }

            .teacher_buttons form {
                display: inline-block;
            }

            .teacher_buttons .submit-button {
                width: 100%;
            }
		</style>
	</head>

	<body>

		<div class="main">
			<h1 style="text-align: center">${requestScope.course.courseName}</h1>
			<div style="text-align: center">
				<span>${start_date}: ${requestScope.course.startDate}</span> <br>
				<span>${end_date}: ${requestScope.course.endDate}</span> <br>
			</div>
			<div style="text-align: center">
				<span>
					<p> <h3>${teacher}:</h3> <span style="font-style: italic; margin-left: 25px">
						${requestScope.course.teacherFirstName}
						${requestScope.course.teacherLastName}
					</span>
					</p>
				</span>
			</div>
			<div style="text-align: center">
				<div style="display: inline-block; text-align: justify; margin: 0 200px 0 200px">
					${requestScope.course.description}
				</div>

			</div>
			<div style="text-align: center">
				<h3>${course_program}:</h3>
				<div style="display: inline-block; text-align: left;">
					${requestScope.course.courseProgram}
				</div>
			</div>

			<div style="text-align: center">
				<br>
				<c:if test="${empty sessionScope.user}">
					${course_sign_up_desc}
					<a href="controller?command=get_sign_in_page" style="padding-bottom: 50px;">
							${course_sign_in}
					</a>
				</c:if>
				<c:if test="${not fn:contains(sessionScope.user.courses, requestScope.course) and sessionScope.user.role eq 'student'}">
					<form action="controller">
						<input type="hidden" name="command" value="register_for_a_course">
						<input type="hidden" name="course_id" value="${requestScope.course.id}">
						<input class="submit-button" type="submit" value="${register}">
					</form>
				</c:if>
				<c:forEach var="user_course" items="${sessionScope.user.courses}">
					<c:choose>
						<c:when test="${user_course.id eq requestScope.course.id and sessionScope.user.role ne 'teacher'}">
							<form action="controller">
								<input type="hidden" name="command" value="get_profile_page">
								<p>${alredy_sign_up}</p>
								<input class="submit-button" type="submit" value="${profile}">
							</form>
						</c:when>
						<c:when test="${user_course.id eq requestScope.course.id and sessionScope.user.role eq 'teacher'}">
							<p>${your_are_teacher}</p>
							<div class="teacher_buttons">
								<form action="controller">
									<input type="hidden" name="command" value="get_course_edit_page">
									<input type="hidden" name="course_id" value="${requestScope.course.id}">
									<input class="submit-button" type="submit" value="${edit_course}">
								</form>
								<form action="controller">
									<input type="hidden" name="command" value="get_list_of_course_students_page">
									<input type="hidden" name="course_id" value="${requestScope.course.id}">
									<input class="submit-button" type="submit" value="${list_students}">
								</form>
								<form action="controller">
									<input type="hidden" name="command" value="get_profile_page">
									<input class="submit-button" type="submit" value="${profile}">
								</form>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</body>
</html>
