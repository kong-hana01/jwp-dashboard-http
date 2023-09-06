package org.apache.coyote.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import org.apache.coyote.http11.ContentType;
import org.apache.coyote.http11.HttpMethod;
import org.apache.coyote.http11.Protocol;
import org.apache.coyote.request.Request;
import org.apache.coyote.request.RequestLine;
import org.apache.coyote.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultAdapterTest {

    @Test
    @DisplayName("hello world라는 정보가 담긴 response가 생성된다.")
    void doHandle() {
        Request request = new Request(
                new RequestLine(HttpMethod.GET, "/", Protocol.HTTP1_1, new HashMap<>()), ContentType.ALL);
        byte[] expected = "hello world!".getBytes();

        Response actual = new DefaultAdapter().doHandle(request);

        assertThat(actual.getResponseBytes()).contains(expected);
    }
}
