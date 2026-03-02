import java.util.*;

public class AgenteIDAStar {
    private final String OBJETIVO = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0";
    private long nodosExpandidos = 0;

    // --- HEURÍSTICA 1: Distancia de Manhattan (Base) ---
    public int calcularManhattan(String estado) {
        int h = 0;
        String[] actual = estado.split(",");
        for (int i = 0; i < actual.length; i++) {
            if (!actual[i].equals("0")) {
                int n = Integer.parseInt(actual[i]);
                int posObjetivo = n - 1; // Para el 1 es 0, para el 2 es 1...
                h += Math.abs(i / 5 - posObjetivo / 5) + Math.abs(i % 5 - posObjetivo % 5);
            }
        }
        return h;
    }

    // --- HEURÍSTICA 2: Conflicto Lineal (Manhattan + Penalización) ---
    public int calcularConflictoLineal(String estado) {
        String[] piezas = estado.split(",");
        int h = calcularManhattan(estado);
        int conflictos = 0;

        // Conflictos Horizontales (Filas)
        for (int fila = 0; fila < 5; fila++) {
            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 5; j++) {
                    int idx1 = fila * 5 + i;
                    int idx2 = fila * 5 + j;
                    int p1 = Integer.parseInt(piezas[idx1]);
                    int p2 = Integer.parseInt(piezas[idx2]);

                    if (p1 != 0 && p2 != 0) {
                        // ¿Ambas pertenecen a esta misma fila?
                        if ((p1 - 1) / 5 == fila && (p2 - 1) / 5 == fila) {
                            // ¿Están en orden invertido? (p1 debería ir después que p2)
                            if (p1 > p2) conflictos++;
                        }
                    }
                }
            }
        }

        // Conflictos Verticales (Columnas)
        for (int col = 0; col < 5; col++) {
            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 5; j++) {
                    int idx1 = i * 5 + col;
                    int idx2 = j * 5 + col;
                    int p1 = Integer.parseInt(piezas[idx1]);
                    int p2 = Integer.parseInt(piezas[idx2]);

                    if (p1 != 0 && p2 != 0) {
                        // ¿Ambas pertenecen a esta misma columna?
                        if ((p1 - 1) % 5 == col && (p2 - 1) % 5 == col) {
                            // ¿Están en orden invertido verticalmente?
                            if (p1 > p2) conflictos++;
                        }
                    }
                }
            }
        }
        return h + (2 * conflictos);
    }

    public void resolver(String inicial, boolean usarConflictoLineal) {
        nodosExpandidos = 0;
        String nombreH = usarConflictoLineal ? "Conflicto Lineal" : "Manhattan Simple";
        System.out.println("\n--- Solución encontrada por " + nombreH + " ---");
        
        long tiempoInicio = System.currentTimeMillis();
        int limite = usarConflictoLineal ? calcularConflictoLineal(inicial) : calcularManhattan(inicial);
        Nodo raiz = new Nodo(inicial);

        while (true) {
            Object resultado = buscar(raiz, 0, limite, usarConflictoLineal);
            if (resultado instanceof Nodo) {
                imprimirReporte((Nodo) resultado, System.currentTimeMillis() - tiempoInicio);
                return;
            }
            limite = (int) resultado;
        }
    }

    private Object buscar(Nodo nodo, int g, int limite, boolean usarConflicto) {
        int f = g + (usarConflicto ? calcularConflictoLineal(nodo.estado) : calcularManhattan(nodo.estado));
        if (f > limite) return f;
        if (nodo.estado.equals(OBJETIVO)) return nodo;

        int min = Integer.MAX_VALUE;
        nodosExpandidos++;

        for (Nodo hijo : nodo.generarSucesores()) {
            if (nodo.padre != null && hijo.estado.equals(nodo.padre.estado)) continue;
            Object res = buscar(hijo, g + 1, limite, usarConflicto);
            if (res instanceof Nodo) return res;
            if ((int) res < min) min = (int) res;
        }
        return min;
    }

    private void imprimirReporte(Nodo n, long tiempo) {
        LinkedList<String> camino = new LinkedList<>();
        Nodo aux = n;
        while (aux != null) {
            camino.addFirst(aux.estado);
            aux = aux.padre;
        }
        for (int i = 0; i < camino.size(); i++) System.out.println("Paso " + i + ": " + camino.get(i));
        System.out.println("\nNivel final (Solución): " + (camino.size() - 1));
        System.out.println("Nodos expandidos: " + nodosExpandidos);
        System.out.println("Tiempo de ejecución: " + tiempo + " ms");
    }
}