<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<%@include file="header.jsp" %>
		<fmt:message bundle="${local}" key="local.sign.email" var="email"/>
		<fmt:message bundle="${local}" key="local.teachear.profile.list.of.course.students" var="student_list"/>
		<fmt:message bundle="${local}" key="local.teacher.add.feedback" var="add_feedback"/>

		<title>Title</title>
		<style>
        .main {
            margin-top: 75px;
        }

        details {
            width: 50%;
            margin: 0 auto ;
            background-color: rgba(241, 240, 240, 0.53);
            margin-bottom: .5rem;
            box-shadow: 0 .1rem 1rem -.5rem rgba(0,0,0,.4);
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

        /* THE MAGIC 🧙‍♀️ */
        details[open] > summary:before {
            transform: rotate(90deg);
        }


        details summary::-webkit-details-marker {
            display:none;
        }

        details > ul {
            padding-bottom: 1rem;
            margin-bottom: 0;
        }
        .summary-content {
            text-align: justify;
            padding: 5px 15px;
        }
        form {
            width: 50%;
            margin: auto;
            text-align: center;
            padding-bottom: 10px;
        }
        .submit-button {
            color: #fff;
            font-weight: 600;
            border: 1px solid rgba(27,31,35,.2);
            background-color: #28a745;
            width: 35%;
            cursor: pointer;
            padding: 6px 12px;
            border-radius: 2px;
			margin-top: 20px;
        }
        .submit-button:hover {
            background-color: #279f43;
        }</style>
	</head>
	<body>
		<div class="main">
			<h1 style="text-align: center">${student_list}:</h1>
			<c:forEach var="user" items="${requestScope.users}">
				<details class="courses-list">
					<summary class="courseRun-name">${user.firstName} ${user.lastName}
						<span style="float: right">${email}: ${user.email} </span>
					</summary>

					<c:choose>
						<c:when test="${user.isGetFeedback eq false}">
							<div class="summary-content">
								<form action="controller" method="post">
									<input type="hidden" name="command" value="get_feedback_page"/>
									<input type="hidden" name="userId" value="${user.id}"/>
									<input type="hidden" name="courseRunId" value="${requestScope.course_id}"/>
<%--									<input type="hidden" name="course_id" value="#">--%>
									<input type="submit" value="${add_feedback}" class="submit-button">
								</form>
							</div>
						</c:when>
						<c:when test="${user.isGetFeedback eq true}">
							HAVE FEEDBACK
						</c:when>
					</c:choose>

				</details>
			</c:forEach>
		</div>
	</body>
</html>
