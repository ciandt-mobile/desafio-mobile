package com.rangeldor.movieapp.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Result {

        @SerializedName("popularity")
        @Expose
        private Double popularity;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("video")
        @Expose
        private Boolean video;
        @SerializedName("vote_count")
        @Expose
        private Integer voteCount;
        @SerializedName("vote_average")
        @Expose
        private Double voteAverage;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("release_date")
        @Expose
        private String releaseDate;
        @SerializedName("original_language")
        @Expose
        private String originalLanguage;
        @SerializedName("original_title")
        @Expose
        private String originalTitle;
        @SerializedName("genre_ids")
        @Expose
        private List<Integer> genreIds = null;
        @SerializedName("backdrop_path")
        @Expose
        private String backdropPath;
        @SerializedName("adult")
        @Expose
        private Boolean adult;
        @SerializedName("overview")
        @Expose
        private String overview;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("homepage")
        @Expose
        private String homepage;
        @SerializedName("runtime")
        @Expose
        private String runtime;
        @SerializedName("videos")
        @Expose
        private Videos videos;
        @SerializedName("genres")
        @Expose
        private List<Genre> genres = null;

        public List<Genre> getGenres() {
            return genres;
        }

        public void setGenres(List<Genre> genres) {
            this.genres = genres;
        }

        public Videos getVideos() {
            return videos;
        }

        public void setVideos(Videos videos) {
            this.videos = videos;
        }

        public Double getPopularity() {
            return popularity;
        }

        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getVideo() {
            return video;
        }

        public void setVideo(Boolean video) {
            this.video = video;
        }

        public Integer getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
        }

        public Double getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public Boolean getAdult() {
            return adult;
        }

        public void setAdult(Boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getHomepage(){
            return homepage;
        }

        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }

        public String getRuntime(){
            return runtime;
        }

        public void setRuntime(String runtime) {
            this.runtime = runtime;
        }

        public class Videos {

            @SerializedName("results")
            @Expose
            private List<Result_> results = null;

            public List<Result_> getResults() {
                return results;
            }

            public void setResults(List<Result_> results) {
                this.results = results;
            }

            public class Result_ {

                @SerializedName("id")
                @Expose
                private String id;
                @SerializedName("iso_639_1")
                @Expose
                private String iso6391;
                @SerializedName("iso_3166_1")
                @Expose
                private String iso31661;
                @SerializedName("key")
                @Expose
                private String key;
                @SerializedName("name")
                @Expose
                private String name;
                @SerializedName("site")
                @Expose
                private String site;
                @SerializedName("size")
                @Expose
                private Integer size;
                @SerializedName("type")
                @Expose
                private String type;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getIso6391() {
                    return iso6391;
                }

                public void setIso6391(String iso6391) {
                    this.iso6391 = iso6391;
                }

                public String getIso31661() {
                    return iso31661;
                }

                public void setIso31661(String iso31661) {
                    this.iso31661 = iso31661;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getSite() {
                    return site;
                }

                public void setSite(String site) {
                    this.site = site;
                }

                public Integer getSize() {
                    return size;
                }

                public void setSize(Integer size) {
                    this.size = size;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

            }
        }
        public class Genre {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

        }
    }

    public class Detail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("cast")
        @Expose
        private List<Cast> cast = null;
        @SerializedName("crew")
        @Expose
        private List<Crew> crew = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<Cast> getCast() {
            return cast;
        }

        public void setCast(List<Cast> cast) {
            this.cast = cast;
        }

        public List<Crew> getCrew() {
            return crew;
        }

        public void setCrew(List<Crew> crew) {
            this.crew = crew;
        }

        public class Cast {

            @SerializedName("cast_id")
            @Expose
            private Integer castId;
            @SerializedName("character")
            @Expose
            private String character;
            @SerializedName("credit_id")
            @Expose
            private String creditId;
            @SerializedName("gender")
            @Expose
            private Integer gender;
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("order")
            @Expose
            private Integer order;
            @SerializedName("profile_path")
            @Expose
            private String profilePath;

            public Integer getCastId() {
                return castId;
            }

            public void setCastId(Integer castId) {
                this.castId = castId;
            }

            public String getCharacter() {
                return character;
            }

            public void setCharacter(String character) {
                this.character = character;
            }

            public String getCreditId() {
                return creditId;
            }

            public void setCreditId(String creditId) {
                this.creditId = creditId;
            }

            public Integer getGender() {
                return gender;
            }

            public void setGender(Integer gender) {
                this.gender = gender;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }

            public String getProfilePath() {
                return profilePath;
            }

            public void setProfilePath(String profilePath) {
                this.profilePath = profilePath;
            }

        }

        public class Crew {

            @SerializedName("credit_id")
            @Expose
            private String creditId;
            @SerializedName("department")
            @Expose
            private String department;
            @SerializedName("gender")
            @Expose
            private Integer gender;
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("job")
            @Expose
            private String job;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("profile_path")
            @Expose
            private String profilePath;

            public String getCreditId() {
                return creditId;
            }

            public void setCreditId(String creditId) {
                this.creditId = creditId;
            }

            public String getDepartment() {
                return department;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public Integer getGender() {
                return gender;
            }

            public void setGender(Integer gender) {
                this.gender = gender;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProfilePath() {
                return profilePath;
            }

            public void setProfilePath(String profilePath) {
                this.profilePath = profilePath;
            }

        }

    }
}


