package pb2_createClasses.http.contracts;

import java.util.Map;

public interface HttpRequest {
    Map<String, HttpCookie> getCookies();

    void setCookie(HttpCookie cookie);

    Map<String,String> getHeaders();

    Map<String,String> getBodyParameters();

    String getMethod();

    void setMethod(String method);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);

    void addHeader(String header, String value);

    void addBodyParameter(String parameter, String value);

    boolean isResource();
}
