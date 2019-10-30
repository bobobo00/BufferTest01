package cn.io.study3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 理解操作步骤
 * 1，创建源   加入缓冲流
 * 2，选择流
 * 3，操作
 * 4，释放资源
 * 
 * 使用缓冲容器按多个字节的读取
 * @author dell
 *
 */

public class BufferTest01 {
	public static void buffer(String srcpath,String destpath) {
		File src=new File(srcpath);
		File dest=new File(destpath);
		InputStream is=null;
		OutputStream os=null;
		try {
			is=new BufferedInputStream(new FileInputStream(src));//提升性能
			os=new BufferedOutputStream(new FileOutputStream(dest));
			byte[] flush=new byte[1024];
			int len=-1;
			while((len=is.read(flush))!=-1) {
				os.write(flush,0,len);
			}
			os.flush();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(null!=is) {
				is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void copy(String srcpath,String destpath) {
		File src=new File(srcpath);
		File dest=new File(destpath);
		InputStream is=null;
		OutputStream os=null;
		try {
			is=new FileInputStream(srcpath);
			os=new FileOutputStream(destpath);
			byte[] flush=new byte[1024];
			int len=0;
			while((len=is.read(flush))!=-1) {
				os.write(flush,0,len);
				os.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//释放资源，分别关闭，先打开的后关闭。
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	//利用递归拷贝文件夹
		public static void copyDir(String srcpath,String destpath) {
			 File src=new File(srcpath);
		        File dest=new File(destpath);
		        if(src!=null&&src.exists()) {
		            if(src.isFile()) {
		                copy(src.getAbsolutePath(),dest.getAbsolutePath());
		            }else {
		                dest.mkdirs();
		                File[] srcs=src.listFiles();
		                for(int i=0;i<srcs.length;i++) {
		                    copyDir(srcs[i].getAbsolutePath(),dest.getAbsolutePath()+"\\"+srcs[i].getName());
		                }
		            }
		        }
			 
		}
	public static void main(String[] args) {
	    long before=System.currentTimeMillis();
		copyDir("E:\\workspace-java\\IO流技术","E:\\workspace-java\\copy-IO流技术");
		long after=System.currentTimeMillis();
		System.out.println(after-before);
	}

}
