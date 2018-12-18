package rubronegra;

public class No {

    private No pai;
    private No esq;
    private No dir;
    private char cor;
    private int chave;
    
    public No() {
        this.setCor('N');
    }

    public No(int chave) {
        this();
        this.setChave(chave);
    }

    public No getPai() {
        return pai;
    }

    public No getEsq() {
        return esq;
    }

    public No getDir() {
        return dir;
    }

    public char getCor() {
        return cor;
    }

    public int getChave() {
        return chave;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public void setEsq(No esq) {
        this.esq = esq;
    }

    public void setDir(No dir) {
        this.dir = dir;
    }

    public void setCor(char cor) {
        this.cor = cor;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }
}
