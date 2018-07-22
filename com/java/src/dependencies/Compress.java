package com.java.classes.dependencies;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import com.java.classes.dependencies.FileAction;

public class Compress{
    FileAction file = new FileAction();

    public void compress(String[] args){
        switch(args.length){
            case 1:
                System.out.println(instructions(null));
                break;
            case 2:
                if(args[1].equalsIgnoreCase("all")){
                    collectCSS();
                }
                else if(file.getFileType(args[1]).equalsIgnoreCase("css")){
                    singleCompress(args[1]);
                }
                else {
                    System.out.println(instructions(args[1]));
                }
                break;
            default:
                if(args.length > 2){
                    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(args));
                    multipleCompress(arrayList);
                }
        }
    }

    private static String instructions(String param){
        String output = (param == null) ? "Incomplete Compress Command.\n\n" 
                            : "Invalid CSS File. - " + param + "\n\n";

        output += "Command Usage:\n    compress [...files]\n\n";

        output += "Examples:\n";
        output += "    compress all                  > Compress CSS files in current directory\n";
        output += "    compress one.css              > Compress one.css\n";
        output += "    compress one.css two.css      > Compress multiple css files\n\n";

        return output;
    }

    private static ArrayList collectCSS(){
        ArrayList files = new ArrayList();
        Compress compress = new Compress();

        try{
            Files.newDirectoryStream(Paths.get(""), "*.css")
                .forEach(files::add);

            compress.multipleCompress(files);
        }
        catch(Exception e){
            System.out.println("Error in Getting CSS Files.");
        }

        return files;
    }
    
    private static boolean compressCSS(String filename, String filecontent){
        FileAction file = new FileAction();
    
        Boolean success = false;
        String spaceRegex = "[\n\t\\s]";
        String commentRegex = "/(\\*)*(\\s)*[\\w\\d\\s(:|.|,|/|\\(\\))]*(\\s)*(\\*)*/";
        String emptyRegex = "(#|\\.|@)+([\\w\\d\\s\\(\\)\\-\\:])+\\{\\}";

        filecontent = filecontent.replaceAll(spaceRegex, "");
        filecontent = filecontent.replaceAll(commentRegex, "");
        filecontent = filecontent.replaceAll(emptyRegex, "");

        if(file.getFileType(filename).equals("css")){
            try{
                PrintWriter pw = new PrintWriter(new FileWriter(filename));
                success = true;
                pw.println(filecontent);
                pw.close();
            }
            catch(Exception e){
                System.out.println("Error in Compressing CSS.");
            }
        }

        return success;
    }
    
    public void multipleCompress(ArrayList files){
        FileAction file = new FileAction();
        List<String> newfiles = new ArrayList<>(files.size());

        for (Object object : files) {
            newfiles.add(Objects.toString(object, null));
        }

        for (String newfile : newfiles) {
            if(!newfile.equalsIgnoreCase("compress") 
                && file.getFileType(newfile).equalsIgnoreCase("css"))
                    singleCompress(newfile);
        }
    }

    private static void singleCompress(String cssfile){
        FileAction file = new FileAction();
        String filecontent = file.readFile(cssfile); 

        if(file.getFileType(cssfile).equalsIgnoreCase("css")){
            compressCSS(cssfile, filecontent);
        }
        else {
            System.out.println("Invalid CSS File. - " + cssfile);
        }
    }
}