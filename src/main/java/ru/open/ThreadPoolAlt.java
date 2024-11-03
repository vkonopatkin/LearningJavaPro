package ru.open;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Iterator;
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
			Thread thread = new Thread(()->{task.run();});
			Thread thread2 = new Thread(() -> {
				thread.run();
				dispatcher(thread.getName());
			});
			threadList.add(thread);
			thread2.start();
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
	synchronized void dispatcher(String threadName){
		int i = 0;
		Iterator iterator = threadList.iterator();
		while(iterator.hasNext()){
			Thread thread0 = (Thread)iterator.next();
			if(thread0.getName() == threadName){
				if(!taskQueue.isEmpty()){
					System.out.println("*** очередь -> поток");
					Thread thread = new Thread(()->{taskQueue.removeFirst().run();});
					Thread thread2 = new Thread(() -> {
						thread.run();
						dispatcher(thread.getName());
					});
					threadList.set(i, thread); // Заменяем
					thread2.start();
					break;
				}
				else{
					System.out.println("*** очередь пустая -> удаляем запись с потоком");
					iterator.remove();
				}
			}
			i++;
		}
	}

	public boolean awaitTermination() {
		return taskQueue.isEmpty();
	}

}
