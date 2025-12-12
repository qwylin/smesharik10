import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class TextEditorWithMenu extends JFrame {
    private JTextArea textArea;
    private JMenuBar menuBar;

    public TextEditorWithMenu() {
        super("Текстовый редактор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        createUI();
    }

    private void createUI() {
        // Создаем текстовую область с прокруткой
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Создаем меню
        createMenuBar();

        // Создаем панель с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton clearButton = new JButton("Очистить");
        JButton aboutButton = new JButton("О программе");

        buttonPanel.add(clearButton);
        buttonPanel.add(aboutButton);

        // Добавляем компоненты
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Обработчики для кнопок
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TextEditorWithMenu.this,
                        "Текстовый редактор v1.0\nСоздано в рамках практической работы №15",
                        "О программе", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();

        // Меню "Файл"
        JMenu fileMenu = new JMenu("Файл");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem saveItem = new JMenuItem("Сохранить", KeyEvent.VK_S);
        JMenuItem exitItem = new JMenuItem("Выйти", KeyEvent.VK_Q);

        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Меню "Правка"
        JMenu editMenu = new JMenu("Правка");
        editMenu.setMnemonic(KeyEvent.VK_E);

        JMenuItem copyItem = new JMenuItem("Копировать");
        JMenuItem cutItem = new JMenuItem("Вырезать");
        JMenuItem pasteItem = new JMenuItem("Вставить");

        editMenu.add(copyItem);
        editMenu.add(cutItem);
        editMenu.add(pasteItem);

        // Меню "Справка"
        JMenu helpMenu = new JMenu("Справка");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        JMenuItem aboutItem = new JMenuItem("О программе");

        helpMenu.add(aboutItem);

        // Добавляем меню в менюбар
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        // Обработчики для пунктов меню
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TextEditorWithMenu.this,
                        "Функция сохранения будет реализована позже",
                        "Сохранение", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Обработчики для операций с текстом
        copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });

        cutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });

        pasteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TextEditorWithMenu.this,
                        "Текстовый редактор\nПрактическая работа №15\nВложенные классы и обработка событий",
                        "О программе", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextEditorWithMenu().setVisible(true);
            }
        });
    }
}