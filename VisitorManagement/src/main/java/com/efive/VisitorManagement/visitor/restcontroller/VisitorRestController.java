package com.efive.VisitorManagement.visitor.restcontroller;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.efive.VisitorManagement.entity.Visitor;
import com.efive.VisitorManagement.entity.Visitorcard;
import com.efive.VisitorManagement.master.service.CommonUtilService;
import com.efive.VisitorManagement.visitor.bean.VisitorBean;
import com.efive.VisitorManagement.visitor.service.VisitorCardService;
import com.efive.VisitorManagement.visitor.service.VisitorService;

@RestController
@RequestMapping("/restvisitor")
public class VisitorRestController extends VisitorBean{
	
	String path="ATTACHMENT/uploadimage/visitorimages";
	@Autowired
	private CommonUtilService service;
	@Autowired
	private CommonDao commondao;
	@Autowired
	private VisitorService vservice;
	@Autowired
	private GetIpMac getipmac;
	 
	@Autowired
	private VisitorCardService vcservice;
	
	@Autowired 
	ServletContext context;
	@Autowired
	HttpServletRequest request;
	
	@Value("${visitor.serverpath}")
	String serverpath;
	
	@RequestMapping(value = "/searchVisitorByAjax")
	public String searchVisitorByAjax(Model model, Pageable pageable, HttpServletRequest request) {

		iDisplayLength = request.getParameter("iDisplayLength");
		iDisplayStart = request.getParameter("iDisplayStart");
		String Direction1 = request.getParameter("sSortDir_0");
		String searchflag = request.getParameter("searchflag");
		String cvsfromtime = request.getParameter("cvsfromtime");
		String cvstotime = request.getParameter("cvstotime");
		String cvscompany = request.getParameter("cvscompany");
		String cvsemployee = request.getParameter("cvsemployee"); 
		
		String avvisitor = request.getParameter("avvisitor");
		String avlocationid = request.getParameter("avlocationid"); 
		String avemployeeid = request.getParameter("avemployeeid");
		String avcardid = request.getParameter("avcardid");
		totalrecords = vservice.countVisitordata();
		JSONObject returndata = new JSONObject();
		calculateDataTableVar();

		Direction dir = null;

		String str ="SELECT v.vid,v.vphoto,v.vname,v.vemail,v.vcontactnumber,v.visfromcompany,v.vcompanyid,v.visitortype,v.vlocation,v.employeeid,"
				+ " e.empname,v.purpose,c.vpassnumber,v.noofperson,v.isvehicle,v.vehiclenumber,v.ismaterialcarried,v.materialdeposit," 
				+ " SUBSTRING(checkedin,12,8) AS checkedinTime,SUBSTRING(checkedin, 1,10) AS checkedinDate,SUBSTRING(checkedout, 1,10) AS checkedoutDate,"
				+ " SUBSTRING(checkedout,12,8) AS checkedoutTime,vc.vcname,(CASE WHEN v.visfromcompany='y' THEN 'Yes' WHEN v.visfromcompany='n' THEN 'No'" 
				+ " END) AS visfromcompanys,(CASE WHEN v.isvehicle='y' THEN 'Yes' WHEN v.isvehicle='n' THEN 'No' END) AS isvehicles,"
				+ " (CASE WHEN v.ismaterialcarried='y' THEN 'Yes' WHEN v.ismaterialcarried='n' THEN 'No' END) AS ismaterialcarrieds,v.locationid"
				+ "  FROM visitor AS v "
				+ " LEFT JOIN visitorcompanymaster AS vc ON v.vcompanyid=vc.vcompanyid "
				+ " LEFT JOIN employeemaster AS e ON v.employeeid=e.employeeid"
				+ " LEFT JOIN visitorcard AS c ON v.vpassid=c.vcardid "
				+ " WHERE v.locationid=1 ";
		
		if(null!=searchflag) {
				
			if(searchflag.equals("c")) {
		 
				if(null!= cvsfromtime && cvsfromtime.trim().length()>0 && null!= cvstotime && cvstotime.trim().length()>0) {
					str += " and (DATE_FORMAT(checkedin,'%H:%i') >=  DATE_FORMAT('"+cvsfromtime+"','%H:%i')   "
							+ " AND  DATE_FORMAT(checkedout,'%H:%i') <=  DATE_FORMAT('"+cvstotime+"','%H:%i')  ";
							
				}
				if(null!= cvscompany && cvscompany.trim().length()>0) {
					str+= " OR LOWER(vc.vcompanyid) LIKE ('%"+cvscompany+"%')";
				}			
				if(null!= cvsemployee && cvsemployee.trim().length()>0) {
					str+= " OR LOWER(v.employeeid) LIKE LOWER('%"+cvsemployee+"%')";
				}
				str+= " AND DATE_FORMAT(v.createdon,'%Y-%m-%d') = DATE_FORMAT(CURDATE(),'%Y-%m-%d')) ORDER BY vid";	
				System.out.println("str Query"+str);
			}
			if(searchflag.equals("a")) {
			
				if(null!= avvisitorid && avvisitorid.trim().length()>0) {
					str+= " AND vid = "+avvisitorid+" ";
				}
				if(null!= avlocationid && avlocationid.trim().length()>0) {
					str+= " OR LOWER(v.vlocation) LIKE LOWER('%"+avlocationid+"%')";
				}			
				if(null!= avemployeeid && avemployeeid.trim().length()>0) {
					str+= " OR v.employeeid = "+avemployeeid+" ";
				}			
				if(null!= avcardid && avcardid.trim().length()>0) {
					str+= " OR vpassid = "+avcardid+"";
				}			
				str+= " ORDER BY vid";	
				System.out.println("all====="+str);
			}
		}	
		returndata = generateJsonArray(getDataFromQuery(str));
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
					
	 			String str= " onclick=\"showUserData('"+setIfNull(data[0],"")+"','"+setIfNull(filePath,"")+"','"+setIfNull(data[2],"")+"','"+setIfNull(data[3],"")+"'"
						+ " ,'"+setIfNull(data[4],"")+"','"+setIfNull(data[8],"")+"','"+setIfNull(data[10],"")+"','"+setIfNull(data[11],"")+"',"
						+ " '"+setIfNull(data[12],"")+"','"+setIfNull(data[13],"")+"','"+setIfNull(data[24],"")+"','"+setIfNull(data[15],"")+"',"
						+ " '"+setIfNull(data[25],"")+"','"+setIfNull(data[17],"")+"','"+setIfNull(data[19],"")+""+setIfNull(data[18],"")+"',"
						+ " '"+data[20]+""+data[21]+"','"+data[22]+"','"+setIfNull(data[23], " ")+"')\" ";
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
					strimg="    <a data-toggle=\"modal\" data-target=\"#primary\" class=\"user-profile text-center color-custom-demo\">\r\n" + 
							"   <img src=\"\" class=\"letterpic\" "+str+" alt='"+data[2]+"' title='"+data[2]+"'>\r\n" + 
							"   </a>";
				}	
	 			obj.put("vphoto", strimg);
				obj.put("vid", setIfNull(data[0], ""));			
				obj.put("vname", setIfNull(data[2], ""));
				obj.put("vemail", setIfNull(data[3], ""));
				obj.put("vcontactnumber",setIfNull(data[4], ""));				 
				obj.put("visitortype", setIfNull(data[7], ""));				 
				obj.put("empname",setIfNull(data[10], ""));		 

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

	@RequestMapping(value="/saveCardData",method=RequestMethod.POST,produces= {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE })
	public Map<String,Object> saveCardData(@RequestBody Visitorcard visitorcard){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
				Long id = visitorcard.getVcardid();
				//isExist Check
				String str="select vpassnumber from visitorcard where active=1 ";
				if(id != null && !StringUtils.isEmpty(String.valueOf(id)))
				str += " AND vid!= '"+id+"' ";
				if(!StringUtils.isEmpty(visitorcard.getVpassnumber()))
				str += " AND  LOWER(vpassnumber)=LOWER('"+visitorcard.getVpassnumber()+"')";
				List<?> isExist=commondao.getQueryData(str);
				if(isExist.size()>0)
				{
					map.put("DUPLICATE", "Card Number Already Exist");
					return map;
				}	
				else {
					String maxid = commondao.getMaxValue("visitorcard", "vcardid", null);
					Visitorcard vcard=new Visitorcard();
					vcard.setVcardid(Long.parseLong(maxid));
					vcard.setVpassnumber(visitorcard.getVpassnumber());
					vcard.setCompanyid(visitorcard.getCompanyid());
					vcard.setLocationid(visitorcard.getLocationid());
					vcservice.save(vcard);
					map.put("SAVE", "SUCCESSFULLY");
				} 
			}
			catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "/CheckInAndCheckOut", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Map<String,Object> CheckInAndCheckOut(@RequestParam("vid") String vid,@RequestParam("checkType") String checkType){
		Map<String, Object> map = new HashMap<String, Object>();		
		Timestamp ts=new Timestamp(System.currentTimeMillis()); 		
		
		try {
			
		    DateFormat format = new SimpleDateFormat("dd/MM/yyy hh:mm a");
			String str = format.format( ts );
			Date parsedDate = format.parse(str);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			System.out.println("str"+str);
			
			Visitor visitor=new Visitor();
			if(checkType.equals("checkin")) {
				
			  visitor=vservice.findById(Long.parseLong(vid));
			  visitor.setVid(Long.parseLong(vid));
			  visitor.setCheckedin(timestamp);			        	
			}    
			else {
				  visitor=vservice.findById(Long.parseLong(vid));
				  visitor.setVid(Long.parseLong(vid));
				  visitor.setCheckedout(timestamp);
			}
			 vservice.save(visitor);
		} catch (Exception e) {
			e.printStackTrace();
		}    
		return map;
	}
	
	
	@RequestMapping(value = "/saveData", method = { RequestMethod.POST } , consumes ={"multipart/form-data"})
	public Map<String, Object> saveData(@RequestPart("visitor") Visitor visitor,@RequestPart(name="vphoto",required=false) MultipartFile file) {
		
		Map<String, Object> map = new HashMap<String, Object>();	
		 
		try {		
			
			Long id = visitor.getVid();
			//isExist Check
			String str="select vemail from visitor where active=1 ";
			if(id != null && !StringUtils.isEmpty(String.valueOf(id)))
			str += " AND vid!= '"+id+"' ";
			if(!StringUtils.isEmpty(visitor.getVemail()))
			str += " AND  LOWER(vemail)=LOWER('"+visitor.getVemail()+"')";
			List<?> isExist=commondao.getQueryData(str);
			if(isExist.size()>0)
			{
				map.put("DUPLICATE", "Email Already Exist");
				return map;
			}	
			else {
			
			Visitor v=new Visitor();
			if (id == null || StringUtils.isEmpty(String.valueOf(id))) {
				String maxid = commondao.getMaxValue("visitor", "vid", null);
				v.setVid(Long.parseLong(maxid));
				v.setActive(1);
				v.setIpaddress(getipmac.getIpAddress());
				v.setMacaddress(getipmac.getMacAddress());
				v.setCreatedby("A");
				v.setCreatedon(new Timestamp(System.currentTimeMillis()));
			
				String filepath="";
				if(null!=file) {
					File uploadfile = FileUtility.convert(file);	
				
					filepath = FileUtility.newUploadFile(uploadfile,uploadfile.getName(),path,"visitor_"+maxid+"",context.getRealPath(""));
					v.setVphoto(filepath);
				}
				
			} else {
				
				v = vservice.findById(id);
				v.setActive(1);
				v.setIpaddress(getipmac.getIpAddress());
				v.setMacaddress(getipmac.getMacAddress());
				v.setModifiedby("A");
				v.setModifiedon(new Timestamp(System.currentTimeMillis()));
			}
			if (null != visitor.getVname() && visitor.getVname().trim().length() > 0) {
				v.setVname(visitor.getVname().trim());
			}			
			if (null != visitor.getVemail() && visitor.getVemail().trim().length() > 0) {
				v.setVemail(visitor.getVemail().trim());
			}
			if (null != visitor.getVcontactnumber() && visitor.getVcontactnumber().trim().length() > 0) {
				v.setVcontactnumber(visitor.getVcontactnumber().trim());	
			}
			if (null != visitor.getVisfromcompany() && visitor.getVisfromcompany().trim().length() > 0) {
				v.setVisfromcompany(visitor.getVisfromcompany());
			
			if (null != visitor.getVcompanyid() && visitor.getVcompanyid() > 0) {
				v.setVcompanyid(visitor.getVcompanyid());
				}
			}
			 
			if (null != visitor.getVisitortype() && visitor.getVisitortype().trim().length() > 0) {
				v.setVisitortype(visitor.getVisitortype());
			}
			else {
				v.setVisitortype("");
			}
			
			if(null!=visitor.getVlocation() && visitor.getVlocation().trim().length() > 0) {
				v.setVlocation(visitor.getVlocation());
			}
			
			if (null != visitor.getCompanyid() && visitor.getCompanyid() > 0) {
				v.setCompanyid(visitor.getCompanyid());
			}
			if (null != visitor.getLocationid() && visitor.getLocationid() > 0) {
				v.setLocationid(visitor.getLocationid());
			}
			if (null != visitor.getDepartmentid() && visitor.getDepartmentid() > 0) {
				v.setDepartmentid(visitor.getDepartmentid());
			}
			if (null != visitor.getEmployeeid() && visitor.getEmployeeid() > 0) {
				v.setEmployeeid(visitor.getEmployeeid());
			}
			if(null!=visitor.getPurpose() && visitor.getPurpose().trim().length() > 0) {
				v.setPurpose(visitor.getPurpose());
			}
			if(null!=visitor.getvpassid() && visitor.getvpassid()> 0) {
				v.setvpassid(visitor.getvpassid());
			}
			if(null!=visitor.getNoofperson() && visitor.getNoofperson() > 0) {
				v.setNoofperson(visitor.getNoofperson());
			}
			
			if(null!=visitor.getIsvehicle() && visitor.getIsvehicle().trim().length() > 0) {
				
				v.setIsvehicle(visitor.getIsvehicle());
				
				if(null!=visitor.getVehiclenumber() && visitor.getVehiclenumber().trim().length() > 0) {
					v.setVehiclenumber(visitor.getVehiclenumber());
				}
				else {
					v.setVehiclenumber("");
				}
				
			}
			
			if(null!=visitor.getIsmaterialcarried() && visitor.getIsmaterialcarried().trim().length() > 0) {
				
				v.setIsmaterialcarried(visitor.getIsmaterialcarried());

				if(null!=visitor.getMaterialdeposit() && visitor.getMaterialdeposit().trim().length() > 0) {
					v.setMaterialdeposit(visitor.getMaterialdeposit());
				}	
				else {
					v.setMaterialdeposit("");
				}
			}	
			
				vservice.save(v);
				map.put("SAVE", "SUCCESSFULLY");
			}	
		} catch (Exception e) {
			map.put("ERRORMASSAGE", "Error occured during save data please contact IT.");
			e.printStackTrace();
		} finally {
			
		}
		return map;
	}
	
	@RequestMapping("/getEmployeeData")
	public Map<String, List<?>> getEmployeeData(@RequestParam("companyid") String companyid ,@RequestParam("locationid") String locationid,@RequestParam("departmentid") String departmentid)
	{ 
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		try {
			if(null!=companyid && companyid.trim().length()>0 && null!=locationid && locationid.trim().length()>0 && null!=departmentid && departmentid.trim().length()>0)
			{
				map.put("employeeList", service.getEmployeeData(Long.parseLong(companyid), Long.parseLong(locationid),Long.parseLong(departmentid)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return map;
	}
	
	@RequestMapping("/getVisitorCompany")
	public Map<String, List<?>> getVisitorCompany(@RequestParam("avcompanyid") String avcompanyid)
	{
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		try {
			if(null!=avcompanyid && avcompanyid.trim().length()>0 ){
				
				map.put("vlocationList", service.getVisitorLocation(Long.parseLong(avcompanyid)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return map;
	}
	
	@Autowired
	EntityManagerFactory emf;	
	public List<?> getDataFromQuery(String strQuery) {
	Session session = emf.createEntityManager().unwrap(Session.class);
		try{ 
			SQLQuery query = session.createNativeQuery(strQuery);
			List<?> dataList = query.list();
			return dataList;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}