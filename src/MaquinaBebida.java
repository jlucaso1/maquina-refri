import java.util.EnumMap;
import java.util.Map;

enum Dinheiro {
  CINQUENTA_CENTAVOS(0.5), UM_REAL(1),
  DOIS_REAIS(2), CINCO_REAIS(5), DEZ_REAIS(10);

  private final double valor;

  Dinheiro(double valor) {
    this.valor = valor;
  }

  public double getValor() {
    return this.valor;
  }
}

class MaquinaBebida {
  private EnumMap<Bebida, Integer> estoque = Util.BEBIDAS_VAZIAS();

  private EnumMap<Dinheiro, Integer> saldo_troco = Util.SALDO_VAZIO();

  public EnumMap<Bebida, Integer> getEstoque() {
    return this.estoque;
  }

  public EnumMap<Dinheiro, Integer> getSaldoTroco() {
    return this.saldo_troco;
  }

  public double saldoTotal(EnumMap<Dinheiro, Integer> saldoMap) {
    double saldo = 0;
    for (Map.Entry<Dinheiro, Integer> entrada : saldoMap.entrySet()) {
      saldo += entrada.getKey().getValor() * entrada.getValue();
    }
    return saldo;
  }

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

  private EnumMap<Dinheiro, Integer> calcularTroco(Bebida bebida, double entrada) {
    double saldoAtual = this.saldoTotal(saldo_troco);

    if (saldoAtual - entrada < 0) {
      throw new IllegalArgumentException("Troco insuficiente");
    }

    double troco = entrada - bebida.getPreco();

    System.out.println("Troco: " + troco);

    EnumMap<Dinheiro, Integer> trocoMap = new EnumMap<Dinheiro, Integer>(Util.SALDO_VAZIO());

    while (troco > 0) {
      boolean trocoEncontrado = false;
      for (Dinheiro dinheiro : Dinheiro.values()) {
        if (troco >= dinheiro.getValor() && saldo_troco.get(dinheiro) > 0) {
          troco -= dinheiro.getValor();
          trocoMap.put(dinheiro, trocoMap.get(dinheiro) + 1);
          saldo_troco.put(dinheiro, saldo_troco.get(dinheiro) - 1);
          trocoEncontrado = true;
        }
      }

      if (!trocoEncontrado) {
        throw new IllegalArgumentException("Troco insuficiente");
      }
    }

    System.out.println("TrocoMap: " + trocoMap);
    return trocoMap;
  }

  public EnumMap<Dinheiro, Integer> comprarBebida(Bebida bebida, double entrada) {
    if (estoque.get(bebida) == 0) {
      throw new IllegalArgumentException("Bebida indisponível");
    }

    if (bebida.getPreco() > entrada) {
      throw new IllegalArgumentException("Você não tem dinheiro suficiente");
    }

    EnumMap<Dinheiro, Integer> troco = calcularTroco(bebida, entrada);

    estoque.put(bebida, estoque.get(bebida) - 1);

    return troco;
  }

}