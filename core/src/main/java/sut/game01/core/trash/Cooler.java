package sut.game01.core.trash;

import static playn.core.PlayN.*;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.Image;
import playn.core.*;
import playn.core.Keyboard;
import playn.core.ImageLayer;
import playn.core.ImageLayer;
import playn.core.Mouse;
import playn.core.PlayN;
import sut.game01.core.*;
import tripleplay.game.Screen;
import react.UnitSlot;
import tripleplay.game.UIScreen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;

import static playn.core.PlayN.graphics;

import sut.game01.core.sprite.*;
import playn.core.util.*;
import sut.game01.core.HomeScreen.*;


public class Cooler {
    private Sprite sprite;
    private int spriteIndex = 0;
    private boolean hasLoaded = false;
    private float x = 60;
    private int action = 0;
    private int a = 0;
    private World world;
    private Body body;
    static int hasThrownum =0;
    public enum State {
        IDLE
    }

    ;

    private State state = State.IDLE;

    private int e = 0;
    private int offset = 0;


    public Cooler(final World world,final float x_px, final float y_px) {

        sprite = SpriteLoader.getSprite("images/trash/cooler.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width() / 2f,
                        sprite.height() / 2f);
                sprite.layer().setTranslation(x_px, y_px);
                body = initPhysicsBody(world,
                        GameScreen.M_PER_PIXEL * x_px,
                        GameScreen.M_PER_PIXEL * y_px);

                hasLoaded = true;
            }

            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("Error loading image!", cause);
            }

        });


    }

    public Layer layer() {
        return sprite.layer();
    }

    public void update(int delta) {
        if (hasLoaded == false) return;
        //System.out.println(action);
        float move =0;
        float jump =0;
        e = e +delta;
        if (e > 150) {
            switch(state){
                case IDLE: offset =0; break;


            }
            spriteIndex = offset + ((spriteIndex +1 ) %1);
            sprite.setSprite(spriteIndex);
            e = 0;
        }

    }

    public void paint(Clock clock) {
        if(hasLoaded == false)return;


        sprite.layer().setTranslation(
                (body.getPosition().x /GameScreen.M_PER_PIXEL) +0,
                body.getPosition().y / GameScreen.M_PER_PIXEL);

        sprite.layer().setTranslation(
                (body.getPosition().x / GameScreen2.M_PER_PIXEL) +0,
                body.getPosition().y / GameScreen2.M_PER_PIXEL);

        sprite.layer().setTranslation(
                (body.getPosition().x / GameScreen3.M_PER_PIXEL) +0,
                body.getPosition().y / GameScreen3.M_PER_PIXEL);

        sprite.layer().setTranslation(
                (body.getPosition().x / GameScreen4.M_PER_PIXEL) +0,
                body.getPosition().y / GameScreen4.M_PER_PIXEL);

    }
    private Body initPhysicsBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0, 0);//แปลง pixel ให้เป็น m คือ เอา pixel ไปคูณ กับค่าคงที่
        body = world.createBody(bodyDef);
        //bodyDef.active = new Boolean(true);

        //PolygonShape shape = new PolygonShape();
        /*CircleShape shape = new CircleShape();
        shape.setRadius(0.7f);*/
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(40 * GameScreen.M_PER_PIXEL / 2,
                40 * GameScreen.M_PER_PIXEL / 2);

        FixtureDef fixtureDef = new FixtureDef();//น้ำหนัก
        fixtureDef.shape = shape;
        fixtureDef.density = 0.4f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.35f;

        body.createFixture(fixtureDef);
        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x, y), 0f);

        return body;
    }
    public static void hasThrow(int hasThrownum0){
        hasThrownum = hasThrownum0;
    }

    public Body getBody() {
        return body;
    }
}