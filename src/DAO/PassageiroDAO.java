package DAO;
import entidades.Passageiro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conection.BDConection;

public class PassageiroDAO {
    private Connection conexao;

    public PassageiroDAO() {
        try {
            conexao = BDConection.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um novo passageiro
    public void adicionarPassageiro(Passageiro passageiro) {
        String sql = "INSERT INTO PASSAGEIROS (Cpf_passag, Cartao_cred, Bandeira_cartao, Cidade_orig) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, passageiro.getCpfPassag());
            stmt.setString(2, passageiro.getCartaoCred());
            stmt.setString(3, passageiro.getBandeiraCartao());
            stmt.setString(4, passageiro.getCidadeOrig());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um passageiro existente
    public void atualizarPassageiro(Passageiro passageiro) {
        String sql = "UPDATE PASSAGEIROS SET Cartao_cred=?, Bandeira_cartao=?, Cidade_orig=? WHERE Cpf_passag=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, passageiro.getCartaoCred());
            stmt.setString(2, passageiro.getBandeiraCartao());
            stmt.setString(3, passageiro.getCidadeOrig());
            stmt.setLong(4, passageiro.getCpfPassag());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um passageiro existente
    public void excluirPassageiro(long cpfPassag) {
        String sql = "DELETE FROM PASSAGEIROS WHERE Cpf_passag=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, cpfPassag);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar um passageiro pelo número do CPF
    public Passageiro recuperarPassageiro(long cpfPassag) {
        Passageiro passageiro = null;
        String sql = "SELECT * FROM PASSAGEIROS WHERE Cpf_passag=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, cpfPassag);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                passageiro = new Passageiro();
                passageiro.setCpfPassag(rs.getLong("Cpf_passag"));
                passageiro.setCartaoCred(rs.getString("Cartao_cred"));
                passageiro.setBandeiraCartao(rs.getString("Bandeira_cartao"));
                passageiro.setCidadeOrig(rs.getString("Cidade_orig"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passageiro;
    }
}