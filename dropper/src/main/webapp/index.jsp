<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:Dropper – drop your message anywhere!</title>

<script src="javascript/script.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css" />
<link href="css/NewFile.css" rel="stylesheet" type="text/css"
	media="all">
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>

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
			${error} <input type="hidden" name="dist" value="50">
	</div>

	<div id="inputArea">
		<textarea name="message" required="required"></textarea>
		<input type="button" value="drop" onclick="dropper_drop()">
		<div
			class="ui-body ui-body-g ui-corner-all ui-shadow padding-responsive">
			<a
				class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-icon-check ui-btn-icon-left"
				href="#popup_lin" data-transition="pop" data-rel="popup"
				data-position-to="window">+α</a>
		</div>
		<div class="ui-corner-all" id="popup_lin" data-role="popup"
			data-theme="a">
			<a
				class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-icon-delete ui-btn-icon-right ui-btn-icon-notext"
				href="#" data-rel="back">閉じる</a>
			<div class="ui-corner-top" data-role="header" data-theme="a">
				<h4>+α</h4>
			</div>
			<div class="ui-corner-bottom ui-content" data-role="content"
				data-theme="a">
				<label class="ui-hidden-accessible" for="u_id">name:</label> <input
					name="user" id="u_id" type="text" placeholder="Enter name" value=""
					data-theme="a"> <label class="ui-hidden-accessible"
					for="u_id">deleteKey:</label> <input name="user" id="u_id"
					type="text" placeholder="Delete Key" value="" data-theme="a">
			</div>
		</div>
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
					<div
						class="ui-body ui-body-g ui-corner-all ui-shadow padding-responsive">
						<a
							class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-icon-check ui-btn-icon-left"
							href="#popup_lin1" data-transition="pop" data-rel="popup"
							data-position-to="window">delete</a>
					</div>


					<div class="ui-corner-all" id="popup_lin1" data-role="popup"
						data-theme="a">

						<a
							class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-icon-delete ui-btn-icon-right ui-btn-icon-notext"
							href="#" data-rel="back">閉じる</a>
						<form>
							<div class="ui-corner-top" data-role="header" data-theme="a">

								<h4>delete</h4>
							</div>
							<div class="ui-corner-bottom ui-content" data-role="content"
								data-theme="a">
								<label class="ui-hidden-accessible" for="u_id">Key:</label> <input
									name="user" id="u_id" type="text"
									placeholder="Enter a deletekey" value="" data-theme="a">
								<button type="submit" data-theme="a" data-icon="check">Delete</button>
							</div>
						</form>
					</div>
				</c:if>
			</article>
		</c:forEach>
	</div>
</body>
</html>