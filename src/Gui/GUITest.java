package Gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Logica.Juego;
import Logica.Celda;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUITest extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Juego juego;
	private JPanel Tablero;
	private JPanel Cronometro;
	private JPanel panel;
	private JLabel[][] matriz;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITest frame = new GUITest();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUITest() throws FileNotFoundException {
		setResizable(false);
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 670);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Tablero = new JPanel();
		Tablero.setBackground(Color.BLACK);
		Tablero.setBounds(10, 11, 721, 510);
		contentPane.add(Tablero);

		Cronometro = new Logica.Cronometro();
		Cronometro.setBounds(561, 533, 170, 90);
		contentPane.add(Cronometro);

		juego = new Juego();
		Tablero.setLayout(new GridLayout(juego.getCantFilas(), 0, 0, 0));

		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(10, 532, 541, 91);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Al finalizar haga click en Comprobar!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(150, 28, 381, 35);
		panel.add(lblNewLabel);
		if (juego.getEmpezar() == 0) {
			JOptionPane.showMessageDialog(null, "La Solucion Sudoku cargada es Correcta. Puede Comenzar!", "AVISO",
					JOptionPane.INFORMATION_MESSAGE);
			matriz = new JLabel[9][9];
			for (int i = 0; i < juego.getCantFilas(); i++) {
				for (int j = 0; j < juego.getCantFilas(); j++) {

					Celda c = juego.getCelda(i, j);
					ImageIcon grafico = c.getGrafica().getGrafico();
					matriz[i][j] = new JLabel();
					if (j % 3 == 0 && i % 3 == 0)
						matriz[i][j].setBorder(BorderFactory.createMatteBorder(8, 8, 1, 1, Color.YELLOW));
					else if (i % 3 == 0)
						matriz[i][j].setBorder(BorderFactory.createMatteBorder(8, 1, 1, 1, Color.YELLOW));
					else if (j % 3 == 0)
						matriz[i][j].setBorder(BorderFactory.createMatteBorder(1, 8, 1, 1, Color.YELLOW));
					else
						matriz[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));

					Tablero.add(matriz[i][j]);
					matriz[i][j].addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							reDimensionar((JLabel) e.getComponent(), grafico);
							((JLabel) e.getComponent()).setIcon(grafico);
						}
					});

					matriz[i][j].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							juego.accionar(c);
							reDimensionar((JLabel) e.getComponent(), grafico);
						}
					});

				}
			}
		} else
			JOptionPane.showMessageDialog(null, "La Solucion Sudoku cargada es incorrecta.", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		JButton btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean estaCompleto = true;
				for (int i = 0; i < juego.getCantFilas() && estaCompleto; i++) {
					for (int j = 0; j < juego.getCantFilas() && estaCompleto; j++) {
						Celda c = juego.getCelda(i, j);
						Integer d = c.getValor();
						if (d == null)
							estaCompleto = false;
					}
				}
				if (estaCompleto) {
					lblNewLabel.setText(juego.comprobar());
					for (int i = 0; i < juego.getCantFilas(); i++) {
						for (int j = 0; j < juego.getCantFilas(); j++) {
							Celda c = juego.getCelda(i, j);
							ImageIcon grafico = c.getGrafica().getGrafico();
							reDimensionar(matriz[i][j], grafico);
							matriz[i][j].setIcon(grafico);
						}
					}
					if (lblNewLabel.getText() == "El Sudoku es correcto. Has Ganado!")
						((Logica.Cronometro) Cronometro).pararCronometro();
				} else
					lblNewLabel.setText("El Sudoku aun no esta completo.");
			}
		});
		btnComprobar.setBounds(38, 32, 102, 30);
		panel.add(btnComprobar);
	}

	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
}
