package ru.eltech.kursovaya.client;

import ru.eltech.Database;
import ru.eltech.kursovaya.api.ShopCentre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

public class ShopCentrePanel extends JPanel {

    static JList list;

    static final DefaultListModel dfm = new DefaultListModel<>();
    static JFrame dialogFrame = new JFrame();
    static JTextField textField;
    static JButton button = new JButton("Ok");
    static class MyDialog extends JDialog {
        public MyDialog() {
            super(dialogFrame, "Добавление ТЦ/ТРК", true);
            setBounds(400, 400, 450, 250);
            setLocationRelativeTo(null);
            setLayout(new FlowLayout());

            JTextField name = new JTextField(40);
            add(new JLabel("Введите название"));//, BorderLayout.CENTER);
            add(name);//, BorderLayout.CENTER);

            JTextField jpgPath = new JTextField(40);
            add(new JLabel("Введите путь к изображению"));//, BorderLayout.CENTER);
            add(jpgPath);//, BorderLayout.CENTER);

            JTextField description = new JTextField(40);
            add(new JLabel("Введите описание"));//, BorderLayout.CENTER);
            add(description);//, BorderLayout.CENTER);

            add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    try {
                        Database.main(1, name.getText(), jpgPath.getText(), description.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    dfm.addElement(name.getText());
                    dispose();
                }
            });
            // pack();
        }
    }
    public ShopCentrePanel() {

        JTextArea aboutArea = new JTextArea();
        JpgFrame jpg = new JpgFrame();
        JButton addShopButton = new JButton("Add");
        JButton showInfoButton = new JButton("Показать информацию:");


        addShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDialog dialog = new MyDialog();
                dialog.setVisible(true);
            }
        });

        dfm.clear();
        String[] str_data = Database.getAll();
        for (String s: str_data) {
            dfm.addElement(s);
        }

        JToolBar toolBarLeft = new JToolBar();
        toolBarLeft.setFloatable(false);
        toolBarLeft.add(addShopButton);

        JToolBar toolBarRight = new JToolBar();
        toolBarRight.setFloatable(false);
        toolBarRight.add(showInfoButton);


        JPanel aboutPanel = new JPanel(new BorderLayout()); //BorderFactory.createTitledBorder("About: ")
        aboutPanel.setBorder(BorderFactory.createTitledBorder("About: "));



        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(toolBarLeft, BorderLayout.NORTH);
        list = new JList(dfm);
        JScrollPane listScroll = new JScrollPane(list);
        leftPanel.add(listScroll, BorderLayout.CENTER);

        JPanel rightPanel  = new JPanel(new BorderLayout());
        rightPanel.add(toolBarRight, BorderLayout.NORTH);
        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(list.getSelectedValue());

                    if (list.getSelectedValue() != null) {
                    rightPanel.removeAll();
                    rightPanel.add(toolBarRight, BorderLayout.NORTH);
                    jpg.loadImage(Database.getJpgPath(list.getSelectedValue()));
                    jpg.drawIcon();
                    JPanel decriptionLabel = new JPanel(new BorderLayout());
                    String textLabel = Database.getDescriptionData("ГУМ");
                    JTextField textField1 = new JTextField(textLabel);
                    JLabel label = new JLabel("ЦУМ (Центральный универсальный магазин) — магазин в центре Москвы, расположенный на углу Петровки и Театральной площади по адресу: ул. Петровка, дом 2. Появился здесь в 1885 году как торговый дом «Мюр и Мерилиз», семиэтажное здание в готическом стиле было построено в 1908 году. После революции был национализирован, современное название носит с 1933 года. В 2002 году перешёл в собственность группы компаний Mercury. С общей площадью в 70 000 м² является одним из крупнейших универмагов Европы.\n" +
                            "\n" +
                            "Адрес: ул. Петровка, 2, Москва");//textLabel);//Database.getDescriptionData("ГУМ")); //(Database.getJpgPath(list.getSelectedValue())));
                    decriptionLabel.setBackground(Color.WHITE);
                    decriptionLabel.add(label, BorderLayout.CENTER);


                    rightPanel.add(jpg, BorderLayout.NORTH); //.setSize(rightPanel.getSize());
                   // rightPanel.add(decriptionLabel, BorderLayout.CENTER);
                }
            }
        });


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);

        splitPane.setDividerLocation(200);

        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);

    }
}

