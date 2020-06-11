/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationjava;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.ConnexionBase;

/**
 *
 * @author USER
 */
public class genererpdf {
     public static void main(String[] args) throws FileNotFoundException, DocumentException{

           String file_name="C:\\wamp64\\www\\pdfd\\Articles.pdf";  
     Document document=new Document();
      PdfWriter.getInstance(document,new FileOutputStream(file_name));
        
     
    try{
     
       document.open();
         document.add(Image.getInstance("C:\\wamp64\\www\\hunt.JPG"));
  
      
        Connection  cnx = ConnexionBase.getInstance().getCnx();
         String req = "SELECT * FROM articles_especes";
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(req);
      while(rs.next()){
     Paragraph para=new  Paragraph(rs.getString("Titre")+" "+rs.getString("Contenu")+rs.getString("Type"));
   
     document.add(para);
     document.add(new Paragraph(""));
       
       
 
       
       document.close();
      }
   } catch (DocumentException | IOException | SQLException e){
       System.out.println(e);
}
}}  

