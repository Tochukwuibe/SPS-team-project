package com.google.sps.data;

public class TestData {
	
	public static void setupTestData(LearningPathService service) {
		service.store(createWebDevPath());
		service.store(createCovidPath());
	}

	private static LearningItem createLearningItem(String name, long sequence) {
		return new LearningItem(name, 1, "description", sequence , "http://google.com", 0, 0);
	}

	private static LearningPath createCovidPath() {

		long sequence = 0;
		LearningSection youtube = new LearningSection(22, "Youtube channels", "description", 0);

		youtube.getItems().add(createLearningItem("Item 1", sequence++));
		youtube.getItems().add(createLearningItem("Item 2", sequence++));

		LearningSection maps = new LearningSection(23, "Maps", "description", 1);
		maps.getItems().add(createLearningItem("Arcgis world-wide map", sequence++));
		maps.getItems().add(createLearningItem("Item 4", sequence++));

		LearningSection food = new LearningSection(23, "Food", "description", 1);
		food.getItems().add(createLearningItem("Great recipes", sequence++));
		food.getItems().add(createLearningItem("Item 4", sequence++));

		LearningPath covid = new LearningPath(2, "Surviving Covid-19", "description");
		covid.getSections().add(youtube);
		covid.getSections().add(maps);
		covid.getSections().add(food);
		return covid;
	}

	private static LearningPath createWebDevPath() {

		long sequence = 0;
		LearningSection html = new LearningSection(11, "HTML", "description", 0);
		html.getItems().add(createLearningItem("Item 1", sequence++));
		html.getItems().add(createLearningItem("Item 2", sequence++));

		LearningSection css = new LearningSection(12, "CSS", "description", 1);
		css.getItems().add(createLearningItem("Item 3", sequence++));
		css.getItems().add(createLearningItem("Item 4", sequence++));

		LearningPath webDev = new LearningPath(1, "Web Development", "description");
		webDev.getSections().add(html);
		webDev.getSections().add(css);
		return webDev;
	}
}
