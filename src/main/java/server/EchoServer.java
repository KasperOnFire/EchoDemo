package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    private static int PORT = 1234;
    private static String IP = "localhost";
    private static ServerSocket serverSocket;

    public static void handleClient(Socket socket) throws IOException {
        Scanner scan = new Scanner(socket.getInputStream());
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); //DONT FORGET AUTOFLUSH
        //IMPORTANT: BLOCKING
        String msg = scan.nextLine();
        System.out.println("Recieved: " + msg);

        while (!msg.equals("STOP")) {
            pw.println(msg.toUpperCase());
            msg = scan.nextLine(); // IMPORTANT BLOCKING
            System.out.println("Recieved: " + msg);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            IP = args[0];
            PORT = Integer.parseInt(args[1]);
        }
        serverSocket = new ServerSocket(); // remember to bind
        serverSocket.bind(new InetSocketAddress(IP, PORT));
        System.out.println("Waiting for connection...");
        while (true) {
            Socket socket = serverSocket.accept(); // IMPORTANT: BLOCKING CALL!
            System.out.println("Connection made!");
            handleClient(socket);
        }
    }
}
