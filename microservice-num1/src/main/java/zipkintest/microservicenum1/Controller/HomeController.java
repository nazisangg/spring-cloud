package zipkintest.microservicenum1.Controller;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zipkintest.microservicenum1.Utils.LoggerFilter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private OkHttpClient client;

    @Autowired
    private LoggerFilter loggerFilter;

    private Random random = new Random();

    @RequestMapping("start")
    public String start() throws InterruptedException, IOException {
        int sleep= random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        Request request = new Request.Builder().url("http://localhost:8082/foo").get().build();
        Response response = client.newCall(request).execute();
        return " [service1 sleep " + sleep+" ms]" + response.body().toString();
    }


    @RequestMapping("post")
    public String startPost() throws InterruptedException, IOException{
        int sleep= random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        Request request = new Request.Builder().url("http://localhost:8082/posttwo").post(RequestBody.create(MediaType.parse("String"), "this is bill")).build();
        Response response = client.newCall(request).execute();
        loggerFilter.run();
        return response.body().toString();
    }
}
