package pb1_parsingHttpRequests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {
    private static BufferedReader reader=new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> request = getRequest();

        Map<String,String> headers=getHeaders(request);
        StringBuilder result=new StringBuilder();

        if(headers.containsKey("Cookie")){
            String cookieHeaderValue = headers.get("Cookie");
            Map<String,String> cookies=getCookies(cookieHeaderValue);
            for (Map.Entry<String, String> cookieKv : cookies.entrySet()) {
                result.append(String.format("%s <-> %s",cookieKv.getKey(),cookieKv.getValue()))
                        .append(System.lineSeparator());
            }
        }
        System.out.print(result.toString());
    }

    private static Map<String,String> getCookies(String cookieHeaderValue) {
        Map<String,String> cookies=new LinkedHashMap<>();
        Arrays.stream(cookieHeaderValue.split(";\\s+"))
        .map(token->token.split("="))
        .forEach(kv->{
            cookies.put(kv[0],kv[1]);
        });
        return cookies;
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

    private static List<String> getRequest() throws IOException {
        List<String> request=new ArrayList<>();

        String line=reader.readLine();
        request.add(line);
        while (reader.ready()){
            line=reader.readLine();
            request.add(line);
        }
        return request;
    }
}
