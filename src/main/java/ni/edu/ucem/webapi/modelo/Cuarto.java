package ni.edu.ucem.webapi.modelo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class Cuarto 
{
	//las reglas de validacion se pueden establecer en esta parte
	//para cada campo.
    private Integer id;
    @NotNull(message = "El numero de cuarto es requerido") //regla de validacion para indicar que este campo no debe ser nulo
    @Range(min=1,max=Short.MAX_VALUE)
    private Short  numero;
    @Pattern(regexp = "^[\\w ]+$") //todos los caracteres ascci mas carateres especiales
    @NotBlank(message = "La descripcion es requerida")
    private String descripcion;
    @NotNull
    @Min(1)
    private Integer categoria;
    
    public Cuarto()
    {
    }
    
    public Cuarto(final Short numero, final String descripcion, final Integer categoria) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public Cuarto(final Integer id, final Short numero, final String descripcion,
            final Integer categoria) 
    {
        this.id = id;
        this.numero = numero;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Short getNumero() {
        return numero;
    }
    public void setNumero(final Short numero) {
        this.numero = numero;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getCategoria() {
        return categoria;
    }
    public void setCategoria(final Integer categoria) {
        this.categoria = categoria;
    }    
}
