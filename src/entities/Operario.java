package entities;

public class Operario {
    private int idOperario;
    private String nomeOperario;
    private String funcao;

    // Getters e Setters
    public int getIdOperario() {
        return idOperario;
    }

    public void setIdOperario(int idOperario) {
        this.idOperario = idOperario;
    }

    public String getNomeOperario() {
        return nomeOperario;
    }

    public void setNomeOperario(String nomeOperario) {
        this.nomeOperario = nomeOperario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
