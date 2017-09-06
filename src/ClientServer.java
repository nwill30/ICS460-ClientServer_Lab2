import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientServer {

    public static void main(String args[])throws Exception{

        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("server is ready");

        Socket socket = serverSocket.accept();

        InputStream streamIn = socket.getInputStream();
        Scanner dataInStream = new Scanner(streamIn);

        String message = dataInStream.nextLine();
        System.out.println(message);
        dataInStream.close();
        streamIn.close();
        socket.close();
        serverSocket.close();
    }
}
