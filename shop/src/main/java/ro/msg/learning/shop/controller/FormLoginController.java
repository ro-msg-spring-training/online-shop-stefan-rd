package ro.msg.learning.shop.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ConditionalOnProperty(prefix = "security", name = "type", havingValue = "withForm", matchIfMissing = false)
public class FormLoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
