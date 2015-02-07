package reflection;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadClass {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Constructor getConstructor(File file, String name) {
		Constructor c = null;
		Class k;
		URL url = null;
		try {
			url = file.toURI().toURL();
		} catch (MalformedURLException ex) {

		}

		URL[] Url = { url };
		URLClassLoader classLoader = URLClassLoader
				.newInstance(new URL[] { url });

		try {
			k = Class.forName(name, true, classLoader);
			c = k.getConstructor(int.class, int.class, int.class, int.class,
					int.class, int.class);
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException ex) {

		}
		return c;
	}
}
