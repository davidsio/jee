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
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author davidnadal
 */
@Named
@SessionScoped
public class convertImgToPDF implements Serializable {
    UploadedFile j2eedesesmorts;

    public UploadedFile getJ2eedesesmorts() {
        return j2eedesesmorts;
    }

    public void setJ2eedesesmorts(UploadedFile j2eedesesmorts) {
        this.j2eedesesmorts = j2eedesesmorts;
    }
    public void convert() throws Exception {

        // Creating a PdfWriter    
        String dest = "/Users/davidnadal/Documents/epsi/jee/src/main/webapp/ressources/uploads/monpdf.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument       
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document        
        Document document = new Document(pdf);

        // Creating an ImageData object       
        String imFile = "/Users/davidnadal/Downloads/" + this.j2eedesesmorts.getFileName();
        ImageData data = ImageDataFactory.create(imFile);

        // Creating an Image object        
        Image image = new Image(data);

        // Adding image to the document       
        document.add(image);

        // Closing the document       
        document.close();

        System.out.println("Image added");
        
    }

}
