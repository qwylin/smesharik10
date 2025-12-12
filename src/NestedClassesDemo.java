import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NestedClassesDemo extends JFrame {
    // Статический вложенный класс
    public static class Configuration {
        public static final String APP_NAME = "Демо вложенных классов";
        public static final int WIDTH = 400;
        public static final int HEIGHT = 300;
    }

    // Внутренний класс (нестатический вложенный класс)
    private class ButtonHandler implements ActionListener {
        private String buttonName;

        public ButtonHandler(String buttonName) {
            this.buttonName = buttonName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(NestedClassesDemo.this,
                    "Нажата кнопка: " + buttonName + "\n" +
                            "Это обработчик внутреннего класса",
                    "Информация", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public NestedClassesDemo() {
        super(Configuration.APP_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Configuration.WIDTH, Configuration.HEIGHT);
        setLocationRelativeTo(null);

        createUI();
    }

    private void createUI() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Кнопка с обработчиком внутреннего класса
        JButton innerClassButton = new JButton("Внутренний класс");
        innerClassButton.addActionListener(new ButtonHandler("Внутренний класс"));

        // Кнопка с анонимным классом
        JButton anonymousClassButton = new JButton("Анонимный класс");
        anonymousClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(NestedClassesDemo.this,
                        "Это обработчик анонимного класса",
                        "Анонимный класс", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Кнопка с лямбда-выражением (Java 8+)
        JButton lambdaButton = new JButton("Лямбда-выражение");
        lambdaButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(NestedClassesDemo.this,
                    "Это обработчик с лямбда-выражением",
                    "Лямбда", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(innerClassButton);
        panel.add(anonymousClassButton);
        panel.add(lambdaButton);

        add(panel);
    }

    // Локальный класс внутри метода
    private void demonstrateLocalClass() {
        class LocalMessage {
            String getMessage() {
                return "Сообщение из локального класса";
            }
        }

        LocalMessage localMessage = new LocalMessage();
        System.out.println(localMessage.getMessage());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NestedClassesDemo demo = new NestedClassesDemo();
                demo.setVisible(true);
                demo.demonstrateLocalClass();
            }
        });
    }
}