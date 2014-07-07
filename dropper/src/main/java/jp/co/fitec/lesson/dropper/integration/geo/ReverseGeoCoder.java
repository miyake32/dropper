package jp.co.fitec.lesson.dropper.integration.geo;

import java.util.List;


// 緯度、経度の情報から住所を取得するためのインターフェース
public interface ReverseGeoCoder {	
	/**
	 * <p>raw dataに近い形で住所を取得するためのメソッド</p>
	 * @param lat　緯度
	 * @param lon　経度
	 * @return　都道府県、市区町村 など、住所の要素を順番に格納したリストを返す
	 * 			住所が取得できなかった場合は空のリストを返す
	 */
	public List<String> getAddressElementList(double lat, double lon);
	
	
	
	/**
	 * <p>特定の形式のStringで住所を取得するためのメソッド</p>
	 * @param lat 緯度
	 * @param lon 経度
	 * @return String
	 * 			住所が取得できなかった場合は""を返す
	 */
	public String getFormattedAddress(double lat, double lon);
}
