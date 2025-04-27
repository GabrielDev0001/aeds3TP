package Entidades;

public class SerieAtor {
    private int idAtor;
    private int idSerie;

    public SerieAtor(int idAtor, int idSerie) {
        this.idAtor = idAtor;
        this.idSerie = idSerie;
    }
    public SerieAtor() {
        this.idAtor = -1;
        this.idSerie = -1;
    }
    public void setIdAtor(int id) {
        this.idAtor = id;
    }
    public void setIdSerie(int id) {
        this.idSerie = id;
    }
    public int getIdAtor() {
        return this.idAtor;
    }
    public int getIdSerie() {
        return this.idSerie;
    }
}