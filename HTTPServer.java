import java.io.*;
import java.net.*;
import java.util.*;

public class HTTPServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Server Start");
        int port = 8081;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server on port:" + port);
        while(true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("clinet connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String s;
            while((s = in.readLine())!= null) {
                System.out.println(s);
                if(s.isEmpty()) {
                    break;
                }
            }
            OutputStream clientOutput = clientSocket.getOutputStream();
            clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("Welcome to the server".getBytes());
            clientOutput.write("\r\n\r\n".getBytes());
            clientOutput.flush();
            System.err.println("Closed connection");
            in.close();
            clientOutput.close();
        }
    }
}
