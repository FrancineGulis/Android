/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempserver2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TempServer2 {
private static final int porta = 12346;

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
                DataInputStream inT = new DataInputStream(socket.getInputStream());
                DataInputStream inE = new DataInputStream(socket.getInputStream());
                float tempI = inT.readFloat();
                float tempO = 0;
                
                if(inE.readInt() == 1)
                    tempO = (float)(tempI * 1.8 + 32);
                
                else
                    tempO = (float)(tempI - 32 / 1.8);
                 
                out.writeFloat(tempO);
                out.flush();
                out.close();
                inT.close();
                inE.close();
                socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }       
        }
    }

}
