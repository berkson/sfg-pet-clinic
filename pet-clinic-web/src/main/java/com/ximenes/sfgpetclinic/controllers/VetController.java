package com.ximenes.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by berkson
 * Date: 03/06/2021
 * Time: 22:35
 */
@Controller
public class VetController {

    @RequestMapping({"vets", "vets/index", "vets/index.html"})
    public String listVets() {
        return "vets/index";
    }
}
