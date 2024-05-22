package frame;

import game.GameState;
import game.componenets.*;
import game.componenets.GameTimer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FrameManager {

    private MainPanel mainPanel;

    private MainMenu mainMenu;
    private GameModeSelectionMenu gameModeSelectionMenu;
    private LevelSelectionMenu levelSelectionMenu;
    private GamePanel gamePanel;
    private GameTimer gameTimer;
    private ControlPanel controlPanel;
    private ReturnToMenuPanel returnToMenuPanel;
    private InformationPanel informationPanel;

    private ArrayList<JPanel> panels = new ArrayList<>();

    private GameState lastGameState;

    public FrameManager(MainPanel mainPanel, MainMenu mainMenu, GameModeSelectionMenu gameModeSelectionMenu, LevelSelectionMenu levelSelectionMenu, GamePanel gamePanel, GameTimer gameTimer, ControlPanel controlPanel, InformationPanel informationPanel, ReturnToMenuPanel returnToMenuPanel) {
        this.mainPanel = mainPanel;

        this.mainMenu = mainMenu;
        this.gameModeSelectionMenu = gameModeSelectionMenu;
        this.levelSelectionMenu = levelSelectionMenu;
        this.gamePanel = gamePanel;
        this.gameTimer = gameTimer;
        this.controlPanel = controlPanel;
        this.returnToMenuPanel = returnToMenuPanel;
        this.informationPanel = informationPanel;

        panels.add(this.mainMenu);
        panels.add(this.gameModeSelectionMenu);
        panels.add(this.levelSelectionMenu);
        panels.add(this.gamePanel);
        panels.add(this.gameTimer);
        panels.add(this.controlPanel);
        panels.add(this.returnToMenuPanel);
        panels.add(this.informationPanel);
    }

    public void update(GameState newGameState){
        System.out.println("last state :" + lastGameState);
        System.out.println("new state :" + newGameState);
        if (newGameState != lastGameState){
            switch (newGameState){
                case PLAYING, RESET_LEVEL, RUN_OUT_OF_TIME -> {

                    openAllExcept(new ArrayList<>(Arrays.asList(gameModeSelectionMenu,levelSelectionMenu, mainMenu)));
                    this.gamePanel.requestFocus();
                }
                case MAIN_MENU -> {
                    closeAllExcept(new ArrayList<>(Collections.singletonList(mainMenu)));
                    this.mainMenu.requestFocus();
                }
                case LEVEL_CHOICE -> {
                    closeAllExcept(new ArrayList<>(Collections.singletonList(levelSelectionMenu)));
                    this.levelSelectionMenu.requestFocus();
                }
                case GAME_MODE_CHOICE -> {
                    closeAllExcept(new ArrayList<>(Collections.singletonList(gameModeSelectionMenu)));

                    this.gameModeSelectionMenu.resetOption();
                    this.gameModeSelectionMenu.requestFocus();

                }
                default -> {
                    return;
                }
            }
            lastGameState = newGameState;
        }
    }

    private void closeAllExcept(ArrayList<JPanel> p){
        for (JPanel panel: panels) {
            panel.setVisible(p.contains(panel));
        }
    }

    private void openAllExcept(ArrayList<JPanel> p){
        for (JPanel panel: panels) {
            panel.setVisible(!p.contains(panel));
        }
    }
}
