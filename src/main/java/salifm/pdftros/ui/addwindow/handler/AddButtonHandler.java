/*
  Copyright (c) 2021 Salif Mehmed
  This Source Code Form is subject to the terms of the Mozilla Public
  License, v. 2.0. If a copy of the MPL was not distributed with this
  file, You can obtain one at http://mozilla.org/MPL/2.0/.
*/

package salifm.pdftros.ui.addwindow.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;

import salifm.pdftros.App;

public class AddButtonHandler implements ActionListener {

	private final File file;
	private final Set<JCheckBox> checkboxes;
	private final Consumer<Void> consumer;

	public AddButtonHandler(File file, Set<JCheckBox> checkboxes, Consumer<Void> consumer) {
		this.file = file;
		this.checkboxes = checkboxes;
		this.consumer = consumer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String[] folders = checkboxes.stream().filter(AbstractButton::isSelected)
			.map(AbstractButton::getText).toArray(String[]::new);
		if (folders.length == 0) {
			this.consumer.accept(null);
			return;
		}
		App.addPdfs(this.file, folders);
		this.consumer.accept(null);
	}
}