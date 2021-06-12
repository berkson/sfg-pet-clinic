package com.ximenes.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by berkson
 * Date: 03/06/2021
 * Time: 21:55
 */
@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {

        return "index";
    }

    @RequestMapping("/oups")
    public String oups(){
        return "notImplemented";
    }
}
