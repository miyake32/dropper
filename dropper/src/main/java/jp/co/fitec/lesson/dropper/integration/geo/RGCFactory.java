package jp.co.fitec.lesson.dropper.integration.geo;

public class RGCFactory {
	public static ReverseGeoCoder createRGC() {
		return new YahooRGC();
	}
}
