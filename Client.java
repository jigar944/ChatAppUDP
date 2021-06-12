import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class Client{

    static Scanner input = new Scanner(System.in);
    byte buffer[] = null;
    static DatagramSocket socket;
    static InetAddress ip;
    static DatagramPacket dPacket;
    static ArrayList<String> users = new ArrayList<>();

    public Client(){

        StartReading();
        StartWriting();

    }

    
    public static void main(String[] args) { 

        System.out.println("Enter Username :");
        String user = input.nextLine();

        String str[] = user.split("U",3);

        for(String a : str){
            System.out.println(a);
        }

        if(users.indexOf(user)==-1){
            users.add(user); 
            String server = str[0];
            String number = str[1];
            System.out.println("Server : "+server);
            System.out.println("Number : "+number);
        }else{
            String server = str[0];
            String number = str[1];
            System.out.println("Server : "+server);
            System.out.println("Number : "+number);
        }

       


            try {
                socket = new DatagramSocket();
                ip = InetAddress.getLocalHost();
                new Client();
            } catch (Exception e) {
               e.printStackTrace();
            }    

    }

    public void StartWriting() {

        Runnable write = () ->{


            try {

                System.out.println("writing is started !");

                while(true){

                   
                    String mesg = input.nextLine();

                    //convert string into byte array
                    buffer = mesg.getBytes();
                    dPacket = new DatagramPacket(buffer, buffer.length,ip,1234);

                    //packet for sending data        
                    socket.send(dPacket);

                    if(mesg.equals("exit")){
                        break;
                    }

                }

              
                
            } catch (Exception e) {
                e.printStackTrace();
            }
           

        };

        new Thread(write).start();

    }


    public void StartReading() {
    }

}