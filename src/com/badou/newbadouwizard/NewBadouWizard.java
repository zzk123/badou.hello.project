package com.badou.newbadouwizard;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * 八斗项目建立向导
 * @author Administrator
 *
 */
public class NewBadouWizard extends Wizard implements INewWizard{

	private BadouWizardPage badouwizardpage;
	
	public NewBadouWizard() {
		setWindowTitle("新建badou Project");
	}
	
	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {
		addPages();
	}

	@Override
	public boolean performFinish() {
		try {
			createProject("badou_project");
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void addPages(){
		badouwizardpage = new BadouWizardPage("新建一个Badou项目");
		addPage(badouwizardpage);
	}
	
	public void projectExport() {
		// 获取工作区
		 String ss = Platform.getInstallLocation().getURL().toString();  
	     int index = ss.indexOf("/");  
	     System.out.println(ss.substring(index + 1, ss.length() - 1));  
	     IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	    // ///////////////////////////////////创建新项目///////////////////////////
	    final IProject project = ((IFile)new File(ss.substring(index + 1, ss.length() - 1)+"/test/badou.hello.project (1)/badou.hello.project")).getProject();

	  //  final IProject project = root.getProject("badou-init");
	    
	    // 设置工程的位置
	    
	    // 为项目指定存放路径,默认放在当前工作区

	    IPath projectPath = new Path(ResourcesPlugin.getPlugin().getStateLocation().makeAbsolute().toFile().getAbsolutePath());
	   
	    IWorkspace workspace = root.getWorkspace();

	    final IProjectDescription description = workspace.newProjectDescription(project.getName());

	    description.setLocation(projectPath);


	}
	
	//新建项目
	public void createProject(String projectName) throws CoreException{  
		// 获取当前工作区
	    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	    // 创建新项目
	    final IProject project = root.getProject(projectName);

	    // 设置工程的位置
	    // 为项目指定存放路径,默认放在当前工作区
	    IPath projectPath = new Path(ResourcesPlugin.getPlugin().getStateLocation().makeAbsolute().toFile().getAbsolutePath());

	    IWorkspace workspace = root.getWorkspace();

	    final IProjectDescription description = workspace.newProjectDescription(projectName);

	    description.setLocation(projectPath);


	    // 设置工程标记,即为java工程
	    String[] javaNature = description.getNatureIds();
	    String[] newJavaNature = new String[javaNature.length + 1];
	    System.arraycopy(javaNature, 0, newJavaNature, 0, javaNature.length);
	    newJavaNature[javaNature.length] = "org.eclipse.jdt.core.javanature"; // 这个标记证明本工程是Java工程
	    description.setNatureIds(newJavaNature);



	    // /////////////////////////////

	    try {

	        NullProgressMonitor monitor = new NullProgressMonitor();

	        project.create(description, monitor);

	        project.open(IResource.BACKGROUND_REFRESH, new SubProgressMonitor(monitor, 1000));

	    } catch (CoreException e) {

	        e.printStackTrace();

	    }



	    // 下面代码只在新建向导的情况下才可用

	    /*

	     * //创建新项目,WorkspaceModifyOperation位于org.eclipse.ui.ide中

	     * WorkspaceModifyOperation workspaceModifyOperation = new

	     * WorkspaceModifyOperation() {

	     *

	     * @Override protected void execute(IProgressMonitor monitor) throws

	     * CoreException, InvocationTargetException, InterruptedException { try

	     * { monitor.beginTask("", ); project.create(description, monitor);

	     *

	     * if(monitor.isCanceled()){ throw new OperationCanceledException(); }

	     *

	     * project.open(IResource.BACKGROUND_REFRESH, new

	     * SubProgressMonitor(monitor, )); } catch (Exception e) {

	     * e.printStackTrace(); } finally{ monitor.done(); } } };

	     * //接下来调用IWizard的getContainer().run()方法.

	     */



	    // 转化成java工程

	    IJavaProject javaProject = JavaCore.create(project);

	    // //////////////////////////////////添加JRE库////////////////////////////

	    try {

	        // 获取默认的JRE库

	        IClasspathEntry[] jreLibrary = PreferenceConstants.getDefaultJRELibrary();

	        // 获取原来的build path

	        IClasspathEntry[] oldClasspathEntries = javaProject.getRawClasspath();

	        List list = new ArrayList();

	        list.addAll(Arrays.asList(jreLibrary));

	        list.addAll(Arrays.asList(oldClasspathEntries));

	        javaProject.setRawClasspath((IClasspathEntry[]) list.toArray(new IClasspathEntry[list.size()]), null);

	    } catch (JavaModelException e) {

	        e.printStackTrace();

	    }



	    // //////////////////////////////////创建输出路径/////////////////////////////

	    IFolder binFolder = javaProject.getProject().getFolder("bin");

	    try {

	        binFolder.create(true, true, null);

	        javaProject.setOutputLocation(binFolder.getFullPath(), null);

	    } catch (CoreException e) {

	        e.printStackTrace();

	    }



	    // /////////////////////////设置Java生成器///////////////////////

	    try {

	        IProjectDescription description2 = javaProject.getProject().getDescription();

	        ICommand command = description2.newCommand();

	        command.setBuilderName("org.eclipse.jdt.core.javabuilder");

	        description2.setBuildSpec(new ICommand[] { command });

	        description2.setNatureIds(new String[] { "org.eclipse.jdt.core.javanature" });

	        javaProject.getProject().setDescription(description2, null);

	    } catch (CoreException e) {

	        e.printStackTrace();

	    }



	    // /////////////////////////////创建源代码文件夹//////////////////////////

	    // ///////////源文件夹和文件夹相似,只是使用PackageFragmentRoot进行了封装////////

	    IFolder srcFolder = javaProject.getProject().getFolder("src");

	    try {

	        srcFolder.create(true, true, null);

	        // this.createFolder(srcFolder);

	        // 创建SourceLibrary

	        IClasspathEntry srcClasspathEntry = JavaCore.newSourceEntry(srcFolder.getFullPath());



	        // 得到旧的build path

	        IClasspathEntry[] oldClasspathEntries = javaProject.readRawClasspath();



	        // 添加新的

	        List list = new ArrayList();

	        list.addAll(Arrays.asList(oldClasspathEntries));

	        list.add(srcClasspathEntry);



	        // 原来存在一个与工程名相同的源文件夹,必须先删除

	        IClasspathEntry temp = JavaCore.newSourceEntry(new Path("/xyz"));

	        if (list.contains(temp)) {

	            list.remove(temp);

	        }



	        System.out.println(list.size());



	        javaProject.setRawClasspath((IClasspathEntry[]) list.toArray(new IClasspathEntry[list.size()]), null);

	    } catch (CoreException e) {

	        e.printStackTrace();

	    }



	    // ///////////////////////////////创建包//////////////////////////

	    // IPackageFragmentRoot packageFragmentRoot = javaProject.getPackageFragmentRoot(javaProject.getResource());

	    //此处得到的src目录只读



	    try {

	        // 先找指定的源文件夹所在的IPackageFragmentRoot

	        IPackageFragmentRoot packageFragmentRoot = javaProject.findPackageFragmentRoot(new Path("/xyz/src"));

	        // 根据IPackageFragmentRoot创建IPackageFragment,IPackageFragment就是包了

	        IPackageFragment packageFragment = packageFragmentRoot.createPackageFragment("com.aptech.plugin", true, null);



	    // //////////////////////////////////创建Java文件////////////////////////

	        String javaCode = "package com.aptech.plugin;public class HelloWorld{public static void main(String[] args){System.out.println(\"中华人民共和国\");}}";

	        packageFragment.createCompilationUnit("HelloWorld.java", javaCode, true, new NullProgressMonitor());



	    } catch (JavaModelException e) {

	        e.printStackTrace();

	    } catch (Exception e) {

	        e.printStackTrace();

	    } 
	 }  
}
