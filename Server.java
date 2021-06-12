import java.io.*;
import java.net.*;
import java.text.BreakIterator;
import java.util.*;


public class Server {

    static DatagramSocket socket;
    static byte[] receive = new byte[65535];
    static DatagramPacket dPacket = null;


    public Server(){

        try {
            //CONCORIDA SERVER
            socket = new DatagramSocket(1234);
        } catch (Exception e) {
            e.printStackTrace();
        }
      

        startWriting();
        startReading();

    }

    private void startReading() {

        Runnable read = () ->{

            try {

                System.out.println("Reading is started !");

                while(true){
    
                    dPacket = new DatagramPacket(receive, receive.length);
                    socket.receive(dPacket);
    
                    //convert byte array to string
                    System.out.println("Client : "+ new String(receive));
        
                    if(new String(receive).equals("Exit")){
                        break;
                    }
    
                    receive = new byte[65535];
    
                }
        
               
    
            } catch (Exception e) {
                e.printStackTrace();
            }

        }; 

        new Thread(read).start();


    }

    private void startWriting() {
    }

    public static void main(String[] args) {
            System.out.println("Server is started.");
            new Server();
            System.out.println("Waiting for connection");
      
     

    }
    
}
