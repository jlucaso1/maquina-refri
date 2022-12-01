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
          maquina.getEstoque().forEach((k, v) -> {
            System.out.println(k.ordinal() + 1 + " - " + k.getNome() + " (R$ " + k.getPreco() + ")");
          });
          System.out.println((maquina.getEstoque().size()+1)+" - Voltar");
          System.out.print("Selecione uma opcao: ");
          int opcaoBebida = scanner.nextInt();
          Bebida bebida = null;

          if (opcaoBebida == 1) {
            bebida = Bebida.AGUA;
          } else if (opcaoBebida == 2) {
            bebida = Bebida.COCA;
          } else if (opcaoBebida == 3) {
            bebida = Bebida.GUARANA;
          } else if (opcaoBebida == 4) {
            bebida = Bebida.FANTA;
          } else if (opcaoBebida == 5) {
            bebida = Bebida.SUCO;
          } else if (opcaoBebida == 6) {
            break;
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
        System.out.println("\n--- Inserir dinheiro (Administrador) ---");
        System.out.println("Selecione uma moeda:");
        System.out.println("1 - 50 centavos");
        System.out.println("2 - 1 real");
        System.out.println("3 - 2 reais");
        System.out.println("4 - 5 reais");
        System.out.println("5 - 10 reais");
        System.out.print("Selecione uma moeda: ");
        int opcaoMoeda = scanner.nextInt();
        Dinheiro dinheiro = null;
        switch (opcaoMoeda) {
          case 1:
            dinheiro = Dinheiro.CINQUENTA_CENTAVOS;
            break;
          case 2:
            dinheiro = Dinheiro.UM_REAL;
            break;
          case 3:
            dinheiro = Dinheiro.DOIS_REAIS;
            break;
          case 4:
            dinheiro = Dinheiro.CINCO_REAIS;
            break;
          case 5:
            dinheiro = Dinheiro.DEZ_REAIS;
            break;
          default:
            System.out.println("Opcao invalida");
            break;
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
        }
      } else if (opcaoAdministrador == 2) {
      }
    }
  }
}