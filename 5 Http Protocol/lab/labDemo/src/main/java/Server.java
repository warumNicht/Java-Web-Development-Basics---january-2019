import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private int port;
    private ServerSocket tcpListener;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        this.tcpListener=new ServerSocket(this.port);
        System.out.println("Listening on http://localhost:"+this.port);

        while (true){
            Socket clientConnection=this.tcpListener.accept();
            System.out.println("Client connected!");

            BufferedReader reader=new BufferedReader(
                    new InputStreamReader(clientConnection.getInputStream()
            ));

            List<String> requestContent=new ArrayList<>();
            String line="";
            while ((line=reader.readLine())!=null&&line.length()>0){
                requestContent.add(line);
            }
            if(requestContent.size()==0){
                continue;
            }
            String requestLine = requestContent.get(0);
            String url = requestLine.split("\\s")[1];
            if(url.contains(".")){
                this.readFile(url,clientConnection);
                continue;
            }

            System.out.println(String.join(System.lineSeparator(),requestContent));
            clientConnection.getOutputStream()
                    .write(this.getResponse().getBytes());

            clientConnection.getOutputStream().close();
        }

    }

    private void readFile(String url, Socket clientConnection) throws IOException {
        String formattedUrl = url.replaceFirst("/", "");
        File file=new File(formattedUrl);
        List<String> contentFile = Files.readAllLines(Paths.get(formattedUrl));
        String fileContent = String.join(System.lineSeparator(), contentFile);
        String response=this.getResponse()+
                String.format("<h1>%s</h1>",fileContent);
        OutputStream outputStream = clientConnection.getOutputStream();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private String getResponse(){
        return "HTTP/1.1 200 OK"+System.lineSeparator()
                +"Host: softuni"+System.lineSeparator()
                +"Content-Type: text/html"+System.lineSeparator()
                +System.lineSeparator();
    }
}
