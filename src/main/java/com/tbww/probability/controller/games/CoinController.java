package com.tbww.probability.controller.games;

import com.tbww.probability.controller.InterfaceController;
import com.tbww.probability.model.Help;
import com.tbww.probability.model.Response;
import com.tbww.probability.model.coin.CoinEnum;
import com.tbww.probability.service.coin.CoinService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coin")
public class CoinController implements InterfaceController {

    @Autowired
    CoinService service;

    @Override
    @GetMapping("/help")
    public Help getHelp() {
        String param = "choice: HEAD/TAIL + level(Optional): 0 = Normal (Default), 1 = Impossible";

        return Help.builder().name("Coin Flip").usage("Specify choice with optional level").params(param)
                .sample("GET: /coin/play?choice=HEAD&level=1").build();
    }

    @Override
    @GetMapping("/")
    public Help getDefault() {
        return getHelp();
    }

    @GetMapping("/play")
    public Response playGame(@RequestParam(value = "choice") CoinEnum choice,
            @RequestParam(value = "level", defaultValue = "0") int level) {
        return service.flip(choice, level);
    }

    @GetMapping("/playone")
    public CoinEnum playOne() {
        return service.flipOne();
    }

}
