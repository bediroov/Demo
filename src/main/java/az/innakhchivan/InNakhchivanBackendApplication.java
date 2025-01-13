package az.innakhchivan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class InNakhchivanBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(InNakhchivanBackendApplication.class, args);
    }

}
