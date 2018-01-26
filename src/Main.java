import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
public class Main extends Application {
    //用于播放音乐
    private boolean softmusic = false;
    private boolean violencemusic = false;
    //确保第二次可以正常开始
    private int forsecondplay = 0;
    private boolean playorpause = true;
    private Timer timer;
    private ImageView weakghost1;
    private ImageView weakghost2;
    private ImageView StrongGhost1;
    private ImageView StrongGhost2;
    private ImageView volghostview1;
    private ImageView volghostview2;
    private ImageView volghostview3;
    private ImageView volghostview4;
    private ImageView pacmanview;
    private ImageView live1view;
    private ImageView live2view;
    private ImageView live3view;
    private ImageView gameoverview;
    private Text score;
    static boolean underbigbean = false;
    static int stepunderbigbean = 0;
    static int ghostmoveunderbigbean = 1;
    static int move,dir;//用于移动。
    //用于排行榜
    private Label label11,label21,label31,label4,label5;
    private int rank1 = -1;
    private int helprank = 4;
    String[] name = new String[5];
    int[] overscore = new int[5];
    //新建一些对象.
    Beans bean = new Beans();
    simpleGhost simplenpc1 = new simpleGhost(282.0,260);
    simpleGhost simplenpc2 = new simpleGhost(304,260);
    strongGhost strongghost1 = new strongGhost(304,260);
    strongGhost strongghost2 = new strongGhost(282,260);
    Pacman pacman = new Pacman();
    Music music = new Music();
    Circle[][] circles = new Circle[26][29];//用于储存豆子。画豆子。
    //用于播放音乐
    public void start(Stage primarystage) {
        //开始界面
        Pane beginpane = new Pane();
        Scene beginscene = new Scene(beginpane,670,300);
        ImageView back = new ImageView(new Image("Resources\\11.png"));back.setFitHeight(300);back.setFitWidth(300);
        ImageView n1 = new ImageView(new Image("Resources\\21.png"));n1.setFitHeight(40);n1.setFitWidth(40);
        ImageView n2 = new ImageView(new Image("Resources\\22.png"));n2.setFitHeight(40);n2.setFitWidth(40);
        ImageView n3 = new ImageView(new Image("Resources\\23.png"));n3.setFitHeight(40);n3.setFitWidth(40);
        ImageView n4 = new ImageView(new Image("Resources\\24.png"));n4.setFitHeight(40);n4.setFitWidth(40);
        ImageView setbackground = new ImageView(new Image("Resources\\help.jpg"));
        setbackground.setX(-1000);setbackground.setY(-1000);setbackground.setFitHeight(200);setbackground.setFitWidth(200);
        ImageView setting = new ImageView(new Image("Resources\\pacman 12.gif"));setting.setX(650);setting.setY(5);
        Image wood = new Image("Resources\\31.png");
        ImageView n1b = new ImageView(wood);n1b.setFitHeight(50);n1b.setFitWidth(180);n1b.setX(300);n1b.setY(20);
        ImageView n2b = new ImageView(wood);n2b.setFitHeight(50);n2b.setFitWidth(220);n2b.setX(300);n2b.setY(80);
        ImageView n3b = new ImageView(wood);n3b.setFitHeight(60);n3b.setFitWidth(390);n3b.setX(300);n3b.setY(200);
        ImageView n4b = new ImageView(wood);n4b.setFitHeight(50);n4b.setFitWidth(250);n4b.setX(300);n4b.setY(140);
        TextField sv = new TextField();sv.setLayoutX(-1000);sv.setLayoutY(-1000);
        TextField wv = new TextField();wv.setLayoutX(-1000);wv.setLayoutY(-1000);
        Text st = new Text("The velocity of the strongghost:");st.setX(-1000);st.setY(-1000);
        Text wt = new Text("The velocity of the weakghost:");wt.setX(-1000);wt.setY(-1000);
        Button vok = new Button("OK"); vok.setLayoutX(-1000);vok.setLayoutY(-1000);
        Label label1 = new Label("Play",n1);
        label1.setContentDisplay(ContentDisplay.RIGHT);
        label1.setTextFill(Color.BROWN);
        label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        label1.setOnMouseEntered(e -> {
            label1.setScaleX(1.5);
            label1.setScaleY(1.2);
        });
        label1.setOnMouseExited(e -> {
            label1.setScaleX(1);
            label1.setScaleY(1);
        });
        Label label2 = new Label("Help",n2);
        label2.setContentDisplay(ContentDisplay.RIGHT);
        label2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        label2.setOnMouseEntered(e -> {
            label2.setScaleX(1.5);
            label2.setScaleY(1.2);
        });
        label2.setOnMouseExited(e -> {
            label2.setScaleX(1);
            label2.setScaleY(1);
        });
        Label label3 = new Label("Ranking List",n3);
        label3.setContentDisplay(ContentDisplay.RIGHT);
        label3.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        label3.setOnMouseEntered(e -> {
            label3.setScaleX(1.5);
            label3.setScaleY(1.2);
        });
        label3.setOnMouseExited(e -> {
            label3.setScaleX(1);
            label3.setScaleY(1);
        });
        Label label9 = new Label("Continue",n4);
        label9.setContentDisplay(ContentDisplay.RIGHT);
        label9.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        label9.setOnMouseEntered(e -> {
            label9.setScaleX(1.5);
            label9.setScaleY(1.2);
        });
        label9.setOnMouseExited(e -> {
            label9.setScaleX(1);
            label9.setScaleY(1);
        });
        label1.setLayoutX(300);label1.setLayoutY(20);
        label2.setLayoutX(300);label2.setLayoutY(80);
        label3.setLayoutX(300);label3.setLayoutY(200);
        label9.setLayoutX(300);label9.setLayoutY(140);
        setting.setOnMouseClicked(e -> {
            setbackground.setX(300);setbackground.setY(120);
            st.setX(310);st.setY(140);
            sv.setLayoutX(310);sv.setLayoutY(150);
            wt.setX(310);wt.setY(200);
            wv.setLayoutX(310);wv.setLayoutY(210);
            vok.setLayoutX(370);vok.setLayoutY(270);
        });
        vok.setOnMouseClicked(e ->{
            simplenpc1.setv(Double.parseDouble(wv.getText()));
            simplenpc2.setv(Double.parseDouble(wv.getText()));
            strongghost1.setv(Double.parseDouble(sv.getText()));
            strongghost2.setv(Double.parseDouble(sv.getText()));
            setbackground.setX(-1000);setbackground.setY(-1000);
            sv.setLayoutX(-1000);sv.setLayoutY(-1000);
            wv.setLayoutX(-1000);wv.setLayoutY(-1000);
            st.setX(-1000);st.setY(-1000);
            wt.setX(-1000);wt.setY(-1000);
            vok.setLayoutX(-1000);vok.setLayoutY(-1000);
        });
        beginpane.getChildren().addAll(n1b,n2b,n3b,n4b,back,label1,label2,label3,label9,setting,setbackground,sv,wv,vok,st,wt);
        //帮助界面
        Pane helepane = new Pane();
        Scene helpscene = new Scene(helepane);
        ImageView helpview = new ImageView(new Image("Resources\\help.jpg"));
        ImageView backup = new ImageView(new Image("Resources\\row.jpg"));
        helpview.setFitWidth(310);helpview.setFitHeight(240);
        backup.setX(5);backup.setY(5);backup.setFitHeight(50);backup.setFitWidth(75);
        Label helpleble = new Label(); helpleble.setLayoutY(60);
        Text helptext = new Text("H e l p");helptext.setX(90);helptext.setY(40);
        helpleble.setText("   Use W S A D to control the moving of  pacman.\n If you don't want to move,just type the space. Wh\n en you eat the red beans, you can  eat the ghost " +
                "\n Every bean you eat ,you will get  10 ,and red bean \n or ghost 100. You can enter the list of the top five \n scores\nThe Pacman on the top right corner can set" +
                " the\n speed of ghost.You should enter the number betw\n een 0 and 1");
        helpleble.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        helptext.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40));
        helepane.getChildren().addAll(helpview,helptext,helpleble,backup);
        label2.setOnMouseClicked(e -> {
            primarystage.setScene(helpscene);
        });
        backup.setOnMouseClicked(e -> {
            primarystage.setScene(beginscene);
        });
        //排行榜
        Pane rankpane = new Pane();
        Scene rankscene = new Scene(rankpane);
        ImageView row = new ImageView(new Image("Resources\\row.jpg"));
        row.setX(5);row.setY(5);row.setFitHeight(50);row.setFitWidth(75);
        Text text = new Text("Ranking List");text.setFont(Font.font(30));text.setX(100);text.setY(30);
        label11 = new Label();label11.setLayoutX(70);label11.setLayoutY(80);
        ImageView imageView11 = new ImageView(new Image("Resources\\71.png"));imageView11.setFitWidth(180);imageView11.setFitHeight(30);imageView11.setX(70);imageView11.setY(80);
        label21 = new Label();label21.setLayoutX(70);label21.setLayoutY(120);
        ImageView imageView12 = new ImageView(new Image("Resources\\71.png"));imageView12.setFitWidth(160);imageView12.setFitHeight(30);imageView12.setX(70);imageView12.setY(120);
        label31 = new Label();label31.setLayoutX(70);label31.setLayoutY(160);
        ImageView imageView13 = new ImageView(new Image("Resources\\71.png"));imageView13.setFitWidth(140);imageView13.setFitHeight(30);imageView13.setX(70);imageView13.setY(160);
        label4 = new Label();label4.setLayoutX(70);label4.setLayoutY(200);
        ImageView imageView14 = new ImageView(new Image("Resources\\71.png"));imageView14.setFitWidth(120);imageView14.setFitHeight(30);imageView14.setX(70);imageView14.setY(200);
        label5 = new Label();label5.setLayoutX(70);label5.setLayoutY(240);
        ImageView imageView15 = new ImageView(new Image("Resources\\71.png"));imageView15.setFitWidth(100);imageView15.setFitHeight(30);imageView15.setX(70);imageView15.setY(240);
        ImageView backimageView = new ImageView(new Image("Resources\\81.jpg"));backimageView.setFitHeight(300);backimageView.setFitWidth(360);
        rankpane.getChildren().addAll(backimageView,text,row,imageView11, label11,imageView12, label21,imageView13, label31,imageView14, label4,imageView15, label5);
        row.setOnMouseClicked(e -> {
            primarystage.setScene(beginscene);
        });
        label3.setOnMouseClicked(e -> {
            read();
            show();
            primarystage.setScene(rankscene);
        });
        //新建排行榜
        Pane enterpane = new Pane();
        Scene enterscene = new Scene(enterpane);
        ImageView winview = new ImageView(new Image("Resources\\wictory.gif"));winview.setFitWidth(400);winview.setFitHeight(400);
        TextField tf = new TextField();tf.setLayoutX(100);tf.setLayoutY(350);
        Button add = new Button("OK");add.setLayoutX(230);add.setLayoutY(350);
        Text wictory = new Text("W I C T O R Y");
        wictory.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40));
        wictory.setFill(Color.PINK);
        wictory.setX(100);wictory.setY(120);
        enterpane.getChildren().addAll(winview,tf,add,wictory);
        add.setOnMouseClicked(e -> {
            read();
            String s = tf.getText();
            checkthescore(s);
            show();
            primarystage.setScene(rankscene);
            rank1 = -1;
            helprank = 4;
        });
        //游戏界面
        Pane pane = new Pane();
        Scene scene = new Scene(pane,880,700);
        ImageView background = new ImageView(new Image("Resources\\background.jpg"));
        Image playagainimage = new Image("Resources\\play again.jpg");
        Image pacmanimage = new Image("Resources\\pacman 00.gif");
        Image weakghostimage = new Image("Resources\\weak ghost.gif");
        Image StrongGhostimage = new Image("Resources\\strong ghost.gif");
        Image volghostimage = new Image("Resources\\vol ghost.gif");
        volghostview1 = new ImageView(volghostimage);volghostview1.setX(-1000);volghostview1.setY(-1000);
        volghostview2 = new ImageView(volghostimage);volghostview2.setX(-1000);volghostview2.setY(-1000);
        volghostview3 = new ImageView(volghostimage);volghostview3.setX(-1000);volghostview3.setY(-1000);
        volghostview4 = new ImageView(volghostimage);volghostview4.setX(-1000);volghostview4.setY(-1000);
        //设置背景。
        pane.getChildren().add(background);
        ImageView playagain = new ImageView(playagainimage);playagain.setFitHeight(60);playagain.setFitWidth(230);playagain.setX(650);playagain.setY(110);
        //画出豆子。
        bean.assignment();
        for(int i = 0, r = 40;i<bean.getx();i++){
            for(int j = 0,l = 40; j < bean.gety();j++){
                //豆子
                if( bean.firstdrawbean(i, j, r, l) ){
                    circles[i][j] = new Circle();
                    circles[i][j].setRadius(4);
                    circles[i][j].setStroke(Color.YELLOW);
                    circles[i][j].setFill(Color.YELLOW);
                    circles[i][j].setCenterX(r + 10);
                    circles[i][j].setCenterY(l + 10);
                    pane.getChildren().add(circles[i][j]);
                }
                //大力丸
                else if(bean.firstdrawbigbean(i,j,r,l)){
                    circles[i][j] = new Circle();
                    circles[i][j].setRadius(8);
                    circles[i][j].setStroke(Color.ROSYBROWN);
                    circles[i][j].setFill(Color.RED);
                    circles[i][j].setCenterX(r + 10);
                    circles[i][j].setCenterY(l + 10);
                    pane.getChildren().add(circles[i][j]);
                }
                //空白
                else{
                    circles[i][j] = new Circle();
                    circles[i][j].setRadius(-1);
                    circles[i][j].setStroke(Color.YELLOW);
                    circles[i][j].setFill(Color.YELLOW);
                    circles[i][j].setCenterX(r + 10);
                    circles[i][j].setCenterY(l + 10);
                    pane.getChildren().add(circles[i][j]);
                }
                l = l + 22;
            }
            r = r + 22;
        }
        //pacman的初始位置设置
        pacmanview = new ImageView(pacmanimage);
        pacmanview.setX(pacman.getX());
        pacmanview.setY(pacman.getY());
        //npc的初始位置的设置
        weakghost1 = new ImageView(weakghostimage);
        weakghost1.setX(simplenpc1.getX());
        weakghost1.setY(simplenpc1.getY());
        weakghost2 = new ImageView(weakghostimage);
        weakghost2.setX(simplenpc2.getX());
        weakghost2.setY(simplenpc2.getY());
        StrongGhost1 = new ImageView(StrongGhostimage);
        StrongGhost1.setX(strongghost1.getX());
        StrongGhost1.setY(strongghost1.getY());
        StrongGhost2 = new ImageView(StrongGhostimage);
        StrongGhost2.setX(strongghost2.getX());
        StrongGhost2.setY(strongghost2.getY());
        //选择音乐
        ImageView choosebackview = new ImageView(new Image("Resources\\score background.jpg"));
        choosebackview.setX(655);
        choosebackview.setY(170);
        choosebackview.setFitHeight(70);
        Text choosemusic = new Text("Choose Music");
        choosemusic.setFont(Font.font("Verdana",18));
        choosemusic.setFill(Color.YELLOW);
        choosemusic.setX(700);choosemusic.setY(190);
        VBox vBox = new VBox(5);
        RadioButton soft = new RadioButton("soft");
        RadioButton violence = new RadioButton("violence");
        vBox.getChildren().addAll(soft, violence);
        ToggleGroup group = new ToggleGroup();
        soft.setToggleGroup(group);
        violence.setToggleGroup(group);
        vBox.setLayoutX(720);vBox.setLayoutY(200);
        soft.setOnMouseClicked(e -> {
            if(violencemusic){
                music.mediaPlayer2.stop();
            }
            music.mediaPlayer1.seek(Duration.ZERO);
            music.mediaPlayer1.play();softmusic = true;
            pacmanview.requestFocus();
        });
        violence.setOnMouseClicked(e -> {
            if(softmusic){
                music.mediaPlayer1.stop();
            }
            music.mediaPlayer2.seek(Duration.ZERO);
            music.mediaPlayer2.play();violencemusic = true;
            pacmanview.requestFocus();
        });
        //存储游戏
        Button save = new Button("Save");
        save.setStyle("-fx-background-color: pink");
        save.setFont(Font.font("Verdana",30));
        save.setLayoutX(710);save.setLayoutY(260);
        save.setOnMouseClicked(e -> {
            timer.cancel();
            simplenpc1.save1();
            simplenpc2.save2();
            strongghost1.save1();
            strongghost2.save2();
            pacman.save();
            bean.save();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            Stage s = new Stage();
            File file;
            file = fileChooser.showSaveDialog(s);
            if (file == null) return;
            try {
                PrintWriter output = new PrintWriter(file);
                if (underbigbean) {
                    output.print(1);
                } else if (!underbigbean) {
                    output.print(0);
                }
                output.print(" ");
                output.print(stepunderbigbean);
                output.print(" ");
                output.print(ghostmoveunderbigbean);
                output.close();
            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
            }

        });
        //游戏结束背景
        Image gameoverimage = new Image("Resources\\game over.jpg");
        gameoverview = new ImageView(gameoverimage);
        gameoverview.setFitWidth(655);
        gameoverview.setFitHeight(720);
        gameoverview.setX(-1000);gameoverview.setY(-1000);
        //画出生命值
        Image live1image = new Image("Resources\\life1.jpeg");
        Image live2image = new Image("Resources\\life2.jpeg");
        Image live3image = new Image("Resources\\life3.jpeg");
        live1view = new ImageView(live1image);
        live2view = new ImageView(live2image);
        live3view = new ImageView(live3image);
        live1view.setX(-1000);
        live1view.setY(-1000);
        live2view.setX(-1000);
        live2view.setY(-1000);
        live3view.setX(655);
        live3view.setY(0);
        //显示分数
        Image scoreimage = new Image("Resources\\score background.jpg");
        ImageView scoreview = new ImageView(scoreimage);
        scoreview.setX(655);
        scoreview.setY(65);
        scoreview.setFitHeight(50);
        score = new Text(655,100,"Score :" + (pacman.eatenbean * 10 + (pacman.eatenbigbean + pacman.eatenghost) * 100));
        score.setFont(Font.font("Verdana",30));
        score.setFill(Color.YELLOW);
        //暂停游戏。
        Image pause = new Image("Resources\\pause.png");
        ImageView pauseviwe = new ImageView(pause);
        pauseviwe.setX(0);pauseviwe.setY(0);pauseviwe.setFitWidth(30);pauseviwe.setFitHeight(30);
        Image play = new Image("Resources\\play.jpg");
        ImageView playview = new ImageView(play);
        playview.setX(-1000);playview.setY(-1000);
        pauseviwe.setOnMouseClicked(e -> {
            playorpause = false;
            playview.setX(220);
            playview.setY(250);
        });
        playview.setOnMouseClicked(e -> {
            playview.setY(-1000);playview.setX(-1000);
            playorpause = true;
        });
        pane.getChildren().addAll(playagain,pacmanview,weakghost1,weakghost2,StrongGhost1,StrongGhost2,choosebackview, choosemusic,vBox,save,gameoverview,volghostview1,volghostview2,volghostview3,volghostview4,live3view,live1view,live2view,scoreview,score,pauseviwe,playview);
        //移动
        pacmanview.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:{
                    move = 0;
                    win:
                    {
                        for (int i = 0; i < bean.getx(); i++) {
                            for (int j = 0; j < bean.gety(); j++) {
                                if (bean.getvalue(i,j)) {
                                    break win;
                                }
                            }
                        }
                        primarystage.setScene(enterscene);
                        timer.cancel();
                    }}break;
                case S:
                {
                    move = 1;
                    win:
                    {
                        for (int i = 0; i < bean.getx(); i++) {
                            for (int j = 0; j < bean.gety(); j++) {
                                if (bean.getvalue(i,j)) {
                                    break win;
                                }
                            }
                        }
                        primarystage.setScene(enterscene);
                        timer.cancel();
                    }}break;
                case A:
                {
                    move = 2;
                    win:
                    {
                        for (int i = 0; i < bean.getx(); i++) {
                            for (int j = 0; j < bean.gety(); j++) {
                                if (bean.getvalue(i,j)) {
                                    break win;
                                }
                            }
                        }
                        primarystage.setScene(enterscene);
                        timer.cancel();
                    }}break;
                case D:
                {
                    move = 3;
                    win:
                    {
                        for (int i = 0; i < bean.getx(); i++) {
                            for (int j = 0; j < bean.gety(); j++) {
                                if (bean.getvalue(i,j)) {
                                    break win;
                                }
                            }
                        }
                        primarystage.setScene(enterscene);
                        timer.cancel();
                    }}break;
                case SPACE:{move = 4;}break;
            }
            //重玩.
            playagain.setOnMouseClicked(a -> {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new move(),3000,50);
                gameoverview.setX(-1000);gameoverview.setY(-1000);
                pacman.getback();
                pacmanview.setX(pacman.getX());
                pacmanview.setY(pacman.getY());
                bean.assignment();
                for(int i = 0, r = 40;i<bean.getx();i++){
                    for(int j = 0,l = 40; j < bean.gety();j++){
                        //豆子
                        if( bean.firstdrawbean(i, j, r, l) ){
                            circles[i][j].setRadius(4);
                            circles[i][j].setCenterX(r + 10);
                            circles[i][j].setCenterY(l + 10);
                            // pane.getChildren().add(circles[i][j]);
                        }
                        //大力丸
                        else if(bean.firstdrawbigbean(i,j,r,l)){
                            circles[i][j].setRadius(8);
                            circles[i][j].setCenterX(r + 10);
                            circles[i][j].setCenterY(l + 10);
                            // pane.getChildren().add(circles[i][j]);
                        }
                        //空白
                        else{
                            circles[i][j].setRadius(-1);
                            circles[i][j].setCenterX(r + 10);
                            circles[i][j].setCenterY(l + 10);
                            // pane.getChildren().add(circles[i][j]);
                        }
                        l = l + 22;
                    }
                    r = r + 22;
                }

                simplenpc1.getback();
                simplenpc2.getback();
                strongghost1.getback();
                strongghost2.getback();
                weakghost1.setX(simplenpc1.getx());weakghost1.setY(simplenpc1.gety());
                volghostview1.setX(-1000);volghostview1.setY(-1000);
                weakghost2.setX(simplenpc2.getx());weakghost2.setY(simplenpc2.gety());
                volghostview2.setX(-1000);volghostview2.setY(-1000);
                StrongGhost1.setX(strongghost1.getx());StrongGhost1.setY(strongghost1.gety());
                volghostview3.setX(-1000);volghostview3.setY(-1000);
                StrongGhost2.setX(strongghost2.getx());StrongGhost2.setY(strongghost2.gety());
                volghostview4.setX(-1000);volghostview4.setY(-1000);
                live1view.setX(-1000);
                live1view.setY(-1000);
                live2view.setX(-1000);
                live2view.setY(-1000);
                live3view.setX(655);
                live3view.setY(0);
                underbigbean = false;stepunderbigbean = 0;ghostmoveunderbigbean = 1;
                pacman.playagain();simplenpc1.playagain();simplenpc2.playagain();strongghost1.playagain();strongghost2.playagain();
                score.setText("Score :" + (pacman.eatenbean * 10 + (pacman.eatenbigbean + pacman.eatenghost) * 100));
            });
        });
        //继续游戏
        label9.setOnMouseClicked(a -> {
            simplenpc1.load1();
            simplenpc2.load2();
            strongghost1.load1();
            strongghost2.load2();
            bean.load();
            pacman.load();
            for (int i = 0, r = 40; i < bean.getx(); i++) {
                for (int j = 0, l = 40; j < bean.gety(); j++) {
                    //豆子
                    if (bean.getvalue(i, j)) {
                        circles[i][j].setRadius(4);
                        circles[i][j].setCenterX(r + 10);
                        circles[i][j].setCenterY(l + 10);
                        // pane.getChildren().add(circles[i][j]);
                    }
                    //大力丸
                    else if (bean.getvalueofbigbean(i, j)) {
                        circles[i][j].setRadius(8);
                        circles[i][j].setCenterX(r + 10);
                        circles[i][j].setCenterY(l + 10);
                        // pane.getChildren().add(circles[i][j]);
                    }
                    //空白
                    else {
                        circles[i][j].setRadius(-1);
                        circles[i][j].setCenterX(r + 10);
                        circles[i][j].setCenterY(l + 10);
                        // pane.getChildren().add(circles[i][j]);
                    }
                    l = l + 22;
                }
                r = r + 22;
            }
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            Stage s = new Stage();
            File file;
            file = fileChooser.showOpenDialog(s);
            if (file == null) return;
            try {
                Scanner input = new Scanner(file);
                int n = input.nextInt();
                if (n == 1) {
                    underbigbean = true;
                } else if (n == 0) {
                    underbigbean = false;
                }
                stepunderbigbean = input.nextInt();
                ghostmoveunderbigbean = input.nextInt();
            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
            }
            pacmanview.setX(pacman.getX());
            pacmanview.setY(pacman.getY());
            if (underbigbean) {
                weakghost1.setX(-1000);
                weakghost1.setY(-1000);
                volghostview1.setX(simplenpc1.getx());
                volghostview1.setY(simplenpc1.gety());
                weakghost2.setX(-1000);
                weakghost2.setY(-1000);
                volghostview2.setX(simplenpc2.getx());
                volghostview2.setY(simplenpc2.gety());
                StrongGhost1.setX(-1000);
                StrongGhost1.setY(-1000);
                volghostview3.setX(strongghost1.getx());
                volghostview3.setY(strongghost1.gety());
                StrongGhost2.setX(-1000);
                StrongGhost2.setY(-1000);
                volghostview4.setX(strongghost2.getx());
                volghostview4.setY(strongghost2.gety());
            }
            if (!underbigbean) {
                weakghost1.setX(simplenpc1.getx());
                weakghost1.setY(simplenpc1.gety());
                volghostview1.setX(-1000);
                volghostview1.setY(-1000);
                weakghost2.setX(simplenpc2.getx());
                weakghost2.setY(simplenpc2.gety());
                volghostview2.setX(-1000);
                volghostview2.setY(-1000);
                StrongGhost1.setX(strongghost1.getx());
                StrongGhost1.setY(strongghost1.gety());
                volghostview3.setX(-1000);
                volghostview3.setY(-1000);
                StrongGhost2.setX(strongghost2.getx());
                StrongGhost2.setY(strongghost2.gety());
                volghostview4.setX(-1000);
                volghostview4.setY(-1000);
            }
            if (pacman.live == 2) {
                live3view.setX(-1000);
                live3view.setY(-1000);
                live2view.setX(655);
                live2view.setY(0);
            }
            if (pacman.live == 1) {
                live2view.setX(-1000);
                live2view.setY(-1000);
                live1view.setX(655);
                live1view.setY(0);
            }
            score.setText("Score :" + (pacman.eatenbean * 10 + (pacman.eatenbigbean + pacman.eatenghost) * 100));
            timer = new Timer();
            timer.schedule(new move(), 3000, 50);
            primarystage.setScene(scene);
        });
        //开始游戏
        label1.setOnMouseClicked(e -> {
            if(forsecondplay > 0 ){
                pacman.getback();
                pacmanview.setX(pacman.getX());
                pacmanview.setY(pacman.getY());
                bean.assignment();
                for(int i = 0, r = 40;i<bean.getx();i++){
                    for(int j = 0,l = 40; j < bean.gety();j++){
                        //豆子
                        if( bean.firstdrawbean(i, j, r, l) ){
                            circles[i][j].setRadius(4);
                            circles[i][j].setCenterX(r + 10);
                            circles[i][j].setCenterY(l + 10);
                            // pane.getChildren().add(circles[i][j]);
                        }
                        //大力丸
                        else if(bean.firstdrawbigbean(i,j,r,l)){
                            circles[i][j].setRadius(8);
                            circles[i][j].setCenterX(r + 10);
                            circles[i][j].setCenterY(l + 10);
                            // pane.getChildren().add(circles[i][j]);
                        }
                        //空白
                        else{
                            circles[i][j].setRadius(-1);
                            circles[i][j].setCenterX(r + 10);
                            circles[i][j].setCenterY(l + 10);
                            // pane.getChildren().add(circles[i][j]);
                        }
                        l = l + 22;
                    }
                    r = r + 22;
                }
                simplenpc1.getback();
                simplenpc2.getback();
                strongghost1.getback();
                strongghost2.getback();
                weakghost1.setX(simplenpc1.getx());weakghost1.setY(simplenpc1.gety());
                volghostview1.setX(-1000);volghostview1.setY(-1000);
                weakghost2.setX(simplenpc2.getx());weakghost2.setY(simplenpc2.gety());
                volghostview2.setX(-1000);volghostview2.setY(-1000);
                StrongGhost1.setX(strongghost1.getx());StrongGhost1.setY(strongghost1.gety());
                volghostview3.setX(-1000);volghostview3.setY(-1000);
                StrongGhost2.setX(strongghost2.getx());StrongGhost2.setY(strongghost2.gety());
                volghostview4.setX(-1000);volghostview4.setY(-1000);
                live1view.setX(-1000);
                live1view.setY(-1000);
                live2view.setX(-1000);
                live2view.setY(-1000);
                live3view.setX(655);
                live3view.setY(0);
                underbigbean = false;stepunderbigbean = 0;ghostmoveunderbigbean = 1;
                pacman.playagain();simplenpc1.playagain();simplenpc2.playagain();strongghost1.playagain();strongghost2.playagain();
                score.setText("Score :" + (pacman.eatenbean * 10 + (pacman.eatenbigbean + pacman.eatenghost) * 100));
            }
            primarystage.setScene(scene);
            timer = new Timer();
            timer.schedule(new move(),3000,50);
            forsecondplay += 1 ;
        });
        primarystage.setScene(beginscene);
        primarystage.show();
        pacmanview.requestFocus();
    }
    private void read(){
        File rank = new File("C:\\Users\\Xsh\\Desktop\\学习\\考试材料\\src\\rank.txt");
        try {
            Scanner input = new Scanner(rank);
            for (int i = 0; i < 5; i++) {
                overscore[i] = input.nextInt();
                name[i] = input.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
    private void show(){
        label11.setText(name[0] + "  " +overscore[0]);
        label21.setText(name[1] + "  " +overscore[1]);
        label31.setText(name[2] + "  " +overscore[2]);
        label4.setText(name[3] + "  " +overscore[3]);
        label5.setText(name[4] + "  " +overscore[4]);
    }
    private void checkthescore(String s){
        entire:
        {
            for (int i = 0; i < 5; i++) {
                if (overscore[i] < (pacman.eatenbean * 10 + (pacman.eatenbigbean + pacman.eatenghost) * 100)) {
                    rank1 = i;
                    break entire;
                }
            }
        }
        if (rank1 != -1) {
            while (helprank > rank1) {
                name[helprank] = name[helprank - 1];
                overscore[helprank] = overscore[helprank - 1];
                helprank -= 1;
            }
            name[rank1] = s;
            overscore[rank1] = (pacman.eatenbean * 10 + (pacman.eatenbigbean + pacman.eatenghost) * 100);
        }
        File rank0 = new File("C:\\Users\\Xsh\\Desktop\\学习\\考试材料\\src\\rank.txt");
        try {
            PrintWriter output = new PrintWriter(rank0);
            for (int i = 0; i < 5; i++) {
                output.print(overscore[i]);
                output.print(" ");
                output.println(name[i]);
            }
            output.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
    class move extends TimerTask {
        public void run() {
            if(playorpause){
                Platform.runLater(() ->{
                    if (pacman.walk(pacman.getx(), pacman.gety() - 22 * pacman.getV()) && move == 0) dir = 0;
                    if (pacman.walk(pacman.getx(), pacman.gety() + 22 * pacman.getV()) && move == 1) dir = 1;
                    if (pacman.walk(pacman.getx() - 22 * pacman.getV(), pacman.gety()) && move == 2) dir = 2;
                    if (pacman.walk(pacman.getx() + 22 * pacman.getV(), pacman.gety()) && move == 3) dir = 3;
                    pacman.record();
                    simplenpc1.record();
                    simplenpc2.record();
                    strongghost1.record();
                    strongghost2.record();
                    if(dir == 0){
                        pacmanview.setRotate(90);
                        if (pacman.walk(pacman.getx(), pacman.gety() - 22*pacman.getV())) {
                            pacman.sety(pacman.gety() - 22*pacman.getV());
                   /* if((pacman.getx() - 40)%22 == 0 && (pacman.gety() - 40)%22 == 0 &&  !(pacman.getx() == 18 && pacman.gety() == 326) && !(pacman.getx() == 612 && pacman.gety() == 326) ) {
                        if (bean.getvalue((int) (pacman.getx() - 40) / 22, (int) (pacman.gety() - 40) / 22)) {
                            music.mediaPlayer5.seek(Duration.ZERO);
                            music.mediaPlayer5.play();
                        } else if (!bean.getvalue((int) (pacman.getx() - 40) / 22, (int) (pacman.gety() - 40) / 22)) {
                            music.mediaPlayer6.seek(Duration.ZERO);
                            music.mediaPlayer6.play();
                        }
                    }
                    else {
                        music.mediaPlayer6.seek(Duration.ZERO);
                        music.mediaPlayer6.play();
                    }*/
                            bean.prepaarefordraw(pacman.getx(), pacman.gety());
                        }
                    }
                    if(dir == 1){
                        pacmanview.setRotate(-90);
                        if (pacman.walk(pacman.getx(), pacman.gety() + 22*pacman.getV())) {
                            pacman.sety(pacman.gety() + 22*pacman.getV());
                   /* if((pacman.getx() - 40)%22 == 0 && (pacman.gety() - 40)%22 == 0 &&  !(pacman.getx() == 18 && pacman.gety() == 326) && !(pacman.getx() == 612 && pacman.gety() == 326) ) {
                        if (bean.getvalue((int) (pacman.getx() - 40) / 22, (int) (pacman.gety() - 40) / 22)) {
                            music.mediaPlayer5.seek(Duration.ZERO);
                            music.mediaPlayer5.play();
                        } else if (!bean.getvalue((int) (pacman.getx() - 40) / 22, (int) (pacman.gety() - 40) / 22)) {
                            music.mediaPlayer6.seek(Duration.ZERO);
                            music.mediaPlayer6.play();
                        }
                    }
                    else {
                        music.mediaPlayer6.seek(Duration.ZERO);
                       music.mediaPlayer6.play();
                    }*/
                            bean.prepaarefordraw(pacman.getx(), pacman.gety());
                        }
                    }
                    if(dir == 2){
                        pacmanview.setRotate(0);
                        if (pacman.walk(pacman.getx()-22*pacman.getV(), pacman.gety())) {
                            pacman.setx(pacman.getx() - 22*pacman.getV());
                    /* if((pacman.getx() - 40)%22 == 0 && (pacman.gety() - 40)%22 == 0 &&  !(pacman.getx() == 18 && pacman.gety() == 326) && !(pacman.getx() == 612 && pacman.gety() == 326) ) {
                        if (bean.getvalue((int) (pacman.getx() - 40) / 22, (int) (pacman.gety() - 40) / 22)) {
                            music.mediaPlayer5.seek(Duration.ZERO);
                            music.mediaPlayer5.play();
                        } else if (!bean.getvalue((int) (pacman.getx() - 40) / 22, (int) (pacman.gety() - 40) / 22)) {
                            music.mediaPlayer6.seek(Duration.ZERO);
                            music.mediaPlayer6.play();
                        }
                    }
                    else {
                        music.mediaPlayer6.seek(Duration.ZERO);
                        music.mediaPlayer6.play();
                    }*/
                            bean.prepaarefordraw(pacman.getx(), pacman.gety());
                        }
                        else if (pacman.getx() == 18 && pacman.gety() == 326) {
                            pacman.setx(612);
                        }
                    }
                    if(dir == 3){
                        pacmanview.setRotate(180);
                        if (pacman.walk(pacman.getx()+22*pacman.getV(), pacman.gety())) {
                            pacman.setx(pacman.getx() + 22*pacman.getV());
                   /* if((pacman.getx() - 40)%22 == 0 && (pacman.gety() - 40)%22 == 0 &&  !(pacman.getx() == 18 && pacman.gety() == 326) && !(pacman.getx() == 612 && pacman.gety() == 326) ) {
                        if (bean.getvalue((int) (pacman.getx() - 40) / 22, (int) (pacman.gety() - 40) / 22)) {
                            music.mediaPlayer5.seek(Duration.ZERO);
                            music.mediaPlayer5.play();
                        } else if (!bean.getvalue((int) (pacman.getx() - 40) / 22, (int) (pacman.gety() - 40) / 22)) {
                            music.mediaPlayer6.seek(Duration.ZERO);
                            music.mediaPlayer6.play();
                        }
                    }
                    else {
                        mediaPlayer6.seek(Duration.ZERO);
                        mediaPlayer6.play();
                    }*/
                            bean.prepaarefordraw(pacman.getx(), pacman.gety());
                        }
                        else if (pacman.getx() == 612 && pacman.gety() == 326) {
                            pacman.setx(18);
                        }
                    }
                    if(move == 4){}//不动.
                    if(!underbigbean){
                        simplenpc1.move();
                        simplenpc2.move();
                        strongghost1.trace(pacman.getx(), pacman.gety(),strongghost1.getx(),strongghost1.gety());
                        strongghost2.trace(pacman.getx(), pacman.gety(),strongghost2.getx(),strongghost2.gety());
                        if ( (simplenpc1.checkwhetherdie( pacman.getx(),pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), simplenpc1.getx(), simplenpc1.gety(), simplenpc1.getrecordx(), simplenpc1.getrecordy() )) || (simplenpc2.checkwhetherdie(pacman.getx(),pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), simplenpc2.getx(), simplenpc2.gety(), simplenpc2.getrecordx(), simplenpc2.getrecordy()) ) || (strongghost1.checkwhetherdie(pacman.getx(), pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), strongghost1.getx(), strongghost1.gety(), strongghost1.getrecordx(), strongghost1.getrecordy())) || (strongghost2.checkwhetherdie(pacman.getx(), pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), strongghost2.getx(), strongghost2.gety(), strongghost2.getrecordx(), strongghost2.getrecordy())))  {
                            pacman.getback();
                            simplenpc1.getback();
                            simplenpc2.getback();
                            strongghost1.getback();
                            strongghost2.getback();
                            music.mediaPlayer4.seek(Duration.ZERO);
                            music.mediaPlayer4.play();
                        }
                    }
                    else {
                        if(ghostmoveunderbigbean == 1 && stepunderbigbean <= 50){
                            stepunderbigbean += 1;
                            ghostmoveunderbigbean = 2;
                            if ( simplenpc1.checkwhetherdie( pacman.getx(),pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), simplenpc1.getx(), simplenpc1.gety(), simplenpc1.getrecordx(), simplenpc1.getrecordy() )) {
                                simplenpc1.getback();
                                music.mediaPlayer3.seek(Duration.ZERO);
                                music.mediaPlayer3.play();
                            }
                            if ( simplenpc2.checkwhetherdie( pacman.getx(),pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), simplenpc2.getx(), simplenpc2.gety(), simplenpc2.getrecordx(), simplenpc2.getrecordy() )) {
                                simplenpc2.getback();
                                music.mediaPlayer3.seek(Duration.ZERO);
                                music.mediaPlayer3.play();
                            }
                            if ( strongghost1.checkwhetherdie(pacman.getx(), pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), strongghost1.getx(), strongghost1.gety(), strongghost1.getrecordx(), strongghost1.getrecordy())) {
                                strongghost1.getback();
                                music.mediaPlayer3.seek(Duration.ZERO);
                                music.mediaPlayer3.play();
                            }
                            if ( strongghost2.checkwhetherdie( pacman.getx(),pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), strongghost2.getx(), strongghost2.gety(), strongghost2.getrecordx(), strongghost2.getrecordy() )) {
                                strongghost2.getback();
                                music.mediaPlayer3.seek(Duration.ZERO);
                                music.mediaPlayer3.play();
                            }
                        }
                        else if(ghostmoveunderbigbean == 2 && stepunderbigbean <= 50){
                            stepunderbigbean += 1;
                            ghostmoveunderbigbean = 1;
                            simplenpc1.move();
                            simplenpc2.move();
                            strongghost1.escape(pacman.getx(), pacman.gety(),strongghost1.getx(),strongghost1.gety());
                            strongghost2.escape(pacman.getx(), pacman.gety(),strongghost2.getx(),strongghost2.gety());
                            if ( simplenpc1.checkwhetherdie( pacman.getx(),pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), simplenpc1.getx(), simplenpc1.gety(), simplenpc1.getrecordx(), simplenpc1.getrecordy() )) {
                                simplenpc1.getback();
                                music.mediaPlayer3.seek(Duration.ZERO);
                                music.mediaPlayer3.play();

                            }
                            if ( simplenpc2.checkwhetherdie( pacman.getx(),pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), simplenpc2.getx(), simplenpc2.gety(), simplenpc2.getrecordx(), simplenpc2.getrecordy() )) {
                                simplenpc2.getback();
                                music.mediaPlayer3.seek(Duration.ZERO);
                                music.mediaPlayer3.play();
                            }
                            if ( strongghost1.checkwhetherdie(pacman.getx(), pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), strongghost1.getx(), strongghost1.gety(), strongghost1.getrecordx(), strongghost1.getrecordy())) {
                                strongghost1.getback();
                                music.mediaPlayer3.seek(Duration.ZERO);
                                music.mediaPlayer3.play();
                            }
                            if ( strongghost2.checkwhetherdie( pacman.getx(),pacman.gety(), pacman.getrecordx(), pacman.getrecordy(), strongghost2.getx(), strongghost2.gety(), strongghost2.getrecordx(), strongghost2.getrecordy() )) {
                                strongghost2.getback();
                                music.mediaPlayer3.seek(Duration.ZERO);
                                music.mediaPlayer3.play();
                            }
                        }
                        if(stepunderbigbean > 50){
                            underbigbean = false;
                        }
                    }
                    pacmanview.setY(pacman.gety());
                    pacmanview.setX(pacman.getx());
                    if(underbigbean){
                        weakghost1.setX(-1000);weakghost1.setY(-1000);
                        volghostview1.setX(simplenpc1.getx());volghostview1.setY(simplenpc1.gety());
                        weakghost2.setX(-1000);weakghost2.setY(-1000);
                        volghostview2.setX(simplenpc2.getx());volghostview2.setY(simplenpc2.gety());
                        StrongGhost1.setX(-1000);StrongGhost1.setY(-1000);
                        volghostview3.setX(strongghost1.getx());volghostview3.setY(strongghost1.gety());
                        StrongGhost2.setX(-1000);StrongGhost2.setY(-1000);
                        volghostview4.setX(strongghost2.getx());volghostview4.setY(strongghost2.gety());
                    }
                    if(!underbigbean ) {
                        weakghost1.setX(simplenpc1.getx());weakghost1.setY(simplenpc1.gety());
                        volghostview1.setX(-1000);volghostview1.setY(-1000);
                        weakghost2.setX(simplenpc2.getx());weakghost2.setY(simplenpc2.gety());
                        volghostview2.setX(-1000);volghostview2.setY(-1000);
                        StrongGhost1.setX(strongghost1.getx());StrongGhost1.setY(strongghost1.gety());
                        volghostview3.setX(-1000);volghostview3.setY(-1000);
                        StrongGhost2.setX(strongghost2.getx());StrongGhost2.setY(strongghost2.gety());
                        volghostview4.setX(-1000);volghostview4.setY(-1000);
                    }
                    score.setText("Score :" + (pacman.eatenbean * 10 + (pacman.eatenbigbean + pacman.eatenghost) * 100));
                    if (pacman.live == 2 ) {
                        live3view.setX(-1000);
                        live3view.setY(-1000);
                        live2view.setX(655);
                        live2view.setY(0);
                    }
                    if (pacman.live == 1 ) {
                        live2view.setX(-1000);
                        live2view.setY(-1000);
                        live1view.setX(655);
                        live1view.setY(0);
                    }
                    //判断游戏结束了没有
                    if (pacman.live == 0) {
                        timer.cancel();
                        gameoverview.setX(0);
                        gameoverview.setY(0);
                    }
                    for (int i = 0, r = 40; i < bean.getx(); i++) {
                        for (int j = 0, l = 40; j < bean.gety(); j++) {
                            if (!bean.getvalue(i,j) && !bean.getvalueofbigbean(i,j)){
                                circles[i][j].setRadius(-1);
                            }
                            l = l + 22;
                        }
                        r = r + 22;
                    }
                });
            }
            else {

            }
        }
    }
}