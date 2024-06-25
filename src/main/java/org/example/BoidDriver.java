package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoidDriver {
        static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1];
        private static final long startTime = System.currentTimeMillis();

        public static void main(String[] args) throws InterruptedException, InvocationTargetException {
                BoidRunner simulation = new BoidRunner();
                SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {

                // Создаем и запускаем симуляцию


                                // Создаем основное окно
                                JFrame frame = new JFrame();
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setUndecorated(true); // Убираем декоративные элементы окна

                                // Получаем размер экрана
                                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                              // frame.setSize(screenSize);
                               // frame.setExtendedState(Frame.MAXIMIZED_BOTH);

                                // Создаем панель для симуляции
                                JPanel simulationPanel = new JPanel();
                                simulationPanel.setPreferredSize(new Dimension((int)(screenSize.width * 0.80), screenSize.height));
                                simulationPanel.setLayout(new BorderLayout());
                                simulationPanel.add(simulation, BorderLayout.CENTER);

                                // Создаем информационную панель
                                JPanel infoPanel = new JPanel();
                                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                                //infoPanel.setPreferredSize(new Dimension((int)(screenSize.width * 0.15), screenSize.height));


                                // Создаем кнопки
                                JButton closeButton = new JButton("закрыть");
                                closeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Выравниваем по центру
                                closeButton.setMaximumSize(new Dimension(((int)(screenSize.getWidth() * 0.20)), 50)); // Фиксированная высота
                                closeButton.addActionListener(e -> System.exit(0)); // Закрываем приложение

                                JButton minimizeButton = new JButton("свернуть");
                                minimizeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Выравниваем по центру
                                minimizeButton.setMaximumSize(new Dimension(((int)(screenSize.getWidth() * 0.20)), 50)); // Фиксированная высота
                                minimizeButton.addActionListener(e -> frame.setState(JFrame.ICONIFIED)); // Сворачиваем окно

                                // Устанавливаем цвет фона и текста для кнопок
                                closeButton.setBackground(new Color(64, 64, 64)); // Темно-серый цвет фона
                                closeButton.setForeground(Color.WHITE); // Белый цвет текста
                                minimizeButton.setBackground(new Color(64, 64, 64)); // Темно-серый цвет фона
                                minimizeButton.setForeground(Color.WHITE); // Белый цвет текста

                                // Добавляем кнопки на информационную панель
                                infoPanel.add(closeButton);
                                infoPanel.add(minimizeButton);

                                // Пример использования функции
                               // createSlider(0,false,10,10.0,1,10,infoPanel, "потеря здоровья в сек.: -", 0, 10, 5, new Color(255, 255, 255), new Color(0, 0, 0), new Color(117, 8, 30));
                                createSlider(0,false,100,100.0,1,10,infoPanel, "потеря здоровья в сек: -", 0, 100, 1, new Color(255, 255, 255), new Color(0, 0, 0), new Color(117, 8, 30));
                                createSlider(1,true,10,10.0,1,5,infoPanel, "клонирование через сек.: ", 0, 100, 50, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));
                                //createSlider(1,true,1,1.0,10,10,infoPanel, "клонирование через сек.: ", 0, 10, 3, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));
                                 createSlider(2,true,10,10.0,1,5,infoPanel, "польза еды для здоровья: +", 0, 50, 10, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));
                                 createSlider(3,false,10,10.0,1,5,infoPanel, "вред яда для здоровья: -", 0, 50, 20, new Color(255, 255, 255), new Color(0, 0, 0), new Color(117, 8, 30));
                                //createSlider(4,true,1,1.0,10,10,infoPanel, "воспроизводство еды сек.: ", 0, 100, 3, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));
                               // createSlider(5,true,1,1.0,10,10,infoPanel, "воспроизводство яда сек.: ", 0, 10, 3, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));
                                createSlider(4,true,10,10.0,1,5,infoPanel, "доп.еда через сек.: ", 0, 100, 50, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));
                                createSlider(5,true,10,10.0,1,5,infoPanel, "доп.яд через сек.: ", 0, 100, 50, new Color(255, 255, 255), new Color(0, 0, 0), new Color(117, 8, 30));
                                createSlider(6,true,1,1.0,10,10,infoPanel, "max скорость : ", 0, 10, 2, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));
                                createSlider(7,true,10,10.0,10,10,infoPanel, "max сила : ", 0, 10, 5, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));
                                createSlider(8,true,100,100.0,1,10,infoPanel, "вероятность мутации гена: ", 0, 100, 50, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));

                                JLabel sliderLabel = new JLabel("                      интервалы мутации генов");
                                sliderLabel.setOpaque(true);
                                sliderLabel.setForeground(Color.GREEN);
                                sliderLabel.setBackground(Color.BLACK);
                                sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                sliderLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, sliderLabel.getPreferredSize().height));
                                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
                                infoPanel.add(sliderLabel);



                                createSlider(9,true,10,10.0,1,5,infoPanel, "сила притяжения еды +/- : ", 0, 50, 30, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));
                                createSlider(10,true,10,10.0,1,5,infoPanel, "сила притяжения яда +/- : ", 0, 50, 30, new Color(255, 255, 255), new Color(0, 0, 0), new Color(117, 8, 30));
                                // Добавляем слайдер под кнопки в infoPanel
                                //infoPanel.add(foodSlider);
                                createSlider(11,true,1,1.0,10,10,infoPanel, "радиус восприятия еды : ", 0, 100, 3, new Color(255, 255, 255), new Color(0, 0, 0), new Color(32, 120, 56));
                                createSlider(12,true,1,1.0,10,10,infoPanel, "радиус восприятия яда : ", 0, 100, 3, new Color(255, 255, 255), new Color(0, 0, 0), new Color(117, 8, 30));

                                JLabel Label = new JLabel("                                доп. комманды");
                                Label.setOpaque(true);
                                Label.setForeground(Color.GREEN);
                                Label.setBackground(Color.BLACK);
                                Label.setAlignmentX(Component.CENTER_ALIGNMENT);
                                Label.setMaximumSize(new Dimension(Integer.MAX_VALUE, sliderLabel.getPreferredSize().height));
                                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
                                infoPanel.add(Label);

                                JLabel Label1 = new JLabel("<html>добавить еду: лкм<br>добавить яд: пкм<br>добавить новый организм: скм<br>удалить всю еду: Ctrl+F<br>удалить весь яд: Ctrl+P<br>удалит все организмы: Ctrl+O</html>");
                                Label1.setOpaque(true);
                                Label1.setForeground(Color.BLACK);
// Label.setBackground(Color.BLACK);
                                Label1.setAlignmentX(Component.CENTER_ALIGNMENT);
                                Label1.setMaximumSize(new Dimension(Integer.MAX_VALUE, Label1.getPreferredSize().height));
                                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
                                infoPanel.add(Label1);
                                // Создаем метку для отображения времени
                                JLabel timerLabel = new JLabel("Время: 00:00:00");
                                timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                infoPanel.add(timerLabel);

                                // Создаем таймер, который обновляет метку каждую секунду
                                Timer timer = new Timer(1000, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                                long elapsedMillis = System.currentTimeMillis() - startTime;
                                                String timeString = formatDuration(elapsedMillis);
                                                timerLabel.setText("Время: " + timeString);
//                                                if (!BoidRunner.flock.isEmpty()) {
//                                                        BoidRunner.healthTick();
//                                                }
                                        }
                                });
                                timer.start();



                  // Добавляем панели на основное окно
                  frame.add(simulationPanel, BorderLayout.WEST);

                                // Создаем JScrollPane, в который помещаем infoPanel
                                JScrollPane scrollPane = new JScrollPane(infoPanel);
                                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                scrollPane.setPreferredSize(new Dimension((int)(screenSize.width * 0.20), screenSize.height));

// Добавляем scrollPane вместо infoPanel в основное окно
                                frame.add(scrollPane, BorderLayout.EAST);
                //  frame.add(infoPanel, BorderLayout.EAST);

                  // Устанавливаем размер и делаем окно видимым
                  frame.pack();
                                device.setFullScreenWindow(frame);
                  frame.setLocationRelativeTo(null); // Центрируем окно

                  frame.setVisible(true);
                        }
                });
                SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                                // Создаем SwingWorker для выполнения симуляции в фоновом режиме
                                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                                        @Override
                                        protected Void doInBackground() throws Exception {
                                                // Запускаем симуляцию в фоновом потоке
                                                simulation.run();
                                                return null;
                                        }

                                        @Override
                                        protected void done() {
                                                // Этот метод выполняется в EDT после завершения doInBackground
                                                // Здесь можно обновить GUI по результатам симуляции, если это необходимо
                                        }
                                };
                                worker.execute(); // Запускаем SwingWorker
                                // simulation.run();
        }
});




        }
        private static String formatDuration(long millis) {
                long seconds = millis / 1000;
                long hours = seconds / 3600;
                long minutes = (seconds % 3600) / 60;
                long secs = seconds % 60;
                return String.format("%02d:%02d:%02d", hours, minutes, secs);
        }

        // Функция для создания JSlider
        // Функция для создания JSlider
        public static void createSlider(int n,boolean positive,int del1,double otobrach,int minny,int del,JPanel panel, String labelPrefix, int min, int max, int initialValue, Color background, Color foreground, Color labelColor) {
                JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, initialValue);
                slider.setMajorTickSpacing(max / del);
                slider.setMinorTickSpacing(minny);
                slider.setPaintTicks(true);
                slider.setPaintLabels(true);
                slider.setBackground(background);
                slider.setForeground(foreground);
                slider.setAlignmentX(Component.CENTER_ALIGNMENT);



                // Создаем пользовательские метки для слайдера
                Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
                for (int i = min; i <= max; i += max / del) {
               // for (int i = min; i <= max; i += 10) {
                        if(positive) {
                                labelTable.put(i, new JLabel(String.valueOf((float) i / del1)));
                        }else{
                                labelTable.put(i, new JLabel(String.valueOf("-"+ ((float) i / del1))));
                        }
                }
                slider.setLabelTable(labelTable);

                // Создаем подпись для слайдера
                JLabel sliderLabel = new JLabel(labelPrefix + initialValue / otobrach);
                sliderLabel.setForeground(labelColor);
                sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Добавляем слушателя для отслеживания изменений значения слайдера
                slider.addChangeListener(new ChangeListener() {
                        @Override
                        public void stateChanged(ChangeEvent e) {
                                JSlider source = (JSlider) e.getSource();
                                double value = source.getValue() / otobrach;
                                sliderLabel.setText(labelPrefix + value);

                                switch(n){
                                        case (0): BoidRunner.health_tick=value;
                                        break;
                                        case(1): {
                                                BoidRunner.childInSec = value;

                                                        //BoidRunner.timer1.schedule(BoidRunner.task1, 0, (int)(BoidRunner.childInSec*1000));
                                                        // Отменяем предыдущую задачу, если она уже запущена
                                                        if (BoidRunner.task1 != null) {
                                                                BoidRunner.task1.cancel();
                                                                BoidRunner.timer1.purge();
                                                        }
                                                if (value!=0) {

// Создаём новую задачу
                                                        BoidRunner.task1 = new TimerTask() {
                                                                @Override
                                                                public void run() {
                                                                        int i = 0;
                                                                        if(! BoidRunner.flock.isEmpty()) {
                                                                                do {
                                                                                        Random rand = new Random();
                                                                                        i = rand.nextInt(BoidRunner.flock.size());
                                                                                        // Получаем Boid по случайному индексу и добавляем его потомка в список
                                                                                } while (BoidRunner.flock.get(i).dead());
                                                                                BoidRunner.flock.add(BoidRunner.flock.get(i).getChild());
                                                                        }
                                                                }
                                                        };

// Планируем задачу с новым интервалом
                                                        BoidRunner.timer1.schedule(BoidRunner.task1, 0, (int) (BoidRunner.childInSec * 1000));
                                                }
                                        }
                                        break;
                                        case(2): BoidRunner.food_value=value;
                                        break;
                                        case(3): BoidRunner.poison_value=value;
                                        break;
                                        case(4): {

                                                        BoidRunner.foodInSec = value;

                                                        //BoidRunner.timer1.schedule(BoidRunner.task1, 0, (int)(BoidRunner.childInSec*1000));
                                                        // Отменяем предыдущую задачу, если она уже запущена
                                                        if (BoidRunner.task2 != null) {
                                                                BoidRunner.task2.cancel();
                                                                BoidRunner.timer2.purge();
                                                        }
                                                        if (value!=0) {

// Создаём новую задачу
                                                                BoidRunner.task2 = new TimerTask() {
                                                                        @Override
                                                                        public void run() {


                                                                                        BoidRunner.goodFood.add(new Food(false));

                                                                        }
                                                                };

// Планируем задачу с новым интервалом
                                                                BoidRunner.timer2.schedule(BoidRunner.task2, 0, (int) (BoidRunner.foodInSec * 1000));
                                                        }
                                        }
                                        break;
                                        case(5):{
                                                BoidRunner.poisonInSec = value;

                                                //BoidRunner.timer1.schedule(BoidRunner.task1, 0, (int)(BoidRunner.childInSec*1000));
                                                // Отменяем предыдущую задачу, если она уже запущена
                                                if (BoidRunner.task3 != null) {
                                                        BoidRunner.task3.cancel();
                                                        BoidRunner.timer3.purge();
                                                }
                                                if (value!=0) {

// Создаём новую задачу
                                                        BoidRunner.task3 = new TimerTask() {
                                                                @Override
                                                                public void run() {


                                                                        BoidRunner.badFood.add(new Food(true));

                                                                }
                                                        };

// Планируем задачу с новым интервалом
                                                        BoidRunner.timer3.schedule(BoidRunner.task3, 0, (int) (BoidRunner.poisonInSec * 1000));
                                                }

                                        }
                                        break;
                                        case(6):BoidRunner.maxSpeed=value;
                                                break;
                                        case(7):BoidRunner.max_force=value;
                                                break;



                                }

                        }
                });

                // Добавляем подпись и слайдер на панель
                panel.add(sliderLabel);
                panel.add(slider);


        }

}



