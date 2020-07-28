package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.service.TestService;

@RestController
@Profile("test")
@RequestMapping("/test")
public class TestController
{
    @Autowired
    private TestService testService;

    @PostMapping("/populate")
    public void populate()
    {
        this.testService.populate();
    }

    @DeleteMapping("/clear")
    public void clear()
    {
        this.testService.clear();
    }
}
