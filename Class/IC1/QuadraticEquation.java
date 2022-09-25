import java.io.*;
import java.util.Scanner;

public class QuadraticEquation
{
     
    private int a;
    private int b;
    private int c;
    
    //constructors
    public QuadraticEquation ()
    {
        a = 0;
        b = 0;
        c = 0;
    }
    
    public QuadraticEquation(int givenA, int givenB, int givenC)
    {
        a = givenA;
        b = givenB;
        c = givenC;
    }
    
    //getters
    public int getA (){return a;}
    public int getB (){return b;}
    public int getC (){return c;}
    
    public double getDiscriminant()
    {
        double d = (b*b) - (4*a*c);
        
        return d;
    }
    
    public double getRoot1 ()
    {
        double r = 0;
        if(getDiscriminant() < 0)
        {
            return 0;
        }
        else
        {
            r = (-b + Math.sqrt(getDiscriminant()))/2*a;
            return r;
        }
        
    }
    
    public double getRoot2()
    {
        double r2 = 0;
        if(getDiscriminant() < 0)
        {
            return 0;
        }
        else
        {
            r2 = (-b - Math.sqrt(getDiscriminant()))/2*a;
            return r2;
        }
    }
    
    //
    
}