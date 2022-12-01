package maquinaRefri;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MaquinaBebida {
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  

  private EnumMap<Bebida, Integer> estoque = Util.BEBIDAS_VAZIAS();

  private EnumMap<Dinheiro, Integer> saldo_troco = Util.SALDO_VAZIO();

  private double dinheiro_recebido = 0;

  private EnumMap<Bebida, Integer> produtos_vendidos = Util.BEBIDAS_VAZIAS();
  private List<String> historico = new ArrayList<String>();

  public EnumMap<Bebida, Integer> getEstoque() {
    return this.estoque;
  }

  public EnumMap<Bebida, Integer> getBebidasVendidas() {
    return this.produtos_vendidos;
  }

  public String[] getHistorico() {
    return this.historico.toArray(new String[0]);
  }

  public EnumMap<Dinheiro, Integer> getSaldoTroco() {
    return this.saldo_troco;
  }

  public double getDinheiroRecebido() {
    return this.dinheiro_recebido;
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
    historico.add("Adicionado " + quantidade + " de " + bebida.getNome() + " ao estoque ["+ dtf.format(LocalDateTime.now()) + "]");
  }

  public void inserirDinheiro(EnumMap<Dinheiro, Integer> novoValor) {
    novoValor.forEach((dinheiro, quantidade) -> {
      saldo_troco.put(dinheiro, saldo_troco.get(dinheiro) + quantidade);
      historico.add("Inserido " + quantidade + " de R$ " + dinheiro.getValor() + " aos trocos ["+ dtf.format(LocalDateTime.now()) + "]");
    });
  }

  private EnumMap<Dinheiro, Integer> calcularTroco(Bebida bebida, double entrada) {
    double saldoAtual = this.saldoTotal(saldo_troco);

    if (saldoAtual - entrada < 0) {
      throw new IllegalArgumentException("Troco insuficiente, devolvendo dinheiro");
    }

    double troco = entrada - bebida.getPreco();

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
        throw new IllegalArgumentException("Troco insuficiente, devolvendo dinheiro");
      }
    }

    return trocoMap;
  }

  public EnumMap<Dinheiro, Integer> comprarBebida(Bebida bebida, double entrada) {
    if (estoque.get(bebida) == 0) {
      throw new IllegalArgumentException("Bebida indisponível, devolvendo dinheiro");
    }

    if (bebida.getPreco() > entrada) {
      throw new IllegalArgumentException("Você não tem dinheiro suficiente, devolvendo dinheiro");
    }

    EnumMap<Dinheiro, Integer> troco = calcularTroco(bebida, entrada);
    estoque.put(bebida, estoque.get(bebida) - 1);
    dinheiro_recebido += bebida.getPreco();
    produtos_vendidos.put(bebida, produtos_vendidos.get(bebida) + 1);
    historico.add("Vendido " + bebida.getNome() + " por R$ " + bebida.getPreco() + " com troco de R$ " + saldoTotal(troco) + " ["+ dtf.format(LocalDateTime.now()) + "]");

    return troco;
  }

}