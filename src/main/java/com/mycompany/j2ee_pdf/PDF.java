/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.j2ee_pdf;

import com.itextpdf.kernel.pdf.*;
import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Hp
 */

@Named
@SessionScoped
public class PDF implements Serializable {

    String dest = "C:/Users/Hp/Documents/TP_PDF/test.pdf";
//    PdfWriter writer;
    private PdfReader reader;
    private PdfReader reader2;
    private UploadedFile file;
    private UploadedFile file2;

    public UploadedFile getFile2() {
        return file2;
    }

    public void setFile2(UploadedFile file2) {
        this.file2 = file2;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public PdfReader getReader() {
        return reader;
    }

    public void setReader(PdfReader reader) {
        this.reader = reader;
    }

    public PdfReader getReader2() {
        return reader2;
    }

    public void setReader2(PdfReader reader2) {
        this.reader2 = reader2;
    }
    
    
//    PdfDocument pdfDoc = new PdfDocument(writer);

//    public PDF() throws FileNotFoundException {
//        this.writer = new PdfWriter(dest);
//    }
    
    
    public void merge() throws IOException, Exception {

        this.toInputStream();
        PdfDocument doc = new PdfDocument(this.getReader());
        PdfDocument doc2 = new PdfDocument(this.getReader2());
        PdfDocument docFinal = null;
        List<PdfPage> listPage;
        listPage = doc.copyPagesTo(1, doc.getNumberOfPages(), doc2);
        for(PdfPage page : listPage){
		docFinal.addPage(page);
	}
        //return  docFinal;
    }

    public void deletePage(File pdf1, int page) {
        PdfDocument doc = new PdfDocument(this.getReader());
        doc.removePage(page);

        //return doc
    }

    public void extractPage(File pdf1, int page) {
        PdfDocument doc = new PdfDocument(this.getReader());
        //return doc.getPage(page);
    }

    public void convertToPNG(File pdf) {
//        PDFDocument document = PDDocument.load(new File(filename));
//        PDFRenderer pdfRenderer = new PDFRenderer(document);
//        for (int page = 0; page < document.getNumberOfPages(); ++page) {
//            BufferedImage bim = pdfRenderer.renderImageWithDPI(
//                    page, 300, ImageType.RGB);
//            ImageIOUtil.writeImage(
//                    bim, String.format("src/output/pdf-%d.%s", page + 1, extension), 300);
//        }
//        document.close();
    }

    public void convertToPDF(File png) {
//        Document document = new Document(A4, 20, 20, 20, 20);
//        PdfWriter.getInstance(document, new FileOutputStream("C:/test.pdf"));
//        document.open();
//        Image image = Image.getInstance(getClass().getResource("/logo.png"));
//        document.add(image);
//        document.close();
    }

    //Trier oui mais comment ?
    public void triHaie(File php) {

    }

    //Ajouter signature
    public void cigneAture(File pdf) {

    }

    public void toInputStream() throws IOException {
        InputStream input = this.file.getInputstream();
        InputStream input2 = this.file2.getInputstream();  
        PdfReader a = new PdfReader(input);
        PdfReader b = new PdfReader(input2);
        this.setReader(a);
        this.setReader2(b);
    }
    public void upload() throws IOException, Exception {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);

            File nfile = new File("C:\\Users\\Hp\\J2EEPDF\\" + file.getFileName());
            try {
                Files.copy(file.getInputstream(), nfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
            }

        } else {
            throw new Exception("file == " + file);
        }
    }
}
