 

import java.awt.Color;

public class Container {	
	
	private int code;
	private Color farbe;
	
	public Container(int code, Color farbe) {
		super();
		this.code = code;
		this.farbe = farbe;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Color getFarbe() {
		return farbe;
	}

	public void setFarbe(Color farbe) {
		this.farbe = farbe;
	}	
}
