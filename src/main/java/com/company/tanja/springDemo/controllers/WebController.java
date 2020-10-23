package com.company.tanja.springDemo.controllers;

import com.company.tanja.springDemo.data_access.CustomerAPIRequests;
import com.company.tanja.springDemo.data_access.WebRequests;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class WebController {

    private WebRequests webRequests = new WebRequests();

    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public String showRandomArtists(Model model) {
        ArrayList<String> artists = webRequests.getRandomArtists();
        model.addAttribute("artists", artists);
        return "index";
    }

}