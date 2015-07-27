
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class FirstPage {

	public static void main(String[] args) {

		Map map = new HashMap();
		map.put("name", "Sam");
		
		staticFileLocation("public");
		// hello.mustache file is in resources/templates directory
		get("/hello", (rq, rs) -> new ModelAndView(map, "hello.mustache"), new MustacheTemplateEngine());
		get("/error404", (rq, rs) -> new ModelAndView(map, "errors/error404.mustache"), new MustacheTemplateEngine());
		get("/error500", (rq, rs) -> new ModelAndView(map, "errors/error500.mustache"), new MustacheTemplateEngine());
		get("/hello2", (req, res) -> "bla, bla");
		
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
}
