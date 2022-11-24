import java.util.Map;

class MaquinaBebida {
  private Map<Bebida, Integer> estoque;

  private double saldo = 0;

  public void adicionarBebida(Bebida bebida, int quantidade) {
    if (quantidade <= 0) {
      throw new IllegalArgumentException("Quantidade deve ser maior que zero");
    }

    if (estoque.containsKey(bebida)) {
      estoque.put(bebida, estoque.get(bebida) + quantidade);
    } else {
      estoque.put(bebida, quantidade);
    }
  }

  public void inserirDinheiro(double valor) {
    saldo += valor;
  }

  public void comprarBebida(Bebida bebida) {
    if (saldo >= bebida.getPreco()) {
      if (estoque.containsKey(bebida)) {
        if (estoque.get(bebida) > 0) {
          saldo -= bebida.getPreco();
          estoque.put(bebida, estoque.get(bebida) - 1);
        } else {
          System.out.println("Estoque esgotado");
        }
      } else {
        System.out.println("Bebida não existe");
      }
    } else {
      System.out.println("Saldo insuficiente");
    }
  }

}