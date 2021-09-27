 /**
  *
 * @author Rodrigo Richard Gomes
 * @version 1.00 2018/3/16
 * 
 */

public class CCelula {
	public Object item;
	public CCelula prox;    	
    public CCelula(Object valorItem, CCelula proxCelula)
    {
        item = valorItem;
        prox = proxCelula;
    }    			
    public CCelula(Object valorItem)
    {
        item = valorItem;
        prox = null;
    }    			        	
    public CCelula()
    {
    	item = null;
        prox = null;
    }    			        	
}