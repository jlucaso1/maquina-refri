package maquinaRefri;

public enum Dinheiro {
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
