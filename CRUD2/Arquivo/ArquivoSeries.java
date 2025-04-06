package Arquivo;
import aed3.*;

public class ArquivoSeries extends aed3.Arquivo<Series> {

    Arquivo<Series> arqSeries;
    HashExtensivel<ParNomeID> indiceNomeSerie;


    public ArquivoSeriesArvore() throws Exception {
        ssuper("series", Serie.class.getConstructor());
        
        indiceNomeSerie = new HashExtensivel<>(
        ParNomeSerieId.class.getConstructor(), 
        5, 
        ".\\dados\\series\\indiceCPF.d.db",   // diretório
        ".\\dados\\series\\indiceCPF.c.db"   // cestos
        );    
    }
    

    @Override
    public int create(Series c) throws Exception {
        int id = super.create(c);
        indiceNomeSerie.create(new ParnomeID(c.getnome(), id));
        return id;
    }

    public Series read(String nome) throws Exception {
        ParnomeID pci = indiceNomeSerie.read(ParnomeID.hash(nome));
        if(pci == null)
            return null;
        return read(pci.getId());
    }
    
    public boolean delete(String nome) throws Exception {
        ParnomeID pci = indiceNomeSerie.read(ParnomeID.hash(nome));
        if(pci != null) 
            if(delete(pci.getId())) 
                return indiceNomeSerie.delete(ParnomeID.hash(nome));
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Series c = super.read(id);
        if(c != null) {
            if(super.delete(id))
                return indiceNomeSerie.delete(ParnomeID.hash(c.getnome()));
        }
        return false;
    }

    @Override
    public boolean update(Series novoSeries) throws Exception {
        Series SeriesVelho = read(novoSeries.getnome());
        if(super.update(novoSeries)) {
            if(novoSeries.getnome().compareTo(SeriesVelho.getnome())!=0) {
                indiceNomeSerie.delete(ParnomeID.hash(SeriesVelho.getnome()));
                indiceNomeSerie.create(new ParnomeID(novoSeries.getnome(), novoSeries.getId()));
            }
            return true;
        }
        return false;
    }
}

