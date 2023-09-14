package nextstep.jwp.controller;

import java.util.Map;
import java.util.Set;
import nextstep.jwp.db.InMemoryUserRepository;
import nextstep.jwp.model.User;
import org.apache.coyote.fileReader.FileReader;
import org.apache.coyote.AbstractController;
import org.apache.coyote.http11.HttpMethod;
import org.apache.coyote.request.Request;
import org.apache.coyote.response.HttpStatus;
import org.apache.coyote.response.Response;

public class RegisterController extends AbstractController {

    private static final String URL = "/register";
    private static final Set<HttpMethod> AVAILABLE_HTTP_METHODS = Set.of(HttpMethod.GET, HttpMethod.POST);
    private static final String ACCOUNT = "account";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";

    public RegisterController() {
        super(URL, AVAILABLE_HTTP_METHODS);
    }

    @Override
    protected void doGet(Request request, Response response) {
        FileReader fileReader = FileReader.from(request.getPath());
        String body = fileReader.read();

        response.setHttpStatus(HttpStatus.OK)
                .addHeaders(CONTENT_TYPE, request.getResourceTypes() + FINISH_VALUE + ENCODING_UTF_8)
                .addHeaders(CONTENT_LENGTH, String.valueOf(body.getBytes().length))
                .setResponseBody(body);
    }

    @Override
    protected void doPost(Request request, Response response) {
        Map<String, String> body = request.getBody();
        String account = body.get(ACCOUNT);
        String password = body.get(PASSWORD);
        String email = body.get(EMAIL);

        if (InMemoryUserRepository.findByAccount(account).isPresent()) {
            return;
        }
        User user = new User(account, password, email);
        InMemoryUserRepository.save(user);

        response.setHttpStatus(HttpStatus.FOUND)
                .redirectLocation(INDEX_PATH);
    }
}
