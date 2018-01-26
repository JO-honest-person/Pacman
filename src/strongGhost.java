import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class strongGhost{
    private double x = 304,X = 304;
    private double y = 260,Y = 260;
    private int stepofbestuck = 10;
    private double lastx,lasty;
    private double v = 0.5;
    private int straightmove = 0;//0ÍùÉÏ£¬1ÍùÏÂ£¬2Íù×ó£¬3ÍùÓÒ
    private Judge judge=new Judge();
    public  strongGhost(double i,double j){
        x = i;
        X = i;
        y = j;
        Y = j;
    }
    public void trace(double px,double py,double nx,double ny){
        if(stepofbestuck >= 10){
            entire:{
                if(straightmove == 0){
                    if(!judge.isWall(x,y-22*v)){
                        move();
                        break entire;
                    }
                    //Ó¦¸ÃÍù×ó×ß¡£
                    else if((px - nx < 0) && (py == ny )){
                        if(!judge.isWall(x-22*v,y)) {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒ×ß¡£
                    else if((py == ny) && (px - nx > 0)){
                        if(!judge.isWall(x+22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y + 22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else{
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //ÓÃ¹ýÍùÏÂ×ß¡£
                    else if((px == nx) && (py - ny > 0)){
                        if(!judge.isWall(x,y+22*v)) {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÏÂ×ß.
                    else if((px - nx > 0) && (py - ny > 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÉÏ×ß.
                    else if((px - nx > 0) && (py - ny < 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÉÏ×ß¡£
                    else if((px - nx < 0) && (py - ny < 0)){
                        if(!judge.isWall(x-22*v ,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÏÂ×ß
                    else {
                        if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                }
                if(straightmove == 1){
                    if(!judge.isWall(x,y+22*v)){
                        move();
                        break entire;
                    }
                    //Ó¦¸ÃÍù×ó×ß¡£
                    else if((px - nx < 0) && (py == ny )){
                        if(!judge.isWall(x-22*v,y)) {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒ×ß¡£
                    else if((py == ny) && (px - nx > 0)){
                        if(!judge.isWall(x+22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y - 22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else{
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //ÓÃ¹ýÍùÉÏ×ß¡£
                    else if((px == nx) && (py - ny < 0)){
                        if(!judge.isWall(x,y-22*v)) {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÏÂ×ß.
                    else if((px - nx > 0) && (py - ny > 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÉÏ×ß.
                    else if((px - nx > 0) && (py - ny < 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÉÏ×ß¡£
                    else if((px - nx < 0) && (py - ny < 0)){
                        if(!judge.isWall(x ,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÏÂ×ß
                    else {
                        if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                }
                if(straightmove == 2){
                    if(!judge.isWall(x-22*v,y)){
                        move();
                        break entire;
                    }
                    //Ó¦¸ÃÍùÏÂ×ß¡£
                    else if((px == nx) && (py - ny > 0)){
                        if(!judge.isWall(x,y+22*v)) {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x+22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒ×ß¡£
                    else if((py == ny) && (px - nx > 0)){
                        if(!judge.isWall(x+22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y - 22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else{
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //ÓÃ¹ýÍùÉÏ×ß¡£
                    else if((px == nx) && (py - ny < 0)){
                        if(!judge.isWall(x,y-22*v)) {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x+22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÏÂ×ß.
                    else if((px - nx > 0) && (py - ny > 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÉÏ×ß.
                    else if((px - nx > 0) && (py - ny < 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÉÏ×ß¡£
                    else if((px - nx < 0) && (py - ny < 0)){
                        if(!judge.isWall(x ,y-22*v)){
                            straightmove = 0;
                            move();
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÏÂ×ß
                    else {
                        if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                }
                if(straightmove == 3){
                    if(!judge.isWall(x+22*v,y)){
                        move();
                        break entire;
                    }
                    //Ó¦¸ÃÍùÏÂ×ß¡£
                    else if((px == nx) && (py - ny > 0)){
                        if(!judge.isWall(x,y+22*v)) {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×ó×ß¡£
                    else if((py == ny) && (px - nx < 0)){
                        if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y - 22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else{
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //ÓÃ¹ýÍùÉÏ×ß¡£
                    else if((px == nx) && (py - ny < 0)){
                        if(!judge.isWall(x,y-22*v)) {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÏÂ×ß.
                    else if((px - nx > 0) && (py - ny > 0)){
                        if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÉÏ×ß.
                    else if((px - nx > 0) && (py - ny < 0)){
                        if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÉÏ×ß¡£
                    else if((px - nx < 0) && (py - ny < 0)){
                        if(!judge.isWall(x ,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÏÂ×ß
                    else {
                        if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                }
            }
        }
        stepofbestuck += 1;
    }
    public void escape(double px,double py,double nx,double ny){
        if(stepofbestuck >= 10){
            entire:{
                if(straightmove == 0){
                    if(!judge.isWall(x,y-22*v)){
                        move();
                        break entire;
                    }
                    //Ó¦¸ÃÍù×ó×ß¡£
                    else if((py == ny) && (px - nx > 0)){
                        if(!judge.isWall(x-22*v,y)) {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒ×ß¡£
                    else if((px - nx < 0) && (py == ny )){
                        if(!judge.isWall(x+22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y + 22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else{
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //ÓÃ¹ýÍùÏÂ×ß¡£
                    else if((px == nx) && (py - ny > 0)){
                        if(!judge.isWall(x+22*v,y)) {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÏÂ×ß.
                    else if((px - nx < 0) && (py - ny < 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÉÏ×ß.
                    else if((px - nx < 0) && (py - ny > 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÉÏ×ß¡£
                    else if((px - nx > 0) && (py - ny > 0)){
                        if(!judge.isWall(x-22*v ,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÏÂ×ß
                    else {
                        if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                }
                if(straightmove == 1){
                    if(!judge.isWall(x,y+22*v)){
                        move();
                        break entire;
                    }
                    //Ó¦¸ÃÍù×ó×ß¡£
                    else if((py == ny) && (px - nx > 0)){
                        if(!judge.isWall(x-22*v,y)) {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒ×ß¡£
                    else if((px - nx < 0) && (py == ny )){
                        if(!judge.isWall(x+22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y - 22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else{
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÏÂ×ß¡£
                    else if((px == nx) && (py - ny < 0)){
                        if(!judge.isWall(x+22*v,y)) {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÏÂ×ß.
                    else if((px - nx < 0) && (py - ny < 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÉÏ×ß.
                    else if((px - nx < 0) && (py - ny > 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÉÏ×ß¡£
                    else if((px - nx > 0) && (py - ny > 0)){
                        if(!judge.isWall(x ,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÏÂ×ß
                    else {
                        if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                }
                if(straightmove == 2){
                    if(!judge.isWall(x-22*v,y)){
                        move();
                        break entire;
                    }
                    //Ó¦¸ÃÍùÏÂ×ß¡£
                    else if((px == nx) && (py - ny < 0)){
                        if(!judge.isWall(x,y+22*v)) {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x+22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×ó×ß¡£
                    else if((py == ny) && (px - nx > 0)){
                        if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y - 22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else{
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //ÓÃ¹ýÍùÉÏ×ß¡£
                    else if((px == nx) && (py - ny > 0)){
                        if(!judge.isWall(x,y-22*v)) {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÏÂ×ß.
                    else if((px - nx < 0) && (py - ny < 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÉÏ×ß.
                    else if((px - nx < 0) && (py - ny > 0)){
                        if(!judge.isWall(x + 22*v,y)){
                            straightmove = 3;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÉÏ×ß¡£
                    else if((px - nx > 0) && (py - ny > 0)){
                        if(!judge.isWall(x ,y-22*v)){
                            straightmove = 0;
                            move();
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÏÂ×ß
                    else {
                        if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 3;
                            move();
                            break entire;
                        }
                    }
                }
                if(straightmove == 3){
                    if(!judge.isWall(x+22*v,y)){
                        move();
                        break entire;
                    }
                    //Ó¦¸ÃÍùÏÂ×ß¡£
                    else if((px == nx) && (py - ny < 0)){
                        if(!judge.isWall(x,y+22*v)) {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒ×ß¡£
                    else if((py == ny) && (px - nx < 0)){
                        if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y - 22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else{
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //ÓÃ¹ýÍùÉÏ×ß¡£
                    else if((px == nx) && (py - ny > 0)){
                        if(!judge.isWall(x,y-22*v)) {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 2;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÏÂ×ß.
                    else if((px - nx < 0) && (py - ny < 0)){
                        if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍùÓÒÉÏ×ß.
                    else if((px - nx < 0) && (py - ny > 0)){
                        if(!judge.isWall(x,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÉÏ×ß¡£
                    else if((px - nx > 0) && (py - ny > 0)){
                        if(!judge.isWall(x ,y-22*v)){
                            straightmove = 0;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 1;
                            move();
                            break entire;
                        }
                    }
                    //Ó¦¸ÃÍù×óÏÂ×ß
                    else {
                        if(!judge.isWall(x,y+22*v)){
                            straightmove = 1;
                            move();
                            break entire;
                        }
                        else if(!judge.isWall(x-22*v,y)){
                            straightmove = 2;
                            move();
                            break entire;
                        }
                        else {
                            straightmove = 0;
                            move();
                            break entire;
                        }
                    }
                }
            }
        }
        stepofbestuck += 1;}
    private void move(){
        if(straightmove == 0) {
            y = y - 22*v;
        }
        if(straightmove == 1) {
            y = y + 22*v;
        }
        if(straightmove == 2) {
            x = x - 22*v;
        }
        if(straightmove == 3) {
            x = x + 22*v;
        }
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
        File file = new File("StrongGhost1.txt");
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
        File file = new File("StrongGhost1.txt");
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
        File file = new File("StrongGhost2.txt");
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
        File file = new File("StrongGhost2.txt");
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
            stepofbestuck = 0;
            Pacman.eatenghost += 1;
        }
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
    public void setv(double i ){
        v = i;
    }
    public void playagain(){
        stepofbestuck = 10;
    }

}