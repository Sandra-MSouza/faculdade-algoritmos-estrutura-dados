import java.util.ArrayList;

//Mesclar listas intercalando//

public class Exercicio3 {
    public static void main(String[] args) {
        ArrayList<String> lista1 = new ArrayList<>();
        lista1.add("Ana");
        lista1.add("João");

        ArrayList<String> lista2 = new ArrayList<>();
        lista2.add("Maria");
        lista2.add("José");

        ArrayList<String> mesclada = new ArrayList<>();

        int maior = Math.max(lista1.size(), lista2.size());

        for (int i = 0; i < maior; i++) {
            if (i < lista1.size()) mesclada.add(lista1.get(i));
            if (i < lista2.size()) mesclada.add(lista2.get(i));
        }

        System.out.println("Lista mesclada: " + mesclada);
    }
}
 