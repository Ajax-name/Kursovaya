package ru.eltech.kursovaya.client;

import ru.eltech.Database;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Error: NimbusLookAndFeel");
        }
        MainFrame frame = new MainFrame();
        frame.setSize(1200,800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
