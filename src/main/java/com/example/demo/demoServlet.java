package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class demoServlet extends HttpServlet {

    private static final HashMap<String, Integer> domains = new HashMap<String, Integer>();
    private static final List<String> url_list = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().startsWith("/add/")){
            resp.setContentType("text/html");
            String html = "<http><head></head><body>%s</body></html>";
            String name = req.getRequestURI().split("/add/")[1];
            resp.getWriter().print(String.format(html, add(name)));
        }


        if (req.getRequestURI().startsWith("/top/")){
            resp.setContentType("text/html");
            String html = "<http><head></head><body><ul>%s</ul></body></html>";
            String name = req.getRequestURI().split("/top/")[1];
            resp.getWriter().print(String.format(html, top(name)));
        }
    }

    private String add(String name){
        try{
            String[] url = name.split("/", 2)[0].split("\\.");
            String host = url[url.length-2];

            if (domains.containsKey(host))
                domains.compute(host, (k, v) -> v += 1);
            else
                domains.put(host, 0);

            url_list.add(name);

            StringBuilder html = new StringBuilder("<lu><lu>");
            url_list.forEach(el -> html.append("<li>").append(el).append("</li>"));
            html.append("</lu>");

            return html.toString();

        }catch (ArrayIndexOutOfBoundsException e){
            return "Wrong! Could not find host!";
        }

    }

    private String top(String name){
        try {
            int n = Integer.parseInt(name);
            StringBuilder html = new StringBuilder("<lu><lu>");
            domains.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey).limit(n).collect(Collectors.toList())
                    .forEach(el -> html.append("<li>").append(el).append("</li>"));
            html.append("</lu>");
            return html.toString();
        }catch (Exception e){
            return "Wrong! Is not a number";
        }
    }
}
