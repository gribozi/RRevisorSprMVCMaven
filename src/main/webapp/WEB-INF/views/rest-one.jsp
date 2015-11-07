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

<title>${restOne.name}</title>

<!--[if IE]><link rel="shortcut icon" href="img/favicon.ico"><![endif]-->
<link rel="icon" href="img/favicon.png">

<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>
<body>
	<div class="container">
		<h1>${restOne.name}</h1>
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
	 	<div class="gallery">
		 	<a class="fancy" rel="group" title="Это фото 1" href="img/gallery/${restOne.id}/010-b.jpg"><img src="img/gallery/${restOne.id}/010-s.jpg" /></a>
		 	<a class="fancy" rel="group" title="Это фото 2" href="img/gallery/${restOne.id}/020-b.jpg"><img src="img/gallery/${restOne.id}/020-s.jpg" /></a>
		 	<a class="fancy" rel="group" title="Это фото 3" href="img/gallery/${restOne.id}/030-b.jpg"><img src="img/gallery/${restOne.id}/030-s.jpg" /></a>
		 	<a class="fancy" rel="group" title="Это фото 4" href="img/gallery/${restOne.id}/040-b.jpg"><img src="img/gallery/${restOne.id}/040-s.jpg" /></a>
		 	<a class="fancy" rel="group" title="Это фото 5" href="img/gallery/${restOne.id}/050-b.jpg"><img src="img/gallery/${restOne.id}/050-s.jpg" /></a>
		 	<a class="fancy" rel="group" title="Это фото 6" href="img/gallery/${restOne.id}/060-b.jpg"><img src="img/gallery/${restOne.id}/060-s.jpg" /></a>
		</div>
		<div data-background-alpha="0.0" data-buttons-color="#FFFFFF" data-counter-background-color="#ffffff" data-share-counter-size="12" data-top-button="false" data-share-counter-type="disable" data-share-style="1" data-mode="share" data-like-text-enable="false" data-mobile-view="false" data-icon-color="#ffffff" data-orientation="horizontal" data-text-color="#000000" data-share-shape="round-rectangle" data-sn-ids="fb.vk.tw.gp." data-share-size="20" data-background-color="#ffffff" data-preview-mobile="false" data-mobile-sn-ids="fb.vk.tw.wh.ok.gp." data-pid="1427468" data-counter-background-alpha="1.0" data-following-enable="false" data-exclude-show-more="false" data-selection-enable="true" class="uptolike-buttons social" ></div>
		
	 	<a class="link" href="RestList">Список ресторанов</a>
	 	<br><br>
	 	<a class="mode" href="AdmRestList">Админка</a>
	</div>
	
	
	<script type="text/javascript">(function(w,doc) {
	if (!w.__utlWdgt ) {
	    w.__utlWdgt = true;
	    var d = doc, s = d.createElement('script'), g = 'getElementsByTagName';
	    s.type = 'text/javascript'; s.charset='UTF-8'; s.async = true;
	    s.src = ('https:' == w.location.protocol ? 'https' : 'http')  + '://w.uptolike.com/widgets/v1/uptolike.js';
	    var h=d[g]('body')[0];
	    h.appendChild(s);
	}})(window,document);
	</script>
	
</body>
</html>