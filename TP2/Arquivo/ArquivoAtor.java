package Arquivo;
import aed3;
import Entidades.Ator;

import java.util.ArrayList;

public class ArquivoAtor extends Arquivo<Ator>{
    ArvoreBMais <ParIdId> indiceIdAtor;
    ArvoreBMais <ParIdAtor> indiceNomeAtor;

    public ArquivoAtor() throws Exception {
    super("ator", Ator.class.getConstructor());

        indiceNomeAtor = new ArvoreBMais<>(
        ParIdAtor.class.getConstructor(),
        5,
        "./dados/" + nomeEntidade + "/indiceAtor.db");
    }

    public int criarAtor(Ator a) throws Exception{
        int id = super.criarAtor(a);
        indiceNomeAtor.criarAtor(new ParIdAtor(a.getNome(), id));
        return id;
    }

    public Ator[] readNome(String nome) throws Exception{
        ArrayList<ParIdAtor> docs = indiceNomeAtor.readNome(new ParIdAtor(nome, -1));
        if (docs.size() > 0) {
            Ator[] atores = new Ator[docs.size()];
            int i = 0;

            for (ParIdAtor doc : docs)
                atores[i++] = readNome(docs.getId());
            return atores;
        } else
            return null;
    }

    public boolean excluirAtor(int id) throws Exception{
        Ator ator = readNome(id);
        if(ator != null) {
            if(super.excluirAtor(id)) {
                return indiceNomeAtor.excluirAtor(new ParIdAtor(ator.getNome(), id));
            }
        }
        else
            return false;
    }

    public boolean excluirAtor(String nome, int id) {
        return false;
    }

    public boolean atualizarAtor(Ator a) throws Exception {
        Ator ator = readNome(a.getId());
        if(super.atualizarAtor(a)) {
            if(!ator.getNome().equals(a.getNome())) {
                indiceNomeAtor.excluirAtor(new ParIdAtor(ator.getNome(), ator.getId()));
                indiceNomeAtor.criarAtor(new ParIdAtor(a.getNome(), ator.getId()));
            }
            return true;
        }
        return false;
    }

    public boolean atorExiste(int id) throws Exception{
        Ator a = readNome(id);
        if(a) {
            return true;
        }
        return false;
    }

}
