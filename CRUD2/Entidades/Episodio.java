package Entidades;

public class Episodio {
    private int id;
    private String nome;
    private String dataLancamento;
    private float duracao; 
    private int temporada; 

    public Episodio(int id, String nome, String dataLancamento, float duracao, int temporada) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.temporada = temporada;
    }

    public Episodio(int id, String nome, int temporada, String dataLancamento, float duracao) throws Exception {
        this.id = id;
        if (nome.length() > 0 && nome.length() <= 50)
            this.nome = nome;
        else
            throw new Exception("Nome inválido! Tamanho deve ser entre 1 e 50 caracteres.");
        if (temporada >= 0)
            this.temporada = (byte) temporada;
        else
            throw new Exception("Temporada inválida! Deve ser maior ou igual a zero.");
        if (dataLancamento.length() == 10 && dataLancamento.charAt(2) == '/' && dataLancamento.charAt(5) == '/')
            this.dataLancamento = dataLancamento;
        else
            throw new Exception("Data inválida! Formato deve ser dd/MM/yyyy.");
        if (duracao >= 0)
            this.duracao = duracao;
        else
            throw new Exception("Duração menor que zero!");    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnome() {
        return nome;
    }

    public String getdataLancamento() {
        return dataLancamento;
    }

    public float getduracao() {
        return duracao;
    }

    public int gettemporada() {
        return temporada;
    }
    
    public String toString() {
        return "\nID.................: " + this.id +
               "\nNome...............: " + this.nome +
               "\nData de lançamento.: " + this.dataLancamento +
               "\nDuração............: " + this.duracao +
               "\nTemporada .........: " + this.temporada;
    }
}
