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

<title>
	<c:choose>
		<c:when test="${restOne.id != null}">
			${restOne.name}: Editing
		</c:when>
		<c:otherwise>
			Adding a new restaurant
		</c:otherwise>		
	</c:choose>
</title>

<!--[if IE]><link rel="shortcut icon" href="img/favicon.ico"><![endif]-->
<link rel="icon" href="img/favicon.png">

<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>
<body class="adm">
	<div class="container">
		
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
			
			<c:if test="${restOne.id != null}">
				<div class="btn-wrap-adm send-btn-adm"><button formaction="AdmRestList" class="btn-adm" formmethod="post">Удалить</button></div>
			</c:if>
		</form>		
			
	    <c:if test="${restOne.id != null}">
		 	<div class="gallery">
			 	<a class="fancy" rel="group" title="Это фото 1" href="img/gallery/${restOne.id}/010-b.jpg"><img src="img/gallery/${restOne.id}/010-s.jpg" /></a>
			 	<a class="fancy" rel="group" title="Это фото 2" href="img/gallery/${restOne.id}/020-b.jpg"><img src="img/gallery/${restOne.id}/020-s.jpg" /></a>
			 	<a class="fancy" rel="group" title="Это фото 3" href="img/gallery/${restOne.id}/030-b.jpg"><img src="img/gallery/${restOne.id}/030-s.jpg" /></a>
			 	<a class="fancy" rel="group" title="Это фото 4" href="img/gallery/${restOne.id}/040-b.jpg"><img src="img/gallery/${restOne.id}/040-s.jpg" /></a>
			 	<a class="fancy" rel="group" title="Это фото 5" href="img/gallery/${restOne.id}/050-b.jpg"><img src="img/gallery/${restOne.id}/050-s.jpg" /></a>
			 	<a class="fancy" rel="group" title="Это фото 6" href="img/gallery/${restOne.id}/060-b.jpg"><img src="img/gallery/${restOne.id}/060-s.jpg" /></a>
			</div>
			<form action="AdmRestOne" enctype="multipart/form-data" method="post" class="form-files-admin">
				<input type="file" name="photo" multiple accept="image/*,image/jpeg">
				<input type="submit" value="Отправить">
 			</form>
	    </c:if>
	    			
	 	<a class="link" href="AdmRestList">Список ресторанов</a>
		<br><br>
	 	<a class="mode" href="RestList">Сайт</a>
	</div>	 	
</body>
</html>