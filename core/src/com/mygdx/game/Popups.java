//package com.mygdx.game;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Pixmap;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.utils.Align;
//
///**
// * Created by rkinnucan2330 on 3/6/2018.
// */
//
//public class Popups implements Screen{
//    Label welcomeLabel;
//    private BitmapFont labelFont = new BitmapFont();
//
//    String labelText;
//    int width;
//    int height;
//    int fontSize;
//
//    public Popups(String labelText, int width, int height, int fontSize) {
//        this.labelText = labelText;
//        this.width = width;
//        this.height = height;
//        this.fontSize = fontSize;
//
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/century.ttf"));
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = fontSize;
//        parameter.spaceX = 4;
//        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:$ ";
//        labelFont = generator.generateFont(parameter); // font size 80 pixels
//        generator.dispose(); // don't forget to dispose to avoid memory leaks!
//
//        Pixmap labelColor = new Pixmap(0, 0, Pixmap.Format.RGBA8888);
//        labelColor.setColor(Color.BLACK);
//        labelColor.fill();
//        Label.LabelStyle wLStyle = new Label.LabelStyle();
//        wLStyle.font = labelFont;
//        wLStyle.fontColor = Color.WHITE;
//        welcomeLabel = new Label(labelText, wLStyle);
//        welcomeLabel.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
//        welcomeLabel.setSize(width, height);
//        welcomeLabel.setPosition(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);
//        welcomeLabel.setAlignment(Align.center);
//    }
//}