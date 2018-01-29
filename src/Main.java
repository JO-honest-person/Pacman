import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
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
import java.net.MalformedURLException;
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
    private Label rank1L,rank2L,rank3L,rank4L,rank5L;
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
    Music music = new Music();    //用于播放音乐
    private Judge judge=new Judge();
    Circle[][] circles = new Circle[26][29];//用于储存豆子。画豆子。

    public void start(Stage primarystage) {
        primarystage.setResizable(false);//禁止窗体缩放
        //开始界面
        Pane beginpane = new Pane();
        Scene beginscene = new Scene(beginpane,670,300);
        //for new Image(); If the passed string is not a valid URL, but a path instead, the Image is searched on the classpath in that case.So, Resources must be placed
        //under src
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
        //四个按钮：Play,Help,Ranking list,Continue
        Label playLabel = new Label("Play",n1);
        playLabel.setContentDisplay(ContentDisplay.RIGHT);
        playLabel.setTextFill(Color.BROWN);
        playLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        playLabel.setOnMouseEntered(e -> {
            playLabel.setScaleX(1.5);
            playLabel.setScaleY(1.2);
        });
        playLabel.setOnMouseExited(e -> {
            playLabel.setScaleX(1);
            playLabel.setScaleY(1);
        });
        Label helpLabel = new Label("Help",n2);
        helpLabel.setContentDisplay(ContentDisplay.RIGHT);
        helpLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        helpLabel.setOnMouseEntered(e -> {
            helpLabel.setScaleX(1.5);
            helpLabel.setScaleY(1.2);
        });
        helpLabel.setOnMouseExited(e -> {
            helpLabel.setScaleX(1);
            helpLabel.setScaleY(1);
        });
        Label rankLabel = new Label("Ranking List",n3);
        rankLabel.setContentDisplay(ContentDisplay.RIGHT);
        rankLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        rankLabel.setOnMouseEntered(e -> {
            rankLabel.setScaleX(1.5);
            rankLabel.setScaleY(1.2);
        });
        rankLabel.setOnMouseExited(e -> {
            rankLabel.setScaleX(1);
            rankLabel.setScaleY(1);
        });
        Label continueLabel = new Label("Continue",n4);
        continueLabel.setContentDisplay(ContentDisplay.RIGHT);
        continueLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        continueLabel.setOnMouseEntered(e -> {
            continueLabel.setScaleX(1.5);
            continueLabel.setScaleY(1.2);
        });
        continueLabel.setOnMouseExited(e -> {
            continueLabel.setScaleX(1);
            continueLabel.setScaleY(1);
        });
        playLabel.setLayoutX(300);playLabel.setLayoutY(20);
        helpLabel.setLayoutX(300);helpLabel.setLayoutY(80);
        rankLabel.setLayoutX(300);rankLabel.setLayoutY(200);
        continueLabel.setLayoutX(300);continueLabel.setLayoutY(140);
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
        beginpane.getChildren().addAll(n1b,n2b,n3b,n4b,back,playLabel,helpLabel,rankLabel,continueLabel,setting,setbackground,sv,wv,vok,st,wt);


        //帮助界面
        Pane helpPane = new Pane();
        Scene helpScene = new Scene(helpPane,670,300);
        ImageView helpview = new ImageView(new Image("Resources\\help_back.jpg"));
        helpview.setFitWidth(670);helpview.setFitHeight(400);
        ImageView backup = new ImageView(new Image("Resources\\row.jpg"));
        backup.setX(5);backup.setY(5);backup.setFitHeight(50);backup.setFitWidth(75);
        Label helpText = new Label(); helpText.setLayoutX(270);helpText.setLayoutY(60);
        Text helpTitle = new Text("H e l p");helpTitle.setX(260);helpTitle.setY(40);
        helpText.setText("   Use W S A D to control the moving of  pacman.\n If you don't want to move,just type the space. Wh\n en you eat the red beans, you can  eat the ghost " +
                "\n Every bean you eat ,you will get  10 ,and red bean \n or ghost 100. You can enter the list of the top five \n scores\nThe Pacman on the top right corner can set" +
                " the\n speed of ghost.You should enter the number betw\n een 0 and 1");
        helpText.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));helpText.setTextFill(Color.PINK);
        helpTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));helpTitle.setFill(Color.web("#0076a3"));
        helpPane.getChildren().addAll(helpview,helpTitle,helpText,backup);
        helpLabel.setOnMouseClicked(e -> {
            primarystage.setScene(helpScene);
        });
        backup.setOnMouseClicked(e -> {
            primarystage.setScene(beginscene);
        });


        //排行榜
        Pane rankPane = new Pane();
        Scene rankScene = new Scene(rankPane,670,300);
        ImageView row = new ImageView(new Image("Resources\\row.jpg"));
        row.setX(5);row.setY(5);row.setFitHeight(50);row.setFitWidth(75);
        Text rankTitle = new Text("Ranking List");rankTitle.setFont(Font.font(30));rankTitle.setX(270);rankTitle.setY(30);rankTitle.setFill(Color.YELLOW);
        rank1L = new Label();rank1L.setLayoutX(270);rank1L.setLayoutY(45);
        ImageView imageView11 = new ImageView(new Image("Resources\\71.png"));imageView11.setFitWidth(180);imageView11.setFitHeight(30);imageView11.setX(270);imageView11.setY(40);
        rank2L = new Label();rank2L.setLayoutX(270);rank2L.setLayoutY(85);
        ImageView imageView12 = new ImageView(new Image("Resources\\71.png"));imageView12.setFitWidth(160);imageView12.setFitHeight(30);imageView12.setX(270);imageView12.setY(80);
        rank3L = new Label();rank3L.setLayoutX(270);rank3L.setLayoutY(125);
        ImageView imageView13 = new ImageView(new Image("Resources\\71.png"));imageView13.setFitWidth(140);imageView13.setFitHeight(30);imageView13.setX(270);imageView13.setY(120);
        rank4L = new Label();rank4L.setLayoutX(270);rank4L.setLayoutY(165);
        ImageView imageView14 = new ImageView(new Image("Resources\\71.png"));imageView14.setFitWidth(120);imageView14.setFitHeight(30);imageView14.setX(270);imageView14.setY(160);
        rank5L = new Label();rank5L.setLayoutX(270);rank5L.setLayoutY(205);
        ImageView imageView15 = new ImageView(new Image("Resources\\71.png"));imageView15.setFitWidth(100);imageView15.setFitHeight(30);imageView15.setX(270);imageView15.setY(200);
        ImageView backimageView = new ImageView(new Image("Resources\\mcmmzxavvfm.jpg"));backimageView.setFitHeight(300);backimageView.setFitWidth(670);
        rankPane.getChildren().addAll(backimageView,rankTitle,row,imageView11, rank1L,imageView12, rank2L,imageView13, rank3L,imageView14, rank4L,imageView15, rank5L);
        row.setOnMouseClicked(e -> {
            primarystage.setScene(beginscene);
        });
        rankLabel.setOnMouseClicked(e -> {
            read();
            show();
            primarystage.setScene(rankScene);
        });


        //胜利界面
        Pane winPane = new Pane();
        Scene winScene = new Scene(winPane);
        ImageView winview = new ImageView(new Image("Resources\\timg.jpg"));winview.setFitWidth(400);winview.setFitHeight(400);
        TextField tf = new TextField();tf.setLayoutX(100);tf.setLayoutY(350);
        Button add = new Button("OK");add.setLayoutX(230);add.setLayoutY(350);
        Text wictory = new Text("Y O U W I N ! ! !");
        wictory.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40));
        wictory.setFill(Color.RED);
        wictory.setX(60);wictory.setY(40);
        winPane.getChildren().addAll(winview,tf,add,wictory);
        add.setOnMouseClicked(e -> {
            read();
            String s = tf.getText();
            checkthescore(s);
            show();
            primarystage.setScene(rankScene);
            rank1 = -1;
            helprank = 4;
        });


        //游戏界面
        Pane gamePane = new Pane();
        Scene gameScene = new Scene(gamePane,880,700);
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
        gamePane.getChildren().add(background);

        ImageView playagain = new ImageView(playagainimage);playagain.setFitHeight(60);playagain.setFitWidth(230);playagain.setX(650);playagain.setY(110);
        //画出豆子。
        bean.assignment();
        for(int i = 0, r = 40;i<bean.getx();i++){
            for(int j = 0,l = 40; j < bean.gety();j++){
                //豆子
                if( bean.initialBean(i, j, r, l) ){
                    circles[i][j] = new Circle();
                    circles[i][j].setRadius(4);
                    circles[i][j].setStroke(Color.YELLOW);
                    circles[i][j].setFill(Color.YELLOW);
                    circles[i][j].setCenterX(r + 10);
                    circles[i][j].setCenterY(l + 10);
                    gamePane.getChildren().add(circles[i][j]);
                }
                //大力丸
                else if(bean.initialBigBean(i,j,r,l)){
                    circles[i][j] = new Circle();
                    circles[i][j].setRadius(8);
                    circles[i][j].setStroke(Color.ROSYBROWN);
                    circles[i][j].setFill(Color.RED);
                    circles[i][j].setCenterX(r + 10);
                    circles[i][j].setCenterY(l + 10);
                    gamePane.getChildren().add(circles[i][j]);
                }
                //空白
                else{
                    circles[i][j] = new Circle();
                    circles[i][j].setRadius(-1);
                    circles[i][j].setStroke(Color.YELLOW);
                    circles[i][j].setFill(Color.YELLOW);
                    circles[i][j].setCenterX(r + 10);
                    circles[i][j].setCenterY(l + 10);
                    gamePane.getChildren().add(circles[i][j]);
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
        Label save_label = new Label("S a v e");
        save_label.setStyle("-fx-text-fill:yellow;");
        Button save = new Button("",save_label);
        save.setStyle("-fx-background-color: black");
        save_label.setFont(Font.font("Verdana",30));
        save.setLayoutX(655);save.setLayoutY(240);save.setMinSize(225,70);
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


        //返回主界面
        BackgroundImage backgroundImage = new BackgroundImage(new Image("Resources\\score background.jpg"),null,null,null,null);
        Background rtn_background = new Background(backgroundImage);
        Button return_btn = new Button("R e t u r n");
        return_btn.setBackground(rtn_background);
        return_btn.setFont(Font.font("Verdana",30));
        return_btn.setLayoutX(655);return_btn.setLayoutY(310);return_btn.setMinSize(225,70);
        return_btn.setOnMouseClicked(e -> {
            timer.cancel();
            playorpause = false;
            primarystage.setScene(beginscene);
        });

        gamePane.getChildren().addAll(playagain,pacmanview,weakghost1,weakghost2,StrongGhost1,StrongGhost2,choosebackview, choosemusic,vBox,save,gameoverview,volghostview1,volghostview2,volghostview3,volghostview4,live3view,live1view,live2view,scoreview,score,pauseviwe,playview,return_btn);
        //移动
        pacmanview.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:{
                    move = 0;
                    win:
                    {
                        for (int i = 0; i < bean.getx(); i++) {
                            for (int j = 0; j < bean.gety(); j++) {
                                if (bean.getValue(i,j)) {
                                    break win;
                                }
                            }
                        }
                        primarystage.setScene(winScene);
                        timer.cancel();
                    }}break;
                case S:
                {
                    move = 1;
                    win:
                    {
                        for (int i = 0; i < bean.getx(); i++) {
                            for (int j = 0; j < bean.gety(); j++) {
                                if (bean.getValue(i,j)) {
                                    break win;
                                }
                            }
                        }
                        primarystage.setScene(winScene);
                        timer.cancel();
                    }}break;
                case A:
                {
                    move = 2;
                    win:
                    {
                        for (int i = 0; i < bean.getx(); i++) {
                            for (int j = 0; j < bean.gety(); j++) {
                                if (bean.getValue(i,j)) {
                                    break win;
                                }
                            }
                        }
                        primarystage.setScene(winScene);
                        timer.cancel();
                    }}break;
                case D:
                {
                    move = 3;
                    win:
                    {
                        for (int i = 0; i < bean.getx(); i++) {
                            for (int j = 0; j < bean.gety(); j++) {
                                if (bean.getValue(i,j)) {
                                    break win;
                                }
                            }
                        }
                        primarystage.setScene(winScene);
                        timer.cancel();
                    }}break;
                case SPACE:{move = 4;}break;

            }
        });
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
            drawbean();
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
            underbigbean = false;stepunderbigbean = 0;ghostmoveunderbigbean = 1;playorpause=true;pacmanview.requestFocus();
            pacman.playagain();simplenpc1.playagain();simplenpc2.playagain();strongghost1.playagain();strongghost2.playagain();
            score.setText("Score :" + (pacman.eatenbean * 10 + (pacman.eatenbigbean + pacman.eatenghost) * 100));
        });
        //继续游戏
        continueLabel.setOnMouseClicked(a -> {
            simplenpc1.load1();
            simplenpc2.load2();
            strongghost1.load1();
            strongghost2.load2();
            bean.load();
            pacman.load();
            for (int i = 0, r = 40; i < bean.getx(); i++) {
                for (int j = 0, l = 40; j < bean.gety(); j++) {
                    //豆子
                    if (bean.getValue(i, j)) {
                        circles[i][j].setRadius(4);
                        circles[i][j].setCenterX(r + 10);
                        circles[i][j].setCenterY(l + 10);
                        // pane.getChildren().add(circles[i][j]);
                    }
                    //大力丸
                    else if (bean.getValueOfBigbean(i, j)) {
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
            forsecondplay +=1;playorpause=true;
            primarystage.setScene(gameScene);
            pacmanview.requestFocus();
        });
        //开始游戏
        playLabel.setOnMouseClicked(e -> {
            if(forsecondplay > 0 ){
                pacman.getback();
                pacmanview.setX(pacman.getX());
                pacmanview.setY(pacman.getY());
                bean.assignment();
                drawbean();
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
                underbigbean = false;stepunderbigbean = 0;ghostmoveunderbigbean = 1;playorpause=true;
                pacman.playagain();simplenpc1.playagain();simplenpc2.playagain();strongghost1.playagain();strongghost2.playagain();
                score.setText("Score :" + (pacman.eatenbean * 10 + (pacman.eatenbigbean + pacman.eatenghost) * 100));
            }
            primarystage.setScene(gameScene);
            timer = new Timer();
            timer.schedule(new move(),3000,50);
            forsecondplay += 1 ;
            playorpause=true;
            pacmanview.requestFocus();
        });
        primarystage.setScene(beginscene);
        primarystage.show();
        pacmanview.requestFocus();
    }
    private void drawbean(){
        for(int i = 0, r = 40;i<bean.getx();i++){
            for(int j = 0,l = 40; j < bean.gety();j++){
                //豆子
                if( bean.initialBean(i, j, r, l) ){
                    circles[i][j].setRadius(4);
                    circles[i][j].setCenterX(r + 10);
                    circles[i][j].setCenterY(l + 10);
                    // pane.getChildren().add(circles[i][j]);
                }
                //大力丸
                else if(bean.initialBigBean(i,j,r,l)){
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
    }
    private void read(){
        File rank = new File("Ranking//rank.txt");
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
        rank1L.setText(name[0] + "  " +overscore[0]);
        rank2L.setText(name[1] + "  " +overscore[1]);
        rank3L.setText(name[2] + "  " +overscore[2]);
        rank4L.setText(name[3] + "  " +overscore[3]);
        rank5L.setText(name[4] + "  " +overscore[4]);
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
        File rank0 = new File("Ranking//rank.txt");
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
                    if (!judge.isWall(pacman.getx(), pacman.gety() - 22 * pacman.getV()) && move == 0) dir = 0;
                    if (!judge.isWall(pacman.getx(), pacman.gety() + 22 * pacman.getV()) && move == 1) dir = 1;
                    if (!judge.isWall(pacman.getx() - 22 * pacman.getV(), pacman.gety()) && move == 2) dir = 2;
                    if (!judge.isWall(pacman.getx() + 22 * pacman.getV(), pacman.gety()) && move == 3) dir = 3;
                    pacman.record();
                    simplenpc1.record();
                    simplenpc2.record();
                    strongghost1.record();
                    strongghost2.record();
                    if(dir == 0){
                        pacmanview.setRotate(90);
                        if (!judge.isWall(pacman.getx(), pacman.gety() - 22*pacman.getV())) {
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
                            bean.eatBean(pacman.getx(), pacman.gety());
                        }
                    }
                    if(dir == 1){
                        pacmanview.setRotate(-90);
                        if (!judge.isWall(pacman.getx(), pacman.gety() + 22*pacman.getV())) {
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
                            bean.eatBean(pacman.getx(), pacman.gety());
                        }
                    }
                    if(dir == 2){
                        pacmanview.setRotate(0);
                        if (!judge.isWall(pacman.getx()-22*pacman.getV(), pacman.gety())) {
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
                            bean.eatBean(pacman.getx(), pacman.gety());
                        }
                        else if (pacman.getx() == 18 && pacman.gety() == 326) {
                            pacman.setx(612);
                        }
                    }
                    if(dir == 3){
                        pacmanview.setRotate(180);
                        if (!judge.isWall(pacman.getx()+22*pacman.getV(), pacman.gety())) {
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
                            bean.eatBean(pacman.getx(), pacman.gety());
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
                            if (!bean.getValue(i,j) && !bean.getValueOfBigbean(i,j)){
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