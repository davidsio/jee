/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.j2ee_pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author davidnadal
 */
@Named
@SessionScoped
public class convertImgToPDF implements Serializable {
    UploadedFile pdfFile;

    public UploadedFile getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(UploadedFile pdfFile) {
        this.pdfFile = pdfFile;
    }
    
    public void convert() {

        PdfWriter writer = null;
        try {
            // Creating a PdfWriter
            String dest = "/Users/davidnadal/Documents/epsi/jee/src/main/webapp/ressources/uploads/monpdf.pdf";
            writer = new PdfWriter(dest);
            // Creating a PdfDocument
            PdfDocument pdf = new PdfDocument(writer);
            // Creating a Document
            Document document = new Document(pdf);
            // Creating an ImageData object
            String imFile = "/Users/davidnadal/Downloads/" + this.pdfFile.getFileName();
            ImageData data = null;
            try {
                data = ImageDataFactory.create(imFile);
            } catch (MalformedURLException ex) {
                Logger.getLogger(convertImgToPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Creating an Image object
            Image image = new Image(data);
            // Adding image to the document
            document.add(image);
            // Closing the document
            document.close();
            System.out.println("Image added");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(convertImgToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(convertImgToPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath()+ "/success.html");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

}
