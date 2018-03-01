package AnnosRaakaAine;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.drinkit.domain.RaakaAine;
import tikape.drinkit.dao.RaakaAineDao;
import tikape.drinkit.database.Database;
import tikape.drinkit.domain.Annos;
import tikape.drinkit.dao.AnnosDao;


public class Main {

    public static void main(String[] args) throws Exception {
        
        
        Database database = new Database("jdbc:sqlite:drinkit.db");
        RaakaAineDao raakaaineet = new RaakaAineDao(database);
        AnnosDao annokset = new AnnosDao(database);
        
       

        

       Spark.get("/raakaaineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("raakaaineet", raakaaineet.findAll());

            return new ModelAndView(map, "raakaaineet");
        }, new ThymeleafTemplateEngine()); 

        Spark.post("/raakaaineet", (req, res) -> {
            RaakaAine raakaAine = new RaakaAine(-1, req.queryParams("nimi"));
            raakaaineet.saveOrUpdate(raakaAine);

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

            return new ModelAndView(map, "luodrinkki");
        }, new ThymeleafTemplateEngine());
        
        
        
        
        
        Spark.post("/luodrinkki", (req, res) -> {
            Annos annos = new Annos(-1, req.queryParams("nimi"));
            annokset.saveOrUpdate(annos);

            res.redirect("/luodrinkki");
            return "";
        }); 
    }
}