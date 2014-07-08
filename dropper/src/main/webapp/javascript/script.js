/**
 * 
 */
function doGeoCode(position) {
// 緯度と経度の取得
var lat = position.coords.latitude;
var lon = position.coords.longitude;

$.get("/dropper/geocode.do", {lat:lat, lon:lon})
}

function getCurrentPosition() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(doGeoCode, 
				doGeoCode({coords:{latitude:35, longitude:135}}), {
			enableHighAccuracy : true,
			maximumAge : 0,
			timeout : 5000
		});
	} else {
		window.alert("位置情報が取得できませんでした")
	}
}

function doRetrieve(){
	$.get("/dropper/retrieve.do");
}

function initialLoad(){
	if (!(document.getElementsByTagName("lat").value || document.getElementsByTagName("lon").value)) {
		getCurrentPosition();
	} else {
		alert(document.getElementsByTagName("lat").value);
		doRetrieve();
	}
}

