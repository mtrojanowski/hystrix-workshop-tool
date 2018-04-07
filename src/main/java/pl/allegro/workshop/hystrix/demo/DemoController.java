package pl.allegro.workshop.hystrix.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/demo")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String demo() throws Exception {

        if (demoService.throwsError()) {
            throw new Exception("It doesn't work :(");
        }

        if (demoService.getDelay() > 0) {
            Thread.sleep(demoService.getDelay());
        }

        return "OK";
    }

    @PostMapping("/switcherror")
    @ResponseStatus(HttpStatus.OK)
    public void switchThrowError() {
        demoService.setThrowsError(!demoService.throwsError());
    }

    @PostMapping("/setdelay")
    @ResponseStatus(HttpStatus.OK)
    public void setDelay(@RequestBody DelayDto delay) {
        demoService.setDelay(delay.getValue());
    }

}
