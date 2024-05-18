/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author lidia
 */
public class Proprietario {
    private long cpfProp;
    private String cnhProp;
    private int bancoProp;
    private int agenciaProp;
    private int contaProp;

    // Getters e Setters
    public long getCpfProp() {
        return cpfProp;
    }

    public void setCpfProp(long cpfProp) {
        this.cpfProp = cpfProp;
    }

    public String getCnhProp() {
        return cnhProp;
    }

    public void setCnhProp(String cnhProp) {
        this.cnhProp = cnhProp;
    }

    public int getBancoProp() {
        return bancoProp;
    }

    public void setBancoProp(int bancoProp) {
        this.bancoProp = bancoProp;
    }

    public int getAgenciaProp() {
        return agenciaProp;
    }

    public void setAgenciaProp(int agenciaProp) {
        this.agenciaProp = agenciaProp;
    }

    public int getContaProp() {
        return contaProp;
    }

    public void setContaProp(int contaProp) {
        this.contaProp = contaProp;
    }
}