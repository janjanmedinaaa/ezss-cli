package com.java.classes;

import com.java.classes.dependencies.Compress;
import com.java.classes.dependencies.Create;
import com.java.classes.dependencies.FileAction;

public class EzSS{
    public static void main(String args[]){
        Compress compress = new Compress();
        Create create = new Create();
        FileAction file = new FileAction();

        if(args.length > 0){
            switch(args[0]){
                case "create":
                    create.create(args);
                    break;
                case "generate":
                    create.create(args);
                    break;
                case "gen":
                    create.create(args);
                    break;
                case "compress":
                    compress.compress(args);
                    break;
                case "comp":
                    compress.compress(args);
                    break;
                case "read":
                    System.out.println(file.readFile(args[1]));
                default:
                    System.out.println(unknown(args[0]));
            }
        }
        else {
            System.out.println(ezss());
        }
    }

    private static String ezss(){
        String output = "EzSS v1.0\n\n";

        output += "Description:\n    EzSS is the ultimate tool in creating and compressing CSS Files. \n" + 
                    "EzSS allows auto-generation of CSS Files based on the HTML Classes and Ids \nusing CSS Best Practices. " +
                    "EzSS also allows CSS Compression. \n\n";
        output += "Author:\n    Janjan Medina - https://github.com/medinajuanantonio95\n\n";
        output += "Usage:\n    java -jar ezss.jar [command] [...files]\n\n";

        output += "Commands:\n";
        output += "    create       > Create CSS Files\n";
        output += "    compress     > Compress CSS Files\n\n";

        output += "Examples:\n";
        output += "    ezss.jar create one.html             > Create one.css\n";
        output += "    ezss.jar create one.php two.html     > Create multiple CSS files\n";
        output += "    ezss.jar create one.html custom.css  > Create CSS file with custom name\n";
        output += "    ezss.jar generate one.php            > Generate also works like create\n";
        output += "    ezss.jar gen one.html                > Shortcuts also work\n";
        output += "    ezss.jar compress one.css            > Compress one.css\n";
        output += "    ezss.jar create all                  > Create CSS files for all HTML files in current directory\n";
        output += "    ezss.jar comp all                    > Shortcuts also work\n";

        return output;
    }

    private static String unknown(String command){
        String output = "Unknown Command: " + command + "\n\n";

        output += "Try using:\n";
        output += "    create       > Create CSS Files\n";
        output += "    compress     > Compress CSS Files\n\n";

        output += "Examples:\n";
        output += "    ezss.jar create one.html             > Create one.css\n";
        output += "    ezss.jar create one.html two.php     > Create multiple CSS files\n";
        output += "    ezss.jar gen one.php custom.css      > Create CSS file with custom name\n";
        output += "    ezss.jar compress one.css            > Compress one.css\n";
        output += "    ezss.jar create all                  > Create CSS files for all HTML files in current directory\n";

        return output;
    }
}