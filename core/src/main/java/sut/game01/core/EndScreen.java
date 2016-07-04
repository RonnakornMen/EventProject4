package sut.game01.core;


import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ImageLayer;
import playn.core.Mouse;

import tripleplay.game.Screen;
import react.UnitSlot;
import tripleplay.game.UIScreen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import static playn.core.PlayN.graphics;
import sut.game01.core.HomeScreen;
import sut.game01.core.LevelScreen;


public class EndScreen extends Screen  {

  private final ScreenStack ss;
  //private final HomeScreen homeScreen;

    private final GameScreen gameScreen;
    private final GameScreen2 gameScreen2;
    private final GameScreen3 gameScreen3;
    private final GameScreen4 gameScreen4;
  private final ImageLayer bg;
  private final ImageLayer backButton;
  private final ImageLayer window;
  private final ImageLayer reButton;
  private final ImageLayer levelButton;
  private final ImageLayer homeButton;
  //private final LevelScreen levelScreen;

  private Root root;

  public EndScreen(final ScreenStack ss, final int level){
    this.ss = ss;
    this.gameScreen = new GameScreen(ss);

      this.gameScreen2 =new GameScreen2(ss);
      this.gameScreen3 =new GameScreen3(ss);
      this.gameScreen4 =new GameScreen4(ss);
   // this.homeScreen =new HomeScreen(ss);
   // this.levelScreen =new LevelScreen(ss);


    Image bgImage = assets().getImage("images/bg.png");
    this.bg = graphics().createImageLayer(bgImage);
    //=============================================================window
    Image windowImage = assets().getImage("images/window1.png");
    this.window = graphics().createImageLayer(windowImage);
    window.setTranslation(110, 20);

    Image reButtonImage = assets().getImage("images/reButton2.png");
    this.reButton = graphics().createImageLayer(reButtonImage);
    reButton.setTranslation(140, 170);

    reButton.addListener(new Mouse.LayerAdapter(){
      @Override
      public void onMouseUp(Mouse.ButtonEvent event){
          if(level ==1)
              ss.push(gameScreen);
          else if(level ==2)
              ss.push(gameScreen2);
          else if(level ==3)
              ss.push(gameScreen3);
          else if(level ==4)
              ss.push(gameScreen4);
         }
    });

    Image levelButtonImage = assets().getImage("images/levelButton.png");
    this.levelButton = graphics().createImageLayer(levelButtonImage);
    levelButton.setTranslation(255, 170);

    levelButton.addListener(new Mouse.LayerAdapter(){
      @Override
      public void onMouseUp(Mouse.ButtonEvent event){
          ss.push(new LevelScreen(ss));
         }
    });

    Image homeButtonImage = assets().getImage("images/homeButton.png");
    this.homeButton = graphics().createImageLayer(homeButtonImage);
    homeButton.setTranslation(360, 170);

    homeButton.addListener(new Mouse.LayerAdapter(){
      @Override
      public void onMouseUp(Mouse.ButtonEvent event){
          HomeScreen.settingSound(false);
          ss.push(new HomeScreen(ss));
         }
    });
    
    //====================================================================backButton
    Image backImage = assets().getImage("images/back.png");
    this.backButton = graphics().createImageLayer(backImage);
    backButton.setTranslation(10, 10);
    
    backButton.addListener(new Mouse.LayerAdapter(){
      @Override
      public void onMouseUp(Mouse.ButtonEvent event){
        ss.remove(ss.top()); // pop screen
      }
    });
    /*
    Image settingImage = assets().getImage("images/setting.png");
    this.settingButton = graphics().createImageLayer(settingImage);
    settingButton.setTranslation(200, 10);
    
    settingButton.addListener(new Mouse.LayerAdapter(){
      @Override
      public void onMouseUp(Mouse.ButtonEvent event){
        ss.push(settingScreen); 
      }
    });*/
  }
 



  //=============================================================
  @Override
  public void wasShown (){
    super.wasShown();
    this.layer.add(bg);
    this.layer.add(window);
    this.layer.add(reButton);
    this.layer.add(levelButton);
    this.layer.add(homeButton);
    //this.layer.add(backButton);
  
  }
}
