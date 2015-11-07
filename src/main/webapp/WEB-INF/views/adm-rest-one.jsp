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

<title>${restOne.name}: Saved</title>

<!--[if IE]><link rel="shortcut icon" href="img/favicon.ico"><![endif]-->
<link rel="icon" href="img/favicon.png">

<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>
<body class="adm">
	<div class="container">

		<c:choose>
		    <c:when test="${savedOK == true}">
		       <p style="color: green" class="message-adm">Всё успешно соранено.</p>
		    </c:when>
		    <c:when test="${savedOK == false}">
		       <p style="color: red" class="message-adm">К сожалению что-то пошло не так. Данные не сохранились.</p>
		    </c:when>
		</c:choose>
		
		<h1>${restOne.name}</h1>
		<form action="AdmRestList" method="post" class="form-admin">
			<div class="btn-wrap-adm"><input type="submit" class="btn-adm" value="Удалить&nbsp;ресторан"></div>
			<input type="hidden" name="id" value="${restOne.id}">
			
			<div class="btn-wrap-adm"><button formaction="AdmRestAddEdit" formmethod="get" class="btn-adm">Редактировать</button></div>
		</form>		
		<div class="allrates">
			<table class="rates-tabl">
				<tr>
					<td>Кухня:</td>
					<td><span class="rate"><span style="width: ${restOne.rateCuisine * 100 / 5}%;" title="${restOne.rateCuisine}"></span></span></td>
				</tr>
				<tr>
					<td>Интерьер:</td>
					<td><span class="rate"><span style="width: ${restOne.rateInterior * 100 / 5}%;" title="${restOne.rateInterior}"></span></span></td>
				</tr>
				<tr>
					<td>Обслуживание:</td>
					<td><span class="rate"><span style="width: ${restOne.rateService * 100 / 5}%;" title="${restOne.rateService}"></span></span></td>
				</tr>
				<tr>
					<td class="total">Общий рейтинг:</td>
					<td><span class="rate"><span style="width: ${restOne.rateTotal * 100 / 5}%;" title="${restOne.rateTotal}"></span></span></td>
				</tr>
			</table>
	 	</div>
	 	<p class="review">${restOne.review}</p>
	 	<c:choose>
	 		<c:when test="${(operationType == 'edit') || (operationType == null)}">
			 	<div class="gallery">
				 	<a class="fancy" rel="group" title="Это фото 1" href="img/gallery/${restOne.id}/010-b.jpg"><img src="img/gallery/${restOne.id}/010-s.jpg" /></a>
				 	<a class="fancy" rel="group" title="Это фото 2" href="img/gallery/${restOne.id}/020-b.jpg"><img src="img/gallery/${restOne.id}/020-s.jpg" /></a>
				 	<a class="fancy" rel="group" title="Это фото 3" href="img/gallery/${restOne.id}/030-b.jpg"><img src="img/gallery/${restOne.id}/030-s.jpg" /></a>
				 	<a class="fancy" rel="group" title="Это фото 4" href="img/gallery/${restOne.id}/040-b.jpg"><img src="img/gallery/${restOne.id}/040-s.jpg" /></a>
				 	<a class="fancy" rel="group" title="Это фото 5" href="img/gallery/${restOne.id}/050-b.jpg"><img src="img/gallery/${restOne.id}/050-s.jpg" /></a>
				 	<a class="fancy" rel="group" title="Это фото 6" href="img/gallery/${restOne.id}/060-b.jpg"><img src="img/gallery/${restOne.id}/060-s.jpg" /></a>
				</div>
			</c:when>
			<c:otherwise>
				<br>
			</c:otherwise>
		</c:choose>
	 	<a class="link" href="AdmRestList">Список ресторанов</a>
		<br><br>
	 	<a class="mode" href="RestList">Сайт</a>
	</div>
</body>
</html>