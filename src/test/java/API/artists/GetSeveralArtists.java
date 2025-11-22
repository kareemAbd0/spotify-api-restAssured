package API.artists;

import java.util.List;

public class GetSeveralArtists {

    private String endPoint;
    private List<String> ids;

    public GetSeveralArtists(List<String> ids) {
        this.ids = ids;
        endPoint = "/artists";
    }



    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public List<String> getIds() {
        return ids;
    }

    public String getCommaSeparatedIds() {
        return String.join(",", ids);
    }

    public void setId(List<String> ids) {
        this.ids = ids;
    }
}
