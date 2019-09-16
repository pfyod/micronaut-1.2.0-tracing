package example;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
@Controller("/ping")
public class PingController {
    @Get
    public HttpResponse<String> authorize(HttpRequest<?> request) {
        return HttpResponse.<String> status(HttpStatus.OK).body("Pong");
    }
}