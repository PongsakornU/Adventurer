package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
	public boolean showDebugText;
	GamePanel gp;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public KeyHandler() {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		/*
		 * if(code == KeyEvent.VK_M) { gp.gameState = gp.mapState; } if(gp.gameState ==
		 * gp.mapState) { mapState(code); }
		 */

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		/*
		 * if(gp.gameState == gp.mapState) { mapState(code); }
		 */
		/*
		 * if(code == KeyEvent.VK_M) { rightPressed = false; }
		 */

	}

	/*
	 * public void mapState(int code) { if(code == KeyEvent.VK_M) { gp.gameState =
	 * gp.playState; } }
	 */
	public void titleState(int code) {
		/*
		 * if(gp.gameState == gp.mapState) { mapState(code); }
		 */

	}
	/*
	 * public void playState(int code) {
	 * 
	 * if(code == KeyEvent.VK_W) { upPressed = true; } if(code == KeyEvent.VK_S) {
	 * downPressed = true; } if(code == KeyEvent.VK_A) { leftPressed = true; }
	 * if(code == KeyEvent.VK_D) { rightPressed = true; } if(code == KeyEvent.VK_M)
	 * { gp.gameState = gp.mapState; } /*if(code == KeyEvent.VK_W) { upPressed =
	 * false; } if(code == KeyEvent.VK_S) { downPressed = false; } if(code ==
	 * KeyEvent.VK_A) { leftPressed = false; } if(code == KeyEvent.VK_D) {
	 * rightPressed = false; } if(code == KeyEvent.VK_ESCAPE) { gp.gameState =
	 * gp.optionState; } } public void optionsState(int code) { if(code ==
	 * KeyEvent.VK_ESCAPE) { gp.gameState = gp.playState; } if(code ==
	 * KeyEvent.VK_ENTER) { enterPressed = true; }
	 * 
	 * }
	 */

}
