package ni.edu.ucem.webapi.core;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ni.edu.ucem.webapi.core.ApiResponse.ApiError;
import ni.edu.ucem.webapi.core.ApiResponse.Status;

@JsonInclude(Include.NON_NULL)
public class ListApiResponseList<T> 
{

	private final Status status;
    private final List<T> result;    
    private final ApiError error;
    

    public ListApiResponseList(final Status status, final List<T> data) 
    {
        this(status, data, null);
    }
        
    public ListApiResponseList(final Status status, List<T> result, final ApiError error) 
    {
        this.status = status;
        this.result = result;
        this.error = error;
    }
    
    public Status getStatus() {
        return status;
    }

    public List<T> getResult() {
        return result;
    }
    
    public ApiError getError() {
        return error;
    }
	
}
