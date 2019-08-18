package com.minioffice.vo;

public class Rank {
	private char rank_no;
	private String rank_name;
	
	public Rank() {
		super();
	}

	public Rank(char rank_no, String rank_name) {
		super();
		this.rank_no = rank_no;
		this.rank_name = rank_name;
	}

	public char getRank_no() {
		return rank_no;
	}

	public void setRank_no(char rank_no) {
		this.rank_no = rank_no;
	}

	public String getRank_name() {
		return rank_name;
	}

	public void setRank_name(String rank_name) {
		this.rank_name = rank_name;
	}
	
}
