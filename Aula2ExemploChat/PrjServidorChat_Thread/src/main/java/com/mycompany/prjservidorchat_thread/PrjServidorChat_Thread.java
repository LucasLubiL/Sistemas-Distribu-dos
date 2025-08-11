package com.mycompany.prjservidorchat_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Lucas
 */
public class PrjServidorChat_Thread extends Thread{
    
    private static Vector clientes;
    private Socket conexao;
    private String meuNome;
    
    public PrjServidorChat_Thread(Socket ss) {
        this.conexao = ss;
    }

    public static void main(String[] args) {
        
       clientes = new Vector();
       
       try{
       
           //instacncia de socket que fica escutando na porta 222
           ServerSocket ss= new ServerSocket(2222);
           
           while(true){
           
               /*aguarda algum cliente se conectar . O metodos accept()
                 segura a execução ate um cliente se conectar; */   
               
               System.out.println("Esperando um cliente realizar a conexao...");
               Socket con = ss.accept();
               System.out.println("Conexao realizada!!!");
               
               Thread t = new PrjServidorChat_Thread(con);
               t.start();
           }
       
       }catch(IOException ex){
           ex.printStackTrace();
       }
        
    }
    
    //execuç~~ao da thread
    @Override
    public void run(){
    
        try{
         
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            
            meuNome = entrada.readLine();
            
            if(meuNome == null){
                return;
            }
            
            clientes.add(saida);
            String linha = entrada.readLine();
            
            while(linha != null && !(linha.trim().equals(""))){
            
                enviarParaTodos(saida, " falou: ", linha);
                linha = entrada.readLine();
            
            }
            
            enviarParaTodos(saida, " saiu ", " do chat");
            clientes.remove(saida);
            conexao.close();
            
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
    
    }
    
    public void enviarParaTodos(PrintStream saida, String acao, String linha){
    
        Enumeration e = clientes.elements();
        
        while(e.hasMoreElements()){
        
            PrintStream chat = (PrintStream) e.nextElement();
            if(chat!=saida){
            
                chat.println(meuNome+acao+linha);
                
            }
        
        }
    
    }
    
}
