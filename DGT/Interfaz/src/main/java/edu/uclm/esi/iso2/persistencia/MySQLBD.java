package edu.uclm.esi.iso2.persistencia;

import java.sql.*;
import javax.swing.JOptionPane;

public class MySQLBD {

    private Connection connection;

    public Connection getConexion() {
        return connection;
    }

    public void setConexion(Connection conexion) {
        this.connection = conexion;
    }

    public MySQLBD conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String BaseDeDatos = "jdbc:mysql://localhost/multas?user=root";
            setConexion(DriverManager.getConnection(BaseDeDatos));
           
            if (connection != null) {
                System.out.println("Conexión hecha");
            } else {
                System.out.println("Conexion fallida");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public ResultSet consulta(String sql) {
        ResultSet resultado;
        try {
            Statement statement = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return resultado;
    }

    public boolean ejecutar(String sql) {
        try {
            Statement statement = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}