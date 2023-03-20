import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoListApp extends JFrame {

    private JButton addButton;
    private JButton completeButton;
    private JButton removeButton;
    private JTextField taskField;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private ArrayList<String> completedTasks;
    private int taskCounter;

    public ToDoListApp() {
        super("To-Do List App");

        // Initialize components
        addButton = new JButton("Add Task");
        completeButton = new JButton("Complete Task");
        removeButton = new JButton("Remove Task");
        taskField = new JTextField(20);
        listModel = new DefaultListModel<String>();
        taskList = new JList<String>(listModel);
        completedTasks = new ArrayList<String>();
        taskCounter = 1;

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);
        add(taskField, BorderLayout.NORTH);

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                if (task.trim().length() > 0) {
                    listModel.addElement(taskCounter + ". " + task + " (incomplete)");
                    taskCounter++;
                    taskField.setText("");
                }
            }
        });

        completeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = taskList.getSelectedIndices();
                for (int i = 0; i < selectedIndices.length; i++) {
                    String task = listModel.getElementAt(selectedIndices[i]);
                    completedTasks.add(task);
                    listModel.set(selectedIndices[i], task.replace("incomplete", "complete"));
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = taskList.getSelectedIndices();
                for (int i = 0; i < selectedIndices.length; i++) {
                    listModel.removeElementAt(selectedIndices[i]);
                }
            }
        });

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ToDoListApp();
    }
}
