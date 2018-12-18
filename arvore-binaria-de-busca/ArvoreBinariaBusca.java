/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebinariadebusca;

/**
 *
 * @author leon
 */
public class ArvoreBinariaBusca {

    private No raiz;

    private No buscar(No pt, int x) {
        No retorno;
        if (pt == null) {
            retorno = null;
        } else if (x == pt.getChave()) {
            retorno = pt;
        } else if (x < pt.getChave()) {
            if (pt.getEsq() == null) {
                retorno = pt;
            } else {
                retorno = buscar(pt.getEsq(), x);
            }
        } else {
            if (pt.getDir() == null) {
                retorno = pt;
            } else {
                retorno = buscar(pt.getDir(), x);
            }
        }
        return retorno;
    }

    /**
     *
     * @param x
     * @return
     */
    public No buscar(int x) {
        return buscar(raiz, x);
    }

    public No remover(int x) {
        No retorno = null;
        No no = buscar(x);
        No noPai = no.getPai();

        if (no == null || no.getChave() != x) {
            raiz = null;

        } else {

            if (no.getEsq() == null && no.getDir() == null) {
                if (no.getPai().getDir() == no) {
                    noPai.setDir(null);
                } else {
                    noPai.setEsq(null);
                }
            } else if (no.getEsq() != null && no.getDir() == null) {
                no.getEsq().setEsq(no.getPai());
                if (no.getPai().getDir() == no) {
                    noPai.setDir(no.getEsq());
                } else {
                    noPai.setEsq(no.getEsq());
                }
            } else if (no.getEsq() == null && no.getDir() != null) {
                no.getDir().setPai(no.getPai());
                if (no.getPai().getDir() == no) {
                    noPai.setDir(no.getDir());
                } else {
                    noPai.setEsq(no.getDir());
                }
            } else if (no.getEsq() != null && no.getDir() != null) {
                no.getDir().setPai(no.getPai());
                no.getEsq().setPai(no.getPai());
                if (no.getPai().getDir() == no) {
                    noPai.setDir(no.getDir());
                    noPai.setDir(no.getEsq());
                } else {
                    noPai.setEsq(no.getDir());
                    noPai.setDir(no.getEsq());
                }
            }
            no.setPai(null);
            no.setEsq(null);
            no.setDir(null);

        }
        return retorno;
    }

    /**
     *
     * @param x
     *
     */
    public void inserir(int x) {
        No aux = buscar(x);
        No novo = new No();
        novo.setChave(x);

        if (aux == null) {
            this.raiz = novo;

        } else {
            if (aux.getChave() == x) {
                System.out.println("Elemento já existe");
            } else {
                if (x < aux.getChave()) {
                    aux.setEsq(novo);
                } else {
                    aux.setDir(novo);
                }
                novo.setPai(aux);
            }
        }
    }

    public void imprimir() {
        inOrdem(raiz);
    }

    private void inOrdem(No pt) {
        if (pt != null) {
            if (pt.getEsq() != null) {
                inOrdem(pt.getEsq());
            }
            System.out.print(pt.getChave() + " ");
            if (pt.getDir() != null) {
                inOrdem(pt.getDir());
            }
        }
    }
}
