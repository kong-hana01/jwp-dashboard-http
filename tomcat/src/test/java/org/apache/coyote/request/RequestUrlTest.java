package org.apache.coyote.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RequestUrlTest {

    @Test
    @DisplayName("url의 값을 파싱하여 RequestUrl을 생성할 수 있다.")
    void init_test() {
        RequestUrl requestUrl = RequestUrl.of("/index.html", new HashMap<>());

        assertThat(requestUrl.getUrl().getPath()).contains("/index.html");
    }

    @Test
    @DisplayName("url의 값이 '/'라면 index.html을 url로 가진다.")
    void home_url() {
        RequestUrl requestUrl = RequestUrl.of("/", new HashMap<>());

        assertThat(requestUrl.getUrl().getPath()).contains("/index.html");
    }

    @Test
    @DisplayName("url의 값이 존재하지 않는다면 404.html을 url로 가진다.")
    void no_url() {
        RequestUrl requestUrl = RequestUrl.of("/없는주소/입니다/ㅎㅎ", new HashMap<>());

        assertAll(
                () -> assertThat(requestUrl.getUrl().getPath()).contains("/404.html"),
                () -> assertThat(requestUrl.isNullPath()).isTrue()
        );
    }

    @Test
    @DisplayName("url의 값이 존재하지않지만 html 문서는 존재할 때 해당 html 문서를 url로 생성한다.")
    void no_url_html() {
        RequestUrl requestUrl = RequestUrl.of("/index", new HashMap<>());

        assertAll(
                () -> assertThat(requestUrl.getUrl().getPath()).contains("/index.html"),
                () -> assertThat(requestUrl.isNullPath()).isFalse()
        );
    }
}
