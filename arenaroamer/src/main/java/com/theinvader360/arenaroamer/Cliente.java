package com.theinvader360.arenaroamer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import sun.rmi.runtime.Log;

/**
 * Created by KarinaRomero on 24/04/2016.
 */
public class Cliente implements Runnable {

    String ip;
    int puerto,dir ;
    String response = "";
    float delta;


    Cliente(String ip, int puerto, float delta, int dir) {
        this.ip = ip;
        this.puerto = puerto;
        this.delta= delta;
        this.dir=dir;
    }

    @Override
    public synchronized void run() {

        Socket socket = null;

        try {
            socket = new Socket(ip, puerto);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            PrintStream printStream = new PrintStream(os);
            printStream.println(delta);
            printStream.println(dir);
            printStream.close();


			/*
			 * notice: inputStream.read() will block if no data return
			 */
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }
            socket.close();


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            response = "IOException: " + e.toString();
        }
    }
}
