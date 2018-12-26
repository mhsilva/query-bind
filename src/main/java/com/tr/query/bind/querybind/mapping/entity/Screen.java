package com.tr.query.bind.querybind.mapping.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Screen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SCREEN_ID")
	private Long screenId;

	@Column
	private String screenNumber;

	@Column
	private String usage;

	@Column(name = "MENU_ID")
	private Long menuId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "SCREEN_ID", referencedColumnName = "SCREEN_ID")
	private List<BackendCall> backendCallList;

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}

	public String getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public List<BackendCall> getBackendCallList() {
		return backendCallList;
	}

	public void setBackendCallList(List<BackendCall> backendCallList) {
		this.backendCallList = backendCallList;
	}
}
