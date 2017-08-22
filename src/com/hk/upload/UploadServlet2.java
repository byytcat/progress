package com.hk.upload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(
					diskFileItemFactory);
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
				if (fileItem.isFormField()) {
					String inputName = fileItem.getFieldName();
					String inputValue = fileItem.getString();
					System.out.println(inputName + "=" + inputValue);
				} else {
					String filename = fileItem.getName().substring(
							fileItem.getName().lastIndexOf("\\") + 1);
					InputStream in = fileItem.getInputStream();
					int len = 0;
					byte buffer[] = new byte[1024];
					FileOutputStream out = new FileOutputStream("f:\\"
							+ filename);
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
