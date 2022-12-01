package maquinaRefri;

import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    MaquinaBebida maquina = new MaquinaBebida();
    maquina.inserirDinheiro(new EnumMap<Dinheiro, Integer>(Map.of(
        Dinheiro.CINQUENTA_CENTAVOS, 2,
        Dinheiro.UM_REAL, 5)));
    maquina.adicionarBebida(Bebida.GUARANA, 1);
    // maquina.comprarBebida(Bebida.GUARANA, 4.5);
    System.out.println("Bem vindo a maquina de bebidas!");
    while (true) {
      System.out.println("\nSelecione uma opcao:");
      System.out.println("1 - Cliente");
      System.out.println("2 - Administrador");
      System.out.println("3 - Sair");
      System.out.print("Selecione uma opção: ");
      int opcao = scanner.nextInt();
      if (opcao == 1) {
        cliente(maquina, scanner);
      } else if (opcao == 2) {
        administrador(maquina, scanner);
      } else if (opcao == 3) {
        System.out.println("Saindo...");
        break;
      }
    }

  }

  private static void cliente(MaquinaBebida maquina, Scanner scanner) {
    while (true) {
      System.out.println("\n--- Cliente ---");
      System.out.println("Selecione uma opcao:");
      System.out.println("1 - Comprar bebida");
      System.out.println("2 - Voltar");
      System.out.println("3 - Sair");
      System.out.print("Selecione uma opção: ");
      int opcaoCliente = scanner.nextInt();
      if (opcaoCliente == 1) {
        while (true) {
          System.out.println("\n--- Comprar bebida (Cliente) ---");
          System.out.println("Selecione uma bebida:");
          for (Bebida bebida : Bebida.values()) {
            System.out.println(bebida.ordinal() + 1 + " - " + bebida.getNome() + " (R$ " + bebida.getPreco() + ")");
          }
          System.out.println((Bebida.values().length + 1) + " - Voltar");
          System.out.print("Selecione uma opcao: ");
          int opcaoBebida = scanner.nextInt();
          Bebida bebida = null;

          try {
            bebida = Bebida.values()[opcaoBebida - 1];
          } catch (ArrayIndexOutOfBoundsException e) {
            if (opcaoBebida == Bebida.values().length + 1) {
              break;
            }
            System.out.println("Opcao invalida!");
            continue;
          }
          if (bebida != null) {
            System.out.print("Insira o dinheiro na maquina: ");
            double valor = scanner.nextDouble();
            try {
              maquina.comprarBebida(bebida, valor);
              System.out.println("Bebida comprada com sucesso!");
            } catch (Exception e) {
              System.out.println(e.getMessage());
            }
            break;
          }
        }
      } else if (opcaoCliente == 2) {
        break;
      } else if (opcaoCliente == 3) {
        System.out.println("Saindo...");
        System.exit(0);
      } else {
        System.out.println("Opção invalida");
      }
    }
  }

  private static void administrador(MaquinaBebida maquina, Scanner scanner) {
    while (true) {
      System.out.println("\n--- Administrador ---");
      System.out.println("Selecione uma opcao:");
      System.out.println("1 - Inserir dinheiro");
      System.out.println("2 - Adicionar bebida");
      System.out.println("3 - Retirar dinheiro");
      System.out.println("4 - Retirar bebida");
      System.out.println("5 - Voltar");
      System.out.println("6 - Sair");
      System.out.print("Selecione uma opcao:");
      int opcaoAdministrador = scanner.nextInt();
      if (opcaoAdministrador == 1) {
        while (true) {
          System.out.println("\n--- Inserir dinheiro (Administrador) ---");
          System.out.println("Selecione uma moeda:");
          for (Dinheiro dinheiro : Dinheiro.values()) {
            System.out.println(dinheiro.ordinal() + 1 + " - R$ " + dinheiro.getValor());
          }
          System.out.println((Dinheiro.values().length + 1) + " - Voltar");
          System.out.print("Selecione uma moeda: ");
          int opcaoMoeda = scanner.nextInt();
          Dinheiro dinheiro = null;
          try {
            dinheiro = Dinheiro.values()[opcaoMoeda - 1];
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Opcao invalida!");
            if (opcaoMoeda == Dinheiro.values().length + 1) {
              break;
            }
            continue;
          }
          if (dinheiro != null) {
            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            try {
              maquina.inserirDinheiro(new EnumMap<Dinheiro, Integer>(Map.of(
                  dinheiro, quantidade)));
              System.out.println("Dinheiro inserido com sucesso!");
            } catch (Exception e) {
              System.out.println(e.getMessage());
            }
            break;
          }
        }
      } else if (opcaoAdministrador == 2) {
        while (true) {
          System.out.println("\n--- Adicionar bebida (Administrador) ---");
          System.out.println("Selecione uma bebida:");
          for (Bebida bebida : Bebida.values()) {
            System.out.println(bebida.ordinal() + 1 + " - " + bebida.getNome() + " (R$ " + bebida.getPreco() + ")");
          }
          System.out.println((Bebida.values().length + 1) + " - Voltar");
          System.out.print("Selecione uma bebida: ");
          int opcaoBebida = scanner.nextInt();
          Bebida bebida = null;
          try {
            bebida = Bebida.values()[opcaoBebida - 1];
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Opcao invalida!");
            if (opcaoBebida == Bebida.values().length + 1) {
              break;
            }
            continue;
          }
          if (bebida != null) {
            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            try {
              maquina.adicionarBebida(bebida, quantidade);
              System.out.println(quantidade+" unidades de "+ bebida.getNome()+" adicionada com sucesso!");
            } catch (Exception e) {
              System.out.println(e.getMessage());
            }
            break;
          }
        }
      } else if (opcaoAdministrador == 3) {
      } else if (opcaoAdministrador == 4) {
      } else if (opcaoAdministrador == 5) {
        break;
      } else if (opcaoAdministrador == 6) {
        System.out.println("Saindo...");
        System.exit(0);
      } else {
        System.out.println("Opção invalida");
      }
    }
  }
}