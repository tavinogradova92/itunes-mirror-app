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

    // showing five random artists, songs and genres
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showRandomStaff(Model model) {
        ArrayList<String> artists = webRequests.getRandomArtists();
        ArrayList<String> songs = webRequests.getRandomSongs();
        ArrayList<String> genres = webRequests.getRandomGenres();
        model.addAttribute("artists", artists);
        model.addAttribute("songs", songs);
        model.addAttribute("genres", genres);
        return "index";
    }


}