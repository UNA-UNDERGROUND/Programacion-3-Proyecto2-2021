package cr.ac.una.model.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cr.ac.una.controller.DBConectionController;

public class DAO {
    protected Connection getConnection(){
        return DBConectionController.getInstance().getConnection();
    }
    protected boolean ejecutarUpdate(String sql){
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.clearParameters();
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    protected ResultSet ejecutarConsulta(String sql){
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.clearParameters();
            return stm.executeQuery();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
