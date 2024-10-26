package ru.open;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadPoolAlt {
	// Ёмкость пула
	private final Integer capacity;
	// Очередь задач на выполнение
	private final LinkedList<Runnable> taskQueue = new LinkedList<>();
	// Список выполняемых потоков
	private final List<Thread> threadList = new ArrayList<>();
	// Признак остановки пула
	private boolean isStopped = false;

	public ThreadPoolAlt(Integer capacity) {
		this.capacity = capacity;
	}

	public void execute(Runnable task){
		if(isStopped) throw new IllegalStateException("Pool is stopped!");
		// Если потоков меньше размера пула, сразу добавляем новый поток
		if(threadList.size() < capacity){
			System.out.println("  -> сразу в поток");
			Thread thread = new Thread(()->{
				task.run();
				dispatcher();
			});
			threadList.add(thread);
			thread.start();
		}
		// Иначе в очередь
		else{
			System.out.println("  -> превышен размер пула (" + capacity + ") - в очередь");
			taskQueue.add(task);
		}
	}

	// Остановка пула
	public void shutdown(){
		isStopped = true;
	}

	// Диспетчер - вызывается после выполнения каждого задания
	// и ставит на выполнение новую задачу из очереди
	@SneakyThrows
	void dispatcher(){
		for(int i = 0; i < threadList.size(); i++){
			if(threadList.get(i).getState() == Thread.State.TERMINATED){
				if(!taskQueue.isEmpty()){
					System.out.println("*** очередь -> поток");
					Thread thread = new Thread(()->{
											taskQueue.removeFirst().run();
											dispatcher();
											});
					threadList.set(i, thread); // Заменяем
					thread.start();
					break;
				}
				else{
					System.out.println("*** очередь пустая -> удаляем запись с потоком");
					threadList.remove(i);
				}
			}
		}
	}

	public boolean awaitTermination() {
		return taskQueue.isEmpty();
	}

}
