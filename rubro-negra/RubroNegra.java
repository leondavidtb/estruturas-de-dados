package rubronegra;

public class RubroNegra {
	
    private No externo = new No();
    private No raiz = externo;
    
    public RubroNegra() {
    	externo.setPai(externo);
    	externo.setEsq(externo);
    	externo.setDir(externo);
    }
    
    private No criarNo(int chave) {
        return new No(chave);
    }
    
    private No buscar(No pt, int x) {
        No retorno;
        if (pt == externo) {
            retorno = externo;
        } else if (x == pt.getChave()) {
            retorno = pt;
        } else if (x < pt.getChave()) {
            if (pt.getEsq() == externo) {
                retorno = pt;
            } else {
                retorno = buscar(pt.getEsq(), x);
            }
        } else {
            if (pt.getDir() == externo) {
                retorno = pt;
            } else {
                retorno = buscar(pt.getDir(), x);
            }
        }
        return retorno;
    }

    private void rotacaoEsquerda(No u) {
        No v = u.getDir();
        u.setDir(v.getEsq());
        if (v.getEsq() != externo) {
            v.getEsq().setPai(u);
        }
        v.setEsq(u);
        v.setPai(u.getPai());
        this.moverPai(u, v);
        u.setPai(v);
    }

    private void rotacaoDireita(No u) {
        No v = u.getEsq();
        u.setEsq(v.getDir());
        if (v.getDir() != externo) {
            v.getDir().setPai(u);
        }
        v.setDir(u);
        v.setPai(u.getPai());
        this.moverPai(u, v);
        u.setPai(v);
    }

    private void moverPai(No u, No v) {
        if (u.getPai() == externo) {
            raiz = v;
            v.setPai(externo);
        } else {
            if (u.getPai().getEsq() == u) {
                u.getPai().setEsq(v);
            } else {
                u.getPai().setDir(v);
            }
        }

        v.setPai(u.getPai());
    }
    
    public void inserir(int chave) {
        this.inserir(criarNo(chave));
    }

    private void inserir(No z) {
        No y = externo;
        No x = raiz;

        while (x != externo) {
            y = x;

            if (x.getChave() == z.getChave()) {
                System.out.println("Elemento existente");
                y = null;
                break;
            } else if (z.getChave() < x.getChave()) {
                x = x.getEsq();
            } else {
                x = x.getDir();
            }
        }

        if (y != null) {
            if (y == externo) {
                raiz = z;
                z.setPai(externo);
            } else {
                if (z.getChave() < y.getChave()) {
                    y.setEsq(z);
                } else {
                    y.setDir(z);
                }
                z.setPai(y);
                z.setCor('R');
            }
            z.setEsq(externo);
            z.setDir(externo);
            rotacao(z);
        }
    }

    private void rotacao(No z) {
        No y;
        while (z.getPai().getCor() == 'R') {
            if (z.getPai() == z.getPai().getPai().getEsq()) {
                y = z.getPai().getPai().getDir();

                if (y.getCor() == 'R') {
                    z.getPai().setCor('N');
                    y.setCor('N');
                    z.getPai().getPai().setCor('R');
                    z = z.getPai().getPai();
                } else {
                    if (z.getPai().getDir() == z) {
                        z = z.getPai();
                        rotacaoEsquerda(z);
                    }
                    z.getPai().setCor('N');
                    z.getPai().getPai().setCor('R');
                    rotacaoDireita(z.getPai().getPai());
                }
            } else if (z.getPai() == z.getPai().getPai().getDir()) {
                y = z.getPai().getPai().getEsq();

                if (y.getCor() == 'R') {
                    z.getPai().setCor('N');
                    y.setCor('N');
                    z.getPai().getPai().setCor('R');
                    z = z.getPai().getPai();
                } else {
                    if (z.getPai().getEsq() == z) {
                        z = z.getPai();
                        rotacaoDireita(z);
                    }
                    z.getPai().setCor('N');
                    z.getPai().getPai().setCor('R');
                    rotacaoEsquerda(z.getPai().getPai());
                }
            }
        }
        raiz.setCor('N');
    }
    
    private No sucessor(No pt) {
        if (pt == externo) {
            return null;
        }

        No pt2 = pt.getDir();

        while (pt2.getEsq() != externo) {
            pt2 = pt2.getEsq();
        }
        return pt2;
    }

    private void rotaCor(No x) {
    	No w;
    	while(x != raiz && x.getCor() != 'R') {
    		if(x == x.getPai().getEsq()) {
    			w = x.getPai().getDir();
    			
    			if(w.getCor() == 'R') {
    				w.setCor('N');
    				w.getPai().setCor('R');
    				this.rotacaoEsquerda(x.getPai());
    				w = x.getPai().getDir();
    			} else {
    				if (w.getEsq().getCor() == 'N' && w.getDir().getCor() == 'N') {
    					w.setCor('R');
    					x = x.getPai();
    				} else {
    					if (w.getDir().getCor() == 'N') {
    						w.getEsq().setCor('N');
    						w.setCor('R');
    						this.rotacaoDireita(w);
    						w = w.getPai();
    					}
    					
    					w.setCor(w.getPai().getCor());
    					w.getDir().setCor('N');
    					w.getPai().setCor('N');
    					this.rotacaoEsquerda(x.getPai());
    					x = raiz;
    				}
    			}
    		}else {
    			w = x.getPai().getEsq();
    			
    			if(w.getCor() == 'R') {
    				w.setCor('N');
    				w.getPai().setCor('R');
    				this.rotacaoDireita(x.getPai());
    				w = x.getPai().getEsq();
    			} else {
    				if (w.getDir().getCor() == 'N' && w.getEsq().getCor() == 'N') {
    					w.setCor('R');
    					x = x.getPai();
    				} else {
    					if (w.getEsq().getCor() == 'N') {
    						w.getDir().setCor('N');
    						w.setCor('R');
    						this.rotacaoEsquerda(w);
    						w = w.getPai();
    					}
    					
    					w.setCor(w.getPai().getCor());
    					w.getEsq().setCor('N');
    					w.getPai().setCor('N');
    					this.rotacaoDireita(x.getPai());
    					x = raiz;
    				}
    			}
    		}
    	}
    	x.setCor('N');
    }
    
    public void remover(int chave) {
    	this.remover(this.buscar(raiz, chave));
    }
    
    private void remover(No z) {
    	char corOriginal = z.getCor();
    	No x,s;
    	if(z.getEsq() == externo) {
    		x = z.getDir();
    		x.setCor(z.getCor());
    		this.moverPai(z, z.getDir());
    	} else {
    		if (z.getDir() == externo) {
    			x = z.getEsq();
    			x.setCor(z.getCor());
    			this.moverPai(z, z.getEsq());
    		} else {
    			s = this.sucessor(z);
    			x = s.getDir();
    			externo.setPai(s);
    			corOriginal = s.getCor();
    			s.setCor(z.getCor());
    			
    			if(s != z.getDir()) {
    				this.moverPai(s, x);
    				s.setDir(z.getDir());
    				s.getDir().setPai(s);
    			}
    			
    			this.moverPai(z, s);
    			s.setEsq(z.getEsq());
    			s.getEsq().setPai(s);
    		}
    	}
    	if(corOriginal == 'N') {
    		this.rotaCor(x);
    	}
    }
    
    public No getRaiz() {
        return raiz;
    }

    public void imprimir(No ptraiz) {
        if (ptraiz != externo) {
            System.out.format("Nó: %3d  | Cor: %3c\n", ptraiz.getChave(), ptraiz.getCor());

            if (ptraiz.getEsq() != externo) {
                imprimir(ptraiz.getEsq());
            }

            if (ptraiz.getDir() != externo) {
                imprimir(ptraiz.getDir());
            }
        }
    }

}
