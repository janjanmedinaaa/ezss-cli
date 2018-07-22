package com.java.classes.dependencies;

import java.io.*;
import java.util.ArrayList;

public class FileAction{
    public String readFile(String filename){
        String readfile;
        String output = "";

        try{
            if((new File(filename)).exists()){
                BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
                while((readfile = br.readLine()) != null){
                    output += readfile + "\n";
                }
                br.close();
            }
            else{
                System.out.println(filename + " not found.");
            }
        }
        catch(Exception e){
            System.out.println("Error in Reading File. - " + filename);
        }

        return output;
    }

    public String getFileName(String file){
        String[] strip = file.split("[\\.]");
        String filename = "";

        for(int a = 0; a < strip.length-1; a++){
            filename += filename.equals("") ? strip[a] : "." + strip[a];
        }

        return filename;
    }

    public String getFileType(String file){
        String[] strip = file.split("[\\.]");

        return strip[strip.length-1];
    }
}