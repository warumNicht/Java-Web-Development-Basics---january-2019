package chushkaApp.util;

import java.io.*;

public class HtmlReader {
    public String readHtmlFile(String htmlFilePath) throws IOException {
        BufferedReader reader=new BufferedReader(
                new InputStreamReader(new FileInputStream(new File(htmlFilePath)))
        );
        StringBuilder htmlFileContent=new StringBuilder();
        String line;
        while ((line=reader.readLine())!=null){
            htmlFileContent.append(line);
        }
        return htmlFileContent.toString();
    }
}
