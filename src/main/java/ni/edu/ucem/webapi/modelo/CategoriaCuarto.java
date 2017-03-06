package ni.edu.ucem.webapi.modelo;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CategoriaCuarto 
{
    private Integer id;
    @NotBlank(message="El Nombre es requerido.")
    @Pattern(regexp="^[\\w ]+$")
    private String nombre;
    @NotBlank(message="La descripcion es requerida.")
    @Pattern(regexp="^[\\w ]+$")
    private String descripcion;
    @NotNull(message="El Precio es requerido.")
    @Range(min=1,max=1000)
    private BigDecimal precio;
    
    public CategoriaCuarto()
    {
    }
    
    public CategoriaCuarto(final Integer id, final String nombre, 
            final String descripcion,final BigDecimal precio) 
    {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
