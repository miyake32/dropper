<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:Dropper – drop your message anywhere!</title>
<link rel="stylesheet" href="css/style.css">
<script src="javascript/script.js"></script>
</head>
<body>
	<div id="headerArea">
		<p>${addr} <br/>
			<form action="/dropper/geocode.do">
			<input type="text" name="lat" value="34"> <input type="text"
				name="lon" value="134"
			> <input type="submit" value="geo">
		</form>
		<h3>${error}</h3>
	</div>

	<div id="inputArea">
	
		<form action="/dropper/register.do">
			<textarea name="message" required="required"></textarea>
			<br /> name<input type=text name="name"> deleteKey<input
				type=text name="deleteKey"
			> <br />
			<button action="submit">送信</button>
		</form>

		<form action="/dropper/retrieve.do">
			<input type="hidden" name="dist" value="50"> <input
				type="submit" value="retrieve"
			>
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
						}> <input type="submit" value="delete">
					</form>
				</c:if>

			</article>
		</c:forEach>
	</div>
</body>
</html>