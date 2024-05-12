import java.util.ArrayList;
import java.util.Scanner;

public class Ahorcado {
    // Declaracion de listas
    public static String[] imagenes = {
        "   _______\n   |     |\n   |     \n   |    \n   |      \n",
        "   _______\n   |     |\n   |     \n   |    \n   |      \\   \n",
        "   _______\n   |     |\n   |     \n   |    \n   |    / \\   \n",
        "   _______\n   |     |\n   |     \n   |      \\ \n   |    / \\   \n",
        "   _______\n   |     |\n   |     \n   |    / \\ \n   |    / \\   \n",
        "   _______\n   |     |\n   |     \n   |    /|\\ \n   |    / \\   \n",
        "   _______\n   |     |\n   |     O\n   |    /|\\ \n   |    / \\   \n",
    };
    public static ArrayList<String> palabra;
    public static ArrayList<String> palabra_oculta = new ArrayList<>();
  

    // Función que corre el programa
    public static void main(String[] args){
        palabra = cargar_palabra();
        palabra_oculta = Ocultar(palabra);
        Boolean jugar_ganar = CicloJuego(palabra_oculta);
        if(jugar_ganar){
            System.out.println("Ganaste!!!");
            System.out.println("La palabra es:");
            Diseno.separadores();
            Diseno.mostrar_letras(palabra);
        }else{
            Diseno.arte_asi(0);
            Diseno.separadores();
            System.out.println("Perdiste :(");
            Diseno.separadores();
            System.out.println("La palabra era");
            Diseno.separadores();
            Diseno.mostrar_letras(palabra);
        }
    }


    // El usuario ingresa una palabra y el método lo transforma en una lista de caracteres de tipo string
    public static ArrayList<String> cargar_palabra(){
        ArrayList<String> palabra_enviar = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese una palabra");
        String palabra_ingresada = sc.nextLine();
        for (int i = 0; i < palabra_ingresada.length(); i++) {
            palabra_enviar.add(String.valueOf(palabra_ingresada.toUpperCase().charAt(i)));
        }
        return palabra_enviar;
    } 


    //Cambia cada elemento de la lista que contiene la palabra por un guión y devuelve la lista obtenida
    public static ArrayList<String> Ocultar(ArrayList<String> lista){
        ArrayList<String> palabra_oculta = new ArrayList<>();
        for (String elemento : lista) {
            if (elemento.equals(" ")) {
                palabra_oculta.add(elemento);
            } else {
                palabra_oculta.add("_");
            } 
        }
        return palabra_oculta;
    }



    // Verifica si hay guiones en la lista
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


    public static class Diseno {

        public static void arte_asi(int vidas){
            switch (vidas) {
                case 0:
                    System.out.print(imagenes[6]);
                    break;
                case 1:
                    System.out.print(imagenes[5]);
                    break;
                case 2:
                    System.out.print(imagenes[4]);
                    break;
                case 3:
                    System.out.print(imagenes[3]);
                    break;
                case 4:
                    System.out.print(imagenes[2]);
                    break;
                case 5:
                    System.out.print(imagenes[1]);
                    break;
                case 6:
                    System.out.print(imagenes[0]);
                default:
                    break;
            }
        }

        public static void mostrar_letras(ArrayList<String> lista){
            for (String elemento : lista) {
                System.out.print(elemento + "  ");
            }
        }


        public static void mostrar_letras_vidas(ArrayList<String> lista, int vidas){
            mostrar_letras(lista);
            System.out.println("Vidas: " + vidas);
        }
        public static void separadores(){
            System.out.println(" ");
            System.out.println(" ");
        }
        
    } 



    // Sirve para mostrar el estado del juego
    public static void mostrar_estado(ArrayList<String> lista, int vidas){
        Diseno.arte_asi(vidas);
        Diseno.separadores();
        Diseno.mostrar_letras_vidas(lista, vidas);
    }


    // Comprueba si la letra está en la palabra, si es así la hace aparecer
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
    

    //Controla el juego en sí
    public static Boolean CicloJuego(ArrayList<String> palabra_oculta){
        int vidas = 6;
        Boolean gano = false;
        Scanner sc = new Scanner(System.in);
        String letra;
        do {
            LimpiarPantalla();
            mostrar_estado(palabra_oculta, vidas);
            System.out.println("Ingrese una letra");
            letra = sc.nextLine().toUpperCase();
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
