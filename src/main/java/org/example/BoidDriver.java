package org.example;

import javax.swing.*;
import java.awt.*;
//public class BoidDriver {
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Natural selection");
//        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        frame.setLocation(0, 0);
//        frame.setPreferredSize(new Dimension(1920, 1080));
//        BoidRunner simulation = new BoidRunner();
//        frame.setResizable(false);
//        frame.add(simulation);
//        frame.pack();
//        frame.setVisible(true);
//        simulation.run();
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoidDriver {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 0);
        //frame.setPreferredSize(new Dimension(2000, 800));
        // Делаем окно на весь экран

        // Убираем декоративные элементы окна
        frame.setUndecorated(true);
        // Создаем кнопку закрыти




        // Создаем панели для симуляции и вывода информации
        JPanel simulationPanel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel(new BorderLayout());

        // Добавляем симуляцию на свою панель
        BoidRunner simulation = new BoidRunner();
        simulationPanel.add(simulation, BorderLayout.CENTER);

        // Добавляем информацию на свою панель
        // JTextArea infoArea = new JTextArea("Информация о симуляции");
      //  infoPanel.add(infoArea, BorderLayout.CENTER);

        // Создаем кнопку закрытия
        JButton closeButton = new JButton("Закрыть");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Закрываем окно
            }
        });
        //closeButton.setPreferredSize(new Dimension(100, 30));
        // Создаем кнопку свернуть
        JButton minimizeButton = new JButton("Свернуть");
        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setState(JFrame.ICONIFIED); // Сворачиваем окно
            }
        });
       // minimizeButton.setPreferredSize(new Dimension(100, 30));

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel(new BorderLayout());
        // Добавляем кнопки в buttonPanel
        buttonPanel.add(closeButton, BorderLayout.NORTH);
        buttonPanel.add(minimizeButton, BorderLayout.SOUTH);

        // Добавляем buttonPanel в infoPanel
        infoPanel.add(buttonPanel, BorderLayout.NORTH);


        // Добавляем кнопку в infoPanel (например, в южную часть)
        //infoPanel.add(closeButton,BorderLayout.NORTH);
        //infoPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Добавляем пробел между кнопками
        //infoPanel.add(minimizeButton,BorderLayout.EAST);


        // Создаем компоновку для разделения окна
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, simulationPanel, infoPanel);
        // Получаем размер экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        splitPane.setDividerLocation(screenSize.width-200); // Делим окно пополам
        //splitPane.setOneTouchExpandable(false); // Делаем разделитель неперемещаемым
        splitPane.setEnabled(false); // Делаем разделитель неперемещаемым
        splitPane.setDividerSize(0);
        frame.add(splitPane);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
        // Запускаем симуляцию
        simulation.run();
    }
}
