package Menu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import Entidades.*;
import java.time.format.DateTimeFormatter;
import javax.sound.midi.Soundbank;
import Arquivo.*;


public class MenuEpisodio {
    ArquivoEpisodios arqEp;
    ArquivoSeries arqSeries;
    private static Scanner console = new Scanner(System.in);

    public MenuEpisodio() throws Exception {
        arqEp = new ArquivoEpisodios();
        arqSeries = new ArquivoSeries();
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("PUCFlix 1.0");
            System.out.println("----------");
            System.out.println("> Início > Episódios");
            System.out.println("\n1) Incluir");
            System.out.println("2) Buscar");
            System.out.println("3) Alterar");
            System.out.println("4) Excluir");
            System.out.println("0) Voltar ao menu anterior");

            System.out.print("\nOpção: ");
            try {
                opcao = Integer.valueOf(console.nextLine());
            } catch(NumberFormatException e) {
                opcao = -1;
            }
            switch(opcao) {
                case 1: 
                    incluirEpisodio();
                    break;
                case 2: 
                    buscarEpisodio();
                    break;
                case 3: 
                    alterarEpisodio();
                    break;
                case 4: 
                    excluirEpisodio();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public void incluirEpisodio() {
        System.out.println("\nInclusão de Serie");
        String nome = "";
        float duracao = 0;
        float avaliacao = 0;
        LocalDate dataLancamento = null;
        int temporada = 0;
        boolean dadosCorretos = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            System.out.print("\nNome (min. de 4 letras ou vazio para cancelar): ");
            nome = console.nextLine();
            if(nome.length()==0)
                return;
            if(nome.length()<4)
                System.err.println("O nome do Episodio deve ter no mínimo 4 caracteres.");
        } while(nome.length()<4);

        do {
            System.out.print("Duração: ");
            duracao = console.nextFloat();
            if(duracao == 0)
                System.err.println("a duração deve ser maior que 0.");
        } while(duracao == 0);

        do {
            System.out.print("Temporada: ");
            temporada = console.nextInt();
            if(temporada ==0)
                System.err.println("Por favor, informe a temporada do Episódio.");
        } while(temporada ==0);

        do {
            System.out.print("Avaliação: ");
            avaliacao = console.nextFloat();
            if(avaliacao==0)
                System.err.println("Por favor, informe a avaliação do Episódio.");
        } while(avaliacao==0);

        do {
            System.out.print("Data de lançamento (DD/MM/AAAA): ");
            String dataStr = console.nextLine();
            dadosCorretos = false;
            try {
                dataLancamento = LocalDate.parse(dataStr, formatter);
                dadosCorretos = true;
            } catch (Exception e) {
                System.err.println("Data inválida! Use o formato DD/MM/AAAA.");
            }
        } while(!dadosCorretos);

        System.out.print("\nConfirma a inclusão do Episódio? (S/N) ");
        char resp = console.nextLine().charAt(0);
        if(resp=='S' || resp=='s') {
            try {
                int idEpisodio = arqEp.getUltimoID(); // Atribui o ID da série ao episódio
                Episodio c = new Episodio(idEpisodio, nome, dataLancamento, avaliacao, duracao ,temporada);

                arqEp.create(c);
                System.out.println("Série incluída com sucesso.");
            } catch(Exception e) {
                System.out.println("Erro do sistema. Não foi possível incluir a Série!");
            }
        }
    }

    public void buscarEpisodio() {
        System.out.println("\nBusca de Série");
        String nome;
        boolean nomeValido = false;

        do {
            System.out.print("\nDigite o nome: ");
            nome = console.nextLine();  

            if(nome.isEmpty())
                return;
            else nomeValido =true;
        } while (!nomeValido);

        try {
            Episodio episodio = arqEp.read(nome);  
            if (episodio != null) {
                mostraEpisodio(episodio); 
            } else {
                System.out.println("Episodio não encontrado.");
            }
        } catch(Exception e) {
            System.out.println("Erro do sistema. Não foi possível buscar o Episodio!");
            e.printStackTrace();
        }
    } 



    public void alterarEpisodio() {
        System.out.println("\nAlteração do Episodio:");
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
            Episodio episodio = arqEp.read(nome);
            if (episodio != null) {
                System.out.println("Episodio encontrado:");
                mostraEpisodio(episodio);  

                System.out.print("\nNovo nome (deixe em branco para manter o anterior): ");
                String novoNome = console.nextLine();
                if (!novoNome.isEmpty()) {
                    episodio.nome = novoNome;  
                }


                System.out.print("Nova duração (deixe em branco para manter o anterior): ");
                float novaDuracao = console.nextFloat();
                if(novaDuracao > 0) {
                    episodio.duracao = novaDuracao;  
                }
                 System.out.print("Nova temporada (deixe em branco para manter o anterior): ");
                 int novoTemporada = console.nextInt();
                 if (novaDuracao > 0) {
                     episodio.temporada = novoTemporada;  
                 }

                 System.out.print("Nova avaliacao (deixe em branco para manter o anterior): ");
                 float novaAvaliacao = console.nextFloat();
                 if (novaDuracao > 0) {
                     episodio.avaliacao = novaAvaliacao;  
                 }

                System.out.print("Nova data de lançamento (DD/MM/AAAA) (deixe em branco para manter a anterior): ");
                String novaDataStr = console.nextLine();
                if (!novaDataStr.isEmpty()) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        episodio.dataLancamento = LocalDate.parse(novaDataStr, formatter);  // Atualiza a data de lancamento se fornecida
                    } catch (Exception e) {
                        System.err.println("Data inválida. Valor mantido.");
                    }
                }

                // Confirmação da alteração
                System.out.print("\nConfirma as alterações? (S/N) ");
                char resp = console.next().charAt(0);
                if (resp == 'S' || resp == 's') {
                    // Salva as alterações no arquivo
                    boolean alterado = arqSeries.update(episodio);
                    if (alterado) {
                        System.out.println("Episodio alterado com sucesso.");
                    } else {
                        System.out.println("Erro ao alterar o Episodio.");
                    }
                } else {
                    System.out.println("Alterações canceladas.");
                }
            } else {
                System.out.println("Episodio não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro do sistema. Não foi possível alterar o Episodio!");
            e.printStackTrace();
        }
        
    }
     
    public void mostraEpisodio(Episodio Episodio) {
        if (Episodio != null) {
            System.out.println("\nDetalhes do Episodio:");
            System.out.println("----------------------");
            System.out.printf("Nome......: %s%n", Episodio.nome);
            System.out.printf("Duração.......: %s%n", Episodio.duracao);
            System.out.printf("Temporada.......: %s%n", Episodio.temporada);
            System.out.printf("Lançamento: %s%n", Episodio.dataLancamento);
            System.out.printf("Avaliação: %s%n", Episodio.avaliacao);
            System.out.printf("ID da Série: %s%n", Episodio.idSerie);
            System.out.println("----------------------");
        }
    }
}
