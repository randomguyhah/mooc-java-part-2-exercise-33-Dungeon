/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author domenhocevar
 */
public class Dungeon {
    private LinkedList<Vampire> vampires;
    private Character player;
    private Random randomizer;
    private int moves;
    private boolean vampiresMove;
    private int length;
    private int height;
    
    public Dungeon(int length, int height, int moves, int vampires, boolean vampiresMove) {
        this.length = length;
        this.height = height;
        
        this.player = new Character(0, 0, length - 1, height - 1);
        
        this.moves = moves;
        
        this.vampiresMove = vampiresMove;
        
        Random randomizer = new Random();
        this.vampires = new LinkedList<Vampire>();
        while (this.vampires.size() < vampires) {
            int x = randomizer.nextInt(length);
            int y = randomizer.nextInt(height);
            Vampire newVampire = new Vampire(x, y, length - 1, height - 1);            
            
            boolean hasPlace = true;
            for (Vampire vampire : this.vampires) {
                if (vampire.haveSamePosition(newVampire)) {
                    hasPlace = false;
                }
            }
            if (newVampire.haveSamePosition(this.player)) {
                hasPlace = false;
            }
            
            if (hasPlace) {
                this.vampires.add(newVampire);
            }    
        }
    }
    
    public void run() {
        Scanner reader = new Scanner(System.in);
        while (this.moves > 0) {
            System.out.println(this.moves + "\n");

            this.printPositions();
            System.out.println();
            
            this.printDungeon();
            System.out.println("");
            
            String moves = reader.nextLine();
            executeMoves(moves);
            
            if (this.vampires.size() == 0) {
                break;
            }
        }
        
        if (this.vampires.size() == 0) {
            System.out.println("YOU WIN");
        } else {
            System.out.println("YOU LOSE");
        }
    }
    
    public void executeMoves(String moves) {
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'a') {
                this.playerMoveLeft();
            }
            if (moves.charAt(i) == 'd') {
                this.playerMoveRight();
            }
            if (moves.charAt(i) == 'w') {
                this.playerMoveUp();
            }
            if (moves.charAt(i) == 's') {
                this.playerMoveDown();
            }
        }
        
        this.moves--;
    }
    
    public void playerMoveLeft() {
        this.player.moveLeftRestricted();
        
        this.destroyVampiresOnPlayer();
        
        this.randomlyMoveVampires();
        
        this.destroyVampiresOnPlayer();
    }
    
    public void playerMoveRight() {
        this.player.moveRightRestricted();
        
        this.destroyVampiresOnPlayer();
        
        this.randomlyMoveVampires();
        
        this.destroyVampiresOnPlayer();
    }

    public void playerMoveUp() {
        this.player.moveUpRestricted();
        
        this.destroyVampiresOnPlayer();
        
        this.randomlyMoveVampires();
        
        this.destroyVampiresOnPlayer();
    }

    public void playerMoveDown() {
        this.player.moveDownRestricted();
        
        this.destroyVampiresOnPlayer();
        
        this.randomlyMoveVampires();
        
        this.destroyVampiresOnPlayer();
    }    
    
    public void destroyVampiresOnPlayer() {
        LinkedList<Vampire> toRemove = new LinkedList<Vampire>();
        for (Vampire vampire : this.vampires) {
            if (this.player.haveSamePosition(vampire)) {
                toRemove.add(vampire);
            }
        }
        
        this.vampires.removeAll(toRemove);
    }
    
    public void randomlyMoveVampires() {
        if (this.vampiresMove) {
            for(Vampire vampire : this.vampires) {
                vampire.randomMove(vampires);
            }
        }    
    }
    
    public int getNumOfVampires() {
        return this.vampires.size();
    }
    
    public void printPositions() {
        System.out.println("@ " + this.player.getX() + " " + this.player.getY());
        for (Vampire vampire : this.vampires) {
            System.out.println("v " + vampire.getX() + " " + vampire.getY());
        }
    }
    
    public void printDungeon() {
        boolean oneSymbolComplete = false;
        for (int y = 0; y < this.length; y++) {
            for (int x = 0; x < this.height; x++) {
                Character placeHolder = new Character(x, y, this.length - 1, this.height - 1);
                
                if (placeHolder.haveSamePosition(this.player)) {
                    System.out.print("@");
                    oneSymbolComplete = true;
                }
                
                if (!oneSymbolComplete) {
                    for (Character character : this.vampires) {
                        if (character.haveSamePosition(placeHolder)) {
                            System.out.print("v");
                            oneSymbolComplete = true;
                            break;
                        }
                    }
                }
                
                if (!oneSymbolComplete) {
                    System.out.print(".");
                }
                
                oneSymbolComplete = false;
            }
            
            System.out.println("");
        }
    }
    
}
