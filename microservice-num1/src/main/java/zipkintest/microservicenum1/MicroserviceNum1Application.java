package zipkintest.microservicenum1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//@EnableZuulProxy
public class MicroserviceNum1Application {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceNum1Application.class, args);
	}
}
