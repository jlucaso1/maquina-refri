import java.util.EnumMap;
import java.util.Map;

public class App {

  public static void main(String[] args) {
    MaquinaBebida maquina = new MaquinaBebida();
    maquina.inserirDinheiro(new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.cinquenta_centavos, 20,Dinheiro.dez_reais, 1)));

    System.out.println(maquina.saldoTotal());

    // Bebida cocacola = new Bebida("Coca-Cola", 5.0);

    // maquina.adicionarBebida(cocacola, 20);

  }
}