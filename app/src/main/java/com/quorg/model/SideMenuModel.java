/**
 * @author valkesh patel
 */
package com.quorg.model;

public class SideMenuModel {

	public String title;
	public int imageid;
	public int imageidClicked;
	public boolean isClicked;

	public SideMenuModel(String title, int imageid, int imageidClicked,
						 boolean isClicked) {
		super();
		this.title = title;
		this.imageid = imageid;
		this.isClicked = isClicked;
		this.imageidClicked = imageidClicked;
	}

	public int getImageidClicked() {
		return imageidClicked;
	}

	public void setImageidClicked(int imageidClicked) {
		this.imageidClicked = imageidClicked;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImageid() {
		return imageid;
	}

	public void setImageid(int imageid) {
		this.imageid = imageid;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

}
