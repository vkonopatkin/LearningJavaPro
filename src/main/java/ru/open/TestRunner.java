package ru.open;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TestRunner {

	static ArrayList<String> errorList = new ArrayList<>();

	public static void main(String[] args) {
		runTests(TestedClass.class);
		runCsvParce(TestedClass.class);
	}

	static void runTests(Class c){
		System.out.println("****** runTests *******");
		errorList.clear();
		System.out.println("----- Анализ методов " + c.getSimpleName() + " ------");
		int pr;
		String prKey;
		ArrayList<String> testPriorList = new ArrayList(); // список для сортировки приоритетов
		Map<String, Method> testMap = new HashMap();       // мапа с методами @Test, ключ из предыдущего списка
		Method methBeforeSuite = null;
		Method methAfterSuite = null;
		Method methBeforeTest = null;
		Method methAfterTest = null;
		Method[] methods = c.getDeclaredMethods();
		for(Method m : methods){
			// Проверяем общее количество аннотаций
			Annotation[] annotations = m.getAnnotations();
			if (annotations.length > 1){
				errorList.add(m.getName() + " - более одной аннотациyиfm  - игнорируем!");
				continue;
			}
			// Ищем метод before
			if (m.isAnnotationPresent(BeforeSuite.class)){
				if (!Modifier.isStatic(m.getModifiers())) {
					errorList.add(m.getName() + " - BeforeSuite не static - игнорируем!");
					continue;
				}
				if (methBeforeSuite != null){
					errorList.add(m.getName() + " - повторный BeforeSuite - игнорируем!");
					continue;
				}
				methBeforeSuite = m;
				methBeforeSuite.setAccessible(true);
			}
			// Ищем метод beforeTest
			if (m.isAnnotationPresent(BeforeTest.class)){
				if (methBeforeTest != null){
					errorList.add(m.getName() + " - повторный BeforeTest - игнорируем!");
					continue;
				}
				methBeforeTest = m;
				methBeforeTest.setAccessible(true);
			}
			// Ищем метод after
			if (m.isAnnotationPresent(AfterSuite.class)){
				if (!Modifier.isStatic(m.getModifiers())) {
					errorList.add(m.getName() + " - AfterSuite не static - игнорируем!");
					continue;
				}
				if (methAfterSuite != null){
					errorList.add(m.getName() + " - повторный AfterSuite - игнорируем!");
					continue;
				}
				methAfterSuite = m;
				methAfterSuite.setAccessible(true);
			}
			// Ищем метод afterTest
			if (m.isAnnotationPresent(AfterTest.class)){
				if (methAfterTest != null){
					errorList.add(m.getName() + " - повторный AfterTest - игнорируем!");
					continue;
				}
				methAfterTest = m;
				methAfterTest.setAccessible(true);
			}

			// Разбираемся с Test
			if (m.isAnnotationPresent(Test.class)){
				pr = m.getAnnotation(Test.class).priority();
				if (pr < 1 | pr > 10){
					errorList.add(m.getName() + " - priority вне разрешенного диапазона 1-10 - игнорируем!");
					continue;
				}
				prKey =	Integer.toString(pr) + Integer.toString(m.hashCode());
				testPriorList.add(prKey);
				testMap.put(prKey, m);
			}

		}

		// Сортируем Test по приоритету
		testPriorList.sort(Comparator.naturalOrder());
		// Запуск
		System.out.println("----- Запуск методов " + c.getSimpleName() + " ------");
		String methName = null;
		try{
			Object obj = c.getDeclaredConstructor().newInstance();
			Method methTest;
			if (methBeforeSuite != null) {
				methName = methBeforeSuite.getName();
				methBeforeSuite.invoke(obj);
			}
			for (int i = 0; i < testPriorList.size(); i++) {
				if (methBeforeTest != null) {
					methName = methBeforeTest.getName();
					methBeforeTest.invoke(obj);
				}
				methTest = testMap.get(testPriorList.get(i));
				methTest.setAccessible(true);
				methName = methTest.getName();
				methTest.invoke(obj);
				if (methAfterTest != null) {
					methName = methAfterTest.getName();
					methAfterTest.invoke(obj);
				}
			}
			if (methAfterSuite != null) {
				methName = methAfterSuite.getName();
				methAfterSuite.invoke(obj);
			}
		}
		catch(NoSuchMethodException ex){
			errorList.add("Не найден конструктор класса " + c.getSimpleName());
		}
		catch(InstantiationException ex){
			errorList.add("Невозможно создать объект класса " + c.getSimpleName());
		}
		catch(IllegalAccessException ex){
			errorList.add("Недостаточный доступ для создания объекта класса " + c.getSimpleName());
		}
		catch(InvocationTargetException ex){
			errorList.add("Ошибка запуска " + methName + ": " + ex.getTargetException().fillInStackTrace());
		}
		System.out.println("---------------------------------------");
		if (!errorList.isEmpty()){
			System.out.println("*** Ошибки:");
			for (int i=0; i<errorList.size(); i++) {
				System.out.println(errorList.get(i));
			}
		}
		System.out.println("***********************");
		System.out.println("");
	}

	static void runCsvParce(Class c){
		System.out.println("****** runCsvParce ******");
		Method method = null;
		errorList.clear();
		try {
			Object obj = c.getDeclaredConstructor().newInstance();
			method = c.getMethod("csvSource", int.class, String.class, int.class, boolean.class);
			String parStr = method.getAnnotation(CsvSource.class).value();
			String[] params = parStr.split(", ");
			method.invoke(obj, Integer.parseInt(params[0]), params[1], Integer.parseInt(params[2]), Boolean.parseBoolean(params[3]));
		}
		catch(NoSuchMethodException ex){
			errorList.add("Не найден конструктор класса " + c.getSimpleName());
			System.out.println(ex.getMessage());
		}
		catch(InstantiationException ex){
			errorList.add("Невозможно создать объект класса " + c.getSimpleName());
		}
		catch(IllegalAccessException ex){
			errorList.add("Недостаточный доступ для создания объекта класса " + c.getSimpleName());
		}
		catch(InvocationTargetException ex){
			errorList.add("Ошибка запуска " + method.getName() + ": " + ex.getTargetException().fillInStackTrace());
		}
		if (!errorList.isEmpty()){
			System.out.println("*** Ошибки:");
			for (int i=0; i<errorList.size(); i++) {
				System.out.println(errorList.get(i));
			}
		}
		System.out.println("*************************");
	}

}
