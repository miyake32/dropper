/**
 * 
 */
// 現在地情報を取得してRGCclientに投げる
//--------------------------------------
function doGeoCode(position) {
	// 緯度と経度の取得
	var lat = position.coords.latitude;
	var lon = position.coords.longitude;

	$.get("/dropper/geocode.do", {
		lat : lat,
		lon : lon
	})
}
var intId;
var success;
function successGetCurPosi(position) {
	success = true;
	clearInterval(intId)
	doGeoCode(position);
	sessionStorage.lastGeo = new Date().getTime();
	sessionStorage.isReloaded = 0;
	location.assign("/dropper/index.jsp");
}

function getCurrentPosition() {
	if (navigator.geolocation) {
		success = false;

		intId = setInterval(navigator.geolocation.getCurrentPosition(
				successGetCurPosi, null, {
					enableHighAccuracy : true,
					maximumAge : 1,
					timeout : 5000
				}), 5000);

	} else {
		window.alert("位置情報が取得できませんでした")
	}
}
//--------------------------------------


//MessageRitrieverを利用する
//--------------------------------------
function doRetrieve() {
	if (!parseInt(sessionStorage.isReloaded)) {
		$.get("/dropper/retrieve.do", {
			dist : document.getElementsByName("dist").item(0).value
		});
		sessionStorage.isReloaded = 1;
		location.assign("/dropper/index.jsp");
	} else {
		sessionStorage.isReloaded = 0;
	}
	alert("retrieve");
}
//--------------------------------------

// 読み込み直後に実行する
//--------------------------------------
function initialLoad() {
	if ((!document.getElementsByName("lat").item(0).value || !document
			.getElementsByName("lon").item(0).value)
			|| (new Date().getTime() - sessionStorage.lastGeo > 30000)) {
		getCurrentPosition();
	} else {
		doRetrieve();
	}
}
//--------------------------------------


// Dropボタンのclickで実行。MessageRegisterを利用
//--------------------------------------
function drop() {
	var message = document.getElementsByName("message").item(0).value;
	
	$.get("/dropper/register.do", {
		message : message,
	});
	location.assign("/dropper/index.jsp");
	
	alert("drop");
}
//--------------------------------------


//返信のDropボタンのclickで実行。ReMessageRegisterを利用
//--------------------------------------

function reDrop(replyTo) {
	var reMessage = document.getElementsByName("reMessage").item(0).value;
	$.get("/dropper/reply.do", {
		message : reMessage,
		replyTo : replyTo
	});
	location.assign("/dropper/index.jsp");
	
	alert("reDrop");
}
//--------------------------------------

// Deleteボタンのclickで実行。MessageRemoverを利用
//--------------------------------------
