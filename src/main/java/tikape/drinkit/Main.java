package tikape.drinkit;



import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.drinkit.domain.RaakaAine;
import tikape.drinkit.dao.RaakaAineDao;
import tikape.drinkit.database.Database;

public class Main {

    public static void main(String[] args) throws Exception {
        
        
        Database database = new Database("jdbc:sqlite:drinkit.db");
        RaakaAineDao drinkit = new RaakaAineDao(database);

        Spark.get("/drinkit", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("drinkit", drinkit.findAll());

            return new ModelAndView(map, "drinkit");
        }, new ThymeleafTemplateEngine());

        Spark.post("/drinkit", (req, res) -> {
            RaakaAine RaakaAine = new RaakaAine(-1, req.queryParams("nimi"));
            drinkit.saveOrUpdate(RaakaAine);

            res.redirect("/drinkit");
            return "";
        });
    }
}