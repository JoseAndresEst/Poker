/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class JugadaPoker {
    //Atributos
    private Mazo m;
    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Carta> cartasComunes;
    
    //Constructor de clase en el que se crea el arraylist de cartas comunes al crear un objeto de la clase JugadaPoker
    public JugadaPoker(Mazo m, ArrayList<Jugador> lJ){
        this.m = m;
        listaJugadores = lJ;
        cartasComunes = new ArrayList<>();
    }
    
    //Metodos
    //Reparte 2 cartas a todos los jugadores
    public void repartirCartas(){
        //Este for es para recorrer el arraylist listaJugadores, y que asi se repartan dos cartas a cada jugador del arrylist
        for(int i=0; i<listaJugadores.size(); i++){
            //Almaceno en una variable temporal el objeto Jugador que toque del arraylist
            Jugador tmp = listaJugadores.get(i);
            //Aqui accedo al metodo de recibir carta dos veces ya que son dos cartas
            //En cada uno, le paso el metodo del mazo cogerCartaBaraja, que devuelve un objeto Carta y que borra la carta del mazo
            tmp.recibirCarta(m.cogerCartaBaraja());
            tmp.recibirCarta(m.cogerCartaBaraja());
        }
    }
    
    //Este metodo es para mostrar las manos de todos los jugadores
    public void mostrarManos(){
        //Recorremos el arraylist para sacar todos los jugadores
        for(int i=0; i<listaJugadores.size(); i++){
            Jugador tmp = listaJugadores.get(i);
            //Aqui accedemos al metodo de la clase Jugador almacenado en la variable tmp en el que nos muestra su mano individual
            tmp.mostrarMano();
        }
    }
    
    //Reparte las cartas comunes
    public void repartirCartasComunes(){
        //Bucle que se repite 5 veces(son 5 cartas comunes)
        for(int i=0; i<5; i++){
            //Barajo porque es lo que pide el ejercicio
            m.barajar();
            //A?ado al arraylist cartas usando el metodo del mazo cogerCartaBaraja, que devuelve un objeto Carta y lo elimina del mazo
            cartasComunes.add(m.cogerCartaBaraja());
        }
    }
    
    //Pausar
    public static void pausa(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e){
        }
    }
    
    //Muestra las cartas comunes
    public void mostrarCartasComunes(){
        //Para que quede mas estetico
        System.out.println("CARTAS COMUNES");
        System.out.println("===================================");
        
        //Recorre el arraylist de las cartasComunes y las va mostrando 1 a 1 con el metodo de la clase Carta imprimir
        for(int i=0; i<cartasComunes.size(); i++){
            Carta tmp = cartasComunes.get(i);
            System.out.print(tmp.imprimir());
        }
    }
    
    //M?todo para evaluar la jugada de un jugador
    public int[] evaluarJugada(ArrayList<Carta> mano, ArrayList<Carta> cartasComunes){
        //jugada[] es lo que nos indicara la propia jugada
        int jugada[] = new int[2];
        //Estas son variables que utilizare para saber que jugada tiene
        int jug=0;
        int v=0;
        int v1=0, v2=0;
        int min1=0, min2=0;
        int cont=0;
        
        /*
        He hecho la siguiente distincion:
        
        Carta mas alta ----- 0
        Pareja ------------- 1
        Trio --------------- 2
        Doble pareja ------- 3
        Full --------------- 4
        Poker -------------- 5
        */
        
        //Aqui saco las dos cartas de la mano que nos han pasado por argumento para compararlas con las cartas comunes
        Carta cm1 = mano.get(0);
        Carta cm2 = mano.get(1);
        
        //Primero con este if compruebo si el valor de las dos cartas de la mano es igual
        if(cm1.getValor()==cm2.getValor()){
            //Si es igual, la minima jugada que tiene el jugador es pareja(1), que lo almaceno en la variable jugada
            //Al mismo tiempo, se descartan algunas jugadas como doble pareja o full
            jug=1;
            //Almaceno el valor de la carta que servira para meterlo luego en jugada[]
            v=cm1.getValor();
            
            //Aqui lo empiezo a comparar con todas las cartas comunes, que voy sacando 1 a 1 y almacenando en una variable temporal
            for(int i=0; i<cartasComunes.size(); i++){
                Carta tmp = cartasComunes.get(i);
                
                //Aqui solo entra si el valor de la carta de la mano es igual que el de la carta comun
                if(cm1.getValor()==tmp.getValor()){
                    //Para saber cuantas veces ha pasado por aqui, hago un switch con una variable contador(que esta inicializado en 0)
                    switch(cont){
                        //Si es la primera vez que se entra en el if, pasara por aqui, ya que el cont=0, y sabemos que es un trio,
                        //ya que las dos cartas de la mano son igual a la que se esta comparando, por lo que jug=2
                        //Tambien aumneto el contador
                        case 0:
                            jug=2;
                            cont++;
                            break;
                            
                        //Para pasar por este case, tiene que haber pasado previamente por el case 1, es decir, que ya tenemos un trio y una carta del mismo valor
                        //que ese trio, o sea, un poker, que lo defino con jug=5. Como otra carta con valor igual es imposible, no hago nada con el contador.
                        case 1:
                            jug=5;
                            break;
                    }
                }
            }
        //Este es el caso en el que la mano del jugador no tiene dos cartas con el mismo valor
        }else{
            //Mismo bucle que arriba para comparar las cartas. En este caso para compararlas con cm1(1? carta de la mano del jugador)
            for(int i=0; i<cartasComunes.size(); i++){
                Carta tmp = cartasComunes.get(i);
            
                //Casi el mismo switch que arriba contemplando: pareja, trio, poker
                //Almaceno la jugada en la variavle min1, que es la minima jugada que se tiene con la cm1(por defecto es 0, es decir, carta mas alta), y almaceno su valor en v1
                //El switch con el contador tiene la misma funcion que el de arriba, saber cuantas veces ha entrado en el if
                if(cm1.getValor()==tmp.getValor()){
                    switch(cont){
                        case 0:
                            min1=1;
                            v1=cm1.getValor();
                            cont++;
                            break;
                            
                        case 1:
                            min1=2;
                            cont++;
                            break;
                            
                        case 2:
                            min1=5;
                            break;
                    }
                }
            }
            
            //Reseteo el contador para utilizarlo en el siguiente bucle(se puede crear otro tambien)
            cont=0;
            
            //Mismo bucle que el anterior pero comparando las cartas comunes con la cm2(2? carta de la mano del jugador)
            for(int i=0; i<cartasComunes.size(); i++){
                Carta tmp = cartasComunes.get(i);
            
                if(cm2.getValor()==tmp.getValor()){
                    switch(cont){
                        case 0:
                            min2=1;
                            v2=cm2.getValor();
                            cont++;
                            break;
                            
                        case 1:
                            min2=2;
                            cont++;
                            break;
                            
                        case 2:
                            min2=5;
                            break;
                    }
                }
            }
        }
        
        //En el caso de que las cartas de la mano sean iguales, meto directament en el array jugada los valores que sean, ya que como he dicho antes, se descartan
        //opciones como doble pareja o full
        if(cm1.getValor()==cm2.getValor()){
            jugada[0]=jug;
            jugada[1]=v;
        }else{
            //Aqui hago un swich con las posibles opciones del valor de min1, que son: 0(carta alta), 1(pareja), 2(trio) o 5(poker)
            //A la vez, en cada case de este switch, hago un switch con min2 para contemplar las combinaciones que hay y decidir la jugada de cada jugador
            switch(min1){
                //min1=carta alta
                case 0:
                    switch(min2){
                        //carta alta + carta alta
                        case 0:
                            //La jugada seria carta alta, y la meto en el array jugada
                            jugada[0]=0;
                            
                            //Esto es para saber cual de las dos cartas de la mano tiene un valor mas alto y meterlo en el array jugada
                            if(cm1.getValor()<cm2.getValor()){
                                jugada[1]=cm2.getValor();
                            }else{
                                jugada[1]=cm1.getValor();
                            }
                            
                            break;
                            
                        //carta alta + pareja
                        case 1:
                            //La jugada seria que mas peso tiene, la pareja, y el valor el de la carta de la pareja(cm2, que ha sido almacenado anteriormente en v2)
                            jugada[0]=1;
                            jugada[1]=v2;
                            
                            break;
                            
                        //carta alta + trio    
                        case 2:
                            //La jugada seria la que mas peso tiene, el trio, y el valor el de la carta del trio(cm2, que ha sido almacenado anteriormente en v2)
                            jugada[0]=2;
                            jugada[1]=v2;
                            
                            break;
                        
                        //carta alta + poker
                        case 5:
                            //La jugada seria la que mas peso tiene, el poker, y el valor el de la carta del poker(cm2, que ha sido almacenado anteriormente en v2)
                            jugada[0]=5;
                            jugada[1]=v2;
                            
                            break;
                    }
                    
                    break;
                    
                //min1 = pareja
                case 1:
                    switch(min2){
                        //pareja + carta alta = pareja
                        case 0:
                            
                            jugada[0]=1;
                            jugada[1]=v1;
                            
                            break;
                        
                        //pareja + pareja = doble pareja
                        case 1:
                            jugada[0]=3;
                            
                            //Cojo el valor mas alto entre las dos parejas
                            if(v1<v2){
                                jugada[1]=v2;
                            }else{
                                jugada[1]=v1;
                            }
                            
                            break;
                        
                        //pareja + trio = full
                        case 2:
                            
                            jugada[0]=4;
                            //Cojo el valor del trio
                            jugada[1]=v2;
                            
                            break;
                            
                        //pareja + poker = poker
                        case 5:
                            jugada[0]=5;
                            //Cojo el valor de la carta del poker
                            jugada[1]=v2;
                            
                            break;
                    }
                    
                    break;
                    
                //min1 = trio
                case 2:
                    switch(min2){
                        //trio + carta alta = trio
                        case 0:
                            jugada[0]=2;
                            jugada[1]=v1;
                            
                            break;
                            
                        //trio + pareja = full
                        case 1:
                            jugada[0]=4;
                            jugada[1]=v1;
                            
                            break;
                            
                        //trio + trio = trio
                        case 2:
                            jugada[0]=2;
                            
                            //Cojo el valor m?s alto entre los dos trios 
                            if(v1<v2){
                                jugada[1]=v2;
                            }else{
                                jugada[1]=v1;
                            }
                            
                            break;
                            
                        //trio + poker = poker
                        case 5:
                            jugada[0]=5;
                            jugada[1]=v2;
                            
                            break;
                    }
                    
                    break;
                
                //min1 = poker (es poker siempre independientemente de min2)
                case 5:
                    jugada[0]=5;
                    jugada[1]=v1;
                            
                    break;
            }
        }
        
        //devuelvo el array jugada[]
        return jugada;
    }
    
    //Aqui compara las jugadas de todos los jugadores pertenecientes a la JugadaPoker pasada por argumento y decide el ganador
    public void determinarGanador(JugadaPoker jugada){
        
        //Saco el primer jugador del arraylist listaJugadores(presupongo que es el ganador y lo voy comparando con los demas jugadores)
        Jugador ganador=listaJugadores.get(0);
        //Tambien saco las dos manos porque el entorno no me deja poner ganador.getMano() en el argumento de evaluarJugada(me da error y no se por que)
        ArrayList<Carta> mg=ganador.getMano();
        
        //El bucle empieza por el indice 1, ya que el del indice 0 lo he cogido antes
        for(int i=1; i<listaJugadores.size(); i++){
            Jugador tmp=listaJugadores.get(i);
            ArrayList<Carta> m1=tmp.getMano();
            
            //Aqui meto ina variable c porque si no lo del if me queda demasiado largo y no me gusta
            //Esto devuelve 1 o 2. Si es 2, la segunda jugada, que es la que no es la del ganador es mejor(entra por el if y se le asigna a ganador el valor de tmp)
            int c=jugada.compararJugadas(jugada.evaluarJugada(mg, cartasComunes), jugada.evaluarJugada(m1, cartasComunes));
            
            if(c==2){
                ganador=tmp;
            }
        }
        
        //Despues de recorrer todos los jugadores y comparar todas las jugadas, imprime el ganador entre todos los jugadores
        System.out.println("");
        System.out.println("GANADOR");
        System.out.println("===================================");
        ganador.mostrarMano();
    }
    
    //Compara las dos jugadas pasadas por argumento
    public int compararJugadas(int[] jugada1, int[] jugada2){
        //Creo una variable g que sera la que devolvera la funcion. Si g=1, la jugada ganadora es la primera pasada por argumento, y si g=2, es la segunda
        int g=0;
        
        //Aqui empiezo a comparar primero las jugadas, en este caso si son iguales (carta alta, pareja, trio,...)
        if(jugada1[0]==jugada2[0]){
            //Si son iguales, el ganador es decidido por el valor mas alto, almacenado en el segundo valor de jugada (jugada[1])
            if(jugada1[1]<jugada2[1]){
                g=2;
            }else{
                g=1;
            }
        }else{
            //Si la jugada no es la misma, la mayor es la ganadora
            if(jugada1[0]<jugada2[0]){
                g=2;
            }else if(jugada1[0]>jugada2[0]){
                g=1;
            }
        }
        
        //Devuelvo g, que es 1 o 2
        return g;
    }

    //Getters y setters
    public Mazo getM() {
        return m;
    }

    public void setM(Mazo m) {
        this.m = m;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public ArrayList<Carta> getCartasComunes() {
        return cartasComunes;
    }

    public void setCartasComunes(ArrayList<Carta> cartasComunes) {
        this.cartasComunes = cartasComunes;
    }
}
