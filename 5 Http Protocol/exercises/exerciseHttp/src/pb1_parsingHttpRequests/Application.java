package pb1_parsingHttpRequests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {
    private static BufferedReader reader=new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> validUrls = getValidUrls();
        List<String> request = getRequest();
        
        String method=getMethod(request.get(0));
        String url = getUrl(request.get(0));


        Map<String,String> headers=getHeaders(request);
        Map<String,String> bodyParams=getBodyParams(request);

        StringBuilder response=new StringBuilder();
        if(!validUrls.contains(url)){
            response.append("HTTP/1.1 404 Not Found")
                    .append(System.lineSeparator());

            addResponseHeaders(headers,response);

            response.append(System.lineSeparator());
            response.append("The requested functionality was not found.");

            System.out.println(response.toString());
            return;
        }
        if(!headers.keySet().contains("Authorization")){
            response.append("HTTP/1.1 401 Unauthorized")
                    .append(System.lineSeparator());

            addResponseHeaders(headers,response);

            response.append(System.lineSeparator());
            response.append("You are not authorized to access the requested functionality.");

            System.out.println(response.toString());
            return;
        }
        if(method.toUpperCase().equals("POST")&&bodyParams.isEmpty()||url.contains("?")){
            response.append("HTTP/1.1 400 Bad Request")
                    .append(System.lineSeparator());

            addResponseHeaders(headers,response);

            response.append(System.lineSeparator());
            response.append("There was an error with the requested functionality due to malformed request.");

            System.out.println(response.toString());
            return;
        }
        if(method.toUpperCase().equals("GET")){
            response.append("HTTP/1.1 200 OK")
                    .append(System.lineSeparator());
            addResponseHeaders(headers,response);
            response.append(System.lineSeparator());
            byte[] authorizations = Base64.getDecoder().decode(headers.get("Authorization").split("\\s+")[1]);
            String username = new String(authorizations);

            response.append(String.format("Greetings %s!",username));

            System.out.println(response.toString());
            return;

        }
        if(method.toUpperCase().equals("GET")==false&&method.toUpperCase().equals("POST")==false){
            return;
        }
        response.append("HTTP/1.1 200 OK")
                .append(System.lineSeparator());

        addResponseHeaders(headers,response);
        response.append(System.lineSeparator());

        byte[] authorizations = Base64.getDecoder().decode(headers.get("Authorization").split("\\s+")[1]);
        String username = new String(authorizations);

        String createdObjectName = bodyParams.entrySet().stream()
                .findFirst()
                .get().getValue();
        StringBuilder placeholderParams=new StringBuilder();
        bodyParams.entrySet().stream()
                .skip(1)
                .limit(3)
                .forEach(kv->{
                    placeholderParams.append(String.format("%s - %s, ",
                            kv.getKey(),kv.getValue()));
                });


        String responseBody=String.
                format("Greetings %s! You have successfully created %s with %s.",
                        username,createdObjectName,
                        placeholderParams.substring(0,placeholderParams.lastIndexOf(", "))
                );
        response.append(responseBody);

        System.out.println(response.toString());

    }

    private static void addResponseHeaders(Map<String,String> headers, StringBuilder response) {
        for (Map.Entry<String, String> headerKv : headers.entrySet()) {
            if(headerKv.getKey().equals("Date")||
                    headerKv.getKey().equals("Host")||
                    headerKv.getKey().equals("Content-Type")){
                response.append(headerKv.getKey())
                        .append(": ")
                        .append(headerKv.getValue())
                        .append(System.lineSeparator());
            }
        }
    }

    private static Map<String,String> getBodyParams(List<String> request) {
        Map<String,String> bodyParams=new LinkedHashMap<>();
        if(request.get(request.size()-1).equals(System.lineSeparator())){
            if(request.get(0).contains("?")){
                String queryString = request.get(0).split("\\?")[1];
                Arrays.stream(queryString.split("&"))
                        .map(token->token.split("="))
                        .forEach(bpKv->{
                            bodyParams.put(bpKv[0],bpKv[1]);
                        });
            }
            return bodyParams;
        }
        Arrays.stream(request.get(request.size() - 1).split("&"))
                .map(token->token.split("="))
                .forEach(bpKv->{
                    bodyParams.put(bpKv[0],bpKv[1]);
                });
        return bodyParams;
    }

    private static Map<String,String> getHeaders(List<String> request) {
        Map<String,String> headers=new LinkedHashMap<>();

        request.stream()
                .skip(1)
                .filter(h->h.contains(": "))
                .map(h->h.split(": "))
                .forEach(kv->{
                    headers.put(kv[0],kv[1]);
                });
        return headers;
    }

    private static String getMethod(String line) {
        return line.split("\\s+")[0];
    }

    private static String getUrl(String line) {
        return line.split("\\s+")[1];
    }

    private static List<String>getValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }
    private static List<String> getRequest() throws IOException {
        List<String> request=new ArrayList<>();

        String line;
        while (reader.ready()&&(line=reader.readLine())!=null&&line.length()>0){
            request.add(line);
        }
        request.add(System.lineSeparator());
        if(reader.ready()&&(line=reader.readLine())!=null&&line.length()>0){
            request.add(line);
        }
        return request;
    }
}
