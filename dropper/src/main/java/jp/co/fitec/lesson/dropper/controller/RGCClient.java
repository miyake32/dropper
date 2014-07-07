package jp.co.fitec.lesson.dropper.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.fitec.lesson.dropper.integration.geo.RGCFactory;
import jp.co.fitec.lesson.dropper.integration.geo.ReverseGeoCoder;

public class RGCClient implements Action {

	@Override
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String latStr = request.getParameter("lat") ;
		String lonStr = request.getParameter("lon");
		
		Double lat = new Double(latStr);
		Double lon = new Double(lonStr);
		
		ReverseGeoCoder rgc = RGCFactory.createRGC();
		String addr = rgc.getFormattedAddress(lat, lon);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("lat", lat);
		session.setAttribute("lon", lon);
		session.setAttribute("addr", addr);
		
		return "/index.jsp";
	}

}
