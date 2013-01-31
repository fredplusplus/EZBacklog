package webplus.ezbacklog.service;

import org.springframework.web.util.HtmlUtils;

public class StringNormalizer {

	public String normalize(String str, int maxlength) {
		if (maxlength < 5) {
			maxlength = 5;
		}
		String result = str;
		if (result == null) {
			result = "";
		}
		result = HtmlUtils.htmlUnescape(result);
		result = HtmlUtils.htmlEscape(result);
		if (result.length() >= maxlength) {
			result = result.substring(0, maxlength - 5) + "\n...";
		}

		return result;
	}
	
	
	public String normalize(String str) {
		return this.normalize(str, 500);
	}
}
