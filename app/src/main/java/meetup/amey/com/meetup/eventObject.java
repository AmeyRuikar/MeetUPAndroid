package meetup.amey.com.meetup;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ameyruikar on 11/12/16.
 */
public class eventObject {


    String eventName;
    String dateTime;
    String people;
    String location;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    String endTime;

    public void setHistoryid(int historyid) {
        this.historyid = historyid;
    }

    int historyid;

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    LatLng position;
    String createdby;

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    String X;
    String Y;


    public int getHistoryid() {
        return historyid;
    }



    public eventObject(String eventName, String dateTime, String e,String people, String location, LatLng pt, String c, String x, String y, int h) {
        this.eventName = eventName;
        this.dateTime = dateTime;
        this.people = people;
        this.location = location;
        this.position = pt;
        this.createdby = c;
        this.endTime = e;
        this.X = x;
        this.Y = y;
        this.historyid = h;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
