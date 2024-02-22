/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author AlumnoT
 */
public class EjPoker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean salida=false;
        int opcion;
        Scanner sc = new Scanner(System.in);
        
        Mazo mazo = new Mazo();
        mazo.inicializarMazo();
        mazo.barajar();
        
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Juan"));
        jugadores.add(new Jugador("Paco"));
        jugadores.add(new Jugador("Sheila"));
        
        JugadaPoker juego = new JugadaPoker(mazo, jugadores);
        
        do{
            System.out.println("");
            System.out.println("MENU DEL CASINO");
            System.out.println("===================================");
            System.out.println("1. Repartir cartas a los jugadores");
            System.out.println("2. Ver manos de los jugadores");
            System.out.println("3. Repartir cartas comunes");
            System.out.println("4. Mostrar cartas del tablero");
            System.out.println("5. Determinar ganador de la ronda");
            System.out.println("6. Nuevo juego");
            System.out.println("0. Salir");
            System.out.println("");
            System.out.print("Selecciona una opcion: ");
            opcion=sc.nextInt();
            
            switch(opcion){
                case 0:
                    salida=true;
                    break;
                    
                case 1:
                    //Para que solo reparta si no se ha repartido antes
                    if(jugadores.get(0).getMano().isEmpty()){
                        juego.repartirCartas();
                    }else{
                        System.out.println("");
                        System.err.println("LAS CARTAS YA HAN SIDO REPARTIDAS A LOS JUGADORES");
                        System.out.println("");
                    }
                    break;
                    
                case 2:
                    juego.mostrarManos();
                    break;
                    
                case 3:
                    //Para que solo reparta si no se ha repartido antes
                    if(juego.getCartasComunes().isEmpty()){
                        juego.repartirCartasComunes();
                    }else{
                        System.out.println("");
                        System.err.println("LAS CARTAS COMUNES YA HAN SIDO REPARTIDAS");
                        System.out.println("");
                    }
                    break;
                    
                case 4:
                    juego.mostrarCartasComunes();
                    break;
                    
                case 5:
                    juego.determinarGanador(juego);
                    break;
                    
                case 6:
                    mazo.getListaCartas().clear();
                    
                    for(int i=0; i<jugadores.size(); i++){
                        Jugador tmp = jugadores.get(i);
                        tmp.getMano().clear();
                    }
                    
                    juego.getCartasComunes().clear();
                    
                    mazo.inicializarMazo();
                    mazo.barajar();

                    break;
            }
        }while(salida==false);
        
    }
    
}
