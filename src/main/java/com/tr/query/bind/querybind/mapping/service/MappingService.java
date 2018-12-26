package com.tr.query.bind.querybind.mapping.service;

import static com.tr.query.bind.querybind.mapping.util.ExcelUtil.getStringFrom;
import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tr.query.bind.querybind.mapping.entity.BackendCall;
import com.tr.query.bind.querybind.mapping.entity.Menu;
import com.tr.query.bind.querybind.mapping.entity.Screen;
import com.tr.query.bind.querybind.mapping.util.ExcelModel;
import com.tr.query.bind.querybind.mapping.util.QueryMapper;

@Service
public class MappingService {

	@Autowired
	private MappingRepository repository;
	
	private ExcelModel createExcelModelFrom(Row row) {
		return ExcelModel.builder()
				.withMenuName(getStringFrom(row.getCell(0)))
				.withMenuDescription(getStringFrom(row.getCell(1)))
				.withScreenUsage(getStringFrom(row.getCell(2)))
				.withScreenName(getStringFrom(row.getCell(3)))
				.withBackendUrl(getStringFrom(row.getCell(4)))
				.withBackendDescription(getStringFrom(row.getCell(5)))
				.withQueryName(getStringFrom(row.getCell(6)))
				.withQueryDescription(getStringFrom(row.getCell(7)))
				.build();
	}

	public List<ExcelModel> loadExcel(InputStream excelInputStream) throws IOException {
		Workbook wb = new XSSFWorkbook(excelInputStream);
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		List<ExcelModel> excelModelList = new ArrayList<>();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// Skip Header row.
			if (row.getRowNum() == 0) {
				continue;
			}
			excelModelList.add(this.createExcelModelFrom(row));
		}
		wb.close();
		return excelModelList;
	}

	public void save(InputStream excelInputStream) throws IOException {
		List<ExcelModel> excelList = this.loadExcel(excelInputStream);
		List<Menu> menuList = new ArrayList<>();
		collectByMenuName(excelList).forEach((menuName, groupByMenuNameList) -> {
			List<Screen> screenList = new ArrayList<>();
			collectByScreenName(groupByMenuNameList).forEach((screenName, groupByScreenNameList) -> {
				Screen screen = new Screen();
				screen.setScreenNumber(screenName);
				screen.setUsage(groupByScreenNameList.get(0).getScreenUsage());
				screen.setBackendCallList(createBackendCallList(collectByBackendCall(groupByScreenNameList)));
				screenList.add(screen);
			});
			Menu menu = new Menu();
			menu.setName(groupByMenuNameList.get(0).getMenuName());
			menu.setDescription(groupByMenuNameList.get(0).getMenuDescription());
			menu.setScreenList(screenList);
			menuList.add(menu);
		});
		repository.saveAll(menuList);
	}

	private List<BackendCall> createBackendCallList(Map<String, List<ExcelModel>> collectByBackendCall) {
		List<BackendCall> backendCallList = new ArrayList<>();
		collectByBackendCall.forEach((backendUrl, queryList) -> {
			BackendCall backendCall = new BackendCall();
			backendCall.setQueryList(queryList.stream()
				.map(QueryMapper::mapper)
				.collect(Collectors.toList()));
			//FIXME: Improve this later, please 
			ExcelModel excelModel = queryList.get(0);
			backendCall.setDescription(excelModel.getBackendDescription());
			backendCall.setBackendUrl(excelModel.getBackendUrl());
		});
		return backendCallList;
	}

	private Map<String, List<ExcelModel>> collectByBackendCall(List<ExcelModel> groupByScreenNameList) {
		return groupByScreenNameList.stream()
				.collect(groupingBy(ExcelModel::getBackendUrl));
	}

	private Map<String, List<ExcelModel>> collectByScreenName(List<ExcelModel> groupByMenuNameList) {
		return groupByMenuNameList.stream()
				.collect(groupingBy(ExcelModel::getScreenName));
	}

	private Map<String, List<ExcelModel>> collectByMenuName(List<ExcelModel> excelList) {
		return excelList.stream()
				.collect(groupingBy(ExcelModel::getMenuName));
	}
}