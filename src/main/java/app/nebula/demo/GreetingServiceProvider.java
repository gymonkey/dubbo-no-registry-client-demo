package app.nebula.demo;

import app.nebula.demo.api.GreetingsService;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.bootstrap.builders.ConsumerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GreetingServiceProvider {

    @Bean
    public GreetingsService get(){
        String serverAddr = System.getenv("SERVER_HOST");
        if (serverAddr == null || serverAddr.equalsIgnoreCase("")) {
            serverAddr = "dubbo://network-399-0.default:50052";
        }
        ReferenceConfig<GreetingsService> reference = new ReferenceConfig<>();
        reference.setInterface(GreetingsService.class);
        reference.setUrl(serverAddr);
        reference.setConnections(2);

        DubboBootstrap.getInstance()
                .application("first-dubbo-consumer")
                .reference(reference).consumer(new ConsumerBuilder().connections(2).build())
                .start();

        GreetingsService service = reference.get();
        return service;
    }
}
