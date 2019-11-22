package edu.poly.helper;


import edu.poly.model.NhanVien;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;


public class ShareHelper {
    public static final Image APP_ICON;
    static{
        String file= "";
        APP_ICON  = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
           
        }
     public static boolean saveLogo(File file){ 
          File dir = new File("logos"); 
          if(!dir.exists()){   
              dir.mkdirs(); 
          }  
           File newFile = new File(dir, file.getName());    
           try {   
               Path source = Paths.get(file.getAbsolutePath());  
               Path destination = Paths.get(newFile.getAbsolutePath()); 
                  Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING); 
                  return true;  
           }   catch (Exception ex) { 
               return false;  
           }
     }
     public static ImageIcon readLogo(String fileName){
         File path = new File("logos", fileName);
         return new ImageIcon(path.getAbsolutePath()); 
         
     }  
      public static NhanVien USER = null;
       public static void logoff() { 
           ShareHelper.USER = null;
       }
         public static boolean authenticated() { 
             return ShareHelper.USER != null; 
         } 
}