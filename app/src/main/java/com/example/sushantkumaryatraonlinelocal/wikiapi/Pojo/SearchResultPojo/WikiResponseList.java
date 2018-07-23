package com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.SearchResultPojo;


import com.google.gson.annotations.SerializedName;

public class WikiResponseList {
    @SerializedName("pageid")
    private int pageId;
    @SerializedName("title")
    private String title;

    private String description;
    private String image;

    public WikiResponseList(String title, String image,String description,int pageId){
        this.setPageId(pageId);
        this.title = title;
        this.image = image;
        this.setDescription(description);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
