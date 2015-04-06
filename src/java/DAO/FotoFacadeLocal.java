package DAO;

import Core.Foto;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FotoFacadeLocal
{
    public void create( Foto foto );

    public void edit( Foto foto );

    public void remove( Foto foto );

    public Foto find( Object id );

    public List<Foto> findAll();

    public List<Foto> findRange( int[] range );

    public int count();
}

