import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicCalculator extends JFrame {
    private JTextField display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    // Внутренний класс для обработки нажатий цифр
    private class NumberButtonListener implements ActionListener {
        private String number;

        public NumberButtonListener(String number) {
            this.number = number;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (startNewNumber) {
                display.setText(number);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + number);
            }
        }
    }

    // Внутренний класс для обработки операций
    private class OperatorButtonListener implements ActionListener {
        private String op;

        public OperatorButtonListener(String op) {
            this.op = op;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!operator.isEmpty()) {
                calculate();
            }
            firstNumber = Double.parseDouble(display.getText());
            operator = op;
            startNewNumber = true;
        }
    }

    public GraphicCalculator() {
        super("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        createUI();
    }

    private void createUI() {
        setLayout(new BorderLayout());

        // Поле вывода
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setText("0");

        add(display, BorderLayout.NORTH);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Массив кнопок
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "CE", "√", "x²"
        };

        // Создаем кнопки и добавляем слушателей
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 16));

            if (Character.isDigit(text.charAt(0))) {
                button.addActionListener(new NumberButtonListener(text));
            } else if (text.equals(".")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!display.getText().contains(".")) {
                            display.setText(display.getText() + ".");
                        }
                    }
                });
            } else if (text.equals("=")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        calculate();
                    }
                });
            } else if (text.equals("C") || text.equals("CE")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        display.setText("0");
                        firstNumber = 0;
                        operator = "";
                        startNewNumber = true;
                    }
                });
            } else if (text.equals("√")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double num = Double.parseDouble(display.getText());
                        if (num >= 0) {
                            display.setText(String.valueOf(Math.sqrt(num)));
                        } else {
                            JOptionPane.showMessageDialog(GraphicCalculator.this,
                                    "Ошибка: нельзя извлечь корень из отрицательного числа",
                                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
                        startNewNumber = true;
                    }
                });
            } else if (text.equals("x²")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double num = Double.parseDouble(display.getText());
                        display.setText(String.valueOf(num * num));
                        startNewNumber = true;
                    }
                });
            } else {
                button.addActionListener(new OperatorButtonListener(text));
            }

            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void calculate() {
        if (operator.isEmpty()) return;

        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        try {
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) {
                        throw new ArithmeticException("Деление на ноль");
                    }
                    result = firstNumber / secondNumber;
                    break;
            }

            // Форматируем результат
            if (result == (long) result) {
                display.setText(String.format("%d", (long) result));
            } else {
                display.setText(String.format("%.6f", result).replaceAll("0*$", "").replaceAll("\\.$", ""));
            }

        } catch (ArithmeticException e) {
            display.setText("Ошибка");
            JOptionPane.showMessageDialog(this, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        operator = "";
        startNewNumber = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GraphicCalculator().setVisible(true);
            }
        });
    }
}