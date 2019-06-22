/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import java.util.List;
import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author domenhocevar
 */
public class Vampire extends Character {

    
    public Vampire(int x, int y, int maxX, int maxY) {
        super(x, y, maxX, maxY);
    }
    
    public void moveLeft(List<Vampire> vampires) {
        for (Vampire vampire : vampires) {
            if (this.wouldHaveSamePositionAfterAMove(-1, 0, vampire)) {
                return;
            }
        }

       this.moveLeftRestricted();
    }
    
    public void moveRight(List<Vampire> vampires) {
        for (Vampire vampire : vampires) {
            if (this.wouldHaveSamePositionAfterAMove(1, 0, vampire)) {
                return;
            }
        }

       this.moveRightRestricted();        
    }
    
    public void moveUp(List<Vampire> vampires) {
        for (Vampire vampire : vampires) {
            if (this.wouldHaveSamePositionAfterAMove(0, -1, vampire)) {
                return;
            }
        }

       this.moveUpRestricted();        
    }
    
    public void moveDown(List<Vampire> vampires) {
        for (Vampire vampire : vampires) {
            if (this.wouldHaveSamePositionAfterAMove(0, 1, vampire)) {
                return;
            }
        }

       this.moveDownRestricted();                
    }
    
    public void randomMove(List<Vampire> vampires) {
        Random randomizer = new Random();
        int i = randomizer.nextInt(4);
        
        if (i == 0) {
            this.moveLeft(vampires);
        }
        if (i == 1) {
            this.moveRight(vampires);
        }
        if (i == 2) {
            this.moveUp(vampires);
        }
        if (i == 3) {
            this.moveDown(vampires);
        }
    }
}
