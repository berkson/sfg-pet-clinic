package com.ximenes.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by berkson
 * Date: 03/06/2021
 * Time: 22:44
 */
@RequestMapping("owners")
@Controller
public class OwnerController {

    @RequestMapping({"", "/", "/index", "o/index.html"})
    public String listOwners() {

        return "owners/index";
    }
}
