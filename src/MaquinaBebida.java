import java.util.EnumMap;
import java.util.Map;

enum Dinheiro {
  cinquenta_centavos(0.5), um_real(1),
  dois_reais(2), cinco_reais(5), dez_reais(10);

  private final double valor;

  Dinheiro(double valor) {
    this.valor = valor;
  }

  public double getValor() {
    return this.valor;
  }
}

class MaquinaBebida {
  private Map<Bebida, Integer> estoque;

  private EnumMap<Dinheiro, Integer> saldo_troco = new EnumMap<Dinheiro, Integer>(Map.of(
      Dinheiro.cinquenta_centavos, 0,
      Dinheiro.um_real, 0,
      Dinheiro.dois_reais, 0,
      Dinheiro.cinco_reais, 0,
      Dinheiro.dez_reais, 0));

  double temp_total = 0;
  public double saldoTotal() {
    temp_total = 0;
    
    saldo_troco.forEach((dinheiro, quantidade) -> {
      temp_total += dinheiro.getValor() * quantidade;
    });

    return temp_total;
  }
  // @Todo
  // public double saldoTotal() {
    
  //   return saldo_troco.values().stream().mapToInt(Integer::valueOf).sum();
  // }

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

  public void inserirDinheiro(EnumMap<Dinheiro, Integer> novoValor) {
    novoValor.forEach((dinheiro, quantidade) -> {
      saldo_troco.put(dinheiro, saldo_troco.get(dinheiro) + quantidade);
    });
  }

  public void comprarBebida(Bebida bebida, double entrada) {
    throw new Error("Não implementado");
  }

}