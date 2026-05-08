package Excecoes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EntradasExceptions {
    public static int lerInteiros(Scanner sc, String texto) {
        while (true) {
            try {
                System.out.print(texto);
                int numero = sc.nextInt();
                sc.nextLine();
                return numero;
            }catch (InputMismatchException e){
                System.out.print("Error inteiro: " + e);
                sc.nextLine();
            }
        }
    }
    public static double lerDouble(Scanner sc,String texto){
        while (true){
            try {
                System.out.print(texto);
                double numero = sc.nextDouble();
                return numero;
            }catch (InputMismatchException e){
                System.out.print("Error double: " + e);
            }
        }
    }
    public static String lerString(Scanner sc,String texto){
        while (true){
            try {
                System.out.print(texto);
                String insert = sc.nextLine();
                return insert;
            }catch (InputMismatchException e){
                System.out.print("Error texto: " +e);
            }
        }
    }
}
