package tikape.drinkit.dao;

import tikape.drinkit.domain.RaakaAine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.drinkit.database.Database;

public class RaakaAineDao implements Dao<RaakaAine, Integer> {

    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {
        List<RaakaAine> drinkit = new ArrayList<>();

        try (Connection conn = database.getConnection();
            ResultSet result = conn.prepareStatement("SELECT rowid, nimi FROM RaakaAine").executeQuery()) {

            while (result.next()) {
                drinkit.add(new RaakaAine(result.getInt("rowid"), result.getString("nimi")));
            }
        }

        return drinkit;
    }

    @Override
    public RaakaAine saveOrUpdate(RaakaAine object) throws SQLException {
        
        RaakaAine byNimi = findByNimi(object.getNimi());

        if (byNimi != null) {
            return byNimi;
        } 

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO RAAKAAINE (nimi) VALUES (?)");
            stmt.setString(1, object.getNimi());
            stmt.executeUpdate();
        }

        return findByNimi(object.getNimi());
    }

    private RaakaAine findByNimi(String nimi) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT rowid, nimi FROM RaakaAine WHERE nimi = ?");
            stmt.setString(1, nimi);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new RaakaAine(result.getInt("rowid"), result.getString("nimi"));
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

    