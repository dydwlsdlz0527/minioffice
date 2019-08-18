package com.minioffice.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {

    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		int index = contextPath.length();
		String servletPath = uri.substring(index);
		String path = "/error/err404.jsp";
		
		ServletContext sc = getServletContext();
		String realPath = sc.getRealPath("/WEB-INF/dispatcher.properties");
		Properties env = new Properties();
		env.load(new FileInputStream(realPath));
		
		String classNMethodName = env.getProperty(servletPath);
		int classNMethodIndex = classNMethodName.lastIndexOf(".");
		String className = classNMethodName.substring(0,classNMethodIndex);
		String methodName = classNMethodName.substring(classNMethodIndex+1);
		try {
			Class clazz = Class.forName(className); //	Ŭ�����̸��� �ش� Ŭ���� �ε�
			//Object obj = clazz.newInstance();	// Ŭ���� Ÿ���� ��ü ����
			Method getInstanceMethod = clazz.getMethod("getInstance", null);
			Object obj = getInstanceMethod.invoke(null, null);
			Method method = clazz.getMethod(methodName, HttpServletRequest.class, 
					HttpServletResponse.class);	// �޼��� �̸��� �ش��ϴ� �޼��� ã��
			System.out.println(method.toString());
			path=(String)method.invoke(obj, request, response); //�޼��� ȣ��
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//5) Ŭ���� �̸��� �ش� ��ü ã��.	-- �̱��� ����
		//6) ��ü�� �����ִ� �޼��� ã��.
		//7) �޼��� ȣ���ϱ�
		System.out.println(path);
		if(path!=null) {
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}

}
