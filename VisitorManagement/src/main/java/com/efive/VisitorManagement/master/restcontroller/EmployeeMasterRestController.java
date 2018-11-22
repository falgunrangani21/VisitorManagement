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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
import com.efive.VisitorManagement.entity.Employeemaster;
import com.efive.VisitorManagement.master.bean.EmployeeMasterBean;
import com.efive.VisitorManagement.master.service.CommonUtilService;
import com.efive.VisitorManagement.master.service.EmployeeService;

@RestController
@RequestMapping(value="/restemployeemaster")
public class EmployeeMasterRestController extends EmployeeMasterBean{

	String path="ATTACHMENT/uploadimage/employeeimages";
	@Autowired
	private CommonUtilService service;
	@Autowired
	private CommonDao commondao;
	@Autowired
	private GetIpMac getipmac;
    
	@Autowired
	EmployeeService eservice;
	
	@Autowired 
	ServletContext context;
	@Autowired
	HttpServletRequest request;
	
	@Value("${visitor.serverpath}")
	String serverpath;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/searchEmployeemasterByAjax")
	public String searchUsermasterByAjax(Model model, Pageable pageable, HttpServletRequest request) {

		iDisplayLength = request.getParameter("iDisplayLength");
		iDisplayStart = request.getParameter("iDisplayStart");
		String sortcolumn = request.getParameter("iSortCol_0");
		String Direction1 = request.getParameter("sSortDir_0");
		String sSearch = request.getParameter("sSearch");
		
		if(null==sSearch.trim() &&  sSearch.trim().length()==0){
			sSearch="";
		}
		int page = Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength);
		totalrecords = eservice.countEmployeedata();
		JSONObject returndata = new JSONObject();
		calculateDataTableVar();

		Direction dir = null;

		if (Direction1.equals("asc")) {
			dir = Direction.ASC;
		} else {
			dir = Direction.DESC;
		}
		returndata = generateJsonArray(eservice.getEmployeeByQuery( new PageRequest(page,  Integer.parseInt(iDisplayLength), dir, cols[Integer.parseInt(sortcolumn)]),sSearch.trim()));
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
	 
				String str= " onclick=\"showUserData('"+setIfNull(filePath,"")+"','"+data[0]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[6]+"','"+data[11]+"','"+data[12]+"','"+data[7]+"','"+data[13]+"','"+data[8]+"')\"";
		
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
				obj.put("empname", data[2]);
				obj.put("emp_email", data[3]);
				obj.put("empcontactnumber", data[4]);
				obj.put("empqualification", data[7]);
				obj.put("designation", data[8]);
				obj.put("cname", data[11]);
				obj.put("branchname", data[12].toString().toUpperCase());
				obj.put("deptname", data[13].toString().toUpperCase());
				strQuery = "<a class=\"mr-1 text-success\" href=\"#\" onclick=\"editData('"+data[0]+"','" + data[14] + "','" + data[9] + "','" + data[2] + "','" + data[6] + "','" + data[3] + "','" + data[4] + "'"
						+ ",'"+setIfNull(filePath,"")+"','" + data[7] + "','" + data[10] + "','"+data[8]+"')\"><i  class=\"fa fa-edit\"></i></a> "
						+ "<a class=\"text-danger mr-1\"  onclick=\"deleteData('" + data[0]	+ "')\"><i class=\"fa fa-trash\"></i>";
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
	
	@RequestMapping(value = "/saveData", method = { RequestMethod.POST },consumes = {"multipart/form-data"})
	public Map<String, Object> saveData(@RequestPart("employeemaster") Employeemaster employeemaster,@RequestPart(name="empphoto",required=false) MultipartFile file) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			Long id = employeemaster.getEmployeeid();
			//isExist Check
			String str="select emp_email from employeemaster where active=1 ";
			if(id != null && !StringUtils.isEmpty(String.valueOf(id)))
			str += " AND employeeid!= '"+id+"' ";
			if(!StringUtils.isEmpty(employeemaster.getEmpEmail()))
			str += " AND emp_email=('"+employeemaster.getEmpEmail()+"')";
			List<?> isExist=commondao.getQueryData(str);
			if(isExist.size()>0)
			{
				map.put("DUPLICATE", "Employee Already Exist");
				return map;
			}	
			else {
			
				Employeemaster employee=new Employeemaster();
			if (id == null || StringUtils.isEmpty(String.valueOf(id))) {
				String maxid = commondao.getMaxValue("employeemaster", "employeeid", null);
				employee.setEmployeeid(Long.parseLong(maxid));
				employee.setActive(1);
				employee.setIpaddress(getipmac.getIpAddress());
				employee.setMacaddress(getipmac.getMacAddress());
				employee.setCreatedby("A");
				employee.setCreatedon(new Timestamp(System.currentTimeMillis()));
				String filepath="";
				if(null!=file) {
					File uploadfile = FileUtility.convert(file);
					filepath = FileUtility.newUploadFile(uploadfile,uploadfile.getName(),path,"employee_"+maxid,context.getRealPath(""));
					employee.setEmpphoto(filepath);
				}
			} else {
				
				employee = eservice.findById(id);
				employee.setActive(1);
				employee.setIpaddress(getipmac.getIpAddress());
				employee.setMacaddress(getipmac.getMacAddress());
				employee.setModifiedby("A");
				employee.setModifiedon(new Timestamp(System.currentTimeMillis()));
				String filepath="";
				if(null!=file) {
					File uploadfile = FileUtility.convert(file);
					filepath = FileUtility.newUploadFile(uploadfile,uploadfile.getName(),path,"employee_"+employeemaster.getEmployeeid(),context.getRealPath(""));
					employee.setEmpphoto(filepath);
				}
			}
			if (null != employeemaster.getCompanyid() && employeemaster.getCompanyid() > 0) {
				employee.setCompanyid(employeemaster.getCompanyid());
			}
			if (null != employeemaster.getLocationid() && employeemaster.getLocationid() > 0) {
				employee.setLocationid(employeemaster.getLocationid());
			}			
			if (null != employeemaster.getEmpname() && employeemaster.getEmpname().trim().length() > 0) {
				employee.setEmpname(employeemaster.getEmpname());
			}
			if (null != employeemaster.getEmpgender() && employeemaster.getEmpgender().trim().length() > 0) {
				employee.setEmpgender(employeemaster.getEmpgender());
			}	
			if (null != employeemaster.getEmpEmail() && employeemaster.getEmpEmail().trim().length() > 0) {
				employee.setEmpEmail(employeemaster.getEmpEmail());
			}					
			if (null != employeemaster.getEmpcontactnumber() && employeemaster.getEmpcontactnumber().trim().length() > 0) {
				employee.setEmpcontactnumber(employeemaster.getEmpcontactnumber());
			}
			if (null != employeemaster.getEmpqualification() && employeemaster.getEmpqualification().trim().length() > 0) {
				employee.setEmpqualification(employeemaster.getEmpqualification());
			}
			if (null != employeemaster.getDepartmentid() && employeemaster.getDepartmentid() > 0) {
				employee.setDepartmentid(employeemaster.getDepartmentid());
			}
			if (null != employeemaster.getDesignation() && employeemaster.getDesignation().trim().length() > 0) {
				employee.setDesignation(employeemaster.getDesignation());
			}
			eservice.save(employee);
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
	public void deleteData(@RequestParam("employeeid") String employeeid)
	{
		try {
			
			if(!StringUtils.isEmpty(employeeid))
			{	
			Employeemaster employee=new Employeemaster();
			employee= eservice.findById(Long.parseLong(employeeid));
			employee.setActive(0);
			employee.setIpaddress(getipmac.getIpAddress());
			employee.setMacaddress(getipmac.getMacAddress());
			employee.setModifiedby("A");
			employee.setModifiedon(new Timestamp(System.currentTimeMillis()));
			eservice.save(employee);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getLocationData")
	public Map<String, List<?>> getLocationData(@RequestParam("companyid") String companyid) {
		
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		try {
			
			if(null!=companyid && companyid.trim().length()>0 && !companyid.isEmpty()) {
				map.put("locationList", service.getLocationData(Long.parseLong(companyid)));
				map.put("vcardList", service.getVisitorCard(Long.parseLong(companyid)));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("/getDepartmentData")
	public Map<String, List<?>> getDepartmentData(@RequestParam("companyid") String companyid ,@RequestParam("locationid") String locationid)
	{
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		try {
			if(null!=companyid && companyid.trim().length()>0 && null!=locationid && locationid.trim().length()>0)
			{
				map.put("departmentList", service.getDepartmentData(Long.parseLong(companyid), Long.parseLong(locationid)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return map;
	}
}
