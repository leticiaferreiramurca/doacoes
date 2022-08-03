package bb.com.donation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerHello {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello Guys!";
    }
}
