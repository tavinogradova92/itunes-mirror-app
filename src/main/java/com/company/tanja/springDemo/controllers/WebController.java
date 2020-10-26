package com.company.tanja.springDemo.controllers;

import com.company.tanja.springDemo.data_access.WebRequests;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private WebRequests webRequests = new WebRequests();

    // showing five random artists, songs and genres, search input
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("artists", webRequests.getRandomArtists());
        model.addAttribute("songs", webRequests.getRandomSongs());
        model.addAttribute("genres", webRequests.getRandomGenres());
        return "index";
    }

    // searching for songs
    @GetMapping("/search")
    public String simpleSearch(@RequestParam(name = "searchterm") String search, Model model) {
        model.addAttribute("songList", webRequests.getSongsFoundList(search));
        model.addAttribute("query", search);
        return "search";
    }

}