/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author lidia
 */
import entidades.Viagem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conection.BDConection;
import java.util.List;
import java.util.ArrayList;

public class ViagemDAO {
    private Connection conexao;

    public ViagemDAO() {
        try {
            conexao = BDConection.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para adicionar uma nova viagem
    public void adicionarViagem(Viagem viagem) {
        String sql = "INSERT INTO VIAGEM (Cpf_pass_viag, Cpf_mot_viag, Placa_veic_viag, Local_orig_viag, Local_dest_viag, Dt_hora_inicio, Dt_hora_fim, Qtde_pass, Forma_pagto, Valor_pagto, Cancelam_mot, Cancelam_pass) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, viagem.getCpfPassViag());
            stmt.setLong(2, viagem.getCpfMotViag());
            stmt.setString(3, viagem.getPlacaVeicViag());
            stmt.setString(4, viagem.getLocalOrigViag());
            stmt.setString(5, viagem.getLocalDestViag());
            stmt.setDate(6, viagem.getDtHoraInicio());
            stmt.setDate(7, viagem.getDtHoraFim());
            stmt.setInt(8, viagem.getQtdePass());
            stmt.setString(9, viagem.getFormaPagto());
            stmt.setDouble(10, viagem.getValorPagto());
            stmt.setString(11, String.valueOf(viagem.getCancelamMot()));
            stmt.setString(12, String.valueOf(viagem.getCancelamPass()));

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar uma viagem existente
    public void atualizarViagem(Viagem viagem) {
        String sql = "UPDATE VIAGEM SET Local_orig_viag=?, Local_dest_viag=?, Dt_hora_inicio=?, Dt_hora_fim=?, Qtde_pass=?, Forma_pagto=?, Valor_pagto=?, Cancelam_mot=?, Cancelam_pass=? WHERE Cpf_pass_viag=? AND Cpf_mot_viag=? AND Placa_veic_viag=? AND Dt_hora_inicio=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, viagem.getLocalOrigViag());
            stmt.setString(2, viagem.getLocalDestViag());
            stmt.setDate(3, viagem.getDtHoraInicio());
            stmt.setDate(4, viagem.getDtHoraFim());
            stmt.setInt(5, viagem.getQtdePass());
            stmt.setString(6, viagem.getFormaPagto());
            stmt.setDouble(7, viagem.getValorPagto());
            stmt.setString(8, String.valueOf(viagem.getCancelamMot()));
            stmt.setString(9, String.valueOf(viagem.getCancelamPass()));
            stmt.setLong(10, viagem.getCpfPassViag());
            stmt.setLong(11, viagem.getCpfMotViag());
            stmt.setString(12, viagem.getPlacaVeicViag());
            stmt.setDate(13, viagem.getDtHoraInicio());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir uma viagem existente
    public void excluirViagem(long cpfPassViag, long cpfMotViag, String placaVeicViag, Date dtHoraInicio) {
        String sql = "DELETE FROM VIAGEM WHERE Cpf_pass_viag=? AND Cpf_mot_viag=? AND Placa_veic_viag=? AND Dt_hora_inicio=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, cpfPassViag);
            stmt.setLong(2, cpfMotViag);
            stmt.setString(3, placaVeicViag);
            stmt.setDate(4, dtHoraInicio);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar uma viagem pelo número do CPF do passageiro, do motorista, placa do veículo e data/hora de início
    public List<Viagem> listarViagens() {
    List<Viagem> viagens = new ArrayList<>();
    String sql = "SELECT * FROM VIAGEM";

    try {
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Viagem viagem = new Viagem();
            viagem.setCpfPassViag(rs.getLong("Cpf_pass_viag"));
            viagem.setCpfMotViag(rs.getLong("Cpf_mot_viag"));
            viagem.setPlacaVeicViag(rs.getString("Placa_veic_viag"));
            viagem.setLocalOrigViag(rs.getString("Local_orig_viag"));
            viagem.setLocalDestViag(rs.getString("Local_dest_viag"));
            viagem.setDtHoraInicio(rs.getDate("Dt_hora_inicio"));
            viagem.setDtHoraFim(rs.getDate("Dt_hora_fim"));
            viagem.setQtdePass(rs.getInt("Qtde_pass"));
            viagem.setFormaPagto(rs.getString("Forma_pagto"));
            viagem.setValorPagto(rs.getDouble("Valor_pagto"));
            viagem.setCancelamMot(rs.getString("Cancelam_mot").charAt(0));
            viagem.setCancelamPass(rs.getString("Cancelam_pass").charAt(0));

            viagens.add(viagem);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return viagens;
}

}