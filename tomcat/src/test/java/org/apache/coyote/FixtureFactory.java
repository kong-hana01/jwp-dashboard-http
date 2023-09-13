package org.apache.coyote;

import java.util.HashMap;
import java.util.Map;
import org.apache.coyote.http11.HttpMethod;
import org.apache.coyote.http11.Protocol;
import org.apache.coyote.request.Request;
import org.apache.coyote.request.RequestBody;
import org.apache.coyote.request.RequestHeader;
import org.apache.coyote.request.RequestLine;
import org.apache.coyote.request.RequestUrl;

public class FixtureFactory {

    public static final Map<String, String> DEFAULT_HEADERS = new HashMap<>() {{
        put("Accept", "*/*");
        put("Host", "localhost:8080");
        put("Connection", "keep-alive");
    }};

    public static RequestLine getRequestLine(HttpMethod httpMethod, String url) {
        return new RequestLine(httpMethod, RequestUrl.from(url), Protocol.HTTP1_1);
    }

    public static RequestHeader getRequestHeader(Map<String, String> headers) {
        return new RequestHeader(headers);
    }

    public static Request getHttpGetRequest(String url, Map<String, String> headers) {
        return new Request(getRequestLine(HttpMethod.GET, url), getRequestHeader(headers), new RequestBody(new HashMap<>()));
    }

    public static Request getHttpPostRequest(String url, Map<String, String> headers, RequestBody requestBody) {
        return new Request(getRequestLine(HttpMethod.POST, url), getRequestHeader(headers), requestBody);
    }
}
