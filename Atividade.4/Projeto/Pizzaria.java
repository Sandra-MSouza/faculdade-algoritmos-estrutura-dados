package Projeto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Projeto.Pizza.TamanhoPizza;

public class Pizzaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Cliente> listaClientes = new ArrayList<>();
        List<Pedido> listaPedidos = new ArrayList<>();

        // populando com um cliente e pedido de exemplo (opcional)
        // Cliente exemplo
        // listaClientes.add(new Cliente("Ana", "Rua A, 10", "99999-9999", "ana@mail.com"));

        boolean continuar = true;
        while (continuar) {
            System.out.println();
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Fazer um novo pedido");
            System.out.println("2 - Alterar um pedido");
            System.out.println("3 - Adicionar um cliente");
            System.out.println("4 - Gerar relatório de vendas");
            System.out.println("5 - Gerar lista de clientes");
            System.out.println("6 - Listar pedidos");
            System.out.println("9 - Sair");

            System.out.print("Opção: ");
            int opcao = -1;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }
            System.out.println();

            switch (opcao) {
                case 1:
                    fazerPedido(scanner, listaPedidos, listaClientes);
                    break;
                case 2:
                    alterarPedido(scanner, listaPedidos, listaClientes);
                    break;
                case 3:
                    listaClientes.add(adicionarCliente(scanner));
                    System.out.println("Cliente adicionado com sucesso!");
                    break;
                case 4:
                    gerarRelatorio(listaPedidos);
                    break;
                case 5:
                    gerarListaClientes(listaClientes);
                    break;
                case 6:
                    listarPedidos(listaPedidos);
                    break;
                case 9:
                    System.out.println("Até amanhã...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        scanner.close();
    }

    private static void fazerPedido(Scanner scanner, List<Pedido> listaPedidos, List<Cliente> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados. Cadastre um cliente primeiro.");
            return;
        }

        List<Pizza> pizzas = new ArrayList<>();
        System.out.println("FAZER PEDIDO");

        int x = 1;
        System.out.println("Selecione um cliente: ");
        for (Cliente cliente : listaClientes) {
            System.out.println(x+" - "+cliente.getNome());
            x++;
        }
        System.out.print("Opção: ");
        int clienteIndex = Integer.parseInt(scanner.nextLine());
        if (clienteIndex < 1 || clienteIndex > listaClientes.size()) {
            System.out.println("Cliente inválido.");
            return;
        }

        Cliente clienteEscolhido = listaClientes.get(clienteIndex-1);

        boolean continuar = true;
        while (continuar) {
            x = 1;
            System.out.println("Qual o tamanho da pizza? ");
            System.out.println("Selecione um tamanho: ");
            for (TamanhoPizza tamanhos : Pizza.TamanhoPizza.values()) {
                System.out.println(x+" - "+tamanhos);
                x++;
            }
            System.out.print("Opção: ");
            int tamanho = Integer.parseInt(scanner.nextLine());

            int quantiSabores = 0;
            while (quantiSabores < 1 || quantiSabores > 4) {
                System.out.println("Digite a quantidade de sabores: 1 - 4 ");
                System.out.print("Opção: ");
                quantiSabores = Integer.parseInt(scanner.nextLine());
            }

            Cardapio cardapio = new Cardapio();
            List<String> saboresList = new ArrayList<>();
            List<String> saboresSelect = new ArrayList<>();

            for (int i = 0; i < quantiSabores; i++) {
                System.out.println("Selecione um sabor: ");

                saboresList.clear(); // importante limpar antes de preencher novamente
                x = 1;
                for (String sabor : cardapio.getCardapio().keySet()) {
                    saboresList.add(sabor);
                    System.out.println(x+" - "+sabor);
                    x++;
                }
                System.out.print("Opção: ");
                int opcao = Integer.parseInt(scanner.nextLine());
                if (opcao < 1 || opcao > saboresList.size()) {
                    System.out.println("Opção inválida de sabor.");
                    i--; // refaz essa iteração
                    continue;
                }
                saboresSelect.add(saboresList.get(opcao-1));
            }

            Pizza pizza = new Pizza(saboresSelect, cardapio.getPrecoJusto(saboresSelect), TamanhoPizza.getByIndex(tamanho-1));
            pizzas.add(pizza);

            System.out.println("Pizza cadastrada com sucesso!");
            System.out.println();
            System.out.println("Deseja cadastrar mais uma pizza no pedido?");
            System.out.print("1 - Sim, 2 - Não: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            if(opcao != 1){
                continuar = false;
            }
        }

        Pedido pedido = new Pedido(listaPedidos.size()+1, clienteEscolhido, pizzas, 0.0);
        pedido.recalcularValorTotal();
        // pergunta distancia para cálculo de frete e exibir
        System.out.print("Digite a distância em km até o endereço de entrega (ex: 3.5): ");
        try {
            double km = Double.parseDouble(scanner.nextLine());
            pedido.setDistanciaKm(km);
        } catch (NumberFormatException e) {
            pedido.setDistanciaKm(0.0);
        }
        listaPedidos.add(pedido);

        System.out.println("Pedido criado com sucesso!");
        System.out.println(pedido);
        double frete = calcularFrete(pedido.getDistanciaKm(), pedido.getPizzas().size());
        System.out.printf("Frete calculado: R$ %.2f%n", frete);
    }

    private static double somarPizzas(List<Pizza> pizzas) {
        double valorTotal = 0;
        for (Pizza pizza : pizzas) {
            valorTotal += pizza.getPreco();
        }
        return valorTotal;
    }

    private static void alterarPedido(Scanner scanner, List<Pedido> listaPedidos, List<Cliente> listaClientes) {
        if (listaPedidos.isEmpty()) {
            System.out.println("Não há pedidos para alterar.");
            return;
        }

        System.out.println("ALTERAR PEDIDO");
        listarPedidos(listaPedidos);

        System.out.print("Digite o ID do pedido que deseja alterar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Pedido alvo = null;
        for (Pedido p : listaPedidos) {
            if (p.getId() == id) {
                alvo = p;
                break;
            }
        }
        if (alvo == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        boolean sairAlteracao = false;
        while (!sairAlteracao) {
            System.out.println("\nPedido selecionado:");
            System.out.println(alvo);
            System.out.println("\nO que deseja fazer?");
            System.out.println("1 - Adicionar uma pizza");
            System.out.println("2 - Remover uma pizza");
            System.out.println("3 - Alterar sabores de uma pizza");
            System.out.println("4 - Alterar cliente do pedido");
            System.out.println("5 - Alterar distância (para frete)");
            System.out.println("9 - Voltar ao menu principal");
            System.out.print("Opção: ");

            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case 1:
                    // adicionar pizza
                    Cardapio cardapio = new Cardapio();
                    List<String> saboresList = new ArrayList<>();
                    List<String> saboresSelect = new ArrayList<>();
                    System.out.println("Adicionando nova pizza ao pedido...");

                    System.out.print("Escolha tamanho (1-BROTO, 2-GRANDE, 3-GIGA): ");
                    int tam = Integer.parseInt(scanner.nextLine());

                    int quantiSabores = 0;
                    while (quantiSabores < 1 || quantiSabores > 4) {
                        System.out.println("Digite a quantidade de sabores: 1 - 4 ");
                        System.out.print("Opção: ");
                        quantiSabores = Integer.parseInt(scanner.nextLine());
                    }

                    for (int i = 0; i < quantiSabores; i++) {
                        System.out.println("Selecione um sabor: ");
                        saboresList.clear();
                        int x = 1;
                        for (String s : cardapio.getCardapio().keySet()) {
                            saboresList.add(s);
                            System.out.println(x + " - " + s);
                            x++;
                        }
                        System.out.print("Opção: ");
                        int escolha = Integer.parseInt(scanner.nextLine());
                        if (escolha < 1 || escolha > saboresList.size()) {
                            System.out.println("Sabor inválido. Tente novamente.");
                            i--;
                            continue;
                        }
                        saboresSelect.add(saboresList.get(escolha-1));
                    }

                    Pizza nova = new Pizza(saboresSelect, new Cardapio().getPrecoJusto(saboresSelect), TamanhoPizza.getByIndex(tam-1));
                    alvo.getPizzas().add(nova);
                    alvo.recalcularValorTotal();
                    System.out.println("Pizza adicionada com sucesso.");
                    break;

                case 2:
                    // remover pizza
                    if (alvo.getPizzas().isEmpty()) {
                        System.out.println("Pedido não contém pizzas.");
                        break;
                    }
                    System.out.println("Escolha o número da pizza a remover (1..): ");
                    for (int i = 0; i < alvo.getPizzas().size(); i++) {
                        System.out.println((i+1) + " - " + alvo.getPizzas().get(i));
                    }
                    System.out.print("Opção: ");
                    int rem = Integer.parseInt(scanner.nextLine());
                    if (rem < 1 || rem > alvo.getPizzas().size()) {
                        System.out.println("Opção inválida.");
                    } else {
                        alvo.getPizzas().remove(rem-1);
                        alvo.recalcularValorTotal();
                        System.out.println("Pizza removida.");
                    }
                    break;

                case 3:
                    // alterar sabores de uma pizza (substituir sabores)
                    if (alvo.getPizzas().isEmpty()) {
                        System.out.println("Pedido não contém pizzas.");
                        break;
                    }
                    System.out.println("Escolha a pizza para alterar (1..): ");
                    for (int i = 0; i < alvo.getPizzas().size(); i++) {
                        System.out.println((i+1) + " - " + alvo.getPizzas().get(i));
                    }
                    System.out.print("Opção: ");
                    int alt = Integer.parseInt(scanner.nextLine());
                    if (alt < 1 || alt > alvo.getPizzas().size()) {
                        System.out.println("Opção inválida.");
                    } else {
                        Pizza pAlt = alvo.getPizzas().get(alt-1);
                        Cardapio card = new Cardapio();
                        List<String> saboresNovos = new ArrayList<>();
                        int qSab = 0;
                        while (qSab < 1 || qSab > 4) {
                            System.out.println("Quantos sabores agora? (1-4): ");
                            qSab = Integer.parseInt(scanner.nextLine());
                        }
                        for (int i = 0; i < qSab; i++) {
                            System.out.println("Selecione um sabor: ");
                            List<String> saboresDisponiveis = new ArrayList<>(card.getCardapio().keySet());
                            for (int j = 0; j < saboresDisponiveis.size(); j++) {
                                System.out.println((j+1) + " - " + saboresDisponiveis.get(j));
                            }
                            System.out.print("Opção: ");
                            int escolha = Integer.parseInt(scanner.nextLine());
                            if (escolha < 1 || escolha > saboresDisponiveis.size()) {
                                System.out.println("Sabor inválido. Tente novamente.");
                                i--;
                                continue;
                            }
                            saboresNovos.add(saboresDisponiveis.get(escolha-1));
                        }
                        pAlt.setSabores(saboresNovos);
                        pAlt.setPreco(card.getPrecoJusto(saboresNovos));
                        alvo.recalcularValorTotal();
                        System.out.println("Sabores alterados com sucesso.");
                    }
                    break;

                case 4:
                    // alterar cliente do pedido
                    System.out.println("Escolha o novo cliente: ");
                    for (int i = 0; i < listaClientes.size(); i++) {
                        System.out.println((i+1) + " - " + listaClientes.get(i).getNome());
                    }
                    System.out.print("Opção: ");
                    int novoCli = Integer.parseInt(scanner.nextLine());
                    if (novoCli < 1 || novoCli > listaClientes.size()) {
                        System.out.println("Cliente inválido.");
                    } else {
                        alvo.setCliente(listaClientes.get(novoCli-1));
                        System.out.println("Cliente do pedido alterado.");
                    }
                    break;

                case 5:
                    System.out.print("Digite a nova distância em km: ");
                    try {
                        double d = Double.parseDouble(scanner.nextLine());
                        alvo.setDistanciaKm(d);
                        System.out.println("Distância atualizada.");
                        double frete = calcularFrete(alvo.getDistanciaKm(), alvo.getPizzas().size());
                        System.out.printf("Frete recalculado: R$ %.2f%n", frete);
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido.");
                    }
                    break;

                case 9:
                    sairAlteracao = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static Cliente adicionarCliente(Scanner scanner) {
        System.out.println("ADICIONAR CLIENTE");
        System.out.println();
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.println();
        System.out.print("Digite o endereço do cliente: ");
        String endereco = scanner.nextLine();
        System.out.println();
        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();
        System.out.println();
        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();
        System.out.println();

        Cliente cliente = new Cliente(nome, endereco, telefone, email);
        return cliente;
    }

    private static void gerarRelatorio(List<Pedido> listaPedidos) {
        System.out.println("GERAR RELATÓRIO");

        if (listaPedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }

        double faturamento = 0.0;
        int totalPizzas = 0;
        Map<String, Integer> saborContagem = new HashMap<>();
        Map<String, Map<String, Integer>> coOcorrencia = new HashMap<>();

        for (Pedido p : listaPedidos) {
            faturamento += p.getValorTotal();
            List<Pizza> pizzas = p.getPizzas();
            totalPizzas += pizzas.size();

            // contar sabores e co-ocorrência por pedido
            for (Pizza pizza : pizzas) {
                List<String> sabores = pizza.getSabores();
                for (String s : sabores) {
                    saborContagem.put(s, saborContagem.getOrDefault(s, 0) + 1);
                }

                // para cada par de sabores na mesma pizza, contar co-ocorrência
                for (int i = 0; i < sabores.size(); i++) {
                    for (int j = i + 1; j < sabores.size(); j++) {
                        String a = sabores.get(i);
                        String b = sabores.get(j);
                        coOcorrencia
                            .computeIfAbsent(a, k -> new HashMap<>())
                            .put(b, coOcorrencia.getOrDefault(a, Collections.emptyMap()).getOrDefault(b, 0) + 1);
                        coOcorrencia
                            .computeIfAbsent(b, k -> new HashMap<>())
                            .put(a, coOcorrencia.getOrDefault(b, Collections.emptyMap()).getOrDefault(a, 0) + 1);
                    }
                }
            }
        }

        System.out.printf("Faturamento total: R$ %.2f%n", faturamento);
        System.out.println("Total de pizzas vendidas: " + totalPizzas);

        // sabores mais pedidos (ordenados)
        List<Map.Entry<String, Integer>> saboresOrdenados = new ArrayList<>(saborContagem.entrySet());
        saboresOrdenados.sort((a,b) -> b.getValue().compareTo(a.getValue()));
        System.out.println("\nSabores mais pedidos:");
        for (Map.Entry<String,Integer> e : saboresOrdenados) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // mostrar co-ocorrências
        System.out.println("\nCo-ocorrências (sabores que aparecem juntos):");
        for (String a : coOcorrencia.keySet()) {
            for (Map.Entry<String,Integer> e : coOcorrencia.get(a).entrySet()) {
                System.out.println(a + " - " + e.getKey() + " : " + e.getValue());
            }
        }

        // export CSV com arestas do grafo
        String fileName = "grafo_sabores.csv";
        try {
            exportGraphCsv(coOcorrencia, fileName);
            System.out.println("\nGrafo exportado para arquivo: " + fileName + " (formato source,target,weight)");
        } catch (IOException e) {
            System.out.println("Erro ao exportar grafo: " + e.getMessage());
        }
    }

    private static void exportGraphCsv(Map<String, Map<String, Integer>> coOcorrencia, String filePath) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write("source,target,weight\n");
            Set<String> seen = new HashSet<>();
            for (String a : coOcorrencia.keySet()) {
                for (Map.Entry<String,Integer> e : coOcorrencia.get(a).entrySet()) {
                    String b = e.getKey();
                    int w = e.getValue();
                    String key1 = a + "|" + b;
                    String key2 = b + "|" + a;
                    if (seen.contains(key1) || seen.contains(key2)) continue;
                    fw.write(String.format("%s,%s,%d\n", a, b, w));
                    seen.add(key1);
                }
            }
        }
    }

    private static double calcularFrete(double distanciaKm, int qtdPizzas) {
        double base = 5.0;
        double perKm = 1.2;
        double perPizza = 2.5;

        double frete = base + distanciaKm * perKm + qtdPizzas * perPizza;

        if (distanciaKm > 20) {
            frete += 10.0;
        }
        if (qtdPizzas >= 10) {
            frete *= 0.9;
        }
        return Math.round(frete * 100.0) / 100.0;
    }

    private static void gerarListaClientes(List<Cliente> listaClientes) {
        int x = 1;
        if (listaClientes.isEmpty()) {
            System.out.println("Lista de clientes esta vazia");
        } else {
            for (Cliente cliente : listaClientes) {
                System.out.println("Cliente "+x);
                System.out.println(cliente.getNome());
                System.out.println(cliente.getEndereco());
                System.out.println(cliente.getTelefone());
                System.out.println(cliente.getEmail());
                System.out.println();
                x++;
            }
        }
    }

    private static void listarPedidos(List<Pedido> listaPedidos) {
        if (listaPedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }
        System.out.println("Pedidos cadastrados:");
        for (Pedido p : listaPedidos) {
            System.out.println(p);
        }
    }
}

    

