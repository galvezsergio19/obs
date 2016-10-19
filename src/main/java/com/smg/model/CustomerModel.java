package com.smg.model;

import java.sql.Date;

public class CustomerModel {

	private String customer_id;
	private String full_name;
	private Date birthday;
	private String salutation;
	private String national_id;
	private String marital_stat;
	private String gender;
	private String email;
	private String race;
	private String permanent_address;
	private int postal_code;
	private String tel_no;
	private String mob_no;
	private String promo_materials;
	private String disclosure_info;
	private String other_salutation;
	private String other_race;
	
	/**** CONSTRUCTOR ****/
	
	public CustomerModel(){}
	public CustomerModel(String customer_id, String full_name, Date birthday, String salutation, String national_id, String marital_stat, String gender,
			String email, String race, String permanent_address, int postal_code, String tel_no, String mob_no, String promo_materials, 
			String disclosure_info, String other_salutation, String other_race) {
		this.full_name = full_name;
		this.birthday = birthday;
		this.salutation = salutation;
		this.national_id = national_id;
		this.marital_stat = marital_stat;
		this.gender = gender;
		this.email = email;
		this.race = race;
		this.permanent_address = permanent_address;
		this.postal_code = postal_code;
		this.tel_no = tel_no;
		this.mob_no = mob_no;
		this.promo_materials = promo_materials;
		this.disclosure_info = disclosure_info;
	}
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getNational_id() {
		return national_id;
	}
	public void setNational_id(String national_id) {
		this.national_id = national_id;
	}
	public String getMarital_stat() {
		return marital_stat;
	}
	public void setMarital_stat(String marital_stat) {
		this.marital_stat = marital_stat;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getPermanent_address() {
		return permanent_address;
	}
	public void setPermanent_address(String permanent_address) {
		this.permanent_address = permanent_address;
	}
	public int getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	public String getMob_no() {
		return mob_no;
	}
	public void setMob_no(String mob_no) {
		this.mob_no = mob_no;
	}
	public String getPromo_materials() {
		return promo_materials;
	}
	public void setPromo_materials(String promo_materials) {
		this.promo_materials = promo_materials;
	}
	public String getDisclosure_info() {
		return disclosure_info;
	}
	public void setDisclosure_info(String disclosure_info) {
		this.disclosure_info = disclosure_info;
	}
	public String getOther_salutation() {
		return other_salutation;
	}
	public void setOther_salutation(String other_salutation) {
		this.other_salutation = other_salutation;
	}
	public String getOther_race() {
		return other_race;
	}
	public void setOther_race(String other_race) {
		this.other_race = other_race;
	}
	
	
}
