package com.practice.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
//the data of movies are come from Moviedb.org
@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@org.springframework.data.elasticsearch.annotations.Document(indexName = "movies") // Elasticsearch

public class Movie {

    @Id
    private String id;
    @Field(type = FieldType.Keyword) // Elasticsearch
    private String imdbId;
    @Field(type = FieldType.Text) // Elasticsearch
    private String title;

    private String releaseDate;

    private String trailerLink;

    private String poster;

    private List<String> genres;

    private List<String> backdrops;

//    @DBRef
    // just omit @DocumentReference
    private List<Review> reviewIds;
//this time I need a list called List<watchMovie>?
    // or I need to create a new pojo
    //in it I have List<Movie>
    //yep this maybe correct
    //then it has @Id
    //and the timestamp




}
