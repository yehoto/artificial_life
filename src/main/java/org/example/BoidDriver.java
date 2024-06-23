package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BoidDriver {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 0);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setPreferredSize(new Dimension(1920, 1080));
        frame.setPreferredSize(new Dimension((int)screenSize.getWidth(),(int) screenSize.getHeight()));
       // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
        // Делаем окно на весь экран

        // Убираем декоративные элементы окна
        frame.setUndecorated(true);

        // Создаем панели для симуляции и вывода информации
        JPanel simulationPanel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel(new BorderLayout());
        // Создаем компоновку для разделения окна

        // Добавляем симуляцию на свою панель
        BoidRunner simulation = new BoidRunner();
        simulationPanel.add(simulation, BorderLayout.CENTER);

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel(new BorderLayout());

        // Создаем кнопку закрытия
        JButton closeButton = new JButton("Закрыть");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Закрываем окно
            }
        });

        // Создаем кнопку свернуть
        JButton minimizeButton = new JButton("Свернуть");
        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setState(JFrame.ICONIFIED); // Сворачиваем окно
            }
        });

        // Добавляем кнопки в buttonPanel
        buttonPanel.add(closeButton, BorderLayout.NORTH);
        buttonPanel.add(minimizeButton, BorderLayout.SOUTH);

        // Добавляем buttonPanel в infoPanel
        infoPanel.add(buttonPanel, BorderLayout.NORTH);
//        infoPanel.revalidate();
//        infoPanel.repaint();

//        // Создаем компоновку для разделения окна
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, simulationPanel, infoPanel);
        // Получаем размер экрана
        splitPane.setDividerLocation(((int)screenSize.getWidth())-200); // Делим окно пополам

        splitPane.setEnabled(false); // Делаем разделитель неперемещаемым
        splitPane.setDividerSize(0);
        frame.add(splitPane);


        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        // Запускаем симуляцию
        simulation.run();
    }
}
