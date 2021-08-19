import modules.EndagereredSpecies;
import modules.Sighten;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.security.Timestamp;
import java.util.Map;
import java.util.HashMap;
import java.util.*;

import static spark.Spark.*;
public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args){
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        get("/", (request, response) ->{
            Map<String, Object > model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.html");
        }, new HandlebarsTemplateEngine());
        get("/sighting",(request, response) -> {
            Map<String, Object > model = new HashMap<String, Object>();
            return new ModelAndView(model, "preSighting.html");
        }, new HandlebarsTemplateEngine());

      get("/sighten/main",(request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        List<EndagereredSpecies>all= EndagereredSpecies.getAll();
        List<Sighten>getInfo= Sighten.getAll();
        ArrayList<String>name=new ArrayList<>();
        ArrayList<String> health= new ArrayList<>();
        ArrayList<String>age = new ArrayList<>();
        ArrayList<String>ranger = new ArrayList<>();
        ArrayList<String>location = new ArrayList<>();
        ArrayList<Timestamp> time = new ArrayList<>();
        for(EndagereredSpecies animal: all){
            name.add(animal.getName());
            health.add(animal.getHEALTH());
            age.add(animal.getAGE());
        }
        for(Sighten sight: getInfo){
            ranger.add(sight.getRanger());
            location.add(sight.getLOCATION());
            time.add(sight.getLastSeen());
        }
      model.put("all",all);
        model.put("name",name);

        model.put("health", health);
        model.put("age", age);
        model.put("ranger", ranger);
        model.put("location", location);
        model.put("time", time);

        

//
        return (new ModelAndView(model,"sighting.hbs"));
      }, new HandlebarsTemplateEngine());
 post("/sighten/main",(request, response) -> {
                    Map<String, Object> model = new HashMap<String, Object>();
                    String name = request.queryParams("name");
                    String health = request.queryParams("health");
                    System.out.println(health);
                    String location = request.queryParams("locationEndager");
                    String ranger = request.queryParams("ranger");
                    String age = request.queryParams("age");
                    EndagereredSpecies newAnimal = new EndagereredSpecies(name, health, age);
                    newAnimal.save();
                    Sighten sighten= new Sighten(ranger,location,newAnimal.getId());
                    sighten.save();
                    model.put("newAnimal", newAnimal);
            return new ModelAndView(model,"success.hbs");
//
        },new HandlebarsTemplateEngine());
    }
}
