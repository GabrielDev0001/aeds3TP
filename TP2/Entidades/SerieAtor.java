package Entidades;

import aed3.EntidadeArquivo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import aed3.Registro;

public class SerieAtor implements EntidadeArquivo{
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
    public byte[] toByteArray() throws IOException {//serializa o objeto
        // cria um ByteArrayOutputStream para armazenar os dados serializados
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(idAtor);
        dos.writeInt(idSerie);

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] b) throws IOException {//deserializa o objeto
        // cria um ByteArrayInputStream para ler os dados serializados
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);

        this.idAtor = dis.readInt();
        this.idSerie = dis.readInt();
    }
}