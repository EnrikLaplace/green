package green.tor;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import blue.lang.json.Json;
import blue.lang.json.JsonObject;
import blue.net.http.HttpRequest;
import blue.runtime.App;
import blue.util.Utils;
import blue.util.ZipExe;

public final class TorApp {
	
	private static final int DEFAULT_PORT = 8077;
	
	// static
	private TorApp(){}
	
	// =============================================

	private static URI fexe;
	private static URI fsettings;
	private static App torr;
	
	// =============================================
	
	private static synchronized final void init(){
		// put runnable in temp
		fexe = ZipExe.getFile("utorrent/torr.exe", TorApp.class);
		// settings
		fsettings = ZipExe.getFile("utorrent/settings.dat", TorApp.class, new File(fexe).getParentFile());
	}
	
	// =============================================

	private static String token;
	private static String guid;
	
	public static boolean refreshToken() {
		try {
			HttpRequest tokenReq = HttpRequest.get(new URL("http://localhost:8077/gui/token.html")).auth("admin", "admin999");
			token = tokenReq.readString();
			if(tokenReq.getRespCode()!=200){
				token = null;
				guid = null;
				return false;
			}
			token = Utils.splitBetween(token, "style='display:none;'>","</div></html>");
			guid = tokenReq.getCookie("GUID").getValue();
			return true;
		} catch (MalformedURLException e) {
		} catch(Exception e){
			// conenct exception
			return false;
		}
		return false;
	}
	
	private static synchronized String doRequest(String req){
		try {
			HttpRequest reqHttp = HttpRequest.get(new URL("http://localhost:8077/gui/?"+req+"&token="+token)).setCookie("GUID", guid).setUseBrowser(true).auth("admin", "admin999");
			String res = reqHttp.readString();
			if(reqHttp.getRespCode() == 200){
				return res;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static synchronized JsonObject request(String req){
		String res = doRequest(req);
		if(res == null){
			refreshToken();
			res = doRequest(req);
		}
		return Json.parse(res);
	}
	
	public static synchronized boolean start(){
		if(fexe == null){
			init();
		}
		stop();
		
		try {
			torr = new App(fexe.toURL(), "/hide");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		torr.setUnique(true);
		torr.setDaemon(true);
		boolean start = torr.start();
		if(!start){
			return false;
		}
		while(!refreshToken()){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				return false;
			}
		}
		return true;
	}
	
	public static synchronized boolean stop(){
		if(torr == null){
			return false;
		}
		torr.stop(0); // brutal stop!
		torr = null;
		return true;
	}
	
}
