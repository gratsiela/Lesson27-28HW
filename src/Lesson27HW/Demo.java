package Lesson27HW;

class Demo {

	public static void main(String[] args) {

		Scheduler scheduler = new Scheduler();

		scheduler.push(new Tasks.ToDoTheHomework());
		scheduler.push(new Tasks.ToUploadTheHomework());
		scheduler.push(new Tasks.ToFindOutWhatIsEnumInJava());
		scheduler.push(new Tasks.ToWatchTheVideoLecture());

		scheduler.getTask();
		scheduler.getTask();
		scheduler.getTask();
		scheduler.getTask();
		scheduler.getTask();

	}

}
