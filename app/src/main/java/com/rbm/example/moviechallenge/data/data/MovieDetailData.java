package com.rbm.example.moviechallenge.data.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailData extends MovieData {

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("runtime")
    private Integer runtime;

    @SerializedName("belongs_to_collection")
    private String belongs_to_collection;

    @SerializedName("budget")
    private int budget;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("imdb_id")
    private String imdb_id;

    @SerializedName("production_companies")
    private List<ProductionCompanies> production_companies;

    @SerializedName("production_countries")
    private List<ProductionCountries> production_countries;

    @SerializedName("revenue")
    private int revenue;

    @SerializedName("spoken_languages")
    private List<SpokenLanguages> spoken_languages;

    @SerializedName("status")
    private String status;

    @SerializedName("tagline")
    private String tagline;


    class ProductionCompanies {

        @SerializedName("name")
        private String name;

        @SerializedName("id")
        private int id;

        @SerializedName("logo_path")
        private String logo_path;

        @SerializedName("origin_country")
        private String origin_country;
    }

    class ProductionCountries {
        @SerializedName("iso_3166_1")
        private String iso_3166_1;

        @SerializedName("name")
        private String name;
    }

    class SpokenLanguages {
        @SerializedName("iso_639_1")
        private String iso_639_1;

        @SerializedName("name")
        private String name;
    }

    public class Genre {
        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(String belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public List<ProductionCompanies> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<ProductionCompanies> production_companies) {
        this.production_companies = production_companies;
    }

    public List<ProductionCountries> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<ProductionCountries> production_countries) {
        this.production_countries = production_countries;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public List<SpokenLanguages> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<SpokenLanguages> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
}
