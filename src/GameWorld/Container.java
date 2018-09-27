package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Container extends WorldObject {

	private List<WorldObject> contents;

	public Container(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,
	String Name,String Description,List<WorldObject> contents ) {

		super(xPos,yPos,zPos,xWidth,yHeight,zDepth,Name,Description);
		this.contents=new ArrayList<WorldObject>(contents);
	}

	public Container() {}

	@XmlElement
	public List<WorldObject> getContents(){
		return contents;
	}

	public void SetContents(List<WorldObject> contents) {
		this.contents=contents;
	}

}
