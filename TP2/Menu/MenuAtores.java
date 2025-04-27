package Menu;

import java.time.LocalDate;
import java.util.Scanner;
import Entidades.*;
import java.time.format.DateTimeFormatter;
import Arquivo.*;

public class MenuAtores {
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
        System.out.println("\nInclusão de Episodio");
        String nome = "";
        float duracao = 0;
        float avaliacao = 0;
        LocalDate dataLancamento = null;
        int temporada = 0;
        int IDserie = 0;
        boolean dadosCorretos = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //Descobre a serie que o usuario vai utilizar
        System.out.println("\nBusca de Série");
        String descobre;
        boolean nomeserieValido = false;
     
        do {
            System.out.print("\nDigite o nome: ");
            descobre = console.nextLine();  
     
            if(descobre.isEmpty())
                return;
            else nomeserieValido = true;
        } while (!nomeserieValido);
     
        try {
            Series[] s = arqSeries.readNome(descobre);
            for (int i = 0; i < s.length; i++) {
                System.out.println(i + " " + s[i].getNome());
            }
            System.out.println("Digite o numero: ");
            int numSerie = console.nextInt();
            IDserie = s[numSerie].getId();
        }catch (Exception e) {
            System.out.println("Erro ao buscar a série: " + e.getMessage());
        }
     

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

                Episodio c = new Episodio(nome, dataLancamento, avaliacao, duracao ,temporada, IDserie);

                arqEp.create(c);
                System.out.println("Série incluída com sucesso.");
            } catch(Exception e) {
                System.out.println("Erro do sistema. Não foi possível incluir a Série!");
            }
        }
    }

    public void excluirEpisodio() {
        System.out.println("\nExclusão do Episodio");
      //  boolean nomeValido = false;
        System.out.print("\nDigite o nome da série: ");

        String nome = console.nextLine();

        try {
            Series[] s = arqSeries.readNome(nome);
            for (int i = 0; i < s.length; i++) {
                System.out.println(i + " " + s[i].getNome());
            }   
            int numSerie = console.nextInt();
            Episodio[] e = arqEp.readEpisodiosSerie(s[numSerie].getId());

            for (int i = 0; i < e.length; i++) {
                System.out.println(i + " " + e[i].getNome());
            }

            int numEp = console.nextInt();

            boolean excluido = arqEp.delete(e[numEp].getId());
            if (excluido) {
                System.out.println("Exclusão efetuada com sucesso!");
            } else {
                System.out.println("Exclusão não efetuada.");
            }
        } 
        catch (Exception e) {
            System.out.println("Não foi possivel abrir o arquivo de series");
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
            else nomeValido = true;
        } while (!nomeValido);

        try {
            Series[] s = arqSeries.readNome(nome);
            for (int i = 0; i < s.length; i++) {
                System.out.println(i + " " + s[i].getNome());
            }
            System.out.println("Digite o numero: ");
            int numSerie = console.nextInt();
            Episodio[] e = arqEp.readEpisodiosSerie(s[numSerie].getId());
            System.out.println("Digite o nome do episodio: ");
            String nomeEp = console.nextLine();
            boolean resp = false;
            for(int i = 0; i < e.length; i++) {
                if (e[i].getNome().equals(nomeEp)) {
                    System.out.println("Episodio encontrado!");
                    resp = true;
                    break;
                }
            }

            if(!resp) {
                System.out.println("Ep nao encontrado");
            }
    


        } catch(Exception e) {
            System.out.println("Erro do sistema. Não foi possível buscar o Episodio!");
            e.printStackTrace();
        }
    } 



    public void alterarEpisodio() {
        System.out.println("\nAlteração do Episodio");
        String nome;
        boolean nomeValido = false;

        do {
            System.out.print("\nnome da série: ");
            nome = console.nextLine();  

            if(nome.isEmpty())
                return; 
            else {
                nomeValido = true;
            }
        } while (!nomeValido);


        try {
            Series[] s = arqSeries.readNome(nome);
            for (int i = 0; i < s.length; i++) {
                System.out.println(i + " " + s[i].getNome());
            }
        //    int numSerie = console.nextInt();
            


        } catch (Exception e) {
            System.out.println("Erro do sistema. Não foi possível alterar o Episodio!");
            e.printStackTrace();
        }
        
    }
     
    public void mostraAtores(Ator Ator) {
        if (Ator != null) {
            System.out.println("\nDetalhes do Ator:");
            System.out.println("----------------------");
            System.out.printf("Nome......: %s%n", Ator.nome);
            System.out.printf("ID do Ator: %s%n", Ator.idAtor);
            //Implementar chave estrangeira

            //
            System.out.println("----------------------");
        }
    }

}
