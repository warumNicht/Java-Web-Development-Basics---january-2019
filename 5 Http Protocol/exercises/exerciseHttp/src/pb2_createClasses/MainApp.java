package pb2_createClasses;


import pb2_createClasses.http.HttpRequest;
import pb2_createClasses.http.HttpRequestImpl;
import pb2_createClasses.http.HttpResponse;
import pb2_createClasses.http.HttpResponseImpl;
import util.TextConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {

    private static BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> validUrls = getValidUrls();
        String requestString= getRequestString();

        HttpRequest request=new HttpRequestImpl(requestString);

        HttpResponse response=new HttpResponseImpl();

        if(!validUrls.contains(request.getRequestUrl())){
            response.setStatusCode(404);
            response.setContent(TextConstants.NOT_FOUND_MESSAGE.getBytes());
        }else if(!request.getHeaders().keySet().contains("Authorization")){
            response.setStatusCode(401);
            response.setContent(TextConstants.UNAUTHORIZED_MESSAGE.getBytes());
        }else if(request.getMethod().equals("POST")&&request.getBodyParameters().isEmpty()){
            response.setStatusCode(400);
            response.setContent(TextConstants.BAD_REQUEST_MESSAGE.getBytes());
        }else{
            response.setStatusCode(200);
            byte[] content=getOkContent(request);
            response.setContent(content);
        }
        addResponseHeaders(request,response);

        byte[] responseBytes = response.getBytes();

    }

    private static byte[] getOkContent(HttpRequest request) {
        byte[] authorizations = Base64.getDecoder().decode(request.getHeaders().get("Authorization").split("\\s+")[1]);
        String username = new String(authorizations);

        String createdObjectName = request.getBodyParameters().entrySet().stream()
                .findFirst()
                .get().getValue();

        if(request.getMethod().equals("GET")){
            return String.format("Greetings %s!",username).getBytes();
        }
        StringBuilder placeholderParams=new StringBuilder();
        request.getBodyParameters().entrySet().stream()
                .skip(1)
                .forEach(kv->{
                    placeholderParams.append(String.format("%s - %s, ",
                            kv.getKey(),kv.getValue()));
                });


        String responseBody=String.
                format("Greetings %s! You have successfully created %s with %s.",
                        username,createdObjectName,
                        placeholderParams.substring(0,placeholderParams.lastIndexOf(", "))
                );
        return responseBody.getBytes();
    }

    private static List<String> getValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }

    private static String getRequestString() throws IOException {
        StringBuilder request=new StringBuilder();
        String line;
        while (reader.ready()&&(line=reader.readLine())!=null&&line.length()>0){
            request.append(line).append(System.lineSeparator());
        }
        request.append(System.lineSeparator());
        if(reader.ready()&&(line=reader.readLine())!=null&&line.length()>0){
            request.append(line);
        }
        return request.toString();
    }


    private static void addResponseHeaders(HttpRequest request, HttpResponse response) {
        for (Map.Entry<String, String> headerKv : request.getHeaders().entrySet()) {
            if(headerKv.getKey().equals("Date")||
                    headerKv.getKey().equals("Host")||
                    headerKv.getKey().equals("Content-Type")){

                response.addHeader(headerKv.getKey(),headerKv.getValue());
            }
        }
    }
}
