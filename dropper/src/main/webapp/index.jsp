<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:Dropper – drop your message anywhere!</title>

<script src="javascript/script.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css"
/>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"
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
				<th>${addr}</th>
				<td class=data-role= "footer" data-theme="g">
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
				</td>
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
					name="user" id="u_id" type="text" placeholder="name"
					value="${nameInStorage}" data-theme="a"
				> <label class="ui-hidden-accessible" for="u_id">delete
					key:</label> <input name="user" id="u_id" type="text"
					placeholder="delete key" value="${deleteKeyInStorage}"
					data-theme="a"
				>
				<button type="submit" data-theme="a" data-icon="check">Save</button>
			</div>
		</form>
	</div>

	<div id="inputArea">

		<form action="/dropper/register.do">
			<textarea name="message" required="required"
				placeholder="Drop message!"
			></textarea>

			<input type="button" value="drop" onclick="drop()">
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
								<p>${msg.dateTime}${msg.name}</p>
							</div>
						</th>
						<td><a
							class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-btn-icon-notext ui-icon-mail ui-btn-inline"
							href="#popup_lin3" data-transition="pop" data-rel="popup"
							data-position-to="window"
						></a> <c:if test='${msg.deleteKey != null}'>
								<a
									class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-btn-icon-notext ui-icon-delete ui-btn-inline"
									href="#popup_lin1" data-transition="pop" data-rel="popup"
									data-position-to="window"
								></a>
							</c:if></td>

					</tr>
				</table>

				<c:if test="${msg.reMessages != null}">
					<div data-role="collapsible" data-mini="true">
						<h6>返信</h6>
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
													<p>${reMsg.dateTime}${reMsg.name}</p>
												</div>
											</th>
											<c:if test='${reMsg.deleteKey != null}'>
												<td><a
													class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-btn-icon-notext ui-icon-delete ui-btn-inline"
													href="#popup_lin2" data-transition="pop" data-rel="popup"
													data-position-to="window"
												></a></td>
											</c:if>

										</tr>
									</table>
								</div>

							</article>
							<div class="ui-corner-all" id="popup_lin2" data-role="popup"
								data-theme="a"
							>


								<form>
									<div class="ui-corner-top" data-role="header" data-theme="a">

									</div>
									<div class="ui-corner-bottom ui-content" data-role="content"
										data-theme="a"
									>
										<label class="ui-hidden-accessible" for="u_id">Key:</label> <input
											name="inputDeleteKey" id="u_id" type="text"
											placeholder="delete key" value="${deleteKeyInStorage}"
											data-theme="a"
										>
										<input type="button" onclick="remove(${reMsg.number})" data-theme="a" data-icon="check" value="Delete"></input>
									</div>
								</form>
							</div>
						</c:forEach>

					</div>
				</c:if>




				<div class="ui-corner-all" id="popup_lin1" data-role="popup"
					data-theme="a"
				>


					<form>
						<div class="ui-corner-top" data-role="header" data-theme="a">

						</div>
						<div class="ui-corner-bottom ui-content" data-role="content"
							data-theme="a"
						>
							<label class="ui-hidden-accessible" for="u_id">Key:</label> <input
								name="inputDeleteKey" id="u_id" type="text"
								placeholder="delete key" value="${deleteKeyInStorage}"
								data-theme="a"
							>
							<input type="button" onclick="remove(${msg.number})" data-theme="a" data-icon="check" value="Delete"></input>
						</div>
					</form>
				</div>


				<div class="ui-corner-all" id="popup_lin3" data-role="popup"
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
									<textarea name="reMessage" required="required"
										placeholder="Drop reply message"
									></textarea>
									<input type="button" value="reply"
										onclick="reDrop(${msg.number})"
									>
								</form>
							</div>
						</div>
					</form>
				</div>




				<div class="ui-corner-all" id="popup_lin2" data-role="popup"
					data-theme="a"
				>

					<a
						class="ui-btn ui-corner-all ui-shadow ui-mini ui-btn-g ui-icon-delete ui-btn-icon-right ui-btn-icon-notext"
						href="#" data-rel="back"
					>閉じる</a>
					<form>
						<div class="ui-corner-top" data-role="header" data-theme="a">

							<h4>距離変更</h4>
						</div>
						<div class="ui-corner-bottom ui-content" data-role="content"
							data-theme="a"
						>
							<label class="ui-hidden-accessible" for="u_id">距離:</label> <input
								name="user" id="u_id" type="text" placeholder="○○m" value=""
								data-theme="a"
							>
							<button type="submit" data-theme="a" data-icon="check">取得範囲変更</button>
						</div>
					</form>
				</div>
			</article>
		</c:forEach>
	</div>
</body>
</html>
