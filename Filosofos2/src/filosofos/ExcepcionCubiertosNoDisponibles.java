package filosofos;

@SuppressWarnings("serial")
public class ExcepcionCubiertosNoDisponibles extends Exception { 
	
    public ExcepcionCubiertosNoDisponibles(String errorMessage) {
    	
        super(errorMessage);
    }
}