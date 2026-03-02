public class App {
    public static void main(String[] args) {
        
        String inicial = "2,1,3,4,5,7,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,0,24"; 
        
        AgenteIDAStar agente = new AgenteIDAStar();

        System.out.println("=== COMPARATIVA DE RENDIMIENTO ===");
        
        // CASO Manhattan: Debería expandir más nodos y tardar más.
        agente.resolver(inicial, false); 

        // CASO Conflicto Lineal: Debería expandir menos nodos y ser más rápido.
        agente.resolver(inicial, true);
    }
}