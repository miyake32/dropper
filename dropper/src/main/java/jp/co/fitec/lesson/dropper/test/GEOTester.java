package jp.co.fitec.lesson.dropper.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.fitec.lesson.dropper.integration.geo.RGCFactory;
import jp.co.fitec.lesson.dropper.integration.geo.ReverseGeoCoder;

/**
 * Servlet implementation class GEOTester
 */
@WebServlet("/Test/GEOTester")
public class GEOTester extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// common
		ReverseGeoCoder rgc = RGCFactory.createRGC();
		String[][] cityList = { { "モンゴル", "ウランバートル", "47.56", "106.55" },
				{ "ラオス", "ビエンチャン", "17.58", "102.37" },
				{ "香港", "香港", "22.18", "114.10" },
				{ "台湾", "タカオ", "22.37", "120.17" },
				{ "台湾", "台北", "25.02", "121.31" },
				{ "大韓民国", "クワンジュ", "35.09", "126.54" },
				{ "大韓民国", "ソウル", "37.30", "127.00" },
				{ "大韓民国", "テグ", "35.52", "128.36" },
				{ "中国", "パイチョン", "45.37", "122.50" },
				{ "中国", "北京", "39.56", "116.17" },
				{ "中国", "チャンチュン", "43.53", "125.19" },
				{ "中国", "ターリエン", "38.55", "121.35" },
				{ "中国", "コワンチョウ", "23.08", "113.14" },
				{ "中国", "ハンチョウ", "30.15", "120.10" },
				{ "中国", "ハルビン", "45.44", "126.37" },
				{ "中国", "ホーカン", "47.20", "130.17" },
				{ "中国", "チーシー", "45.17", "130.58" },
				{ "中国", "ナンチャン", "28.40", "115.53" },
				{ "中国", "ナンチン", "32.03", "118.46" },
				{ "中国", "チンタオ", "36.05", "120.21" },
				{ "中国", "チチハル", "47.21", "123.58" },
				{ "中国", "上海", "31.10", "121.26" },
				{ "中国", "スワトウ", "23.21", "116.40" },
				{ "中国", "シェンヤン", "41.48", "123.24" },
				{ "中国", "ティエンチン", "39.09", "117.12" },
				{ "中国", "ウーハン", "30.34", "114.17" },
				{ "中国", "シューチョウ", "34.16", "117.11" },
				{ "朝鮮民主主義人民共和国", "キムチェク", "40.41", "129.10" },
				{ "朝鮮民主主義人民共和国", "ピョンヤン", "39.01", "125.45" },
				{ "日本", "青森", "40.50", "140.45" },
				{ "日本", "福岡", "33.35", "130.23" },
				{ "日本", "浜松", "34.43", "137.44" },
				{ "日本", "広島", "34.23", "132.27" },
				{ "日本", "鹿児島", "31.35", "130.33" },
				{ "日本", "金沢", "36.34", "136.39" },
				{ "日本", "高知", "33.33", "133.32" },
				{ "日本", "熊本", "32.47", "130.43" },
				{ "日本", "釧路", "42.58", "144.23" },
				{ "日本", "松江", "35.28", "133.03" },
				{ "日本", "那覇", "26.12", "127.42" },
				{ "日本", "新潟", "37.55", "139.03" },
				{ "日本", "大阪", "34.41", "135.31" },
				{ "日本", "札幌", "43.03", "141.20" },
				{ "日本", "仙台", "38.16", "140.54" },
				{ "日本", "東京", "35.41", "139.46" } };
		PrintWriter out = response.getWriter();

		// getAddressElementのテスト => OK!
//		for (String[] city : cityList) {
//			for (String str : city) {
//				System.out.print(str + " ");
//			}
//			System.out.println(rgc.getAddressElementList(new Double(city[2]),
//					new Double(city[3])));
//		}
		
		//getFormattedAddressのテスト => OK!
		for (String[] city :cityList){
			for (String str : city) {
				System.out.print(str + " ");
			}
			System.out.println(rgc.getFormattedAddress(new Double(city[2]),
					new Double(city[3])));
		}
	}
}
