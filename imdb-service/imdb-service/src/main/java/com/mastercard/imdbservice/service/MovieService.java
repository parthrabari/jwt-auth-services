package com.mastercard.imdbservice.service;

import com.mastercard.imdbservice.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    public List<Movie> getTopTenMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("The Shawshank Redemption", 1994, 9.2));
        movies.add(new Movie("The Godfather", 1972, 9.1));
        movies.add(new Movie("The Godfather: Part II", 1974, 9.0));
        movies.add(new Movie("The Dark Knight", 2008, 9.0));
        movies.add(new Movie("12 Angry Men", 1957, 8.9));
        movies.add(new Movie("Schindler's List", 1993, 8.9));
        movies.add(new Movie("The Lord of the Rings: The Return of the King", 2003, 8.9));
        movies.add(new Movie("Pulp Fiction", 1994, 8.9));
        movies.add(new Movie("The Good, the Bad and the Ugly", 1966, 8.8));
        movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", 2001, 8.8));

        return movies;
    }
}
