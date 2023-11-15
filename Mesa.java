import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private List<Carta> cartas;
    private Baraja baraja;

    public Mesa(Baraja baraja) {
        this.cartas = new ArrayList<>();
        this.baraja = baraja;
    }

   public List<Carta> getCartasMesa(){
        return cartas;
   } 

    public void sacarCarta() throws NoMasCartasException{
       cartas.add(baraja.sacarCarta());
    }

    public Carta robarCarta() throws NoMasCartasException{
        return baraja.sacarCarta();
    }

    public String toString() {
        return Carta.mostrarCartas(cartas);
    }


    public int cartasRestantes(){
        return baraja.cartasRestantes();
    }
    
}
