package ni.edu.ucem.webapi.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * See {@linktourl https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html}
 * @author fmedina
 */
@JsonInclude(Include.NON_NULL) //le estoy indicando que no incluya valores que son nulos en la respuesta
public class ApiResponse
{

    public static enum Status
    {
        OK(200),
        BAD_REQUEST(400),
        INTERNAL_SERVER_ERROR(500);
        private final int value;
        
        private Status(final int value){
            this.value = value;
        }
        
        public int getValue(){
            return this.value;
        }
    }
    
    public static final class ApiError
    {
        private final int codigoError;
        private final String descripcion;
        
        public ApiError(final int codigoError, final String descripcion)
        {
            this.codigoError = codigoError;
            this.descripcion = descripcion;
        }
        
        public int getCodigoError() {
            return codigoError;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
    
    private final Status status;
    private final Object data;
    private final ApiError error;
    
    public ApiResponse(final Status status)
    {
        this(status,null,null);
    }
    
    public ApiResponse(final Status status, final Object data)
    {
        this(status,data,null);
    }

    public ApiResponse(final Status status, 
            final Object data, final ApiError error)
    {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public Status getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }
}
    
