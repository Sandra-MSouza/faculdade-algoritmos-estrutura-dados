import java.util.Stack;

//Verificação de expressões (parênteses balanceados)//

public class Exercicio4 {
    public static boolean verificarExpressao(String exp) {
        Stack<Character> pilha = new Stack<>();

        for (char c : exp.toCharArray()) {
            if (c == '(') {
                pilha.push(c);
            } else if (c == ')') {
                if (pilha.isEmpty()) return false;
                pilha.pop();
            }
        }

        return pilha.isEmpty();
    }

    public static void main(String[] args) {
        String exp = "(2+3)*(5+(2-1))";
        System.out.println("Expressão balanceada? " + verificarExpressao(exp));
    }
}
