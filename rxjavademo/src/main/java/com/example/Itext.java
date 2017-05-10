package com.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by android on 2017/4/27.
 */

public class Itext {
    public static final String SRC = "./hello.pdf";
    public static final String DEST = "./hello_with_image_id.pdf";
    public static final String IMG = "./bruno.jpg";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
//        new Itext().manipulatePdf(SRC, DEST);
        createPdf();
    }

    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        Image image = Image.getInstance(IMG);
        PdfImage stream = new PdfImage(image, "", null);
        stream.put(new PdfName("ITXT_SpecialId"), new PdfName("123456789"));
        PdfIndirectObject ref = stamper.getWriter().addToBody(stream);
        image.setDirectReference(ref.getIndirectReference());
        image.setAbsolutePosition(36, 400);
        PdfContentByte over = stamper.getOverContent(1);
        over.addImage(image);
        stamper.close();
        reader.close();
    }
    public static void createPdf(){
        final String pdf_address = DEST;
        Rectangle a5 = PageSize.A5;
        float x = (PageSize.A4.getWidth()-PageSize.A5.getWidth()) / 2 + 15;
        Document doc = new Document(PageSize.A4, x, x, 8, (PageSize.A4.getHeight()-PageSize.A5.getHeight()) + 30);// 创建一个document对象
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(pdf_address); // pdf_address为Pdf文件保存到sd卡的路径
            PdfWriter instance = PdfWriter.getInstance(doc, fos);
            doc.open();

            Image image = Image.getInstance(IMG);

            doc.add(image);


            // ,setChineseFont()为pdf字体
            // 一定要记得关闭document对象
            doc.close();
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
