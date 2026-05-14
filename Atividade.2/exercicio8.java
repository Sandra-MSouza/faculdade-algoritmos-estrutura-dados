import java.util.HashMap;

//HashMap – contagem de palavras//

public class Exercicio8 {
    public static void main(String[] args) {
        String frase = "eu gosto de estudar porque estudar é importante";

        String[] palavras = frase.split(" ");

        HashMap<String, Integer> mapa = new HashMap<>();

        for (String p : palavras) {
            mapa.put(p, mapa.getOrDefault(p, 0) + 1);
        }

        System.out.println("Contagem de palavras: " + mapa);
    }
}
