
package wad.logger;

import java.util.TreeMap;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

/**
 *
 * @author axido
 */
public class SturdySuccostash {

    public static void main(String[] args) throws Exception {
        
        get("/", (req, res) -> {
            TreeMap<String, Object> data = new TreeMap<>();
            return new ModelAndView(data, "index");
        }, new ThymeleafTemplateEngine());
    }
    
}
