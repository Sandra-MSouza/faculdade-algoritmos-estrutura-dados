import java.util.ArrayList;

//Inversão de Lista//

public class Exercicio1 {
    public static void main(String[] args) {
        ArrayList<String> tarefas = new ArrayList<>();
        tarefas.add("Estudar");
        tarefas.add("Treinar");
        tarefas.add("Trabalhar");
        tarefas.add("Dormir");

        ArrayList<String> invertida = new ArrayList<>();

        for (int i = tarefas.size() - 1; i >= 0; i--) {
            invertida.add(tarefas.get(i));
        }

        System.out.println("Lista invertida:");
        for (String item : invertida) {
            System.out.println(item);
        }
    }
}
