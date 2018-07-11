package TestTess4j01;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;


public class Test01 {
    public static void main(String[] args) throws IOException {
        //图片所在文件夹
        testTess4j("D:\\testImage");
    }
    public static void testTess4j(String filePath) throws IOException{
        File root = new File(filePath);  
        ITesseract instance = new Tesseract();  
        instance.setDatapath("D:\\Tess4J");
        instance.setLanguage("chi_sim");
        try {  
            File[] files = root.listFiles();  
            for (File file : files) {  
                String result = instance.doOCR(file);  
                String fileName = file.toString().substring(file.toString().lastIndexOf("\\")+1);
                BufferedImage grayImage = ImageHelper.convertImageToBinary(ImageIO.read(file));
                ImageIO.write(grayImage, "jpg", new File("D:\\testImage\\", "test.jpg"));
                System.out.println("图片名：" + file.toString() +" 识别结果："+result);  
            }  
        } catch (TesseractException e) {  
            System.err.println(e.getMessage());  
        }  
    }
}