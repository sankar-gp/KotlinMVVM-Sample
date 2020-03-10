package com.sankar.kotlinmvvm.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sankar.kotlinmvvm.model.ListModel;

import java.util.List;

public class ListViewResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<ListModel> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<ListModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ListModel> articles) {
        this.articles = articles;
    }
}
