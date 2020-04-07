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

	private static LearningItem createLearningItem(String name, long sequence) {
		return new LearningItem(name, 1, "description", sequence , "http://google.com", 0, 0);
	}

	@Test
	public void testGetLearningPaths() {

		LearningPathService svc = new LearningPathService();
		svc.store(new LearningPath(1, "Path 1", "description"));
		svc.store(new LearningPath(2, "Path 2", "description"));

		List<LearningPathSummary> list = svc.listLearningPaths();

		assertEquals(2, list.size());
	}

	@Test
	public void saveComplexLearningPath() throws EntityNotFoundException {
		long sequence = 0;
		LearningSection html = new LearningSection(12, "HTML", "description", 0);
		html.getItems().add(createLearningItem("Item 1", sequence++));
		html.getItems().add(createLearningItem("Item 2", sequence++));

		LearningSection css = new LearningSection(11, "CSS", "description", 1);
		// TODO make items not just strings!
		css.getItems().add(createLearningItem("Item 3", sequence++));
		css.getItems().add(createLearningItem("Item 4", sequence++));

		LearningPath path = new LearningPath(1, "Web Development", "description");
		path.getSections().add(html);
		path.getSections().add(css);

		LearningPathService svc = new LearningPathService();
		svc.store(path);

		LearningPath result = svc.load(path.getId());

		assertSameLearningPath(path, result);
	}

	@Test
	public void updateLearningPath() throws EntityNotFoundException {

		long sequence = 0;

		LearningSection html = new LearningSection(12, "HTML", "description", 0);
		html.getItems().add(createLearningItem("Item 1", sequence++));
		html.getItems().add(createLearningItem("Item 2", sequence++));

		LearningSection css = new LearningSection(11, "CSS", "description", 1);
		css.getItems().add(createLearningItem("Item 3", sequence++));
		css.getItems().add(createLearningItem("Item 4", sequence++));

		LearningPath initial = new LearningPath(1, "Web Development", "description");
		initial.getSections().add(css);

		LearningPath updated = new LearningPath(1, "New Name", "description");
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