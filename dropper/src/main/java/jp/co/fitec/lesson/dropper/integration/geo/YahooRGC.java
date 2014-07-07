package jp.co.fitec.lesson.dropper.integration.geo;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import javax.json.stream.JsonParser.Event;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class YahooRGC implements ReverseGeoCoder {

	@Override
	public List<String> getAddressElementList(double lat, double lon) {
		List<String> addressElementList = new ArrayList<>();

		// Web Services Clientの作成
		WebTarget webTarget = ClientBuilder
				.newClient()
				.target("http://reverse.search.olp.yahooapis.jp")
				.path("OpenLocalPlatform/V1/reverseGeoCoder")
				.queryParam("lat", lat)
				// パラメーター「緯度」
				.queryParam("lon", lon)
				// パラメーター「経度」
				.queryParam("appid",
						"dj0zaiZpPW9lUmk3RWRKVGpWYSZzPWNvbnN1bWVyc2VjcmV0Jng9MDM-") // パラメーター「アプリケーションID」
				.queryParam("output", "json"); // XML形式で取得する場合には「xml」（デフォルト）を指定

		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_JSON_TYPE);
		invocationBuilder.header("some-header", "true");

		Response response = invocationBuilder.get(); // リクエストの送信とレスポンスの取得
		String json = response.readEntity(String.class); // JSONデータを文字列として取り出し

		// JSON APIによるJSON Documentの処理
		JsonParserFactory factory = Json.createParserFactory(null);
		JsonParser parser = factory.createParser(new StringReader(json));

		boolean isAddressElement = false;
		boolean isAddressElementName = false;

		while (parser.hasNext()) {

			Event event = parser.next();

			switch (event) {

			case KEY_NAME: {
				String keyName = parser.getString();
				if (isAddressElement && keyName.equals("Name")) {
					isAddressElementName = true;
					break;
				}
				if (isAddressElement && keyName.equals("Geometry")) {
					isAddressElement = false;
				}
				if (keyName.equals("AddressElement")) {
					isAddressElement = true;
					break;
				}
				break;
			}

			case VALUE_STRING: {
				if (isAddressElementName) {
					String name = parser.getString();
					if (name.matches("\\S+"))
						addressElementList.add(name);
					isAddressElementName = false;
				}
				break;
			}

			default:
				break;
			}
		}
		return addressElementList;
	}

	@Override
	public String getFormattedAddress(double lat, double lon) {
		List<String> addrElemList = getAddressElementList(lat, lon);
		int numOfElem = addrElemList.size();

		String formattedAddr = "";

		if (numOfElem > 2) {
			formattedAddr = addrElemList.get(2) + "（" + addrElemList.get(0)
					+ addrElemList.get(1) + "）";
		}
		if (numOfElem == 2){
		
			formattedAddr = addrElemList.get(0) + addrElemList.get(1);
		}
		if (numOfElem == 1) {
			formattedAddr = addrElemList.get(0);
		}
		
		return formattedAddr;

	}

	// for test
//	public static void main(String[] args) {
//		ReverseGeoCoder rgc = new YahooRGC();
//		List list = rgc.getAddressElementList(35.443708, 139.638026);
//		System.out.println(list);
//		System.out.println(rgc.getFormattedAddress(35.443708, 139.638026));
//	}
}
