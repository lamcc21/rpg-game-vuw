package Persistence;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A simple Item class used to text the JAXB Xml parsing - Michael Henrys
 */
@XmlRootElement
public class JAXBTestItem {

    private int x;
    private int z;
    private int y;
    String name;

    public JAXBTestItem(){}

    public JAXBTestItem(int x, int z, int y, String name) {
        this.x = x;
        this.z = z;
        this.y = y;
        this.name = name;
    }

    @XmlElement
    public int getX() {
        return x;
    }

    @XmlElement
    public int getZ() {
        return z;
    }

    @XmlElement
    public int getY() {
        return y;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }
}
