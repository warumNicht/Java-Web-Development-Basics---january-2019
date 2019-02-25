package fdmc.util;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.Charset;

public class HtmlReaderImpl implements HtmlReader {
    @Override
    public String readHtmlFile(String filePath) throws IOException {

        Charset inputCharset = Charset.forName("UTF-8");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                new FileInputStream(new ClassPathResource(filePath).getFile()),
                        inputCharset));

        StringBuilder htmlFileContent = new StringBuilder();
        String htmlLine;
        while ((htmlLine = reader.readLine()) != null) {
                htmlFileContent.append(htmlLine).append(System.lineSeparator());

        }
        return htmlFileContent.toString().trim();
    }
}
