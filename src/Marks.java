import java.util.Arrays;

public class Marks {

	public static Grade marksAverage(int mark1, int mark2, int mark3) throws InvalidMarkException {
		for (int m : Arrays.asList(mark1, mark2, mark3))
			if (m < 0 || m > 100)
				throw new InvalidMarkException(m);

		int avg;
		avg = (mark1 + mark2 + mark3) / 3; 
		
		//avg = (int) Math.round((mark1 + mark2 + mark3) / 3.0);
		
		return new Grade(avg);
	}
	
}
