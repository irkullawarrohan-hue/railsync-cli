package CNFM.TICKT.entities;

import java.util.Date;

public class Ticket {

    private String ticketId;
    private String userID;
    private String source;
    private String destination;
    private Date dateOfTravel;
    private Train train;

    public String getTicketId() {
        return this.ticketId = ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserID() {
        return this.userID = userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSource() {
        return this.source = source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getDestination(){
        return this.destination = destination;
    }
    public void setDestination(String destination){
        this.destination = destination;
    }
    public Date getDateOfTravel(){
        return this.dateOfTravel = dateOfTravel;
    }
    public void setDateOfTravel(Date dateOfTravel){
        this.dateOfTravel = dateOfTravel;
    }
    public Train getTrain(){
        return this.train = train;
    }
    public void setTrain(Train train){
        this.train = train;
    }
    public String getTicketInfo(){
        return String.format("Ticket id %s belongs to user %s from source %s to %s on %s" , ticketId, userID, source, destination, dateOfTravel);
    }
}
