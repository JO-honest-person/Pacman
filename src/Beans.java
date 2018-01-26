import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Beans {
    private Boolean[][] beans = new Boolean[26][29];
    private Boolean[][] bigbeans = new Boolean[26][29];
    public void assignment(){
        for(int i = 0;i < 26;i++){
            for(int j = 0;j < 29;j++){
                beans[i][j] = false;
                bigbeans[i][j] = false;
            }
        }
    }
    public boolean firstdrawbean(int i ,int j,int r,int l)  {
        boolean whetherdraw = false;
        if (walk(r,l)) {
            beans[i][j] = true;
        }
        else{
            beans[i][j] = false;
        }
        if(r >= 216 && r <= 414 && l >= 260 && l <= 436){
            beans[i][j] = false;
        }
        if(l == 326 && r != 150 && r != 480){
            beans[i][j] = false;
        }
        if((r == 40 && l == 40) || (r == 40 && l == 194) || (r == 590 && l == 194) || (r == 590 && l == 458) || (r == 40 && l == 458)){
            beans[i][j] = false;
        }
        whetherdraw = beans[i][j];
        return whetherdraw;
    }
    // ///////////¸Õ¼ÓµÄ/
    public boolean firstdrawbigbean(int i ,int j,int r,int l){
        boolean whetherdraw = false;
        if ((r == 40 && l == 194) || (r == 590 && l == 194) || (r == 590 && l == 458) || (r == 40 && l == 458)) {
            bigbeans[i][j] = true;
        }
        else{
            bigbeans[i][j] = false;
        }
        whetherdraw = bigbeans[i][j];
        return whetherdraw;
    }
    public int getx(){
        return beans.length;
    }
    public int gety(){
        return beans[0].length;
    }
    public boolean getvalue(int i ,int j){
        return beans[i][j];
    }
    public boolean getvalueofbigbean(int i ,int j){
        return bigbeans[i][j];
    }
    public void prepaarefordraw(double i ,double j){
        if((i - 40)%22 == 0 && (j - 40)%22 == 0 &&  !(i == 18 && j == 326) && !(i == 612 && j == 326) ){
            if(beans[(int)((i-40)/22)][(int)((j-40)/22)]){
                Pacman.eatenbean += 1;
            }
            if(bigbeans[(int)((i-40)/22)][(int)((j-40)/22)]){
                Pacman.eatenbigbean += 1;
                Main.underbigbean = true;
                Main.stepunderbigbean = 0;
                Main.ghostmoveunderbigbean = 1;
            }
            beans[(int)((i-40)/22)][(int)((j-40)/22)] = false;
            bigbeans[(int)((i-40)/22)][(int)((j-40)/22)] = false;
        }
    }
    public void save(){
        File file1 = new File("beans.txt");
        try{
            PrintWriter output = new PrintWriter(file1);
            for(int i = 0;i < 26;i++){
                for(int j = 0;j < 29;j++){
                    if(beans[i][j]){
                        output.print(1);
                        output.print(" ");
                    }
                    else if(!beans[i][j]) {
                        output.print(0);
                        output.print(" ");
                    }
                }
            }
            output.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        File file2 = new File("bigbeans.txt");
        try{
            PrintWriter output = new PrintWriter(file2);
            for(int i = 0;i < 26;i++){
                for(int j = 0;j < 29;j++){
                    if(bigbeans[i][j]){
                        output.print(1);
                        output.print(" ");
                    }
                    else if(!bigbeans[i][j]) {
                        output.print(0);
                        output.print(" ");
                    }
                }
            }
            output.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
    public void load(){
        File file1 = new File("beans.txt");
        try
        {
            Scanner input = new Scanner(file1);
            for(int i = 0;i < 26;i++){
                for(int j = 0;j < 29;j++){
                    int n = input.nextInt();
                    if(n == 1){
                        beans[i][j] = true;
                    }
                    else {
                        beans[i][j] = false;
                    }
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
        File file2 = new File("bigbeans.txt");
        try
        {
            Scanner input = new Scanner(file2);
            for(int i = 0;i < 26;i++){
                for(int j = 0;j < 29;j++){
                    int n = input.nextInt();
                    if(n == 1){
                        bigbeans[i][j] = true;
                    }
                    else {
                        bigbeans[i][j] = false;
                    }
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
    private boolean walk(int x,int y){
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