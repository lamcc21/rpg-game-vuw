package Persistence;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * A simple Room class used to text the JAXB Xml parsing - Michael Henrys
 */
@XmlRootElement
public class JAXBTestRoom {
    private String name;
    private int x;
    private int y;
    private List<JAXBTestItem> items;

    public JAXBTestRoom(){}

    public JAXBTestRoom(String name, int x, int y, List<JAXBTestItem> items) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.items = items;
    }

    @XmlElement
    public List<JAXBTestItem> getItems() {
        return items;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public int getX() {
        return x;
    }

    @XmlElement
    public int getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setItems(List<JAXBTestItem> items){
        this.items = items;
    }

}
