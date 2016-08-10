<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/header.jsp"%>
<link rel="stylesheet" type="text/css" href="assets/css/fund.css" />
<script type="text/javascript" src="assets/js/fund.js"></script>
<title>Insert title here</title>
</head>
<body>

<table class="step_form">
        	<colgroup>
            	<col width="25%">
                <col width="25%">
                <col width="25%">
                <col width="25%">
            </colgroup>
            <tbody><tr>
            	<th class="choice step_title"><div class="step1"></div><span>를 선택해주세요</span></th>
              <th class="default step_title"><div class="step2"></div><span>를 선택해주세요</span></th>
              <th class="default step_title"><div class="step3"></div><span>를 선택해주세요</span></th>
              <th class="default step_title"><div class="step4"></div><span>를 선택해주세요</span></th>
            </tr>
            <tr>
	            	<td class="current">
                	<ul id="step1"  value=1>
<li class="img_button"   onclick="load_fund('일반기부금')">일반기부금</li>

<li class="img_button" onclick="load_fund('지정기부금')">지정기부금</li>

</ul>
                </td>
                <td class="default">
                	<ul id="step2">

                  </ul>
                </td>
                <td class="default">
                	<ul id="step3">

                  </ul>
                </td>
                <td class="default">
                	<ul id="step4">

                  </ul>
                </td>
            </tr>
        </tbody></table>

</body>
</html>