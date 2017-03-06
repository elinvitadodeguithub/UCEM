package ni.edu.ucem.webapi.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ni.edu.ucem.webapi.dao.ReservacionCuartoDAO;
import ni.edu.ucem.webapi.modelo.ReservacionCuarto;

@Repository
public class ReservacionCuartoDAOImpl implements ReservacionCuartoDAO {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ReservacionCuartoDAOImpl(final JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//
	private final RowMapper<ReservacionCuarto> rp= new RowMapper<ReservacionCuarto>(){

		@Override
		public ReservacionCuarto mapRow(ResultSet rs, int rowNum) throws SQLException {

			ReservacionCuarto r = new ReservacionCuarto();
			r.setId(rs.getInt(1));
			r.setDesde(rs.getDate(2));
			r.setHasta(rs.getDate(3));			
			r.setCuarto(rs.getInt(4));
			r.setHuesped(rs.getInt(5));
			
			return r;
		}
		
	};
	
	@Override
	public List<ReservacionCuarto> obtenerReservacionPorFechas(int cuarto, Date fechaIni, Date fechaFin) {
		String sql = "Select * From reservacion Where cuarto = ? And desde >= ? And hasta <= ?"; 	
		return this.jdbcTemplate.query(sql, 
						   new Object[]{cuarto, fechaIni, fechaFin}, rp);
	}
	
	@Override
	public void agregarReservacion(final ReservacionCuarto reservacion) {
			
		final String sql = new StringBuilder()
                .append("INSERT INTO reservacion")
                .append(" ")
                .append("(desde, hasta, cuarto, huesped)")
                .append(" ")
                .append("VALUES (?, ?, ?, ?)")
                .toString();
		
        final Object[] parametros = new Object[4];        
		parametros[0] = reservacion.getDesde();			
        parametros[1] = reservacion.getHasta();        
        parametros[2] = reservacion.getCuarto();
        parametros[3] = reservacion.getHuesped();
        this.jdbcTemplate.update(sql,parametros);
	}	
	
}
