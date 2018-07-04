package com.badou.newbadouwizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
/**
 * 向导对话框
 * @author Administrator
 *
 */
public class  BadouWizardPage extends WizardPage { 
    protected BadouWizardPage(String pageName) { 
	    super(pageName); 
	    this.setTitle(pageName); 
    } 
    
    public void createControl(final Composite parent) { 
	    final Composite composite = new Composite(parent, SWT.NONE); 
	    setControl(composite); 
	    composite.setLayout(new GridLayout(2, false)); 
	    /*Label description = new Label(composite, SWT.WRAP); 
	    description.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1)); 
	    description.setText("This is a WizardPage for Help Demo."); */
	    final Label projectNameLabel = new Label(composite, SWT.NONE);
	    projectNameLabel.setText("Project Name:");
	    
	    final Text projectNameText = new Text(composite, SWT.BORDER);
	    projectNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	   
	    final Label grouoIdLabel = new Label(composite, SWT.NONE);
	    grouoIdLabel.setText("Group Id:");
	    
	    final Text grouoIdText = new Text(composite, SWT.BORDER);
	    grouoIdText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	    
	    final Label ArtifactLabel = new Label(composite, SWT.NONE);
	    ArtifactLabel.setText("Artifact Id:");
	    
	    final Text ArtifactText = new Text(composite, SWT.BORDER);
	    ArtifactText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	    
	    final Label versionLabel = new Label(composite, SWT.NONE);
	    versionLabel.setText("Version:");
	    
	    final Text versionText = new Text(composite, SWT.BORDER);
	    versionText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	   
	    final Label descriptionlable = new Label(composite, SWT.NONE);
	    descriptionlable.setText("Description:");
	    
	    final Text descriptionText = new Text(composite,SWT.BORDER|SWT.H_SCROLL); 
	    GridData grid = new GridData(SWT.FILL, SWT.CENTER, true, false,1,1);
	    grid.heightHint = 50;
	    descriptionText.setLayoutData(grid);
    } 
}
