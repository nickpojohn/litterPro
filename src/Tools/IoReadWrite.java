package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * IO 读写操作
 * @author liting 作者 E-mail:pojohn@163.com
 * @version 创建时间：2016年11月18日 上午9:37:56
 * 类说明
 */

public class IoReadWrite {

	/**
	 * 读取文件
	 *
	 * @param readPath
	 *            文件路径
	 * @return
	 */
	public String read(String readPath) {
		String readTxt = "";
		try {
			File f = new File(readPath);
			if (f.isFile() && f.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(f), "GBK");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					readTxt += line + "rn";
				}
				read.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readTxt;
	}

	/**
	 * 写入文件
	 *
	 * @param writePath
	 *            文件路径
	 */
	public void write(String writePath, String writeTxt) {
		try {
			File f = new File(writePath);
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(
					new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(writeTxt);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		IoReadWrite readWrite = new IoReadWrite();

		String iptMsg = "";
		Scanner sc = new Scanner(System.in);
		System.err.println("请输入内容：");
		iptMsg = sc.next();
		System.err.println("接收到内容：" + iptMsg);
		System.out.println("----开始写文件----");
		String writePath = "G:/3.txt";
		readWrite.write(writePath, iptMsg);
		System.out.println("----结束写文件----");

		System.out.println("----读取文件开始----");
		String readPath = "G:/3.txt";
		String txt = readWrite.read(readPath);
		System.out.println("----读取文件结束----");

		System.out.println("----开始写文件----");
		String writePathString = "G:/4.txt";
		readWrite.write(writePathString, txt);
		System.out.println("----结束写文件----");
	}
}