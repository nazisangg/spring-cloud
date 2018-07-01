package zipkintest.microservicenum2.Controller;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private OkHttpClient client;

    private Random random = new Random();

    @RequestMapping("foo")
    public String foo() throws InterruptedException, IOException {
        int sleep= random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        Request request = new Request.Builder().url("http://localhost:8083/bar").get().build();  //service3
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        request = new Request.Builder().url("http://localhost:8084/tar").get().build();  //service4
        response = client.newCall(request).execute();
        result += response.body().string();
        return " [service2 sleep " + sleep+" ms]" + result;
    }

    @RequestMapping("posttwo")
    public String postTwo() throws InterruptedException, IOException {
        int sleep= random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        Request request = new Request.Builder().url("http://localhost:8083/postthree").post(RequestBody.create(MediaType.parse("String"), "Hi this is bill two are you three?")).build();  //service3
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        request = new Request.Builder().url("http://localhost:8084/postfour").post(RequestBody.create(MediaType.parse("String"),"Hi this is bill two are you four?")).build();  //service4
        response = client.newCall(request).execute();
        result += response.body().string();
        return " [service2 sleep " + sleep+" ms]" + result;
    }

}
