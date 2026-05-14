import java.util.ArrayList;

//Remover elementos duplicados//

import java.util.ArrayList;

public class Exercicio2 {
    public static void main(String[] args) {
        ArrayList<String> contatos = new ArrayList<>();
        contatos.add("Ana");
        contatos.add("Maria");
        contatos.add("Ana");
        contatos.add("João");
        contatos.add("Maria");

        ArrayList<String> semDuplicados = new ArrayList<>();

        for (String c : contatos) {
            if (!semDuplicados.contains(c)) {
                semDuplicados.add(c);
            }
        }

        System.out.println("Lista sem duplicados: " + semDuplicados);
    }
} 


    

