package lamph11.home.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class Test {


    @GetMapping
    public String test(@RequestParam(name = "page",required = true) Integer page){
        System.out.println(page);
        return "Test Api";
    }
}
