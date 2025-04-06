package Arquivo;
import Entidades.Series;
import aed3.*;

public class ArquivoSeries extends aed3.Arquivo<Series> {

    Arquivo<Series> arqSeries;
    HashExtensivel<ParaID> indiceIndiretoIDSerie;
    ArvoreBMais<ParaNomeID> indiceNomeSerie;


    public ArquivoSerieshash() throws Exception {
        super("series", Series.class.getConstructor());
        indiceIndiretoIDSerie = new HashExtensivel<>(
            ParaID.class.getConstructor(), 
            4, 
            ".\\dados\\series\\indiceID.d.db",   // diretório
            ".\\dados\\series\\indiceID.c.db"    // cestos 
        );
    }

    public ArquivoSeriesArvore() throws Exception {
        super("series", Series.class.getConstructor());
        indiceNomeSerie = new HashExtensivel<>(
            ParaNomeID.class.getConstructor(), 
            4, 
            ".\\dados\\series\\indiceNome.d.db",   // diretório
            ".\\dados\\series\\indiceNome.c.db"    // cestos 
        );
    }

    @Override
    public int create(Cliente c) throws Exception {
        int id = super.create(c);
        indiceIndiretoCPF.create(new ParCPFID(c.getCpf(), id));
        return id;
    }

    public Cliente read(String cpf) throws Exception {
        ParCPFID pci = indiceIndiretoCPF.read(ParCPFID.hash(cpf));
        if(pci == null)
            return null;
        return read(pci.getId());
    }
    
    public boolean delete(String cpf) throws Exception {
        ParCPFID pci = indiceIndiretoCPF.read(ParCPFID.hash(cpf));
        if(pci != null) 
            if(delete(pci.getId())) 
                return indiceIndiretoCPF.delete(ParCPFID.hash(cpf));
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Cliente c = super.read(id);
        if(c != null) {
            if(super.delete(id))
                return indiceIndiretoCPF.delete(ParCPFID.hash(c.getCpf()));
        }
        return false;
    }

    @Override
    public boolean update(Cliente novoCliente) throws Exception {
        Cliente clienteVelho = read(novoCliente.getCpf());
        if(super.update(novoCliente)) {
            if(novoCliente.getCpf().compareTo(clienteVelho.getCpf())!=0) {
                indiceIndiretoCPF.delete(ParCPFID.hash(clienteVelho.getCpf()));
                indiceIndiretoCPF.create(new ParCPFID(novoCliente.getCpf(), novoCliente.getId()));
            }
            return true;
        }
        return false;
    }

    public static boolean serieExiste(int id) throws Exception{
        ArquivoSeries arqSeries = new ArquivoSeries();
        Series s = arqSeries.read(id);   // na superclasse
        if(s != null) {
            return true;
        }
        return false;
    }
}

