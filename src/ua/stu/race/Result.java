package ua.stu.race;

import java.io.Serializable;

public class Result implements Serializable, Comparable {
	private String name;
	private int score;
	
	public Result(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "User: " + name + "\tScore: " + score;
	}

	@Override
	public int compareTo(Object another) {
		return this.score - ((Result)another).score;
	}
	
}	
