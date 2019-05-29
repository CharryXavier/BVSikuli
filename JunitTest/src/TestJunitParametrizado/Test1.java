package TestJunitParametrizado;



import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;



@RunWith (value = Parameterized.class)	

public class Test1 {	

	private String Mundo;
    public Test1(String Mundo, int Javier)	{
	
	this.Mundo = Mundo;
}

@Parameters

public static Collection<Object[]>data() {
	
	Object[][] data= new Object[][] {{"Mundo",1 },{"Javier",2}};
	return Arrays.asList(data);	
}

	@Test
	public void test() {
		System.out.println("Hola "+ Mundo );

	}

}
