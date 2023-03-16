package main;

import entity.NPC_OldMan;
import main.GamePanel;

import java.util.ArrayList;
import java.util.Random;

import entity.Entity;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {

	}

	public void setNPC() {
		ArrayList<Integer> listPosX = new ArrayList<Integer>();
		ArrayList<Integer> listPosY = new ArrayList<Integer>();
		listPosX.add(21);
		listPosY.add(21);

		listPosX.add(38);
		listPosY.add(12);

		listPosX.add(21);
		listPosY.add(41);

		listPosX.add(36);
		listPosY.add(38);

		listPosX.add(10);
		listPosY.add(34);
		Random random = new Random();
		for (int i = 0; i < gp.npc.length; i++) {
			int pos = random.nextInt(5);
			gp.npc[i] = new NPC_OldMan(gp);
			gp.npc[i].worldX = gp.tileSize * listPosX.get(pos);
			gp.npc[i].worldY = gp.tileSize * listPosY.get(pos);
			listPosX.set(pos, listPosX.get(pos) + 1);
			System.out.println("position spawn" + listPosX.get(pos) + ' ' + listPosY.get(pos));
		}
	}
}
