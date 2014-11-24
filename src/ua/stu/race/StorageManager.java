package ua.stu.race;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import android.content.Context;

public class StorageManager<T> {

	private Context context;
	private static final String FILE_NAME = "file_list";
	
	public StorageManager(Context context) {
		this.context = context;
	}
	
	public void saveList(List<T> list) {
		FileOutputStream fos;
		try {
			fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(list);
			os.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList() {
		FileInputStream fis;
        ObjectInputStream is;
		try {
			fis = context.openFileInput(FILE_NAME);
			is = new ObjectInputStream(fis);
		    List<T> list = (List<T>) is.readObject();
	        is.close();
	        return list;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
