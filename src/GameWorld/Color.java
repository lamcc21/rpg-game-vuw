package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Color color = (Color) o;
		return R == color.R &&
				G == color.G &&
				B == color.B;
	}

	@Override
	public int hashCode() {
		return Objects.hash(R, G, B);
	}
}
