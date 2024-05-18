package entidades;

public class Passageiro {
    private long cpfPassag;
    private String cartaoCred;
    private String bandeiraCartao;
    private String cidadeOrig;

    // Getters e Setters
    public long getCpfPassag() {
        return cpfPassag;
    }

    public void setCpfPassag(long cpfPassag) {
        this.cpfPassag = cpfPassag;
    }

    public String getCartaoCred() {
        return cartaoCred;
    }

    public void setCartaoCred(String cartaoCred) {
        this.cartaoCred = cartaoCred;
    }

    public String getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(String bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

    public String getCidadeOrig() {
        return cidadeOrig;
    }

    public void setCidadeOrig(String cidadeOrig) {
        this.cidadeOrig = cidadeOrig;
    }
}