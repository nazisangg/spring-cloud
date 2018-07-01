package zipkintest.microservicenum3.Controller;

import okhttp3.OkHttpClient;
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

    @RequestMapping("bar")
    public String bar() throws InterruptedException, IOException {  //service3 method
        int sleep= random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return " [service3 sleep " + sleep+" ms]";
    }

    @RequestMapping("postthree")
    public String postThree() throws InterruptedException, IOException {  //service3 method
        int sleep= random.nextInt(100);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return " hi this is three";
    }
}
