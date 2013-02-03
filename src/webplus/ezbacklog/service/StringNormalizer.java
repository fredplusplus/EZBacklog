package webplus.ezbacklog.service;

import java.net.URLDecoder;

import org.springframework.web.util.HtmlUtils;

public class StringNormalizer {

	public String URIDecode(String str) {
		if (str != null) {
			try {
				return URLDecoder.decode(str.replace("+", "%2B"), "UTF-8").replace("%2B", "+");
			} catch (Exception e) {
			}
		}
		return str;
	}

	public String normalize(String str, int maxlength) {
		str = URIDecode(str);
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
