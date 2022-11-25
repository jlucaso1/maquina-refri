import java.util.EnumMap;
import java.util.Map;

public class App {

  public static void main(String[] args) {
    MaquinaBebida maquina = new MaquinaBebida();
    maquina.inserirDinheiro(new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.CINQUENTA_CENTAVOS, 1,
        Dinheiro.UM_REAL, 5)));
    maquina.adicionarBebida(Bebida.GUARANA, 1);
    maquina.comprarBebida(Bebida.GUARANA, 4.5);

  }
}