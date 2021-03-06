package ni.edu.ucem.webapi.core;

import ni.edu.ucem.webapi.core.ApiResponse.Status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.core.ApiResponse.ApiError;

@JsonInclude(Include.NON_NULL) //le estoy indicando que no incluya valores que son nulos en la respuesta
public class ListApiResponse<T>
{
	private final Status status;
    private final Pagina<T> result;
    private final ApiError error;
    

    public ListApiResponse(final Status status, final Pagina<T> data) 
    {
        this(status, data, null);
    }
    
    public ListApiResponse(final Status status, Pagina<T> result, final ApiError error) 
    {
        this.status = status;
        this.result = result;
        this.error = error;
    }
    
    public Status getStatus() {
        return status;
    }

    public Pagina<T> getResult() {
        return result;
    }

    public ApiError getError() {
        return error;
    }
}
