package com.cst.sc.model;

public class Student {
	private String ID;
	private String name;
	private String dept_name;
	private int tot_cred;
	private int credit;
	private String password;
	/**
	 * @return the id
	 */
	public String getId() {
		return ID;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.ID = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the major
	 */
	public String getMajor() {
		return dept_name;
	}
	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.dept_name = major;
	}
	/**
	 * @return the total_credit
	 */
	public int getTotal_credit() {
		return tot_cred;
	}
	/**
	 * @param total_credit the total_credit to set
	 */
	public void setTotal_credit(int total_credit) {
		this.tot_cred = total_credit;
	}
	/**
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
        return "[id=" + ID + ", name=" + name + ", major=" + dept_name + ", total_credits=" + tot_cred + ", credits=" + credit + ", password=" +password + "]";
    }
}

