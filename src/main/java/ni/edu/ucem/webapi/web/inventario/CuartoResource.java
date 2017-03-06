package ni.edu.ucem.webapi.web.inventario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ni.edu.ucem.webapi.core.ApiResponse;
import ni.edu.ucem.webapi.core.ApiResponse.Status;
import ni.edu.ucem.webapi.core.ListApiResponse;
import ni.edu.ucem.webapi.modelo.Cuarto;
import ni.edu.ucem.webapi.serviceImpl.InventarioServiceImpl;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.modelo.Paginacion;

@RestController
@RequestMapping("/v1/inventario/cuartos") //esta estructura de uri es siguiendo la guia de estilos
public class CuartoResource 
{
    private final InventarioServiceImpl inventarioService;
    
    @Autowired
    public CuartoResource(final InventarioServiceImpl inventarioService)
    {
        this.inventarioService = inventarioService;
    }
    
    /***
     * localhost:8080/v1/inventario/cuartos?offset=0&limit=2   
     * localhost:8080/v1/inventario/cuartos?categoria=1&offset=0&limit=2  
     */
    
    @RequestMapping(method = RequestMethod.GET,produces="application/json")
    public ListApiResponse<Cuarto> obtenerCuartos(
    		@RequestParam(value = "categoria", required = false) final Integer categoria,
            @RequestParam(value = "offset", required = false, defaultValue ="0") final Integer offset,
            @RequestParam(value = "limit", required = false, defaultValue="0") final Integer limit)
    {
    	final Paginacion paginacion = new Paginacion.Builder(offset, limit).build();
        Pagina<Cuarto> pagina;
        if(categoria != null)
        {
        	pagina = this.inventarioService.obtenerTodosCuartoEnCategoria(categoria, paginacion);
        }
        else
        {
        	pagina = this.inventarioService.obtenerTodosCuarto(paginacion);
        }
        return new ListApiResponse<Cuarto>(Status.OK, pagina);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
    public ApiResponse obtener(@PathVariable("id") final int id)
    {
        /*try
        {*/
            final Cuarto cuarto = this.inventarioService.obtenerCuarto(id);
            return new ApiResponse(Status.OK, cuarto);
        /*}
        catch (EmptyResultDataAccessException e)
        {
            return new ApiResponse(Status.BAD_REQUEST, "No exite un cuarto con el ID" + id);
        }*/
    }
    
    @RequestMapping(method = RequestMethod.POST,
            produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse guardarCuarto(@Valid @RequestBody final Cuarto cuarto) 
    {
        this.inventarioService.agregarCuarto(cuarto);
        return new ApiResponse(Status.OK, cuarto);
    }
    
    /**
     * 
     * @param numero
     * @param descripcion
     * @param categoria
     * @return
     * 
     * esta forma de registro utiliza el parametro consumes para indicar que el tipo de
     * medio de envio es un form urlencoded. 
     */
    @RequestMapping(method 	 = RequestMethod.POST,
            		produces = "application/json",
            		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse guardarCuartoConFormParameter(final short 	numero, 
    												 final String 	descripcion,
    												 final Integer 	categoria) 
    {
    	final Cuarto cuarto = new Cuarto(numero, descripcion, categoria);
        this.inventarioService.agregarCuarto(cuarto);
        return new ApiResponse(Status.OK, cuarto);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces="application/json")
    public ApiResponse guardarCuarto(@PathVariable("id") final int id, 
            @RequestBody final Cuarto cuartoActualizado) 
    {
        final Cuarto cuarto = new Cuarto(id,
                cuartoActualizado.getNumero(), 
                cuartoActualizado.getDescripcion(),
                cuartoActualizado.getCategoria());
        this.inventarioService.guardarCuarto(cuarto);
        return new ApiResponse(Status.OK, cuarto);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, 
            produces="application/json")
    public ApiResponse eliminarCuarto(@PathVariable("id") final int id) 
    {
        /*try
        {*/
            final Cuarto cuarto = this.inventarioService.obtenerCuarto(id);
            this.inventarioService.eliminarCuarto(cuarto.getId());
            return new ApiResponse(Status.OK,null);
        /*} 
        catch (EmptyResultDataAccessException e)
        {
            return new ApiResponse(Status.BAD_REQUEST, "No exite un cuarto con el ID " + id);
        }*/
    }
}
