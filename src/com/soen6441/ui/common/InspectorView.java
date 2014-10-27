package com.soen6441.ui.common;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.TextView;
import com.soen6441.ui.parallel.View;

// not finish 
/**
 * This class defines the inspector view. On the right side of our game the menu that will show editing options or tower information is the inspector view.
 * An element needs to be inspectable to be displayed on the view.
 * 
 * @author Zhe Zhao
 * @author JeanRaymondDaher
 * @author Chenglong Zhang
 * 
 * @see IInspectable
 * @version $Revision: 1.0 $
 */
public class InspectorView extends View {
	
	/*
	 * Mark - Command - Properties
	 */

	private List<Command> commands;
	
	/*
	 * Mark - Views - Properties
	 */
	
	private Label titleLabel;
	private Label subtitleLabel;
//	private ImageView imageView;
	private TextView descriptionTextArea;
	private List<Button> commandButtons;
	
	/*
	 * Mark - Views - Life Cycle 
	 */
	
	@Override
	protected void init() {
		super.init();
		this.setSize(180, 500);
		this.commandButtons = new ArrayList<Button>();
	}
	
	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		Label titleLabel = new Label();
		titleLabel.setFontSize(16);
		titleLabel.setSize(160, 20);
		titleLabel.setLocation(10, 10);
		this.add(titleLabel);
		this.titleLabel = titleLabel;
		
		Label subtitleLabel = new Label();
		subtitleLabel.setFontSize(12);
		subtitleLabel.setSize(160, 20);
		subtitleLabel.setLocation(10, titleLabel.getY() + titleLabel.getHeight() + 0);
		this.add(subtitleLabel);
		this.subtitleLabel = subtitleLabel;
		
		TextView descriptionTextView = new TextView();
		descriptionTextView.setFontSize(12);
		descriptionTextView.setSize(160, 200);
		descriptionTextView.setLocation(10, 160);
		descriptionTextView.setBackground(new Color(0xEEEEEE));
        
		this.add(descriptionTextView);
		this.descriptionTextArea = descriptionTextView;
	}

	public void update() {
		titleLabel.setText(on.title());
		subtitleLabel.setText(on.subtitle());
		descriptionTextArea.setText(on.description());
		
		//remove old buttons
		for (Button button:commandButtons){
			this.remove(button);
		}
		
		//add new buttons
		commandButtons.clear();
		commands = on.commands();
		if (commands != null) {
			for (int i = 0; i < commands.size(); i++){
				Command command = commands.get(i);
				
				Button button = new Button();
				button.setFontSize(12);
				button.setTitle(command.getTitle());
				button.setSubtitle(command.getSubtitle());
				button.setSize(160, 40);
				button.setLocation(10, this.getHeight() - 50 * (commands.size() - i));
				this.add(button);
				this.commandButtons.add(button);
				
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int index = commandButtons.indexOf(e.getSource());
						on.execute(commands.get(index));
					}
				});
			}
		}
	}
	
	/*
	 * Mark - On - Properties
	 */
	
	private IInspectable on;
	
	/*
	 * Mark - On - Getters & Setters
	 */
	
	/**
	 * Method getOn.
	 * @return IInspectable 
	 */
	public IInspectable getOn() {
		return on;
	}
	/**
	 * Method setOn.
	 * @param on IInspectable
	 */
	public void setOn(IInspectable on) {
		this.on = on;
	}
	
}
