import java.net.URL;

public class URLGenerator {

	//A method to get the URLs of images in the resources folder
	public URL getURL(String src) {
		return this.getClass().getResource(src);
	}

}
