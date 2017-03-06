package ni.edu.ucem.webapi.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.edu.ucem.webapi.dao.CategoriaCuartoDAO;
import ni.edu.ucem.webapi.dao.CuartoDAO;
import ni.edu.ucem.webapi.dao.ReservacionCuartoDAO;
import ni.edu.ucem.webapi.modelo.CategoriaCuarto;
import ni.edu.ucem.webapi.modelo.Cuarto;
import ni.edu.ucem.webapi.service.InventarioService;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.modelo.Paginacion;
import ni.edu.ucem.webapi.modelo.ReservacionCuarto;

@Service
public class InventarioServiceImpl implements InventarioService 
{
    private final CategoriaCuartoDAO categoriaCuartoDAO;
    private final CuartoDAO cuartoDAO;
    private final ReservacionCuartoDAO reservacionDAO;
    
    public InventarioServiceImpl(
    		final CategoriaCuartoDAO categoriaCuartoDAO,
            final CuartoDAO cuartoDAO, 
            final ReservacionCuartoDAO reservacionDAO)
    {
        this.categoriaCuartoDAO = categoriaCuartoDAO;
        this.cuartoDAO = cuartoDAO;
        this.reservacionDAO = reservacionDAO;
    }
    @Transactional
    @Override
    public void agregarCategoriaCuarto(final CategoriaCuarto pCategoriaCuarto) 
    {
        this.categoriaCuartoDAO.agregar(pCategoriaCuarto);
    }

    @Transactional
    @Override
    public void guardarCategoriaCuarto(final CategoriaCuarto pCategoriaCuarto) 
    {
        if(pCategoriaCuarto.getId() < 1)
        {
            throw new IllegalArgumentException("La categoría del cuarto no existe");
        }
        this.categoriaCuartoDAO.guardar(pCategoriaCuarto);
    }

    @Transactional
    @Override
    public void eliminarCategoriaCuarto(final int pId) 
    {
        if(pId < 1)
        {
            throw new IllegalArgumentException("ID invalido. Debe ser mayor a cero");
        }
        this.categoriaCuartoDAO.eliminar(pId);
    }

    @Override
    public CategoriaCuarto obtenerCategoriaCuarto(final int pId) 
    {
        return this.categoriaCuartoDAO.obtenerPorId(pId);
    }

    @Override
    public Pagina<CategoriaCuarto> obtenerTodosCategoriaCuartos(Paginacion pagina) 
    {
        List<CategoriaCuarto> categorias;
        final int count = this.categoriaCuartoDAO.contar();
        if(count > 0)
        {
        	categorias = this.categoriaCuartoDAO.obtenerTodos(pagina.getOffset(), pagina.getLimit());        	
        }
        else
        {
        	categorias = new ArrayList<CategoriaCuarto>();        	
        }
    	return new Pagina<CategoriaCuarto>(categorias, count, pagina.getOffset(), pagina.getLimit());
    }
    
    @Transactional
    @Override
    public void agregarCuarto(final Cuarto pCuarto) 
    {
        this.cuartoDAO.agregar(pCuarto);

    }
    
    @Transactional
    @Override
    public void guardarCuarto(final Cuarto pCuarto) 
    {
        if(pCuarto.getId() < 1)
        {
            throw new IllegalArgumentException("El cuarto no existe");
        }
        this.cuartoDAO.guardar(pCuarto);
    }
    
    @Transactional
    @Override
    public void eliminarCuarto(final int pId) 
    {
        if(pId < 1)
        {
            throw new IllegalArgumentException("ID invalido. Debe ser mayor a cero");
        }
        this.cuartoDAO.eliminar(pId);
    }

    @Override
    public Cuarto obtenerCuarto(final int pId) 
    {
        if (pId < 0) 
        {
            throw new IllegalArgumentException("ID inválido. debe ser mayor a cero.");
        }
        return this.cuartoDAO.obtenerPorId(pId); 
    }

    @Override
    public Pagina<Cuarto> obtenerTodosCuarto(Paginacion paginacion) 
    {
    	List<Cuarto> cuartos;
        final int count = this.cuartoDAO.contar();
        if(count > 0)
        {
            cuartos = this.cuartoDAO.obtenerTodos(paginacion.getOffset(),
                    paginacion.getLimit());
        }
        else
        {
            cuartos = new ArrayList<Cuarto>();
        }
        return new Pagina<Cuarto>(cuartos, count,  paginacion.getOffset(), paginacion.getLimit());
    }

    @Override
    public Pagina<Cuarto> obtenerTodosCuartoEnCategoria(final int pCategoriaCuartoId, final Paginacion paginacion)
    {
    	final int count = this.cuartoDAO.contarPorCategoria(pCategoriaCuartoId);
        List<Cuarto> cuartos = null;
        if(count > 0)
        {
            cuartos = this.cuartoDAO.obtenerTodosPorCategoriaId(pCategoriaCuartoId, paginacion.getOffset(),
                    paginacion.getLimit());
        }
        else
        {
            cuartos = new ArrayList<Cuarto>();
        }
        return new Pagina<Cuarto>(cuartos, count,  paginacion.getOffset(), paginacion.getLimit());
    }
    
    //-----------------------
    
	@Override
	public void agregarReservacion(ReservacionCuarto reservacion) {
		if(reservacion.getDesde() == null 
		|| reservacion.getHasta() == null)
		{
			throw new IllegalArgumentException("Debe indicar el periodo completo de fecha entrada y salida.");
		}
		if(reservacion.getCuarto() < 0)
		{
			throw new IllegalArgumentException("Debe indicar el numero de cuarto.");
		}
		
		Cuarto cuarto = new Cuarto();
		cuarto = this.cuartoDAO.obtenerPorId(reservacion.getCuarto());
		if(cuarto == null)
		{
			throw new IllegalArgumentException("El numero de cuarto indicado no existe.");
		}
		if(reservacion.getHuesped() <= 0 || reservacion.getHuesped() > 3)
		{
			throw new IllegalArgumentException("Seleccione al huesped 1 o 2 para esta prueba.");			
		}
		
		List<ReservacionCuarto> r = null;
		r = reservacionDAO.obtenerReservacionPorFechas(reservacion.getCuarto(), 
													   reservacion.getDesde(), 
													   reservacion.getHasta());				
		if(r != null && r.size() > 0)
		{
			throw new IllegalArgumentException("El cuarto ya está reservado para las fechas indicadas.");
		}
		
		reservacionDAO.agregarReservacion(reservacion);		
		
	}
	
	@Override
	public List<ReservacionCuarto> verificarReservacion(int cuarto, Date fechaIni, Date fechaFin) {
		
		if(cuarto <= 0)
		{
			throw new IllegalArgumentException("El numero de cuarto no es valido.");
		}
		Cuarto c = null;
		c = cuartoDAO.obtenerPorId(cuarto);
		if(c.equals(null))
		{
			throw new IllegalArgumentException("El numero de cuarto no existe. ");
		}
		return reservacionDAO.obtenerReservacionPorFechas(cuarto, fechaIni, fechaFin);		
	}	
}
