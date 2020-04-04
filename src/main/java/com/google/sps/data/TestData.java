package com.google.sps.data;

public class TestData {

	public static void setupTestData(LearningPathService service) {
		service.store(createWebDevPath());
		service.store(createCovidPath());
	}

	private static LearningPath createCovidPath() {
		LearningSection youtube = new LearningSection("Youtube channels", 22, 0);
		youtube.getItems().add("Item 1");
		youtube.getItems().add("Item 2");

		LearningSection maps = new LearningSection("Maps", 23, 1);
		maps.getItems().add("Arcgis world-wide map");
		maps.getItems().add("Item 4");

		LearningSection food = new LearningSection("Food", 23, 1);
		food.getItems().add("Great recipes");
		food.getItems().add("Item 4");

		LearningPath covid = new LearningPath("Surviving Covid-19", 2);
		covid.getSections().add(youtube);
		covid.getSections().add(maps);
		covid.getSections().add(food);
		return covid;
	}

	private static LearningPath createWebDevPath() {
		LearningSection html = new LearningSection("HTML", 11, 0);
		html.getItems().add("Item 1");
		html.getItems().add("Item 2");

		LearningSection css = new LearningSection("CSS", 12, 1);
		css.getItems().add("Item 3");
		css.getItems().add("Item 4");

		LearningPath webDev = new LearningPath("Web Development", 1);
		webDev.getSections().add(html);
		webDev.getSections().add(css);
		return webDev;
	}
}
