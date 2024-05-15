package app.nebula.demo;

import app.nebula.demo.api.GreetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private GreetingsService greetingsService;

    @GetMapping("/invoke")
    public String invoke(){
        System.out.println("invoke rpc provider");
        return greetingsService.sayHi("nebula");
    }

}
