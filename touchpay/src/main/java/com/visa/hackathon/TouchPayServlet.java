package com.visa.hackathon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TouchPayServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JSONParser parser = new JSONParser();
		try {
			java.util.logging.Logger.getLogger("touchpay").log(Level.INFO, "entering doPost");
			JSONObject jsonRequest = (JSONObject) parser.parse(new InputStreamReader(req.getInputStream()));
			Map<String, String> requestMap = getRequestMap(jsonRequest);
			
			VDInvoker vInvoke = new VDInvoker(requestMap);

			// fill other request details
			// convert map to json
			Map<String, String> map = vInvoke.invoke();

			// build json for UI
			JSONObject clientResp = prepareClientResponse(map);
			resp.setContentType("application/json");
			resp.getWriter().println(clientResp.toJSONString());
			java.util.logging.Logger.getLogger("touchpay").log(Level.INFO, "exiting doPost");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JSONObject prepareClientResponse(Map<String, String> map) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			obj.put(entry.getKey(), entry.getValue());
		}
		return obj;
	}

	String[] requestFields = new String[] { "sender_email", "sender_data",
			"amount", "recipient_email" };


	private Map<String, String> getRequestMap(JSONObject jsonRequest) {
		Map<String, String> map = new HashMap<String, String>();
		for (String oneField : requestFields) {
			map.put(oneField, (String) jsonRequest.get(oneField));
		}
		return map;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");

		JSONObject obj = new JSONObject();
		obj.put("name", "Satish Joshi");
		obj.put("age", new Integer(100));

		JSONArray list = new JSONArray();
		list.add("msg 1");
		list.add("msg 2");
		list.add("msg 3");

		obj.put("messages", list);

		resp.getWriter().println(obj.toJSONString());
	}
}
