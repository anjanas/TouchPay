package com.visa.hackathon;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TouchPayServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
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
