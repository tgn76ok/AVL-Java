public class Elemento {
    int RGM;
    String nome;

    public Elemento(int RGM, String nome) {
        this.RGM = RGM;
        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public int getRGM() {
        return RGM;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRGM(int RGM) {
        this.RGM = RGM;
    }
}
