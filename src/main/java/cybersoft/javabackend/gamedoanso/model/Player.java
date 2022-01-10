package cybersoft.javabackend.gamedoanso.model;

public class Player {
	private String name;
	private int  timesGuess;
	
	public Player() {
	}


	public Player(String name, int timesGuess) {
		this.name = name;
		this.timesGuess = timesGuess;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getTimesGuess() {
		return timesGuess;
	}
	public void setTimesGuess(int timesGuess) {
		this.timesGuess = timesGuess;
	}
}
