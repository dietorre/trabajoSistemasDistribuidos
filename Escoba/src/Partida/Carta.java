package Partida;
import java.util.List;

public class Carta {
    private int numero;
    private Palo palo;

    public int getNumero() {
        return this.numero;
    }

    public Palo getPalo() {
        return this.palo;
    }

    public Carta(int numero,Palo palo){
        this.numero = numero;
        this.palo   = palo;
    }

    @Override
    public String toString() {
        String[] figuras = new String[] { "Sota" ,"Caballo", "Rey"};
        String numeroCarta;
        if(getNumero() > 7 && getNumero() < 11){
           numeroCarta = figuras[getNumero()-8];
        }
        else{
            numeroCarta = String.valueOf(getNumero());
        }
        return  numeroCarta + " de " + getPalo();
    }

    public static String mostrarCartas(List<Carta> lista){
        String resultado = "";
        for(Carta c:lista){
            resultado += lista.indexOf(c) + ":" + c.toString() + "   ";
        }
        return resultado;
    }

    public String toMensaje(){
        return String.valueOf(numero) + "," + palo.name();
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Carta)) {
            return false;
        }
        Carta carta = (Carta) o;
        return numero == carta.numero && palo == carta.palo;
    }

    public static Carta cartaFromText(String s){
        String[] textoPartido = s.split(",");
        int valor = Integer.parseInt(textoPartido[0]);
        Palo palo = Palo.valueOf(textoPartido[1]);
        return new Carta(valor, palo);
    }
    
    
}
