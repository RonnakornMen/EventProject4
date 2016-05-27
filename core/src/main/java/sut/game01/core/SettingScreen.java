package sut.game01.core;


import static playn.core.PlayN.*;

import playn.core.*;
import playn.core.ImageLayer;

import tripleplay.game.Screen;
import react.UnitSlot;
import tripleplay.game.UIScreen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import static playn.core.PlayN.graphics;



public class SettingScreen extends Screen  {

  private final ScreenStack ss;
  private final ImageLayer bg;
  private final ImageLayer backButton;
  private  final ImageLayer soundOn;
  private  final ImageLayer soundOff;
  private final ImageLayer settingBox;
  private Root root;
  static boolean controlMusic =true;

  public SettingScreen(final ScreenStack ss){
    this.ss = ss;
    Image bgImage = assets().getImage("images/bg.png");
    this.bg = graphics().createImageLayer(bgImage);
    

    Image backImage = assets().getImage("images/backButton.png");
    this.backButton = graphics().createImageLayer(backImage);
    backButton.setTranslation(40, 390);

      Image settingBoxImage = assets().getImage("images/settingBox.png");
      this.settingBox = graphics().createImageLayer(settingBoxImage);
      settingBox.setTranslation(10, 10);

      Image soundOnImage = assets().getImage("images/soundOn.png");
      this.soundOn = graphics().createImageLayer(soundOnImage);
      soundOn.setTranslation(100, 170);
      soundOn.addListener(new Mouse.LayerAdapter(){
          @Override
          public void onMouseUp(Mouse.ButtonEvent event){
              controlMusic = true;
              HomeScreen.settingSound(controlMusic); // pop screen
          }
      });

      Image soundOffImage = assets().getImage("images/soundOff.png");
      this.soundOff = graphics().createImageLayer(soundOffImage);
      soundOff.setTranslation(400, 170);

      soundOff.addListener(new Mouse.LayerAdapter(){
          @Override
          public void onMouseUp(Mouse.ButtonEvent event){
              controlMusic = false;
              HomeScreen.settingSound(controlMusic); // pop screen
          }
      });
    
    backButton.addListener(new Mouse.LayerAdapter(){
      @Override
      public void onMouseUp(Mouse.ButtonEvent event){
        ss.remove(ss.top()); // pop screen
      }
    });
  }

  //=============================================================
  @Override
  public void wasShown (){
    super.wasShown();
    this.layer.add(bg);
      this.layer.add(settingBox);
    this.layer.add(backButton);
      this.layer.add(soundOn);
      this.layer.add(soundOff);
  
  }
}
