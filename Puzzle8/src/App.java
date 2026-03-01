public class App {
    public static void main(String[] args) {
        String inicial = "1238_4765";
        String objetivo = "123478_65";

        //== Probando Anchura ==
        Arbol a1 = new Arbol(new Nodo(inicial));
        Nodo n1 = a1.busquedaxAnchura(objetivo);
        if(n1 != null) System.out.println("Nivel final: " + n1.nivel);

        //== Probando Profundidad ==
        Arbol a2 = new Arbol(new Nodo(inicial));
        Nodo n2 = a2.busquedaxProfundidad(objetivo);
        if(n2 != null) System.out.println("Nivel final: " + n2.nivel);

        //== Probando Prioridad ==
        Arbol a3 = new Arbol(new Nodo(inicial));
        Nodo n3 = a3.busquedaxPrioridad(objetivo);
        if(n3 != null) System.out.println("Nivel final: " + n3.nivel);
    }
}