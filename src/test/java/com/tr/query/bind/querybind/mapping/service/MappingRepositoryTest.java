package com.tr.query.bind.querybind.mapping.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tr.query.bind.querybind.mapping.entity.BackendCall;
import com.tr.query.bind.querybind.mapping.entity.Menu;
import com.tr.query.bind.querybind.mapping.entity.Query;
import com.tr.query.bind.querybind.mapping.entity.Screen;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
// @TestPropertySource("classpath:application.yml")
public class MappingRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MappingRepository repository;

	private Menu createMenu() {
		Menu menuExpected = new Menu();
		menuExpected.setName("Menu Testing");

		List<Query> queryList = new ArrayList<>();
		queryList.add(Query.builder()
							.withDescription("Query Desc")
						.build());

		List<BackendCall> backendCallList = new ArrayList<>();
		BackendCall backendCall = new BackendCall();
		backendCall.setBackendUrl("URL");
		backendCall.setQueryList(queryList);
		backendCallList.add(backendCall);

		List<Screen> screenList = new ArrayList<>();
		Screen screen = new Screen();
		screen.setScreenNumber("OFTEST");
		screen.setBackendCallList(backendCallList);
		screenList.add(screen);

		menuExpected.setScreenList(screenList);
		return menuExpected;
	}

	@Test
	public void testWhenITryToFindByNameItShouldReturnIt() throws Exception {
		// given a Menu entity
		Menu menuExpected = createMenu();

		entityManager.persistAndFlush(menuExpected);

		// when I find it by id
		Optional<Menu> menuActual = repository.findByName(menuExpected.getName());

		// then it should return
		assertTrue(menuActual.isPresent());
		Menu menuEntity = menuActual.get();
		assertEquals(menuExpected, menuEntity);
	}
}