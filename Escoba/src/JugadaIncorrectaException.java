
public class JugadaIncorrectaException extends Exception{
    private int sumaCartas;
    public JugadaIncorrectaException(int n){
        super();
        sumaCartas = n;
    }

    public int getSumaCartas(){
        return sumaCartas;
    }
}
