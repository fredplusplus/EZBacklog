package webplus.ezbacklog.service;

import org.springframework.web.util.HtmlUtils;

public class StringNormalizer {

	public String normalize(String str) {
		String result = str;
		if (result == null) {
			result = "";
		}
		result = HtmlUtils.htmlUnescape(result);
		result = HtmlUtils.htmlEscape(result);
		if (result.length() >= 500) {
			result = result.substring(0, 495) + "\n...";
		}

		return result;
	}
}
