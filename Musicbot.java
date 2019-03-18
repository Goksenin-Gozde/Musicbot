/*
 * Copyright 2016 Göksenin GÖZDE
 */
package musicbot;


import java.util.Scanner;
import java.util.ArrayList;
import java.io.*; 
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author Göksenin
 */
public class MusicBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
       //Regex'imizi oluşturmak için içinde grupların yer aldığı dosyamızı okuyoruz.
       String theGroupRegex = "";
       File groupFile = new File("gruplar.txt");
       Path groupFilePath = Paths.get("gruplar.txt");
       Scanner groupScanner = null;
       try{
       groupScanner = new Scanner(groupFilePath,"ISO-8859-9");
       }
       catch (IOException e) 
       {
           System.out.println("Bilinmeyen bir hata oluştur");
           System.exit(0);
       }
       
       while(groupScanner.hasNext()){ 
            theGroupRegex = theGroupRegex+groupScanner.next()+"|"; //Regeximiz burada oluşuyor
        }
        groupScanner.close();
        String theQuestionRegex = "solist|tur|millet|best|kurulus|gruplar"; //Sorularımız için anahtar kelimeler
        String soru; 
        ArrayList<String> keywords = new ArrayList<>();
        Scanner scan = new Scanner(System.in,"ISO-8859-9");
        System.out.println("Merhabalar ben MusicBot. Aylardır senin için hazırlanıyorum\n"
                + "Bana müzik gruplarıyla ilgili birçok soruyu sorabilirsin!!\n"
                + "Mesela müzik grubu nerede kuruldu, solisti kim, ne tarz müzik yapıyorlar,"
                + " en iyi şarkıları ne veya kaç yılında kuruldular?\n"
                + "Bunlar sadece örnekler cevap verebildiğim her sorunun cevabını vermek için sabırsızlanıyorum\n"
                + "Hakkında bilgi sahibi olduğum grupları öğrenmek için gruplar yazman yeterli!\n"
                + "Eğitimimi tamamladıkça daha fazla soruna cevap verebilirim");
        soru = scan.nextLine();
        soru = prepareQuestion(soru);// Sorumuzu regeximize uygun hale getirip türkçe karakterlerden arındırıyoruz
       RegexChecker bandNameChecker = new RegexChecker(theGroupRegex,soru);
       RegexChecker questionChecker = new RegexChecker (theQuestionRegex,soru);
       keywords.add(bandNameChecker.check());
       keywords.add(questionChecker.check()); 
       String groupName = keywords.get(0);
       String title = keywords.get(1);
       String fileName = title+".txt";
       File file = new File(fileName);
       Path path = Paths.get(fileName);
       Scanner scanner = null;
       try 
       {
        scanner = new Scanner(path,"ISO-8859-9");
       }
       catch (IOException e) 
       {
           System.out.println("Özür deilerim bu soruyu cevaplamak için eğitilmedim");
           System.exit(0);
       }
       if (title.equalsIgnoreCase("gruplar")){
           
           while(scanner.hasNext())
               System.out.println(scanner.nextLine());    
       }
       else{
            while(scanner.hasNext()){ 
                String line = scanner.next();
                if (line.equalsIgnoreCase(groupName))
                System.out.println(scanner.nextLine());
            }
       }
scanner.close();
    }
    
    
    
    
    
    
    public static String prepareQuestion(String str){
    
        str = str.replace("ü","u");
        str = str.replace("ö","o");
        str = str.replace("ı","i");
        str = str.replace("ş","s");
        str = str.replace("ç","c");
        str = str.replace("ğ","g");
        str = str.replace("Vokalist", "solist");
        str = str.replace("Vokal", "solist");
        str = str.replace("şarkıcı", "solist");
        str = str.replace("nereli", "millet");
        str = str.replace("nerede", "millet");
        str = str.replace("kuruluş","kurulus");
        str = str.replace("ne zaman","kurulus");
        str = str.replace("tarz","tur");
        str = str.replace("nasil","tur");
        str = str.replace("en iyi","best");
        str = str.replace("oner", "best");
        str = str.toLowerCase();
        
        return str; 
    }
    }
    

