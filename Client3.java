/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaiTapUDP;

import TCP.Student;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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
public class Client3 {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        int port = 1109;
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            
            TCP.Student student = new Student("B15DCCN291");
            
            InetAddress dest = InetAddress.getByName("192.168.1.100");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(student);
            oos.flush();
            sendData = bos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,dest,port);
            socket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length, dest, port);
            socket.receive(receivePacket);
            ByteArrayInputStream bis = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bis);
            TCP.Student s = (TCP.Student)ois.readObject();
            System.out.println(s.getId());
            System.out.println(s.getCode());
            System.out.println(s.getGpa());
            System.out.println(s.getGpaLetter());
            if((3.7<s.getGpa())&&(s.getGpa()<=4.0)){
                s.setGpaLetter("A");
            }
            if((3.0<s.getGpa())&&(s.getGpa()<=3.7)){
                s.setGpaLetter("B");
            }
            if((2.0<s.getGpa())&&(s.getGpa()<=3.0)){
                s.setGpaLetter("C");
            }
            if((1.0<s.getGpa())&&(s.getGpa()<=2.0)){
                s.setGpaLetter("D");
            }
            if((0<s.getGpa())&&(s.getGpa()<=1.0)){
                s.setGpaLetter("F");
            }            
            ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
            ObjectOutputStream oos1 = new ObjectOutputStream(bos1);
            oos1.writeObject(s);
            oos1.flush();
            sendData = bos1.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, dest, port);
            socket.send(sendPacket);
            System.out.println(s.getGpaLetter());
            socket.close();
        } catch (SocketException ex) {
            Logger.getLogger(Client3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
