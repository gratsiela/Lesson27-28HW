package Lesson27HW;

import java.util.concurrent.ScheduledExecutorService;

abstract class Tasks {

	static class ToDoTheHomework implements Scheduler.Task {
		@Override
		public void doWork() {
			System.out.println("\n-------------------");
			System.out.println("TO DO THE HOMEWORK!");
			System.out.println("-------------------");
			System.out.println("I'm doing the homework...");
		}
	}

	static class ToUploadTheHomework implements Scheduler.Task {
		@Override
		public void doWork() {
			System.out.println("\n-------------------");
			System.out.println("TO UPLOAD THE HOMEWORK!");
			System.out.println("-------------------");
			System.out.println("I'm uploading the homework...");
		}
	}

	static class ToWatchTheVideoLecture implements Scheduler.Task {
		@Override
		public void doWork() {
			System.out.println("\n-------------------");
			System.out.println("TO DO THE HOMEWORK!");
			System.out.println("-------------------");
			System.out.println("I'm watching the video lecturee...");
		}
	}

	static class ToFindOutWhatIsEnumInJava implements Scheduler.Task {
		@Override
		public void doWork() {
			System.out.println("\n-------------------");
			System.out.println("TO READ ABOUT ENUM IN JAVA!");
			System.out.println("-------------------");
			System.out.println("I'm reading about enum in java...");
		}
	}
}
