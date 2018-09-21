package Persistence;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * A simple Game class used to text the JAXB Xml parsing - Michael Henrys
 */
@XmlRootElement
public class JAXBTestGame {

    private List<JAXBTestRoom> rooms;

    public JAXBTestGame(){
    }

    public JAXBTestGame(List<JAXBTestRoom> rooms) {
        this.rooms = rooms;
    }

    @XmlElement
    public List<JAXBTestRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<JAXBTestRoom> rooms) {
        this.rooms = rooms;
    }
}
