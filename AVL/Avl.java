/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author leon
 */
public class Avl {

    private No raiz;
    No aux = raiz;
    boolean h = false;

    public No getRaiz() {
        return this.raiz;
    }
    
    public No buscar(int x) {
        return buscar(raiz, x);
    }

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

    private void rotacaoDireita(No pt) {
        No ptu = pt.getEsq();
        pt.setEsq(ptu.getDir());

        if (ptu.getDir() != null) {
            ptu.getDir().setPai(pt);
        }

        ptu.setDir(pt);
        ptu.setPai(pt.getPai());

        if (pt.getPai() != null) {
            if (pt.getPai().getEsq() == pt) {
                pt.getPai().setEsq(ptu);
            } else {
                pt.getPai().setDir(ptu);
            }
        } else {
            raiz = ptu;
        }

        pt.setPai(ptu);
    }

    private void rotacaoEsquerda(No pt) {
        No ptu = pt.getDir();
        pt.setDir(ptu.getEsq());

        if (ptu.getEsq() != null) {
            ptu.getEsq().setPai(pt);
        }

        ptu.setEsq(pt);
        ptu.setPai(pt.getPai());

        if (pt.getPai() != null) {
            if (pt.getPai().getDir() == pt) {
                pt.getPai().setDir(ptu);
            } else {
                pt.getPai().setEsq(ptu);
            }
        } else {
            raiz = ptu;
        }

        pt.setPai(ptu);
    }

    private void RDD(No pt) {
        No ptu = pt.getEsq();
        rotacaoEsquerda(ptu);
        rotacaoDireita(pt);
    }

    private void RDE(No pt) {
        No ptu = pt.getDir();
        rotacaoDireita(ptu);
        rotacaoEsquerda(pt);
    }

    private void caso1(No pt) {
        No ptu = pt.getEsq();

        if (ptu.getBal() == -1) {
            this.rotacaoDireita(pt);
            pt.setBal(0);
            ptu.setBal(0);
        } else {
            RDD(pt);

            switch (pt.getPai().getBal()) {
                case -1:
                    pt.setBal(1);
                    ptu.setBal(0);
                    break;
                case 0:
                    pt.setBal(0);
                    ptu.setBal(0);
                    break;
                default:
                    pt.setBal(0);
                    ptu.setBal(-1);
                    break;
            }

            pt.getPai().setBal(0);
        }

        h = false;
    }

    private void caso2(No pt) {
        No ptu = pt.getDir();

        if (ptu.getBal() == 1) {
            this.rotacaoEsquerda(pt);
            pt.setBal(0);
            ptu.setBal(0);
        } else {
            RDE(pt);

            switch (pt.getPai().getBal()) {
                case 1:
                    pt.setBal(-1);
                    ptu.setBal(0);
                    break;
                case 0:
                    pt.setBal(0);
                    ptu.setBal(0);
                    break;
                default:
                    pt.setBal(0);
                    ptu.setBal(1);
                    break;
            }

            pt.getPai().setBal(0);
        }

        h = false;
    }

    public boolean inserir(int chave) {
        if (raiz == null) {
            raiz = new No(chave, 0);
        } else {
            this.inserir(chave, raiz);
        }

        return true;
    }

    private boolean inserir(int chave, No pt) {

        if (pt == null) {
            No novo = new No(chave, 0);

            if (chave < aux.getChave()) {
                aux.setEsq(novo);
            } else {
                aux.setDir(novo);
            }

            novo.setPai(aux);
            h = true;
        } else if (chave != pt.getChave()) {
            if (chave < pt.getChave()) {
                if (pt.getEsq() == null) {
                    aux = pt;
                }

                inserir(chave, pt.getEsq());

                if (h == true) {
                    switch (pt.getBal()) {
                        case 1:
                            pt.setBal(0);
                            h = false;
                            break;
                        case 0:
                            pt.setBal(-1);
                            break;
                        case (-1):
                            this.caso1(pt);
                            break;
                    }
                }
            } else {
                if (pt.getDir() == null) {
                    aux = pt;
                }

                inserir(chave, pt.getDir());

                if (h == true) {
                    switch (pt.getBal()) {
                        case -1:
                            pt.setBal(0);
                            h = false;
                            break;
                        case 0:
                            pt.setBal(1);
                            break;
                        case 1:
                            this.caso2(pt);
                            break;
                    }
                }
            }
        }
        return h;
    }

    private No sucessor(No pt) {
        if (pt == null) {
            return null;
        }

        No pt2 = pt.getDir();

        while (pt2.getEsq() != null) {
            pt2 = pt2.getEsq();
        }
        return pt2;
    }

    private void moverPai(No u, No v) {
        if (u.getPai() == null) {
            raiz = v;
        } else {
            if (u.getPai().getEsq() == u) {
                u.getPai().setEsq(v);
            } else {
                u.getPai().setDir(v);
            }
        }
        if (v != null) {
            v.setPai(u.getPai());
        }
    }

    private void removerCaso1(No pt) {
        No ptu = pt.getEsq();

        if (ptu.getBal() <= 0) {
            this.rotacaoDireita(pt);

            if (ptu.getBal() == 0) {
                ptu.setBal(1);
                pt.setBal(-1);
                h = false;
            } else {
                ptu.setBal(0);
                pt.setBal(0);
                h = true;
            }
        } else {
            No ptz = ptu.getDir();
            this.RDD(pt);

            switch (ptz.getBal()) {
                case 0:
                    ptu.setBal(0);
                    pt.setBal(0);
                    break;
                case (-1):
                    ptu.setBal(0);
                    pt.setBal(1);
                    break;
                case 1:
                    ptu.setBal(-1);
                    pt.setBal(0);
                    break;
            }

            ptz.setBal(0);
            h = true;
        }
    }

    private void removerCaso2(No pt) {
        No ptu = pt.getDir();

        if (ptu.getBal() >= 0) {
            this.rotacaoEsquerda(pt);

            if (ptu.getBal() == 0) {
                ptu.setBal(-1);
                pt.setBal(1);
                h = false;
            } else {
                ptu.setBal(-1);
                pt.setBal(0);
                h = true;
            }
        } else {
            No ptz = ptu.getEsq();
            this.RDE(pt);

            switch (ptz.getBal()) {
                case 0:
                    ptu.setBal(0);
                    pt.setBal(0);
                    break;
                case (-1):
                    ptu.setBal(0);
                    pt.setBal(-1);
                    break;
                case 1:
                    ptu.setBal(1);
                    pt.setBal(0);
                    break;
            }

            ptz.setBal(0);
            h = true;
        }
    }

    private void balancearAvl(No p, char noRemovido) {
        if (noRemovido == 'D') {
            switch (p.getBal()) {
                case 1:
                    p.setBal(0);
                    h = true;
                    break;
                case 0:
                    p.setBal(-1);
                    h = false;
                    break;
                case (-1):
                    this.removerCaso1(p);
                    break;
            }
        } else {
            switch (p.getBal()) {
                case (-1):
                    p.setBal(0);
                    h = true;
                    break;
                case 0:
                    p.setBal(1);
                    h = false;
                    break;
                case 1:
                    this.removerCaso2(p);
                    break;
            }
        }

        if (h == true) {
            if (p.getPai() != null) {
                if (p.getPai().getEsq() == p) {
                    this.balancearAvl(p.getPai(), 'E');
                } else {
                    this.balancearAvl(p.getPai(), 'D');
                }
            }
        }
    }

    public void remover(int x) {
        char noRemovido = 0;
        No pt = this.buscar(raiz, x);
        No p = null;

        if (pt.getChave() != x) {
            System.out.println("Elemento não encontrado!");
        } else {
            p = pt.getPai();
            if (p != null) {
                if (pt.getPai().getEsq() == pt) {
                    noRemovido = 'E';
                } else {
                    noRemovido = 'D';
                }
            }

            if (pt.getEsq() == null) {
                this.moverPai(pt, pt.getDir());
            } else if (pt.getDir() == null) {
                this.moverPai(pt, pt.getEsq());
            } else {
                
                No s = sucessor(pt);
                s.setBal(pt.getBal());
                p = s;
                noRemovido = 'D';

                if (pt.getDir() != s) {
                    p = s.getPai();
                    noRemovido = 'E';
                    this.moverPai(s, s.getDir());
                    s.setDir(pt.getDir());
                    s.getDir().setPai(s);
                }

                this.moverPai(pt, s);
                s.setEsq(pt.getEsq());
                s.getEsq().setPai(s);
            }
        }

        if (pt != null) {
            this.balancearAvl(p, noRemovido);
        }
    }

    public void imprimir(No pt) {
        if (pt != null) {
            System.out.println("Chave: " + pt.getChave() + " Bal: " + pt.getBal());
            if (pt.getEsq() != null) {
                imprimir(pt.getEsq());
            }
            if (pt.getDir() != null) {
                imprimir(pt.getDir());
            }
        }
    }
}
