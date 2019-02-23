import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Neeraj Sidhaye
 */

@ComponentScan(basePackages = "com.xp.*")
@SpringBootApplication
public class SseServerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SseServerApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
}
