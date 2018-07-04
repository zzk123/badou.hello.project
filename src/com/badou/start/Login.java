package com.badou.start;


import javax.swing.UIManager;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.progress.UIJob;

public class Login implements IStartup {

	@Override
	public void earlyStartup() {
        Job job = new UIJob("") {
        	
             public IStatus runInUIThread(IProgressMonitor monitor) {
            	 try {  
                     // select Look and Feel  
                     UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");  
                     // start application  
                     new LoginDialog();  
                 }  
                 catch (Exception ex) {  
                     ex.printStackTrace();  
                 }  
                 return Status.OK_STATUS;
             }

        };
        job.schedule(0); 
	}
}
