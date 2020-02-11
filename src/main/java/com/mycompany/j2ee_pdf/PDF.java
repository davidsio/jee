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
import javax.faces.context.ExternalContext;
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

    // mac : /Users/davidnadal/Documents/epsi/jee/src/main/webapp/ressources/uploads/monpdf.pdf
    // windows : C:\\Users\\Hp\\Documents\\NetBeansProjects\\J2EE_PDF\\src\\main\\webapp\\ressources\\uploads\\monpdf.pdf
    String DEST = "/Users/davidnadal/Documents/epsi/jee/src/main/webapp/ressources/uploads/monpdf.pdf";

    private PdfReader reader;
    private PdfReader reader2;
    private PdfWriter writer;

    public PdfWriter getWriter() {
        return writer;
    }

    public void setWriter(PdfWriter writer) {
        this.writer = writer;
    }
    private UploadedFile file;
    private UploadedFile file2;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

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

    public void merge() throws IOException, Exception {

        this.toInputStream();
        PdfDocument doc = new PdfDocument(this.getReader());
        PdfDocument doc2 = new PdfDocument(this.getReader2());

        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdfFinal = new PdfDocument(writer);

        doc.copyPagesTo(1, doc.getNumberOfPages(), pdfFinal);
        doc2.copyPagesTo(1, doc2.getNumberOfPages(), pdfFinal);
        pdfFinal.close();

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/success.html");

    }

    public void deletePage() throws FileNotFoundException, IOException {

        this.toInputStreamSolo();
        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdfFinal = new PdfDocument(writer);

        PdfDocument doc = new PdfDocument(this.getReader());
        //doc.removePage(this.getNumber());       
        doc.copyPagesTo(1, doc.getNumberOfPages(), pdfFinal);
        pdfFinal.removePage(this.getNumber());

        pdfFinal.close();

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/success.html");
    }

     public void extractPage() throws IOException {
        this.toInputStreamSolo();
        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdfFinal = new PdfDocument(writer);
   
        PdfDocument doc = new PdfDocument(this.getReader());
        doc.copyPagesTo(this.getNumber(), this.getNumber(), pdfFinal);
       
     
        pdfFinal.close();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/success.html");
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

    public void toInputStreamSolo() throws IOException {
        InputStream input = this.file.getInputstream();
        PdfReader a = new PdfReader(input);
        this.setReader(a);
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
