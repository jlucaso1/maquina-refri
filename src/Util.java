import java.util.EnumMap;
import java.util.Map;

public class Util {
  static final EnumMap<Dinheiro, Integer> SALDO_VAZIO() {
    return new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.CINQUENTA_CENTAVOS, 0,
        Dinheiro.UM_REAL, 0,
        Dinheiro.DOIS_REAIS, 0,
        Dinheiro.CINCO_REAIS, 0,
        Dinheiro.DEZ_REAIS, 0));
  }

  static final EnumMap<Bebida, Integer> BEBIDAS_VAZIAS() {
    return new EnumMap<Bebida, Integer>(Map.of(
        Bebida.AGUA, 0,
        Bebida.COCA, 0,
        Bebida.GUARANA, 0,
        Bebida.SUCO, 0));
  }
}
