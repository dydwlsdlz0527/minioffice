package com.minioffice.vo;

import java.util.Date;

public class Comment {

	private int comment_no;
	private int board_no;
	private int parent_comment_no;
	private String comment_content;
	private Date comment_date;
	
	public Comment(int comment_no, int board_no, int parent_comment_no, String comment_content, Date comment_date) {
		super();
		this.comment_no = comment_no;
		this.board_no = board_no;
		this.parent_comment_no = parent_comment_no;
		this.comment_content = comment_content;
		this.comment_date = comment_date;
	}
	
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getParent_comment_no() {
		return parent_comment_no;
	}
	public void setParent_comment_no(int parent_comment_no) {
		this.parent_comment_no = parent_comment_no;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	
}
