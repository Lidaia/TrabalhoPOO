/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conection;

/**
 *
 * @author lidia
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConection {
    private static final String URL = "jdbc:postgresql://localhost:5432/CMP1611-Lidia-Liandra-Matheus-Paulo";
    private static final String USUARIO = "lidia";
    private static final String SENHA = "5533";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}