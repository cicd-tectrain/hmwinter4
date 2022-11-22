package at.tectrain.cicd.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculateController {

    private final CalculatorService service;

    public CalculateController(CalculatorService service)
    {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return service.add(a, b);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sub")
    public int sub(@RequestParam int a, @RequestParam int b) {
        return service.subtract(a, b);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mult")
    public int multiply(@RequestParam int a, @RequestParam int b) {
        return service.multiply(a, b);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/divide")
    public int divide(@RequestParam int a, @RequestParam int b) {
        return service.divide(a, b);
    }
}
