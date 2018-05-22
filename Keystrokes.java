frame.add(panel);
		frame.pack();
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"slideLeft");
		panel.getActionMap().put("slideLeft",new AbstractAction(){
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveLeft(tank1);
			}
		});
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"slideRight");
		panel.getActionMap().put("slideRight",new AbstractAction(){
			
