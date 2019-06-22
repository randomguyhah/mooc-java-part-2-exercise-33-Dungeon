/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

/**
 *
 * @author domenhocevar
 */
public class Character {
    private int x;
    private int y;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    
    public Character(int x, int y, int minY, int maxY, int minX, int maxX) {
        this.x = x;
        this.y = y;
        this.minY = minY;
        this.maxY = maxY;
        this.minX = minX;
        this.maxX = maxX;
    }
    
    public Character(int x, int y, int maxY, int maxX) {
        this(x, y, 0, maxY, 0, maxX);
    }
    
    public void moveLeft() {
        this.x--; 
    }
    
    public void moveLeftRestricted() {
        if (this.minX < this.x) {
            this.moveLeft();
        }
    }
    
    public void moveRightRestricted() {
        if (this.maxX > this.x) {
            this.moveRight();
        }        
    }
    
    public void moveUpRestricted() {
        if (this.minY < this.y) {
            this.moveUp();
        }
    }
    
    public void moveDownRestricted() {
        if (this.maxY > this.y) {
            this.moveDown();
        }
    }
    
    public void moveRight() {
        this.x++;
    }
    
    public void moveDown() {
        this.y++;
    }
    
    public void moveUp() {
        this.y--;
    }
    
    public boolean haveSamePosition(Character character) {
        return this.wouldHaveSamePositionAfterAMove(0, 0, character);
    }
    
    public boolean wouldHaveSamePositionAfterAMove(int dx, int dy, Character character) {
        if (this.x + dx == character.getX() && this.y + dy == character.getY()) {
            return true;
        }
        
        return false;       
        
    }
        
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
}
