/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author lidia
 */
import entidades.Proprietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conection.BDConection;

public class ProprietarioDAO {
    private Connection conexao;

    public ProprietarioDAO() {
        try {
            conexao = BDConection.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um novo proprietário
    public void adicionarProprietario(Proprietario proprietario) {
        String sql = "INSERT INTO PROPRIETARIOS (Cpf_prop, CNH_prop, Banco_prop, Agencia_prop, Conta_prop) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, proprietario.getCpfProp());
            stmt.setString(2, proprietario.getCnhProp());
            stmt.setInt(3, proprietario.getBancoProp());
            stmt.setInt(4, proprietario.getAgenciaProp());
            stmt.setInt(5, proprietario.getContaProp());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um proprietário existente
    public void atualizarProprietario(Proprietario proprietario) {
        String sql = "UPDATE PROPRIETARIOS SET CNH_prop=?, Banco_prop=?, Agencia_prop=?, Conta_prop=? WHERE Cpf_prop=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, proprietario.getCnhProp());
            stmt.setInt(2, proprietario.getBancoProp());
            stmt.setInt(3, proprietario.getAgenciaProp());
            stmt.setInt(4, proprietario.getContaProp());
            stmt.setLong(5, proprietario.getCpfProp());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um proprietário existente
    public void excluirProprietario(long cpfProp) {
        String sql = "DELETE FROM PROPRIETARIOS WHERE Cpf_prop=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, cpfProp);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar um proprietário pelo número do CPF
    public Proprietario recuperarProprietario(long cpfProp) {
        Proprietario proprietario = null;
        String sql = "SELECT * FROM PROPRIETARIOS WHERE Cpf_prop=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, cpfProp);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                proprietario = new Proprietario();
                proprietario.setCpfProp(rs.getLong("Cpf_prop"));
                proprietario.setCnhProp(rs.getString("CNH_prop"));
                proprietario.setBancoProp(rs.getInt("Banco_prop"));
                proprietario.setAgenciaProp(rs.getInt("Agencia_prop"));
                proprietario.setContaProp(rs.getInt("Conta_prop"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proprietario;
    }
}