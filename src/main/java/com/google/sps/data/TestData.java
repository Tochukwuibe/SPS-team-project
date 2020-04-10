package com.google.sps.data;

import java.util.Arrays;
import java.util.List;

public class TestData {

	public static void setupTestData(LearningPathService service) {
		TestData td = new TestData();
		List<LearningPath> content = td.generateContent();
		for (LearningPath learningPath : content) {
			service.store(learningPath);
		}
	}

	public List<LearningPath> generateContent() {
		return Arrays.asList(
				createWebDevPath(),
				createCovidPath(),
				createJavaPath(),
				createReactPath()
		);
	}

	private long nextId = 1;

	private LearningPath createCovidPath() {
		return learningPath("Surviving Covid-19", "description",
				section("Entertainment", "description",
						item("Channel 1", "Description", "http://www.youtube.com/"),
						item("Channel 2", "Description", "http://www.youtube.com/")),

				section("Maps", "description",
						item("Johns Hopkins Map",
								"All reported cases world-wide",
								"https://coronavirus.jhu.edu/map.html")),

				section("Food", "description",
						item("Great Recipes", "Yum", "https://www.bonappetit.com/"))
		);
	}

	private LearningPath createWebDevPath() {
		return learningPath("Web Development", "description",
				section("HTML", "description",
						item("Sample Item 1", "Sample Description", "http://www.google.com"),
						item("Sample Item 2", "Sample Description", "http://www.google.com")
				),
				section("CSS", "description",
						item("Sample Item 3", "Sample Description", "http://www.google.com"),
						item("Sample Item 4", "Sample Description", "http://www.google.com")
				)
		);
	}


	private LearningPath createJavaPath() {
		return learningPath("Intro to Java",
				"In this learning path, you will learn about the basics of java and object oriented programming.",

				section("Learning Java",
						"Being one of the most famous languages, Java, learning about its basics " +
								"will allow you to get a deeper understanding in how basic programs work. ",

						item("Java Basics",
								"You will learn about basic syntax, data types, variables, keywords, " +
										"operators, decision making, and loops.",
								"https://www.geeksforgeeks.org/java-programming-basics/?ref=lbp")
				),


				section(" Object-Oriented Programming",
						"This section will focus on the idea behind Object-oriented programming.",
						item("Encapsulation",
								"This action of enclosing all the features of something in one " +
										"place is exactly what encapsulation is about in the context of " +
										"object oriented programming.",
								"https://www.geeksforgeeks.org/encapsulation-in-java/"),
						item("Inheritance",
								"With inheritance, you can set certain relations between classes.",
								"https://beginnersbook.com/2013/03/inheritance-in-java/"),
						item("Polymorphism",
								"This fancy word means “many forms” and it takes advantage of inheritance. " +
										"This feature is useful when you want to set subclasses with different " +
										"behaviors with the method they share in common. ",
								"https://www.w3schools.com/java/java_polymorphism.asp"),
						item("Abstract Class & Interface",
								"Abstraction is used whenever you want to look at the big picture of " +
										"something rather than its components. Abstract classes and interfaces " +
										"both use abstraction, but are better used in different occasions.",
								"https://www.geeksforgeeks.org/difference-between-abstract-class-and-interface-in-java/")
				)
		);
	}

	private LearningPath createReactPath() {
		return learningPath("React JS",
				"In this learning path, you will learn about a javascript " +
						"library for building user interfaces called React",

				section("Motivation and Overall Idea",
						"In here you will see the purpose of having a library like" +
								" React, its advantages, disadvantages, and some cool beginner projects",
						item("Why React?",
								"Getting the insight of professionals is always helpful, " +
										"here we will hear what Piot Matek has to say about react.",
								"https://railsware.com/blog/why-use-react/"),

						item("Features of React",
								"In this section we will explore the main features of react.",
								"https://www.javatpoint.com/react-features"),

						item("Beginner Projects",
								"Here we will take a look at some projects done in react, " +
										"feel free to look around the users code, it will help giving you" +
										" a good idea how to start a project of your own.",
								"https://alik0211.github.io/pokedex/")
				),

				section("Learning the basic concepts",
						"This section will walk you through a basic intro on the main features of React",

						item("JXS",
								"JSX makes it easier to write React applications by being able to " +
										"write HTML in React.",
								"https://reactjs.org/docs/introducing-jsx.html"),
						item("Components",
								"In order to separate data into reusable bits of code that return HTML, " +
										"components exist to make this process easier.",
								"https://www.javatpoint.com/react-components"),
						item("State",
								"The state, similar to other software concepts, is used to save some " +
										"information about something in a certain time. In here, components have a " +
										"state, and it can change over time. This comes in handy whenever you want to " +
										"vary the behavior of certain components based on different conditions.",
								"https://www.tutorialspoint.com/reactjs/reactjs_props_overview.htm"),
						item("Props",
								"To set a distinction from the information of components that is meant to " +
										"change, props are “prop”erties sent to a component to render dynamic data.",
								"https://www.tutorialspoint.com/reactjs/reactjs_props_overview.htm"),
						item("Router",
								"Router makes the decision and organization to render specific components " +
										"easier based on a specified path.",
								"https://reacttraining.com/react-router/web/guides/quick-start"),
						item("Component Life Cycle",
								"From the creation of a certain component, you might want it to behave " +
										"differently at different stages when it receives information or it is " +
										"being updated to the DOM. There are various helpful methods that make " +
										"this process intuitive.",
								"https://www.javatpoint.com/react-component-life-cycle")
				),

				section("Redux",
						"To manage state more efficiently, redux can be used to make a container," +
								" called store, that stores all the states of the components in one place. " +
								"To know a little more about what redux is, we have provided you with some helpful articles.",
						item("Why Redux?",
								"This article points out the advantages of redux through some examples.",
								"https://blog.logrocket.com/why-use-redux-reasons-with-clear-examples-d21bffd5835/"),
						item("General things to know about redux",
								"Here are some important answers that will help you get professional " +
										"insights on when and why to use redux.",
								"https://redux.js.org/faq/general")
				)
		);
	}

	private LearningSection section(String name, String description, LearningItem... items) {
		long id = nextId++;
		LearningSection section = new LearningSection(id, name, description, id);
		section.getItems().addAll(Arrays.asList(items));
		return section;
	}

	private LearningPath learningPath(String name, String description, LearningSection... sections) {
		LearningPath learningPath = new LearningPath(nextId++, name, description);
		learningPath.getSections().addAll(Arrays.asList(sections));
		return learningPath;
	}

	private LearningItem item(String name, String description, String url) {
		long id = nextId++;
		return new LearningItem(name, id, description, id, url, 0, 0);
	}
}
