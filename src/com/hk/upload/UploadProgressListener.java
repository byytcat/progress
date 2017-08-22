package com.hk.upload;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class UploadProgressListener implements ProgressListener {

	private HttpSession session;

	public UploadProgressListener(HttpServletRequest request) {

		this.session = request.getSession();
	}

	@Override
	public void update(long PBytesRead, long PContentLength, int PItems) {
		if (PContentLength == -1) {
			System.out.println("目前已经读取了" + PBytesRead + "字节数据");
		} else {
			System.out.println("目前已经读取了" + PContentLength + "中的" + PBytesRead
					+ "字节数据");
		}
		Long KBytes = PBytesRead / 1024;
		double read = ((double) KBytes) / (PContentLength / 1024);
		System.out.println("已经读取百分比:" + read);
		// 获取上传进度的百分比

		NumberFormat nf = NumberFormat.getPercentInstance();

		session.setAttribute("key", nf.format(read));
	}
}
