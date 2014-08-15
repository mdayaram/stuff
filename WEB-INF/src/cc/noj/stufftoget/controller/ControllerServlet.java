package cc.noj.stufftoget.controller;

import javax.servlet.http.HttpServlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;

import cc.noj.stufftoget.databeans.ItemBean;
import cc.noj.stufftoget.databeans.UserBean;
import cc.noj.stufftoget.model.Model;


public class ControllerServlet extends HttpServlet {
	
	private Model myModel;

	public void init() throws ServletException {
		myModel = new Model(getServletConfig());
		
		Action.add(new AddAction(myModel));
		Action.add(new EditAction(myModel));
		Action.add(new PurchaseAction(myModel));
		Action.add(new ListAvailableAction(myModel));
		Action.add(new ListPurchasedAction(myModel));
		Action.add(new LoginAction(myModel));
		Action.add(new ListPrizesAction(myModel));
		Action.add(new AddPrizeAction(myModel));
		Action.add(new RedeemAction(myModel));
		Action.add(new ViewAction(myModel));
		Action.add(new RegisterAction(myModel));
		Action.add(new ImageAction(myModel));
		Action.add(new LogoutAction(myModel));
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
        
		String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException {
    	
    	doGet(request,response);
    }
    
    
    /**
     * Extracts the requested action and (depending on whether the user 
     * is logged in) perform it (or make the user login).
     * 
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) 
    	throws ServletException {
    	
        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
        
        UserBean user = (UserBean) session.getAttribute("user");
        String action = getActionName(servletPath);

        // Since every page displays the user list on the left side,
        // we place it on the request now before performing any action.
        if (session.getAttribute("userList") == null){
        	try{
        		UserBean[] userList = myModel.getUserDAO().listTopTen();
        		session.setAttribute("userList", userList);
        	}catch(DAOException e){
        		throw new ServletException(e);
        	}
        }
        
        if(session.getAttribute("userMap") == null){
        	try {
        		HashMap<Integer, UserBean> userMap = new HashMap<Integer,UserBean>();
        		UserBean[] userList = myModel.getUserDAO().listUsers();
        		for(int i = 0; i < userList.length; i++){
        			userMap.put(userList[i].getId(), userList[i]);
        		}
        		session.setAttribute("userMap", userMap);
        	}catch(DAOException e){
        		throw new ServletException(e);
        	}
        }
        
        if(session.getAttribute("statusMap") == null){
        	String[] statusMap = new String[3];
        	statusMap[0] = ItemBean.statusToString(0);
        	statusMap[1] = ItemBean.statusToString(1);
        	statusMap[2] = ItemBean.statusToString(2);
        	session.setAttribute("statusMap", statusMap);
        }
        
        if(session.getAttribute("rankMap") == null) {
        	String[] rankMap = new String[3];
        	rankMap[0] = ItemBean.rankToString(0);
        	rankMap[1] = ItemBean.rankToString(1);
        	rankMap[2] = ItemBean.rankToString(2);
        	session.setAttribute("rankMap", rankMap);
        }
        
        // If he's at the start page, send him to home.
        if (action.equals("start")) {
			return Action.perform("view.do", request);
        }
        
        // Let users that are not logged in register or log in.
        if (action.equals("register.do") || action.equals("login.do")
        		|| action.equals("view.do") || action.equals("available.do")
        		|| action.equals("purchased.do") || action.equals("prizes.do")
        		|| action.equals("image.do")) {
			return Action.perform(action,request);
        }
        
        // If a user is not logged in from here on out, just ask them to log in.
        if (user == null) {
        	request.setAttribute("from", action);
        	return Action.perform("login.do", request);
        }
        
        // only manoj gets to edit items
        if(action.equals("edit.do") || action.equals("addprize.do")){
        	if (!user.getEmailAddress().equals("i.am.noj@gmail.com")){
        		request.removeAttribute("id");
        		List<String> errors = new ArrayList<String>();
                request.setAttribute("errors",errors);
                errors.add("You're not authorized to view this page.");
        		return "error.jsp"; 
        	}
        }
        
      	// Let the logged in user do whatever else he wants.
		return Action.perform(action,request);
    }
    
    
    /**
     * If nextPage is null, send back 404
     * If nextPage starts with a '/', redirect to this page.
     * 	In this case, the page must be the whole servlet path including 
     * 	the webapp name.
     * Otherwise dispatch to the jsp page (the view), this is the common case.
     * If nextPage starts with an http://, it means the user clicked on a 
     * 	bookmark, so redirect them to that page.
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, 
    		HttpServletResponse response) throws IOException, ServletException {
    	
    	// 404 error
    	if (nextPage == null) {
    		int not_found = HttpServletResponse.SC_NOT_FOUND;
    		response.sendError(not_found,request.getServletPath());
    		return;
    	}
    	
    	// performing an action
    	if (nextPage.charAt(0) == '/') {
			String host = request.getServerName();
			String port = ":"+String.valueOf(request.getServerPort());
			if (port.equals(":80")) port = "";
			response.sendRedirect("http://"+host+port+nextPage);
			return;
    	}
    	
    	// clicking on an external link
    	if (nextPage.startsWith("http://")){
        	response.sendRedirect(nextPage);
        	return;
    	}
    	
    	// viewing jsp page
   		RequestDispatcher d = request.getRequestDispatcher("/"+nextPage);
   		d.forward(request,response);
    }
    
	/**
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
    
}

