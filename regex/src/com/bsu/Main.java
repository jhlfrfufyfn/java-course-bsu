package com.bsu;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try(Scanner sc = new Scanner(new File("data/text.txt"))){
            String line = sc.nextLine();
            if(checkEmail(line)) {
                System.out.println(line);
            }
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
    static boolean checkEmail(String line){
        return line.matches("^\\w[\\w-\\.]*@[\\w]+\\.[\\w]{2,4}");
    }
}
