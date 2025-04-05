package Entidades;

public class Episodio {
    private int id;
    private String nome;
    private String sinopse;
    private int duracao; 
    private int temporadaId; 

    public Episodio(int id, String nome, String sinopse, int duracao, int temporadaId) {
        this.id = id;
        this.nome = nome;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.temporadaId = temporadaId;
    }

    public int getId() {
        return id;
    }

    public String getnome() {
        return nome;
    }

    public String getsinopse() {
        return sinopse;
    }

    public int getduracao() {
        return duracao;
    }

    public int getTemporadaId() {
        return temporadaId;
    }
    
}
