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
    private static final String URL = "jdbc:postgresql://localhost:5432/CMP1611- Lidia-Liandra-Matheus-Paulo";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "5533";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // Ajuste o driver conforme seu banco de dados
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do banco de dados não encontrado.", e);
        }
    }
}