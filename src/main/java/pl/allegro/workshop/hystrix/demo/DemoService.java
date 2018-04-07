package pl.allegro.workshop.hystrix.demo;

import org.springframework.stereotype.Component;

@Component
public class DemoService {
    private boolean throwsError;
    private int delay;

    public DemoService() {
        this.throwsError = false;
        this.delay = 0;
    }

    public boolean throwsError() {
        return throwsError;
    }

    public int getDelay() {
        return delay;
    }

    public void setThrowsError(boolean throwsError) {
        this.throwsError = throwsError;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
