package com.tr.query.bind.querybind.mapping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.annotation.Generated;

@Entity
public class Query {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "QUERY_ID")
	private Long queryId;

	@Column
	private String queryName;

	@Column
	private String description;

	@Column(name = "BACKEND_CALL_ID")
	private Long backendCallId;

	private Query(Builder builder) {
		this.queryId = builder.queryId;
		this.queryName = builder.queryName;
		this.description = builder.description;
		this.backendCallId = builder.backendCallId;
	}

	public Long getQueryId() {
		return queryId;
	}

	public void setQueryId(Long queryId) {
		this.queryId = queryId;
	}

	public Long getBackendCallId() {
		return backendCallId;
	}

	public void setBackendCallId(Long backendCallId) {
		this.backendCallId = backendCallId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Creates builder to build {@link Query}.
	 * 
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Query}.
	 */
	public static final class Builder {
		private Long queryId;
		private String queryName;
		private String description;
		private Long backendCallId;

		private Builder() {
		}

		public Builder withQueryId(Long queryId) {
			this.queryId = queryId;
			return this;
		}

		public Builder withQueryName(String queryName) {
			this.queryName = queryName;
			return this;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder withBackendCallId(Long backendCallId) {
			this.backendCallId = backendCallId;
			return this;
		}

		public Query build() {
			return new Query(this);
		}
	}
}
