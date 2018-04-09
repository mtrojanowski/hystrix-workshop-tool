package pl.allegro.workshop.hystrix.demo;

import org.springframework.stereotype.Component;

@Component
public class DemoService {
    private boolean throwsError;
    private int delay;
    private boolean throwsClientError;

    public DemoService() {
        this.throwsError = false;
        this.delay = 0;
        this.throwsClientError = false;
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

    public boolean throwsClientError() {
        return throwsClientError;
    }

    public void setThrowsClientError(boolean throwsClientError) {
        this.throwsClientError = throwsClientError;
    }
}
