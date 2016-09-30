package green.tor.service;

import java.util.List;

import blue.lang.json.JsonArray;
import blue.lang.json.JsonElement;
import blue.lang.json.JsonObject;

public class TsTorrentList {
	
	// ======================================
	
	private JsonObject obj;
	
	// ======================================

	TsTorrentList(JsonObject obj){
		this.obj = obj;
	}
	
	// ======================================
	
	public Long getTorrentc(){
		return obj.getLong("torrentc");
	}
	
	private JsonArray getTorrents(){
		return obj.get("torrents").getAsJsonArray();
	}
	
	private JsonArray getRssFeeds(){
		return obj.get("rssfeeds").getAsJsonArray();
	}
	
	private JsonArray getRssFilters(){
		return obj.get("rssfilters").getAsJsonArray();
	}
	
	private JsonArray getLabels(){
		return obj.get("label").getAsJsonArray();
	}
}
