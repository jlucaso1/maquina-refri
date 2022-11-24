public class App {

  public static void main(String[] args) {
    MaquinaBebida maquina = new MaquinaBebida();

    Bebida cocacola = new Bebida("Coca-Cola", 5.0);

    maquina.adicionarBebida(cocacola, 20);

  }
}