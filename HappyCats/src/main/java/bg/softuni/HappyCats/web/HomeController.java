package bg.softuni.HappyCats.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String start() {
        return "index";
    }
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/service")
    public String service(){
        return "service";
    }

    @GetMapping("/price")
    public String price(){
        return "price";
    }
}
