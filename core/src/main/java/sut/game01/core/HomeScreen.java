package sut.game01.core;


import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ImageLayer;
import playn.core.Mouse;
import playn.core.Keyboard;
import playn.core.*;
import playn.core.PlayN;
import playn.core.gl.GLShader;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import tripleplay.game.Screen;
import react.UnitSlot;
import tripleplay.game.UIScreen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import static playn.core.PlayN.graphics;
import playn.core.util.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;


public class HomeScreen extends Screen  {

  private final LevelScreen levelScreen;
  private final SettingScreen settingScreen;
  private final ScreenStack ss;
  private final ImageLayer bg;
  private final ImageLayer startButton;
  private final ImageLayer settingButton;
  private final ImageLayer faceButton;
  private final ImageLayer name;
  private static  Sound music;
  private static  boolean controlMusic = true;
  private Root root;


  public HomeScreen(final ScreenStack ss){
    this.ss = ss;
    this.levelScreen =new LevelScreen(ss);
    this.settingScreen =new SettingScreen(ss);

    music = assets().getSound("sounds/music");
      music.setVolume(0.1f);
      music.setLooping(true);
      music.play();

      Image bgImage = assets().getImage("images/bg.png");
    this.bg = graphics().createImageLayer(bgImage);

    //=============================================================name
    Image nameImage = assets().getImage("images/name.png");
    this.name = graphics().createImageLayer(nameImage);
    name.setTranslation(160, 32);

    
    //=============================================================START
    Image startImage = assets().getImage("images/startButton.png");
    this.startButton = graphics().createImageLayer(startImage);
    startButton.setTranslation(245, 135);

    startButton.addListener(new Mouse.LayerAdapter(){
      @Override
      public void onMouseUp(Mouse.ButtonEvent event){
        ss.push(levelScreen); 
      }

        /*@Override
        public void onMouseOver(Mouse.MotionEvent event) {
            startButton.setVisible(false);
        }

        @Override
        public void onMouseOut(Mouse.MotionEvent event) {
            startButton.setVisible(true);
        }*/
    });


    //=============================================================Setting
    Image settingImage = assets().getImage("images/settingButton.png");
    this.settingButton = graphics().createImageLayer(settingImage);
    settingButton.setTranslation(540, 390);

    Image faceImage = assets().getImage("images/faceButton.png");
    this.faceButton = graphics().createImageLayer(faceImage);
    faceButton.setTranslation(40, 390);
    
    settingButton.addListener(new Mouse.LayerAdapter(){
      @Override
      public void onMouseUp(Mouse.ButtonEvent event){
        ss.push(settingScreen); 
      }
    });
    
  }

 public static void settingSound(boolean controleMusic2){
     controlMusic = controleMusic2;
     if(controlMusic == true)
         music.setVolume(0.1f);
     if(controlMusic == false)
         music.setVolume(0.0f);
 }

  //=============================================================
  @Override
  public void wasShown (){
    super.wasShown();
    this.layer.add(bg);
    this.layer.add(startButton);  
    this.layer.add(settingButton);
    this.layer.add(faceButton);
    this.layer.add(name);
    if(controlMusic == true)

    PlayN.keyboard().setListener(new Keyboard.Adapter() {
        @Override
        public void onKeyUp(Keyboard.Event event) {
            if (event.key() == Key.ENTER) {
                ss.push(levelScreen);
            }
        }
      });
  }
}

