package Menu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import Entidades.*;
import Arquivo.ArquivoEpisodios;
import Arquivo.ArquivoSeries;
import java.time.format.DateTimeFormatter;

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
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1: if (incluirEpisodio()) System.out.println("Episodio incluído com sucesso!");
                        else System.out.println("Não foi possível incluir este episódio.");
                        break;
                case 2: buscarEpisodio();
                        break;
                case 3: alterarEpisodio();
                        break;
                case 4: excluirEpisodio();
                        break;
                case 0: break;
                default: System.out.println("Opção inválida!");
                         break;
            }
        } while (opcao != 0);
    }

    public boolean incluirEpisodio() {
        try {
            System.out.print("Nome do episódio: ");
            String nome = console.nextLine();
            System.out.print("Data de lançamento (dd/MM/yyyy): ");
            LocalDate data = LocalDate.parse(console.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.print("Duração (min): ");
            float duracao = Float.parseFloat(console.nextLine());
            System.out.print("Temporada: ");
            int temporada = Integer.parseInt(console.nextLine());
            System.out.print("ID da série: ");
            int idSerie = Integer.parseInt(console.nextLine());

            int id = arqEp.getNextId();
            Episodio ep = new Episodio(id, nome, data, duracao, temporada);
            ep.setId(id);
            ep.setIDSerie(idSerie);
            arqEp.incluir(ep);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao incluir episódio: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirEpisodio() {
        try {
            System.out.print("ID do episódio para excluir: ");
            int idEP = Integer.parseInt(console.nextLine());
            if (arqEp.excluir(idEP)) {
                System.out.println("Episódio excluído com sucesso!");
                return true;
            }
            System.out.println("Episódio não encontrado.");
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao excluir episódio: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarEpisodio() {
        try {
            System.out.print("ID do episódio para alterar: ");
            int idEP = Integer.parseInt(console.nextLine());
            Episodio ep = arqEp.buscar(idEP);
            if (ep != null) {
                System.out.print("Novo nome: ");
                ep.setNome(console.nextLine());
                System.out.print("Nova duração: ");
                ep.setDuracao(Float.parseFloat(console.nextLine()));
                System.out.print("Nova temporada: ");
                ep.setTemporada(Integer.parseInt(console.nextLine()));
                arqEp.atualizar(ep);
                System.out.println("Episódio alterado com sucesso!");
                return true;
            }
            System.out.println("Episódio não encontrado.");
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao alterar episódio: " + e.getMessage());
            return false;
        }
    }

    public void buscarEpisodio() {
        try {
            System.out.print("Nome do episódio: ");
            String nome = console.nextLine();
            Episodio[] eps = arqEp.buscarPorNome(nome);
            if (eps.length > 0) {
                for (Episodio ep : eps) {
                    System.out.println(ep);
                }
            } else {
                System.out.println("Nenhum episódio encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar episódio: " + e.getMessage());
        }
    }
}