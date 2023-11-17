import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket; 
import java.util.ArrayList; 
import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        try(Socket s = new Socket("localhost",55555);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()))){
            
            Jugador jugador = new Jugador();
            
            boolean partidaTerminada = false;
            while(!partidaTerminada){
                String mensajeRecibido =  in.readLine();
                System.out.println(mensajeRecibido);
                String[] textoPartido = mensajeRecibido.split(":");

                switch (textoPartido[0]) {
                    case "robarCarta":
                        Carta c = Carta.cartaFromText(textoPartido[1]);
                        jugador.robarCarta(c);
                        break;

                    case "jugarTurno":
                        List <Carta> cartasMesa = new ArrayList<>();
                        for(int i=1;i<textoPartido.length;i++){
                            cartasMesa.add(Carta.cartaFromText(textoPartido[i]));
                        }
                        Accion a = jugador.jugarTurno(cartasMesa);
                        out.write(a.toMensage());
                        break;
                    case "terminarPartida":
                        partidaTerminada = true;
                        break;

                    default:
                        break;
                }

            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
