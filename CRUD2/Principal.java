import java.util.Scanner;


public class Principal {

public static void main(String[] args) {

    Scanner console;

    try {
        console = new Scanner(System.in);
        int opcao;
        int opcao2;
        do {

            System.out.println("\n\nPUCFlix");
            System.out.println("-------");
            System.out.println("> Início");
            System.out.println("\n1 - Séries");
            System.out.println("2 - Episódios");
            System.out.println("3 - Atores");
            System.out.println("0 - Sair");

            System.out.print("\nOpção: ");
            try {
                opcao = Integer.valueOf(console.nextLine());
            } catch(NumberFormatException e) {
                opcao = -1;
            }
            switch (opcao) {
                case 1:
                    do{
                        System.out.println("\n\nPUCFlix");
                        System.out.println("-------");
                        System.out.println("> Início > Séries");
                        System.out.println("\n1 - Incluir");
                        System.out.println("2 - Buscar");
                        System.out.println("3 - Alterar");
                        System.out.println("4 - Excluir");
                        System.out.println("0 - Retonar ao menu anterior");
                        switch(opcao2){
                            case 1:

                            break;
                            case 2:

                            break;
                            case 3:

                            break;
                            case 4:

                            break;
                            default:
                                System.out.println("Opção inválida!");
                            break;
                        } 
                    }while(opcao2 != 0);   
                break;
                case 2:
                    do{
                        System.out.println("\n\nPUCFlix");
                        System.out.println("-------");
                        System.out.println("> Início > Episódios");
                        System.out.println("\n1 - Incluir");
                        System.out.println("2 - Buscar");
                        System.out.println("3 - Alterar");
                        System.out.println("4 - Excluir");
                        System.out.println("0 - Retonar ao menu anterior");
                        switch(opcao2){
                            case 1:

                            break;
                            case 2:

                            break;
                            case 3:

                            break;
                            case 4:

                            break;
                            default:
                                System.out.println("Opção inválida!");
                            break;
                        }
                    }while(opcao2 != 0);
                break;
                case 3:
                    do{
                        System.out.println("\n\nPUCFlix");
                        System.out.println("-------");
                        System.out.println("> Início > Atores");
                        System.out.println("\n1 - Incluir");
                        System.out.println("2 - Buscar");
                        System.out.println("3 - Alterar");
                        System.out.println("4 - Excluir");
                        System.out.println("0 - Retonar ao menu anterior");
                        switch(opcao2){
                            case 1:

                            break;
                            case 2:

                            break;
                            case 3:

                            break;
                            case 4:

                            break;
                            default:
                                System.out.println("Opção inválida!");
                            break;
                        }
                    }while(opcao2 != 0);
                break;
                case 0:
                break;
                
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);



    } catch(Exception e) {
        e.printStackTrace();
    }

}

}