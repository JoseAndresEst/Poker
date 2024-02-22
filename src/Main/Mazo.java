/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    //Atributo
    private ArrayList<Carta> listaCartas;
    
    //Constructor(se inicializa el arraylist sin necesidad de pasarle nada)
    public Mazo(){
        listaCartas = new ArrayList<>();
    }

    //Metodos
    
    //Con un for voy creando todas las cartas a partir del 2 hasta el 14
    public void inicializarMazo(){
        for(int i=2; i<=14; i++){
            listaCartas.add(new Carta("Corazones",i));
            listaCartas.add(new Carta("Diamantes",i));
            listaCartas.add(new Carta("Picas",i));
            listaCartas.add(new Carta("Treboles",i)); 
        }
    }
    
    //Utilizo la funcion shuffle para mezclar las cartas
    public void barajar(){
        Collections.shuffle(listaCartas);
    }
    
    //Cojo las cartas 1 a 1 con un for y llamo al metodo imprimir de la clase carta
    public void mostrarBaraja(){
        for(int i=0; i<listaCartas.size(); i++){
            Carta tmp = listaCartas.get(i);
            System.out.print(tmp.imprimir()+"| ");
        }
    }
    
    //Este metodo copia el valor del primer elemento de la lista en uno temporal para despues borrarlo del arraylist y devolver ese valor
    //Sera util despues para repartir las cartas a los jugadores y las cartas comunes
    public Carta cogerCartaBaraja(){
        Carta c = listaCartas.getFirst();
        listaCartas.remove(0);
        return c;
    }
    
    //Getters y setters
    public ArrayList<Carta> getListaCartas() {
        return listaCartas;
    }

    public void setListaCartas(ArrayList<Carta> ListaCartas) {
        this.listaCartas = ListaCartas;
    }
    
    
}
