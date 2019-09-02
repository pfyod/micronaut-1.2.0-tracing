package example;

import com.dneonline.calculator.CalculatorSoap;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.scheduling.annotation.Scheduled;

import javax.inject.Inject;

@Prototype
public class BackgroundTasks {
    @Inject
    private CalculatorSoap calculatorSoap;

    @Scheduled(fixedRate = "10s")
    public void runLoadAirlineToAlliancesMapping() {
        calculatorSoap.add(2, 2);
    }
}
