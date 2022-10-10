

import org.testng.annotations.Test;

public class Priority {
	
	
	
	
	
	
	
	
	@Test (priority = 2)
	public void testcase1() {
		System.out.println("day3");
	}
	@Test (priority = 3)
public void testcase2() {
	System.out.println("day4");
	}
	@Test (priority = 0)
public void testcase3() {
	System.out.println("day1");
}
	@Test (priority = 1)
public void testcase4() {
	System.out.println("day2");
}


}
