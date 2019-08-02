package com.bw.utils;


import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.file.Files;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class ViewController {
   private final static int CANVAS_WIDTH = 342;
    private final static int CANVAS_HEIGHT = 200;

    @FXML
    private Button saveBarcodeButton; // button to save the generated barcode

    @FXML
    private TextField codeTextField;

    @FXML
    private ImageView previewHolder;

    private BarCodeUtilImpl barCodeUtil = new BarCodeUtilImpl();

    @FXML
    public void generateBarcode() throws IOException {
        String text = codeTextField.getText();
        if (StringUtils.isBlank(text)) {
            return;
        }
//        currentCode.setText(text);
        int hPadding = 20;

        BufferedImage propertyOf = new BufferedImage(625, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = propertyOf.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, propertyOf.getWidth(), propertyOf.getHeight());
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 72));
        g2d.drawString("Property of: ", hPadding, 72);
        g2d.dispose();

        BufferedImage byteworks = ImageIO.read(getClass().getResourceAsStream("/logo.png"));
        BufferedImage barCode = ImageIO.read(new ByteArrayInputStream(barCodeUtil.getCode128(text, ImageType.PNG, byteworks.getWidth() * 2, 250)));
        int width = barCode.getWidth();
        BufferedImage code = getCodeBufferedImage(width - 20, text);


        BufferedImage[] images = {propertyOf, byteworks, barCode, code};

        int vSpacing = 20;

        int height = 0;
        for (int i = 0; i < images.length; i++) {
            height += images[i].getHeight();
            if (i < images.length - 1) {
                height += vSpacing;
            }
        }

        BufferedImage concatImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g2d = concatImage.createGraphics();

        g2d.fillRect(0, 0, width, height);
        int heightCurr = 0;
        for (int j = 0; j < images.length; j++) {
            g2d.drawImage(images[j], 10, heightCurr + vSpacing, null);
            heightCurr += images[j].getHeight() + vSpacing;
        }
        g2d.dispose();

        ByteArrayOutputStream imageData = new ByteArrayOutputStream();
        ImageIO.write(concatImage, "png", imageData);
        previewHolder.setImage(new Image(new ByteArrayInputStream(imageData.toByteArray())));



        //start resize
          BufferedImage before = concatImage;
        int w = 370;
        int h = 215;
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(0.3, 0.3);
        AffineTransformOp scaleOp =
                new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(before, after);

        //resize done

        //save the image file
        File outputFile = new File("barcodes/" + codeTextField.getText().replaceAll("[^a-zA-Z0-9]+", "_") + ".png");
        if(!outputFile.getParentFile().exists()){
            Files.createDirectories(outputFile.getParentFile().toPath());
        }
        ImageIO.write(after, "png", outputFile);

        BarcodeController.notify("Success", "Barcode Generated Successfully\n\n" + outputFile.getAbsolutePath());
        codeTextField.setText(""); //clear the textField for another input

    }


    private BufferedImage getCodeBufferedImage(int width, String text) {
        Graphics2D g2d;
        BufferedImage code = new BufferedImage(width, 200, BufferedImage.TYPE_INT_RGB);
        g2d = code.createGraphics();

        int fontSize = 128;
        g2d.setFont(new Font("Arial", Font.BOLD, fontSize));
        FontMetrics fm = g2d.getFontMetrics();
        int charsWidth = fm.charsWidth(text.toCharArray(), 0, text.length());
        System.out.println(charsWidth);
        while (charsWidth>width && fontSize!=1){
            g2d.setFont(new Font("Arial", Font.BOLD, --fontSize));
            fm = g2d.getFontMetrics();
            charsWidth = fm.charsWidth(text.toCharArray(), 0, text.length());
        }

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, 200);
//        g2d.setColor(Color.PINK);
//        g2d.fillRect((width-charsWidth)/2, 0, charsWidth, 200);
        g2d.setColor(Color.BLACK);
        System.out.printf("%s, %d, %d\n", text, (width-charsWidth)/2, 128);
        g2d.drawString(text, (width-charsWidth)/2, 128);
        g2d.dispose();
        return code;
    }



}
