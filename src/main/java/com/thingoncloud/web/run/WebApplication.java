package com.thingoncloud.web.run;

import spark.servlet.SparkApplication;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class WebApplication implements SparkApplication {

	@Override
	public void init() {
		
		Map map = new HashMap();
		map.put("name", "Sam");
		get("/hello", (rq, rs) -> new ModelAndView(map, "another/helloqq.mustache"), new MustacheTemplateEngine());

	}

	public static void main(String[] args) {
		
	}
}
