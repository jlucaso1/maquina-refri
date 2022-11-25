import java.util.EnumMap;
import java.util.Map;

public class App {

  public static void main(String[] args) {
    MaquinaBebida maquina = new MaquinaBebida();
    maquina.inserirDinheiro(new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.cinquenta_centavos, 1,
        Dinheiro.um_real, 5)));
    maquina.adicionarBebida(Bebida.GUARANA, 1);
    maquina.comprarBebida(Bebida.GUARANA, 4.5);

  }
}