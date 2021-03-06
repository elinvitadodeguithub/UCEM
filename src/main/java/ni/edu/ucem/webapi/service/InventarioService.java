package ni.edu.ucem.webapi.service;

import java.util.Date;
import java.util.List;

import ni.edu.ucem.webapi.modelo.CategoriaCuarto;
import ni.edu.ucem.webapi.modelo.Cuarto;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.modelo.Paginacion;
import ni.edu.ucem.webapi.modelo.ReservacionCuarto;

public interface InventarioService 
{
    public void agregarCategoriaCuarto(final CategoriaCuarto pCategoriaCuarto);

    public void guardarCategoriaCuarto(final CategoriaCuarto pCategoriaCuarto);

    public void eliminarCategoriaCuarto(int id);
    
    public CategoriaCuarto obtenerCategoriaCuarto(final int id);

    public Pagina<CategoriaCuarto> obtenerTodosCategoriaCuartos(final Paginacion pagina);

    public void agregarCuarto(final Cuarto pCuarto);

    public void guardarCuarto(final Cuarto pCuarto);

    public void eliminarCuarto(final int pCuarto);

    public Cuarto obtenerCuarto(final int pCuarto);

    public Pagina<Cuarto> obtenerTodosCuarto(final Paginacion paginacion);

    public Pagina<Cuarto> obtenerTodosCuartoEnCategoria(final int pCategoriaCuarto, final Paginacion paginacion);
    
    //-----------------    
    public void agregarReservacion(final ReservacionCuarto reservacion);    
    //public void eliminarReservacion(final int reservacion);
    public List<ReservacionCuarto> verificarReservacion(int cuarto,
    											  Date fechaIni,
    											  Date fechaFin);		   
}
