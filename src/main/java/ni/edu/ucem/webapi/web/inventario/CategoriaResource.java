package ni.edu.ucem.webapi.web.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ni.edu.ucem.webapi.core.ApiResponse;
import ni.edu.ucem.webapi.core.ApiResponse.Status;
import ni.edu.ucem.webapi.core.ListApiResponse;
import ni.edu.ucem.webapi.modelo.CategoriaCuarto;
import ni.edu.ucem.webapi.modelo.Paginacion;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.serviceImpl.InventarioServiceImpl;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	private final InventarioServiceImpl inventarioService;
	
	@Autowired
	public CategoriaResource(final InventarioServiceImpl i)
	{		
		this.inventarioService = i;
	}

	@RequestMapping(method = RequestMethod.GET,produces="application/json")
    public ListApiResponse<CategoriaCuarto> obtenerCategorias(
    		@RequestParam(value = "categoria",required = false) final Integer categoria,
		    @RequestParam(value = "offset", required = false, defaultValue ="0") final Integer offset,
		    @RequestParam(value = "limit", required = false, defaultValue="0") final Integer limit)
    {
		final Paginacion paginacion = new Paginacion.Builder(offset, limit).build();
		Pagina<CategoriaCuarto> pagina;
		
        pagina = this.inventarioService.obtenerTodosCategoriaCuartos(paginacion);
        
        return new ListApiResponse<CategoriaCuarto>(Status.OK, pagina);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
    public ApiResponse obtener(@PathVariable("id") final int id)
    {
        try
        {
            final CategoriaCuarto categoria = this.inventarioService.obtenerCategoriaCuarto(id);
            return new ApiResponse(Status.OK, categoria);
        }
        catch (EmptyResultDataAccessException e)
        {
            return new ApiResponse(Status.BAD_REQUEST, "No exite una categoria con el ID" + id);
        }
    }
	
    @RequestMapping(method = RequestMethod.POST,
            produces = "application/json")
    public ApiResponse guardarCategoria(@RequestBody final CategoriaCuarto categoria) 
    {
        this.inventarioService.agregarCategoriaCuarto(categoria);
        return new ApiResponse(Status.OK, categoria);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces="application/json")
    public ApiResponse guardarCategoria(@PathVariable("id") final int id, 
            @RequestBody final CategoriaCuarto categoriaActualizado) 
    {
        final CategoriaCuarto categoria = new CategoriaCuarto(id,
        		categoriaActualizado.getNombre(),
        		categoriaActualizado.getDescripcion(),
        		categoriaActualizado.getPrecio());
        this.inventarioService.guardarCategoriaCuarto(categoria);
        return new ApiResponse(Status.OK, categoria);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, 
            produces="application/json")
    public ApiResponse eliminarCuarto(@PathVariable("id") final int id) 
    {
        try
        {
            final CategoriaCuarto categoria = this.inventarioService.obtenerCategoriaCuarto(id);
            this.inventarioService.eliminarCategoriaCuarto(categoria.getId());
            return new ApiResponse(Status.OK,null);
        } 
        catch (EmptyResultDataAccessException e)
        {
            return new ApiResponse(Status.BAD_REQUEST, "No exite una categoria con el ID " + id);
        }
    }
    
}
