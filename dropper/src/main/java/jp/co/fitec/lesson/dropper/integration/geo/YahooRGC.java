package jp.co.fitec.lesson.dropper.integration.geo;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	/**
	 * <p>
	 * 政令指定都市のSet: {"神奈川県", "横浜市"}のようなString[]を格納する
	 * </p>
	 * <p>
	 * formatted addressの形式を政令指定都市とその他で分けるために使用
	 * </p>
	 */
	private static final Set<String[]> DESIGNATED_CITIES = new HashSet<String[]>();

	/**
	 * <p>
	 * Designated informationsを設定するためのメソッド
	 * </p>
	 */
	private static void setDesignatedInfos() {
		String[][] designatedCities = { { "北海道", "札幌市" }, { "宮城県", "仙台市" },
				{ "埼玉県", "さいたま市" }, { "千葉県", "千葉市" }, { "神奈川県", "横浜市" },
				{ "神奈川県", "川崎市" }, { "神奈川県", "相模原市" }, { "新潟県", "新潟市" },
				{ "静岡県", "静岡市" }, { "静岡県", "浜松市" }, { "愛知県", "名古屋市" },
				{ "京都府", "京都市" }, { "大阪府", "大阪市" }, { "大阪府", "堺市" },
				{ "兵庫県", "神戸市" }, { "岡山県", "岡山市" }, { "広島県", "広島市" },
				{ "福岡県", "北九州市" }, { "福岡県", "福岡市" }, { "熊本県", "熊本市" } };

		for (String[] designatedCity : designatedCities) {
			DESIGNATED_CITIES.add(designatedCity);
		}
	}

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

		while (parser.hasNext()) {

			Event event = parser.next();

			boolean isAddressElement = false;
			boolean isAddressElementName = false;

			switch (event) {

			case KEY_NAME: {
				if (isAddressElement && parser.getString() == "Name") {
					isAddressElementName = true;
					break;
				}
				if (isAddressElement && parser.getString() == "Geometry") {
					isAddressElement = false;
				}
				if (parser.getString() == "AddressElement") {
					isAddressElement = true;
					break;
				}
				break;
			}

			case VALUE_STRING: {
				if (isAddressElementName) {
					addressElementList.add(parser.getString());
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
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// for test
	public static void main(String[] args) {
		ReverseGeoCoder rgc = new YahooRGC();
		List list =rgc.getAddressElementList(35.999792, 136.499662);
		System.out.println(list);
	}

}
