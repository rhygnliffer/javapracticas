import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args){
        int[] arreglito = {1, 2, 3, 4 ,5, 6, 7, 8, 9, 10};
        int cont = 0;
        System.out.println("Que quiere ver? escriba 1 para pares, 2 para inpares ");
        Scanner sc = new Scanner(System.in);
        int respuesta = sc.nextInt();
        sc.close();
        boolean par_inpar = respuesta == 1;
        if(par_inpar){
            for (int i : arreglito) {
                if(i % 2 == 0){
                    cont++;
                }
                
            }
            
            int[] pares = new int[cont];
            cont = 0;
            for (int i : arreglito) {
                if (i % 2 == 0){
                    pares[cont] = i;
                    cont++;
                }
            }
    
    
            for (int i : pares) {
                System.out.println(i);
            };
        }else{
            for (int i : arreglito) {
                if(i % 2 != 0){
                    cont++;
                }
                
            }
            
            int[] inpares = new int[cont];
            cont = 0;
            for (int i : arreglito) {
                if (i % 2 != 0){
                    inpares[cont] = i;
                    cont++;
                }
            }
    
    
            for (int i : inpares) {
                System.out.println(i);
            };
        }
    }
}
