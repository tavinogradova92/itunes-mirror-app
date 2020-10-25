package com.company.tanja.springDemo.controllers;

import com.company.tanja.springDemo.data_access.CustomerAPIRequests;
import com.company.tanja.springDemo.data_access.WebRequests;
import com.company.tanja.springDemo.models.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class WebController {

    private WebRequests webRequests = new WebRequests();

    // showing five random artists, songs and genres
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showRandomStaff(Model model) {
        model.addAttribute("artists", webRequests.getRandomArtists());
        model.addAttribute("songs", webRequests.getRandomSongs());
        model.addAttribute("genres", webRequests.getRandomGenres());
        model.addAttribute("songList", webRequests.getSongsFoundList());
        return "index";
    }

    // searching for songs
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String simpleSearch(@RequestParam("query") String terms) {
        return "Search results for " + terms;
    }


}