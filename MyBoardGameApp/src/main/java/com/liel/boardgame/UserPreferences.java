package com.liel.boardgame;


import java.io.*;

public class UserPreferences {

    private static UserPreferences instance = new UserPreferences();
    private boolean isScrollable;
    private String boardLength = "6 x 6";
    private int numberOfPlayers = 4;

    private UserPreferences() {

    }

    public static UserPreferences getInstance() {
        return instance;
    }

    public boolean isScrollable() {
        return isScrollable;
    }

    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getBoardLength() {
        return boardLength;
    }

    public void setBoardLength(String boardLength) {
        this.boardLength = boardLength;
    }


    public void saveData() {
        File file = new File("settings.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.valueOf(this.isScrollable));
            writer.write("\n");
            writer.write(String.valueOf(this.boardLength));
            writer.write("\n");
            writer.write(String.valueOf(this.numberOfPlayers));
            System.out.println("Data saved");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadData() {
        File file = new File("settings.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String isScrollable = reader.readLine();
            String boardSize = reader.readLine();
            String numPlayers = reader.readLine();
            this.isScrollable = Boolean.parseBoolean(isScrollable);
            this.boardLength = boardSize;
            this.numberOfPlayers = Integer.parseInt(numPlayers);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
