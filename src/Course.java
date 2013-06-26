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
import java.awt.event.FocusListener;
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
public class Course extends JFrame implements ActionListener {

	Toolkit tk = getToolkit();
	Dimension screen = tk.getScreenSize();
	JLabel sign = new JLabel("Mahmoud Abdrabo v1");
	Font f = new Font("Arial", Font.ITALIC, 12);

	JTextField course = new JTextField("Course name");
	JLabel mark = new JLabel("Mark");
	JLabel fullMark = new JLabel("Full mark");
	JLabel percent = new JLabel("Percentage");
	JLabel fullpercent = new JLabel("Full percentage");
	JLabel total = new JLabel("Total");
	JLabel tprcnt = new JLabel();
	JLabel tmrk = new JLabel();

	ArrayList<JTextField> marks;
	ArrayList<JTextField> fullmarks;
	ArrayList<JTextField> fullpercents;
	ArrayList<JLabel> percents;

	JButton add = new JButton("add");
	JButton calculate = new JButton("calculate");
	JButton export = new JButton("export");
	JButton New = new JButton("new");
	JButton reset = new JButton("reset");

	JPanel titles = new JPanel();
	JPanel header = new JPanel();
	JPanel controls;
	JPanel area = new JPanel();
	JPanel p = new JPanel();
	JPanel totalpanel;
	JScrollPane scroll;

	int count = 1;
	static int c = 0;

	public Course() {
		super("Course work Calculator");
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {
				if (c > 0) {
					c--;
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} else
					setDefaultCloseOperation(EXIT_ON_CLOSE);

			}
		});
		setLayout(new BorderLayout());
		setSize(600, 200);
		setLocation((int) screen.getWidth() / 2 - this.getWidth() / 2, (int) screen.getHeight() / 2 - this.getHeight() / 2);
		setVisible(true);

		marks = new ArrayList<JTextField>();
		fullmarks = new ArrayList<JTextField>();
		fullpercents = new ArrayList<JTextField>();
		percents = new ArrayList<JLabel>();

		header.setLayout(new BoxLayout(header, BoxLayout.PAGE_AXIS));
		titles.setLayout(new BoxLayout(titles, BoxLayout.LINE_AXIS));
		area.setLayout(new BoxLayout(area, BoxLayout.PAGE_AXIS));
		area.setBackground(Color.GRAY);
		scroll = new JScrollPane(area);
		scroll.setMaximumSize(new Dimension(0, 300));
		area.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

		course.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				if (course.getText().isEmpty())
					course.setText("Course name");
			}

			public void focusGained(FocusEvent e) {
				if (course.getText().equalsIgnoreCase("Course name"))
					course.setText(null);
			}
		});
		header.add(course);

		titles.add(Box.createHorizontalGlue());
		titles.add(mark);
		titles.add(Box.createHorizontalGlue());

		titles.add(fullMark);
		titles.add(Box.createHorizontalGlue());

		titles.add(fullpercent);
		titles.add(Box.createHorizontalGlue());

		titles.add(percent);
		titles.add(Box.createHorizontalGlue());
		

		header.add(titles);
		add(header, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);

		sign.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// OpenBrowser.openURL("http://goo.gl/2zKc5");
				OpenBrowser.openURL("gplus.to/mahmoudabdrabo");
			}

			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				sign.setFont(f.deriveFont(Font.BOLD));
			}

			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				sign.setFont(f.deriveFont(Font.ITALIC));
			}
		});
		sign.setFont(f);
		add(sign, BorderLayout.SOUTH);
		enterValue();
		validate();
		repaint();
	}

	private void enterValue() {

		p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
		p.setMaximumSize(new Dimension((int) screen.getWidth(), 50));

		JLabel counter = new JLabel(count + ".");

		final JTextField mrk = new JTextField("0");
		mrk.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				try {
					if ((!mrk.getText().isEmpty()) && Double.parseDouble(mrk.getText()) > 0) {
					} else
						mrk.setText("");
				} catch (Exception e2) {
				}
			}

			public void focusLost(FocusEvent e) {
				try {
					if ((!mrk.getText().isEmpty()) && Double.parseDouble(mrk.getText()) > 0) {
					} else
						mrk.setText("0");
					// grd.setText(evaluateGrade(mark.getText()));
				} catch (Exception e2) {
				}
			}
		});
		marks.add(mrk);

		final JTextField fmrk = new JTextField("0");
		fmrk.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				try {
					if ((!fmrk.getText().isEmpty()) && Double.parseDouble(fmrk.getText()) > 0) {
					} else
						fmrk.setText("");
				} catch (Exception e2) {
				}
			}

			public void focusLost(FocusEvent e) {
				try {
					if ((!fmrk.getText().isEmpty()) && Double.parseDouble(fmrk.getText()) > 0) {
					} else
						fmrk.setText("0");
					// grd.setText(evaluateGrade(mark.getText()));
				} catch (Exception e2) {
				}
			}
		});
		fullmarks.add(fmrk);

		final JTextField fprcnt = new JTextField("0");
		// fprcnt.setMaximumSize(new Dimension(400 , getHeight()));
		fprcnt.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				try {
					if ((!fprcnt.getText().isEmpty()) && Double.parseDouble(fprcnt.getText()) > 0) {
					} else
						fprcnt.setText("");
				} catch (Exception e2) {
				}
			}

			public void focusLost(FocusEvent e) {
				try {
					if ((!fprcnt.getText().isEmpty()) && Double.parseDouble(fprcnt.getText()) > 0) {
					} else
						fprcnt.setText("0");
					// grd.setText(evaluateGrade(mark.getText()));
				} catch (Exception e2) {
				}
			}
		});
		fullpercents.add(fprcnt);

		final JLabel prcnt = new JLabel("             ");
		prcnt.setOpaque(true);

		if (count % 2 == 0) {
			p.setBackground(Color.LIGHT_GRAY);
			prcnt.setBackground(Color.LIGHT_GRAY);
			prcnt.setForeground(Color.DARK_GRAY);
			counter.setForeground(Color.DARK_GRAY);
		} else {
			p.setBackground(Color.DARK_GRAY);
			prcnt.setBackground(Color.DARK_GRAY);
			prcnt.setForeground(Color.LIGHT_GRAY);
			counter.setForeground(Color.LIGHT_GRAY);
		}

		percents.add(prcnt);

		controls = new JPanel();
		controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));

		totalpanel = new JPanel();
		totalpanel.setLayout(new BoxLayout(totalpanel, BoxLayout.LINE_AXIS));

		add = new JButton("add");
		add.addActionListener(this);

		calculate = new JButton("calculate");
		calculate.addActionListener(this);

		reset.addActionListener(this);

		New.addActionListener(this);

		export.addActionListener(this);

		if (!(getHeight() >= screen.getHeight() - 300)) {
			setSize(getWidth(), getHeight() + 20);
			setLocation((int) screen.getWidth() / 2 - this.getWidth() / 2, getY() - 20);
		}

		p.add(counter);
		p.add(mrk);
		// p.add(Box.createHorizontalGlue());
		p.add(fmrk);
		// p.add(Box.createHorizontalGlue());
		p.add(fprcnt);
		p.add(Box.createHorizontalGlue());
		p.add(prcnt);
		controls.add(add);
		controls.add(calculate);
		totalpanel.add(total);
		totalpanel.add(Box.createHorizontalGlue());
		totalpanel.add(Box.createRigidArea(new Dimension(5, 0)));
		totalpanel.add(Box.createHorizontalGlue());
		totalpanel.add(tmrk);
		totalpanel.add(Box.createHorizontalGlue());
		totalpanel.add(tprcnt);
		controls.add(New);
		controls.add(reset);
		area.add(p);
		area.add(totalpanel);
		area.add(controls);

		marks.get(marks.size() - 1).requestFocusInWindow();
		validate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent s) {
		if (s.getSource() == add) {
			count++;
			repaint();

			// calculate.removeActionListener(this);
			add.removeActionListener(this);
			export.removeActionListener(this);
			reset.removeActionListener(this);
			New.removeActionListener(this);

			controls.remove(calculate);
			controls.remove(add);
			controls.remove(export);
			enterValue();
		}

		if (s.getSource() == calculate) {

			controls.remove(calculate);
			controls.remove(add);
			controls.remove(export);

			double tp = 0;
			double tm = 0;
			for (int i = 0; i < percents.size(); i++) {
				if (Double.parseDouble(fullmarks.get(i).getText()) == 0 || Double.parseDouble(fullpercents.get(i).getText()) == 0)
					continue;
				double x = Double.parseDouble(marks.get(i).getText());
				double y = Double.parseDouble(fullmarks.get(i).getText());
				double z = Double.parseDouble(fullpercents.get(i).getText());
				percents.get(i).setText("" + (x * z / y));
				tp += Double.parseDouble(percents.get(i).getText());
				tm += Double.parseDouble(fullpercents.get(i).getText());
			}
			tprcnt.setText("" + tp);
			tmrk.setText("" + tm);

			controls.add(add);
			controls.add(calculate);
			controls.add(New);
			controls.add(reset);
			controls.add(export);
			repaint();
		}

		if (s.getSource() == export) {
			allSetVisible(false);
			boolean flag = false;
			JLabel tmp = new JLabel(course.getText());

			if (JOptionPane.showConfirmDialog(this, "take screen-shot?", null, JOptionPane.YES_NO_OPTION) == 0) {

				JPanel shot = new JPanel();
				shot.setSize(area.getWidth(), area.getHeight() + 60);
				JPanel titles = new JPanel();
				titles.setLayout(new BoxLayout(titles, BoxLayout.LINE_AXIS));
				titles.add(Box.createHorizontalGlue());
				titles.add(new JLabel("Mark"));
				titles.add(Box.createHorizontalGlue());
				titles.add(new JLabel("Full mark"));
				titles.add(Box.createHorizontalGlue());
				titles.add(new JLabel("Full percentage"));
				titles.add(Box.createHorizontalGlue());
				titles.add(new JLabel("Percentage"));
				shot.setLayout(new BorderLayout());
				shot.add(titles, BorderLayout.NORTH);
				JPanel x = new JPanel();
				x = area;
				shot.add(x, BorderLayout.CENTER);
				shot.add(new JLabel("Course work calculator by Mahmoud abdrabo, Aug 2011"), BorderLayout.SOUTH);

				JFrame f = new JFrame();
				f.setSize(shot.getSize());
				f.add(shot);
				f.setVisible(true);
				flag = windowShot.getScreenShot(f);
				f.dispose();
			}
			if (flag)
				JOptionPane.showMessageDialog(this, "exported to desktop");
			remove(tmp);
			// course.setVisible(true);
			allSetVisible(true);

			remove(scroll);
			remove(area);
			validate();
			scroll = new JScrollPane(area);
			add(scroll, BorderLayout.CENTER);
			validate();
			repaint();

		}

		if (s.getSource() == reset) {
			this.dispose();
			new Course();
		}

		if (s.getSource() == New) {
			c++;
			new Course();
		}
	}

	private void allSetVisible(boolean b) {
		// New.setVisible(b);
		// reset.setVisible(b);
		add.setVisible(b);
		calculate.setVisible(b);
		export.setVisible(b);
		validate();
		repaint();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		new Course();
	}
}
