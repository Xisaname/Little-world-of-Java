package file;


import java.util.Scanner;

public class FileTest {

	public static void main(String[] args) {
		FileSystem files=new FileSystem();
		Scanner sc=new Scanner(System.in);
		String aline=sc.nextLine();
		sc.close();
		String[]paths=aline.split(" ");
		String[]cpaths=new String[paths.length-1];
		String cmd=paths[0];
		for(int i=0;i<paths.length-1;i++) {
			cpaths[i]=paths[i+1];
		}
		switch (cmd) {
		case "mkdir":
			files.mkdir(cpaths);
			break;
		case "nfile":
			files.nfile(cpaths);
			break;
		case "rm":
			files.rm(cpaths[0]);
			break;
		case "ls":
			files.ls(cpaths[0]);
			break;
		default:System.out.println(cmd+"指令有误，不符合语法");
			break;
		}
	}

}
