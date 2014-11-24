package ua.stu.race;

import java.util.Comparator;

public class ResultComparator implements Comparator<Result>{

	@Override
	public int compare(Result lhs, Result rhs) {
		return lhs.compareTo(rhs);
	}

}
