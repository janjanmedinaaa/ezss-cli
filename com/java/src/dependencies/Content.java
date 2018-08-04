package com.java.classes.dependencies;

import com.java.classes.dependencies.FileAction;
public class Content{
    public String CSSFunction(String name, String type){
        String classifier = (type == "id") ? "#" : ".";
        String defString = " {\n\n}\n\n";

        String func = classifier + name + defString;

        return func;
    }

    public String comments(String type, String name){
        String comment = "";

        switch(type){
            case "info":
                comment += "/*\n";
                comment += "\t!!!DON'T FORGET TO UPDATE THESE INFORMATION!!!\n";
                comment += "\tFilename: index.html\n";
                comment += "\tAuthor: Janjan Medina\n";
                comment += "\tAuthor URI: https://github.com/medinajuanantonio95\n";
                comment += "\tProject: " + currentFolder() + "\n";
                comment += "*/\n\n";
                break;
            case "general":
                comment = "/***** General Styles *****/\n\n";
                break;
            case "header":
                comment = "/***** Header Style *****/\n\n";
                break;
            case "footer":
                comment = "/***** Footer Style *****/\n\n";
                break;
            case "nav":
                comment = "/***** Navigation Style *****/\n\n";
                break;
            case "basic":
                comment = "/***** " + name + " *****/\n\n";
                break;
            default: 
                comment = "/* Comment type unknown */\n\n";
        }

        return comment;
    }

    public String mediaQueries(){
        String mediaqueries = "";

        mediaqueries += "/* Small devices (tablets, 768px and up) */\n\n";
        mediaqueries += "@media (min-width: 768px) {\n\n}\n\n";
        mediaqueries += "/* Small devices (desktops, 992px and up) */\n\n";
        mediaqueries += "@media (min-width: 992px) {\n\n}\n\n";
        mediaqueries += "/* Small devices (large desktops, 1200px and up) */\n\n";
        mediaqueries += "@media (min-width: 1200px) {\n\n}\n\n";

        return mediaqueries;
    }

    public String bodyCSS(){
        return "body {\n\tmargin: 0;\n\tpadding: 0;\n\twidth: 100%;\n}\n\n";
    }

    public String addImport(String filename){
        return "<link rel=\"stylesheet\" href=\"" + filename + "\" />";
    }

    public String writeContent(String filename){
        FileAction file = new FileAction();

        String filetype = file.getFileType(filename);
        String content = "";

        switch(filetype){
            case "html":
                content = "<html>\n\t<head>\n\t\t<title>MyProject</title>" + 
                    "\n\t</head>\n\t<body>\n\n\n\t</body>\n</html>";
                break;
            case "css":
                content = comments("info", null) + comments("general", null) + 
                    bodyCSS() + mediaQueries();
                break;
            case "php":
                content = "<html>\n\t<head>\n\t\t<title>MyProject</title>" + 
                "\n\t</head>\n\t<body>\n\t\t<%php\n\t\t\t\"Hello World\"" + 
                "\n\t\t%>\n\t</body>\n</html>";
                break;
        }

        return content;
    }

    private static String currentFolder(){
        String dir = System.getProperty("user.dir");
        dir = dir.replaceAll("[\\\\s]", "\\\\");

        String[] split = dir.split("\\\\\\\\");

        return split[split.length-1];
    }
    
}