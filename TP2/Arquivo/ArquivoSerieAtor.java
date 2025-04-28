package Arquivo;

import aed3.*;
import Entidades.SerieAtor;
import java.util.ArrayList;

public class ArquivoSerieAtor extends Arquivo<SerieAtor> {

  Arquivo<SerieAtor> arqSerieAtor;
  ArvoreBMais<ParIdId> indiceIdAtor_IdSerie;
  ArvoreBMais<ParIdAtoSer> indiceNomeAtor;

  private ArquivoAtor arqAtor; 

  public ArquivoSerieAtor() throws Exception {
    super("SerieAtor", SerieAtor.class.getConstructor());
    arqAtor = new ArquivoAtor();

    indiceIdAtor_IdSerie = new ArvoreBMais<>(
        ParIdId.class.getConstructor(),
        5,
        "./dados/SerieAtor/indiceIdId.db");
  }

  @Override
  public int create(SerieAtor e) throws Exception {

    // Metodo para verificar se a serie existe
    if (ArquivoSeries.serieExiste(e.getIdSerie()) == false) {
      throw new Exception("SerieAtor não pode ser criado pois a serie vinculada não existe");
    }

    //verificar se o ator existe
    if (arqAtor.atorExiste(e.getIdAtor()) == false) {
      throw new Exception("SerieAtor não pode ser criado pois esse ator não existe");
    }

    int id = super.create(e);

    indiceIdAtor_IdSerie.create(new ParIdId(e.getIdSerie(), id));

    return id;
  }

  public SerieAtor[] readSerieAtor(int nome) throws Exception {
    if (nome < 0)
      return null;

    ArrayList<ParIdAtoSer> pias = indiceNomeAtor.read(new ParIdAtoSer(nome , -1));
    if (pias.size() > 0) {
      SerieAtor[] SerieAtor = new SerieAtor[pias.size()];
      int i = 0;
      for (ParIdAtoSer pia : pias)
        SerieAtor[i++] = read(pia.getId());
      return SerieAtor;

    } else
      return null;
  }

  public SerieAtor[] readSerieAtorPorSerie(int nome, int id_serie) throws Exception {
    if (nome < 0)
      return null;

    ArrayList<ParIdAtoSer> pias = indiceNomeAtor.read(new ParIdAtoSer(nome, -1));
    if (pias.size() > 0) {
      SerieAtor[] SerieAtor = new SerieAtor[pias.size()];
      int i = 0;
      for (ParIdAtoSer pti : pias)
        SerieAtor[i++] = read(pti.getId());

      ArrayList<SerieAtor> SerieAtorSerie = new ArrayList<>();

      // Verifica se o SerieAtor pertence a serie
      for (SerieAtor e : SerieAtor) {
        if (e.getIdSerie() == id_serie)
          SerieAtorSerie.add(e);
      }

      return SerieAtorSerie.toArray(new SerieAtor[SerieAtorSerie.size()]);
    } else
      return null;
  }

  @Override
  public boolean delete(int id) throws Exception {
    SerieAtor e = read(id);
    if (e != null) {
      if (super.delete(id))
        return indiceIdAtor_IdSerie.delete(new ParIdId(e.getIdSerie(), id))
            && indiceNomeAtor.delete(new ParIdAtoSer(e.getIdAtor(), id));
    }
    return false;
  }

  public boolean deleteSerieAtorSerie(int id_serie) throws Exception {

    // Metodo para verificar se a serie vinculada ao SerieAtor existe
    ArrayList<ParIdId> pIds = indiceIdAtor_IdSerie.read(new ParIdId(id_serie, -1));

    System.out.println("Quantidade de atores da serie deletados: " + pIds.size());

    if (pIds.size() > 0) {
      for (ParIdId pID : pIds)
        delete(pID.getId_agregado());
      return true;
    }
    return false;
  }

  @Override
  public boolean update(SerieAtor novoSerieAtor) throws Exception {
    SerieAtor e = read(novoSerieAtor.getId());
    if (e != null) {
      if (super.update(novoSerieAtor)) {
        if (e.getIdSerie() != novoSerieAtor.getIdSerie()) {
          indiceIdAtor_IdSerie.delete(new ParIdId(e.getIdSerie(), e.getId()));
          indiceIdAtor_IdSerie.create(new ParIdId(novoSerieAtor.getIdSerie(), e.getId()));
        }

        return true;
      }
    }
    return false;
  }

}