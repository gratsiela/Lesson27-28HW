package Lesson27HW;

import java.util.LinkedList;
import java.util.Queue;

class Scheduler {

	private Queue<Task> listOfTasks;

	public Scheduler() {
		this.listOfTasks = new LinkedList<Task>();
	}

	void push(Task task) {
		this.listOfTasks.add(task);
	}

	void getTask() {
		if (this.listOfTasks.peek() == null) {
			System.out.println("\nAll of your tasks are done.");
			return;
		}
		this.listOfTasks.poll().doWork();
	}

	interface Task {
		void doWork();
	}
}
