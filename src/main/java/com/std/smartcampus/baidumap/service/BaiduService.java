package com.std.smartcampus.baidumap.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class BaiduService {

	public String getLocationFromAddress(String address){
		String result = null;
		StringBuilder url = new StringBuilder("http://api.map.baidu.com/geocoder/v2/?")
				.append("address=").append(address)
				.append("&output=").append("json")
				.append("&ak=").append("yj91Vu4z9jatCPvnRkS1kwLAoT39eSTI");
		try {
			result = IOUtils.toString(new URL(url.toString()), Charset.forName("UTF-8"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
