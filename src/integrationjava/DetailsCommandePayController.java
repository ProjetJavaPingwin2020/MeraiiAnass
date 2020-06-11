/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;


import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import Entity.Commande;
import Entity.FosUser;
import Entity.Produit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Services.CommandeService;
import Services.ProduitCategorieService;
import Services.UserService;
import animatefx.animation.Tada;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import utils.SendSMS;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailsCommandePayController implements Initializable {
    
    
    
      @FXML
    private JFXTextField txt2;

    @FXML
    private JFXTextField txt3;

    @FXML
    private JFXTextArea txt4;

     
      @FXML
    private  Label id_commande;

    @FXML
    private  Label nomprod_commande;

    @FXML
    private Label prixtotal_commande;

    @FXML
    private  Label etat_commande;

    @FXML
    private  Label quantite_commande;

    @FXML
    private  ImageView photoprod_commande;

      @FXML
    private  Label user_commande;
    
    
    private Image image;
    
    @FXML
    private JFXButton payer;
    
    @FXML
    private Label paye;
    
    @FXML
    private JFXButton yassine;
    
        @FXML
    private Pane panedetail;
    
    
    static public Commande c = new Commande();
    
       public static String etat;//variable static
    
    /////////////////////////////////////////////////
      private static String FILE = "Bon de commande.pdf";
      
     
            
    private static Font black = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD, BaseColor.BLACK);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD, BaseColor.RED);
    
     private static Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD, BaseColor.RED);
     
     private static Font boldFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD, BaseColor.ORANGE);
    
     private static Font bleueFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.BLUE);
     
      private static Font magentaFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.MAGENTA);
     
      private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.ORANGE);
      
       private static Font pinkFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.PINK);
       
         private static Font cyanFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.CYAN);
         
          private static Font greenFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.GREEN);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    
 CommandeService s3=new CommandeService();
   ProduitCategorieService s2=new ProduitCategorieService();
UserService s1=new UserService();
    
    public static  LocalDateTime last_change=LocalDateTime.now(); 
    public static int jour;
     public static double price;
    
    
    
    
    
    
    
     public void Pay(ActionEvent e) throws SQLException
    {
       
        
        last_change=LocalDateTime.now();
        
         jour=last_change.getDayOfMonth();
         
       if (FXMLPanierController.etat.equals("Payée"))
       {
           JOptionPane.showMessageDialog(null, "Erreur ! Commande déjà Payée");
       }
       else 
       {
         
            try {
                s3.PayerCommande(FXMLPanierController.idcommande1);
                price=s3.findprixbyid(FXMLPanierController.idcommande1);
                
               s2.ajouter_revenu();
            
             
                  s3.ajouter_notif();
              
                  try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("DetailsCommandePay.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                         
            
          
        
            } catch (SQLException ex) {
                Logger.getLogger(DetailsCommandePayController.class.getName()).log(Level.SEVERE, null, ex);
            }
         JOptionPane.showMessageDialog(null, "Commande Payée");
         FXMLProduitCategorieController.revenu+=s3.findprixbyid(FXMLPanierController.idcommande1);
        
           ((Node) e.getSource()).getScene().getWindow().hide();
          
         
         
        
                          

       }
    }
    
  
   
    
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

          int idd = FXMLPanierController.idcommande1;
       
        //String yassine = FXMLDocumentController.missionsel.getYassine();
        Commande c = new Commande();
        
         
         Produit p = new Produit();
         ProduitCategorieService ps = new ProduitCategorieService();
         
         
        
        this.id_commande.setText(String.valueOf(idd));
         CommandeService cs = new CommandeService();
        c =cs.findcommandebyid(idd);
        id_commande.setText(""+c.getId());
          try {
              nomprod_commande. setText(""+ps.getnomprod(c.getProduit())); //attentionnnn
              
             
              
          } catch (SQLException ex) {
              Logger.getLogger(DetailsCommandePayController.class.getName()).log(Level.SEVERE, null, ex);
          }
        prixtotal_commande.setText(""+c.getPrixtotal());
        
         quantite_commande.setText(""+c.getQuantite());
         
          etat_commande.setText(""+c.getEtat());
          
          paye.setText(""+c.getPay());
         
         
       
       
          String a=null;
          try {
              a = ps.getnomimage(c.getProduit());
               Image image = new Image("file:src/images/"+a+"",70, 70, true, true);
                
                photoprod_commande.setImage(image);
          } catch (SQLException ex) {
              Logger.getLogger(DetailsCommandePayController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
     
     
    }   
    
   
  
 
   
    //////////////////////////PDF//////////////////////////////
    
      @FXML
    void pdf(ActionEvent event) {
        
 try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
           
            document.close();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText("pdf");
         alert.setContentText("Votre bon de commande est pret à imprimer ");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
     private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
        
    }

    private static void addTitlePage(Document document)
            throws DocumentException, BadElementException, IOException {
        
        
      
     
         //image.setAbsolutePosition(0, 800);
         
        
         CommandeService cs = new CommandeService();
        c =cs.findcommandebyid(FXMLPanierController.idcommande1);
           
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("                                                          La Commande de " +c.getNom_client(), black));

        addEmptyLine(preface, 7);
        
         
        preface.add(new Paragraph(
                   "             *Référence de la Commande    "+ c.getId(),
                black));
        

        addEmptyLine(preface, 2);
        
       
           preface.add(new Paragraph
        ("             *Nom du Produit Commandé:        "   +c.getNom_prod() , black));
        
         addEmptyLine(preface, 2);
         
           preface.add(new Paragraph
        ("             *Quantite de la Commande:        "  +c.getQuantite() ,black));
          
           addEmptyLine(preface,2);
           
          
           
            preface.add(new Paragraph
        ("            *La Commande:                    "  +c.getPay() ,black));
            
              addEmptyLine(preface,2);
           
            preface.add(new Paragraph
        ("            *Etat de la Commande:            "  +   c.getEtat() ,black));

         
                addEmptyLine(preface,2);
           
            preface.add(new Paragraph
        ("            *Prix Total de la commande:                   "  +c.getPrixtotal() ,black));
        
        addEmptyLine(preface, 3);
        
        preface.add(new Paragraph
        ("                         *HuntKingdom vous remercie de votre confiance !                   "   ,black));
        
        addEmptyLine(preface, 6);
        
        document.add(new Paragraph("                                                                                                                 "));
         document.add(new Paragraph("                                                                                                                 "));
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        
        
        document.add(preface);
        // Start a new page
        
        document.newPage();
    }

   


    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    
    @FXML
    void retour(MouseEvent e) {
        try {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLPanier.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLyassineController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) e.getSource()).getScene().getWindow().hide();

    }
           
    
    @FXML
    void panedetail(MouseEvent event) {
         new Tada(panedetail).play();

    }  
}
