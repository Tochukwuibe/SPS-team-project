package com.google.sps.data;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.sps.html.LearningPathSummary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class LearningPathServiceTest {

	private final LocalServiceTestHelper helper =
			new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void testGetLearningPaths() {

		LearningPathService svc = new LearningPathService();
		svc.store(new LearningPath("Path 1", 1));
		svc.store(new LearningPath("Path 2", 2));

		List<LearningPathSummary> list = svc.listLearningPaths();

		assertEquals(2, list.size());
	}

	@Test
	public void saveComplexLearningPath() throws EntityNotFoundException {
		LearningSection html = new LearningSection("HTML", 12, 0);
		html.getItems().add("Item 1");
		html.getItems().add("Item 2");

		LearningSection css = new LearningSection("CSS", 11, 1);
		// TODO make items not just strings!
		css.getItems().add("Item 3");
		css.getItems().add("Item 4");

		LearningPath path = new LearningPath("Web Development", 1);
		path.getSections().add(html);
		path.getSections().add(css);

		LearningPathService svc = new LearningPathService();
		svc.store(path);

		LearningPath result = svc.load(path.getId());

		assertSameLearningPath(path, result);
	}

	@Test
	public void updateLearningPath() throws EntityNotFoundException {
		LearningSection html = new LearningSection("HTML", 12, 0);
		html.getItems().add("Item 1");
		html.getItems().add("Item 2");

		LearningSection css = new LearningSection("CSS", 11, 1);
		css.getItems().add("Item 3");
		css.getItems().add("Item 4");

		LearningPath initial = new LearningPath("Web Development", 1);
		initial.getSections().add(css);

		LearningPath updated = new LearningPath("New Name", 1);
		updated.getSections().add(html);

		LearningPathService svc = new LearningPathService();
		svc.store(initial);
		svc.store(updated);

		LearningPath result = svc.load(updated.getId());

		assertSameLearningPath(updated, result);
	}

	private static void assertSameLearningPath(LearningPath expected, LearningPath actual) {
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getSections().size(), actual.getSections().size());

		for (int i = 0; i < expected.getSections().size(); i++) {
			assertSameSection(expected.getSections().get(i), actual.getSections().get(i));
		}
	}

	private static void assertSameSection(LearningSection expected, LearningSection actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		// TODO check all items
	}
}