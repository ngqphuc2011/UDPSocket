/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaiTapUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Client {
    public static int UCLN(int a,int b){
        while(a!=b){
            if(a>b){
                a = a -b;
            }else{
                b = b-a;
            }
        }
        return a;
    }
    public static int BCNN(int a,int b){
        return ((a*b)/UCLN(a,b));
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException{
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            int port;
            port = 1107;
            String s = ";B15DCCN291";
            sendData = s.getBytes();
            InetAddress dest = InetAddress.getByName("192.168.1.100");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,dest,port);
            socket.send(sendPacket);
            String receiveString = "";
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length,dest,port);
            socket.receive(receivePacket);
            String result = new String(receivePacket.getData());
            System.out.println(result);
            String [] part =result.split(";");
            String [] numPart = part[1].split(",");
            int a = Integer.parseInt(numPart[0]);
            int b = Integer.parseInt(numPart[1].trim());
            
            String sendString = new String(part[0]+";"+UCLN(a,b)+","+BCNN(a,b)+","+(a+b)+","+(a*b));
            sendData = sendString.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length,dest,port);
            socket.send(sendPacket);
            socket.close();
            
            
            
            System.out.println(part[0]+";"+UCLN(a,b)+","+BCNN(a,b)+","+(a+b)+","+(a-b));
            System.out.println(a);
            System.out.println(b);
            
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
