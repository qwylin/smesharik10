import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountryInfo extends JFrame {
    private JComboBox<String> countryComboBox; // Правильное название переменной
    private JTextArea infoTextArea;

    // Внутренний класс для хранения информации о странах
    private static class Country {
        String name;
        String capital;
        String population;
        String language;

        public Country(String name, String capital, String population, String language) {
            this.name = name;
            this.capital = capital;
            this.population = population;
            this.language = language;
        }

        @Override
        public String toString() {
            return name;
        }

        public String getInfo() {
            return "Страна: " + name + "\n" +
                    "Столица: " + capital + "\n" +
                    "Население: " + population + "\n" +
                    "Официальный язык: " + language + "\n";
        }
    }

    private Country[] countries = {
            new Country("Россия", "Москва", "146 млн", "Русский"),
            new Country("США", "Вашингтон", "331 млн", "Английский"),
            new Country("Германия", "Берлин", "83 млн", "Немецкий"),
            new Country("Франция", "Париж", "67 млн", "Французский"),
            new Country("Япония", "Токио", "126 млн", "Японский"),
            new Country("Китай", "Пекин", "1.4 млрд", "Китайский")
    };

    public CountryInfo() {
        super("Информация о странах");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        createUI();
    }

    private void createUI() {
        setLayout(new BorderLayout(10, 10));

        // Панель выбора страны
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Выберите страну:");
        countryComboBox = new JComboBox<>(); // Правильное название

        for (Country country : countries) {
            countryComboBox.addItem(country.toString());
        }

        topPanel.add(label);
        topPanel.add(countryComboBox); // ИСПРАВЛЕНО: было countComboBox

        // Текстовая область для информации
        infoTextArea = new JTextArea(10, 30);
        infoTextArea.setEditable(false);
        infoTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        infoTextArea.setLineWrap(true);
        infoTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Информация о стране"));

        // Кнопка показа информации
        JButton showInfoButton = new JButton("Показать информацию");

        // Добавляем компоненты
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(showInfoButton, BorderLayout.SOUTH);

        // Обработчики событий
        countryComboBox.addActionListener(new ActionListener() { // Правильное название
            @Override
            public void actionPerformed(ActionEvent e) {
                showCountryInfo();
            }
        });

        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCountryInfo();
            }
        });

        // Показываем информацию о первой стране при запуске
        showCountryInfo();
    }

    private void showCountryInfo() {
        int selectedIndex = countryComboBox.getSelectedIndex(); // Правильное название
        if (selectedIndex >= 0 && selectedIndex < countries.length) {
            infoTextArea.setText(countries[selectedIndex].getInfo());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CountryInfo().setVisible(true);
            }
        });
    }
}