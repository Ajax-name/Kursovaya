package ru.eltech.kursovaya.client;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        super("Торговые центры в различных городах");
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Москва", new ShopCentrePanel());
//        tabbedPane.addTab("Санкт-Петербург", new ShopCentrePanel());
//        tabbedPane.addTab("Барановичи", new JButton("Барановичи"));
        setLayout(new BorderLayout());
        add(tabbedPane);
    }
}
