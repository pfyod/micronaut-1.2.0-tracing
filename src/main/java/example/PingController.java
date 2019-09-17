package example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@Controller("/ping")
public class PingController {
    @Get
    public String plain(HttpRequest<?> request) {
        return "Pong";
    }

    @Get("/reactive")
    public Single<String> reactive(HttpRequest<?> request) {
        return Single.just("Pong").subscribeOn(Schedulers.newThread());
    }
}