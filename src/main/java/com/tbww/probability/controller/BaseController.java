package com.tbww.probability.controller;

import com.tbww.probability.model.Help;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BaseController implements InterfaceController {

    @Override
    @GetMapping("/help")
    public Help getHelp() {
        String param = "Possibilities: Rock Paper Scissors = /RPS, Coin flip = /coin";

        return Help.builder().name("Probability Games")
                .usage("Append param choice onto API and request /help for more info. All games have a /playone endpoint that returns a direct random choice")
                .params(param)
                .sample("GET: /RPS/help").build();
    }

    @Override
    @GetMapping("/")
    public Help getDefault() {
        return getHelp();
    }

}
