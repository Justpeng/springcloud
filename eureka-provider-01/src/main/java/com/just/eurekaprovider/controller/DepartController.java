package com.just.eurekaprovider.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/depart")
public class DepartController {

    @GetMapping("/names")
    public List<String> getDepartNames() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        return list;
    }

}
