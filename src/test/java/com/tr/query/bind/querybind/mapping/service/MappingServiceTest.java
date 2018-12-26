package com.tr.query.bind.querybind.mapping.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.tr.query.bind.querybind.mapping.util.ExcelModel;

@RunWith(SpringRunner.class)
public class MappingServiceTest {

	private MappingService service;

	@Before
	public void setUp() {
		this.service = spy(new MappingService()); 
	}
	
	@Test
	public void testLoadExcel() throws IOException {
		// Given an excel file
		InputStream mappingTest = this.getClass().getResourceAsStream("/excel/mapping_test.xlsx");
		assertNotNull(mappingTest);

		// When I read it
		ExcelModel excelModelActual = service.loadExcel(mappingTest).get(0);

		// It Should return
		ExcelModel excelModelExpected = ExcelModel.builder()
				.withMenuName("C.O.Issuance")
				.withMenuDescription(
						"Generate C.O. for Origin Determination. Menu URL: /of/issu/coodoc/CertissueListMove.do")
				.withScreenUsage("Select material for issuance (C.O. Issuance)")
				.withScreenName("OF32010")
				.withBackendUrl("/of/issu/coodoc/CertissueDetailMove.do")
				.withBackendDescription("")
				.withQueryName("")
				.withQueryDescription("")
				.build();

		assertEquals(excelModelExpected, excelModelActual);
	}

	@Test 
	public void testSave() throws IOException {
		List<ExcelModel> excelModelList = new ArrayList<>();
		excelModelList.add(ExcelModel.builder()
				.withMenuName("Menu Name 1")
				.withMenuDescription(
						"Menu Desc 1")
				.withScreenUsage("Screen Usage 1")
				.withScreenName("ScreenName 1")
				.withBackendUrl("Backend URL 1")
				.withBackendDescription("Backend Desc 1")
				.withQueryName("Query Name 1")
				.withQueryDescription("Query Desc 1")
				.build());
		excelModelList.add(ExcelModel.builder()
				.withMenuName("Menu Name 1")
				.withMenuDescription(
						"Menu Desc 1")
				.withScreenUsage("Screen Usage 2")
				.withScreenName("ScreenName 2")
				.withBackendUrl("Backend URL 1")
				.withBackendDescription("Backend Desc 1")
				.withQueryName("Query Name 1")
				.withQueryDescription("Query Desc 1")
				.build());
		excelModelList.add(ExcelModel.builder()
				.withMenuName("Menu Name 1")
				.withMenuDescription(
						"Menu Desc 1")
				.withScreenUsage("Screen Usage 2")
				.withScreenName("ScreenName 2")
				.withBackendUrl("Backend URL 1")
				.withBackendDescription("Backend Desc 1")
				.withQueryName("Query Name 2")
				.withQueryDescription("Query Desc 2")
				.build());
		excelModelList.add(ExcelModel.builder()
				.withMenuName("Menu Name 1")
				.withMenuDescription(
						"Menu Desc 1")
				.withScreenUsage("Screen Usage 2")
				.withScreenName("ScreenName 2")
				.withBackendUrl("Backend URL 2")
				.withBackendDescription("Backend Desc 2")
				.withQueryName("Query Name 1")
				.withQueryDescription("Query Desc 1")
				.build());
		excelModelList.add(ExcelModel.builder()
				.withMenuName("Menu Name 1")
				.withMenuDescription(
						"Menu Desc 1")
				.withScreenUsage("Screen Usage 2")
				.withScreenName("ScreenName 2")
				.withBackendUrl("Backend URL 2")
				.withBackendDescription("Backend Desc 2")
				.withQueryName("Query Name 2")
				.withQueryDescription("Query Desc 2")
				.build());
		excelModelList.add(ExcelModel.builder()
				.withMenuName("Menu Name 2")
				.withMenuDescription(
						"Menu Desc 2")
				.withScreenUsage("Screen Usage 1")
				.withScreenName("ScreenName 1")
				.withBackendUrl("Backend URL 1")
				.withBackendDescription("Backend Desc 1")
				.withQueryName("Query Name 1")
				.withQueryDescription("Query Desc 1")
				.build());
		excelModelList.add(ExcelModel.builder()
				.withMenuName("Menu Name 2")
				.withMenuDescription(
						"Menu Desc 2")
				.withScreenUsage("Screen Usage 2")
				.withScreenName("ScreenName 2")
				.withBackendUrl("Backend URL ")
				.withBackendDescription("Backend Desc 1")
				.withQueryName("Query Name 1")
				.withQueryDescription("Query Desc 1")
				.build());
		doReturn(excelModelList).when(service).loadExcel(any());
		service.save(null);
	}
}
