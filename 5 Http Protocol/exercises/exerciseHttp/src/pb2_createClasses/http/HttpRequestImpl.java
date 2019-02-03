package pb2_createClasses.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class HttpRequestImpl implements HttpRequest {
    private String requestUrl;
    private String method;
    private HashMap<String,String> headers;
    private HashMap<String,String> bodyParameters;

    public HttpRequestImpl(String request) {
        this.headers=new LinkedHashMap<>();
        this.bodyParameters=new LinkedHashMap<>();
        this.initializeRequest(request);
    }

    private void initializeRequest(String request)  {
        BufferedReader reader=new BufferedReader(new StringReader(request));

        try {

            String requestLine=reader.readLine();
            String[] requestLineTokens = requestLine.split("\\s+");

            this.method=requestLineTokens[0];
            String initialUrl = requestLineTokens[1];
            this.requestUrl=initialUrl;
            if(initialUrl.contains("?")){
                String[] urlTokens = initialUrl.split("\\?");
                String bodyParams=urlTokens[1];
                this.fillParams(bodyParams);
            }

            String currentHeader;
            while (!"".equals(currentHeader =reader.readLine())){
                String[] currentHeaderTokens = currentHeader.split(": ");
                String header=currentHeaderTokens[0];
                String value=currentHeaderTokens[1];
                this.headers.put(header,value);
            }
            if((currentHeader =reader.readLine())!=null){
                this.fillParams(currentHeader);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillParams(String bodyParams) {
        HashMap<String, String> bodyParameters=new LinkedHashMap<>();
        String[] pairs = bodyParams.split("&");
        for (String pair : pairs) {
            String[] currentPair = pair.split("=");
            String key=currentPair[0];
            String value=currentPair[1];
            bodyParameters.put(key,value);
        }
        this.bodyParameters=bodyParameters;
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public HashMap<String, String> getBodyParameters() {
        return this.bodyParameters;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method=method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl=requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header,value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter,value);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(".");
    }
}
