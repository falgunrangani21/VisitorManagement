package com.efive.VisitorManagement.master.restcontroller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efive.VisitorManagement.common.CommonDao;
import com.efive.VisitorManagement.common.GetIpMac;
import com.efive.VisitorManagement.entity.Departmentmaster;
import com.efive.VisitorManagement.master.bean.DepartmentMasterBean;
import com.efive.VisitorManagement.master.service.CommonUtilService;
import com.efive.VisitorManagement.master.service.DepartmentService;

@RestController
@RequestMapping("/restdepartmentmaster")
public class DepartmentMasterRestController extends DepartmentMasterBean{
	
	@Autowired
	private CommonUtilService service; 
	@Autowired
	private CommonDao commondao;
	@Autowired
	private GetIpMac getipmac;
	@Autowired
	private DepartmentService dservice;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/searchDepartmentmasterByAjax")
	public String searchDepartmentmasterByAjax(Model model, Pageable pageable, HttpServletRequest request) {

		iDisplayLength = request.getParameter("iDisplayLength");
		iDisplayStart = request.getParameter("iDisplayStart");
		String sortcolumn = request.getParameter("iSortCol_0");
		String Direction1 = request.getParameter("sSortDir_0");
		String sSearch = request.getParameter("sSearch");
		
		if(null==sSearch.trim() &&  sSearch.trim().length()==0){
			sSearch="";
		}
		int page = Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength);
		totalrecords = dservice.countDepartmentdata();
		JSONObject returndata = new JSONObject();
		calculateDataTableVar();

		Direction dir = null;

		if (Direction1.equals("asc")) {
			dir = Direction.ASC;
		} else {
			dir = Direction.DESC;
		}
		returndata = generateJsonArray(dservice.getDepartmentByQuery( new PageRequest(page,  Integer.parseInt(iDisplayLength), dir, cols[Integer.parseInt(sortcolumn)]),sSearch.trim()));
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
				obj.put("deptname", data[1].toString().toUpperCase());
				obj.put("companyname", data[3]);
				obj.put("branchname", data[5]);
				 
				strQuery = "<a class=\"mr-1 text-success\" href=\"#\" onclick=\"editData('" + data[0] + "','" + data[1] + "','"	+data[2]+ "','"+data[4]+"')\">"
						+ "<i  class=\"fa fa-edit\"></i>"+ "</a><a class=\"text-danger mr-1\"  href=\"#\" onclick=\"deleteData('" + data[0] + "')\">"
						+ "<i class=\"fa fa-trash\"></i>";
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

	@RequestMapping(value = "/saveData", method = { RequestMethod.POST })
	public Map<String, Object> saveData(@RequestBody Departmentmaster departmentmaster, HttpServletRequest req) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
			try {
			
			Long id = departmentmaster.getDepartmentid();
			
			String str="SELECT deptname from departmentmaster where active=1 ";
			if(id != null && !StringUtils.isEmpty(String.valueOf(id)))
			str += " AND departmentid!= '"+id+"' ";
			if(!StringUtils.isEmpty(departmentmaster.getDeptname()))
			str += " AND UPPER(companyid)=UPPER('"+departmentmaster.getCompanyid()+"') AND UPPER(locationid)=UPPER('"+departmentmaster.getLocationid()+"') AND UPPER(deptname)= UPPER('"+departmentmaster.getDeptname()+"')";
			List<?> isExist=commondao.getQueryData(str);
			if(isExist.size()>0)
			{
				map.put("DUPLICATE", "Department Name Already Exist");
				return map;
			}	
			else {
			
			Departmentmaster department=new Departmentmaster();
			if (id == null || StringUtils.isEmpty(String.valueOf(id))) {
				String maxid = commondao.getMaxValue("departmentmaster", "departmentid", null);
				department.setDepartmentid((Long.parseLong(maxid)));
				department.setActive(1);
				department.setIpaddress(getipmac.getIpAddress());
				department.setMacaddress(getipmac.getMacAddress());
				department.setCreatedby("A");
				department.setCreatedon(new Timestamp(System.currentTimeMillis()));
				
			} else {
					
				department = dservice.findById(id);
				department.setIpaddress(getipmac.getIpAddress());
				department.setMacaddress(getipmac.getMacAddress());
				department.setModifiedby("A");
				department.setModifiedon(new Timestamp(System.currentTimeMillis()));
			}
			if (null != departmentmaster.getDeptname()	&& departmentmaster.getDeptname().trim().length() > 0) {
				department.setDeptname((departmentmaster.getDeptname()).trim());
			}
			if (null != departmentmaster.getCompanyid() && departmentmaster.getCompanyid() > 0) {
				department.setCompanyid(departmentmaster.getCompanyid());
			}
			if (null != departmentmaster.getLocationid() && departmentmaster.getLocationid() > 0) {
				department.setLocationid(departmentmaster.getCompanyid());
			}
	
			dservice.save(department);
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
	public void deleteData(@RequestParam("departmentid") String departmentid)
	{
		try {
			
			if(!StringUtils.isEmpty(departmentid))
			{	
				Departmentmaster department=new Departmentmaster();
				department= dservice.findById(Long.parseLong(departmentid));
				department.setActive(0);
				department.setIpaddress(getipmac.getIpAddress());
				department.setMacaddress(getipmac.getMacAddress());
				department.setModifiedby("A");
				department.setModifiedon(new Timestamp(System.currentTimeMillis()));
				dservice.save(department);
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
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
