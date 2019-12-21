/*
package agh.cs.lab2;

import agh.cs.lab5.RectangularMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingMap extends JFrame{ //implements ActionListener {
    //JTextField tf;
    JTextArea mapArea;
    JLabel mapTitle;
    JButton arrowUp;
    JButton arrowDown;
    JButton arrowLeft;
    JButton arrowRight;
    JTextField animalText;

    public SwingMap(RectangularMap map) {
        mapTitle = new JLabel("Map 1");
        mapTitle.setBounds(50, 20, 150, 20);
        mapArea = new JTextArea(map.toString());
        mapArea.setBounds(25, 50, map.getUpperright().x*17+90, map.getUpperright().y*20+90);
        mapArea.setFont(new Font("monospaced", Font.PLAIN, 14));
        arrowUp = new JButton("^");
        arrowDown = new JButton("v");
        arrowLeft = new JButton("<");
        arrowRight = new JButton(">");
        animalText = new JTextField();
        BasicObjects.Vector2d buttonsCenter = new BasicObjects.Vector2d(map.getUpperright().x*40+100, map.getUpperright().y*15+100);
        BasicObjects.Vector2d buttonDim = new BasicObjects.Vector2d(25, 25);
        arrowUp.setBounds(buttonsCenter.x, buttonsCenter.y-buttonDim.y, buttonDim.x, buttonDim.y);
        arrowDown.setBounds(buttonsCenter.x, buttonsCenter.y+buttonDim.y, buttonDim.x, buttonDim.y);
        arrowLeft.setBounds(buttonsCenter.x-buttonDim.x, buttonsCenter.y, buttonDim.x, buttonDim.y);
        arrowRight.setBounds(buttonsCenter.x+buttonDim.x, buttonsCenter.y, buttonDim.x, buttonDim.y);
        animalText.setBounds(buttonsCenter.x, buttonsCenter.y, buttonDim.x, buttonDim.y);
        arrowUp.setMargin(new Insets(0, 0, 0, 0));
        arrowDown.setMargin(new Insets(0, 0, 0, 0));
        arrowLeft.setMargin(new Insets(0, 0, 0, 0));
        arrowRight.setMargin(new Insets(0, 0, 0, 0));
        animalText.setMargin(new Insets(0, 0, 0, 0));
        arrowUp.setFocusPainted(false);arrowDown.setFocusPainted(false);arrowRight.setFocusPainted(false);arrowLeft.setFocusPainted(false);
        add(arrowUp);add(arrowDown);add(arrowLeft);add(arrowRight);
        add(animalText);
        add(mapTitle);
        add(mapArea);
        arrowUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int animalNumber = Integer.parseInt(animalText.getText());
                map.moveAnimal(map.getAnimals().get(animalNumber), BasicObjects.MoveDirection.FORWARD);
                mapArea.setText(map.toString());
            }
        });
        arrowDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int animalNumber = Integer.parseInt(animalText.getText());
                map.moveAnimal(map.getAnimals().get(animalNumber), BasicObjects.MoveDirection.BACKWARD);
                mapArea.setText(map.toString());
            }
        });
        arrowLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int animalNumber = Integer.parseInt(animalText.getText());
                map.moveAnimal(map.getAnimals().get(animalNumber), BasicObjects.MoveDirection.LEFT);
                mapArea.setText(map.toString());
            }
        });
        arrowRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int animalNumber = Integer.parseInt(animalText.getText());
                map.moveAnimal(map.getAnimals().get(animalNumber), BasicObjects.MoveDirection.RIGHT);
                mapArea.setText(map.toString());
            }
        });
        setSize(map.getUpperright().x*50+150, map.getUpperright().y*50+150);
        setBackground(new Color(100,100,0));
        setLayout(null);
        setVisible(true);
    }
}
*/