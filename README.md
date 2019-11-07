# This passage is about the file system
*Let's begin.* 
## Function
 method|function|anything else
--|:--:|--: 
public void mkdir(String...args)|create one or more directory(s)|NULL
public void nfile(String...args)|create one or more file(s)|add one condition:if there has already been a same file
public boolean rm(String args)|delete one file or directory's all data|need recursion
public void ls(String args)|ls the data of attracted directory|many...see the detail
---
### the details of these methods
+ public void mkdir(String...args)
    `String...args`
    this code mains `String [] args` but the first one has more functions than the last one.
+ public void nfile(String...args)
    ``` 
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
    ```
    this code add a judging condition which is if there has been a sme file and add try-catch
+ public boolean rm(String args)
    1. Firstly, add a condition that if there is a attracted directory, if so, add code `String[] delchild= filer.list();` to get    the child directory or file of the attracted directory, if so, add a circulation and in the circulation use recursion to delete child dierctory.
    2. If there is a file or empty directory, delete it directly. 
+ public void ls(String args)
    1. In this method, I should give four kinds of information, Name, Time created, Time modified and Type. The Type is the easist one so let's forget it. I use `String[] files=filel.list()`to get the Name, use `File[] cfiles=filel.listFiles()`to get all of the information to get time I need.
    2. To get a child dierctory's or file's modified time, I use a method called`long lastModified=cfiles[i].lastModified();`, very convenient, isn't it?
    3. And the most difficult one is getting the Time created. I find a **freaking awesome** method and use it, let's feel it together...
   ```
   private Long getFileCreateTime(String filePath){
	    File file = new File(filePath);
	    try {
	        Path path= Paths.get(filePath);  //get the file'spath
	        BasicFileAttributeView basicview= Files.getFileAttributeView(path, BasicFileAttributeView.class, 
            LinkOption.NOFOLLOW_LINKS );  //foegive me...I can't understand it...
	        BasicFileAttributes attr = basicview.readAttributes();
	        return attr.creationTime().toMillis();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return file.lastModified();
	    }
	}
    ``` 
---
That's all of my description of the file system.
~~Add a final result picture~~
![blockchain](https://raw.githubusercontent.com/Xisaname/Little-world-of-Java/master/File.PNG)




    