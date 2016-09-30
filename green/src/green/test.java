package green;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import blue.lang.json.JsonObject;
import blue.net.tcp.Tcp;
import blue.util.StreamUtils;
import green.tor.TorApp;

public class test {
	
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
	
		
//		TorApp.start();
//		JsonObject res = null;
//		while(res == null){
//			try{
//				res = TorApp.request("list=1");
//			}catch(Exception e){
//				Thread.sleep(3000);
//				continue;
//			}
//			System.out.println(res);
//			Thread.sleep(1000);
//		}
	}
	
	
//	public static void main(String[] args) throws InterruptedException, MalformedURLException {
//		// put runnable in temp
//		URI fexe = ZipExe.getFile("utorrent/torr.exe", test.class);
//		// settings
//		URI settings = ZipExe.getFile("utorrent/settings.dat", test.class, new File(fexe).getParentFile());
//		System.out.println(new File(fexe).getAbsolutePath());
//		System.out.println(new File(settings).getAbsolutePath());
//		
////		App app = ZipExe.open(test.class, "utorrent/torr.exe", "/hide");
////		App app = new App(fexe.toURL(), "/hide");
//		App app = new App(fexe.toURL());
//		app.setUnique(true);
//		app.setDaemon(true);
//		app.start();
//		app.waitMe();
//		
//		
//	}
}
