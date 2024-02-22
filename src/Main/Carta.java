/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author AlumnoT
 */
public class Carta {
    //Atributos
    //He puesto que valor sea int porque es mas facil para despues inicializar el mazo
    private String palo;
    private int valor;
    
    //Constructor de clase
    public Carta(String p, int v){
        palo = p;
        valor = v;
    }
    
    //Metodos
    public String imprimir(){
        String c="";
        
        //Como he hecho que el valor sea int y no string, creo un switch para que se muestre por pantalla 
        //Q, J, K y A en vez de 11, 12, 13 y 14
        if(valor>10){
                switch(valor){
                    case 11:
                        c = ("J "+palo+" ");
                        break;
                        
                    case 12:
                        c = ("Q "+palo+" ");
                        break;
                        
                    case 13:
                        c = ("K "+palo+" ");
                        break;
                        
                    case 14:
                        c = ("A "+palo+" ");
                        break;
                }
            }else{
                c = (valor+" "+palo+" ");
            }
        return c;
    }
    
    //Esta funcion no vale para nada por lo dicho anteriormente(he puesto qeu valor sea int directamente)
    public int getValorNumerico(){
        return valor;
    }
    
    //Getters y setters
    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}
