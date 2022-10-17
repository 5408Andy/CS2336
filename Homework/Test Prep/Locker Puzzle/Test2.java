public class Test2 {
    
    public static void main(String[] args)
	{
		boolean[] lockers = new boolean[100];
		for(int i=0;i<100;i++) lockers[i] = true;			// student 1 opens all lockers to start
		for(int student=2;student<=100;student++)			// students 2-100 take turns
			for(int locker=student-1;locker<100;locker=locker+student)		// for each locker, notice we start at student-1 since lockers are actually 0-99
				if(lockers[locker]) lockers[locker]=false;	// change lockers[locker] (open if closed, close if opened)
					else lockers[locker] = true;
		for(int i=0;i<100;i++) {					// output the results
			
            if (lockers[i] == true)
                System.out.println((i+1) + " " + lockers[i]);

        }
	}

}
