package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos. Las listas no aceptan a
 * <code>null</code> como elemento.</p>
 */
public class Lista {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Object elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Object elemento) {
            this.elemento = elemento;
            this.anterior = anterior;
            this.siguiente = siguiente;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return this.anterior;
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            // Aquí va su código.
            return this.siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public Object get() {
            // Aquí va su código.
            return this.elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        longitud = 0;
        if(cabeza == null)
        longitud = 0;
        Nodo n = cabeza;
        while(n!= null){
         longitud ++;
         n = n.siguiente;
        }
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        return longitud == 0;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaFinal(Object elemento) {
        if(elemento != null){
        Nodo n = new Nodo(elemento);
        longitud++;
        if(rabo == null){
          cabeza=rabo=n;
          }else {
          rabo.siguiente = n;
          n.anterior = rabo;
          rabo = n;
        }
      }
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaInicio(Object elemento) {
        if(elemento != null){
        Nodo n = new Nodo(elemento);
        longitud++;
        if(cabeza == null){
          cabeza=rabo=n;
          }else {
          cabeza.anterior = n;
          n.siguiente = cabeza;
          cabeza = n;
        }
      }
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar. El elemento se inserta únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void inserta(int i, Object elemento) {
        if(elemento == null)
          return;
        if(longitud == 0) {
          agregaFinal(elemento);
          return;
        } else if(i >= longitud) {
          agregaFinal(elemento);
          return;
        } else if(i <= 0){
          agregaInicio(elemento);
          return;
        }
            longitud++;
            Nodo n = cabeza;
                int j = 0;
                while(n!=null && j<i){
                    n = n.siguiente;
                    j++;
                  }
                Nodo m = new Nodo(elemento);
                m.siguiente = n;
                m.anterior = n.anterior;
                (n.anterior).siguiente = m;
                n.anterior = m;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Object elemento) {
        Nodo n = buscaNodo(elemento,cabeza);
           if(n!= null){
               if(n.equals(cabeza)){
               eliminaPrimero();
               }else{
               if(n.equals(rabo)){
                 eliminaUltimo();
                 }else{
                   (n.anterior).siguiente = n.siguiente;
                   (n.siguiente).anterior = n.anterior;
             longitud--;
        }
       }
      }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Object eliminaPrimero() {
        if(cabeza == null)
          return null;
        Object r = cabeza.elemento;
          if(longitud == 1){
            cabeza = rabo = null;
            longitud--;
            return r;
          }
          else{
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longitud--;
            return r;
          }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Object eliminaUltimo() {
        if(rabo == null)
          return null;
        Object r = rabo.elemento;
        if(longitud == 1){
        cabeza = rabo = null;
        longitud--;
        return r;
       }
        else{
        rabo = rabo.anterior;
        rabo.siguiente = null;
        longitud--;
        return r;
       }
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(Object elemento) {
        return buscaNodo(elemento,cabeza) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista reversa() {
        Lista r = new Lista();
        return reversa(r, rabo);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista copia() {
        Lista copiaLista = new Lista();
        return copia(copiaLista, cabeza);
        
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Object getPrimero() {
        return cabeza != null? cabeza.elemento:null;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Object getUltimo() {
        return rabo != null? rabo.elemento :null;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, o <code>null</code> si
     *         <em>i</em> es menor que cero o mayor o igual que el número de
     *         elementos en la lista.
     */
    public Object get(int i) {
        return (i<0 || i>=longitud) ? null : get(cabeza, i, 0);
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Object elemento) {
        return indiceDe(elemento, cabeza, 0);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        if(cabeza == null)
          return "[]";
        String r = "[" + cabeza.elemento.toString();
        return toString(r, cabeza.siguiente);
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Lista))
            return false;
        Lista lista = (Lista)objeto;
        return (lista == null || longitud != lista.longitud) ? false : equals(cabeza, lista.cabeza);
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return rabo;
    }


    //Zona de metodos auxiliares

    /*metodo auxiliar getLongitud*/
    private int getLongitud(Nodo n){
      return (n == null)?0: (1 + getLongitud(n.siguiente));
    }

    /*metodo auxiliar para inserta*/
    private Nodo inserta(Nodo n,int i){
      return i-- > 0? inserta(n.siguiente,i): n;
    }

    /*metodo auxiliar para buscar nodo*/
    private Nodo buscaNodo(Object elemento, Nodo n){
        return(n == null)? null : n.elemento.equals(elemento)? n: buscaNodo(elemento, n.siguiente);
    }

    /*metodo auxiliar para reversa*/
    private Lista reversa(Lista r, Nodo n){
     if(n == null)
          return r;
     r.agregaFinal(n.elemento);
     return reversa(r, n.anterior);
    }

    /*metodo auxiliar para copia*/
    private Lista copia(Lista copiaLista, Nodo n){
      if (n == null)
        return copiaLista;
      copiaLista.agregaFinal(n.elemento);
      return copia(copiaLista, n.siguiente);
    }

    /*metodo auxiliar para get*/
    private Object get(Nodo n, int i, int c){
        return (i == c) ? n.elemento : get(n.siguiente, i--, c+1);
    }

    /*metodo auxiliar para indiceDe*/
    private int indiceDe(Object elemento, Nodo n, int c){
      return (n == null) ? -1 : (n.elemento.equals(elemento)) 
                         ? c : indiceDe(elemento, n.siguiente, c+1);
    }

    /*metodo auxiliar para toString*/
    private String toString(String r, Nodo n){
        if(n == null)
          return r + "]";
        r+=", "+ n.elemento.toString();
        return toString(r, n.siguiente);
    }

    /*metodo auxiliar para equals*/
    private boolean equals(Nodo n, Nodo listaDiferente){
        return (n == null) ? true : n.elemento.equals(listaDiferente.elemento)
                           ? equals(n.siguiente,listaDiferente.siguiente) :false;
    }
}
