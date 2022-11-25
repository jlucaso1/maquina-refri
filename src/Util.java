import java.util.EnumMap;
import java.util.Map;

public class Util {
  static final EnumMap<Dinheiro, Integer> SALDO_VAZIO() {
    return new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.cinquenta_centavos, 0,
        Dinheiro.um_real, 0,
        Dinheiro.dois_reais, 0,
        Dinheiro.cinco_reais, 0,
        Dinheiro.dez_reais, 0));
  }
}
