package com.mycompany.prjthread_exemplo;

import Classes.ClasseContA;
import Classes.ClasseContThreadB;
import Classes.ClasseContThreadC;

/**
 *
 * @author Lucas
 */
public class PrjThread_Exemplo {

    public static void main(String[] args) {

        //Nao utiliza thread
        //new ClasseContA("Teste I", 100).contador();
        //new ClasseContA("Teste II", 100).contador();
        
        
        //utiliza thread
        //Thread A = new ClasseContThreadB("Teste I", 100);
        //Thread B = new ClasseContThreadB("Teste II", 100);
        
        //A.start();
        //B.start();
        
        Thread A = new Thread(new ClasseContThreadC("Teste I", 100));
        Thread B = new Thread(new ClasseContThreadC("Teste II", 100));
        
        A.start();
        B.start();
    }
}
