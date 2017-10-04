package com.example.stenleyaltidor.popularmoviesstage1;




public  class Movies {

    private String poster;
    private String release_date;
    private String original_title;
    private String overview;
    private String vote_average;
    private String id;


    public Movies() {
    }

    public Movies(String poster, String release_date, String original_title, String overview, String vote_average,String id) {
        this.poster = poster;
        this.release_date = release_date;
        this.original_title = original_title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.id = id;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}
