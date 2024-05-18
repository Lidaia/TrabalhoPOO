package DAO;
import entidades.MotoristaVeiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conection.BDConection;

public class MotoristaVeiculoDAO {
    private Connection conexao;

    public MotoristaVeiculoDAO() {
        try {
            conexao = BDConection.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar uma nova relação motorista-veículo
    public void adicionarMotoristaVeiculo(MotoristaVeiculo motoristaVeiculo) {
        String sql = "INSERT INTO MOTORISTA_VEICULO (Cpf_motorista, Placa_veiculo) VALUES (?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, motoristaVeiculo.getCpfMotorista());
            stmt.setString(2, motoristaVeiculo.getPlacaVeiculo());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar uma relação motorista-veículo existente
    public void atualizarMotoristaVeiculo(MotoristaVeiculo motoristaVeiculo) {
        String sql = "UPDATE MOTORISTA_VEICULO SET Placa_veiculo=? WHERE Cpf_motorista=? AND Placa_veiculo=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, motoristaVeiculo.getPlacaVeiculo());
            stmt.setLong(2, motoristaVeiculo.getCpfMotorista());
            stmt.setString(3, motoristaVeiculo.getPlacaVeiculo());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir uma relação motorista-veículo existente
    public void excluirMotoristaVeiculo(long cpfMotorista, String placaVeiculo) {
        String sql = "DELETE FROM MOTORISTA_VEICULO WHERE Cpf_motorista=? AND Placa_veiculo=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, cpfMotorista);
            stmt.setString(2, placaVeiculo);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar uma relação motorista-veículo pelo CPF do motorista e placa do veículo
    public MotoristaVeiculo recuperarMotoristaVeiculo(long cpfMotorista, String placaVeiculo) {
        MotoristaVeiculo motoristaVeiculo = null;
        String sql = "SELECT * FROM MOTORISTA_VEICULO WHERE Cpf_motorista=? AND Placa_veiculo=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, cpfMotorista);
            stmt.setString(2, placaVeiculo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                motoristaVeiculo = new MotoristaVeiculo();
                motoristaVeiculo.setCpfMotorista(rs.getLong("Cpf_motorista"));
                motoristaVeiculo.setPlacaVeiculo(rs.getString("Placa_veiculo"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return motoristaVeiculo;
    }

    // Método para recuperar todas as relações motorista-veículo
    public List<MotoristaVeiculo> listarMotoristaVeiculos() {
        List<MotoristaVeiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM MOTORISTA_VEICULO";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MotoristaVeiculo motoristaVeiculo = new MotoristaVeiculo();
                motoristaVeiculo.setCpfMotorista(rs.getLong("Cpf_motorista"));
                motoristaVeiculo.setPlacaVeiculo(rs.getString("Placa_veiculo"));
                lista.add(motoristaVeiculo);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}