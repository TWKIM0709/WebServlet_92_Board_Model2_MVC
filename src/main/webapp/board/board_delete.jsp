<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String idx = (String)request.getAttribute("idx");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	 <link rel="Stylesheet" href="<%=request.getContextPath()%>/style/default.css" />
	<script type="text/javascript">
	function delCheck(){
		
		if(del.pwd.value==""){
			alert("비밀번호를 입력해야합니다.");
			del.pwd.focus();
			return false;
		}
		if(del.pwd.value.length>8){
			alert("비밀번호는 8자리 이내입니다.");
			del.pwd.select();
			return false;
		}//if---------
		document.del.submit();
	}
</script>

</head>
<body>
	<%
		pageContext.include("/include/header.jsp");
	%>
	<div id="pageContainer">
		<div style="padding-top: 25px; text-align: center">
			<form name="del" method="POST" action="deleteok.do">
				<center>
					비밀번호 :
					<input type="password" name="pwd">
					<input type="hidden"  name="idx" value="<%=idx %>">
					<hr width="500" color="gold">
					<input type="button" value="삭제" onclick="delCheck();">
					<input type="reset" value="다시">
				</center>
			</form>
		</div>
	</div>
</body>
</html>