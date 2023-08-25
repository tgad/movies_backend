package com.practice.movies;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class SearchService {


    @Autowired
    private ElasticsearchOperations operations;
    public List<Movie> searchMovies(String query) {
        // Fuzzy query
        QueryBuilder fuzzyQueryBuilder = QueryBuilders
                .fuzzyQuery("title", query)
                .fuzziness(Fuzziness.TWO);

        // Prefix query
        QueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("title", query);

        // Combine queries with bool query
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(fuzzyQueryBuilder)
                .should(prefixQueryBuilder);

        Query searchQuery = new StringQuery(boolQueryBuilder.toString());
//        NativeQuery searchQuery = NativeQuery.builder()
//                .withQuery(boolQueryBuilder)
//                .build();
        SearchHits<Movie> searchHits = operations.search(searchQuery, Movie.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
    public List<Movie> getAllMovies() {
        Query searchQuery = new StringQuery(QueryBuilders.matchAllQuery().toString());
        SearchHits<Movie> searchHits = operations.search(searchQuery, Movie.class);
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
