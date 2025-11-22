package POJO.artists;

import java.util.List;

public class SeveralArtistsPOJO {
    private List<ArtistPOJO> artistPOJOS;

    // Standard Getter and Setter
    public List<ArtistPOJO> getArtists() {
        return artistPOJOS;
    }

    public void setArtists(List<ArtistPOJO> artistPOJOS) {
        this.artistPOJOS = artistPOJOS;
    }
}
