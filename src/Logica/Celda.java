package Logica;

public class Celda {
	private Integer valor;
	private Grafica grafica;

	public Celda() {
		valor = null;
		grafica = new Grafica();
	}

	public int getCantElementos() {
		return grafica.getImagenes().length - 1;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer v) {
		if (v != null && v <= getCantElementos()) {
			valor = v;
			grafica.actualizar(valor);
		} else {
			valor = null;
		}
	}

	public void actualizar() {
		if (this.valor != null && valor > 0 && this.valor < this.getCantElementos()) {
			this.valor++;
		} else {
			this.valor = 1;
		}
		grafica.actualizar(this.valor);
	}

	public Grafica getGrafica() {
		return grafica;
	}

	public void setGrafica(Grafica g) {
		grafica = g;
	}
}
