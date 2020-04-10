package com.google.sps.data;

public class TestData {
	
	public static void setupTestData(LearningPathService service) {
		service.store(createWebDevPath());
		service.store(createCovidPath());
		service.store(createJavaPath());
		service.store(createReactPath());
	}

	private static LearningItem createLearningItem(String name, long sequence) {
		return new LearningItem(name, 1, "description", sequence , "http://google.com", 0, 0);
	}

	private static LearningPath createJavaPath(){

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


	private static LearningPath createJavaPath(){

		long sequence = 0;

		LearningPath java = new LearningPath(0, "Intro to Java", 
		"In this learning path, you will learn about the basics of java and object oriented programming.");

		LearningSection introToJava = new LearningSection(30, "Learning Java", 
		"Being one of the most famous languages, Java, learning about its 
		basics will allow you to get a deeper understanding in how basic 
		programs work. ", 0);

		LearningItem javaBasics = new LearningItem("Java Basics", 
		"You will learn about basic syntax, data types, variables, keywords, operators, decision making, and loops.",
		sequence++, "https://www.geeksforgeeks.org/java-programming-basics/?ref=lbp",0,0);
		
		introToJava.getItems().add(javaBasics);

		LearningSection OOP = new LearningSection(31, " Object-Oriented Programming", 
		"This section will focus on the idea behind Object-oriented programming.", 0);

		LearningItem encap = new LearningItem("Encapsulation", 
		"This action of enclosing all the features of something in one place
		 is exactly what encapsulation is about in the context of object oriented
		  programming.",
		sequence++, "https://www.geeksforgeeks.org/encapsulation-in-java/",0,0);
		
		LearningItem inheritance = new LearningItem("Inheritance", 
		"With inheritance, you can set certain relations between classes.",
		sequence++, "https://beginnersbook.com/2013/03/inheritance-in-java/",0,0);


		LearningItem poly = new LearningItem("Polymorphism", 
		"This fancy word means “many forms” and it takes advantage of inheritance. 
		This feature is useful when you want to set subclasses with different
		 behaviors with the method they share in common. ",
		sequence++, "https://www.w3schools.com/java/java_polymorphism.asp",0,0);
		
		LearnigItem absAndInter = new LearningItem("Abstract Class & Interface", 
		"Abstraction is used whenever you want to look at the big picture of something 
		rather than its components. Abstract classes and interfaces both use abstraction,
		 but are better used in different occasions.",
		sequence++, "https://www.geeksforgeeks.org/difference-between-abstract-class-and-interface-in-java/",0,0);

		OOP.getItems().add(encap);
		OOP.getItems().add(inheritance);
		OOP.getItems().add(poly);
		OOP.getItems().add(absAndInter);
		
		java.getSections().add(introToJava);
		java.getSections().add(OPP);

		return java;
	}

	private static LearningPath createReactPath(){

		long sequence = 0;

		LearningPath react = new LearningPath(0, "React JS", 
		"In this learning path, you will learn about a javascript library for building user interfaces called React");

		LearningSection moti = new LearningSection(33, "Motivation and Overall Idea", 
		"In here you will see the purpose of having a library like React, its advantages,
		 disadvantages, and some cool beginner projects", 0);

		LearningItem whyReact = new LearningItem("Why React?", 
		" Getting the insight of professionals is always helpful, here we will hear what Piot Matek has to say about react.",
		sequence++, "https://railsware.com/blog/why-use-react/",0,0);

		LearningItem featReact = new LearningItem("Features of React", 
		"In this section we will explore the main features of react. ",
		sequence++, "https://www.javatpoint.com/react-features",0,0);

		LearningItem beg = new LearningItem("Beginner Projects", 
		" Here we will take a look at some projects done in react, 
		feel free to look around the users code, it will help giving 
		you a good idea how to start a project of your own.",
		sequence++, "https://alik0211.github.io/pokedex/",0,0);
		
		react.getItems().add(whyReact);
		react.getItems().add(featReact);
		react.getItems().add(beg);

		LearningSection basic = new LearningSection(34, " Learning the basic concepts", 
		"This section will walk you through a basic intro on the main features of React", 0);

		LearningItem jxs = new LearningItem("JXS", 
		"JSX makes it easier to write React applications by being able to write HTML in React.",
		sequence++, "https://reactjs.org/docs/introducing-jsx.html",0,0);
		
		LearningItem components = new LearningItem("Components", 
		" In order to separate data into reusable bits of code that return HTML, components exist to make this process easier.",
		sequence++, "https://www.javatpoint.com/react-components",0,0);

		LearningItem state = new LearningItem("State", 
		"The state, similar to other software concepts, is used to save some information 
		about something in a certain time. In here, components have a state, and it can 
		change over time. This comes in handy whenever you want to vary the behavior of 
		certain components based on different conditions.",
		sequence++, "https://www.tutorialspoint.com/reactjs/reactjs_props_overview.htm",0,0);
		
		LearningItem props = new LearningItem("Props", 
		"To set a distinction from the information of components that is meant to change, 
	    props are “prop”erties sent to a component to render dynamic data. 
        ",
		sequence++, "https://www.tutorialspoint.com/reactjs/reactjs_props_overview.htm",0,0);
		
		LearningItem router = new LearningItem("Router", 
		"Router makes the decision and organization to render specific components easier 
		based on a specified path.",
		sequence++, "https://reacttraining.com/react-router/web/guides/quick-start",0,0);

		LearnigItem  lifecycle = new LearningItem(" Component Life Cycle", 
		"From the creation of a certain component, you might want it to behave differently 
		at different stages when it receives information or it is being updated to the DOM.
		There are various helpful methods that make this process intuitive.",
		sequence++, "https://www.javatpoint.com/react-component-life-cycle",0,0);

		basic.getItems().add(jxs);
		basic.getItems().add(components);
		basic.getItems().add(state);
		basic.getItems().add(props);
	    basic.getItems().add(router);
		basic.getItems().add(lifecycle);

		LearningSection redux = new LearningSection(35, "Redux", 
		"To manage state more efficiently, redux can be used to make a container, called 
		store, that stores all the states of the components in one place. To know a little
		more about what redux is, we have provided you with some helpful articles.",0);

		LearnigItem  genRedux = new LearningItem("Why Redux?", 
		"This article points out the advantages of redux through some examples.",
		sequence++, "https://blog.logrocket.com/why-use-redux-reasons-with-clear-examples-d21bffd5835/",0,0);

		LearnigItem  moreRedux = new LearningItem("General things to know about redux", 
		"Here are some important answers that will help you get professional insights on when and why to use redux.",
		sequence++, " https://redux.js.org/faq/general",0,0);

		redux.getItems().add(genRedux);
		redux.getItems().add(moreRedux);

		react.getSections().add(basic);
		react.getSections().add(redux);

		return react;
	}
}
