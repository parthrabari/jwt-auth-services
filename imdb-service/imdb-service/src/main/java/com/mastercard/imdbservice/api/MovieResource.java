package com.mastercard.imdbservice.api;

import com.mastercard.imdbservice.model.Movie;
import com.mastercard.imdbservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String helloWorld() {
        return "hello, stranger!";
    }

    @GetMapping("/movies")
    public List<Movie> getTopMovies() {
        return this.movieService.getTopTenMovies();
    }
}
