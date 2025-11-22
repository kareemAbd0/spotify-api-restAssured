package API.artists;

public class GetArtist {
    private String endPoint;
    private String id;

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

}
