/**
 * 
 */
var nameInStorage = localStorage.nameInStorage;
var deleteKeyInStorage = localStorage.deleteKeyInStorage;

function storageParams() {
	if (nameInStorage == null) {
		nameInStorage = "";
	}
	if (deleteKeyInStorage == null) {
		deleteKeyInStorage = "";
	}
}

// 現在地情報を取得してRGCclientに投げる
// --------------------------------------
function doGeoCode(position) {
	alert("geocodedo");
	// 緯度と経度の取得
	sessionStorage.lat = position.coords.latitude + "";
	sessionStorage.lon = position.coords.longitude + "";
	storageParams();
	$.get("/dropper/geocode.do", {
		lat : sessionStorage.lat + "",
		lon : sessionStorage.lon + "",
		nameInStorage : nameInStorage,
		deleteKeyInStorage : deleteKeyInStorage
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
	alert("getCurrentPosition successed");
	location.assign("/dropper/index.jsp");
}

function getCurrentPosition() {
	alert("Get CurrentPosition")
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
// --------------------------------------

// MessageRitrieverを利用する
// --------------------------------------
function doRetrieve() {
	var dist;
	switch (document.distForm.dist.selectedindex) {
	case 0:
		dist = 10;
	case 1:
		dist = 50;
	case 2:
		dist = 100;
	case 3:
		dist = 500;
	default:
		dist = 50;
	}
	storageParams();

	if (!parseInt(sessionStorage.isReloaded)) {
		$.get("/dropper/retrieve.do", {
			dist : dist,
			nameInStorage : nameInStorage,
			deleteKeyInStorage : deleteKeyInStorage
		});
		sessionStorage.isReloaded = 1;
		location.assign("/dropper/index.jsp");
	} else {
		sessionStorage.isReloaded = 0;
	}
	alert("retrieve");
}
// --------------------------------------

// 読み込み直後に実行する
// --------------------------------------
function initialLoad() {
	if ((!sessionStorage.lat || !sessionStorage.lon)
			|| (new Date().getTime() - sessionStorage.lastGeo > 30000)) {
		getCurrentPosition();
	} else {
		doRetrieve();
	}
}
// --------------------------------------

// Dropボタンのclickで実行。MessageRegisterを利用
// --------------------------------------
function drop() {
	var message = document.getElementsByName("message").item(0).value;

	storageParams();
	$.get("/dropper/register.do", {
		message : message,
		nameInStorage : nameInStorage,
		deleteKeyInStorage : deleteKeyInStorage
	});
	location.assign("/dropper/index.jsp");

	alert("drop");
}
// --------------------------------------

// 返信のDropボタンのclickで実行。ReMessageRegisterを利用
// --------------------------------------

function reDrop(replyTo) {
	var reMessage = document.getElementsByName("reMessage").item(0).value;
	storageParams();
	$.get("/dropper/reply.do", {
		message : reMessage,
		replyTo : replyTo,
		nameInStorage : nameInStorage,
		deleteKeyInStorage : deleteKeyInStorage
	});
	location.assign("/dropper/index.jsp");

	alert("reDrop");
}
// --------------------------------------

// Deleteボタンのclickで実行。MessageRemoverを利用
// --------------------------------------
