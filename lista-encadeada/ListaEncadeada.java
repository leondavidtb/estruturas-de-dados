package listaencadeada;

/**
 * @author leonardo
 *
 */
public class ListaEncadeada {

	public No inicio;
	public int contador = 0;

	public void addInicio(int valor) {
		No novo = new No();
		novo.valor = valor;
		if(contador == 0) {
			inicio = novo;
		} else {
			novo.prox = inicio;
			inicio = novo;
		}
		contador++;
	}

	public void addFim(int valor) {
		No novo = new No();
		novo.valor = valor;
		if(contador == 0) {
			inicio = novo;
		} else {
			No aux = inicio;
			for(int i = 0; i < contador - 1; i++) {
				aux = aux.prox;
			}
			novo.prox = aux.prox;
			aux.prox = novo;
		}
		contador++;
	}

	public void add(int posicao, int valor) {
		No novo = new No();
		novo.valor = valor;
		if(posicao >= 0 && posicao <= contador) {
			if(posicao == 0){
				this.addInicio(valor);
			} else if (posicao == contador) {
				this.addFim(valor);
			} else {
				No aux = inicio;
				for (int i = 0; i < posicao - 1; i++) {
					aux = aux.prox;
				}
				novo.prox = aux.prox;
				aux.prox = novo;
				contador ++;
			}
		}
	}

	public int removeInicio() {
		if(contador == 0) {
			System.out.println("Não tem elemento!");
			return -1;
		}else {
			int retorno = inicio.valor;
			inicio = inicio.prox;
			contador--;
			
			return retorno;
			
		}
	}

	public int removeFim() {
		if(contador == 0) {
			System.out.println("Não tem elemento!");
			return -1;
		}else {
			No aux = inicio;
			for(int i = 0; i < contador - 1; i++) {
				aux = aux.prox;
			}
			int retorno = aux.valor;
			aux.prox = null;
			contador--;
			
			return retorno;
		}
	}

	public void remove(int posicao) {
		if(posicao >= 0 && posicao <= contador) {
			if(posicao == 0) {
				this.removeInicio();
			} else if (posicao == contador) {
				this.removeFim();
			} else {
				No aux = inicio;
				for (int i = 0;i < posicao - 1 ;i++ ) {
					aux = aux.prox;
				}
				aux.prox = aux.prox.prox;
				contador--;
			}
		}	
	}

	public void imprime() {
		No aux = inicio;
		for (int i = 0;i < contador ;i++ ) {
			System.out.println(" " + aux.valor);
			aux = aux.prox;
		}
	}
}
