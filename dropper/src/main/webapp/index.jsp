<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:Dropper – drop your message anywhere!</title>
<link rel="stylesheet" href="css/style.css">
<script src="javascript/script.js"></script>
<script src="javascript/jquery-2.1.1.min.js"></script>
</head>
<body onload="initialLoad()">
	<div id="variableStore">
		<input type="hidden" name="lat" value="${lat}"> <input
			type="hidden" name="lon" value="${lon}"> <input type="hidden"
			name="error" value="${error}"> <input type="hidden"
			name="messages" value="${messages}">
	</div>

	<div id="headerArea">
		<p>${addr}
			<br />
		<h3>${error}</h3>
	</div>

	<div id="inputArea">
		<form action="/dropper/register.do">
			<textarea name="message" required="required"></textarea>
			<br /> name<input type=text name="name"> deleteKey<input
				type=text name="deleteKey"> <br />
			<button action="submit">送信</button>
		</form>

	</div>

	<div id="contentArea">

		<c:forEach var="msg" items="${messages}">
			<article>
				<div id="message">
					<p>${msg.message}</p>
				</div>
				<div id="messageFooter">
					<p>${msg.dateTime}${msg.name}</p>
				</div>
				<c:if test="${msg.deleteKey != null}">
					<form action="/dropper/remove.do">
						<input type="text" name="deleteKey"> <input type="hidden"
							name="msgNum" value=${msg.number
						}> <input
							type="submit" value="delete">
					</form>
				</c:if>
			</article>
		</c:forEach>
	</div>
</body>
</html>