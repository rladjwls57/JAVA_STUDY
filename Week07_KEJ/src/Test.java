import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) throws IOException {
        File file1 = new File("src/input.txt");
        File file2 = new File("output.txt");

        BufferedReader in = new BufferedReader(new FileReader(file1));
        PrintWriter out = new PrintWriter(new FileWriter(file2));

        int ch;

        while ((ch = in.read()) != -1) {
            char character = (char) ch;
            if (Character.isLowerCase(character)) {
                character = Character.toUpperCase(character);
            }
            out.write(character);
        }

        in.close();
        out.close();
        System.out.println("File conversion completed.");
    }
}
