import java.io.*;
import java.util.*;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class q1 {
    public static String filename_to_string(String filename){
        String filetypee = null;
        String res = null;
        if(filename.substring(filename.length()-4).equalsIgnoreCase(".pdf")){
            filetypee = "PDF";
        }
        else if(filename.substring(filename.length()-4).equalsIgnoreCase(".doc")){
            filetypee = "DOC";
        }
        else if(filename.substring(filename.length()-5).equalsIgnoreCase(".docx")){
            filetypee = "DOCX";
        }
        else {
            System.out.println("Unsupported filetype!");
            System.exit(-1);
        }
        //filetype decided;
        /////////////////////////////////////////////////////////////
        if (filetypee.equals("PDF")){
            try{
                //System.out.println("Entered PDF part");
                File srcfile = new File(filename);
                PDDocument pdoc = PDDocument.load(srcfile);
                PDFTextStripper pdfStripper = new PDFTextStripper();
                res = pdfStripper.getText(pdoc);
                pdoc.close();
            }
            catch (IOException e){
                System.out.println("File could not be opened.");
                System.exit(1);
            }
           /* catch (FileNotFoundException e){
                System.out.println("File Not Found!");
                System.exit(1);
            }*/
        }
        else if(filetypee.equalsIgnoreCase("DOC")){
            try {
                //System.out.println("Entered DOC part");
                FileInputStream fis = new FileInputStream(new File(filename));
                HWPFDocument ddoc = new HWPFDocument(fis);
                WordExtractor textLatch = new WordExtractor(ddoc);
                res = textLatch.getText();
            }
            catch (IOException e) {
                System.out.println("File could not be opened.");
                System.exit(1);
            }
/*            catch (FileNotFoundException e){
                System.out.println("File Not Found!");
                System.exit(1);
            }*/
        }
        else if(filetypee.equalsIgnoreCase("DOCX")){
            try {
                //System.out.println("Entered DOCX part");
                FileInputStream fis = new FileInputStream(new File(filename));
                XWPFDocument ddocx = new XWPFDocument(fis);
                XWPFWordExtractor textLatch = new XWPFWordExtractor(ddocx);
                res = textLatch.getText();
            }
            catch (IOException e) {
                System.out.println("File could not be opened.");
                System.exit(1);
            }
            /*catch (FileNotFoundException e){
                System.out.println("File Not Found!");
                System.exit(1);
            }*/
        }
        else{
            System.out.println("Unsupported filetype!");
            System.exit(1);
        }
        return res;
    }
    public static List<String> string_to_array_list(String content){
        List<String> res = new ArrayList<String>(Arrays.asList(content.split("\\s+")));
        return res;
    }
    public static List<String> csal(String csal_content){
        //returns arraylist after parsing comma separated string as input
        List<String> res = new ArrayList<String>(Arrays.asList(csal_content.split("\\s*,\\s*")));
        return res;
    }
    public static void filtr_list1(List<String> myList, List<String> filtered_list, List<String> banned_words, Set<String> singleton_filtered_set){
        //List<String> filtered_list = new ArrayList<String>();
        String ttt;
        for (int i = 0; i < myList.size() ; i++) {
            ttt = myList.get(i).toLowerCase().replaceAll("[^A-Za-z0-9 ]", "").trim();
            if (ttt.trim().equals("") || banned_words.contains(ttt)){
                continue;
            }
            filtered_list.add(ttt);
            singleton_filtered_set.add(ttt);
        }
        return;
    }
}
