
package wad.logger;

import java.util.*;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import wad.logger.dao.*;
import java.time.LocalDate;
import wad.logger.domain.*;

/**
 *
 * @author axido
 */
public class SturdySuccostash {
    
    public static void main(String[] args) throws Exception {
        // connect to database 
        Database database = new Database("jdbc:h2:~/database;DATABASE_TO_UPPER=false"); //;DATABASE_TO_UPPER=false
        CardioDao cardioDao = new CardioDao(database);
        
        get("/", (req, res) -> {
            TreeMap<String, Object> data = new TreeMap<>();
            data.put("cardio", cardioDao.findAll());
            return new ModelAndView(data, "index");
        }, new ThymeleafTemplateEngine());
        
        get("/test", (req, res) -> {
            TreeMap<String, Object> data = new TreeMap<>();
            return new ModelAndView(data, "test");
        }, new ThymeleafTemplateEngine());
        
        // delete a record
        post("/delete/:id", (req, res) -> {
            Integer id = Integer.parseInt(req.params(":id"));
            
            if (id != null) {
                cardioDao.delete(id);
            }
            res.redirect("/");
            return "";
        });
        
        // add a record
        post("/add", (req, res) -> {
            // get stuff from form, update session table and whatever the exercise was
            String sport = req.queryParams("sport");
            //int distance = Integer.parseInt(req.queryParams("distance"));
            int duration = Integer.parseInt(req.queryParams("duration"));
            //String notes = req.queryParams("notes");
            //String today = LocalDate.now().toString();
            
            //database.update("INSERT INTO Session (session_date) VALUES (?)", today);
            // just running and cycling for now
            if (sport.equals("running")) {
                Cardio running = new Cardio(sport, duration);
                cardioDao.create(running);
            } else {
                Cardio cycling = new Cardio(sport, duration);
                cardioDao.create(cycling);
            }
            res.redirect("/");
            return "";
        });
    }
    
}
