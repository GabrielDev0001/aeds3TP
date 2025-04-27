package Arquivo;
import Entidades.*;

import java.io.File;
import java.util.ArrayList;

import aed3.*;

public class ArquivoSeries extends Arquivo<Series> {
    
    Arquivo<Series> arqSeries;
    ArvoreBMais<ParNomeSerieId> indiceNomeSerie;
    ArvoreBMais <ParIdId> indices;

    public ArquivoSeries() throws Exception {
        super("series", Series.class.getConstructor());
        
       new File("./dados/series").mkdirs();

    indiceNomeSerie = new ArvoreBMais<>(
        ParNomeSerieId.class.getConstructor(), 
        5, 
        "./dados/series/indiceNomeSerie.d.db"
    );
    }

    @Override
    public int create(Series s) throws Exception {
        int id = super.create(s);
        indiceNomeSerie.create(new ParNomeSerieId(s.getNome(), id));
        return id;
    }
 
    public Series[] readNome(String nome) throws Exception {
        if(nome.length()==0)
            return null;
            
        ArrayList<ParNomeSerieId> ptis = indiceNomeSerie.read(new ParNomeSerieId(nome, -1));
        if(ptis.size()>0) {
            Series[] series = new Series[ptis.size()];
            int i=0;
            
            for(ParNomeSerieId pti: ptis) 
                series[i++] = read(pti.getId());
            return series;
        }
        else 
            return null;
    }


    @Override
    public boolean delete(int id) throws Exception {
        Series s = read(id);   // na superclasse
        if(s!=null) {
            if(super.delete(id))
                return indiceNomeSerie.delete(new ParNomeSerieId(s.getNome(), id));
        }
        return false;
    }

    public boolean delete(String nome, int id) throws Exception {
        ArquivoEpisodios arqEpisodios = new ArquivoEpisodios();
        
        Series s = read(id); 
        //verificar se a serie existe
        if(s != null && s.getId() == id && s.getNome().equals(nome)){

            System.out.println(s);
           
            //verificar episodios vinculados a serie
           Episodio[] ids = arqEpisodios.readEpisodiosSerie(id); 
          
           //indices.readE(new ParIdId(id, -1));
            
            if(ids!= null){
                
                //excluir todos os episodios
                boolean deletados = arqEpisodios.deleteEpisodioSerie(id);
                //deletar a serie
                if(deletados){
                    return delete(id);
                }
            } 

                return delete(id);
            
        }
        return false;
    }

    @Override
    public boolean update(Series novaSerie) throws Exception {
        Series s = read(novaSerie.getId());    // na superclasse
        if(s!=null) {
            if(super.update(novaSerie)) {
                if(!s.getNome().equals(novaSerie.getNome())) {
                    indiceNomeSerie.delete(new ParNomeSerieId(s.getNome(), s.getId()));
                    indiceNomeSerie.create(new ParNomeSerieId(novaSerie.getNome(), s.getId()));
                }
                return true;
            }
        }
        return false;
    }

    //metodo para testar se ha serie vinculada ao id buscado
    public static boolean serieExiste(int id) throws Exception{
        ArquivoSeries arqSeries = new ArquivoSeries();
        Series s = arqSeries.read(id);   // na superclasse
        if(s != null) {
            return true;
        }
        return false;
    }
}