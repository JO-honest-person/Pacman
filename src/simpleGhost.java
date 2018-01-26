import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class simpleGhost{
    private double x = 282,X = 282;
    private double y = 348,Y = 348;
    private int stepofbestuck = 10;
    private double lastx,lasty;
    private double v = 0.5;
    public  simpleGhost(double i,double j){
        x = i;
        X = i;
        y = j;
        Y = j;
    }
    public void move(){
        boolean ensuremove = true;
        if(stepofbestuck >= 10){
            while(ensuremove) {
                int move = (int)(Math.random()*4);
                if(move == 0) {
                    if (walk(x,y-(22*v) )){
                        y = y-(22*v);
                        ensuremove = false;
                    }
                }
                if(move == 1) {
                    if (walk(x,y+22*v)){
                        y = y + 22*v;
                        ensuremove = false;
                    }
                }
                if(move == 2) {
                    if (walk(x-22*v,y)){
                        x = x - 22*v;
                        ensuremove = false;
                    }
                }
                if(move == 3) {
                    if (walk(x+22*v,y)){
                        x = x + 22*v;
                        ensuremove = false;
                    }
                }
            }
        }
        stepofbestuck += 1;}
    public void setv(double i ){
        v = i;
    }
    /*private boolean assistmove(int a,double v){
        boolean result = true;
        entire:{
            for(;v > 0;v--){
                if(a == 0) {
                    if (!walk(x,y-(22*v) )){
                        result = false;
                        break entire;
                    }
                }
                if(a == 1) {
                    if (!walk(x,y+22*v)){
                        result = false;
                        break entire;
                    }
                }
                if(a == 2) {
                    if (!walk(x-22*v,y)){
                        result = false;
                        break entire;
                    }
                }
                if(a == 3) {
                    if (!walk(x+22*v,y)){
                        result = false;
                        break entire;
                    }
                }
            }
        }
        return result;
    }*/
    public void playagain(){
        stepofbestuck = 10;
    }
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
    public void setx(int x){
        this.x = x;
    }
    public void setn2y(int y){
        this.y = y;
    }
    public void record(){
        lastx = x;
        lasty = y;
    }
    public void save1(){
        File file = new File("simplenpc1.txt");
        try{
            PrintWriter output = new PrintWriter(file);
            output.print(x);
            output.print(" ");
            output.println(y);
            output.print(lastx);
            output.print(" ");
            output.println(lasty);
            output.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
    public void load1(){
        File file = new File("simplenpc1.txt");
        try
        {
            Scanner input = new Scanner(file);
            x = input.nextDouble();
            y = input.nextDouble();
            String s = input.nextLine();
            lastx = input.nextDouble();
            lasty = input.nextDouble();
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
    public void save2(){
        File file = new File("simplenpc2.txt");
        try{
            PrintWriter output = new PrintWriter(file);
            output.print(x);
            output.print(" ");
            output.println(y);
            output.print(lastx);
            output.print(" ");
            output.println(lasty);
            output.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
    public void load2(){
        File file = new File("simplenpc2.txt");
        try
        {
            Scanner input = new Scanner(file);
            x = input.nextDouble();
            y = input.nextDouble();
            String s = input.nextLine();
            lastx = input.nextDouble();
            lasty = input.nextDouble();
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
    public double getrecordx(){
        return lastx;
    }
    public double getrecordy(){
        return lasty;
    }
    public void getback(){
        x = X;
        y = Y;
        if(Main.underbigbean){
            Pacman.eatenghost += 1;
            stepofbestuck = 0;
        }
    }
    public double getv(){
        return v;
    }
    public boolean checkwhetherdie(double px,double py,double lpx,double lpy,double nx,double ny,double lnx,double lny){
        boolean die = false;
        if((px - nx<= 11&&px - nx >= -11)&&(py - ny <= 11 && py - ny >= -11))
            die = true;
        if( (px - nx >= -v*22 && px - nx <=v*22)&&(lpx - lnx >= -v*22 && lpx - lnx <=v*22) && py == ny && lpy == lny)
            die = true;
        if( (py - ny >= -v*22 && py - ny <=v*22)&&(lpy - lny >= -v*22 && lpy - lny <=v*22) && px == nx && lpx == lnx)
            die = true;
        return die;
    }
    private boolean walk(double x,double y){
        boolean result = false;
        if((x == 40) && ((y >= 40 && y <= 194) || (y == 326) || (y >= 458 && y <= 524) || (y >= 590 && y <= 656) ))
            result = true;
        if((x == 150) && ((y >= 40 && y <= 590) || (y == 656) ))
            result = true;
        if((x == 216) && ((y == 40) || (y >= 128 && y <= 194) || (y >= 260 && y <= 458) || (y >= 524 && y <= 590) || (y == 656) ))
            result = true;
        if((x == 282) && ((y >= 40 && y <= 128) || (y >= 194 && y <= 260) || (y >= 326 && y <= 348) || (y == 392) || (y >= 458 && y <= 524 ) || (y >= 590 && y <=656 ) ))
            result = true;
        if((x == 304 || x == 326)&&((y == 128) || (y == 260) || (y == 392) || (y == 524) || (y == 656)))
            result = true;
        if((x == 348) && ((y >= 40 && y <= 128) || (y >= 194 && y <= 260) || (y >= 326 && y <= 348) || (y == 392) || (y >= 458 && y <= 524 ) || (y >= 590 && y <=656 ) ))
            result = true;
        if((x == 414) && ((y == 40) || (y >= 128 && y <= 194) || (y >= 260 && y <= 458) || (y >= 524 && y <= 590) || (y == 656) ))
            result = true;
        if((x == 480) && ((y >= 40 && y <= 590) || (y == 656) ))
            result = true;
        if((x == 590) && ((y >= 40 && y <= 194) || (y == 326) || (y >= 458 && y <= 524) || (y >= 590 && y <= 656) ))
            result = true;
        if((y == 40) && ((x >= 40 && x <= 282) || (x >= 348 && x <= 590)))
            result = true;
        if((y == 128) && (x >= 40 && x <= 590))
            result = true;
        if((y == 194) && ((x >= 40 && x <= 150) || (x >= 216 && x <= 282) || (x >= 348 && x <= 414) || (x >= 480 && x <= 590)))
            result = true;
        if((y == 260) && ((x >= 216 && x <= 414)))
            result =true;
        if((y == 326) && ((x >= 18 && x <= 216) || (x >= 282 && x <= 348) || (x >= 414 && x <= 612)))
            result = true;
        if((y == 392) && ((x >= 216 && x <= 414)))
            result =true;
        if((y == 458) && ((x >= 40 && x <= 282) || ( x >= 348 && x <= 590)))
            result = true;
        if((y == 524) && ((x >= 40 && x <= 84) || (x >= 150 && x <= 480) || (x >= 546 && x <= 590)))
            result = true;
        if((y == 590) && ((x >= 40 && x <= 150) || (x >= 216 && x <= 282) || (x >= 348 && x <= 414) || (x >= 480 && x <= 590)))
            result = true;
        if((y == 656) && (x >= 40 && x <= 590))
            result = true;
        if((x == 84 || x == 546) && (y == 546 || y == 568))
            result = true;
        if((x == 304 || x == 326) && (y == 282 || y == 304 || y == 348 ))
            result = true;
        return result;
    }
}