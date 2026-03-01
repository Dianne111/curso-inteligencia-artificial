import java.util.LinkedList;

public class Nodo implements Comparable<Nodo> {
    String estado;
    int nivel;
    int costo;
    Nodo padre;

    public Nodo(String estado) {
        this.estado = estado;
    }

    public LinkedList<Nodo> generarSucesores() {
        LinkedList<Nodo> sucesores = new LinkedList<>();
        int indice = estado.indexOf('_');

        switch (indice) {
            case 0:
                sucesores.add(new Nodo(intercambiar(estado, 0, 1)));
                sucesores.add(new Nodo(intercambiar(estado, 0, 3)));
                break;
            case 1:
                sucesores.add(new Nodo(intercambiar(estado, 1, 0)));
                sucesores.add(new Nodo(intercambiar(estado, 1, 2)));
                sucesores.add(new Nodo(intercambiar(estado, 1, 4)));
                break;
            case 2:
                sucesores.add(new Nodo(intercambiar(estado, 2, 1)));
                sucesores.add(new Nodo(intercambiar(estado, 2, 5)));
                break;
            case 3:
                sucesores.add(new Nodo(intercambiar(estado, 3, 0)));
                sucesores.add(new Nodo(intercambiar(estado, 3, 4)));
                sucesores.add(new Nodo(intercambiar(estado, 3, 6)));
                break;
            case 4:
                sucesores.add(new Nodo(intercambiar(estado, 4, 1)));
                sucesores.add(new Nodo(intercambiar(estado, 4, 3)));
                sucesores.add(new Nodo(intercambiar(estado, 4, 5)));
                sucesores.add(new Nodo(intercambiar(estado, 4, 7)));
                break;
            case 5:
                sucesores.add(new Nodo(intercambiar(estado, 5, 2)));
                sucesores.add(new Nodo(intercambiar(estado, 5, 4)));
                sucesores.add(new Nodo(intercambiar(estado, 5, 8)));
                break;
            case 6:
                sucesores.add(new Nodo(intercambiar(estado, 6, 3)));
                sucesores.add(new Nodo(intercambiar(estado, 6, 7)));
                break;
            case 7:
                sucesores.add(new Nodo(intercambiar(estado, 7, 4)));
                sucesores.add(new Nodo(intercambiar(estado, 7, 6)));
                sucesores.add(new Nodo(intercambiar(estado, 7, 8)));
                break;
            case 8:
                sucesores.add(new Nodo(intercambiar(estado, 8, 5)));
                sucesores.add(new Nodo(intercambiar(estado, 8, 7)));
                break;
        }
        return sucesores;
    }

    private String intercambiar(String estado, int i, int j) {
        char a = estado.charAt(i);
        char b = estado.charAt(j);
        String resultado = "";
        for (int k = 0; k < estado.length(); k++) {
            if (k == i) resultado += b;
            else if (k == j) resultado += a;
            else resultado += estado.charAt(k);
        }
        return resultado;
    }

    @Override
    public int compareTo(Nodo n) {
        return Integer.compare(this.costo, n.costo);
    }
}