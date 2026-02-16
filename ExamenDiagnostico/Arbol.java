public class Arbol {

    Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    public boolean vacio() {
        return raiz == null;
    }

    public void insertar(String nombre) {

        Nodo nuevo = new Nodo(nombre);

        if (raiz == null) {
            raiz = nuevo;
            return;
        }

        Nodo actual = raiz;

        while (true) {

            if (nombre.compareTo(actual.nombre) < 0) {

                if (actual.izquierdo == null) {
                    actual.izquierdo = nuevo;
                    return;
                }

                actual = actual.izquierdo;

            } else {

                if (actual.derecho == null) {
                    actual.derecho = nuevo;
                    return;
                }

                actual = actual.derecho;
            }
        }
    }

    public Nodo buscarNodo(String nombre) {

        Nodo actual = raiz;

        while (actual != null) {

            if (nombre.equals(actual.nombre)) {
                return actual;
            }

            if (nombre.compareTo(actual.nombre) < 0) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }

        return null;
    }

    public void imprimirArbol() {
        imprimir(raiz);
    }

    private void imprimir(Nodo nodo) {
        if (nodo != null) {
            imprimir(nodo.izquierdo);
            System.out.println(nodo.nombre);
            imprimir(nodo.derecho);
        }
    }
}
