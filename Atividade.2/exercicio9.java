import java.util.HashMap;

//Mesclar mapas//

public class Exercicio9 {
    public static void main(String[] args) {
        HashMap<String, Integer> mapa1 = new HashMap<>();
        mapa1.put("Arroz", 10);
        mapa1.put("Feijão", 5);

        HashMap<String, Integer> mapa2 = new HashMap<>();
        mapa2.put("Feijão", 3);
        mapa2.put("Macarrão", 7);

        HashMap<String, Integer> mesclado = new HashMap<>(mapa1);

        for (String chave : mapa2.keySet()) {
            mesclado.put(chave, mesclado.getOrDefault(chave, 0) + mapa2.get(chave));
        }

        System.out.println("Mapa mesclado: " + mesclado);
    }
}
