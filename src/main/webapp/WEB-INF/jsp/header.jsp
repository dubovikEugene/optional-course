<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    body {
        margin: 0;
        background-color: #f9f9f9;
        height: 100vh;
        font-family: sans-serif;
        line-height: 1.5;
        letter-spacing: 1px;
        margin-top: 2rem;
    }

    .navbar {
        overflow: hidden;
        background-color: #333;
        position: fixed;
        top: 0;
        font-size: 19px;
        width: 100%;
    }

    .navbar a {
        float: left;
        display: block;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 19px;
    }

    .navbar a:hover {
        background: #ddd;
        color: black;
    }


    .sign-button {
        float: right;
        margin-right: 10px;
    }

    .logo {
        float: left;
        display: block;
        text-align: center;
        padding: 6px 25px;
        text-decoration: none;
        font-size: 17px;
    }

</style>


<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="local"/>
<fmt:message bundle="${local}" key="local.localbutton.name.en" var="button_en"/>
<fmt:message bundle="${local}" key="local.localbutton.name.ru" var="button_ru"/>
<fmt:message bundle="${local}" key="local.home.name" var="home_name"/>
<fmt:message bundle="${local}" key="local.courses.list.name" var="courses_list_name"/>
<fmt:message bundle="${local}" key="local.sign.in.name" var="sign_in"/>
<fmt:message bundle="${local}" key="local.sign.up.name" var="sign_up"/>
<fmt:message bundle="${local}" key="local.sign.out.name" var="sign_out"/>
<fmt:message bundle="${local}" key="local.profile.name" var="profile"/>


<div class="navbar">

	<div class="logo">
		<%@include file="/img/logo.svg" %>
	</div>

	<a class="active" href="controller?command=get_main_page">${home_name}</a>
	<a href="controller?command=get_courses_page">${courses_list_name}</a>


	<div class="sign-button">
		<a href="controller?local=ru&command=change_local">${button_ru}</a>
		<a href="controller?local=en&command=change_local">${button_en}</a>
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<a href="controller?command=get_sign_in_page">
						${sign_in}
				</a>
				<a href="controller?command=get_registration_page">
						${sign_up}
				</a>
			</c:when>
			<c:when test="${not empty sessionScope.user}">
				<a href="controller?command=get_profile_page">
						${profile}
				</a>

				<a href="controller?command=sign_out">
						${sign_out}
				</a>
			</c:when>
		</c:choose>
	</div>

</div>


