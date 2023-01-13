
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class koneksi {
    private static Connection conn;
    private static final String HOST = "jdbc:mysql://localhost:3306/db_penyewaan_pc";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
    try {
        conn = DriverManager.getConnection(HOST, USER, PASSWORD);
    } catch (SQLException se) {
        se.printStackTrace();
    }
        return conn;
    }
}