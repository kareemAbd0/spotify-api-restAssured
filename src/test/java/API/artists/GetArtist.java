package API.artists;

import POJO.Artist;

import java.util.ArrayList;
import java.util.List;

public class GetArtist {
    private String endPoint;
    private String id;
    private Artist artist;
    private List objectList;

    public GetArtist(String id) {
        this.id = id;
        endPoint = "/artists/" + id;
    }



    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Artist getArtistPOJO() {
        return artist;
    }

    public void setArtistPOJO(Artist artist) {
        this.artist = artist;
    }


}
