package tikape.drinkit.dao;

import tikape.drinkit.domain.RaakaAine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.drinkit.database.Database;
import tikape.drinkit.domain.Annos;

public class AnnosDao implements Dao<Annos, Integer> {

    private Database database;

    public AnnosDao(Database database) {
        this.database = database;
    }

    @Override
    public Annos findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Annos> findAll() throws SQLException {
        List<Annos> annos = new ArrayList<>();

        try (Connection conn = database.getConnection();
            ResultSet result = conn.prepareStatement("SELECT rowid, nimi FROM Annos").executeQuery()) {

            while (result.next()) {
                annos.add(new Annos(result.getInt("rowid"), result.getString("nimi")));
            }
        }

        return annos;
    }
    
    

    @Override
    public Annos saveOrUpdate(Annos object) throws SQLException {
        
        Annos byNimi = findByNimi(object.getNimi());

        if (byNimi != null) {
            return byNimi;
        } 

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO ANNOS (nimi) VALUES (?)");
            stmt.setString(1, object.getNimi());
            stmt.executeUpdate();
        }

        return findByNimi(object.getNimi());
    }

    private Annos findByNimi(String nimi) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT rowid, nimi FROM Annos WHERE nimi = ?");
            stmt.setString(1, nimi);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Annos(result.getInt("rowid"), result.getString("nimi"));
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

    