package com.google.sps.data;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.google.sps.data.LearningPathServiceTest.createLearningItem;
import static org.junit.Assert.assertEquals;

public class ItemFeedbackServiceTest {

	public static final String USER2 = "443322";
	public static final String USER1 = "123432432";
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
	public void testSubmitFeedback() throws EntityNotFoundException {
		long sequence = 100;
		LearningSection html = new LearningSection(12, "HTML", "description", 0);
		LearningItem item1 = createLearningItem("Item 1", sequence++);
		LearningItem item2 = createLearningItem("Item 2", sequence++);
		html.getItems().add(item1);
		html.getItems().add(item2);

		LearningSection css = new LearningSection(11, "CSS", "description", 1);
		// TODO make items not just strings!
		css.getItems().add(createLearningItem("Item 3", sequence++));
		css.getItems().add(createLearningItem("Item 4", sequence++));

		LearningPath path = new LearningPath(1, "Web Development", "description");
		path.getSections().add(html);
		path.getSections().add(css);

		LearningPathService svc = new LearningPathService();
		svc.store(path);
		LearningPath load = svc.load(path.getId());

		ItemFeedbackService itemFeedbackService = new ItemFeedbackService();

		itemFeedbackService.submitFeedback(path.getId(), item1.getId(), USER1, 5, true);

		LearningItem ratedItem = svc.loadItem(item1.getId());
		assertEquals(1, ratedItem.getRatingCount());
		assertEquals(5, ratedItem.getRatingTotal());

		itemFeedbackService.submitFeedback(path.getId(), item1.getId(), USER2, 1, false);

		ratedItem = svc.loadItem(item1.getId());
		assertEquals(2, ratedItem.getRatingCount());
		assertEquals(6, ratedItem.getRatingTotal());
	}
}
