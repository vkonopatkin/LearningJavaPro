package ru.open;

import java.util.*;
import java.util.stream.Collectors;

public class StreamExamples {

	public static void main(String[] args) {

		///// Запуск методов /////

		System.out.println("- 1 -");
		List<Integer> list1 = new ArrayList<>(List.of(1,1,1,3,2,5,4,1,6,9,7,8,9,0,9));
		System.out.println(m1DelDbl(list1));

		System.out.println("\n- 2 -");
		List<Integer> list2_3 = new ArrayList<>(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13));
		System.out.println(m2MaxThird(list2_3));

		System.out.println("\n- 3 -");
		System.out.println(m3UniqueMaxThird(list2_3));

		System.out.println("\n- 4 -");
		List<Employee> list4_5 = new ArrayList<>(List.of(
				new Employee("Иванов И.И.", 50, "Инженер"),
				new Employee("Петров П.П.", 55, "Инженер"),
				new Employee("Сидоров С.С.", 57, "Инженер"),
				new Employee("Смирнов С.С.", 56, "Инженер"),
				new Employee("Волков В.В.", 51, "Разработчик"),
				new Employee("Зайцев И.И.", 43, "Эксперт")
				)
		);
		m4EmplOlderEngineer(list4_5).stream()
				.peek(System.out::println)
				.toList();

		System.out.println("\n- 5 -");
		System.out.println(m5EmplEngineerAvgAge(list4_5));

		System.out.println("\n- 6 -");
		List<String> list6_8 = new ArrayList<>(List.of("Слово", "Ок", "Синхрофазотрон", "Як", "Строка", "Междометие", "Антигравитация", "Да"));
		System.out.println(m6LongestWord(list6_8));

		System.out.println("\n - 7 -");
		String string7 = "слова через пробел повторяются слова пробел слова";
		System.out.println(m7WordsToMap(string7));

		System.out.println("\n - 8 -");
		System.out.println(m8WordsInOrder(list6_8));

		System.out.println("\n - 9 -");
		String[] strings9 = {"слова пробел разной длины самоедлинноеслово",
							"один два три четыре пять",
							"красный оранжевый желтый зеленый голубой",
							"каждый охотник желает знать где"};
		System.out.println(m9LongestWord(strings9));
	}


	///// Методы /////

	// 1. удаление из листа всех дубликатов
	static List<Integer> m1DelDbl(List<Integer> list){
		return list.stream()
				.distinct()
				.toList();
	}

	// 2. Найти в списке целых чисел 3-е наибольшее число
	static Integer m2MaxThird(List<Integer> list){
		return list.stream()
				.sorted(Collections.reverseOrder())
				.limit(3)
				.reduce((a, b) -> b)
				.get();
	}

	// 3. Найти в списке целых чисел 3-е наибольшее «уникальное» число
	static Integer m3UniqueMaxThird(List<Integer> list){
		return list.stream()
				.sorted(Collections.reverseOrder())
				.distinct()
				.limit(3)
				.reduce((a, b) -> b)
				.get();
	}

	// 4. Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список
	// имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
	static List<Employee> m4EmplOlderEngineer(List<Employee> list){
		return list.stream()
				.filter(x -> Objects.equals(x.getPosition(), "Инженер"))
				.sorted((o1, o2) -> o2.getAge() - o1.getAge()) // лямбдим метод Comparator.compare
				.limit(3)
				.toList();
	}

	// 5. Имеется список объектов типа Сотрудник (имя, возраст, должность),
	// необходимо посчитать средний возраст сотрудников с должностью «Инженер»
	static Double m5EmplEngineerAvgAge(List<Employee> list){
		return list.stream()
				.filter(x -> Objects.equals(x.getPosition(), "Инженер"))
				.mapToInt(Employee::getAge)
				.average()
				.getAsDouble();
	}

	// 6. Найти в списке слов самое длинное
	static String m6LongestWord(List<String> list){
		return list.stream()
				.min((o1, o2) -> o2.length() - o1.length())
				.get();
	}

	// 7. Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
	// Построить хеш-мапу, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
	static Map<String, Long> m7WordsToMap(String string){
		return Arrays.stream(string.split(" "))
				.collect(Collectors.groupingBy(x -> x, Collectors.counting()));
	}

	// 8. Отпечатать в консоль строки из списка в порядке увеличения длины слова,
	// если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
	static List<String> m8WordsInOrder(List<String> list){
		return list.stream()
				.sorted(Comparator.comparing(x->x.toString().length()).thenComparing(Object::toString))
				.toList();
	}

	// 9. Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом,
	// найти среди всех слов самое длинное, если таких слов несколько, получить любое из них

	static String m9LongestWord(String[] strings){
	return Arrays.stream(strings)
			.flatMap(x -> Arrays.stream(x.split(" ")))
			.min((o1, o2) -> o2.length() - o1.length())
			.get();
	}

}
