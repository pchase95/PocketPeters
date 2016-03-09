import java.util.*;
import java.io.*;
import java.net.*;

public class Server{
//Globals
public static List<Socket> ConnectionArray = new ArrayList<>();
public static List<String> CurrentUsers = new ArrayList<>();

  public static void main(String[] args){
    try{
      final int port = 4324;
      ServerSocket ss = new ServerSocket(port);
      System.out.println("Waiting for clients...");

      while(true){
        Socket sock = ss.accept();
        ConnectionArray.add(sock);
        System.out.println("Client connected from: " + sock.getLocalAddress().getHostName());

         AddUser(sock);
         Server_Return server_return = new Server_Return(sock);
         Thread t = new Thread(server_return);
         t.start();
      }
    }catch(Exception e){
      System.out.println(e);
    }
  }

  private static void AddUser(Socket skt) throws IOException {
    Scanner input = new Scanner(skt.getInputStream());
    String user_name = input.nextLine();
    CurrentUsers.add(user_name);

    for(int i = 1; i <= ConnectionArray.size(); i++){
      Socket temp_sock = ConnectionArray.get(i-1);
      PrintWriter out = new PrintWriter(temp_sock.getOutputStream());
      out.println("#?!" + CurrentUsers);
      out.flush();
    }
  }


}
