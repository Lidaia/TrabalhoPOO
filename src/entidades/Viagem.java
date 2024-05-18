/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author lidia
 */
import java.sql.Date;

public class Viagem {
    private long cpfPassViag;
    private long cpfMotViag;
    private String placaVeicViag;
    private String localOrigViag;
    private String localDestViag;
    private Date dtHoraInicio;
    private Date dtHoraFim;
    private int qtdePass;
    private String formaPagto;
    private double valorPagto;
    private char cancelamMot;
    private char cancelamPass;

    // Getters e Setters
    public long getCpfPassViag() {
        return cpfPassViag;
    }

    public void setCpfPassViag(long cpfPassViag) {
        this.cpfPassViag = cpfPassViag;
    }

    public long getCpfMotViag() {
        return cpfMotViag;
    }

    public void setCpfMotViag(long cpfMotViag) {
        this.cpfMotViag = cpfMotViag;
    }

    public String getPlacaVeicViag() {
        return placaVeicViag;
    }

    public void setPlacaVeicViag(String placaVeicViag) {
        this.placaVeicViag = placaVeicViag;
    }

    public String getLocalOrigViag() {
        return localOrigViag;
    }

    public void setLocalOrigViag(String localOrigViag) {
        this.localOrigViag = localOrigViag;
    }

    public String getLocalDestViag() {
        return localDestViag;
    }

    public void setLocalDestViag(String localDestViag) {
        this.localDestViag = localDestViag;
    }

    public Date getDtHoraInicio() {
        return dtHoraInicio;
    }

    public void setDtHoraInicio(Date dtHoraInicio) {
        this.dtHoraInicio = dtHoraInicio;
    }

    public Date getDtHoraFim() {
        return dtHoraFim;
    }

    public void setDtHoraFim(Date dtHoraFim) {
        this.dtHoraFim = dtHoraFim;
    }

    public int getQtdePass() {
        return qtdePass;
    }

    public void setQtdePass(int qtdePass) {
        this.qtdePass = qtdePass;
    }

    public String getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(String formaPagto) {
        this.formaPagto = formaPagto;
    }

    public double getValorPagto() {
        return valorPagto;
    }

    public void setValorPagto(double valorPagto) {
        this.valorPagto = valorPagto;
    }

    public char getCancelamMot() {
        return cancelamMot;
    }

    public void setCancelamMot(char cancelamMot) {
        this.cancelamMot = cancelamMot;
    }

    public char getCancelamPass() {
        return cancelamPass;
    }

    public void setCancelamPass(char cancelamPass) {
        this.cancelamPass = cancelamPass;
    }
}