package ni.edu.ucem.webapi.modelo;

public class Paginacion 
{
    public static final int DEFAULT_OFFSET = 0;
    public static final int DEFAULT_LIMIT = 10;
    private final int limit;
    private final int offset;
    
    public static class Builder
    {
        private int offset;
        private int limit;
        
        public Builder(final int offset, final int limit)
        {
            this.offset = offset;
            this.limit = limit;
        }
        
        public Paginacion build() 
        {
            if (this.offset < 0 || this.limit < 1) 
            {
                this.offset = Paginacion.DEFAULT_OFFSET;
                this.limit = Paginacion.DEFAULT_LIMIT;
            } 
            return new Paginacion(this.offset,this.limit);
        }
    }

    public Paginacion(final int offset, final int limit)
    {
        this.offset = offset;
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
