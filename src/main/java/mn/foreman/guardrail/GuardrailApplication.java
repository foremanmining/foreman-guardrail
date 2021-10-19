package mn.foreman.guardrail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** The application entry point. */
@SpringBootApplication
public class GuardrailApplication {

    /**
     * Application entry point.
     *
     * @param args The arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(
                GuardrailApplication.class,
                args);
    }
}