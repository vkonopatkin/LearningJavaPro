package ru.open.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.open.entities.Limit;
import ru.open.entities.LimitReset;
import ru.open.entities.Payment;
import ru.open.repository.LimitRepo;
import ru.open.service.exceptions.Ex400BadRequest;

import java.util.Optional;

@Service
public class PayLimitService {

	@Autowired
	LimitRepo limitRepo;

	@SneakyThrows
		public double payLimit(Payment payment){

		if(payment.getPaymentSumma() < 0){
			throw new Ex400BadRequest("Сумма платежа должна быть положительной!");
		}

		// Проверяем остаток лимита, если хватает, уменьшаем на сумму платежа
		Optional<Limit> limitOpt = limitRepo.findByUserId(payment.getUserId());
		Limit limit;
		if(limitOpt.isEmpty()){ // добавляем
			limit = new Limit(payment.getUserId(), LimitReset.getDefaultLimit());
			limitRepo.save(limit);
			System.out.println("     *** Добавлен новый лимит (id=" + limit.getId() + ")");
		}
		else{
			limit = limitOpt.get();
		}

		if (payment.getPaymentSumma() > limit.getLimitValue()){
			throw new Ex400BadRequest("Платёж невозможен: заданная сумма " + payment.getPaymentSumma() + " превышает остаток лимита " + limit.getLimitValue());
		}

		limit.setLimitValue(limit.getLimitValue() - payment.getPaymentSumma());
		limitRepo.save(limit);
		return limit.getLimitValue();
	}

}
