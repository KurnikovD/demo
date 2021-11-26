//package com.example.demo;
//
//
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@RestController
//public class Controller {
//
//    private static final HashMap<String, Integer> domains = new HashMap<String, Integer>();
//    private static final List<String> url_list = new ArrayList<>();
//
//    @RequestMapping(value = "/add/{name:[a-zA-Z0-9.]*}")
//    public List<String> add(@PathVariable String name) {
//        try {
//            String[] url = name.split("/");
//            url = url[0].split("\\.");
//
//            String host = url[url.length - 2];
//
//            if (domains.containsKey(host))
//                domains.compute(host, (k, v) -> v = v + 1);
//            else
//                domains.put(host, 0);
//
//            url_list.add(name);
//
//            return url_list;
//        }catch (ArrayIndexOutOfBoundsException e){
//            System.out.println(e);
//            return Arrays.asList("Wrong URL!");
//        }
//    }
//
//    @RequestMapping(value = "/top/{n}")
//    public List<String> top(@PathVariable int n){
//        return domains.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .map(Map.Entry::getKey).limit(n).collect(Collectors.toList());
//    }
//
//    @RequestMapping(value = "/domain")
//    public HashMap<String, Integer> getDomains(){
//        return domains;
//    }
//}
