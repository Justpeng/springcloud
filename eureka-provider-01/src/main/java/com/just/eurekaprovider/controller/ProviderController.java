package com.just.eurekaprovider.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/provider")
public class ProviderController {

    @GetMapping("/info")
    public String getDescription() {
        return "this is provider 01";
    }

}
