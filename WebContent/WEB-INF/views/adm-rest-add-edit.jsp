<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="<c:url value="/resources/static/css/style.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/static/js/fancybox/jquery.fancybox.css" />" rel="stylesheet" type="text/css" />
<script src="<c:url value="/resources/static/js/jquery-1.11.3.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/static/js/fancybox/jquery.fancybox.pack.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/static/js/functions.js" />" type="text/javascript"></script>

<title>
	<c:choose>
		<c:when test="${not empty restOne.id}">
			${restOne.name}: Editing
		</c:when>
		<c:otherwise>
			Adding a new restaurant
		</c:otherwise>		
	</c:choose>
</title>

<!--[if IE]><link rel="shortcut icon" href="<c:url value="/resources/static/img/favicon.png" />"><![endif]-->
<link rel="icon" href="<c:url value="/resources/static/img/favicon.png" />">

<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>
<body class="adm">
	<div class="container">
	
		<sec:authorize access="isAuthenticated()">
			<p>${userName} (<a href="<c:url value="/logout"/>" class="link">выйти</a>)</p>
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
			<p><a href="<c:url value="/login"/>" class="link">Войти</a></p>
		</sec:authorize>
		
		<form action="AdmRestOne" method="post" class="form-admin">
			<input type="text" name="name" class="name-adm" size="20" required value="${restOne.name}" placeholder="Название ресторана">
			<div class="allrates">
				<table class="rates-tabl">
					<tr>
						<td>Кухня:</td>
						<td>
							<c:forEach var="i" begin="1" end="5">
								<input type="radio" name="cuisine" required value="${i}" <c:if test="${restOne.rateCuisine == i}">checked</c:if>>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>Интерьер:</td>
						<td>
							<c:forEach var="i" begin="1" end="5">
								<input type="radio" name="interior" required value="${i}" <c:if test="${restOne.rateInterior == i}">checked</c:if>>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>Обслуживание:</td>
						<td>
							<c:forEach var="i" begin="1" end="5">
								<input type="radio" name="service" required value="${i}" <c:if test="${restOne.rateService == i}">checked</c:if>>
							</c:forEach>
						</td>
					</tr>
				</table>
			</div>

			<textarea name="review" cols="47" rows="10" placeholder="Ревью ресторана" class="review review-adm">${restOne.getReviewAdm()}</textarea>
			
			<input type="hidden" name="id" value="${restOne.id}">
			
			<div class="btn-wrap-adm send-btn-adm"><input type="submit" class="btn-adm" value="Сохранить&nbsp;ресторан"></div>
			
			<c:if test="${not empty restOne.id}">
				<div class="btn-wrap-adm send-btn-adm"><button formaction="AdmRestList" class="btn-adm" formmethod="post">Удалить</button></div>
			</c:if>
		</form>		
			
	    <c:if test="${not empty restOne.id}">
		 	<div class="gallery">
			 	<a class="fancy" rel="group" title="Это фото 1" href="<c:url value="/resources/static/img/gallery/${restOne.id}/010-b.jpg" />"><img src="<c:url value="/resources/static/img/gallery/${restOne.id}/010-s.jpg" />"></a>
			 	<a class="fancy" rel="group" title="Это фото 2" href="<c:url value="/resources/static/img/gallery/${restOne.id}/020-b.jpg" />"><img src="<c:url value="/resources/static/img/gallery/${restOne.id}/020-s.jpg" />"></a>
			 	<a class="fancy" rel="group" title="Это фото 3" href="<c:url value="/resources/static/img/gallery/${restOne.id}/030-b.jpg" />"><img src="<c:url value="/resources/static/img/gallery/${restOne.id}/030-s.jpg" />"></a>
			 	<a class="fancy" rel="group" title="Это фото 4" href="<c:url value="/resources/static/img/gallery/${restOne.id}/040-b.jpg" />"><img src="<c:url value="/resources/static/img/gallery/${restOne.id}/040-s.jpg" />"></a>
			 	<a class="fancy" rel="group" title="Это фото 5" href="<c:url value="/resources/static/img/gallery/${restOne.id}/050-b.jpg" />"><img src="<c:url value="/resources/static/img/gallery/${restOne.id}/050-s.jpg" />"></a>
			 	<a class="fancy" rel="group" title="Это фото 6" href="<c:url value="/resources/static/img/gallery/${restOne.id}/060-b.jpg" />"><img src="<c:url value="/resources/static/img/gallery/${restOne.id}/060-s.jpg" />"></a>
			</div>
			<form action="AdmRestOne" enctype="multipart/form-data" method="post" class="form-files-admin">
				<input type="file" name="photo" multiple accept="image/*,image/jpeg">
				<input type="submit" value="Отправить">
 			</form>
	    </c:if>
	    			
	 	<a class="link" href="AdmRestList">Список ресторанов</a>
		<br><br>
	 	<a class="mode" href="../">Сайт</a>
	</div>	 	
</body>
</html>