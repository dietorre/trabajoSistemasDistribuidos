public class Pruebas{
    public static void main(String[] args){
        Baraja b = new Baraja();
        while(b.cartasRestantes() > 0){
            Carta c = b.sacarCarta();
            System.out.println(c);
        }
    }
}
