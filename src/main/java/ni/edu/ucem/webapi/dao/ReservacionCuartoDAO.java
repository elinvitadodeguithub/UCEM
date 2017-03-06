package ni.edu.ucem.webapi.dao;

import java.util.Date;
import java.util.List;

import ni.edu.ucem.webapi.modelo.ReservacionCuarto;

public interface ReservacionCuartoDAO {

	public List<ReservacionCuarto> obtenerReservacionPorFechas(final int cuarto, 
														 final Date fechaIni, 
														 final Date fechaFin);	
	public void agregarReservacion(final ReservacionCuarto reservacion);
		
}
