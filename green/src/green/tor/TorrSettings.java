package green.tor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import blue.lang.Bencode;
import blue.lang.Bencode.BcList;
import blue.lang.Bencode.BcObject;
import blue.util.StreamUtils;

public class TorrSettings {
	protected static final String CFG_EXTERNAL_TCP_PORT = "upnp.external_tcp_port"; // 54033
	protected static final String CFG_EXTERNAL_UDP_PORT = "upnp.external_udp_port"; // 54033
	protected static final String WEBUI_PORT = "webui.port"; // 9090
	protected static final String CFG_CONFIRM_EXIT = "confirm_exit"; // 0
	protected static final String CFG_DIRECTORIES = "labelDirectoryMap"; // audio=?/documents=?/video=?

	protected static final String CFG_DL_ACTIVE = "dir_active_download"; // C:\Users\Matteo\Downloads\ldr
	protected static final String CFG_DL_ACTIVE_FLG = "dir_active_download_flag"; // 1
	protected static final String CFG_DL_AUTOLOAD = "dir_autoload";
	protected static final String CFG_DL_AUTOLOAD_FLG = "dir_autoload_flag"; // 1
	protected static final String CFG_DL_DONE = "dir_completed_download";
	protected static final String CFG_DL_DONE_FLG = "dir_completed_download_flag"; // 1
	protected static final String CFG_TORR_DONE = "dir_completed_torrents";
	protected static final String CFG_TORR_DONE_FLG = "dir_completed_torrents_flag"; // 1
	protected static final String CFG_TORR = "dir_torrent_files";
	protected static final String CFG_TORR_FLG = "dir_torrent_files_flag"; // 1
	
	private File file;
	private BcObject settDecoder;
	
	public TorrSettings(File file) throws FileNotFoundException{
		this.file = file;
		settDecoder = Bencode.decode(new String(StreamUtils.read(new FileInputStream(file), true)));
	}
	
	public void save(){
		StreamUtils.save(new ByteArrayInputStream(settDecoder.encode()), file);
	}
	
	protected String[] getParams(){
		BcList list = ((BcList)settDecoder);
		return list.keySet();
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends BcObject> T get(String key){
		return (T) ((BcList)settDecoder).get(key);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		TorrSettings sett = new TorrSettings(new File(ClassLoader.getSystemClassLoader().getResource("utorrent/settings.dat").getPath()));
		for(String p:sett.getParams()){
			System.out.println(p + " = " + sett.get(p));
		}
	}
}
