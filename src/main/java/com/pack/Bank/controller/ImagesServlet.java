package com.pack.Bank.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImagesServlet
 */
@WebServlet("/ImagesServlet")
public class ImagesServlet extends HttpServlet {
	
	public String path = "C:\\Users\\sarka\\Desktop\\Topics Training\\Practice Java\\SmartBank\\src\\main\\webapp\\cliparts\\";
	
	private static final long serialVersionUID = 1L;
       
    public ImagesServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		System.out.println(action);
		switch(action)
		{
		case "login":
			login(request,response);
			break;
		case "password":
			password(request,response);
			break;
		case "account_icon":
			account_icon(request,response);
			break;
		case "account_icon1":
			account_icon1(request,response);
			break;
		case "account_add":
			account_add(request,response);
			break;
		case "account_list":
			account_list(request,response);
			break;
		case "corporate_icon":
			corporate_icon(request,response);
			break;
		case "corporate_icon1":
			corporate_icon1(request,response);
			break;
		case "update":
			update(request,response);
			break;
		case "user_icon":
			user_icon(request,response);
			break;
		case "user":
			user(request,response);
			break;
		case "transaction":
			transaction(request,response);
			break;
		case "trans":
			trans(request,response);
			break;
		}
	}
	public static boolean isPathValid(String path) {
        try {
            Paths.get(path);

        } catch (InvalidPathException ex) {
            return false;
        }

        return true;
    }
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"login.jpg");
		System.out.println(file.exists());
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void password(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"password.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
		System.out.println("Read");
	}
	protected void account_icon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"account-icon-1.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void transaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"transaction1.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void trans(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"trans.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void account_icon1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"account-icon1.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void account_list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"aclist.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void account_add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"add_account.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void corporate_icon1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"corporate-icon.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"update.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void corporate_icon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"corporate-icon-1.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void user_icon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"user-icon1.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
	protected void user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(path+"users.jpg");
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "JPG", response.getOutputStream());
	}
}
