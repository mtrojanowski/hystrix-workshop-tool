package pl.allegro.workshop.hystrix.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/demo")
    @ResponseBody
    public String demo(HttpServletResponse response) throws Exception {
        if (demoService.throwsError()) {
            throw new Exception("It doesn't work :(");
        }

        Random random = ThreadLocalRandom.current();
        if (demoService.throwsClientError() || random.nextFloat() <= 0.1) {
            response.setStatus(401);
            return "Not quite OK";
        }

        response.setStatus(200);


        if (demoService.getDelay() > 0) {
            Thread.sleep(demoService.getDelay());
        }

        return "OK";
    }

    @PostMapping("/switcherror")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String switchThrowError() {
        demoService.setThrowsError(!demoService.throwsError());

        return demoService.throwsError() ? "Service now returns error :|\n" : "Service is running smoothly ;]\n";
    }

    @PostMapping("/setdelay")
    @ResponseStatus(HttpStatus.OK)
    public String setDelay(@RequestBody DelayDto delay) {
        demoService.setDelay(delay.getValue());

        return demoService.getDelay() > 0 ? "Service now works with " + demoService.getDelay() + " ms of delay\n" : "Service works without delay\n";
    }

    @PostMapping("/switchclienterror")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String switchClientError() {
        demoService.setThrowsClientError(!demoService.throwsClientError());

        return demoService.throwsClientError() ? "Service now returns 422\n" : "Service is running smoothly ;]\n";
    }
}
