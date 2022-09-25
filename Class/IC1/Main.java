/******************************************************************************

Evelyn Maxson EGM200005
Andy Nguyen ADN200004
Evelyn did the parsing and typing/main

Andy did the class for Quadratic Equation

Both verbally worked through code together.

*******************************************************************************/
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		String equation;
		String aString;
		int a;
		String bString;
		int b;
		String cString;
		int c;
		int position;
		int position2;
		equation = input.next();
		
		//System.out.println(equation);
		
		position = equation.indexOf('^');
		aString = equation.substring(0, position - 1);
		//System.out.println(aString);
		
		position2 = equation.indexOf('+');
		if(position2 != -1)
		{
		    equation = equation.substring(position2+1);
		}
		else
		{
		    position2 = equation.indexOf('-');
		    equation = equation.substring(position2);
		}
		
		
		//System.out.println(equation);
		
		position2 = equation.indexOf('x');
		//equation = equation.substring(0, position2 );
		bString = equation.substring(0, position2);
		System.out.println(bString);
		
		equation = equation.substring(position2);
		position2 = equation.indexOf('+');
		if(position2 != -1)
		{
		    equation = equation.substring(position2+1);
		}
		else
		{
		    position2 = equation.indexOf('-');
		    equation = equation.substring(position2);
		}
		
		cString = equation;
		//System.out.println(cString);
		
		a = Integer.parseInt(aString);
		System.out.println(a);
		b = Integer.parseInt(bString);
		System.out.println(b);
		c = Integer.parseInt(cString);
		System.out.println(c);
		
		QuadraticEquation eq1 = new QuadraticEquation(a, b, c);
		System.out.println(eq1.getRoot1());
		System.out.println(eq1.getRoot2());
		
		
		
		
	}
}
