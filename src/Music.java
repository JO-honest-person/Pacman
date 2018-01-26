
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Music{
    private File backmusic=new File ("src\\Music\\backmusic.mp3");Media backmedia =new Media(backmusic.toURI().toString());MediaPlayer mediaPlayer1=new MediaPlayer(backmedia);
    private File backmusic1=new File ("src\\Music\\backmusic1.mp3");Media backmedia1 =new Media(backmusic1.toURI().toString());MediaPlayer mediaPlayer2=new MediaPlayer(backmedia1);
    private File eatmusic=new File ("src\\Music\\eat.mp3");Media eatmedia =new Media(eatmusic.toURI().toString());MediaPlayer mediaPlayer3=new MediaPlayer(eatmedia);
    private File beeatenmusic=new File ("src\\Music\\beeaten.wav");Media beeatenmedia =new Media(beeatenmusic.toURI().toString());MediaPlayer mediaPlayer4=new MediaPlayer(beeatenmedia);
    private File full=new File ("src\\Music\\full.mp3");Media fullmedia =new Media(full.toURI().toString());MediaPlayer mediaPlayer5=new MediaPlayer(fullmedia);
    private File space=new File ("src\\Music\\space.wav");Media spacemedia =new Media(space.toURI().toString());MediaPlayer mediaPlayer6=new MediaPlayer(spacemedia);
}