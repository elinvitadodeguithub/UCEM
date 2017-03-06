package ni.edu.ucem.webapi.modelo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservacionCuarto {
    
	private Integer id;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date desde;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date hasta;
	private int cuarto;
	private int huesped;
	
	public ReservacionCuarto(){}
	
	public ReservacionCuarto(Date _fechaDesde,
							 Date _fechaHasta,
							 int _cuarto,
							 int _huesped)		
	{
		this.desde = _fechaDesde;
		this.hasta = _fechaHasta;
		this.cuarto = _cuarto;
		this.huesped = _huesped;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDesde()
	{
		return desde;
	}
	public void setDesde(final Date value)
	{
		this.desde = value;
	}	
	
	public Date getHasta()
	{
		return hasta;
	}
	public void setHasta(final Date value)
	{
		this.hasta = value;
	}
		
	public int getCuarto()
	{
		return cuarto;
	}
	public void setCuarto(final int value)
	{
		this.cuarto = value;
	}
	
	public int getHuesped()
	{
		return huesped;
	}
	public void setHuesped(final int value)
	{
		this.huesped = value;
	}
}
