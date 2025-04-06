package Menu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import Entidades.*;
import Arquivo.ArquivoEpisodios;
import Arquivo.ArquivoSeries;
import java.time.format.DateTimeFormatter;
import javax.sound.midi.Soundbank;


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
                case 1: incluirEpisodio();
                    break;
                case 2: buscarEpisodio();
                    break;
                case 3: alterarEpisodio();
                    break;
                case 4: excluirEpisodio();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public boolean incluirEpisodio() {
        System.out.println("\nInclusão de Episódio");
        String nome = new String();
        LocalDate dataLancamento = null;
        boolean dadosCorretos = false;
        int duracao;
        int temporada;
        float avaliacao;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            System.out.print("\nNome (min. de 4 letras ou vazio para cancelar): ");
            nome = console.nextLine();
            if(nome.length()==0)
                return false;
            if(nome.length()<4)
                System.err.println("O nome o Episódio deve ter no mínimo 4 caracteres.");
        } while(nome.length()<4);

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

        System.out.println("Duração do episódio (em minutos): ");
        duracao = console.nextInt();

        System.out.println("Digite qual a temporada em que o episódio está inserido: ");
        temporada = console.nextInt();

        System.out.println("Digite a avaliação do episódio: ");
        avaliacao = console.nextFloat();

    }
    }

    public boolean excluirEpisodio(int idEP) {
        
    }

    public boolean alterarEpisodio(int idEP) {

    }

    public Episodio buscarEpisodio (String nome, String nomeSerie){
        
}
