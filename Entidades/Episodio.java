package Entidades;

public class Episodio {
    private int id;
    private String nome;
    private String dataLancamento;
    private int duracao; 
    private int temporada; 

    public Episodio(int id, String nome, String dataLancamento, int duracao, int temporada) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.temporada = temporada;
    }

    public int getId() {
        return id;
    }

    public String getnome() {
        return nome;
    }

    public String getdataLancamento() {
        return dataLancamento;
    }

    public int getduracao() {
        return duracao;
    }

    public int gettemporada() {
        return temporada;
    }
    
}
