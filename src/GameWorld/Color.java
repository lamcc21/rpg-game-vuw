package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Color {

	int R;
	int G;
	int B;

	public Color() {}

	public Color(int r, int g, int b) {
		this.R=r;
		this.B=b;
		this.G=g;
	}

	@XmlElement
	public int getG() {
		return G;
	}

	@XmlElement
	public void setG(int g) {
		G = g;
	}

	@XmlElement
	public int getB() {
		return B;
	}

	public void setB(int b) {
		B = b;
	}

	@XmlElement
	public int getR() {
		return R;
	}

	public void setR(int r) {
		R = r;
	}


}
