import java.util.ArrayList;
import java.util.Scanner;

public class Ahorcado {
    public static ArrayList<String> palabra = new ArrayList<>();
    public static ArrayList<String> palabra_oculta = new ArrayList<>();
  
    public static void main(String[] args){
        palabra = cargar_palabra();
        palabra_oculta = Ocultar(palabra);
        Boolean jugar_ganar = CicloJuego(palabra_oculta);
        if(jugar_ganar){
            System.out.println("Ganaste!!!");
        }else{
            System.out.println("Perdiste :(");
        }
    }

    public static ArrayList<String> Ocultar(ArrayList<String> lista){
        ArrayList<String> palabra_oculta = new ArrayList<>();
        for (String elemento : lista) {
            palabra_oculta.add("_");
        }
        return palabra_oculta;
    }
    public static ArrayList<String> cargar_palabra(){
        ArrayList<String> palabra = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Boolean finalizar = false;
        System.out.println("Ingrese una palabra por letras, escriba no para finalizar");
        do {
            System.out.println("Ingrese una letra");
            String letra = sc.nextLine();
            if (letra.equals("no")) {
                finalizar = true;
            } else {
                palabra.add(letra);
            }
        } while (finalizar == false);
        return palabra;
    } 
    public static Boolean hay_guiones(ArrayList<String> lista){
        int guiones = 0;
        for (String elemento : lista) {
            if (elemento == "_") {
                guiones++;
                break;
            }
        }
        return guiones > 0;
    }
    public static void mostrar_estado(ArrayList<String> lista, int vidas){
        for (String elemento : lista) {
            System.out.print(elemento + "  ");
        }
        System.out.println("Vidas: " + vidas);
    }
    public static Boolean comprobar_letra(String letra) {
        Boolean hay_cambio = false;
        for (int i = 0; i < palabra.size(); i++) {
            if (letra.equals(palabra.get(i))){
                palabra_oculta.set(i, letra);
                hay_cambio = true;
             }
        }
        return hay_cambio;
    }

    public static void LimpiarPantalla() {
        System.out.print("\u001b[2J\u001b[H");
        System.out.flush();
    }
    
    public static Boolean CicloJuego(ArrayList<String> palabra_oculta){
        int vidas = 5;
        Boolean gano = false;
        Scanner sc = new Scanner(System.in);
        String letra;
        do {
            LimpiarPantalla();
            mostrar_estado(palabra_oculta, vidas);
            System.out.println("Ingrese una letra");
            letra = sc.nextLine();
            Boolean cambio = comprobar_letra(letra);
            if (cambio) {
                System.out.println("Acertaste");
            } else {
                vidas = vidas - 1;
                System.out.println("Fallaste");
            }
        } while (vidas > 0 && hay_guiones(palabra_oculta));
        if (vidas > 0){
            gano = true;
        }
        sc.close();
        return gano;
    } 
}
