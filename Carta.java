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


}
