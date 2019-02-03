package pb2_createClasses;


import pb2_createClasses.http.contracts.HttpRequest;
import pb2_createClasses.http.HttpRequestImpl;
import pb2_createClasses.http.contracts.HttpResponse;
import pb2_createClasses.http.HttpResponseImpl;
import util.TextConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class MainApp {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String requestString = getRequestString();

        HttpRequest request = new HttpRequestImpl(requestString);

        request.getCookies().values().stream()
                .forEach(cookie -> {
                    System.out.println(cookie.getKey() + " <-> " + cookie.getValue());
                });

    }

    private static byte[] getOkContent(HttpRequest request) {
        byte[] authorizations = Base64.getDecoder().decode(request.getHeaders().get("Authorization").split("\\s+")[1]);
        String username = new String(authorizations);

        String createdObjectName = request.getBodyParameters().entrySet().stream()
                .findFirst()
                .get().getValue();

        if (request.getMethod().equals("GET")) {
            return String.format("Greetings %s!", username).getBytes();
        }
        StringBuilder placeholderParams = new StringBuilder();
        request.getBodyParameters().entrySet().stream()
                .skip(1)
                .forEach(kv -> {
                    placeholderParams.append(String.format("%s - %s, ",
                            kv.getKey(), kv.getValue()));
                });


        String responseBody = String.
                format("Greetings %s! You have successfully created %s with %s.",
                        username, createdObjectName,
                        placeholderParams.substring(0, placeholderParams.lastIndexOf(", "))
                );
        return responseBody.getBytes();
    }

    private static List<String> getValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }

    private static String getRequestString() throws IOException {
        StringBuilder request = new StringBuilder();
        String line = reader.readLine();
        request.append(line).append(System.lineSeparator());
        while (reader.ready()) {
            line = reader.readLine();
            if (line.equals("")) {
                request.append(System.lineSeparator());
                continue;
            }
            request.append(line).append(System.lineSeparator());
        }
        return request.toString().trim();
    }


    private static void addResponseHeaders(HttpRequest request, HttpResponse response) {
        for (Map.Entry<String, String> headerKv : request.getHeaders().entrySet()) {
            if (headerKv.getKey().equals("Date") ||
                    headerKv.getKey().equals("Host") ||
                    headerKv.getKey().equals("Content-Type")) {

                response.addHeader(headerKv.getKey(), headerKv.getValue());
            }
        }
    }
}
