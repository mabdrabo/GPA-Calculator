import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

@SuppressWarnings("serial")
class area extends JPanel implements ActionListener {
	Toolkit tk = getToolkit();
	Dimension screen = tk.getScreenSize();
	JLabel name = new JLabel("Mahmoud Abdrabo");
	JLabel updates = new JLabel("check for updates");
	JPanel sign = new JPanel();
	Font f = new Font("Arial", Font.ITALIC, 12);

	ArrayList<JTextField> marks;
	ArrayList<JTextField> creditHours;
	int count = 0;

	static int x = 0;

	public GPA frame;

	JLabel total = new JLabel("Total");
	JLabel tValue = new JLabel("0");
	JLabel gpa = new JLabel();
	JButton add = new JButton("add");
	JButton calculate = new JButton("calculate GPA");
	JButton reset = new JButton("reset");
	JButton New = new JButton("new window");
	JButton export = new JButton("export");
	JPanel scrollarea = new JPanel();
	JPanel titles = new JPanel();
	JPanel p;
	JPanel controls;
	JPanel totalpanel;
	JScrollPane scroll;

	public area(GPA frame) {

		this.frame = frame;
		setLayout(new BorderLayout());
		setVisible(true);
		marks = new ArrayList<JTextField>();
		creditHours = new ArrayList<JTextField>();
		scrollarea.setLayout(new BoxLayout(scrollarea, BoxLayout.PAGE_AXIS));
		scrollarea.setBackground(Color.GRAY);
		scroll = new JScrollPane(scrollarea);
		scroll.setMaximumSize(new Dimension(0, 300));
		scrollarea.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

		// if (x == 0) {
		// // OpenBrowser.openURL("http://goo.gl/LR1t8");
		// Thread t = new
		// OpenBrowser("https://sites.google.com/site/mabdrabo93/documents");
		// t.start();
		// }

		titles.setLayout(new BoxLayout(titles, BoxLayout.X_AXIS));
		titles.add(Box.createRigidArea(new Dimension(60, 0)));
		titles.setBackground(Color.GRAY);

		JLabel mark = new JLabel("Numeric");
		mark.setForeground(Color.WHITE);
		titles.add(mark);
		titles.add(Box.createHorizontalGlue());

		JLabel grade = new JLabel("Grade");
		grade.setForeground(Color.WHITE);
		titles.add(grade);
		titles.add(Box.createHorizontalGlue());

		JLabel crdtHrz = new JLabel("Hours");
		crdtHrz.setForeground(Color.WHITE);
		titles.add(crdtHrz);
		titles.add(Box.createRigidArea(new Dimension(60, 0)));

		name.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// OpenBrowser.openURL("http://goo.gl/2zKc5");
				OpenBrowser.openURL("gplus.to/mahmoudabdrabo");
			}

			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				name.setFont(f.deriveFont(Font.BOLD));
			}

			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				name.setFont(f.deriveFont(Font.ITALIC));
			}
		});

		updates.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// OpenBrowser.openURL("http://goo.gl/2zKc5");
				OpenBrowser.openURL("https://sites.google.com/site/mabdrabo93/documents");
			}

			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				updates.setFont(f.deriveFont(Font.BOLD));
			}

			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				updates.setFont(f.deriveFont(Font.ITALIC));
			}
		});

		sign.setLayout(new BoxLayout(sign, BoxLayout.LINE_AXIS));
		name.setFont(f);
		updates.setFont(f);
		sign.add(name);
		sign.add(Box.createHorizontalGlue());
		sign.add(updates);
		sign.setBackground(Color.GRAY);

		add(titles, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(sign, BorderLayout.SOUTH);
		enterValue();
		validate();
	}

	public void enterValue() {
		p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));

		count = creditHours.size() + 1;
		JLabel counter = new JLabel(count + ".");

		final JLabel grd = new JLabel();

		final JTextField mark = new JTextField("0");
		mark.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				try {
					if ((!mark.getText().isEmpty()) && Double.parseDouble(mark.getText()) > 0) {
					} else
						mark.setText("");
				} catch (Exception e2) {
				}
			}

			public void focusLost(FocusEvent e) {
				try {
					if ((!mark.getText().isEmpty()) && Double.parseDouble(mark.getText()) > 0) {
					} else
						mark.setText("0");
					grd.setText(evaluateGrade(mark.getText()));
				} catch (Exception e2) {
				}
			}
		});
		marks.add(mark);

		final JTextField crtHr = new JTextField("0");
		crtHr.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if ((!crtHr.getText().isEmpty()) && Double.parseDouble(crtHr.getText()) > 0) {
				} else
					crtHr.setText("");
			}

			public void focusLost(FocusEvent e) {
				if ((!crtHr.getText().isEmpty()) && Double.parseDouble(crtHr.getText()) > 0) {
				} else
					crtHr.setText("0");
			}
		});
		creditHours.add(crtHr);

		add.addActionListener(this);
		calculate.addActionListener(this);
		reset.addActionListener(this);
		New.addActionListener(this);

		if (!(frame.getHeight() >= screen.getHeight() - 300)) {
			frame.setSize(frame.getWidth(), frame.getHeight() + 20);
			this.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
			frame.setLocation(frame.getX(), frame.getY() - 20);
		}

		if (count % 2 == 0) {
			p.setBackground(Color.LIGHT_GRAY);
			grd.setForeground(Color.DARK_GRAY);
			counter.setForeground(Color.DARK_GRAY);
		} else {
			p.setBackground(Color.DARK_GRAY);
			grd.setForeground(Color.LIGHT_GRAY);
			counter.setForeground(Color.LIGHT_GRAY);
		}

		controls = new JPanel();
		controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));

		totalpanel = new JPanel();
		totalpanel.setLayout(new BoxLayout(totalpanel, BoxLayout.LINE_AXIS));

		p.add(counter);
		p.add(Box.createRigidArea(new Dimension(15, 0)));
		p.add(mark);
		p.add(Box.createHorizontalGlue());
		p.add(Box.createRigidArea(new Dimension(15, 0)));
		p.add(grd);
		p.add(Box.createRigidArea(new Dimension(15, 0)));
		p.add(Box.createHorizontalGlue());
		p.add(crtHr);
		p.add(Box.createHorizontalGlue());
		p.setAlignmentX(CENTER_ALIGNMENT);

		totalpanel.add(total);
		totalpanel.add(Box.createHorizontalGlue());
		totalpanel.add(tValue);
		totalpanel.add(Box.createRigidArea(new Dimension(60, 0)));
		// controls.add(Box.createHorizontalGlue());
		controls.add(add);
		controls.add(calculate);
		controls.add(reset);
		controls.add(New);
		scrollarea.add(p);
		scrollarea.add(totalpanel);
		scrollarea.add(controls);

		marks.get(marks.size() - 1).requestFocusInWindow();
		validate();
		repaint();
	}

	public String evaluateGrade(String s) {
		double v = Double.parseDouble(s);
		String grade = " ";

		if (v == 0.7)
			grade = "A+";
		else {
			if (v == 1)
				grade = "A";
			else {
				if (v == 1.3)
					grade = "A-";
				else {
					if (v == 1.7)
						grade = "B+";
					else {
						if (v == 2)
							grade = "B";
						else {
							if (v == 2.3)
								grade = "B-";
							else {
								if (v == 2.7)
									grade = "C+";
								else {
									if (v == 3)
										grade = "C";
									else {
										if (v == 3.3)
											grade = "C-";
										else {
											if (v == 3.7)
												grade = "D+";
											else {
												if (v == 4)
													grade = "D";
												else {
													if (v == 5)
														grade = "F";
													else
														grade = "X";
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return grade;
	}

	public void actionPerformed(ActionEvent s) {
		if (s.getSource() == add) {
			int x = 0;
			for (int i = 0; i < creditHours.size(); i++)
				x += Double.parseDouble(creditHours.get(i).getText());
			tValue.setText(x + "");
			repaint();

			add.removeActionListener(this);
			calculate.removeActionListener(this);
			reset.removeActionListener(this);
			New.removeActionListener(this);

			controls.remove(export);
			controls.remove(New);
			controls.remove(reset);
			controls.remove(gpa);
			controls.remove(add);
			controls.remove(calculate);
			enterValue();
		}

		if (s.getSource() == calculate) {
			int x = 0;
			reset.removeActionListener(this);
			New.removeActionListener(this);
			export.removeActionListener(this);

			try {
				for (int i = 0; i < creditHours.size(); i++)
					x += Double.parseDouble(creditHours.get(i).getText());
				tValue.setText(x + "");
				repaint();
				double sum = 0;
				double r = 0;
				for (int i = 0; i < marks.size(); i++) {
					sum += Double.parseDouble(marks.get(i).getText()) * Double.parseDouble(creditHours.get(i).getText());
				}
				r = sum / Double.parseDouble(tValue.getText());
				gpa.setText("GPA: " + r + "");
			} catch (Exception e) {
			}
			reset.addActionListener(this);
			New.addActionListener(this);
			export.addActionListener(this);

			controls = new JPanel();
			controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));

			controls.add(gpa);
			controls.add(Box.createHorizontalGlue());
			controls.add(add);
			controls.add(calculate);
			controls.add(export);
			controls.add(reset);
			controls.add(New);
			scrollarea.add(controls);
			validate();
			repaint();
		}

		if (s.getSource() == reset) {
			frame.dispose();
			new GPA();
		}

		if (s.getSource() == New) {
			new GPA();
			GPA.c++;
		}

		if (s.getSource() == export) {
			boolean flag = false;
			allSetVisible(false);

			if (JOptionPane.showConfirmDialog(this, "take screen-shot?", null, JOptionPane.YES_NO_OPTION) == 0) {
				JPanel shot = new JPanel();
				shot.setSize(scrollarea.getWidth(), scrollarea.getHeight() + 60);
				JPanel titles = new JPanel();
				titles.setLayout(new BoxLayout(titles, BoxLayout.LINE_AXIS));
				titles.add(new JLabel("Numeric"));
				titles.add(Box.createHorizontalGlue());
				titles.add(new JLabel("Grade"));
				titles.add(Box.createHorizontalGlue());
				titles.add(new JLabel("Hours"));
				shot.setLayout(new BorderLayout());
				shot.add(titles, BorderLayout.NORTH);
				JPanel x = new JPanel();
				x = scrollarea;
				shot.add(x, BorderLayout.CENTER);
				shot.add(new JLabel("GPA calculator by Mahmoud abdrabo, Aug 2011"), BorderLayout.SOUTH);

				JFrame f = new JFrame();
				f.setSize(shot.getSize());
				f.add(shot);
				f.setVisible(true);
				flag = windowShot.getScreenShot(f);
				f.dispose();

			}
			if (flag)
				JOptionPane.showMessageDialog(this, "exported to desktop");
			allSetVisible(true);
			remove(scroll);
			remove(scrollarea);
			validate();
			scroll = new JScrollPane(scrollarea);
			add(scroll, BorderLayout.CENTER);
			validate();
			repaint();
		}
	}

	private void allSetVisible(boolean b) {
		New.setVisible(b);
		reset.setVisible(b);
		add.setVisible(b);
		calculate.setVisible(b);
		export.setVisible(b);
		validate();
		repaint();
	}
}

@SuppressWarnings("serial")
public class GPA extends JFrame {
	Toolkit tk = getToolkit();
	Dimension screen = tk.getScreenSize();
	static int c = 0;

	public GPA() {
		super("GPA Calculator v1.2");
		setSize(600, 160);
		setLocation((int) screen.getWidth() / 2 - this.getWidth() / 2, (int) screen.getHeight() / 2 - this.getHeight() / 2);
		setVisible(true);
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {
				if (c > 0) {
					c--;
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} else
					setDefaultCloseOperation(EXIT_ON_CLOSE);

			}
		});
		area a = new area(this);
		add(a);
		pack();
		validate();
		repaint();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		new GPA();
	}
}
