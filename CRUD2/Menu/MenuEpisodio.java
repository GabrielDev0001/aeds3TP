package Menu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import Entidades.*;
import java.time.format.DateTimeFormatter;
import javax.sound.midi.Soundbank;


public class MenuEpisodio {
    ArquivoCliente arqClientes;
    private static Scanner console = new Scanner(System.in);

    public MenuClientes() throws Exception {
        arqClientes = new ArquivoCliente();
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
                case 2: System.out.println("Digite o nome da série: "); 
                    String nomeSerie = console.nextLine();
                    System.out.println("Digite o nome do episódio: ");
                    String nomeEpisodio = console.nextLine();
                    buscarEpisodio(nomeEpisodio, nomeSerie);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public void incluirEpisodio() {
        System.out.println("Incluir episódio: ");
        String nomeEP = new String();
        int temp = 0;
        LocalDate dataLancamento = LocalDate.now();
        int duracao = 0;
        float avaliacao = 0F;
        String sinopse = new String();
        int idSerie = 0;

        boolean dadosCorretos = false;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do { 
            System.out.println("Em que série você deseja inserir o episódio?");
        } while (true);
         

    }

    public boolean excluirEpisodio(int idEP) {
        
    }

    public boolean alterarEpisodio(int idEP) {

    }

    public Episodio buscarEpisodio (String nome, String nomeSerie){

    }
}
