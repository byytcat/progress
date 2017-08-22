package com.hk.upload;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 如果表单类型为multipart/form-data的话，在servlet中注意就不能采用传统方式获取数据
		/*
		 * String username = request.getParameter("username");
		 * System.out.println(username);
		 */

		InputStream in = request.getInputStream();
		int len = 0;
		byte buffer[] = new byte[1024];
		while ((len = in.read(buffer)) > 0) {
			System.out.println(new String(buffer, 0, len));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
