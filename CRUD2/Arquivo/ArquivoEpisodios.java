package Arquivo;

import aed3.*;
import java.util.ArrayList;
import Entidades.*;

public class ArquivoEpisodios extends Arquivo<Episodio> {

    Arquivo<Episodio> arqEpisodio;
    ArvoreBMais<ParaID> indiceIndiretoIDEpisodio;
    ArvoreBMais<ParaNomeID> indiceNomeEpisodio;


    public ArquivoEpisodioHash() throws Exception {
        super("episodio", Episodio.class.getConstructor());
        indiceIndiretoIDEpisodio = new HashExtensivel<>(
            ParaID.class.getConstructor(), 
            4, 
            ".\\dados\\episodios\\indiceID.d.db",   // diretório
            ".\\dados\\episodios\\indiceID.c.db"    // cestos 
        );
    }

    public ArquivoEpisodiosArvore() throws Exception {
        super("episodios", Episodio.class.getConstructor());
        indiceNomeEpisodio = new HashExtensivel<>(
            ParaNomeID.class.getConstructor(), 
            4, 
            ".\\dados\\episodios\\indiceNome.d.db",   // diretório
            ".\\dados\\episodios\\indiceNome.c.db"    // cestos 
        );
    }

    public int getUltimoID() throws Exception {
        int id = 0;
        ArrayList<ParaID> locs = indiceIndiretoIDEpisodio.read(new ParaID(-1, -1));
        if (locs.size() > 0) {
            for (ParaID pID : locs) {
                if (pID.getId_agregado() > id) {
                    id = pID.getId_agregado();
                }
            }
        }
        return id;
    }

    @Override
    public int create(Episodio e) throws Exception {
        if(Arquivo.Series.serieExiste(e.getIDSerie()) == false) {
            throw new Exception("Episodio não pode ser criado pois a série vinculada não existe.");
        }

        int id = super.create(e);

        indiceIndiretoIDEpisodio.create(new ParaID(e.getIDSerie(), id));
        indiceNomeEpisodio.create(new ParaNomeID(e.getNome(), id));

        return id;
    }

    public Episodio[] readNomeEpisodio(String nome) throws Exception {
        if(nome.length() == 0) {
            return null;
        }

        ArrayList<ParaNomeID> docs = indiceNomeEpisodio.read(new ParaNomeID(nome, -1));
        if (docs.size() > 0) {
            Episodio[] episodios = new Episodio[docs.size()];
            int i = 0;
            for(ParaNomeID doc: docs) {
                episodios[i++] = read(doc.getId());
            }
            return episodios;
        }
        else {
            return null;
        }
    }
    
    public Episodio[] lerNomeEpisodioPorSerie(String nome, int idSerie) throws Exception {
        if (nome.length() == 0) {
            return null;
        }

        ArrayList<ParaNomeID> docs = indiceNomeEpisodio.read(new ParaNomeID(nome, -1));
        if (docs.size() > 0) {
            Episodio[] episodios = new Episodio[docs.size()];
            int i = 0;
            for (ParaNomeID doc : docs) {
                episodios[i++] = read(doc.getId());
            }

            ArrayList<Episodio> episodiosSerie = new ArrayList<>();

            for (Episodio e : episodios) {
                if (e.getIDSerie() == idSerie) {
                    episodiosSerie.add(e);
                }
            }
            return episodiosSerie.toArray(new Episodio[episodiosSerie.size()]);
        } else {
            return null;
        }
    }

    public Episodio[] readEpisodiosSerie(int idSerie) throws Exception{
        ArrayList<ParaID> locs = indiceIndiretoIDEpisodio.read(new ParaID(idSerie, -1));

        if (locs.size() > 0) {
            Episodio[] episodios = new Episodio[locs.size()];
            int i = 0;

            for (ParaID pID : locs) {
                episodios[i++] = read(pID.getId_agregado());
            }
            return episodios;
        } else {
            return null;
        }
    }

    public boolean delete(String nome) throws Exception {
        int id = indiceNomeEpisodio.read(nome);
        
        if (id != null) {
            Episodio e = read(id);
            
            if (e != null) {
                if (super.delete(id)) {
                    return indiceIndiretoIDEpisodio.delete(new ParaID(e.getIDSerie(), id))
                        && indiceNomeEpisodio.delete(new ParaNomeID(e.getNome(), id));
                }
            }
        }
        return false; 
    }
    

    public boolean deleteEpisodioSerie(int id_serie) throws Exception{

        ArrayList<ParaID> pIds = indiceIndiretoIDEpisodio.read(new ParaID(id_serie, -1));
    
        System.out.println("Quantidade de episódios da serie deletados: " + pIds.size());
    
        if(pIds.size() > 0){
          for(ParaID pID : pIds)
            delete(pID.getId_agregado());
          return true;
        } 
        return false;
      }
    
      @Override
      public boolean update(Episodio novoEpisodio) throws Exception{
        Episodio e = read(novoEpisodio.getId());
        if(e != null){
          if(super.update(novoEpisodio)){
            if(!e.getNome().equals(novoEpisodio.getNome())){
              indiceNomeEpisodio.delete(new ParaNomeID(e.getNome(), e.getId()));
              indiceNomeEpisodio.create(new ParaNomeID(novoEpisodio.getNome(), e.getId()));
            }
    
            if(e.getIDSerie() != novoEpisodio.getIDSerie()){
              indiceIndiretoIDEpisodio.delete(new ParaID(e.getIDSerie()), e.getIDSerie());
              indiceIndiretoIDEpisodio.create(new ParaID(novoEpisodio.getIDSerie()), e.getId());
            }
            return true;
          }
        }
        return false;
      }
    
    
     
      public float avaliacaoMediaSerie(int id_serie) throws Exception{
        
        float soma = 0;
        
        ArrayList<ParaID> pIds = indiceIndiretoIDEpisodio.read(new ParaID(id_serie, -1));
        if(pIds.size() > 0){
            Episodio[] episodios = new Episodio[pIds.size()];
            int i = 0;
    
            for(ParaID pID : pIds){
                episodios[i++] = read(pID.getId_agregado());
                soma += episodios[i-1].getAvaliacao();
            }
            return soma / episodios.length;
        }
        else
          return 0;
    }

    Episodio read() {
        Episodio x;
    }
}
