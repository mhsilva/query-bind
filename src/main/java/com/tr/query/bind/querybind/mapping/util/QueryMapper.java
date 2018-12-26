package com.tr.query.bind.querybind.mapping.util;

import com.tr.query.bind.querybind.mapping.entity.Query;

public class QueryMapper {

	public static Query mapper(ExcelModel excelModel) {
		return Query.builder()
					.withQueryName(excelModel.getQueryName())
					.withDescription(excelModel.getQueryDescription())
				.build();
	}
}
