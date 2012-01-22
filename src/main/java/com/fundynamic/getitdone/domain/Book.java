package com.fundynamic.getitdone.domain;

public class Book {

	private String title;
	private String writer;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Book(String title, String writer) {
		super();
		this.title = title;
		this.writer = writer;
	}

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 * 
	 * @return a <code>String</code> representation of this object.
	 */
	public String toString() {
		final String TAB = "    ";

		String retValue = "";

		retValue = "Book ( " + super.toString() + TAB + "title = " + this.title
				+ TAB + "writer = " + this.writer + TAB + " )";

		return retValue;
	}

}
