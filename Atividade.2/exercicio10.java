import java.util.ArrayList;

//Algoritmo de ordenação (Bubble Sort)//

public class Exercicio10 {

    public static void bubbleSort(ArrayList<Integer> lista) {
        int n = lista.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {

                if (lista.get(j) < lista.get(j + 1)) {
                    int temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> vendas = new ArrayList<>();
        vendas.add(20);
        vendas.add(5);
        vendas.add(40);
        vendas.add(15);

        bubbleSort(vendas);

        System.out.println("Ordenação decrescente: " + vendas);
    }
}
