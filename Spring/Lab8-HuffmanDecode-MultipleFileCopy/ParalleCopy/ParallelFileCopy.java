/* GenericLinkedList.java  
 *Version: 
 *     1 
 * 
 * Revisions: 
 *     
 *     Tejas Raval MS-CS 2018 RIT tr7550@rit.edu
 *     Lipisha Chowdhry MS-CS 2018 RIT lc2919@rit.edu
 */
/** 
 *This program implements file copy in 2 ways.
 *1)Copying using single thread
 *2)Copying using multiple threads
 * 
 *@author      Tejas Raval 
 *@author      Lipisha Chowdhry
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;



/**
 * The actual file copy function will be used by each thread(single/multiple)
 * @author Tejas Raval
 * @author      Lipisha Chowdhry
 */
public  class ParallelFileCopy {
	void  CopyFile(File from, File to) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(from);
			os = new FileOutputStream(to);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}

	}
/**
 * If the destination path contains the folder with same name, then this function
 * resolves the conflict by renaming the new folder
 * @param args
 * @return confilict resolving suffix for the folder
 */
	public String  CheckConflict(String args[]) {
		//System.out.println("Called by"+Thread.currentThread().getName());
		boolean flag=true;
		String folderName="";
		while(flag) {
			File check = new File( args[2]);
			if (check.exists()) { //check if folder with same name exists  
				File checkCopy = new File( args[2]+"_Copy");
				//System.out.println(checkCopy.getAbsolutePath());
				if(checkCopy.exists()) {//check if even _Copy exists
					for(int i=1;i<10000;i++) {
						File checkCopyCout = new File( args[2]+"_Copy("+i+")");
						if(!checkCopyCout.exists()) {//Now check till which number folders are present
							folderName+=folderName+"_Copy("+i+")";
							File newFile=new File(args[2]+folderName+"//");
							newFile.mkdir();
							flag=false;
							break;
						}
					}
				}
				else {//if the first confilict, then make a new folder with _copy
					folderName+=folderName+"_Copy";
					File newFile=new File(args[2]+folderName+"//");
					newFile.mkdir();
					//flag=false;
					break;
				}

			}
			else {//if no confilict, then make a new folder
				File newFile=new File(args[2]+"//");
				newFile.mkdir();
				break;
			}
		}
		//System.out.println("Giving "+folderName+" "+Thread.currentThread().getName());
		return folderName;
	}
	/**
	 * Copy using single thread in a sequential manner
	 * @param folder  From directory
	 * @param fileCouter Number of the files in the directory
	 * @param args   to and from paths in String format
	 */
	void SingleCopy(File folder, int fileCouter,String args[]) {
		String folderName=CheckConflict(args);
		System.out.println("Single folder name made will be "+folderName);
		long startSingle = System.currentTimeMillis();//Start the execution time counter
		for (File file : folder.listFiles()) {
			if(file.isFile()){
				File fromSingle = new File(file.getAbsolutePath());
				File toSingle= new File(args[2]+folderName+"//"+file.getName());
				try {
					CopyFile(fromSingle,toSingle);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		long endSingle = System.currentTimeMillis();//End the execution time counter
		NumberFormat   formatter = new DecimalFormat("#0.00000");
		System.out.println("Execution time for Single Thread copy is " + formatter.format((endSingle - startSingle) / 1000d) + " seconds");

	}
	/**
	 * 
	 * @param folder  From directory
	 * @param fileCouter Number of the files in the directory
	 * @param args   to and from paths in String format
	 * @throws InterruptedException
	 */
	void ParalleCopy(File folder, int fileCouter,String args[]) throws InterruptedException {
		String folderName=CheckConflict(args);
		System.out.println("Parallel folder name made will be "+folderName);
		long start = System.currentTimeMillis();//Start the execution time counter
		Thread t1[] = new Thread[fileCouter];
		int i = 0;
		for (File file : folder.listFiles()) {
			if(file.isFile()){
				t1[i++] = new Thread(new Runnable() {

					public void run() {
						//file.getAbsolutePath()
						File from = new File(file.getAbsolutePath());
						File to = new File(args[2]+folderName+"\\"+file.getName());

						try {
							CopyFile(from,to);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		}
        //Start the threads.
		for (int k = 0; k < fileCouter; k++)
			t1[k].start();
        //wait till every threads joins
		for (int k = 0; k < fileCouter; k++)
			t1[k].join();
		long end = System.currentTimeMillis();//End the execution time counter
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.println("Execution time for Thread copy is " + formatter.format((end - start) / 1000d) + " seconds");

	}

	public static void main(String args[]) throws InterruptedException {
		String from=args[1];
		String to=args[2];
		ParallelFileCopy PFC = new ParallelFileCopy();
		File folder = new File(from);
		int tempfileCouter=0;
		for (File file : folder.listFiles()) {
			if(file.isFile()){
				tempfileCouter++;
			}
		}
		final int fileCounter=tempfileCouter;
        //Below code calls the Single and Multiple file copy simultaneously 
		Runnable a = new Runnable() {
			public void run() {
				try {

					PFC.ParalleCopy(folder,fileCounter,args);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Runnable b = new Runnable() {
			public void run() {
				PFC.SingleCopy(folder,fileCounter,args);
			}
		};
		System.out.println("Parallel Copying function called");
		new Thread(a).start();

		Thread.sleep(1);

		System.out.println("Single Copying function called");
		new Thread(b).start();
	}
}
