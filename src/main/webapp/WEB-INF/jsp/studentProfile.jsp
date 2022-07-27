<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<%@include file="header.jsp" %>

		<fmt:message bundle="${local}" key="local.student.profile.list.courses" var="student_courses"/>
		<fmt:message bundle="${local}" key="local.profile.course.drop" var="drop_course"/>
		<fmt:message bundle="${local}" key="local.profile.welcome" var="profile_welcome"/>
		<fmt:message bundle="${local}" key="local.courses.more.details" var="more_details"/>
		<fmt:message bundle="${local}" key="local.start.date.name" var="start_date"/>
		<fmt:message bundle="${local}" key="local.end.date.name" var="end_date"/>
		<fmt:message bundle="${local}" key="local.teacher.name" var="teacher_name"/>
		<fmt:message bundle="${local}" key="local.profile.mark" var="mark"/>
		<fmt:message bundle="${local}" key="local.profile.feedback" var="feedback"/>
		<fmt:message bundle="${local}" key="local.profile.feedback.wait" var="feedback_wait"/>
		<fmt:message bundle="${local}" key="local.profile.dont.have.courses" var="dont_have_course"/>

		<title>${profile}</title>
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

            form {
                display: inline-block;
                margin: 0 5px;
                padding-bottom: 10px;
            }

            .submit-button {
                color: #fff;
                font-weight: 600;
                border: 1px solid rgba(27, 31, 35, .2);
                background-color: #28a745;
                cursor: pointer;
                padding: 6px 12px;
                border-radius: 2px;
            }

            .submit-button:hover {
                background-color: #279f43;
            }

            details {
                width: 50%;
                margin: 0 auto;
                background-color: rgba(241, 240, 240, 0.53);
                margin-bottom: .5rem;
                box-shadow: 0 .1rem 1rem -.5rem rgba(0, 0, 0, .4);
                border-radius: 5px;
                overflow: hidden;
            }

            summary {
                padding: 1rem;
                display: block;
                background: #ffffff;
                padding-left: 2.2rem;
                position: relative;
                cursor: pointer;
            }

            summary:before {
                content: '';
                border-width: .4rem;
                border-style: solid;
                border-color: transparent transparent transparent #181818;
                position: absolute;
                top: 1.3rem;
                left: 1rem;
                transform: rotate(0);
                transform-origin: .2rem 50%;
                transition: .25s transform ease;
            }

            details[open] > summary:before {
                transform: rotate(90deg);
            }


            details summary::-webkit-details-marker {
                display: none;
            }

            details > ul {
                padding-bottom: 1rem;
                margin-bottom: 0;
            }

            .summary-content {
                text-align: justify;
                padding: 5px 15px;
            }

            table {
                width: 100%;
                border-spacing: 7px 11px;
            }

            .inner-details {
                width: 100%;
                overflow: auto;
            }

		</style>
	</head>
	<body>


		<div class="main">
			<div style="text-align: center">
				<h2>${profile_welcome}, ${sessionScope.user.firstName}</h2>
			</div>
			<div style="text-align: center">
				<h2>${student_courses}:</h2>

			</div>
			<c:if test="${empty sessionScope.user.courses}">
				<details class="courses-list">
					<summary>
							${dont_have_course}
					</summary>
				</details>
				</td>
				</tr>
			</c:if>
			<c:forEach var="courseRun" items="${sessionScope.user.courses}">
				<details class="courses-list">
					<summary class="courseRun-name">${courseRun.courseName}</summary>
					<div class="summary-content">

						<table style="text-align: left">
							<tbody>
								<tr>
									<td>${start_date}:</td>
									<td>${courseRun.startDate}</td>
								</tr>
								<tr>
									<td>${end_date}:</td>
									<td>${courseRun.endDate}</td>
								</tr>
								<tr>
									<td>${teacher_name}:</td>
									<td>${courseRun.teacherFirstName} ${courseRun.teacherLastName}</td>
								</tr>
								<c:if test="${empty sessionScope.user.feedbacks}">
									<tr>
										<td>${mark}:</td>
										<td>-/10</td>
									</tr>
									<tr>
										<td colspan="2">
											<details class="inner-details">
												<summary>
														${feedback}
												</summary>
												<div class="inner-summary summary-content">
														${feedback_wait}
												</div>
											</details>
										</td>
									</tr>
								</c:if>
								<c:forEach var="feed" items="${sessionScope.user.feedbacks}">
									<c:choose>
										<c:when test="${courseRun.id eq feed.courseRunId}">
											<tr>
												<td>${mark}:</td>
												<td>${feed.mark}/10</td>
											</tr>
											<tr>
												<td colspan="2">
													<details class="inner-details">
														<summary>
																${feedback}
														</summary>
														<div class="inner-summary summary-content">
																${feed.feedback}
														</div>
													</details>
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td>${mark}</td>
												<td> -/10</td>
											</tr>
											<tr>
												<td colspan="2">
													<details style="width: 100%">
														<summary>
																${feedback}
														</summary>
														<div class="inner-summary summary-content">
																${feedback_wait}
														</div>
													</details>
												</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>

							</tbody>
						</table>
					</div>
					<br>
					<div class="menu">
						<form action="controller" method="post">
							<input type="hidden" name="command" value="get_course_page">
							<input type="hidden" name="course_id" value="${courseRun.id}">
							<input type="submit" value="${more_details}" class="submit-button">
						</form>


						<form action="controller" method="post">
							<input type="hidden" name="command" value="drop_course_from_student">
							<input type="hidden" name="course_id" value="${courseRun.id}">
							<input type="submit" value="${drop_course}" class="submit-button">
						</form>
					</div>


				</details>
			</c:forEach>
		</div>


	</body>
</html>
