package com.tbww.probability.controller;

import com.tbww.probability.model.Help;
import com.tbww.probability.model.Response;
import com.tbww.probability.model.roshambo.RoshamboEnum;
import com.tbww.probability.service.roshambo.RoshamboService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RPS")
public class RoshamboController implements InterfaceController {

    @Autowired
    private RoshamboService service;

    @Override
    @GetMapping("/help")
    public Help getHelp() {
        String param = "choice: ROCK, PAPER, SCISSOR + level(Optional): 0 = Normal (Default), 1 = Hard, 2 = Impossible";

        return Help.builder().name("Rock, Paper, Scissor").usage("Specify choice with optional level").params(param)
                .sample("GET: /RPS/play?choice=PAPER&level=1").build();
    }

    @GetMapping("/play")
    public Response playGame(@RequestParam(value = "choice") RoshamboEnum choice,
            @RequestParam(value = "level", defaultValue = "0") int level) {
        return service.compute(choice, level);
    }

    @GetMapping("/")
    public Help getDefault() {
        return getHelp();
    }
}