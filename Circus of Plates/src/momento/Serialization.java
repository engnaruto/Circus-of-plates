package momento;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.SavedState;

public class Serialization {

	private static Serialization instance;

	private Serialization() {
	}

	public static Serialization getInstance() {
		if (instance == null) {
			instance = new Serialization();
		}
		return instance;
	}

	public void save(SavedState savedState) {
		try {
			FileOutputStream fos = new FileOutputStream("save", false);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			System.out.println("TIME: " + savedState.getTimer().getTime());
			out.writeObject(savedState);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SavedState load() {

		SavedState s = null;
		try {
			FileInputStream fis = new FileInputStream("save");
			ObjectInputStream ois = new ObjectInputStream(fis);
			s = (SavedState) ois.readObject();
			if (s == null) {
				System.out.println("NULLLLLLLLL");
			}
			System.out.println("TIME: " +  s.getTimer().getTime());
			// savedState = s;

			// Thread.sleep(1000);
			// savedState.setSavedState(s);
			ois.close();
		} catch (ClassNotFoundException | IOException e) {
			// e.printStackTrace();
		}
		return s;
		// return savedState;
	}

}
