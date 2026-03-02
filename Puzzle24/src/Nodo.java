import java.util.ArrayList;
import java.util.List;

public class Nodo {
    String estado; 
    Nodo padre;

    public Nodo(String estado) {
        this.estado = estado;
    }

    public List<Nodo> generarSucesores() {
        List<Nodo> sucesores = new ArrayList<>();
        String[] piezas = estado.split(",");
        int idx = -1;

        // Buscar la posición del vacío (representado por 0)
        for (int i = 0; i < piezas.length; i++) {
            if (piezas[i].equals("0")) {
                idx = i;
                break;
            }
        }

        int r = idx / 5;
        int c = idx % 5;

        // Movimientos: Arriba, Abajo, Izquierda, Derecha
        if (r > 0) sucesores.add(crearHijo(piezas, idx, idx - 5));
        if (r < 4) sucesores.add(crearHijo(piezas, idx, idx + 5));
        if (c > 0) sucesores.add(crearHijo(piezas, idx, idx - 1));
        if (c < 4) sucesores.add(crearHijo(piezas, idx, idx + 1));

        return sucesores;
    }

    private Nodo crearHijo(String[] piezas, int i, int j) {
        String[] copia = piezas.clone();
        String temp = copia[i];
        copia[i] = copia[j];
        copia[j] = temp;
        
        // Reconstruir el string con comas (Concatenación)
        String nuevoEstado = "";
        for (int k = 0; k < copia.length; k++) {
            nuevoEstado += copia[k] + (k < copia.length - 1 ? "," : "");
        }
        
        Nodo hijo = new Nodo(nuevoEstado);
        hijo.padre = this;
        return hijo;
    }
}