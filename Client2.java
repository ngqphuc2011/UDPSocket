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
public class Client2 {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        try {
            DatagramSocket socket = new DatagramSocket();
            int port = 1108;
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            String receiveString = "";
            String s = ";B15DCCN291";
            sendData = s.getBytes();
            InetAddress dest = InetAddress.getByName("192.168.1.100");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,dest,port);
            socket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length,dest,port);
            socket.receive(receivePacket);
            String result = new String(receivePacket.getData());
            String [] part = result.split(";");
            char[] c = part[1].toCharArray();
            String str1 = "";
            String str2 = "";
            for(int i = 0 ; i<c.length; i++){
                if((c[i]>='a')&&(c[i]<='z')||(c[i]>='A')&&(c[i]<='Z')||(c[i]>='0')&&(c[i]<='9')){
                    str1 +=c[i];
                }else{
                    str2 +=c[i];
                }
            }
            String sendString = new String(part[0]+";"+str1+","+str2);
            sendData = sendString.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length,dest,port);
            socket.send(sendPacket);
            System.out.println(result);
            System.out.println(str1);
            System.out.println(str2);
            socket.close();
        } catch (SocketException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
