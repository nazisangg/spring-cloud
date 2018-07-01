package zipkintest.microservicenum1.Utils;
import com.netflix.zuul.ZuulFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Component
public class LoggerFilter extends ZuulFilter {

    @Autowired
    Tracer tracer;

    @Override
    public String filterType(){
        return "Post";
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        tracer.addTag("operator","BillXia");
        System.out.print(tracer.getCurrentSpan().getTraceId());
        return null;
    }

}
