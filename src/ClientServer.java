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
        BufferedWriter bufferedWriter = getBufferedWriter(socket);
        BufferedReader bufferedReader =  getBufferedReader(fileName, socket);
        outputStream(bufferedWriter, bufferedReader);

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

    private static BufferedWriter getBufferedWriter(Socket socket){
        try {
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            return bufferedWriter;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("invalid socket provided");
        }
        return null;
    }

    private static BufferedReader getBufferedReader(String fileName, Socket socket) {

        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            return bufferedReader;
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot locate requested file");
        } catch (IOException e) {
            System.out.println("Error: Cannot read file");
        }
        return null;
    }
    private static void outputStream(BufferedWriter bufferedWriter,
                                     BufferedReader bufferedReader) throws IOException {

        String readLine = null;
        while((readLine = bufferedReader.readLine())!= null){
            bufferedWriter.write(readLine);
        }
    }
}
