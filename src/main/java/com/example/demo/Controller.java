package com.example.demo;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class Controller {

    private static final HashMap<String, Integer> domains = new HashMap<String, Integer>();
    private static final List<String> url_list = new ArrayList<>();

    @RequestMapping(value = "/add/{name}")
    public List<String> add( @PathVariable String name) {

        if (domains.containsKey(name))
            domains.compute(name, (k, v) -> v = v + 1);
        else
            domains.put(name, 0);

        url_list.add(name);

        return url_list;
    }

    @RequestMapping(value = "/top/{n}")
    public List<String> top(@PathVariable int n){

        return domains.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).limit(n).collect(Collectors.toList());
    }

    @RequestMapping(value = "/domain")
    public HashMap<String, Integer> getDomains(){
        return domains;
    }
}
