package com.example;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerREST extends HttpServlet {
	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("lastname") != null || request.getParameter("firstname") != null) {
			if (!request.getParameter("lastname").equals("") && !request.getParameter("firstname").equals("")) {
				String lastname = request.getParameter("lastname");
				String firstname = request.getParameter("firstname");
				firstname = firstname.substring(0, 1);
				return lastname + " " + firstname + ".";
			} else
				return "No POST data lastname or firstname";
		}
		return "Not POST data ";
	}
	@RequestMapping("/getTransport")
	public String getTransport(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("leftBut") != null || request.getParameter("center") != null) {
			if (!request.getParameter("leftBut").equals("") && !request.getParameter("center").equals("")) {
				String leftBut = request.getParameter("leftBut");
				String center = request.getParameter("center");
				Transport t = new Transport(leftBut, center);
				Map<String, ArrayList> JsonMap = t.mapJSON;
				return t.AllString;
			}
		}
		return "error";
	}

}
