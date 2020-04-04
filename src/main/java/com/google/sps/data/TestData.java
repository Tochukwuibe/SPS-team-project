package com.google.sps.data;

public class TestData {

	public static void setupTestData(LearningPathService service) {
		service.store(createWebDevPath());
		service.store(createCovidPath());
	}

	private static LearningPath createCovidPath() {
		LearningSection youtube = new LearningSection(22, "Youtube channels", "description", 0);
		youtube.getItems().add("Item 1");
		youtube.getItems().add("Item 2");

		LearningSection maps = new LearningSection(23, "Maps", "description", 1);
		maps.getItems().add("Arcgis world-wide map");
		maps.getItems().add("Item 4");

		LearningSection food = new LearningSection(23, "Food", "description", 1);
		food.getItems().add("Great recipes");
		food.getItems().add("Item 4");

		LearningPath covid = new LearningPath(2, "Surviving Covid-19", "description");
		covid.getSections().add(youtube);
		covid.getSections().add(maps);
		covid.getSections().add(food);
		return covid;
	}

	private static LearningPath createWebDevPath() {
		LearningSection html = new LearningSection(11, "HTML", "description", 0);
		html.getItems().add("Item 1");
		html.getItems().add("Item 2");

		LearningSection css = new LearningSection(12, "CSS", "description", 1);
		css.getItems().add("Item 3");
		css.getItems().add("Item 4");

		LearningPath webDev = new LearningPath(1, "Web Development", "description");
		webDev.getSections().add(html);
		webDev.getSections().add(css);
		return webDev;
	}
}
