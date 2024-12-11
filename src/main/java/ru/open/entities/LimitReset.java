package ru.open.entities;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.open.repository.LimitRepo;

import java.util.List;

// Восстановление лимитов клиентов
@Service
public class LimitReset {
	@Autowired
	LimitRepo limitRepo;

	@Getter
	private static double defaultLimit = 10000.0;

	@Scheduled(cron = "0 0 0 * * *") // в полночь каждый день
	//@Scheduled(cron = "*/10 * * * * *")  // каждые 10 секунд - для проверки, что лимиты восстанавливаются
	public void reset(){
		System.out.println("       *** Восстановление лимитов ***");
		List<Limit> limits;
		limits = limitRepo.findAll();
		for(int i = 0; i < limits.size(); i++){
			limits.get(i).setLimitValue(defaultLimit);
		}
		limitRepo.saveAll(limits);
	}

}
