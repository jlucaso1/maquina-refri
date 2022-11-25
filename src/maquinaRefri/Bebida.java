package maquinaRefri;

public enum Bebida {
  AGUA(2.0), COCA(3.0), GUARANA(3.0), SUCO(2.5);

  private double preco;

  Bebida(double preco) {
    this.preco = preco;
  }

  public double getPreco() {
    return preco;
  }

  public String getNome() {
    return this.name();
  }

  @Override
  public String toString() {
    return this.name() + " (" + this.preco + ")";
  }
}
