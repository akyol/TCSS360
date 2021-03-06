package View;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.Component;
import Model.Project;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 * View that handles the logic for a project GUI.
 * 
 * @author Abraham Lee, Jonathan Kim
 *
 */

public class ProjectView extends JPanel {
	
	/** Auto-generated UID */
	private static final long serialVersionUID = 1L;
	/** Textfield for the project **/
	private JTextField textField;
	/** List of componentview for the project view **/
	private List<ComponentView> compViewList;
	/** list of components for the project **/
	private List<java.awt.Component> myComp = new ArrayList<>();
	/** an object of mainGUI for the project **/
	private MainGUI mainGUI;
	
	/**
	 * Create the panel as well as handling the look of the project view.
	 */
	public ProjectView(Project project) {
		mainGUI = new MainGUI();
		compViewList = new ArrayList<>();
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Project");
		setBorder(title);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel projectPanel = new JPanel();
		projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		
		textField = new JTextField("", 20);
		textField.setText(project.getName());
		add(textField);
		
		for (Component comp : project.getComponents()) {
			ComponentView temp = new ComponentView(comp);
			compViewList.add(temp);
			projectPanel.add(temp);
		}
		
		JButton btnAddComponent = new JButton("Add Component");
		btnAddComponent.addActionListener(theEvent -> {
			ComponentView temp = new ComponentView(new Component());
			compViewList.add(temp);
			projectPanel.add(temp);
			revalidate();
        });
		add(projectPanel);
		
		btnAddComponent.setBounds(218, 185, 117, 29);
		buttonPanel.add(btnAddComponent);
		
		JButton btnDeleteComponent = new JButton("Delete Component");
		btnDeleteComponent.setBounds(327, 185, 117, 29);
		btnDeleteComponent.addActionListener(theEvent -> {
			if (compViewList.size() > 0) {
				compViewList.remove(compViewList.size() - 1);
				java.awt.Component[] components = projectPanel.getComponents();
				for (java.awt.Component component : components) {
					myComp.add(component);
				}
				projectPanel.remove(myComp.size() - 1);
				myComp.clear();
				revalidate();
				
			}
        });
		buttonPanel.add(btnDeleteComponent);
		add(buttonPanel);
		
		JButton btnDeleteProject = new JButton("Delete Project");
		add(btnDeleteProject);
		btnDeleteProject.addActionListener(theEvent -> {
			textField.setText("");
			compViewList.clear();
			projectPanel.removeAll();
			mainGUI.deleteProject();
			revalidate();
        });
		add(btnDeleteProject);
		
		setVisible(true);
		validate();

	}
	
	/**
	 * Get the name of the project.
	 */
	public String getName() {
		return textField.getText();
	}
	
	/**
	 * Method to get the Component View List
	 * @return a list of component views.
	 */
	public List<ComponentView> getCompViewList() {
		return compViewList;
	}

}
