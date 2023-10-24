import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class UserInform {
    public static void main(String[] args) throws IOException {
        int num2;
        String search;
        String num, name, tel, email;
        Scanner s = new Scanner(System.in);
        PrintWriter in = new PrintWriter(new FileWriter("user.txt", true)); 
        ArrayList<String> users = new ArrayList<>();
        Scanner fileReader = new Scanner(new BufferedReader(new FileReader("user.txt")));
        
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            users.add(line);
        }
        
        while (true) {
            System.out.println("번호: ");
            num = s.next();
            System.out.println("이름: ");
            name = s.next();
            System.out.println("전화번호: ");
            tel = s.next();
            System.out.println("이메일: ");
            email = s.next();
            in.print(num + "," + name + "," + tel + "," + email);
            in.println(); 
            in.flush();
            users.add(num + "," + name + "," + tel + "," + email);
            System.out.println("입력을 끝났으면 1, 계속하려면 0: ");
            num2 = s.nextInt();
            if (num2 == 1)
                break;
        }
        
        in.close();

        System.out.println("검색할 번호를 입력하세요: ");
        search = s.next();
        for (String userEntry : users) {
            String[] userInfo = userEntry.split(",");
            if (userInfo.length >= 3 && userInfo[0].equals(search)) {
                System.out.println("검색된 전화번호: " + userInfo[2]);
                break; 
            }
        }
    }
}
