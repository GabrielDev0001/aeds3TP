package Entidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import aed3.Registro;

public class Ator {
    private int id;
    private String nome;

    public Ator (int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    public Ator () {
        this.id = -1;
        this.nome = "";
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }

}