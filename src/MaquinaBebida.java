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
  private EnumMap<Bebida, Integer> estoque = new EnumMap<Bebida, Integer>(Bebida.class);

  private EnumMap<Dinheiro, Integer> saldo_troco = Util.SALDO_VAZIO();

  public EnumMap<Dinheiro, Integer> saldoTroco() {
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