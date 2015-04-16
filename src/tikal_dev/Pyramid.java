package tikal_dev;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Pyramid extends JPanel {
	int currentValue;
	JLabel IMG;
	JLabel Value;

	public Pyramid(int V) {
		currentValue = V;
		IMG = new JLabel();
		IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/tikal_dev/Pyramid.png")));
		IMG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.setLayout(null);
		//this.setPreferredSize(new java.awt.Dimension(100, 100));
		this.setOpaque(false);
		this.setSize(IMG.getIcon().getIconHeight(), IMG.getIcon().getIconWidth());
		IMG.setBounds(0, 5, 45, 45);

		Value = new JLabel();
		Value.setForeground(Color.black);
		Value.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		Value.setText("0");
		Value.setFont(Value.getFont().deriveFont(18.0f));
		Value.setBounds(12, 20, 20, 20);
		this.setVisible(false);
		this.add(Value);
		this.add(IMG);
	}

	public void setValue(int i) {
		// Sets value of the current pyramid and updates the image accordingly
		switch (i) {
		case 0:
			
			Value.setText("0");
			//IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Pyramid.png")));
			System.out.println("value: "+i);
			repaint();
			break;
		case 1:
			//IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Pyramid_1.png")));
			Value.setText("1");
			System.out.println("value: "+i);
			repaint();
			break;
		case 2:
			//IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Pyramid_2.png")));
			Value.setText("2");
			System.out.println("value: "+i);
			repaint();
			break;
		case 3:
			//IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Pyramid_3.png")));
			Value.setText("3");
			System.out.println("value: "+i);
			repaint();
			break;
		case 4:
			//IMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tikal_dev/Pyramid_4.png")));
			System.out.println("value: "+i);
			Value.setText("4");
			repaint();
			break;
		default:
			JOptionPane.showMessageDialog(null,
					"Cant set a pyramid of this value");
			break;
		}
		currentValue = i;
	}

	public int getValue() {
		return currentValue;
	}
}