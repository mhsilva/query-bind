package com.tr.query.bind.querybind.mapping.util;

import javax.annotation.Generated;

public class ExcelModel {

	private String menuName;

	private String menuDescription;

	private String screenUsage;

	private String screenName;

	private String backendUrl;

	private String backendDescription;

	private String queryName;

	private String queryDescription;

	private ExcelModel(Builder builder) {
		this.menuName = builder.menuName;
		this.menuDescription = builder.menuDescription;
		this.screenUsage = builder.screenUsage;
		this.screenName = builder.screenName;
		this.backendUrl = builder.backendUrl;
		this.backendDescription = builder.backendDescription;
		this.queryName = builder.queryName;
		this.queryDescription = builder.queryDescription;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public String getScreenUsage() {
		return screenUsage;
	}

	public void setScreenUsage(String screenUsage) {
		this.screenUsage = screenUsage;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getBackendUrl() {
		return backendUrl;
	}

	public void setBackendUrl(String backendUrl) {
		this.backendUrl = backendUrl;
	}

	public String getBackendDescription() {
		return backendDescription;
	}

	public void setBackendDescription(String backendDescription) {
		this.backendDescription = backendDescription;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryDescription() {
		return queryDescription;
	}

	public void setQueryDescription(String queryDescription) {
		this.queryDescription = queryDescription;
	}

	/**
	 * Creates builder to build {@link ExcelModel}.
	 * 
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link ExcelModel}.
	 */
	public static final class Builder {
		private String menuName;
		private String menuDescription;
		private String screenUsage;
		private String screenName;
		private String backendUrl;
		private String backendDescription;
		private String queryName;
		private String queryDescription;

		private Builder() {
		}

		public Builder withMenuName(String menuName) {
			this.menuName = menuName;
			return this;
		}

		public Builder withMenuDescription(String menuDescription) {
			this.menuDescription = menuDescription;
			return this;
		}

		public Builder withScreenUsage(String screenUsage) {
			this.screenUsage = screenUsage;
			return this;
		}

		public Builder withScreenName(String screenName) {
			this.screenName = screenName;
			return this;
		}

		public Builder withBackendUrl(String backendUrl) {
			this.backendUrl = backendUrl;
			return this;
		}

		public Builder withBackendDescription(String backendDescription) {
			this.backendDescription = backendDescription;
			return this;
		}

		public Builder withQueryName(String queryName) {
			this.queryName = queryName;
			return this;
		}

		public Builder withQueryDescription(String queryDescription) {
			this.queryDescription = queryDescription;
			return this;
		}

		public ExcelModel build() {
			return new ExcelModel(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backendDescription == null) ? 0 : backendDescription.hashCode());
		result = prime * result + ((backendUrl == null) ? 0 : backendUrl.hashCode());
		result = prime * result + ((menuDescription == null) ? 0 : menuDescription.hashCode());
		result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + ((queryDescription == null) ? 0 : queryDescription.hashCode());
		result = prime * result + ((queryName == null) ? 0 : queryName.hashCode());
		result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
		result = prime * result + ((screenUsage == null) ? 0 : screenUsage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelModel other = (ExcelModel) obj;
		if (backendDescription == null) {
			if (other.backendDescription != null)
				return false;
		} else if (!backendDescription.equals(other.backendDescription))
			return false;
		if (backendUrl == null) {
			if (other.backendUrl != null)
				return false;
		} else if (!backendUrl.equals(other.backendUrl))
			return false;
		if (menuDescription == null) {
			if (other.menuDescription != null)
				return false;
		} else if (!menuDescription.equals(other.menuDescription))
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (queryDescription == null) {
			if (other.queryDescription != null)
				return false;
		} else if (!queryDescription.equals(other.queryDescription))
			return false;
		if (queryName == null) {
			if (other.queryName != null)
				return false;
		} else if (!queryName.equals(other.queryName))
			return false;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		if (screenUsage == null) {
			if (other.screenUsage != null)
				return false;
		} else if (!screenUsage.equals(other.screenUsage))
			return false;
		return true;
	}
}
