package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {

    private static int PORT = 1235;
    private static String IP = "localhost";
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            IP = args[0];
            PORT = Integer.parseInt(args[1]);
        }
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(IP, PORT));
        System.out.println("Waiting for Conenction");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Connection made!");
            handleClient(socket);
        }
    }

    public static void handleClient(Socket socket) throws IOException {
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); //DONT FORGET AUTOFLUSH
        Date now = new Date();
        pw.println(now.toString());
    }

}
