package Entidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import aed3.Registro;

public class SerieAtor implements Registro{

    private int id;
    private int idAtor;
    private int idSerie;
   

    public SerieAtor(int id ,int idAtor, int idSerie) {
        this.id = id;
        this.idAtor = idAtor;
        this.idSerie = idSerie;
    }
    public SerieAtor() {
        this.id = -1;
        this.idAtor = -1;
        this.idSerie = -1;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setIdAtor(int id) {
        this.idAtor = id;
    }
    public void setIdSerie(int id) {
        this.idSerie = id;
    }
    public int getId() {
        return this.id;
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