/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author lidia
 */
import entidades.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conection.BDConection;


public class VeiculoDAO {
    private Connection conexao;

    public VeiculoDAO() {
        try {
            conexao = BDConection.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar um novo veículo
    public void adicionarVeiculo(Veiculo veiculo) {
        String sql = "INSERT INTO VEICULO (placa, marca, modelo, ano_fabric, capacidade_pass, cor, tipo_combust, potencia_motor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setInt(4, veiculo.getAnoFabric());
            stmt.setInt(5, veiculo.getCapacidadePass());
            stmt.setString(6, veiculo.getCor());
            stmt.setString(7, veiculo.getTipoCombust());
            stmt.setInt(8, veiculo.getPotenciaMotor());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um veículo existente
    public void atualizarVeiculo(Veiculo veiculo) {
        String sql = "UPDATE VEICULO SET marca=?, modelo=?, ano_fabric=?, capacidade_pass=?, cor=?, tipo_combust=?, potencia_motor=? WHERE placa=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAnoFabric());
            stmt.setInt(4, veiculo.getCapacidadePass());
            stmt.setString(5, veiculo.getCor());
            stmt.setString(6, veiculo.getTipoCombust());
            stmt.setInt(7, veiculo.getPotenciaMotor());
            stmt.setString(8, veiculo.getPlaca());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um veículo existente
    public void excluirVeiculo(String placa) {
        String sql = "DELETE FROM VEICULO WHERE placa=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, placa);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar um veículo pelo número da placa
    public Veiculo recuperarVeiculo(String placa) {
        Veiculo veiculo = null;
        String sql = "SELECT * FROM VEICULO WHERE placa=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAnoFabric(rs.getInt("ano_fabric"));
                veiculo.setCapacidadePass(rs.getInt("capacidade_pass"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setTipoCombust(rs.getString("tipo_combust"));
                veiculo.setPotenciaMotor(rs.getInt("potencia_motor"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculo;
    }
}