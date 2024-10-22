package ru.open;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestStreamExamples {

	List<Integer> list2_3 = new ArrayList<>(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13));
	List<Employee> list4_5 = new ArrayList<>(List.of(
			new Employee("Иванов И.И.", 50, "Инженер"),
			new Employee("Петров П.П.", 55, "Инженер"),
			new Employee("Сидоров С.С.", 57, "Инженер"),
			new Employee("Смирнов С.С.", 56, "Инженер"),
			new Employee("Волков В.В.", 51, "Разработчик"),
			new Employee("Зайцев И.И.", 43, "Эксперт")
			)
	);
	List<String> list6_8 = new ArrayList<>(List.of("Слово", "Ок", "Синхрофазотрон", "Як", "Строка", "Междометие", "Антигравитация", "Да"));

	@Test
	public void testM1() {
		List<Integer> list1 = new ArrayList<>(List.of(1, 1, 1, 3, 2, 5, 4, 1, 6, 9, 7, 8, 9, 0, 9));
		List<Integer> list1Res = new ArrayList<>(List.of(1, 3, 2, 5, 4, 6, 9, 7, 8, 0));
		Assertions.assertEquals(StreamExamples.m1DelDbl(list1), list1Res);
	}

	@Test
	public void testM2() {
		Assertions.assertEquals(StreamExamples.m2MaxThird(list2_3), 10);
	}

	@Test
	public void testM3() {
		Assertions.assertEquals(StreamExamples.m3UniqueMaxThird(list2_3), 9);
	}

	@Test
	public void testM4() {
		List<Employee> list4Res = new ArrayList<>(List.of(
				new Employee("Сидоров С.С.", 57, "Инженер"),
				new Employee("Смирнов С.С.", 56, "Инженер"),
				new Employee("Петров П.П.", 55, "Инженер")
				)
		);
		Assertions.assertEquals(StreamExamples.m4EmplOlderEngineer(list4_5), list4Res);
	}

	@Test
	public void testM5() {
		Assertions.assertEquals(StreamExamples.m5EmplEngineerAvgAge (list4_5), 54.5);
	}

	@Test
	public void testM6() {
		Assertions.assertEquals(StreamExamples.m6LongestWord(list6_8), "Синхрофазотрон");
	}

	@Test
	public void testM7() {
		String string7 = "слова через пробел повторяются слова пробел слова";
		Map<String, Long> map7Res = new HashMap<>(Map.of("повторяются", 1L, "через", 1L, "слова", 3L, "пробел", 2L));
		Assertions.assertEquals(StreamExamples.m7WordsToMap(string7), map7Res);
	}

	@Test
	public void testM8() {
		List<String> list8Res = new ArrayList<>(List.of("Да", "Ок", "Як", "Слово", "Строка", "Междометие", "Антигравитация", "Синхрофазотрон"));
		Assertions.assertEquals(StreamExamples.m8WordsInOrder(list6_8), list8Res);
	}

	@Test
	public void testM9() {
		String[] strings9 = {"слова пробел разной длины самоедлинноеслово",
				"один два три четыре пять",
				"красный оранжевый желтый зеленый голубой",
				"каждый охотник желает знать где"};
		Assertions.assertEquals(StreamExamples.m9LongestWord(strings9), "самоедлинноеслово");
	}


}
