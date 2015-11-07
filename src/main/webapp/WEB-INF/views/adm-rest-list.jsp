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

<title>RRevisor Admin Panel</title>
  
<!--[if IE]><link rel="shortcut icon" href="img/favicon.ico"><![endif]-->
<link rel="icon" href="img/favicon.png">

<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>
<body class="adm">
	<div class="container">
	
		<c:choose>
		    <c:when test="${dellOK == true}">
		       <p style="color: green" class="message-adm">Выбранные рестораны успешно удалены.</p>
		    </c:when>
		    <c:when test="${dellOK == false}">
		       <p style="color: red" class="message-adm">К сожалению что-то пошло не так. Выбранные рестораны не удлены.</p>
		    </c:when>
		</c:choose>
		
		<h1>Рестораны</h1>
		<form action="AdmRestList" method="post" class="form-admin">
			<div class="btn-wrap-adm"><input type="submit" id="submitbtn" value="Удалить&nbsp;выбранные" disabled class="btn-adm"></div>
			<div class="btn-wrap-adm"><button formaction="AdmRestAddEdit" formmethod="post" class="btn-adm">Добавить&nbsp;ресторан</button></div>
			<ul class="rests-list">
				<c:forEach var="rest" items="${restList}">
				<li>
					<div><input type="checkbox" name="checked" class="restaurant" value="${rest.id}"></div>
					<div class="rest-name"><a href="AdmRestAddEdit?id=${rest.id}">${rest.name}</a></div>
					<div><span class="rate"><span style="width: ${rest.rateTotal * 100 / 5}%;" title="Общий рейтинг: ${rest.rateTotal}"></span></span></div>
				</li>
				</c:forEach>
			</ul>
		</form>
		<br><br>
	 	<a class="mode" href="RestList">Сайт</a>
	</div>

</body>
</html>