package com.example.sushantkumaryatraonlinelocal.wikiapi.Pojo.SearchResultPojo;

public class WikiPage {
    private String title;
    private String extract;
    private String image;

    public WikiPage(String title,String extract,String image){
        this.setTitle(title);
        this.setExtract(extract);
        this.setImage(image);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
