package com.badou.perspective.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory{

	 @Override
	    public void createInitialLayout(IPageLayout layout) {
	        // TODO Auto-generated method stub
	        String editorArea = layout.getEditorArea();
	        layout.setEditorAreaVisible(false);
	        layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.LEFT, 0.25f,
	                editorArea);
	        IFolderLayout bottom = layout.createFolder("bottom",
	                IPageLayout.BOTTOM, 0.66f, editorArea);
	        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
	    }
}