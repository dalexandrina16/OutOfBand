package outofband;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

public class SendUrgent {  
    public static void main(String[] args) throws IOException {  
        Socket s = new Socket("localhost",61234);
        s.shutdownInput();
        OutputStream os = s.getOutputStream();
        byte []n = {'p', 'w', 'd'};
        byte []password = {'0', 'o', 'l', '.', '8'};   
        os.write(n);
        for (int i = 0; i < password.length; i++) {
            s.sendUrgentData(password[i]);
        }
        System.out.println("OOB sent");
    }  
}
