package pb2_createClasses.http;

import pb2_createClasses.http.contracts.HttpCookie;
import pb2_createClasses.http.contracts.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class HttpRequestImpl implements HttpRequest {
    private String requestUrl;
    private String method;
    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private Map<String, HttpCookie> cookies;

    public HttpRequestImpl(String request) {
        this.headers = new LinkedHashMap<>();
        this.bodyParameters = new LinkedHashMap<>();
        this.cookies = new LinkedHashMap<>();
        this.initializeRequest(request);
        this.initializeCookies();
    }

    private void initializeCookies() {
        if (this.headers.keySet().contains("Cookie")) {
            String cookiesString = this.headers.get("Cookie");
            HashMap<String, HttpCookie> cookies = new LinkedHashMap<>();

            Arrays.stream(cookiesString.split(";\\s+"))
                    .map(token -> token.split("="))
                    .forEach(kv -> {
                        cookies.put(kv[0], new HttpCookieImpl(kv[0], kv[1]));
                    });
            this.cookies = cookies;
        }
    }

    private void initializeRequest(String request) {
        BufferedReader reader = new BufferedReader(new StringReader(request));

        try {

            String requestLine = reader.readLine();
            String[] requestLineTokens = requestLine.split("\\s+");

            this.method = requestLineTokens[0];
            String initialUrl = requestLineTokens[1];
            this.requestUrl = initialUrl;
            if (initialUrl.contains("?")) {
                String[] urlTokens = initialUrl.split("\\?");
                String bodyParams = urlTokens[1];
                this.fillParams(bodyParams);
            }

            String currentHeader;
            while ((currentHeader = reader.readLine())!=null&&currentHeader.length()>0) {
                String[] currentHeaderTokens = currentHeader.split(": ");
                String header = currentHeaderTokens[0];
                String value = currentHeaderTokens[1];
                this.headers.put(header, value);
            }
            if ((currentHeader = reader.readLine()) != null) {
                this.fillParams(currentHeader);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillParams(String bodyParams) {
        Map<String, String> bodyParameters = new LinkedHashMap<>();
        String[] pairs = bodyParams.split("&");
        for (String pair : pairs) {
            String[] currentPair = pair.split("=");
            String key = currentPair[0];
            String value = currentPair[1];
            bodyParameters.put(key, value);
        }
        this.bodyParameters = bodyParameters;
    }

    @Override
    public Map<String, HttpCookie> getCookies() {
        return Collections.unmodifiableMap(this.cookies);
    }

    @Override
    public void setCookie(HttpCookie cookie) {
        this.cookies.put(cookie.getKey(), cookie);
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(".");
    }
}
