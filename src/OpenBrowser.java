import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class OpenBrowser extends Thread {
	String URL;

	public OpenBrowser(String url) {
		URL = url;
	}

	public void run() {
		openURL(URL);
	}

	public static void openURL(String url) {
		if (!checkInternet()) {

		} else {
			String os = System.getProperty("os.name").toLowerCase();
			Runtime rt = Runtime.getRuntime();

			try {

				if (os.indexOf("win") >= 0) {

					// this doesn't support showing urls in the form of
					// "page.html#nameLink"
					rt.exec("cmd /c rundll32 url.dll,FileProtocolHandler " + url);

				} else if (os.indexOf("mac") >= 0) {

					rt.exec("open " + url);

				} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {

					// Do a best guess on unix until we get a platform
					// independent
					// way
					// Build a list of browsers to try, in this order.
					String[] browsers = { "google-chrome", "firefox", "epiphany", "mozilla", "konqueror", "netscape", "opera",
							"links", "lynx" };

					// Build a command string which looks like
					// "browser1 "url" || browser2 "url" ||..."
					StringBuffer cmd = new StringBuffer();
					for (int i = 0; i < browsers.length; i++)
						cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \"" + url + "\" ");

					rt.exec(new String[] { "sh", "-c", cmd.toString() });

				} else {
					return;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error in opening browser" + ":\n" + e.getLocalizedMessage());
			}
			return;
		}
	}

	public static boolean checkInternet() {

		try {
			// make a URL to a known source
			URL url = new URL("http://www.google.com");

			// open a connection to that source
			HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

			// trying to retrieve data from the source. If there
			// is no connection, this line will fail
			urlConnect.getContent();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
		return true;

	}

}