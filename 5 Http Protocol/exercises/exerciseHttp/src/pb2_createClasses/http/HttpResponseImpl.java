package pb2_createClasses.http;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {
    private final static String PROTOCOL_VERSION="HTTP/1.1";

    private int statusCode;
    private HashMap<String, String> headers;
    private byte[] content;

    public HttpResponseImpl() {
        this.headers=new LinkedHashMap<>();
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        byte[] headersBytes=this.buildHeadersToBytes();
        byte[] responseBytes=this.buildTotalResponse(headersBytes);
        String responseToString=new String(responseBytes);
        System.out.println(responseToString); //printed only for visualisation
        return responseBytes;
    }

    private byte[] buildTotalResponse(byte[] headersBytes) {
        int headersLength = headersBytes.length;
        int contentLength = this.content.length;
        int contentTotalLength=headersLength+contentLength;

        byte[] result=new byte[contentTotalLength];
        for (int i = 0; i < headersLength; i++) {
            result[i]=headersBytes[i];
        }
        for(int i=0; i<contentLength; i++){
            result[i+headersLength]=this.content[i];
        }
        return result;
    }

    private byte[] buildHeadersToBytes() {
        StringBuilder headers=new StringBuilder();
        String statusPhrase=this.getStatusPhrase(this.statusCode);
        String statusLine=String.format("%s %d %s",PROTOCOL_VERSION,this.statusCode,statusPhrase);

        headers.append(statusLine).append(System.lineSeparator());
        for (Map.Entry<String,String> headerEntry : this.getHeaders().entrySet()) {
            headers.append(String.format("%s: %s",headerEntry.getKey(),headerEntry.getValue()))
            .append(System.lineSeparator());
        }
        headers.append(System.lineSeparator());
        return headers.toString().getBytes();
    }

    private String getStatusPhrase(int statusCode) {
        String statusPhrase=null;
        switch (statusCode){
            case 200:{
                statusPhrase="OK";
            }break;
            case 400:{
                statusPhrase="Bad Request";
            }break;
            case 401:{
                statusPhrase="Unauthorized";
            }break;
            case 404:{
                statusPhrase="Not Found";
            }break;
        }
        return statusPhrase;
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode=statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content=content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.getHeaders().put(header,value);
    }
}
