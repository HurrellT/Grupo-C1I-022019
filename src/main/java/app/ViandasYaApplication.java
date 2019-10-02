package app;

import app.api.user.UserRepository;
import app.model.User.Client.ClientFactory;
import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ViandasYaApplication {


    public static void main(String[] args) {
        SpringApplication.run(ViandasYaApplication.class, args);
    }
}

@Component
class DBPreloader {

    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        //Users to Preload
        User tomasHurrell = ClientFactory.tomasHurrell();
        User federicoMartinez = ClientFactory.federicoMartinez();

        userRepository.save(tomasHurrell);
        userRepository.save(federicoMartinez);
    }
}
