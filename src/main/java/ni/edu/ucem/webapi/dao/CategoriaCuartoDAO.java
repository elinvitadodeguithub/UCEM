package ni.edu.ucem.webapi.dao;

import java.util.List;

import ni.edu.ucem.webapi.modelo.CategoriaCuarto;

public interface CategoriaCuartoDAO 
{
    public CategoriaCuarto obtenerPorId(final int pId);

    public List<CategoriaCuarto> obtenerTodos(final int offset, final int limit);

    public void agregar(final CategoriaCuarto pCategoriaCuarto);

    public void guardar(final CategoriaCuarto pCategoriaCuarto);

    public void eliminar(final int pId);
    
    public int contar();
}
