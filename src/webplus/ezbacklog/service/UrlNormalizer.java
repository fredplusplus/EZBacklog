package webplus.ezbacklog.service;

public class UrlNormalizer {
	public String normalize(String url) {
		String result = url;
		if (result == null || result.length() == 0) {
			result = "about:blank";
		} else {
			if (!url.contains("://")) {
				result = "http://" + url;
			}
		}
		return result;
	}
}
