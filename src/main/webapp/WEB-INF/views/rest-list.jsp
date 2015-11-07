<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="js/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/fancybox/jquery.fancybox.pack.js" type="text/javascript"></script>
<script src="js/functions.js" type="text/javascript"></script>
<script src="http://maps.googleapis.com/maps/api/js?sensor=false" type="text/javascript"></script>

<title>Рестораны</title>

<!--[if IE]><link rel="shortcut icon" href="img/favicon.ico"><![endif]-->
<link rel="icon" href="img/favicon.png">

<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>
<body>
	<div class="container">
		<h1>Рестораны</h1>
		<form action="RestList" method="post" class="form-user">
			<p><input type="search" name="queary" placeholder="Поиск по ресторанам" value="${quearyFromPost}" size="22" required>
			<input type="submit" value="Найти"></p>
			<c:if test="${quearyFromPost != null}">
				<a class="link-help" href="RestList">Полный список ресторанов</a>
			</c:if>
		</form>
		<form action="RestList" method="post" class="form-user">
			<p class="help">Отсортировать все рестораны:</p>
			<select id="sort" name="sort" onchange="this.form.submit()">
				<c:choose>
					<c:when test="${sortFromPost == null}">
						<option selected>Выберите сортировку</option>
					</c:when>
					<c:otherwise>
						<option disabled>Выберите сортировку</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortFromPost == 'total_rating'}">
						<option value="total_rating" selected>по суммарному рейтингу</option>
					</c:when>
					<c:otherwise>
						<option value="total_rating">по суммарному рейтингу</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortFromPost == 'cuisine_rating'}">
						<option value="cuisine_rating" selected>по ретингу кухни</option>
					</c:when>
					<c:otherwise>
						<option value="cuisine_rating">по ретингу кухни</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortFromPost == 'interior_rating'}">
						<option value="interior_rating" selected>по рейтингу интерьера</option>
					</c:when>
					<c:otherwise>
						<option value="interior_rating">по рейтингу интерьера</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortFromPost == 'service_rating'}">
						<option value="service_rating" selected>по рейтингу обслуживания</option>
					</c:when>
					<c:otherwise>
						<option value="service_rating">по рейтингу обслуживания</option>
					</c:otherwise>
				</c:choose>
			</select>
		</form>

		<ul class="rests-list">
			<c:forEach var="rest" items="${restList}">
			<li>
				<div class="rest-name"><a href="RestOne?rest=${rest.id}">${rest.name}</a></div>
				<div><span class="rate"><span style="width: ${rest.rateTotal * 100 / 5}%;" title="Общий рейтинг: ${rest.rateTotal}"></span></span></div>
			</li>
			</c:forEach>
		</ul>

		<div class="map-container"></div>

	 	<a class="mode" href="AdmRestList">Админка</a>
	</div>
</body>
</html>