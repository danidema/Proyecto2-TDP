package Logica;

import javax.swing.ImageIcon;

public class Grafica {
	private ImageIcon grafico;
	private String[] imagenes;

	public Grafica() {
		grafico = new ImageIcon();
		imagenes = new String[] { "/img/uno.png", "/img/dos.png", "/img/tres.png", "/img/cuatro.png", "/img/cinco.png",
				"/img/seis.png", "/img/siete.png", "/img/ocho.png", "/img/nueve.png", "/img/cruz.png" };
	}

	public void actualizar(int indice) {
		if (indice >= 0 && indice <= 9) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice - 1]));
			this.grafico.setImage(imageIcon.getImage());
		}
		if (indice == -1) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[9]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}

	public ImageIcon getGrafico() {
		return grafico;
	}

	public void setGrafico(ImageIcon graf) {
		grafico = graf;
	}

	public String[] getImagenes() {
		return imagenes;
	}

	public void setImagenes(String[] imags) {
		imagenes = imags;
	}
}
