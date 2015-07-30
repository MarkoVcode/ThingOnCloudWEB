package com.thingoncloud.web.run;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.mustache.MustacheTemplateEngine;

public class WebApplication implements SparkApplication {

	@Override
	public void init() {
		
		staticFileLocation("public");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Sam");
		get("/hello1", (rq, rs) -> new ModelAndView(map, "another/helloqq.mustache"), new MustacheTemplateEngine());
		

		// hello.mustache file is in resources/templates directory
		get("/hello", (rq, rs) -> new ModelAndView(map, "hello.mustache"), new MustacheTemplateEngine());
		get("/error404", (rq, rs) -> new ModelAndView(map, "errors/error404.mustache"), new MustacheTemplateEngine());
		get("/error500", (rq, rs) -> new ModelAndView(map, "errors/error500.mustache"), new MustacheTemplateEngine());
		get("/hello2", (req, res) -> "bla, bla");	
		get("/login", (rq, rs) -> new ModelAndView(map, "user/standalone_login_register.mustache"), new MustacheTemplateEngine());
		get("/myText/:id", (request, response) -> {
            return request.params(":id");
        });		
        
     //   get("*", (rq, rs) -> new ModelAndView(map, "errors/error404.mustache"), new MustacheTemplateEngine());
              
        exception(Exception.class, (e, request, response) -> {
            response.status(500);
            MustacheTemplateEngine m = new MustacheTemplateEngine();
            ModelAndView mv = new ModelAndView(map, "errors/error500.mustache");         
            response.body(m.render(mv));
        });
       
		// get("/hello", (req, res) -> "Hello World");
		// http://mustache.github.io/mustache.5.html
        // https://github.com/perwendel/spark
		
	}

	public static void main(String[] args) {
		WebApplication wa = new WebApplication();
		wa.init();
	}
}
