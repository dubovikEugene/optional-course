<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<%@include file="header.jsp" %>
		<fmt:message bundle="${local}" key="local.courses.your.courses.list" var="your_courses_list"/>
		<fmt:message bundle="${local}" key="local.start.date.name" var="start_date"/>

		<fmt:message bundle="${local}" key="local.courses.more.details" var="more_details"/>

		<title>${courses_list_name}</title>
		<link rel = "icon" href =
				"../../img/book_logo.png"
			  type = "image/x-icon">
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
            }
            .submit-button:hover {
                background-color: #279f43;
            }
		</style>
	</head>
	<body>


		<div class="main">
			<h1 style="text-align: center">${your_courses_list}:</h1>
			<c:forEach var="course" items="${sessionScope.user.courses}">
				<details class="courses-list">
					<summary class="courseRun-name">${course.courseName}
						<span style="float: right">${start_date}: ${course.startDate}</span>
					</summary>
					<div class="summary-content">
							${course.description}
						<form action="controller" method="post">
							<input type="hidden" name="command" value="get_course_page"/>
							<input type="hidden" name="course_id" value="${course.id}">
							<input type="submit" value="${more_details}" class="submit-button">
						</form>
					</div>
				</details>
			</c:forEach>
		</div>
	</body>
</html>
