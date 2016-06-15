
package wad.logger;

import java.sql.*;
import java.util.TreeMap;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import wad.logger.dao.Database;
import java.time.LocalDate;

/**
 *
 * @author axido
 */
public class SturdySuccostash {
    
    public static void main(String[] args) throws Exception {
        //connect to database 
        Database database = new Database("jdbc:h2:./database;DATABASE_TO_UPPER=false");
        
        get("/", (req, res) -> {
            TreeMap<String, Object> data = new TreeMap<>();
            return new ModelAndView(data, "index");
        }, new ThymeleafTemplateEngine());
        
        get("/test", (req, res) -> {
            TreeMap<String, Object> data = new TreeMap<>();
            return new ModelAndView(data, "test");
        }, new ThymeleafTemplateEngine());
        
        post("/add", (req, res) -> {
            // get stuff from form, update session table and whatever the exercise was
            String sport = req.queryParams("sport");
            int distance = Integer.parseInt(req.queryParams("distance"));
            int duration = Integer.parseInt(req.queryParams("duration"));
            String notes = req.queryParams("notes");
            String today = LocalDate.now().toString();
            
            //database.update("INSERT INTO Session (session_date) VALUES (?)", today);
            // just running and cycling for now
            if (sport.equals("running")) {
                database.update("INSERT INTO Running (distance, duration, notes, session_id) "
                        + "VALUES (?, ?, ?, ?)", distance, duration, notes, 1);
            } else {
                database.update("INSERT INTO Cycling (distance, duration, notes, session_id) "
                        + "VALUES (?, ?, ?, ?)", distance, duration, notes, 1);
            }
            res.redirect("/");
            return "";
        });
    }
    
}
