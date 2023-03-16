package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
// import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.Map;
// import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 10;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	public AssetSetter aSetter = new AssetSetter(this);

	// public final int worldWidth = tileSize * maxWorldCol;
	// public final int worldHeight = tileSize * maxWorldRow;

	// for full screen
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	// world setting
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	// public int maxWorldCol;
	// public int maxWorldRow;
	public final int maxMap = 10;
	public int currentMap = 0;

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	// entity and obj
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[6];
	int FPS = 60;
	TileManager tileM = new TileManager(this);
	ArrayList<Entity> entityList = new ArrayList<>();// create an arraylist of entity
	Map map = new Map(this);

	// game state
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int mapState = 10;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

	}

	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		// gameState = playState;
		gameState = titleState;

		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) tempScreen.getGraphics();

		setFullScreen();
	}

	public void setFullScreen() {
		// GET LOCAL XCREEN DEVICE
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);

		// GET FULLSCREEN WIDTH AND HEIGHT
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();

	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		// double nextDrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				// repaint();
				drawToTempScreen();
				drawToScreen();
				delta--;
				drawCount++;
			}

			if (timer >= 1000000000) {
				// System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}

			/*
			 * try { double remainingTime = nextDrawTime - System.nanoTime(); remainingTime
			 * = remainingTime / 1000000; if (remainingTime < 0) { remainingTime = 0; }
			 * Thread.sleep((long) remainingTime);
			 * 
			 * nextDrawTime += drawInterval; } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
		}

	}

	public void update() {

		// player
		player.update();
		// npc
		for (int i = 0; i < npc.length; i++) {
			if (npc[i] != null) {
				npc[i].update();
			}
		}
	}

	public void drawToTempScreen() {

		// CREATE TITLE SCREEN
		/*
		 * if (gameState == titleState) { // ui.draw(g2); } // OTHER else {
		 * tileM.draw(g2);
		 * 
		 * // OBJECT for (int i = 0; i < obj.length; i++) { if (obj[i] != null) {
		 * obj[i].draw(g2, this); } } // NPC for (int i = 0; i < npc.length; i++) { if
		 * (npc[i] != null) { npc[i].draw(g2); } } player.draw(g2); }
		 */

		// map.drawFullMapScreen(g2);

		map.drawFullMapScreen(g2);

		tileM.draw(g2);

		entityList.add(player);

		for (int i = 0; i < npc.length; i++) {
			if (npc[i] != null) {
				entityList.add(npc[i]);
			}
		}
		Collections.sort(entityList, new Comparator<Entity>() {
			public int compare(Entity e1, Entity e2) {
				int result = Integer.compare(e1.worldY, e2.worldY);
				return result;
			}
		});

		// draw entities
		for (int i = 0; i < entityList.size(); i++) {
			entityList.get(i).draw(g2);
		}

		// clear entity list
		entityList.clear();

		player.draw(g2);

		map.drawMiniMap(g2);

	}

	/*
	 * public void paintComponent(Graphics g) { super.paintComponent(g); Graphics2D
	 * g2 = (Graphics2D) g;
	 * 
	 * // CREATE TITLE SCREEN if (gameState == titleState) { // ui.draw(g2); } //
	 * OTHER else { tileM.draw(g2);
	 * 
	 * // OBJECT for (int i = 0; i < obj.length; i++) { if (obj[i] != null) {
	 * //obj[i].draw(g2, this); } } // NPC for (int i = 0; i < npc.length; i++) { if
	 * (npc[i] != null) { npc[i].draw(g2); } } player.draw(g2); }
	 * 
	 * tileM.draw(g2);
	 * 
	 * entityList.add(player);
	 * 
	 * for (int i = 0; i < npc.length; i++){ if(npc[i] != null){
	 * entityList.add(npc[i]); } } Collections.sort(entityList, new
	 * Comparator<Entity>() { public int compare(Entity e1, Entity e2){ int result =
	 * Integer.compare(e1.worldY, e2.worldY); return result; } }); //draw entities
	 * for (int i = 0; i<entityList.size(); i++){ entityList.get(i).draw(g2); }
	 * //clear entity list entityList.clear();
	 * 
	 * player.draw(g2); g2.dispose();
	 * 
	 * }
	 */

	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();

	}
}
