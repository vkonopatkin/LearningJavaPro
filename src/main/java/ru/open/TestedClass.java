package ru.open;

public class TestedClass {

	// before
	@BeforeSuite
	public void before1(){
		System.out.println("Запуск before1");
	}

	@BeforeSuite
	public static void before2(){
	System.out.println("Запуск before2");
			// Имитируем ошибку
//			int a;
//			String s = "asd";
//			a=Integer.parseInt(s);
	}

	// after
	@AfterSuite
	public static void after1(){
		System.out.println("Запуск after1");
	}

	@AfterSuite
	public static void after2(){
		System.out.println("Запуск after");
	}

	// test
	@Test(priority = 8)
	public void testPriority8_1(){
		System.out.println(" Запуск testPriority8_1");
	}

	@Test(priority = 8)
	public static void testPriority8_2(){
		System.out.println(" Запуск testPriority8_2");
	}

	@Test(priority = 1)
	private void testPriority1(){
		System.out.println(" Запуск testPriority1");
	}

	@Test(priority = 2)
	private static void testPriority2(){
		System.out.println(" Запуск testPriority2");
	}

	@Test
	private void testPriority5(){
		System.out.println(" Запуск testPriority5");
	}

	@Test(priority = 11)
	private void testPriority11(){
		System.out.println(" Запуск testPriority11");
	}

	// несколько аннотаций
	@Test
	@BeforeSuite
	public void testBefore(){
		System.out.println(" - Запуск testBefore");
	};

	// Before/After Test
	@BeforeTest
	public void beforeTest(){
		System.out.println(" / Запуск beforeTest");
	}
	@AfterTest
	public void afterTest(){
		System.out.println(" \\ Запуск afterTest");
	}


	// Для распарсивания
	@CsvSource("10, Java, 20, true")
	public void csvSource(int a, String b, int c, boolean d) {
			// Имитируем ошибку
//				int e;
//				String s = "asd";
//				e=Integer.parseInt(s);
		System.out.println( "a=" + a + System.lineSeparator() +
							"b=" + b + System.lineSeparator() +
							"c=" + c + System.lineSeparator() +
							"d=" + d);
	}


}