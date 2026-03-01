import java.util.*;

public class Arbol {
    Nodo raiz;

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
        if (this.raiz != null) this.raiz.nivel = 0;
    }

    // BFS: Primero en Anchura (Usa Queue)
    public Nodo busquedaxAnchura(String objetivo) {
        Queue<Nodo> cola = new LinkedList<>();
        HashSet<String> visitados = new HashSet<>();

        cola.add(raiz);
        visitados.add(raiz.estado);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            if (actual.estado.equals(objetivo)) return terminar(actual, "Anchura");

            for (Nodo hijo : actual.generarSucesores()) {
                if (!visitados.contains(hijo.estado)) {
                    asignarPadre(hijo, actual);
                    visitados.add(hijo.estado);
                    cola.add(hijo);
                }
            }
        }
        return null;
    }

    // DFS: Primero en Profundidad (Usa Stack)
    public Nodo busquedaxProfundidad(String objetivo) {
        Stack<Nodo> pila = new Stack<>();
        HashSet<String> visitados = new HashSet<>();

        pila.push(raiz);
        visitados.add(raiz.estado);

        while (!pila.isEmpty()) {
            Nodo actual = pila.pop();
            if (actual.estado.equals(objetivo)) return terminar(actual, "Profundidad");

            for (Nodo hijo : actual.generarSucesores()) {
                if (!visitados.contains(hijo.estado)) {
                    asignarPadre(hijo, actual);
                    visitados.add(hijo.estado);
                    pila.push(hijo);
                }
            }
        }
        return null;
    }

    // UCS: Búsqueda por Prioridad (Usa PriorityQueue)
    public Nodo busquedaxPrioridad(String objetivo) {
        PriorityQueue<Nodo> pq = new PriorityQueue<>();
        HashSet<String> visitados = new HashSet<>();

        raiz.costo = 0;
        pq.add(raiz);
        visitados.add(raiz.estado);

        while (!pq.isEmpty()) {
            Nodo actual = pq.poll();
            if (actual.estado.equals(objetivo)) return terminar(actual, "Prioridad");

            for (Nodo hijo : actual.generarSucesores()) {
                if (!visitados.contains(hijo.estado)) {
                    asignarPadre(hijo, actual);
                    hijo.costo = actual.costo + 1; // En este caso costo = nivel
                    visitados.add(hijo.estado);
                    pq.add(hijo);
                }
            }
        }
        return null;
    }

    private void asignarPadre(Nodo hijo, Nodo padre) {
        hijo.padre = padre;
        hijo.nivel = padre.nivel + 1;
    }

    private Nodo terminar(Nodo n, String tipo) {
        System.out.println("\n--- Solución encontrada por " + tipo + " ---");
        imprimirCamino(n);
        return n;
    }

    private void imprimirCamino(Nodo n) {
        LinkedList<Nodo> camino = new LinkedList<>();
        Nodo aux = n;
        while (aux != null) {
            camino.addFirst(aux);
            aux = aux.padre;
        }
        for (int i = 0; i < camino.size(); i++) {
            System.out.println("Paso " + i + ": " + camino.get(i).estado);
        }
    }
}