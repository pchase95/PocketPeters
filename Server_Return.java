import java.io.*;
import java.net.*;
import java.util.*;

public class Server_Return implements Runnable {
  //Globals
  private Socket sock;
  private Scanner input;
  private PrintWriter out;
  String msg = "";

  public Server_Return(Socket sock){
    this.sock = sock;
  }

  private void CheckConnection() throws IOException {
    if(!sock.isConnected()){
      for(int i = 1; i < Server.ConnectionArray.size(); i++){
        if(Server.ConnectionArray.get(i) == sock){
          Server.ConnectionArray.remove(i);
        }
      }
    }
    for(int i = 1; i < Server.ConnectionArray.size(); i++){
      Socket temp_sock = Server.ConnectionArray.get(i-1);
      PrintWriter temp_out = new PrintWriter(temp_sock.getOutputStream());
      temp_out.println(temp_sock.getLocalAddress().getHostName() + " disconnected");
      temp_out.flush();
      // Print user disconnecting from Server
      System.out.println(temp_sock.getLocalAddress().getHostName() + " disconnected");
    }
  }

  public void run(){
    try{
      try{
        input = new Scanner(sock.getInputStream());
        out = new PrintWriter(sock.getOutputStream());

        while(true){
          CheckConnection();

          if(!input.hasNext()){
            return;
          }

          for(int i = 1; i < Server.ConnectionArray.size(); i++){
            Socket temp_sock = Server.ConnectionArray.get(i-1);
            PrintWriter temp_out = new PrintWriter(temp_sock.getOutputStream());
            temp_out.println(msg);
            temp_out.flush();
            System.out.println("Sent to: " + temp_sock.getLocalAddress().getHostName());
          }
        }
      }
      finally{
        sock.close();
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
  }

}
