package com.example;


import org.jpedal.PdfDecoder;
import org.jpedal.exception.PdfException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgPdfUtils {
    public static void main(String[] args) {
        PdfDecoder decode_pdf = new PdfDecoder(true);
        try {
            decode_pdf.openPdfFile("J:\\printer.pdf"); //file
//　　　decode_pdf.openPdfFile("C:/jpedalPDF.pdf", "password"); //encrypted file
//      decode_pdf.openPdfArray(bytes); //bytes is byte[] array with PDF
//      decode_pdf.openPdfFileFromURL("http://www.mysite.com/jpedalPDF.pdf",false);
//      decode_pdf.openPdfFileFromInputStream(in, false);

            int start = 1, end = decode_pdf.getPageCount();
            for (int i = start; i < end + 1; i++) {
                BufferedImage img = decode_pdf.getPageAsImage(i);
                try {
                    ImageIO.write(img, "png", new File("J:\\jpedal_image.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            decode_pdf.closePdfFile();
        } catch (PdfException e) {
            e.printStackTrace();
        }
    }

}
