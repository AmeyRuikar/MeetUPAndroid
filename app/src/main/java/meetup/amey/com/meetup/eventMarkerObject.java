package meetup.amey.com.meetup;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ameyruikar on 11/24/16.
 */
public class eventMarkerObject {

    String eventName;
    String genre;
    String subgenre;
    String rating;
    LatLng position;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String url;

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public eventMarkerObject(String eventid, String eventName, String g, String subg, LatLng latLng, String url, String r) {
        this.eventName = eventName;
        this.eventid = eventid;
        this.genre = g;
        this.subgenre = subg;
        this.position = latLng;
        this.url = url;
        this.rating = r;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    String eventid;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


}
