public class Main {

    public static void main(String[] args) {

        Arbol arbol = new Arbol();

        System.out.println("Esta vacio? " + arbol.vacio());

        arbol.insertar("Dianne");
        arbol.insertar("Parroquin");
        arbol.insertar("Diaz");

        System.out.println("Contenido del arbol:");
        arbol.imprimirArbol();

        Nodo buscado = arbol.buscarNodo("Dianne");

        if (buscado != null) {
            System.out.println("Encontrado");
        } else {
            System.out.println("No encontrado");
        }

        System.out.println("Esta vacio? " + arbol.vacio());
    }
}
