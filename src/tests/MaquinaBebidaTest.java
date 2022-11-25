package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import maquinaRefri.Bebida;
import maquinaRefri.Dinheiro;
import maquinaRefri.MaquinaBebida;
import maquinaRefri.Util;

public class MaquinaBebidaTest {

  @Test
  public void adicionarBebida() {
    MaquinaBebida maquina = new MaquinaBebida();
    maquina.adicionarBebida(Bebida.COCA, 1);
    assertEquals(1, maquina.getEstoque().get(Bebida.COCA));
  }

  @Test
  public void inserirDinheiro() {
    MaquinaBebida maquina = new MaquinaBebida();
    maquina.inserirDinheiro(Util.SALDO_VAZIO());
    assertEquals(0, maquina.getSaldoTroco().get(Dinheiro.CINQUENTA_CENTAVOS));
    assertEquals(0, maquina.getSaldoTroco().get(Dinheiro.UM_REAL));
    assertEquals(0, maquina.getSaldoTroco().get(Dinheiro.DOIS_REAIS));
    assertEquals(0, maquina.getSaldoTroco().get(Dinheiro.CINCO_REAIS));
    assertEquals(0, maquina.getSaldoTroco().get(Dinheiro.DEZ_REAIS));

    maquina.inserirDinheiro(new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.CINQUENTA_CENTAVOS, 1,
        Dinheiro.UM_REAL, 5)));

    assertEquals(1, maquina.getSaldoTroco().get(Dinheiro.CINQUENTA_CENTAVOS));
    assertEquals(5, maquina.getSaldoTroco().get(Dinheiro.UM_REAL));
  }

  @Test
  public void comprarBebida() {
    MaquinaBebida maquina = new MaquinaBebida();
    maquina.inserirDinheiro(new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.CINQUENTA_CENTAVOS, 1,
        Dinheiro.UM_REAL, 5)));
    maquina.adicionarBebida(Bebida.GUARANA, 1);
    maquina.comprarBebida(Bebida.GUARANA, 4.5);

    assertEquals(0, maquina.getEstoque().get(Bebida.GUARANA));
    assertEquals(0, maquina.getSaldoTroco().get(Dinheiro.CINQUENTA_CENTAVOS));
    assertEquals(4, maquina.getSaldoTroco().get(Dinheiro.UM_REAL));
  }

  @Test
  public void comprarBebidaSemSaldo() {
    MaquinaBebida maquina = new MaquinaBebida();
    maquina.inserirDinheiro(new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.CINQUENTA_CENTAVOS, 1)));
    maquina.adicionarBebida(Bebida.GUARANA, 1);
    assertThrows(IllegalArgumentException.class, () -> maquina.comprarBebida(Bebida.GUARANA, 4.5));
  }

  @Test
  public void comprarBebidaSemEstoque() {
    MaquinaBebida maquina = new MaquinaBebida();
    maquina.inserirDinheiro(new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.CINQUENTA_CENTAVOS, 1,
        Dinheiro.UM_REAL, 5)));
    assertThrows(IllegalArgumentException.class, () -> maquina.comprarBebida(Bebida.GUARANA, 4.5));
  }

  @Test
  public void comprarBebidaSemTroco() {
    MaquinaBebida maquina = new MaquinaBebida();
    maquina.inserirDinheiro(new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.CINQUENTA_CENTAVOS, 1,
        Dinheiro.UM_REAL, 5)));
    maquina.adicionarBebida(Bebida.GUARANA, 1);
    assertThrows(IllegalArgumentException.class, () -> maquina.comprarBebida(Bebida.GUARANA, 4.6));
  }

}
