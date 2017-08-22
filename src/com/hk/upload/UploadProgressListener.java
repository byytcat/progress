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
			System.out.println("Ŀǰ�Ѿ���ȡ��" + PBytesRead + "�ֽ�����");
		} else {
			System.out.println("Ŀǰ�Ѿ���ȡ��" + PContentLength + "�е�" + PBytesRead
					+ "�ֽ�����");
		}
		Long KBytes = PBytesRead / 1024;
		double read = ((double) KBytes) / (PContentLength / 1024);
		System.out.println("�Ѿ���ȡ�ٷֱ�:" + read);
		// ��ȡ�ϴ����ȵİٷֱ�

		NumberFormat nf = NumberFormat.getPercentInstance();

		session.setAttribute("key", nf.format(read));
	}
}
