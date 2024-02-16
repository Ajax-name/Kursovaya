package ru.eltech.kursovaya.client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JpgFrame extends JPanel {

    private int width = 30;
    private int height = 30;

    private BufferedImage bufferedImage;
    private Image img;
    private Image image;
    public JpgFrame() {

        setSize(width, height);
        setBackground(Color.WHITE);
        setVisible(true);

    }

    public void loadImage(String src) {
        img = new ImageIcon(src).getImage();
        bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(src));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        image = bufferedImage.getScaledInstance(730, 400, Image.SCALE_DEFAULT);
    }

    public void drawIcon() {
        ImageIcon icon = new ImageIcon(image);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(icon);
        add(jLabel);
    }
}