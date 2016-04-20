import frames.MenuFrame;

import javax.swing.*;
import java.io.*;

public class GameMain {

    public static void main(String[] args) throws IOException {

        MenuFrame frame = new MenuFrame("Infinite Runner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}

