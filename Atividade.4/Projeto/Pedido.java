package Projeto;

import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Pizza> pizzas;
    private double valorTotal;
    private double distanciaKm; // adicionei para possibilitar cálculo de frete por pedido

    public Pedido(int id, Cliente cliente, List<Pizza> pizzas, double valorTotal){
        this.id = id;
        this.cliente = cliente;
        this.pizzas = pizzas;
        this.valorTotal = valorTotal;
        this.distanciaKm = 0.0;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
        recalcularValorTotal();
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public void recalcularValorTotal() {
        double soma = 0;
        if (pizzas != null) {
            for (Pizza p : pizzas) {
                soma += p.getPreco();
            }
        }
        this.valorTotal = soma;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{id=").append(id)
          .append(", cliente=").append(cliente.getNome())
          .append(", endereco=").append(cliente.getEndereco())
          .append(", distanciaKm=").append(String.format("%.2f", distanciaKm))
          .append(", valorTotal=").append(String.format("%.2f", valorTotal))
          .append(", pizzas=[\n");
        if (pizzas != null) {
            for (int i = 0; i < pizzas.size(); i++) {
                sb.append("  ").append(i+1).append(" - ").append(pizzas.get(i)).append("\n");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
