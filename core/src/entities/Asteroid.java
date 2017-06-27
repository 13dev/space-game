package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.test.screens.MainGame;

public class Asteroid {
	public static final int SPEED = 180;
	public static final int WIDTH = 44;
	public static final int HEIGHT = 42;
	public static final float ANGLE_WAIT_TIME = 0f;
	private static Texture texture;
	private static TextureRegion region;
	
	float x, y;
	public boolean remove = false;
	public float rotation;
	public float randomScale;
	public float internalTimer;
	public double angle;
	
	public Asteroid (float x) {
		this.x = x;
		this.y = Gdx.graphics.getHeight() + HEIGHT;
		//this.rect = new CollisionRect(x, y, WIDTH, HEIGHT);
		
		if (texture == null)
			texture = new Texture("asteroid.png");
		
		region = new TextureRegion(texture, 0, 0, WIDTH, HEIGHT);
		rotation = MainGame.getRandomFloat(-360, 360);
		randomScale = MainGame.getRandomFloat(0.3f, 1);
		internalTimer = 0;
		angle = Math.atan2(MainGame.getShipY() - getY(), MainGame.getShipX()  - getX());
		
		System.out.println("Asteroid! x:" + x);
	}
	
	public void update (float deltaTime) {
		internalTimer += deltaTime;

		x += Math.cos(angle)* SPEED * deltaTime;
		
		y += Math.sin(angle) * SPEED * deltaTime;
		

		if ( y < -(HEIGHT) )
			remove = true;
		
		//Rotação do asteroide!
		
		if( rotation >= 0){
			rotation--;
			
		}else{
			rotation++;
		}
		
		
		//rect.move(x, y);
	}
	
	public void render (SpriteBatch batch) {
		batch.draw(region, x, y, WIDTH / 2f, HEIGHT / 2f, WIDTH, HEIGHT, randomScale, randomScale, rotation);
		
	}
	
	/*public CollisionRect getCollisionRect () {
		return rect;
	}*/
	
	public float getX () {
		return x;
	}
	
	public float getY () {
		return y;
	}
	
	public Rectangle getCollisionBox(){
		Rectangle collisionBox = new Rectangle(getX(), getY(), WIDTH, HEIGHT);
		return collisionBox;
	
	}
}
