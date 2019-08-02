/*    */
package com.bw.utils;
/*    */
/*    */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class BarcodeController
        /*    */ implements Initializable
        /*    */ {
     private static String barCodeFileName;
     private String barCode;
    /*    */


    public String getBarCode() {
        return barCode.replaceAll("[^a-zA-Z0-9]+", "_");
    }

    public static String getBarCodeFileName() {
        return barCodeFileName.replaceAll("[^a-zA-Z0-9]+", "_");
    }

    @FXML
    /*    */ private TextField barcodeTextField;

    /*    */
    /*    */
            @FXML
    /*    */ private void handleButtonAction(ActionEvent event)
    /*    */ {
        /*    */
        try
            /*    */ {
            /* 34 */
             barCode = this.barcodeTextField.getText();
            /* 35 */
             barCodeFileName = barCode.replaceAll("[^a-zA-Z0-9]", "_");
            /* 37 */
            if (barCode.trim().isEmpty()) {
                /* 38 */
                return;
                /*    */
            }
            /*    */
///* 41 */       Code128Bean bean = new Code128Bean();
///*    */
///* 43 */       bean.setHeight(20.0D);
///* 44 */       bean.setFontSize(5.0D);
///* 45 */       bean.setFontName("Arial Bold");
///* 46 */       bean.setQuietZone(5.0D);
///*    */
///* 48 */       bean.doQuietZone(true);
///*    */
///* 50 */       File outFile = new File("barcodes/" + barCodeFileName + ".png");
///*    */
///* 52 */       OutputStream out = new FileOutputStream(outFile);Throwable localThrowable3 = null;
///* 53 */       try { BitmapCanvasProvider provider = new BitmapCanvasProvider(out, "image/png", 220, 10, true, 0);
///* 54 */         bean.generateBarcode(provider, barCode);
///*    */
///* 56 */         provider.finish();
///* 57 */         out.flush();
///*    */
///* 59 */         this.barcodeTextField.setText("");
///*    */
///* 61 */         notify("Success", "Barcode Generated Successfully\n\n" + outFile.getAbsolutePath());
///*    */       }
///*    */       catch (Throwable localThrowable1)
///*    */       {
///* 52 */         localThrowable3 = localThrowable1;throw localThrowable1;
///*    */
///*    */
///*    */
///*    */
///*    */       }
///*    */       finally
///*    */       {
///*    */
///*    */
///*    */
///* 63 */         if (out != null) if (localThrowable3 != null) try { out.close(); } catch (Throwable localThrowable2) { localThrowable3.addSuppressed(localThrowable2); } else out.close();
///*    */       }



            /*    */
        } catch (Exception e) {
            /* 66 */
            e.printStackTrace();
            /*    */
        }
        /*    */
    }

    /*    */
    /*    */
    public void initialize(URL url, ResourceBundle rb)
    /*    */ {
        /* 72 */
        new File("barcodes").mkdir();
        /*    */
    }

    /*    */
    /*    */
    public static void notify(String title, String message) {
        /* 76 */
        Alert alert = new Alert(AlertType.INFORMATION);
        /* 77 */
        alert.setTitle("Information");
        /* 78 */
        alert.setHeaderText(title);
        /* 79 */
        alert.setContentText(message);
        /* 80 */
        alert.showAndWait();
        /*    */
    }
    /*    */
}


/* Location:              /home/byteworks/Documents/My Projects/BarCode/BWBarCode.jar!/com/bw/utils/BarcodeController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */