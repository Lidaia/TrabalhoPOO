package DAO;

import entidades.Motorista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conection.BDConection;
import java.util.List;
import java.util.ArrayList;

public class MotoristaDAO {
    private Connection conexao;

    public MotoristaDAO() {
        try {
            conexao = BDConection.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um novo motorista
    public void adicionarMotorista(Motorista motorista) {
        String sql = "INSERT INTO MOTORISTAS (Cpf_motorista, CNH, Banco_mot, Agencia_mot, Conta_mot) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, motorista.getCpfMotorista());
            stmt.setString(2, motorista.getCnh());
            stmt.setInt(3, motorista.getBancoMot());
            stmt.setInt(4, motorista.getAgenciaMot());
            stmt.setInt(5, motorista.getContaMot());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um motorista existente
    public void atualizarMotorista(Motorista motorista) {
        String sql = "UPDATE MOTORISTAS SET CNH=?, Banco_mot=?, Agencia_mot=?, Conta_mot=? WHERE Cpf_motorista=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, motorista.getCnh());
            stmt.setInt(2, motorista.getBancoMot());
            stmt.setInt(3, motorista.getAgenciaMot());
            stmt.setInt(4, motorista.getContaMot());
            stmt.setLong(5, motorista.getCpfMotorista());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um motorista existente
    public void excluirMotorista(long cpfMotorista) {
        String sql = "DELETE FROM MOTORISTAS WHERE Cpf_motorista=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, cpfMotorista);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar um motorista pelo número do CPF
    public List<Motorista> recuperarMotoristas() {
    List<Motorista> motoristas = new ArrayList<>();
    String sql = "SELECT * FROM MOTORISTAS";
    try {
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Motorista motorista = new Motorista();
            motorista.setCpfMotorista(rs.getLong("Cpf_motorista"));
            motorista.setCnh(rs.getString("CNH"));
            motorista.setBancoMot(rs.getInt("Banco"));
            motorista.setAgenciaMot(rs.getInt("Agencia"));
            motorista.setContaMot(rs.getInt("Conta"));
            motoristas.add(motorista);
        }
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return motoristas;
}
}