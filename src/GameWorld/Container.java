package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class Container extends WorldObject {

	private List<WorldObject> contents;



}
