/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tbosch.tools.jsudoku.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.apache.commons.lang3.StringUtils;

import de.tbosch.tools.jsudoku.model.SudokuMatrix;
import de.tbosch.tools.jsudoku.service.SudokuService;

/**
 * 
 * @author Thomas Bosch
 */
public class JSudokuView extends javax.swing.JFrame {

	private SudokuMatrix matrix;

	/**
	 * Creates new form JSudokuView
	 */
	public JSudokuView() {
		initComponents();
		initEmptyMatrix();
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		matrixPanel = new javax.swing.JPanel();
		buttonPanel = new javax.swing.JPanel();
		verifyButton = new javax.swing.JButton();
		fixButton = new javax.swing.JButton();
		solveButton = new javax.swing.JButton();
		topMenuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		newMenuItem = new javax.swing.JMenuItem();
		aboutItem = new javax.swing.JMenuItem();
		exitItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("de/tbosch/tools/jsudoku/gui/JSudokuView"); // NOI18N
		setTitle(bundle.getString("TITLE")); // NOI18N
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

		matrixPanel.setToolTipText(bundle.getString("")); // NOI18N
		matrixPanel.setLayout(new java.awt.GridLayout(1, 0));
		getContentPane().add(matrixPanel);

		verifyButton.setText(bundle.getString("CHECK")); // NOI18N
		verifyButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				verifyButtonActionPerformed(evt);
			}
		});
		buttonPanel.add(verifyButton);

		fixButton.setText(bundle.getString("FIX")); // NOI18N
		fixButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fixButtonActionPerformed(evt);
			}
		});
		buttonPanel.add(fixButton);

		solveButton.setText(bundle.getString("SOLVE")); // NOI18N
		solveButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				solveButtonActionPerformed(evt);
			}
		});
		buttonPanel.add(solveButton);

		getContentPane().add(buttonPanel);

		fileMenu.setText(bundle.getString("FILE")); // NOI18N

		newMenuItem.setText(bundle.getString("NEW")); // NOI18N
		newMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(newMenuItem);

		aboutItem.setText(bundle.getString("ABOUT")); // NOI18N
		aboutItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aboutItemActionPerformed(evt);
			}
		});
		fileMenu.add(aboutItem);

		exitItem.setMnemonic('q');
		exitItem.setText(bundle.getString("QUIT")); // NOI18N
		exitItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitItem);

		topMenuBar.add(fileMenu);

		setJMenuBar(topMenuBar);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void aboutItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_aboutItemActionPerformed
		new About(this).setVisible(true);
	}// GEN-LAST:event_aboutItemActionPerformed

	private void exitItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitItemActionPerformed
		System.exit(0);
	}// GEN-LAST:event_exitItemActionPerformed

	private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newMenuItemActionPerformed
		initEmptyMatrix();
	}// GEN-LAST:event_newMenuItemActionPerformed

	private void verifyButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_verifyButtonActionPerformed
		if (matrix.istFertig() && matrix.istGueltig()) {
			JOptionPane.showMessageDialog(
					this,
					java.util.ResourceBundle.getBundle("de/tbosch/tools/jsudoku/gui/JSudokuView").getString(
							"SUDOKU_VALID"));
		}
		else {
			JOptionPane.showMessageDialog(
					this,
					java.util.ResourceBundle.getBundle("de/tbosch/tools/jsudoku/gui/JSudokuView").getString(
							"SUDOKU_INVALID"));
		}
	}// GEN-LAST:event_verifyButtonActionPerformed

	private void fixButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_fixButtonActionPerformed
		int[][] zahlen = new int[9][9];
		for (Component component : matrixPanel.getComponents()) {
			for (Component innerComponent : ((JPanel)component).getComponents()) {
				JTextFieldLimit textField = (JTextFieldLimit)innerComponent;
				if (StringUtils.isNotEmpty(textField.getText())) {
					zahlen[textField.getI()][textField.getJ()] = Integer.valueOf(textField.getText());
				}
			}
		}
		matrix = new SudokuMatrix(zahlen);
		drawMatrix();
	}// GEN-LAST:event_fixButtonActionPerformed

	private void solveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_solveButtonActionPerformed
		try {
			matrix = new SudokuService().loeseSudoku(matrix);
			drawMatrix();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(
					this,
					java.util.ResourceBundle.getBundle("de/tbosch/tools/jsudoku/gui/JSudokuView").getString(
							"NO_SOLUTION"),
					java.util.ResourceBundle.getBundle("de/tbosch/tools/jsudoku/gui/JSudokuView").getString("ERROR"),
					JOptionPane.ERROR_MESSAGE);
		}
	}// GEN-LAST:event_solveButtonActionPerformed
		// Variables declaration - do not modify//GEN-BEGIN:variables

	private javax.swing.JMenuItem aboutItem;

	private javax.swing.JPanel buttonPanel;

	private javax.swing.JMenuItem exitItem;

	private javax.swing.JMenu fileMenu;

	private javax.swing.JButton fixButton;

	private javax.swing.JPanel matrixPanel;

	private javax.swing.JMenuItem newMenuItem;

	private javax.swing.JButton solveButton;

	private javax.swing.JMenuBar topMenuBar;

	private javax.swing.JButton verifyButton;

	// End of variables declaration//GEN-END:variables

	private void initEmptyMatrix() {
		matrix = new SudokuMatrix(9);
		drawMatrix();
	}

	private void drawMatrix() {
		matrixPanel.removeAll();
		matrixPanel.setLayout(new GridLayout(3, 3));
		List<JPanel> subfields = new ArrayList<JPanel>();
		for (int i = 0; i < matrix.getDimension(); i++) {
			JPanel subfield = new JPanel();
			subfield.setBorder(new LineBorder(Color.BLACK, 3));
			subfield.setLayout(new GridLayout(3, 3));
			matrixPanel.add(subfield);
			subfields.add(subfield);
		}
		for (int i = 0; i < matrix.getDimension(); i++) {
			for (int j = 0; j < matrix.getDimension(); j++) {
				final JTextField textField = new JTextFieldLimit(1, i, j);
				textField.setSize(50, 50);
				textField.setPreferredSize(new Dimension(50, 50));
				textField.setFont(new Font("Arial", Font.BOLD, 24));
				textField.setHorizontalAlignment(JTextField.CENTER);
				int zahl = matrix.getZahlen()[i][j];
				if (zahl != SudokuMatrix.LEER) {
					textField.setEditable(false);
					textField.setText(Integer.toString(zahl));
				}
				subfields.get(matrix.getQuadratNummer(i, j)).add(textField);
				this.repaint();
				this.pack();
			}
		}
	}
}
