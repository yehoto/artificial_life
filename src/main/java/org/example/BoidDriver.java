package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BoidDriver {

    // Запоминаем время запуска программы
    private static final long startTime = System.currentTimeMillis();
    public static void main(String[] args) {
        // Создаем основное окно
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true); // Убираем декоративные элементы окна

        // Получаем размер экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);

        // Создаем панель для симуляции
        JPanel simulationPanel = new JPanel();
        simulationPanel.setPreferredSize(new Dimension((int)(screenSize.width * 0.85), screenSize.height));
        simulationPanel.setLayout(new BorderLayout());

        // Добавляем симуляцию на свою панель
        BoidRunner simulation = new BoidRunner();
        simulationPanel.add(simulation, BorderLayout.CENTER);


        // Создаем информационную панель
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension((int)(screenSize.width * 0.15), screenSize.height));

        JButton closeButton = new JButton("закрыть");
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Выравниваем по центру
        closeButton.setMaximumSize(new Dimension(((int)(screenSize.getWidth()* 0.15)), 50)); // Фиксированная высота
        closeButton.addActionListener(e -> System.exit(0)); // Закрываем приложение


        JButton minimizeButton = new JButton("свернуть");
        minimizeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Выравниваем по центру
        minimizeButton.setMaximumSize(new Dimension(((int)(screenSize.getWidth()* 0.15)), 50)); // Фиксированная высота
        minimizeButton.addActionListener(e -> frame.setState(JFrame.ICONIFIED)); // Сворачиваем окно


        // Устанавливаем цвет фона и текста для кнопок
        closeButton.setBackground(new Color(64, 64, 64)); // Темно-серый цвет фона
        closeButton.setForeground(Color.WHITE); // Белый цвет текста
        minimizeButton.setBackground(new Color(64, 64, 64)); // Темно-серый цвет фона
        minimizeButton.setForeground(Color.WHITE); // Белый цвет текста


        // Добавляем кнопки на информационную панель
        infoPanel.add(closeButton);
        infoPanel.add(minimizeButton);

        // Создаем метку для отображения времени
        JLabel timerLabel = new JLabel("Время: 00:00:00");
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Создаем таймер, который обновляет метку каждую секунду
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedMillis = System.currentTimeMillis() - startTime;
                String timeString = formatDuration(elapsedMillis);
                timerLabel.setText("Время: " + timeString);
            }
        });
        timer.start();

        // Добавляем метку времени на информационную панель
        infoPanel.add(timerLabel);


        // Добавляем панели на основное окно
        frame.add(simulationPanel, BorderLayout.WEST);
        frame.add(infoPanel, BorderLayout.EAST);

        // Устанавливаем размер и делаем окно видимым
        frame.pack();
        frame.setLocationRelativeTo(null); // Центрируем окно
        frame.setVisible(true);

        // Устанавливаем фокус на кнопку "закрыть"
        //closeButton.requestFocusInWindow();

        // Запускаем симуляцию
        simulation.run();
    }

    private static String formatDuration(long millis) {
        long seconds = millis / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}

