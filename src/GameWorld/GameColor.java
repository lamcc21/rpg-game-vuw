package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class GameColor {
  public static final GameColor cyan = new GameColor(67,125,128);
  public static final GameColor purple = new GameColor(75, 66, 121);
  public static final GameColor green = new GameColor(63,99, 37);
  public static final GameColor gold = new GameColor(170, 170, 36);
  public static final GameColor brown = new GameColor(114,50,28);
  public static final GameColor silver = new GameColor(121,121,121);

	private int R;
	private int G;
	private int B;

	public GameColor() {}

	public GameColor(int r, int g, int b) {
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
		GameColor gameColor = (GameColor) o;
		return R == gameColor.R &&
				G == gameColor.G &&
				B == gameColor.B;
	}

	@Override
	public int hashCode() {
		return Objects.hash(R, G, B);
	}
}
