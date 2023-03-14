package com.omeryildizce.survivorbird;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Texture bird;
    Texture bee1;
    Texture bee2;
    Texture bee3;

    float birdX = 0;
    float birdY = 0;
    float enemyVelocity = 10f;
    float birdSize = 0;
    int gameState = 0;
    float velocity = 0;
    float gravity = 0.3f;
    int numberOfEnemies = 4;
    float[] enemyX = new float[numberOfEnemies];
    Random random;
    float[] enemyOfSet1 = new float[numberOfEnemies];
    float[] enemyOfSet2 = new float[numberOfEnemies];
    float[] enemyOfSet3 = new float[numberOfEnemies];
    float distance = 0;
    Circle birdCircle;
    ShapeRenderer shapeRenderer;
    Circle[] enemyCircles1;
    Circle[] enemyCircles2;
    Circle[] enemyCircles3;
    int score = 0;
    int scoredEnemy = 0;
    BitmapFont font;
    BitmapFont fontGameOver;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("background.png");
        bird = new Texture("bird1.png");
        bee1 = new Texture("bee1.png");
        bee2 = new Texture("bee1.png");
        bee3 = new Texture("bee1.png");
        distance = Gdx.graphics.getWidth() / 2f;
        random = new Random();

        birdX = Gdx.graphics.getWidth() / 10f;
        birdY = Gdx.graphics.getHeight() / 2f;
        birdSize = Gdx.graphics.getHeight() / 12f;
        shapeRenderer = new ShapeRenderer();
        birdCircle = new Circle();
        enemyCircles1 = new Circle[numberOfEnemies];
        enemyCircles2 = new Circle[numberOfEnemies];
        enemyCircles3 = new Circle[numberOfEnemies];
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(4);
        fontGameOver = new BitmapFont();
        fontGameOver.setColor(Color.RED);
        fontGameOver.getData().setScale(4);
        for (int i = 0; i < numberOfEnemies; i++) {
            enemyOfSet1[i] = random.nextFloat() * Gdx.graphics.getHeight();
            enemyOfSet2[i] = random.nextFloat() * Gdx.graphics.getHeight();
            enemyOfSet3[i] = random.nextFloat() * Gdx.graphics.getHeight();
            enemyX[i] = Gdx.graphics.getWidth() + i * distance;
            enemyCircles1[i] = new Circle();
            enemyCircles2[i] = new Circle();
            enemyCircles3[i] = new Circle();
        }
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (gameState == 1) {
            if (enemyX[scoredEnemy] < birdX) {
                score++;
                if (scoredEnemy < numberOfEnemies - 1) {
                    scoredEnemy++;
                } else {
                    scoredEnemy = 0;
                }
            }
            if (Gdx.input.justTouched()) {
                velocity = -10;
            }
            for (int i = 0; i < numberOfEnemies; i++) {
                if (enemyX[i] < -birdSize) {
                    enemyX[i] += numberOfEnemies * distance;
                    enemyOfSet1[i] = (random.nextFloat() - 0.5f) * Gdx.graphics.getHeight();
                    enemyOfSet2[i] = (random.nextFloat() - 0.5f) * Gdx.graphics.getHeight();
                    enemyOfSet3[i] = (random.nextFloat() - 0.5f) * Gdx.graphics.getHeight();
                } else {
                    enemyX[i] -= enemyVelocity;
                }
                float enemyY1 = Gdx.graphics.getHeight() / 2 + enemyOfSet1[i] / 0.5f;
                float enemyY2 = Gdx.graphics.getHeight() / 2 + enemyOfSet2[i] / 1.5f;
                float enemyY3 = Gdx.graphics.getHeight() / 2 + enemyOfSet3[i] / 2.5f;
                batch.draw(bee1, enemyX[i], enemyY1, birdSize, birdSize);
                batch.draw(bee2, enemyX[i], enemyY2, birdSize, birdSize);
                batch.draw(bee3, enemyX[i], enemyY3, birdSize, birdSize);
                enemyCircles1[i] = new Circle(enemyX[i] + birdSize / 2, enemyY1 + birdSize / 2, birdSize / 2);
                enemyCircles2[i] = new Circle(enemyX[i] + birdSize / 2, enemyY2 + birdSize / 2, birdSize / 2);
                enemyCircles3[i] = new Circle(enemyX[i] + birdSize / 2, enemyY3 + birdSize / 2, birdSize / 2);


            }

            if (birdY > 0) {
                velocity += gravity;
                birdY = birdY - velocity;
                if (birdY > Gdx.graphics.getHeight()){
                    gameState = 2;

                }
            }
            else {
                gameState = 2;
            }
        } else if (gameState == 0) {
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        } else if (gameState == 2) {
            fontGameOver.draw(batch, "    Game Over!\n\nTap To Play Again!", Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 1.5f);
            if (Gdx.input.justTouched()) {
                gameState = 1;
                birdX = Gdx.graphics.getWidth() / 10f;
                birdY = Gdx.graphics.getHeight() / 2f;
                for (int i = 0; i < numberOfEnemies; i++) {
                    enemyOfSet1[i] = random.nextFloat() * Gdx.graphics.getHeight();
                    enemyOfSet2[i] = random.nextFloat() * Gdx.graphics.getHeight();
                    enemyOfSet3[i] = random.nextFloat() * Gdx.graphics.getHeight();
                    enemyX[i] = Gdx.graphics.getWidth() + i * distance;
                    enemyCircles1[i] = new Circle();
                    enemyCircles2[i] = new Circle();
                    enemyCircles3[i] = new Circle();
                }
                velocity = 0;
                scoredEnemy = 0;
                score = 0;
            }

        }

        batch.draw(bird, birdX, birdY, birdSize, birdSize);
        font.draw(batch, String.valueOf(score), Gdx.graphics.getWidth() * 0.03f, Gdx.graphics.getHeight() * 0.95f);
        batch.end();

        birdCircle.set(birdX + birdSize / 2, birdY + birdSize / 2, birdSize / 2);
        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(Color.BLACK);
        //shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius);

        for (int i = 0; i < numberOfEnemies; i++) {
            //shapeRenderer.circle(enemyX[i] + birdSize / 2, Gdx.graphics.getHeight() / 2 + enemyOfSet1[i] / 0.5f + birdSize / 2, birdSize / 2);
            //shapeRenderer.circle(enemyX[i] + birdSize / 2, Gdx.graphics.getHeight() / 2 + enemyOfSet2[i] / 1.5f + birdSize / 2, birdSize / 2);
            //shapeRenderer.circle(enemyX[i] + birdSize / 2, Gdx.graphics.getHeight() / 2 + enemyOfSet3[i] / 2.5f + birdSize / 2, birdSize / 2);

            if (Intersector.overlaps(birdCircle, enemyCircles1[i])
                    || Intersector.overlaps(birdCircle, enemyCircles2[i])
                    || Intersector.overlaps(birdCircle, enemyCircles3[i])) {
                gameState = 2;
            }
        }
        //shapeRenderer.end();
    }

    @Override
    public void dispose() {

    }
}
