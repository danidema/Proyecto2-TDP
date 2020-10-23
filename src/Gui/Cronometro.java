package Gui;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cronometro extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	protected Timer timer;
	private ImageIcon[] imagenes;
	private Integer minutos = 0;
	private Integer segundos = 0;
	private Integer horas = 0;
	private JLabel unidadSegundos;
	private JLabel decenasSegundos;
	private JLabel unidadMinutos;
	private JLabel decenasMinutos;
	private JLabel unidadHoras;
	private JLabel separador1;
	private JLabel separador2;

	public Cronometro() {
		setSize(175, 94);
		setBackground(new Color(0, 51, 51));
		setLayout(null);
		imagenes = setImagenes();
		// Boton iniciar
		JButton btn = new JButton("Iniciar");
		btn.setBounds(0, 0, 175, 23);
		btn.addActionListener(this);
		add(btn);
		// Boton reiniciar. inicia nuevamente desde 0
		JButton btnP = new JButton("Reiniciar");
		btnP.setBounds(83, 65, 92, 23);
		btnP.addActionListener(this);
		add(btnP);
		// Boton detener. detiene el cronometro
		JButton btnD = new JButton("Detener");
		btnD.setBounds(0, 65, 83, 23);
		btnD.addActionListener(this);
		add(btnD);

		setVisible(true);
		
		unidadSegundos = new JLabel();
		unidadSegundos.setBounds(143, 22, 27, 43);
		unidadSegundos.setIcon(new ImageIcon("img/r0.png"));
		add(unidadSegundos);

		decenasSegundos = new JLabel();
		decenasSegundos.setBounds(117, 22, 27, 43);
		decenasSegundos.setIcon(new ImageIcon("img/r0.png"));
		add(decenasSegundos);

		unidadMinutos = new JLabel();
		unidadMinutos.setBounds(80, 22, 27, 43);
		unidadMinutos.setIcon(new ImageIcon("img/r0.png"));
		add(unidadMinutos);

		decenasMinutos = new JLabel();
		decenasMinutos.setBounds(50, 22, 27, 43);
		decenasMinutos.setIcon(new ImageIcon("img/r0.png"));
		add(decenasMinutos);

		unidadHoras = new JLabel();
		unidadHoras.setBounds(13, 22, 27, 43);
		unidadHoras.setIcon(new ImageIcon("img/r0.png"));
		add(unidadHoras);

		separador1 = new JLabel(":");
		separador1.setForeground(Color.CYAN);
		separador1.setFont(new Font("Arial", Font.BOLD, 28));
		separador1.setBounds(108, 22, 12, 43);
		add(separador1);

		separador2 = new JLabel(":");
		separador2.setForeground(Color.CYAN);
		separador2.setFont(new Font("Arial", Font.BOLD, 28));
		separador2.setBounds(40, 22, 12, 43);
		add(separador2);
		segundos = minutos = horas = 0;
		timer = new Timer(1000, (ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (segundos < 59) {
					segundos++;
				} else if (minutos < 59) {
					minutos++;
					segundos = 0;

				} else {
					horas++;
					minutos = 0;
					segundos = 0;
				}
				unidadSegundos.setIcon(imagenes[segundos % 10]);
				reDimensionar(unidadSegundos, imagenes[segundos % 10]);
				decenasSegundos.setIcon(imagenes[segundos / 10]);
				reDimensionar(decenasSegundos, imagenes[segundos / 10]);
				unidadMinutos.setIcon(imagenes[minutos % 10]);
				reDimensionar(unidadMinutos, imagenes[minutos % 10]);
				decenasMinutos.setIcon(imagenes[minutos / 10]);
				reDimensionar(decenasMinutos, imagenes[minutos / 10]);
				unidadHoras.setIcon(imagenes[horas % 10]);
				reDimensionar(unidadHoras, imagenes[horas % 10]);
			}
		});
	}

	public void actionPerformed(ActionEvent evt) {
		Object o = evt.getSource();
		if (o instanceof JButton) {
			JButton btn = (JButton) o;
			if (btn.getText().equals("Iniciar")) {
				start();
			}
			if (btn.getText().equals("Reiniciar")) {
				restart();
			}
			if (btn.getText().equals("Detener")) {
				stop();
			}
		}
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void restart() {
		segundos = minutos = horas = 0;
		timer.restart();
	}

	public int getSegundos() {
		return segundos;
	}

	public int getMinutos() {
		return minutos;
	}

	public int getHoras() {
		return horas;
	}

	public ImageIcon[] setImagenes() {
		ImageIcon[] toReturn = new ImageIcon[10];
		for (int i = 0; i < 10; i++)
			toReturn[i] = new ImageIcon(getClass().getResource("/img/timer" + i + ".PNG"));
		return toReturn;
	}

	public ImageIcon[] getImagenes() {
		return imagenes;
	}

	protected void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}

	public static void main(String[] args) {
		new Cronometro();
	}
}
