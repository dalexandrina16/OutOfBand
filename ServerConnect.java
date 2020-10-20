package outofband;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnect {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(61234);
        Socket s = ss.accept();
        s.shutdownOutput();
        s.setOOBInline(true);
        InputStream is = s.getInputStream();
        StringBuilder acceptedPassword = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ready to take the password");
        for (;;) {
          byte []d = new byte[10];
          int l = is.read(d);
          if (l==-1) break;
          for (int i=0; i<l; i++) acceptedPassword.append((char)d[i]);
          Thread.sleep(2000);
        }
        String inputPassword = "";
        int attempts = 0;
        while(attempts <= 5) {
            System.out.println("Please enter the password: ");
            inputPassword = sc.nextLine();
            if(inputPassword.equals(acceptedPassword.toString().substring(3))) {
                System.out.println("You logged in successfully");
                break;
            }
            System.out.println("Wrong, " + (5 - attempts) + " attempts left");
            attempts++;
            if(attempts > 5) {
                System.err.println("You entered a wrong password too many times, session is closed");
            }
        }
        ss.close();
    }
}