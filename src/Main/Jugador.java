/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.ArrayList;

public class Jugador {
    //Atributos
    private String nombre;
    private ArrayList<Carta> mano;
    private JugadaPoker jugada;
    
    //Constructor
    //Pido que solo sea necesario el nombre del jugador y que se cree aoutomaticamente el arraylist mano
    public Jugador(String n){
        nombre = n;
        mano = new ArrayList<>();
    }
    
    //Metodos
    //Este metodo recibe una carta y la a?ade a la mano del jugador
    public void recibirCarta(Carta carta){
        mano.add(carta);
    }
    
    //Muestra la mano del jugador
    public void mostrarMano(){
        System.out.println("");
        System.out.println(nombre);
        System.out.println("");
        //Para mostrar todas las cartas
        for(int i=0; i<mano.size(); i++){
            Carta tmp = mano.get(i);
            System.out.println(tmp.imprimir());
        }
        System.out.println("===================================");
    }

    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public JugadaPoker getJugada() {
        return jugada;
    }

    public void setJugada(JugadaPoker jugada) {
        this.jugada = jugada;
    }
    
    
}
