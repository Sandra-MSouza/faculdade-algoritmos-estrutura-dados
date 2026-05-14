import java.util.LinkedList;
import java.util.Queue;

//Sistema de fila de espera//

public class Exercicio6 {
    public static void main(String[] args) {
        Queue<String> fila = new LinkedList<>();

        fila.add("Ana");
        fila.add("João");
        fila.add("Maria");

        System.out.println("Fila inicial: " + fila);

        System.out.println("Atendendo: " + fila.poll());
        System.out.println("Fila atual: " + fila);
    }
}
