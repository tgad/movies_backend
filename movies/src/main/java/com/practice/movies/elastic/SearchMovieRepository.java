package com.practice.movies.elastic;

import com.practice.movies.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchMovieRepository extends ElasticsearchRepository<Movie,String> {
}
