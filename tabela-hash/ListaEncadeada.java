package hashtable;

/**
 * @author leonardo
 *
 */
public class ListaEncadeada {
  public No inicio;
  public int contador = 0;

  public void add(int valor) {
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

  public int remove(int valor) {
    int retorno = 0;
    if(contador == 0) {
      System.out.println("Tabela vazia");
    } else if (contador == 1) {
      retorno = inicio.valor;
      inicio = null;
      contador--;
    } else {
      No aux = inicio;
      if(aux.valor == valor) {
        retorno = aux.valor;
        return retorno;
      }
      for (int i = 0;i < contador;i++) {
        if(aux.prox.valor == valor) {
          retorno = aux.valor;
          aux.prox = aux.prox.prox;
          return retorno;
        }
        aux = aux.prox;
      }
      contador--;
    }
    return 0;
  }

  public int buscar(int valor) {
    if(contador == 0) {
      System.out.println("Hash vazia");
    } else {
      No aux = inicio;
      int retorno = 0;
      for (int i = 0;i < contador;i++) {
        if(aux.valor == valor) {
          retorno = aux.valor;
          System.out.println("Valor encontrado: " + retorno);
          return retorno;
        }
        aux = aux.prox;
      }
    }
    return 0;
  }

  public void imprimir() {
    No aux = inicio;
    for (int i = 0;i < contador ;i++ ) {
      System.out.println(aux.valor);
      aux = aux.prox;
    }
  }
}
