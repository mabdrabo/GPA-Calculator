import java.awt.AWTException;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class windowShot {

	Rectangle captureSize;
	static FileSystemView filesys = FileSystemView.getFileSystemView();

	public windowShot(GPA f, int x, int y, int w, int h) {

		captureSize = new Rectangle(x, y, w, h);
		run();

	}

	public static boolean getScreenShot(Component component) {

		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
		component.paint(image.getGraphics());

		return createImageFile(image);
	}

	private static boolean createImageFile(BufferedImage image) {
		String name = JOptionPane.showInputDialog("please enter file name.");
		File f = new File(filesys.getHomeDirectory() + "/Desktop/" + name);
if (name == null)
	return false;
		if (!f.exists() && name != null)
			try {
				ImageIO.write(image, "jpg", f);
				return true;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else {
			while (f.exists() && name != null) {
				name = JOptionPane
						.showInputDialog("file of the same name exists in the same directory.\nplease enter file name.");
				f = new File(filesys.getHomeDirectory() + "/Desktop/" + name);
			}
			if (!f.exists() && name != null)
				try {
					ImageIO.write(image, "jpg", f);
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return false;

	}

	public void run() {
		try {
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(captureSize);
			createImageFile(image);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new windowShot(null, 0, 0, 1000, 1000);
	}
}
