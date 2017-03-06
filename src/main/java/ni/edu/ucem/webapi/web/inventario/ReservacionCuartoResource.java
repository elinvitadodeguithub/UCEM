package ni.edu.ucem.webapi.web.inventario;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ni.edu.ucem.webapi.core.ApiResponse;
//import ni.edu.ucem.webapi.core.ListApiResponse;
import ni.edu.ucem.webapi.core.ApiResponse.Status;
import ni.edu.ucem.webapi.core.ListApiResponseList;
import ni.edu.ucem.webapi.modelo.ReservacionCuarto;
import ni.edu.ucem.webapi.serviceImpl.InventarioServiceImpl;

@RestController
@RequestMapping("/v1/inventario/reservaciones") //esta estructura de uri es siguiendo la guia de estilos
public class ReservacionCuartoResource {

	private final InventarioServiceImpl inventarioService;
	
	@Autowired
	public ReservacionCuartoResource(final InventarioServiceImpl inventarioService)
	{
		this.inventarioService = inventarioService;
	}	
	
	/***
	 * 
	 * 	 /v1/inventario/reservaciones?cuarto=1&desde=2017-02-27&hasta=2017-02-28
	 */
	@RequestMapping(method = RequestMethod.GET,produces="application/json")
	public ListApiResponseList<ReservacionCuarto>obtenerReservaciones(
			@RequestParam(value = "cuarto", required = false) final int cuarto,
			@RequestParam(value = "desde", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date desde,
			@RequestParam(value = "hasta", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date hasta)
	{		
		List<ReservacionCuarto> lista = null; 
		if(desde != null && hasta != null)
		{
			lista = inventarioService.verificarReservacion(cuarto, desde, hasta);			
		}
		
		return new ListApiResponseList<ReservacionCuarto>(Status.OK, lista);				
	}
	
	@RequestMapping(method = RequestMethod.POST,
            produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
	public ApiResponse guardarReservacionCuarto(@Valid @RequestBody final ReservacionCuarto reservacion)			
	{
		this.inventarioService.agregarReservacion(reservacion);
		return new ApiResponse(Status.OK,reservacion);
	}				
}
