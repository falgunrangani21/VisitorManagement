package com.efive.VisitorManagement.master.restcontroller;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.efive.VisitorManagement.common.CommonDao;
import com.efive.VisitorManagement.common.FileUtility;
import com.efive.VisitorManagement.common.GetIpMac;
import com.efive.VisitorManagement.entity.Usermaster;
import com.efive.VisitorManagement.master.bean.UserMasterBean;
import com.efive.VisitorManagement.master.service.CommonUtilService;
import com.efive.VisitorManagement.master.service.UserService;

@RestController
@RequestMapping(value="/restusermaster")
public class UserMasterRestController extends UserMasterBean{

	String path="ATTACHMENT/uploadimage/userimages";
	@Autowired
	private CommonUtilService service;
	@Autowired
	private CommonDao commondao;	
	@Autowired
	private GetIpMac getipmac;
	@Autowired
	private UserService userservice;
	 
	@Autowired 
	ServletContext context;
	@Autowired
	HttpServletRequest request;
	
	@Value("${visitor.serverpath}")
	String serverpath;
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/searchUsermasterByAjax")
	public String searchUsermasterByAjax(Model model, Pageable pageable, HttpServletRequest request) {

		iDisplayLength = request.getParameter("iDisplayLength");
		iDisplayStart = request.getParameter("iDisplayStart");
		String sortcolumn = request.getParameter("iSortCol_0");
		String Direction1 = request.getParameter("sSortDir_0");
		String sSearch = request.getParameter("sSearch");
		
		/*if(null==sSearch.trim() &&  sSearch.trim().length()==0){
			sSearch="";
		}*/
		//int page = Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength);
		//totalrecords = userservice.countUserdata();
		JSONObject returndata = new JSONObject();
		calculateDataTableVar();

		/*Direction dir = null;

		if (Direction1.equals("asc")) {
			dir = Direction.ASC;
		} else {
			dir = Direction.DESC;
		}*/
		returndata = generateJsonArray(userservice.getUserByQuery());
		return returndata.toString();

	}

	public JSONObject generateJsonArray(List<?> list) {
		
		
		try {
			result = new JSONObject();
			array = new JSONArray();

			JSONObject obj = null;
			
			for (int i = 0; i < list.size(); i++) {
				String activeStr = "";
				Object[] data = (Object[]) list.get(i);
				obj = new JSONObject();
				String filePath = null;
				if(data[1] !=null && data[1].toString().length() >0)
				filePath =serverpath+setIfNull(data[1], ""); 
			
				String str= " onclick=\"showUserData('"+setIfNull(filePath,"")+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[6]+"'"
							+ ",'"+data[13]+"','"+data[18]+"','"+data[16]+"','"+data[0]+"')\" ";
					
				String strimg=null;
	 			if(data[1] !=null && data[1].toString().length() >0)
				{	
	 				filePath =serverpath+setIfNull(data[1], ""); 
	 				strimg	= " <a data-toggle=\"modal\" data-target=\"#primary\" class=\"user-profile text-center color-custom-demo\">"
	 						+ " <img src='"+filePath+"'  class=\"user-profile text-center color-custom-demo\" "+str+" alt='"+setIfNull(data[2], "")+"' "
	 								+ " title='"+setIfNull(data[2], " ")+"'></a>";
				}
				else 
				{
					strimg= " <a data-toggle=\"modal\" data-target=\"#primary\" class=\"user-profile text-center color-custom-demo\">" + 
							" <img src=\"\" class=\"letterpic\" "+str+" alt='"+data[2]+"' title='"+data[2]+"'></a>";
				}
	 			
	 			obj.put("profileurl", strimg);
				obj.put("username", data[2]);
				obj.put("email", data[3]);
				obj.put("contactnumber", "+"+data[17] +""+data[4]);
				obj.put("gender", data[6]);
				obj.put("countryname", data[8]);
				obj.put("statename", data[10]);
				obj.put("cityname", data[12]);
				
				strQuery = "<a class=\"mr-1 text-success\" href=\"#\" onclick=\"editData('" + data[0] + "','"+setIfNull(filePath, "")+"','"+ data[2] +"','"+ setIfNull(data[15], "") +"'"
						+ ",'"+data[16]+"','"+data[6]+"','"+data[3]+"','"+setIfNull(data[4], "")+"','"+setIfNull(data[7], "")+"','"+setIfNull(data[9], "")+"','"+setIfNull(data[11], "") +"'"
								+ ",'"+data[13]+"','"+data[14]+"')\"><i  class=\"fa fa-edit\"></i></a>"
						+ "<a class=\"text-danger mr-1\"  href=\"#\" onclick=\"deleteData('" + data[0] + "')\"><i class=\"fa fa-trash\"></i>";
				obj.put("action", strQuery);
				array.put(obj);
				obj = null;
				totalAfterFilter = i;
			}

			result.put("iTotalRecords", totalrecords);
			result.put("iTotalDisplayRecords", totalrecords);
			result.put("iDisplayLength", iDisplayLength);
			result.put("aaData", array);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/saveData", method = { RequestMethod.POST } , consumes ={"multipart/form-data"})
	public Map<String, Object> saveData(@RequestPart("usermaster") Usermaster usermaster,@RequestPart(name="userprofile",required=false) MultipartFile file) {
		
		Map<String, Object> map = new HashMap<String, Object>();
	
		try {
						
			Long id = usermaster.getUserid();
			//isExist Check
			String str="select loginname,email from usermaster where active=1 ";
			if(id != null && !StringUtils.isEmpty(String.valueOf(id)))
			str += " AND userid!= '"+id+"' ";
			if(!StringUtils.isEmpty(usermaster.getLoginname()) && !StringUtils.isEmpty(usermaster.getEmail()))
			str += " AND (LOWER(loginname)=LOWER('"+usermaster.getLoginname()+"') OR LOWER(email)=LOWER('"+usermaster.getEmail()+"'))";
			List<?> isExist=commondao.getQueryData(str);
			if(isExist.size()>0)
			{
				map.put("DUPLICATE", "User Already Exist");
				return map;
			}	
			else {
			
			Usermaster user=new Usermaster();
			if (id == null || StringUtils.isEmpty(String.valueOf(id))) {
				String maxid = commondao.getMaxValue("usermaster", "userid", null);
				user.setUserid(Long.parseLong(maxid));
				user.setActive(1);
				user.setIpaddress(getipmac.getIpAddress());
				user.setMacaddress(getipmac.getMacAddress());
				user.setCreatedby("A");
				user.setCreatedon(new Timestamp(System.currentTimeMillis()));
				String filepath="";
				if(null!=file) {
					File uploadfile = FileUtility.convert(file);
					filepath = FileUtility.newUploadFile(uploadfile,uploadfile.getName(),path,"user_"+maxid+"",context.getRealPath(""));
					user.setProfileurl(filepath);
				}
				
			} else {
				
				user = userservice.findById(id);
				user.setActive(1);
				user.setIpaddress(getipmac.getIpAddress());
				user.setMacaddress(getipmac.getMacAddress());
				user.setModifiedby("A");
				user.setModifiedon(new Timestamp(System.currentTimeMillis()));
			 	String filepath="";
				if(null!=file) {
					File uploadfile = FileUtility.convert(file);	
				
					filepath = FileUtility.newUploadFile(uploadfile,uploadfile.getName(),path,"user_"+usermaster.getUserid()+"",context.getRealPath(""));
					user.setProfileurl(filepath);
				}
			}
			if (null != usermaster.getUsername() && usermaster.getUsername().trim().length() > 0) {
				user.setUsername(usermaster.getUsername().trim());
			}
			if (null != usermaster.getUsertype()	&& usermaster.getUsertype().trim().length() > 0) {
				user.setUsertype(usermaster.getUsertype().trim());
			}
			if (null != usermaster.getRoleaccesstype() && usermaster.getRoleaccesstype().trim().length() > 0) {
				user.setRoleaccesstype(usermaster.getRoleaccesstype().trim());
			}
			if (null != usermaster.getGender() && usermaster.getGender().trim().length() > 0) {
				user.setGender(usermaster.getGender().trim());
			}
			if (null != usermaster.getEmail() && usermaster.getEmail().trim().length() > 0) {
				user.setEmail(usermaster.getEmail().trim());
			}
			if (null != usermaster.getContactnumber() && usermaster.getContactnumber().trim().length() > 0) {
				user.setContactnumber(usermaster.getContactnumber().trim());	
			}
			if (null != usermaster.getCountryid() && usermaster.getCountryid() > 0) {
				user.setCountryid(usermaster.getCountryid());
			}
			if (null != usermaster.getStateid() && usermaster.getStateid() > 0) {
				user.setStateid(usermaster.getStateid());
			}
			if (null != usermaster.getCityid() && usermaster.getCityid() > 0) {
				user.setCityid(usermaster.getCityid());
			}
			if (null != usermaster.getLoginname() && usermaster.getLoginname().trim().length() > 0) {
				user.setLoginname(usermaster.getLoginname().trim());
			}
			if (null != usermaster.getPassword() && usermaster.getPassword().trim().length() > 0) {
				user.setPassword(usermaster.getPassword().trim());
			}
			userservice.save(user);
			map.put("SAVE", "SUCCESSFULLY");
			}	
		} catch (Exception e) {
			map.put("ERRORMASSAGE", "Error occured during save data please contact IT.");
			e.printStackTrace();
		} finally {
			
		}
		return map;
	}
	
	@RequestMapping(value = "/deleteData", method = { RequestMethod.POST })
	public void deleteData(@RequestParam("userid") String userid)
	{
		try {
			
			if(!StringUtils.isEmpty(userid))
			{	
				Usermaster user=new Usermaster();
				user= userservice.findById(Long.parseLong(userid));
				user.setActive(0);
				user.setIpaddress(getipmac.getIpAddress());
				user.setMacaddress(getipmac.getMacAddress());
				user.setModifiedby("A");
				user.setModifiedon(new Timestamp(System.currentTimeMillis()));
				userservice.save(user);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getStateData")
	public Map<String, List<?>> getStateData(@RequestParam("countryid") String countryid) {
		
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		try {
			
			if(null!=countryid && countryid.trim().length()>0 && !countryid.isEmpty()) {
				map.put("stateList", service.getStateData(Long.parseLong(countryid)));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/getCityData")
	public Map<String, List<?>> getCityData(@RequestParam("countryid") String countryid ,@RequestParam("stateid") String stateid)
	{
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		try {
			if(null!=countryid && countryid.trim().length()>0 && null!=stateid && stateid.trim().length()>0)
			{
				map.put("cityList", service.getCityData(Long.parseLong(stateid),Long.parseLong(countryid)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
