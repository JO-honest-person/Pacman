import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Beans {
    private Boolean[][] beans = new Boolean[26][29];
    private Boolean[][] bigbeans = new Boolean[26][29];
    private Judge judge=new Judge();
    public void assignment(){
        for(int i = 0;i < 26;i++){
            for(int j = 0;j < 29;j++){
                beans[i][j] = false;
                bigbeans[i][j] = false;
            }
        }
    }
    //用于游戏初始化时画出豆子
    public boolean initialBean(int i ,int j,int r,int l)  {
        boolean whetherdraw = false;
        if (!judge.isWall(r,l)) {
            beans[i][j] = true;
        }
        else{
            beans[i][j] = false;
        }
        //在有难度的地方不生成豆子。
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
    //用于游戏初始化时画出大力丸
    public boolean initialBigBean(int i ,int j,int r,int l){
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
    public boolean getValue(int i ,int j){
        return beans[i][j];
    }
    public boolean getValueOfBigbean(int i ,int j){
        return bigbeans[i][j];
    }
    //判断是否吃了豆子并更新状态
    public void eatBean(double i ,double j){
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
        File file1 = new File("Data//beans.txt");
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
        File file2 = new File("Data//bigbeans.txt");
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
        File file1 = new File("Data//beans.txt");
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
        File file2 = new File("Data//bigbeans.txt");
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
}