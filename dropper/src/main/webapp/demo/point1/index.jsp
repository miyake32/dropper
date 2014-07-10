<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:Dropper – drop your message anywhere!</title>

<script src="script.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="../../css/jquery.mobile-1.4.3.min.css"
/>

<script src="../../javascript/jquery-2.1.1.min.js"></script>
<script
	src="../../javascript/jquery.mobile-1.4.3.min.js"
></script>


</head>
<body onload="initialLoad()">
	<div id="variableStore">
		<input type="hidden" name="error" value="${error}"> <input
			type="hidden" name="messages" value="${messages}"
		> <input type="hidden" name="addr" value="${addr}">

	</div>
	<div id="headerArea">
		<table>
			<tr>
				<th>上目黒（東京都目黒区）</th>
				<!-- <td class=data-role= "footer" data-theme="g">
					<form name="distForm" id="distForm">
						<select name="dist" id="dist" onchange="select_box()"
							data-theme="g" data-mini="true" data-inline="true"
						>
							<option value="select01">10</option>
							<option value="select02">50</option>
							<option value="select03">100</option>
							<option value="select04">500</option>
						</select>
					</form>
				</td> -->
				<td><a
					class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-btn-icon-notext ui-icon-gear ui-btn-inline"
					href="#popup_lin" data-transition="pop" data-rel="popup"
					data-position-to="window"
				></a></td>
			</tr>
		</table>
	</div>



	<div class="ui-corner-all" id="popup_lin" data-role="popup"
		data-theme="a"
	>


		<form>
			<div class="ui-corner-bottom ui-content" data-role="content"
				data-theme="a"
			>
				<label class="ui-hidden-accessible" for="u_id">name:</label> <input
					name="inputName" id="u_id" type="text" placeholder="name"
					value="${nameInStorage}" data-theme="a"
				> <label class="ui-hidden-accessible" for="u_id">delete
					key:</label> <input name="inputDeleteKey" id="u_id" type="text"
					placeholder="delete key" value="${deleteKeyInStorage}"
					data-theme="a"
				>
				<input type="submit" onclick="saveToStorage()" data-theme="a" data-icon="check" value="Save"></input>
			</div>
		</form>
	</div>

	<div id="inputArea">

		<form>
			<textarea name="messageBox" required="required"
				placeholder="Drop message!"
			></textarea>

			<input type="submit" value="drop" onclick="drop()">
		</form>


	</div>
	<div id="contentArea">

		<c:forEach var="msg" items="${messages}">
			<article name="parentMessage">
				<div id="message">
					<p>${msg.message}</p>
				</div>
				<table border="3">
					<tr>

						<th>
							<div id="messageFooter">
								<fmt:formatDate value="${msg.dateTime}" type="both"/> 
								<c:if test='${msg.name != "" && msg.name != null}'>
								<br/>dropped by ${msg.name}</c:if>
							</div>
						</th>
						<td><a
							class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-btn-icon-notext ui-icon-mail ui-btn-inline"
							href="#popup_lin3_${msg.number}" data-transition="pop" data-rel="popup"
							data-position-to="window"
						></a> <c:if test='${msg.deleteKey != "" && msg.deleteKey != null}'>
								<a
									class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-btn-icon-notext ui-icon-delete ui-btn-inline"
									href="#popup_lin1_${msg.number}" data-transition="pop" data-rel="popup"
									data-position-to="window"
								></a>
							</c:if></td>

					</tr>
				</table>

				<c:if test="${msg.reMessages != null}">
					<div data-role="collapsible" data-mini="true">
						<h6>${fn:length(msg.reMessages)} reply dropped</h6>
						<c:forEach var="reMsg" items="${msg.reMessages}">
							<article name="childMessage">
								<div id="childMessage">
									<p>${reMsg.message}</p>
								</div>
								<div id="childMessageFooter">
									<table border="3">
										<tr>

											<th>
												<div id="messageFooter">
													<fmt:formatDate value="${reMsg.dateTime}" type="both"/> 
								<c:if test='${reMsg.name != "" && reMsg.name != null}'>
								<br/>dropped by ${reMsg.name}</c:if>
												</div>
											</th>
											<c:if test='${reMsg.deleteKey != "" && reMsg.deleteKey != null}'>
												<td><a
													class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-btn-icon-notext ui-icon-delete ui-btn-inline"
													href="#popup_lin2_${reMsg.number}" data-transition="pop" data-rel="popup"
													data-position-to="window"
												></a></td>
											</c:if>

										</tr>
									</table>
								</div>

							</article>
							<div class="ui-corner-all" id="popup_lin2_${reMsg.number}" data-role="popup"
								data-theme="a"
							>


								<form>
									<div class="ui-corner-top" data-role="header" data-theme="a">

									</div>
									<div class="ui-corner-bottom ui-content" data-role="content"
										data-theme="a"
									>
										<label class="ui-hidden-accessible" for="u_id">Key:</label> <input
											name="inputDeleteKey${reMsg.number}" id="u_id" type="text"
											placeholder="delete key" value="${deleteKeyInStorage}"
											data-theme="a" required="required"
										>
										<input type="submit" onclick="removeMessage(${reMsg.number})" data-theme="a" data-icon="check" value="Delete"></input>
									</div>
								</form>
							</div>
						</c:forEach>

					</div>
				</c:if>




				<div class="ui-corner-all" id="popup_lin1_${msg.number}" data-role="popup"
					data-theme="a"
				>


					<form>
						<div class="ui-corner-top" data-role="header" data-theme="a">

						</div>
						<div class="ui-corner-bottom ui-content" data-role="content"
							data-theme="a"
						>
							<label class="ui-hidden-accessible" for="u_id">Key:</label> <input
								name="inputDeleteKey${msg.number}" id="u_id" type="text"
								placeholder="delete key" value="${deleteKeyInStorage}"
								data-theme="a" required="required"
							>
							<input type="submit" onclick="removeMessage(${msg.number})" data-theme="a" data-icon="check" value="Delete"></input>
						</div>
					</form>
				</div>


				<div class="ui-corner-all" id="popup_lin3_${msg.number}" data-role="popup"
					data-theme="a"
				>


					<form>
						<div class="ui-corner-top" data-role="header" data-theme="a">


						</div>
						<div class="ui-corner-bottom ui-content" data-role="content"
							data-theme="a"
						>
							<label class="ui-hidden-accessible" for="u_id">コメント:</label>
							<div id="inputArea">

								<form>
									<textarea name="reMessage${msg.number}" required="required"
										placeholder="Drop reply!"
									></textarea>
									<input type="submit" value="reply"
										onclick="reDrop(${msg.number})"
									>
								</form>
							</div>
						</div>
					</form>
				</div>




			
			</article>
		</c:forEach>
	</div>
</body>
</html>
