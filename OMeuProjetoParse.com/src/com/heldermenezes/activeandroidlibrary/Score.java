package com.heldermenezes.activeandroidlibrary;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Score")
public class Score extends Model {

	@Column(name = "scorePhysics")
	private int scorePhysics;
	@Column(name = "scoreChemistry")
	private int scoreChemistry;
	@Column(name = "scoreMaths")
	private int scoreMaths;
	@Column(name = "scoreBiology")
	private int scoreBiology;

	public Score() {
		super();
	}

	public Score(int scorePhysics, int scoreChemistry, int scoreMaths,
			int scoreBiology) {
		this.setScorePhysics(scorePhysics);
		this.setScoreChemistry(scoreChemistry);
		this.setScoreMaths(scoreMaths);
		this.setScoreBiology(scoreBiology);
	}

	public int getScoreBiology() {
		return scoreBiology;
	}

	public void setScoreBiology(int scoreBiology) {
		this.scoreBiology = scoreBiology;
	}

	public int getScoreMaths() {
		return scoreMaths;
	}

	public void setScoreMaths(int scoreMaths) {
		this.scoreMaths = scoreMaths;
	}

	public int getScoreChemistry() {
		return scoreChemistry;
	}

	public void setScoreChemistry(int scoreChemistry) {
		this.scoreChemistry = scoreChemistry;
	}

	public int getScorePhysics() {
		return scorePhysics;
	}

	public void setScorePhysics(int scorePhysics) {
		this.scorePhysics = scorePhysics;
	}
	
	@Override
    public String toString() {
        return "P: "
                + scorePhysics
                + " C: "
                + scoreChemistry
                + " M: "
                + scoreMaths
                + " B: "
                + scoreBiology;
    }
}
