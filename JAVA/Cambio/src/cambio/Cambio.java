/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cambio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Francine
 */
public class Cambio {

private static final int porta = 12347;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(porta);
        System.out.println("Server socket criado... ");
        
        while(true){
            System.out.println("Aguardando conexões... ");
            Socket socket = serverSocket.accept();
            System.out.println("Conectou... ");
            new ServerSocketThread(socket).start();
        }
    }
    
    public static class ServerSocketThread extends Thread{
        private final Socket socket;
        
        public ServerSocketThread(Socket socket){
            this.socket = socket;
        }
        
        @Override
        public void run(){
            super.run();
            try{
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream inR = new DataInputStream(socket.getInputStream());
                DataInputStream inM = new DataInputStream(socket.getInputStream());
                float real = inR.readFloat();
                float moedaO = 0;
                
                switch(inM.readInt()){
                    case 1:{
                        moedaO = real / 3.56f;
                        break;
                    }
                    case 2:{
                        moedaO = real / 3.99f;                        
                        break;                        
                    }
                    case 3:{
                        moedaO = real / 5.19f;                        
                        break;                        
                    }
                    case 4:{
                        moedaO = real / 0.25f;                        
                        break;                        
                    }                    
                }
                        
                out.writeFloat(moedaO);
                out.flush();
                out.close();
                inR.close();
                inM.close();
                socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }       
        }
    }
}
