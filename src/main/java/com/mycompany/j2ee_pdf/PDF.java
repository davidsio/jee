/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.j2ee_pdf;

import com.itextpdf.kernel.pdf.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.*;
/**
 *
 * @author Hp
 */

@Named
@SessionScoped
public class PDF implements Serializable{

    String dest = "C:/Users/Hp/Documents/TP_PDF/test.pdf";
//    PdfWriter writer;
    private PdfReader reader;
    private PdfReader reader2;
//    PdfDocument pdfDoc = new PdfDocument(writer);

//    public PDF() throws FileNotFoundException {
//        this.writer = new PdfWriter(dest);
//    }

    public void merge() throws IOException {
        //PdfReader reader = new PdfReader(pdf1);
        //PdfReader reader2 = new PdfReader(pdf2);

        PdfDocument doc = new PdfDocument(this.reader);
        PdfDocument pdfDocResult = new PdfDocument(this.reader2);
        doc.copyPagesTo(1, doc.getNumberOfPages(), pdfDocResult);

        //return  pdfDocResult
    }

    public void deletePage(File pdf1, int page) {
        PdfDocument doc = new PdfDocument(this.reader);
        doc.removePage(page);

        //return doc
    }

    public void extractPage(File pdf1, int page) {
        PdfDocument doc = new PdfDocument(this.reader);
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
}
