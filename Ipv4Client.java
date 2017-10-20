//Alex Kimea
//Jerahmeel Calma
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class Ipv4Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("18.221.102.182", 38003)) {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static short checkSum(byte[] b) {
        int sum = 0, count = 0;
        byte firstHalf, secondHalf;

        while(count < b.length - 1) {
            firstHalf = b[count];
            secondHalf = b[count+1];
            sum += ((firstHalf << 8 & 0xFF00) | (secondHalf & 0xFF));
            if((sum & 0xFFFF0000) > 0) {
                sum &= 0xFFFF;
                sum++;
            }
            count += 2;
        }

        if((b.length) % 2 == 1) {
            byte overflow = b[b.length - 1];
            sum += ((overflow << 8) & 0xFF00);
            if((sum & 0xFFFF0000) > 0) {
                sum &= 0xFFFF;
                sum++;
            }
        }
        return (short) ~(sum & 0xFFFF);
    }
}