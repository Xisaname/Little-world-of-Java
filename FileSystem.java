package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

/**
 * 
 * @author CostFan
 * @fuction some methods of files
 */
public class FileSystem {
	public FileSystem() {};
	/**
	 * 
	 *@fuction create file directory
	 */
	public void mkdir(String...args) {
		for(int i=0;i<args.length;i++)
		{
			File filem=new File(args[i]);
			if(filem.mkdir()) {
				System.out.println("目录创建成功");
			}
			else {
				System.out.println("目录创建失败，可能的原因是已经存在或路径出现错误");
			}
		}
	}
	/**
	 * 
	 *@fuction create new or nasty file
	 */
	public void nfile(String...args) {
		for(int i=0;i<args.length;i++) {
			File filen=new File(args[i]);
			if(!filen.exists()) {
				try {
					filen.createNewFile();
					System.out.println(args[i]+"文件创建成功");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println(args[i]+"文件已经存在");
			}
		}
	}
	/**
	 * 
	 *@fuction delete file or directory
	 */
	public boolean rm(String args) {
		File filer= new File(args);
		if(filer.isDirectory()) {//determine if there are files in the directory
			String[] delchild= filer.list();
			for(int i=0;i<delchild.length;i++) {
				boolean success=rm(delchild[i]);//recursive calls
				if(!success) {
					System.out.println("删除子目录失败");
					return false;
				}
			}
		}
		if(filer.exists()) {
			System.out.println("删除成功");
			return filer.delete();//delete empty directory or file
		}
		else {
			System.out.println("文件不存在");
			return false;
		}
	}
	/**
	 * 
	 *@fuction show the list of directory
	 */
	public void ls(String args) {
		File filel=new File(args);
		if(filel.isDirectory()) {
			String[] files=filel.list();
			File[] cfiles=filel.listFiles();
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			if(files.length==0) {
				System.out.println("是空文件夹");
			}else {
				System.out.println("Directory   "+args);
				System.out.println("           ------------------------------------------------");
				System.out.printf("%-30s","Name");
				System.out.printf("%-30s","Time created");
				System.out.printf("%-30s","Time modified");
				System.out.printf("%-10s","Type");
				System.out.println("");
				for (int i=0;i<files.length;i++) {
					System.out.printf("%-30s",files[i]);
					long lastModified=cfiles[i].lastModified();
					System.out.printf("%-30s",sdf.format(getFileCreateTime(String.valueOf(cfiles[i]))));
					System.out.printf("%-30s",sdf.format(lastModified));
					if(cfiles[i].isDirectory()) {
						System.out.printf("%-10s","d");
						System.out.println("");
					}else {
						System.out.printf("%-10s","t");
						System.out.println("");
					}
				}
			} 
		}else {
			System.out.println("并不是文件夹，请确认后再进行");
		}
	}
	/**
	 * 
	 *@fuction get file's created time
	 */
	private Long getFileCreateTime(String filePath){
	    File file = new File(filePath);
	    try {
	        Path path= Paths.get(filePath);
	        BasicFileAttributeView basicview= Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS );
	        BasicFileAttributes attr = basicview.readAttributes();
	        return attr.creationTime().toMillis();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return file.lastModified();
	    }
	}
}
