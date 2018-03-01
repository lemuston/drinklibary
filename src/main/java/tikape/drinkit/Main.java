package tikape.drinkit;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.drinkit.domain.RaakaAine;
import tikape.drinkit.dao.RaakaAineDao;
import tikape.drinkit.database.Database;
import tikape.drinkit.domain.Annos;
import tikape.drinkit.dao.AnnosDao;
import tikape.drinkit.domain.AnnosRaakaAine;
import tikape.drinkit.dao.AnnosRaakaAineDao;



public class Main {

    public static void main(String[] args) throws Exception {
        
        if (System.getenv("PORT") != null) {
    Spark.port(Integer.valueOf(System.getenv("PORT")));
}
        
        
        Database database = new Database("jdbc:sqlite:drinkit.db");
        RaakaAineDao raakaaineet = new RaakaAineDao(database);
        AnnosDao annokset = new AnnosDao(database);
        AnnosRaakaAineDao annosraakaaineet = new AnnosRaakaAineDao(database); 
        
       

        

       Spark.get("/raakaaineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("raakaaineet", raakaaineet.findAll());

            return new ModelAndView(map, "raakaaineet");
        }, new ThymeleafTemplateEngine()); 

        Spark.post("/raakaaineet", (req, res) -> {
            RaakaAine raakaaine = new RaakaAine(-1, req.queryParams("nimi"));
            raakaaineet.saveOrUpdate(raakaaine);

            res.redirect("/raakaaineet");
            return "";
        });
        
        
        
        Spark.get("/drinkit", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("drinkit", annokset.findAll());

            return new ModelAndView(map, "drinkit");
        }, new ThymeleafTemplateEngine());
        
        Spark.get("/luodrinkki", (req, res) -> {
        HashMap map = new HashMap<>();
        map.put("annokset", annokset.findAll()); 
        map.put("raakaaineet", raakaaineet.findAll());
     /*   map.put("annosraakaaineet", annosraakaaineet.findAll()); */

            return new ModelAndView(map, "luodrinkki");
        }, new ThymeleafTemplateEngine());
        
        
        
        
        
        Spark.post("/luodrinkki", (req, res) -> {
            Annos annos = new Annos(-1, req.queryParams("nimi"));
            annokset.saveOrUpdate(annos);
            
           

            res.redirect("/luodrinkki");
            return "";
        }); 
        
        Spark.post("/luodrinkki", (req, res) -> {
            
            
            AnnosRaakaAine annosraakaaine = new AnnosRaakaAine(-1, 0, 0, 0, 0, req.queryParams("ohje"));
            annosraakaaineet.saveOrUpdate(annosraakaaine); 

            res.redirect("/luodrinkki");
            return "";
        }); 
    }
}