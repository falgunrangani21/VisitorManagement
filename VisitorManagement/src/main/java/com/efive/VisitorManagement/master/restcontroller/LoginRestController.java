package com.efive.VisitorManagement.master.restcontroller;

import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.efive.VisitorManagement.common.CommonDao;
import com.efive.VisitorManagement.common.EfiveUtils;
import com.efive.VisitorManagement.common.GetIpMac;
import com.efive.VisitorManagement.entity.Usermaster;
import com.efive.VisitorManagement.master.bean.LoginBean;
import com.efive.VisitorManagement.master.service.LoginService;
import com.efive.VisitorManagement.security.JwtGenerator;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("restlogin")
public class LoginRestController extends LoginBean {

	@Autowired
	private HttpSession session=null;
	JSONObject result = null;
	JSONArray array = null;

	@Autowired
	private LoginService loginservice;
	@Autowired
	private JwtGenerator jwt;
	@Autowired
	private CommonDao commondao;
	

	String token = "";

	@RequestMapping(value = "/checklogin", method = { RequestMethod.POST })
	public Map<String, Object> checklogin(@RequestBody Usermaster usermaster,HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {

				String jwtToken = "";
				if (usermaster != null)
	
					if (StringUtils.isEmpty(usermaster.getLoginname()) && usermaster.getPassword() == null || StringUtils.isEmpty(usermaster.getLoginname()) && usermaster.getPassword() == null) {
						token = "false";
					}
	
				List<?> list = loginservice.findUserByusernameAndpassword(usermaster.getLoginname(),usermaster.getPassword());
				if (list.size() == 0 || list.isEmpty() || list == null) {
	
					token = "false";
					map.put("failed", token);
					System.out.println("login failed");
				} else {
	
					Object[] objLoginData = (Object[]) list.get(0);
						if(true ==setUserDataInSession(objLoginData,request)){
								if(true ==saveLoginHistory(objLoginData,request)){
									fetchMenuList(objLoginData);
								}
						}
						result = new JSONObject();
						array = new JSONArray();
		
					 	Usermaster user = new Usermaster();
						user.setUserid(Long.parseLong(objLoginData[0].toString()));
						user.setLoginname(objLoginData[1].toString());
						user.setUsertype(objLoginData[2].toString());
		
						if (user != null) {
							jwtToken = jwt.generate(user);
							array.put(jwtToken);
							array.put(objLoginData[1].toString());
						}
						token = result.toString();
						token = "true";
						map.put("success", token);
						map.put("GetToken", jwtToken);
				}
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

	public  boolean setUserDataInSession(Object[] objLoginData,HttpServletRequest request){		// Set in Session
		try{
			
				String ipaddress = request.getRemoteAddr();
				String macaddress= new GetIpMac().getMacAddress();  
				
				if (null!=objLoginData[0])
					session.setAttribute("userid", objLoginData[0].toString());				
				if (null!=objLoginData[1])
					session.setAttribute("loginname", objLoginData[1].toString());
				if (null!=objLoginData[2])
					session.setAttribute("usertype", objLoginData[2].toString());								
				if (null!=objLoginData[3])
					session.setAttribute("roleaccesstype", objLoginData[3].toString());								
				if (null!=objLoginData[4])
					session.setAttribute("gender", objLoginData[4].toString());				
				// 5  =password				
				if (null!=objLoginData[6])
					session.setAttribute("email", objLoginData[5].toString());				
				if (null!=objLoginData[7])
					session.setAttribute("contactnumber", objLoginData[6].toString());				
				if (null!=objLoginData[8])
					session.setAttribute("countryid", objLoginData[7].toString());
				if (null!=objLoginData[9])
					session.setAttribute("stateid", objLoginData[8].toString());
				if (null!=objLoginData[10])
					session.setAttribute("cityid", objLoginData[9].toString());
				if (null!=objLoginData[10])
					session.setAttribute("passwordupdateon", objLoginData[10].toString());
				if (null!=objLoginData[10])
					session.setAttribute("isfirst", objLoginData[11].toString());
				if (null!=objLoginData[10])
					session.setAttribute("last_login", objLoginData[12].toString());
				session.setAttribute("ipaddress", ipaddress);
				session.setAttribute("macaddress", macaddress);					
				session.setAttribute("loginTxn", EfiveUtils.getTxnnumber());
				session.setAttribute("viewType", objLoginData[2].toString());
			
			return true;
		}catch(Exception e){ 
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean saveLoginHistory(Object[] objLoginData,HttpServletRequest request){		// Login History...
		try {		
				String ipaddress = request.getRemoteAddr();
				String macaddress= new GetIpMac().getMacAddress();  	
				String maxval =	commondao.getMaxValue("loginhistory", "UNIQUEID", null);
				String strQuery ="INSERT INTO loginhistory(uniqueid,userid,logintimedate,usertype, ipaddress, macaddress) "
						 			+" VALUES("+maxval+", "+objLoginData[0]+", NOW(), '"+objLoginData[2]+"', '"+ipaddress+"', '"+macaddress+"');";
				System.out.println("Login History Query"+strQuery);
				commondao.executeQuery(strQuery);
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void fetchMenuList(Object[] objLoginData){		// fetch Menu According Access and Team  Type  .. Pending....   teamwise.... menu
		try{
			// Menu Start .....
					String strQuery ="SELECT  l.linkid, l.linkname,l.linkurl,l.sortorder,l.parentlinkid,l.description, (SELECT COUNT(parentlinkid) FROM linkmaster i WHERE i.parentlinkid = l.linkid) AS COUNT, " + 
								" imagepath FROM linkmaster l  WHERE  UPPER(l.linktype)='M' AND l.active =1 ORDER  BY l.sortorder";
				
				System.out.println("Menu List"+strQuery);
				menuList = commondao.getQueryData(strQuery);
				System.err.println(menuList.size());
				for(Object obj : menuList) {
					Object[] obj1 = (Object[]) obj;
					System.err.println(obj);
					System.err.println(String.valueOf(obj1[4]));
				}
				
				optionList = commondao.getQueryData(strQuery.replaceAll("='M'", "='O'") );
				
				if (null!=menuList && menuList.size()>0)
				session.setAttribute("menuList", menuList);
				
				if (null!=optionList && optionList.size()>0)
				session.setAttribute("optionList", optionList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}