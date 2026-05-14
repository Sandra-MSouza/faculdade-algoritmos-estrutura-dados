import java.util.ArrayList;
import java.util.Stack;

//Inversão usando Pilha//

public class Exercicio5 {
    public static void main(String[] args) {
        ArrayList<String> produtos = new ArrayList<>();
        produtos.add("Arroz");
        produtos.add("Feijão");
        produtos.add("Óleo");

        Stack<String> pilha = new Stack<>();

        for (String p : produtos) {
            pilha.push(p);
        }

        ArrayList<String> invertida = new ArrayList<>();
        while (!pilha.isEmpty()) {
            invertida.add(pilha.pop());
        }

        System.out.println("Produtos invertidos: " + invertida);
    }
}
