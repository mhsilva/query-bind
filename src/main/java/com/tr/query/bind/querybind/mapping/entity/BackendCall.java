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
public class BackendCall {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BACKEND_CALL_ID")
	private Long backendCallId;

	@Column
	private String backendUrl;

	@Column
	private String description;
	
	@Column(name = "SCREEN_ID")
	private Long screenId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "BACKEND_CALL_ID", referencedColumnName = "BACKEND_CALL_ID")
	private List<Query> queryList;

	public String getBackendUrl() {
		return backendUrl;
	}

	public void setBackendUrl(String backendUrl) {
		this.backendUrl = backendUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}
	
	public Long getBackendCallId() {
		return backendCallId;
	}

	public void setBackendCallId(Long backendCallId) {
		this.backendCallId = backendCallId;
	}

	public List<Query> getQueryList() {
		return queryList;
	}

	public void setQueryList(List<Query> queryList) {
		this.queryList = queryList;
	}
}
