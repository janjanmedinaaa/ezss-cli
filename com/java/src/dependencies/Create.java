package com.java.classes.dependencies;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import com.java.classes.dependencies.FileAction;
import com.java.classes.dependencies.Assemble;

public class Create{
    public void create(String[] args){
        FileAction file = new FileAction();
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(args));

        switch(args.length){
            case 1:
                System.out.println(instructions(null));
                break;
            case 2:
                if(args[1].equalsIgnoreCase("all")){
                    collectHTML();
                }
                else if(file.getFileType(args[1]).equalsIgnoreCase("html")){
                    singleCreate(args[1], null);
                }
                else {
                    System.out.println(instructions(args[1]));
                }
                break;
            case 3:
                    if(file.getFileType(args[2]).equalsIgnoreCase("css")){
                        singleCreate(args[1], args[2]);
                    }
                    else if(file.getFileType(args[2]).equalsIgnoreCase("html")){
                        multipleCreate(arrayList);
                    } 
                break;
            default:
                if(args.length > 3){
                    multipleCreate(arrayList);
                }
        }
    }

    private static String instructions(String param){
        String output = (param == null) ? "Incomplete Create Command.\n\n" 
                            : "Invalid HTML File. - " + param + "\n\n";

        output += "Command Usage:\n    create [...files]\n\n";

        output += "Examples:\n";
        output += "    create all                  > Create CSS files for all HTML files in current directory\n";
        output += "    create one.html             > Create one.css\n";
        output += "    create one.html two.html    > Create multiple css files\n";
        output += "    create one.html styles.css  > Create CSS file with custom name\n\n";

        return output;
    }

    private static ArrayList collectHTML(){
        ArrayList files = new ArrayList();
        Create create = new Create();

        try{
            Files.newDirectoryStream(Paths.get(""), "*.html")
                .forEach(files::add);

            create.multipleCreate(files);
        }
        catch(Exception e){
            System.out.println("Error in getting HTML Files.");
        }

        return files;
    }

    private static boolean writeCSS(String htmlfile, String compiled, String filename){
        FileAction file = new FileAction();

        filename = (filename != null) ? filename : file.getFileName(htmlfile) + ".css";
        Boolean success = false;
        
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(new File(filename)));
            success = true;
            pw.println(compiled);
            pw.close();
        }
        catch(Exception e){
            System.out.println("Error in Creating CSS File.");
        }

        return success;
    }

    public void multipleCreate(ArrayList files){
        FileAction file = new FileAction();
        List<String> newfiles = new ArrayList<>(files.size());

        for (Object object : files) {
            newfiles.add(Objects.toString(object, null));
        }

        for (String newfile : newfiles) {
            if(!newfile.equalsIgnoreCase("create") 
                && file.getFileType(newfile).equalsIgnoreCase("html"))
                    singleCreate(newfile, null);
        }
    }
    
    private static void singleCreate(String htmlfile, String filename){
        FileAction file = new FileAction();
        Assemble assemble = new Assemble();

        String filecontent = file.readFile(htmlfile); 

        ArrayList ids = assemble.ids(filecontent);
        ArrayList classes = assemble.classes(filecontent);

        String compiled = assemble.compile(ids, classes);

        if(file.getFileType(htmlfile).equalsIgnoreCase("html")){
            if (filename == null){
                writeCSS(htmlfile, compiled, filename);
            }
            else{
                if(file.getFileType(filename).equalsIgnoreCase("css")){
                    writeCSS(htmlfile, compiled, filename);
                }
                else {
                    System.out.println("Invalid CSS Filename. - " + filename);
                }
            }
        }
        else {
            System.out.println("Invalid HTML File. - " + htmlfile);
        }
        
    }
}