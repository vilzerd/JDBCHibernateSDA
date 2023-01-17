package reminder;

import javax.swing.*;
import java.util.Scanner;

public class SimpleGuiPopupsDemo {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        JOptionPane.showMessageDialog(null, "How old are you?");
//        System.out.println("How old are you?");
//        int age = scanner.nextInt();

       int age = Integer.parseInt(JOptionPane.showInputDialog("How old are you?"));
//        System.out.println("You are " + age + " years old.");
        JOptionPane.showMessageDialog(null, "You are " + age + " years old.");
    }
}
