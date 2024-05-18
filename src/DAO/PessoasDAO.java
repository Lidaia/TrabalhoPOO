/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author lidia
 */
import entidades.Pessoas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conection.BDConection;
public class PessoasDAO {
    private Connection conexao;

    public PessoasDAO() {
        try {
            conexao = BDConection.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar uma nova pessoa
    public void adicionarPessoa(Pessoas pessoa) {
        String sql = "INSERT INTO PESSOAS (Cpf_pessoa, Nome, Endereco, Telefone, Sexo, Email) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, pessoa.getCpf());
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getEndereco());
            stmt.setInt(4, pessoa.getTelefone());
            stmt.setString(5, String.valueOf(pessoa.getSexo()));
            stmt.setString(6, pessoa.getEmail());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar uma pessoa existente
    public void atualizarPessoa(Pessoas pessoa) {
        String sql = "UPDATE PESSOAS SET Nome=?, Endereco=?, Telefone=?, Sexo=?, Email=? WHERE Cpf_pessoa=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEndereco());
            stmt.setInt(3, pessoa.getTelefone());
            stmt.setString(4, String.valueOf(pessoa.getSexo()));
            stmt.setString(5, pessoa.getEmail());
            stmt.setLong(6, pessoa.getCpf());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir uma pessoa existente
    public void excluirPessoa(long cpf) {
        String sql = "DELETE FROM PESSOAS WHERE Cpf_pessoa=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, cpf);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar uma pessoa pelo número do CPF
    public Pessoas recuperarPessoa(long cpf) {
        Pessoas pessoa = null;
        String sql = "SELECT * FROM PESSOAS WHERE Cpf_pessoa=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new Pessoas();
                pessoa.setCpf(rs.getLong("Cpf_pessoa"));
                pessoa.setNome(rs.getString("Nome"));
                pessoa.setEndereco(rs.getString("Endereco"));
                pessoa.setTelefone(rs.getInt("Telefone"));
                pessoa.setSexo(rs.getString("Sexo").charAt(0));
                pessoa.setEmail(rs.getString("Email"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoa;
    }
}