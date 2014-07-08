/**
 * 
 */
var reloaded = false;

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

function successGetCurPosi(position) {
	success = true;
	clearInterval(intId)
	doGeoCode(position);
	location.assign("/dropper/index.jsp");
}

function getCurrentPosition() {
	if (navigator.geolocation) {
		var success = false;

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
}

function initialLoad() {
	if ((!document.getElementsByName("lat").item(0).value || !document
			.getElementsByName("lon").item(0).value)
			|| (new Date().getTime() - sessionStorage.lastGeo > 30000)) {
		getCurrentPosition();
		sessionStorage.lastGeo = new Date().getTime();
		sessionStorage.isReloaded = 0;
	} else {
		doRetrieve();
	}
}

function drop() {
	$.get("/dropper/register.do", {
		message : document.getElementsByName("message").item(0).value,
		name : document.getElementsByName("name").item(0).value,
		deleteKey : document.getElementsByName("deleteKey").item(0).value
	});
	location.assign("/dropper/index.jsp");

}
