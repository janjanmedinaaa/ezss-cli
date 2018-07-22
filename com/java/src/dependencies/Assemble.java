package com.java.classes.dependencies;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.regex.Matcher;
import com.java.classes.dependencies.Content;

public class Assemble{
    public String compile(ArrayList ids, ArrayList classes){
        String compiled = "";
        Content content = new Content();

        compiled += content.comments("info", null);
        compiled += content.comments("general", null) + content.bodyCSS();

        for(int a = 0; a < ids.size(); a++){
            switch(ids.get(a).toString()){
                case "header":
                    compiled += content.comments("header", ids.get(a).toString());
                    break;
                case "footer":
                    compiled += content.comments("footer", ids.get(a).toString());
                    break;
                case "nav":
                    compiled += content.comments("nav", ids.get(a).toString());
                    break;
                default:
                    compiled += content.comments("basic", ids.get(a).toString());
            }

            compiled += content.CSSFunction(ids.get(a).toString(), "id");
        }

        for(int b = 0; b < classes.size(); b++){
            compiled += content.CSSFunction(classes.get(b).toString(), "classes");
        }

        compiled += content.mediaQueries();

        return compiled;
    }

    public ArrayList classes(String filecontent){
        ArrayList classes = new ArrayList();
        String regex = "(class)=\"([a-zA-Z0-9\\s-]*)\""; 
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(filecontent);

        while (matcher.find()) {
            String found = matcher.group(2);
            String[] seperateFound = found.split(" ");

            for(int a = 0; a < seperateFound.length; a++){
                if(!classes.contains(seperateFound[a])){
                    classes.add(seperateFound[a]);
                }
            }
        }

        return classes;
    }

    public ArrayList ids(String filecontent){
        ArrayList ids = new ArrayList();
        String regex = "(id)=\"([a-zA-Z0-9\\s-]*)\""; 
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(filecontent);

        while (matcher.find()) {
            String found = matcher.group(2);
            String[] seperateFound = found.split(" ");

            for(int a = 0; a < seperateFound.length; a++){
                if(!ids.contains(seperateFound[a])){
                    ids.add(seperateFound[a]);
                }
            }
        }

        return ids;
    }
}