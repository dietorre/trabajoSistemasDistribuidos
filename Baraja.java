import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Baraja {
    private List<Carta> cartas;

    public Baraja() {
        this.cartas = new ArrayList<>();
        for(Palo p:Palo.values()){
            for(int i=1;i<=10;i++){
                cartas.add(new Carta(i, p));
            }
        }
    }

    public Carta sacarCarta(){
        Random rand = new Random();
        int numeroCarta = rand.nextInt(cartas.size());
        if ((cartasRestantes()>0)) {
            Carta resultado = cartas.get(numeroCarta);
            cartas.remove(numeroCarta);
            return resultado;
        }
        else{
            return null;
        }
    }

    public int cartasRestantes(){
        return cartas.size();
    }
}

