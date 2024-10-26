package ru.open;

import lombok.SneakyThrows;

public class MainApplication {

	public static void main(String[] args) throws InterruptedException {

		ThreadPoolAlt threadPoolAlt = new ThreadPoolAlt(3);

		for (int i = 0; i < 5; i++) {
			int fn = i + 1;
			System.out.println("* Отправляем на исполнение задачу " + fn);
			Task task = new Task(fn);
			threadPoolAlt.execute(task);
			}

		System.out.println("  awaitTermination - " + (threadPoolAlt.awaitTermination() ? "Очередь заданий пустая" : "В очереди есть задания"));

		Thread.sleep(10000);

		System.out.println("  awaitTermination - " + (threadPoolAlt.awaitTermination() ? "Очередь заданий пустая" : "В очереди есть задания"));

		Task task = new Task(6);
		threadPoolAlt.execute(task);
		task = new Task(7);
		threadPoolAlt.execute(task);

		System.out.println("  *** Shutdown ***");
		threadPoolAlt.shutdown();
		System.out.println("Попытка запуска задания послел остановки пула");
		task = new Task(8);
		threadPoolAlt.execute(task);

	}

}

// Нумерованные задания для запуска в потоках
class Task implements Runnable {
	private final int taskNum;
	@SneakyThrows
	public void run(){
		for (int i = 0; i < 5; i++) {
			Thread.sleep(500 + 10L * taskNum);
			System.out.println("   ~~~ Работает задание N " + taskNum);
		}
		System.out.println("--- Завершение задания N " + taskNum);
	}
	Task(int i){
		this.taskNum = i;
		System.out.println(" -- Создано задание " + i);
	}
}
