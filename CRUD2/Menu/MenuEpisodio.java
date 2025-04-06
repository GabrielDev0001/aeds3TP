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

    public MenuClientes() throws Exception {
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
                case 1: if(incluirEpisodio()) System.out.println("Episodio incluido com sucesso!");
                    else System.out.println("Não foi possivel incluir este episódio.");
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

    }

    public boolean excluirEpisodio(int idEP) {
        
    }

    public boolean alterarEpisodio(int idEP) {

    }

    public Episodio buscarEpisodio (String nome, String nomeSerie){

    }
}
