import java.util.LinkedList;
import java.util.Queue;

//Ordem de chegada / ordem de saída//

public class Exercicio7 {
    public static void main(String[] args) {
        Queue<String> clientes = new LinkedList<>();

        clientes.add("Cliente 1");
        clientes.add("Cliente 2");
        clientes.add("Cliente 3");

        System.out.println("Ordem de saída:");

        while (!clientes.isEmpty()) {
            System.out.println(clientes.poll());
        }
    }
}
