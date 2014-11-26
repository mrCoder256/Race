package ua.stu.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;

public class ResultManager {
	
	private Context context;
	private static StorageManager<Result> storage;

	public ResultManager(Context context) {
		this.context = context;
		storage = new StorageManager<Result>(context);
	}
    
    public List<Result> getTopResults() {
        List<Result> lst = (List<Result>)storage.getList();
        if (lst == null)
        	return null;
        Collections.sort(lst, new ResultComparator());
        Collections.reverse(lst);
        return lst;
    }
    
    public List<Result> addResult(Result newResult) {
        List<Result> lst = getTopResults();
        if (lst == null)
        	lst = new ArrayList<Result>();
        lst.add(newResult);
        Collections.sort(lst, new ResultComparator());
        Collections.reverse(lst);
        if (lst.size() > 5) 
        	lst.remove(lst.size() - 1);
        
        this.storage.saveList(lst);
        return lst;
    }
}
