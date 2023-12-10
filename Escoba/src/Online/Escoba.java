package Online;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Escoba {
    public static void main(String[] args) {
        try(BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))){

            boolean terminar = false;

            while(!terminar){

                System.out.println("1: Crear partida");
                System.out.println("2: Unirse a partida");
                System.out.println("3: Instrucciones");
                System.out.println("4: Salir");

                String opcion = teclado.readLine();

                switch (opcion) {
                    case "1":
                        try{
                        
                            int puerto = crearPartida(teclado);
                            unirseAPartida(teclado,"localhost",puerto);

                        }catch(IOException e){
                            System.out.println("Error al crear partida");
                        }
                        break;
                
                    case "2":
                        String IP = "";
                        boolean IPvalida = false;
                        while(!IPvalida){
                            System.out.println("Introduce la IP en la que se encuentra la partida (localhost por defecto)");
                            IP = teclado.readLine();
                            if(!IP.isBlank()){
                                try{
                                    InetAddress.getByName(IP);
                                    IPvalida = true; 
                                }catch(UnknownHostException e){
                                System.out.println("IP no válida");
                                }
                            }
                            else{
                                IP = "localhost";
                                IPvalida = true; 
                            }
                            
                        }

                        int puerto = 0;
                        while(!(puerto > 0)){
                            System.out.println("Introduce el puerto en la que se encuentra la partida");
                            String leido = teclado.readLine();
                            try{
                                puerto = Integer.parseInt(leido);
                                if(!(puerto > 0)){
                                    System.out.println("Número de puerto no válido");
                                }
                            }catch(NumberFormatException e){
                                System.out.println("Entrada no válida");
                            }
                        }

                        unirseAPartida(teclado, IP, puerto);
                        break;

                    case "3":
                        reglasEscoba();
                        break;

                    case "4":
                        terminar = true;
                        break;
                    
                    default:
                        System.out.println("Entrada incorrecta");
                        break;
                }

            }

        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    
    public static int crearPartida(BufferedReader teclado) throws IOException{
        int numeroJugadores = 0;
            int numeroIA = 0;
            while(numeroJugadores + numeroIA == 0){
                numeroJugadores = -1;
                while(!(numeroJugadores >= 0)){
                    System.out.println("Introduce el número de jugadores:");
                    String leido = teclado.readLine();
                    try{
                        numeroJugadores = Integer.parseInt(leido);
                        if(!(numeroJugadores >= 0)){
                            System.out.println("Número no válido de jugadores");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Entrada no válida");
                    }
                    
                }

                numeroIA = -1;
                while(!(numeroIA >= 0)){
                    System.out.println("Introduce el número de bots:");
                    String leido = teclado.readLine();
                    try{
                        numeroIA = Integer.parseInt(leido);
                        if(!(numeroIA >= 0)){
                            System.out.println("Número no válido de bots");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Entrada no válida");
                    }
                }
                if(numeroJugadores + numeroIA == 0 ){
                    System.out.println("El número total de jugadores debe ser mayor que 0");
                }
            }
            int puerto = 0;
            while(!(puerto > 0)){
                System.out.println("Introduce el puerto en el que crear  la partida:");
                String leido = teclado.readLine();
                try{
                    puerto = Integer.parseInt(leido);
                    if(!(puerto > 0)){
                        System.out.println("Número de puerto no válido");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Entrada no válida");
                }
            }

            Thread th = new Thread(new HiloCrearPartida(puerto,numeroJugadores,numeroIA));

            th.start();

            return puerto;
    }

    private static void unirseAPartida(BufferedReader teclado,String IP,int puerto) {
        Cliente.jugarPartida(IP, puerto);
    }

    private static void reglasEscoba(){
        System.out.println("Reglas:");
        System.out.println("El objetivo del juego es obtener el máximo número de puntos. Cuando un jugador llega a 21, gana el jugador con más puntos");
        System.out.println("Los puntos se obtienen en función de las cartas robadas");
        System.out.println("Cada turno, el jugador debe usar una de las cartas de su mano");
        System.out.println("Con esa carta, el jugador puede robar cartas de la mesa, siempre que la suma de la carta usada mas las cartas robadas sea 15");
        System.out.println("También se puede no robar ninguna carta, en este caso, la carta usada por el jugador queda en la mesa");
        System.out.println("Si se roban todas las cartas de la mesa en una jugada se obtiene una ESCOBA, con lo que se consigue un punto");
        System.out.println("Al acabar una ronda, obtendrán un punto adicional el jugador que más cartas haya robado, el que más sietes haya robado, y el que mas oros haya robado");
        System.out.println("Además, el jugador que haya robado el 7 de oros, obtendrá un punto extra");
    }

}
