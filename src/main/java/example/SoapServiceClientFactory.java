package example;

import com.dneonline.calculator.Calculator;
import com.dneonline.calculator.CalculatorSoap;
import io.micronaut.context.annotation.Factory;
import io.opentracing.Tracer;
import org.apache.cxf.tracing.opentracing.OpenTracingClientFeature;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.ws.BindingProvider;
import java.util.Map;

@Factory
public class SoapServiceClientFactory {

    @Inject
    private Tracer tracer;

    @Singleton
    public CalculatorSoap produceCalculatorSoap() {
        // create service with opentracing configured
        CalculatorSoap service = new Calculator().getCalculatorSoap(new OpenTracingClientFeature(tracer));
        BindingProvider bindingProvider = (BindingProvider) service;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();
        // needed to be able to reuse the client in multi-threaded environment
        requestContext.put("thread.local.request.context", "true");
        return service;
    }
}
