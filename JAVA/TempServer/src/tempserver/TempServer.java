/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TempServer {

    private static final int porta = 12345;

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
                DataInputStream in = new DataInputStream(socket.getInputStream());
                float tempC = in.readFloat();
                float tempF = (float)(tempC * 1.8 + 32);
                out.writeFloat(tempF);
                out.flush();
                out.close();
                in.close();
                socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }       
        }
    }
}
