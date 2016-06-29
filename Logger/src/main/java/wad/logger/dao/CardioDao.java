
package wad.logger.dao;

import java.sql.*;
import java.sql.SQLException;
import java.util.List;
import wad.logger.domain.Cardio;

/**
 *
 * @author axido
 */
public class CardioDao implements Dao <Cardio, Integer> {
    
    private final Database database;
    
    public CardioDao(Database database) {
        this.database = database;
    }

    @Override
    public Cardio create(Cardio t) throws SQLException {
        database.update("INSERT INTO Cardio (name, duration, notes) VALUES (?, ?, ?)", 
                t.getName(), t.getDuration(), t.getNotes());
        return findOne(t.getId());
    }

    @Override
    public Cardio findOne(Integer key) throws SQLException {
        //return (Cardio) database.queryAndCollect("SELECT * FROM Cardio WHERE id = ?", rs -> new Cardio(rs.getInt("id"), rs.getString("name"), rs.getInt("duration")), key).get(0);
        
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Cardio WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        //Integer id = Integer.parseInt(rs.getString("id"));
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Integer duration = rs.getInt("duration");
        //Integer distance = rs.getInt("distance");
        //String notes = rs.getString("notes");
        Cardio p;
        if (rs.getString("notes") != null) {
            String notes = rs.getString("notes");
            p = new Cardio(id, name, duration, notes);
        } else {
            p = new Cardio(id, name, duration);
        }

        

        rs.close();
        stmt.close();
        connection.close();

        return p;
    }

    @Override
    public List<Cardio> findAll() throws SQLException {
        List<Cardio> exercises = database.queryAndCollect("SELECT * FROM Cardio", rs -> new Cardio(rs.getInt("id"), rs.getString("name"), rs.getInt("duration"), rs.getString("notes")));
        
        return exercises;        
    }

    @Override
    public void update(Integer key, Cardio t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        database.update("DELETE FROM Cardio WHERE id = ?", key);
    }
    
}
