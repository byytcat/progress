package com.hk.upload;

import java.io.File;
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

public class UploadServlet4 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			diskFileItemFactory.setSizeThreshold(1024 * 1024);// 1M
			diskFileItemFactory.setRepository(new File(this.getServletContext()
					.getRealPath("/temp")));
			ServletFileUpload upload = new ServletFileUpload(
					diskFileItemFactory);
			if (!upload.isMultipartContent(request)) {
				String username = request.getParameter("username");
				return;
			}
			// 中文文件上传的乱码解决
			upload.setHeaderEncoding("UTF-8");
			upload.setFileSizeMax(1024 * 1024 * 50);
			// 设置监听器
			UploadProgressListener listener = new UploadProgressListener(
					request);
			upload.setProgressListener(listener);
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
				if (fileItem.isFormField()) {
					String inputName = fileItem.getFieldName();
					String inputValue = fileItem.getString("UTF-8");
					System.out.println(inputName + "=" + inputValue);
				} else {
					String filename = fileItem.getName().substring(
							fileItem.getName().lastIndexOf("\\") + 1);
					InputStream in = fileItem.getInputStream();
					int len = 0;
					byte buffer[] = new byte[1024];
					String fileNamePath = this.getServletContext().getRealPath(
							"/WEB-INF/upload");
					FileOutputStream out = new FileOutputStream(fileNamePath
							+ File.separator + filename);
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					fileItem.delete();
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
