package entidades;

public class Motorista {
    private long cpfMotorista;
    private String cnh;
    private int bancoMot;
    private int agenciaMot;
    private int contaMot;

    // Getters e Setters
    public long getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(long cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public int getBancoMot() {
        return bancoMot;
    }

    public void setBancoMot(int bancoMot) {
        this.bancoMot = bancoMot;
    }

    public int getAgenciaMot() {
        return agenciaMot;
    }

    public void setAgenciaMot(int agenciaMot) {
        this.agenciaMot = agenciaMot;
    }

    public int getContaMot() {
        return contaMot;
    }

    public void setContaMot(int contaMot) {
        this.contaMot = contaMot;
    }
}