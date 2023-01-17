package movies.controller;

import javax.swing.*;

public class GuiController extends Controller {
    void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    int readInt(String message) {
        try {
            String input = readString(message);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showMessage("Number should be given");
            return readInt(message);
        }
    }

    String readString(String message) {
        return JOptionPane.showInputDialog(message);
    }
}
