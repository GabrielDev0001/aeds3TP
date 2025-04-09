package Menu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuSeries {
    
    ArquivoSerie arqSeries;
    private static Scanner console = new Scanner(System.in);

    public MenuSeries() throws Exception {
        arqSeries = new ArquivoSerie();
    }

    public void menu() {

        int opcao;
        do {
            System.out.println("PUCFlix 1.0");
            System.out.println("-------");
            System.out.println("> Início > Séries");
            System.out.println("\n1 - Buscar");
            System.out.println("2 - Incluir");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar ao menu anterior");

            System.out.print("\nOpção: ");
            try {
                opcao = Integer.valueOf(console.nextLine());
            } catch(NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    buscarSerie();
                    break;
                case 2:
                    incluirSerie();
                    break;
                case 3:
                    alterarSerie();
                    break;
                case 4:
                    excluirSerie();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);
    }


    public void buscarSerie() {
        System.out.println("\nBusca de Série");
        String nome;
        boolean nomeValido = false;

        do {
            System.out.print("\nNome: ");
            nome = console.nextLine();  

            if(nome.isEmpty())
                return;
            else nomeValido =true;
        } while (!nomeValido);

        try {
            Serie Serie = arqSeries.read(nome);  
            if (Serie != null) {
                mostraSerie(Serie);  
            } else {
                System.out.println("Serie não encontrado.");
            }
        } catch(Exception e) {
            System.out.println("Erro do sistema. Não foi possível buscar a Serie!");
            e.printStackTrace();
        }
    }   


    public void incluirSerie() {
        System.out.println("\nInclusão de Serie");
        String nome = "";
        String sinopse = "";
        LocalDate dataNascimento = null;
        String stream = "";
        boolean dadosCorretos = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            System.out.print("\nNome (min. de 4 letras ou vazio para cancelar): ");
            nome = console.nextLine();
            if(nome.length()==0)
                return;
            if(nome.length()<4)
                System.err.println("O nome da Serie deve ter no mínimo 4 caracteres.");
        } while(nome.length()<4);

        do {
            System.out.print("Sinopse: ");
            sinopse = console.nextLine();
            if(sinopse.length()!=0)
                System.err.println("a sinopse deve conter pelo menos um caracter.");
        } while(sinopse.length()!=0);

        do {
            System.out.print("Stream: ");
            stream = console.nextLine();
            if(stream.length()!=0)
                System.err.println("Porfavor, informe onde se encontra a série.");
        } while(stream.length()!=0);

        do {
            System.out.print("Data de lançamento (DD/MM/AAAA): ");
            String dataStr = console.nextLine();
            dadosCorretos = false;
            try {
                dataNascimento = LocalDate.parse(dataStr, formatter);
                dadosCorretos = true;
            } catch (Exception e) {
                System.err.println("Data inválida! Use o formato DD/MM/AAAA.");
            }
        } while(!dadosCorretos);

        System.out.print("\nConfirma a inclusão da Serie? (S/N) ");
        char resp = console.nextLine().charAt(0);
        if(resp=='S' || resp=='s') {
            try {
                Serie c = new Serie(nome, sinopse, dataNascimento, stream);
                arqSeries.create(c);
                System.out.println("Série incluída com sucesso.");
            } catch(Exception e) {
                System.out.println("Erro do sistema. Não foi possível incluir a Série!");
            }
        }
    }

    public void alterarSerie() {
        System.out.println("\nAlteração de Série");
        String nome;
        boolean nomeValido = false;

        do {
            System.out.print("\nnome: ");
            nome = console.nextLine();  

            if(nome.isEmpty())
                return; 
            else {
                nomeValido = true;
            }
        } while (!nomeValido);


        try {
            Serie Serie = arqSeries.read(nome);
            if (Serie != null) {
                System.out.println("Serie encontrado:");
                mostraSerie(Serie);  

                System.out.print("\nNovo nome (deixe em branco para manter o anterior): ");
                String novoNome = console.nextLine();
                if (!novoNome.isEmpty()) {
                    Serie.nome = novoNome;  
                }


                System.out.print("Nova sinopse (deixe em branco para manter o anterior): ");
                String novoSinopse = console.nextLine();
                if (!novoSinopse.isEmpty()) {
                    Serie.sinopse = novoSinopse;  
                }


                 System.out.print("Novo stream (deixe em branco para manter o anterior): ");
                 String novoStream = console.nextLine();
                 if (!novoStream.isEmpty()) {
                     Serie.stream = novoStream;  
                 }

                // Alteração de data de lancamento
                System.out.print("Nova data de lançamento (DD/MM/AAAA) (deixe em branco para manter a anterior): ");
                String novaDataStr = console.nextLine();
                if (!novaDataStr.isEmpty()) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        Serie.lancamento = LocalDate.parse(novaDataStr, formatter);  // Atualiza a data de lancamento se fornecida
                    } catch (Exception e) {
                        System.err.println("Data inválida. Valor mantido.");
                    }
                }

                // Confirmação da alteração
                System.out.print("\nConfirma as alterações? (S/N) ");
                char resp = console.next().charAt(0);
                if (resp == 'S' || resp == 's') {
                    // Salva as alterações no arquivo
                    boolean alterado = arqSeries.update(Serie);
                    if (alterado) {
                        System.out.println("Serie alterada com sucesso.");
                    } else {
                        System.out.println("Erro ao alterar a Serie.");
                    }
                } else {
                    System.out.println("Alterações canceladas.");
                }
            } else {
                System.out.println("Serie não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro do sistema. Não foi possível alterar a Serie!");
            e.printStackTrace();
        }
        
    }


    public void excluirSerie() {
        System.out.println("\nExclusão de Serie");
        String nome;
        boolean nomeValido = false;

        do {
            System.out.print("\nnome: ");
            nome = console.nextLine();  

            if(nome.isEmpty())
            return; 
            else {
                nomeValido = true;
            }
        } while (!nomeValido);

        try {
            Serie Serie = arqSeries.read(nome);
            if (Serie != null) {
                System.out.println("Serie encontrada:");

                System.out.print("\nConfirma a exclusão do Serie? (S/N) ");

                if (resp == 'S' || resp == 's') {
                    boolean excluido = arqSeries.delete(nome);  
                    if (excluido) {
                        System.out.println("Serie excluída com sucesso.");
                    } else {
                        System.out.println("Erro ao excluir a Serie.");
                    }
                } else {
                    System.out.println("Exclusão cancelada.");
                }
            } else {
                System.out.println("Serie não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro do sistema. Não foi possível excluir a Serie!");
            e.printStackTrace();
        }
    }


    public void mostraSerie(Serie Serie) {
    if (Serie != null) {
        System.out.println("\nDetalhes da Serie:");
        System.out.println("----------------------");
        System.out.printf("Nome......: %s%n", Serie.nome);
        System.out.printf("Sinopse.......: %s%n", Serie.sinopse);
        System.out.printf("Stream.......: %s%n", Serie.stream);
        System.out.printf("Lançamento: %s%n", Serie.lancamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("----------------------");
    }
}
}
