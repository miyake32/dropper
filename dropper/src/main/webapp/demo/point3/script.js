/**
 * 
 */

var nameInStorage = "point3";
var deleteKeyInStorage = "point3";

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
	// 緯度と経度の取得
	$.get("/dropper/geocode.do", {
		lat : position.coords.latitude + "",
		lon : position.coords.longitude + "",
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
	localStorage.lastGeo = new Date().getTime();
	localStorage.isReloaded = 0;
	location.assign("/dropper/demo/point3/index.jsp");
}

function getCurrentPosition() {
	if (navigator.geolocation) {
		success = false;

		successGetCurPosi({coords:{latitude:35.644307 , longitude:139.7015}});

	} else {
		alert("位置情報が取得できませんでした")
	}
}
// --------------------------------------

// MessageRitrieverを利用する
// --------------------------------------
function doRetrieve() {
	var dist = 150;
	// switch (document.distForm.dist.selectedindex) {
	// case 0:
	// dist = 10;
	// case 1:
	// dist = 50;
	// case 2:
	// dist = 100;
	// case 3:
	// dist = 500;
	// default:
	// dist = 150;
	// }
	storageParams();

	if (parseInt(localStorage.isReloaded) < 2) {
		$.get("/dropper/retrieve.do", {
			dist : dist,
			nameInStorage : nameInStorage,
			deleteKeyInStorage : deleteKeyInStorage,
			lat : 35.644307,
			lon: 139.7015
		});
		localStorage.isReloaded = parseInt(localStorage.isReloaded) + 1;

		location.assign("/dropper/demo/point3/index.jsp");
	} else if (parseInt(localStorage.isReloaded < 3)) {
		localStorage.isReloaded = parseInt(localStorage.isReloaded) + 1;
		location.assign("/dropper/demo/point3/index.jsp");
	} else {
		localStorage.isReloaded = 0;
	}
}
// --------------------------------------

// 読み込み直後に実行する
// --------------------------------------
function initialLoad() {
	
		doRetrieve();
}
// --------------------------------------

// Dropボタンのclickで実行。MessageRegisterを利用
// --------------------------------------
function drop() {
	var message = document.getElementsByName("messageBox").item(0).value;

	if (message) {
		storageParams();
		$.get("/dropper/register.do", {
			message : message,
			nameInStorage : nameInStorage,
			deleteKeyInStorage : deleteKeyInStorage,
			lat : 35.644307,
			lon: 139.7015
		});
		location.assign("/dropper/demo/point3/index.jsp");
	}
}

// --------------------------------------

// 返信のDropボタンのclickで実行。ReMessageRegisterを利用
// --------------------------------------

function reDrop(replyTo) {
	var reMessage = document.getElementsByName("reMessage" + replyTo).item(0).value;
		
	if (reMessage) {
		storageParams();
		$.get("/dropper/reply.do", {
			message : reMessage,
			replyTo : replyTo,
			nameInStorage : nameInStorage,
			deleteKeyInStorage : deleteKeyInStorage
		});
		location.assign("/dropper/demo/point3/index.jsp");
	}
}
// --------------------------------------

// Deleteボタンのclickで実行。MessageRemoverを利用
// --------------------------------------
function removeMessage(number) {
	var inputDeleteKey = document.getElementsByName("inputDeleteKey" + number)[0].value;

	if (inputDeleteKey) {
		$.get("/dropper/remove.do", {
			msgNum : number,
			deleteKey : inputDeleteKey
		})
		location.assign("/dropper/demo/point3/index.jsp");
	}
}
// --------------------------------------

// saveのclickで実行
// --------------------------------------
function saveToStorage() {
	var inputName = document.getElementsByName("inputName").item(0).value;
	var inputDeleteKey = document.getElementsByName("inputDeleteKey").item(0).value;

	if (inputName == undefined) {
		inputName = "";
	}
	if (inputDeleteKey == undefined) {
		inputDeleteKey = "";
	}

}
// --------------------------------------
