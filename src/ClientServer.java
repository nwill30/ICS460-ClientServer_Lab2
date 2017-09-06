import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientServer {

    public static void main(String args[])throws Exception{

        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("server is ready");

        Socket socket = serverSocket.accept();
        String fileName = readStream(socket);

        socket.close();
        serverSocket.close();
    }

    private static String readStream(Socket socket){

        InputStream streamIn = null;
        try {
            streamIn = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("invalid socket provided");
        }
        Scanner dataInStream = new Scanner(streamIn);
        String message = dataInStream.nextLine();
        dataInStream.close();
        try {
            streamIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot close input stream");
        }
        return message;
    }

    private static String outputFile(String fileName){

        File file = new File(fileName);
        String readLine = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while((readLine = bufferedReader.readLine())!= null){
                System.out.println(readLine);
            }
            return "hi";
        } catch (FileNotFoundException e) {
            return "Error: Cannot locate requested file";
        } catch (IOException e) {
            return "Error: Cannot read file";
        }

    }
}
