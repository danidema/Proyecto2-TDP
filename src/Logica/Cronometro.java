package Logica;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cronometro extends JPanel implements Runnable, ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel tiempo;
	Thread hilo;
	boolean cronometroActivo;
	boolean pausar;
	boolean iniciado = true;

	public Cronometro() {
		setSize(175, 94);
		setBackground(Color.WHITE);
		setLayout(null);
		tiempo = new JLabel("00:00:000");
		tiempo.setBounds(0, 23, 175, 43);
		tiempo.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		tiempo.setForeground(Color.WHITE);
		tiempo.setBackground(Color.BLACK);
		tiempo.setOpaque(true);
		add(tiempo);
		// Boton iniciar
		JButton btn = new JButton("Iniciar");
		btn.setBounds(0, 0, 175, 23);
		btn.addActionListener(this);
		add(btn);
		// Boton reiniciar inicia nuevamente desde 0
		JButton btnP = new JButton("Reiniciar");
		btnP.setBounds(83, 65, 92, 23);
		btnP.addActionListener(this);
		add(btnP);
		// Boton detener detiene el cronometro
		JButton btnD = new JButton("Detener");
		btnD.setBounds(0, 65, 83, 23);
		btnD.addActionListener(this);
		add(btnD);
		setVisible(true);
	}

	public void run() {
		Integer minutos = 0, segundos = 0, milesimas = 0;
		String min = "", seg = "", mil = "";
		try {
			// Mientras cronometroActivo sea verdadero entonces seguira aumentando el tiempo
			while (cronometroActivo) {
				if (!pausar) {
					Thread.sleep(4);
					milesimas += 4;
					// Cuando llega a 1000 osea 1 segundo aumenta 1 segundo y las milesimas de
					// segundo de nuevo a 0
					if (milesimas == 1000) {
						milesimas = 0;
						segundos += 1;
						// Si los segundos llegan a 60 entonces aumenta 1 los minutos y los segundos
						// vuelven a 0
						if (segundos == 60) {
							segundos = 0;
							minutos++;
						}
					}
					if (minutos < 10)
						min = "0" + minutos;
					else
						min = minutos.toString();
					if (segundos < 10)
						seg = "0" + segundos;
					else
						seg = segundos.toString();
					if (milesimas < 10)
						mil = "00" + milesimas;
					else if (milesimas < 100)
						mil = "0" + milesimas;
					else
						mil = milesimas.toString();
					tiempo.setText(min + ":" + seg + ":" + mil);
				}
			}
			tiempo.setText(min + ":" + seg + ":" + mil);

		} catch (Exception e) {
			System.out.println("Error al correr metodo run");
		}
		tiempo.setText("00:00:000");
	}

	public void actionPerformed(ActionEvent evt) {
		Object o = evt.getSource();
		if (o instanceof JButton) {
			JButton btn = (JButton) o;
			if (btn.getText().equals("Iniciar")) {
				iniciarCronometro();
			}
			if (btn.getText().equals("Reiniciar")) {
				reiniciarCronometro();
			}
			if (btn.getText().equals("Detener")) {
				pararCronometro();
			}
		}
	}

	public void iniciarCronometro() {
		if (iniciado) {
			hilo = new Thread(this);
			cronometroActivo = true;
			pausar = false;
			hilo.start();
			iniciado = false;
		}
	}

	public void pararCronometro() {
		pausar = true;
		iniciado = true;
	}

	public void reiniciarCronometro() {
		cronometroActivo = false;
		iniciado = true;
		tiempo.setText("00:00:000");
	}

	public static void main(String[] args) {
		new Cronometro();
	}
}
