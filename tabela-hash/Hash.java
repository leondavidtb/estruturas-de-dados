package hashtable;

/**
 * @author leonardo
 *
 */
public class Hash {

  public ListaEncadeada hash[];

  public Hash(int m) {
    this.hash = new ListaEncadeada[m];
  }

  public int h(int chave) {
    return ((chave * 3) % hash.length);
  }

  public void add(int n) {
    if(n > 0) {
      int indice = h(n);
      if(hash[indice] == null) {
        hash[indice] = new ListaEncadeada();
      }
      hash[indice].add(n);
    }
  }

  public int remove(int n) {
    if(n > 0){
      int indice = h(n);
      if(hash[indice] != null) {
        return hash[indice].remove(n);
      }
    }
    return 0;
  }

  public int buscar(int n) {
    int indice = h(n);
    if(hash[indice] != null) {
      return  hash[indice].buscar(n);
    }
    return 0;
  }

  public void imprimir() {
    for (int i = 0;i < hash.length ;i++ ) {
      if(hash[i] != null) {
        hash[i].imprimir();
      }
    }
  }
}
