import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class checkInternet extends Thread {

	boolean value = true;

	public checkInternet() {
		this.start();
	}

	public synchronized void run() {
		check2();
		System.out.println(value +" 1\n2 "+ value);
	}

	public void check2() {

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
			value = false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = false;

		}
		value = true;

	}
}
