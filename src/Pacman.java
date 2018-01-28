import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pacman {
    private double x = 40,X = 40;
    private double y = 40,Y = 40;
    private double lastx,lasty;
    private double v = 0.5;
    static int live = 3;
    static int eatenbean = 0;
    static int eatenbigbean = 0;
    static int eatenghost = 0;
    public double getX(){
        return X;
    }
    public double getY(){
        return Y;
    }
    public double getx(){
        return x;
    }
    public double gety(){
        return y;
    }
    public void setx(double x){
        this.x = x;
    }
    public void sety(double y){
        this.y = y;
    }
    public void record(){
        lastx = x;
        lasty = y;
    }
    public double getrecordx(){
        return lastx;
    }
    public double getrecordy(){
        return lasty;
    }
    public void getback(){
        live -= 1;
        x = X;
        y = Y;
    }
    public void playagain(){
        live = 3;
        eatenbean = 0;
        eatenbigbean = 0;
        eatenghost = 0;
    }
    public void save(){
        File file = new File("Data//pacman.txt");
        try{
            PrintWriter output = new PrintWriter(file);
            output.print(x);
            output.print(" ");
            output.println(y);
            output.print(lastx);
            output.print(" ");
            output.println(lasty);
            output.println(live);
            output.println(eatenbean);
            output.println(eatenbigbean);
            output.println(eatenghost);
            output.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
    public void load(){
        File file = new File("Data//pacman.txt");
        try
        {
            Scanner input = new Scanner(file);
            x = input.nextDouble();
            y = input.nextDouble();
            String s1 = input.nextLine();
            lastx = input.nextDouble();
            lasty = input.nextDouble();
            String s2 = input.nextLine();
            live = input.nextInt();
            String s3 = input.nextLine();
            eatenbean = input.nextInt();
            String s4 = input.nextLine();
            eatenbigbean = input.nextInt();
            String s5 = input.nextLine();
            eatenghost = input.nextInt();
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
    public double getV(){
        return v;
    }
}