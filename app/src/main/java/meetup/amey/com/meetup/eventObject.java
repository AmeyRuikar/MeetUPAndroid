package meetup.amey.com.meetup;

/**
 * Created by ameyruikar on 11/12/16.
 */
public class eventObject {


    String eventName;
    String dateTime;
    String people;
    String location;



    public eventObject(String eventName, String dateTime, String people, String location) {
        this.eventName = eventName;
        this.dateTime = dateTime;
        this.people = people;
        this.location = location;
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
