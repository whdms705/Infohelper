<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

<script>
$(function() {
	  $("#datepicker").datepicker({
		  dateFormat:'yymmdd',
		  monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		  dayNamesMin:['일','월','화','수','목','금','토'],
		  changeMonth:true, // 월변경가능
		  changeYear:true,  // 년변경가능
		  showMonthAfterYear:true // 년 뒤에 월표시
		  
		  
		  
	  });
	 });
</script>


<title>Insert title here</title>
</head>
<body>
	<div id="wrap">
		<%@ include file="/WEB-INF/views/nav.jsp"%>

		<div class="container">
			<div id="article">
			
 Date: <input type="text" id="datepicker" />
 
 

<div id="calendar" >
		</div>
<script type="text/javascript" src="assets/js/calendar.js"></script>
<script type="text/javascript" src="assets/js/fullcalendar.min.js"></script>
<script type="text/javascript" src='assets/js/lang-all.js'></script>
<script type="text/javascript" src='assets/js/moment.min.js'></script>
			
			
			</div>
		</div>





	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>