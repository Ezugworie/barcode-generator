/*    */ package com.bw.utils;
/*    */ 
/*    */ import javafx.application.Application;
/*    */ import javafx.fxml.FXMLLoader;
/*    */ import javafx.scene.Parent;
/*    */ import javafx.scene.Scene;
/*    */ import javafx.stage.Stage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BWBarCode
/*    */   extends Application
/*    */ {
/*    */   public void start(Stage stage)
/*    */     throws Exception
/*    */   {
/* 22 */     Parent root = (Parent)FXMLLoader.load(getClass().getResource("/Application.fxml"));
/*    */     
/* 24 */     Scene scene = new Scene(root);
/*    */     
/* 26 */     stage.setScene(scene);
/* 27 */     stage.show();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 34 */     launch(args);
/*    */   }
/*    */ }


/* Location:              /home/byteworks/Documents/My Projects/BarCode/BWBarCode.jar!/com/bw/utils/BWBarCode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */