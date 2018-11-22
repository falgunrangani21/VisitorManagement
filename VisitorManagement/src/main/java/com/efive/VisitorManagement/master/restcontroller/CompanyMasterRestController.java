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
import com.efive.VisitorManagement.entity.Companymaster;
import com.efive.VisitorManagement.master.bean.CompanyMasterBean;
import com.efive.VisitorManagement.master.service.CompanyService;

@RestController
@RequestMapping("/restcompanymaster")
public class CompanyMasterRestController extends CompanyMasterBean{

	@Autowired
	private CommonDao commondao;
	@Autowired
	private GetIpMac getipmac;
	@Autowired
	private CompanyService companyservice;

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/searchCompanyMasterByAjax")
	public String searchCompanyMasterByAjax(Model model, Pageable pageable,HttpServletRequest request) {

		iDisplayLength = request.getParameter("iDisplayLength");
		iDisplayStart = request.getParameter("iDisplayStart");
		String sortcolumn = request.getParameter("iSortCol_0");
		String Direction1 = request.getParameter("sSortDir_0");
		String sSearch = request.getParameter("sSearch");
		
		if(null==sSearch.trim() &&  sSearch.trim().length()==0){
			sSearch="";
		}
		int page = Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength);
		totalrecords = companyservice.countCompanydata();
		JSONObject returndata = new JSONObject();
		calculateDataTableVar();

		Direction dir = null;

		if (Direction1.equals("asc")) {
			dir = Direction.ASC;
		} else {
			dir = Direction.DESC;
		}
		returndata = generateJsonArray(companyservice.getCompanyByQuery( new PageRequest(page,  Integer.parseInt(iDisplayLength), dir, cols[Integer.parseInt(sortcolumn)]),sSearch.trim()));
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
				obj.put("companyname", setIfNull(data[1], ""));
				obj.put("email", setIfNull(data[2], ""));
				obj.put("contactnumber", setIfNull(data[4], ""));
				obj.put("workDetails", setIfNull(data[5], ""));
				obj.put("branches", setIfNull(data[7], ""));
				obj.put("website", setIfNull(data[8], ""));

				strQuery = "<a class=\"mr-1 text-success\" href=\"#\" onclick=\"editData('" + data[0] + "','" + data[1] + "','"+ data[2] +"','"+ setIfNull(data[3], "")+"'"
						+ ",'"+setIfNull(data[4], "")+"','"+data[5]+"','"+setIfNull(data[6], "")+"','"+setIfNull(data[7], "")+"','"+setIfNull(data[8], "")+"')\"><i  class=\"fa fa-edit\"></i></a>"
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

	@RequestMapping(value = "/saveData", method = { RequestMethod.POST })
	public Map<String, Object> saveData(@RequestBody Companymaster companymaster, HttpServletRequest req) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
			try {
			
			Long id = companymaster.getCompanyid();
			//isExist Check
				String str="select cname from companymaster where active=1 ";
				if(id != null && !StringUtils.isEmpty(String.valueOf(id)))
					str += " AND companyid!= '"+id+"' ";
				if(!StringUtils.isEmpty(companymaster.getCname()))
					str += "  and upper(cname)= UPPER('"+companymaster.getCname()+"')";
			
				List<?> isExist=commondao.getQueryData(str);
				if(isExist.size()>0)
				{
					map.put("DUPLICATE", "Company Name Already Exist");
					return map;
				}	
				else
				{	
			
					Companymaster cmaster=new Companymaster();
					if (id == null || StringUtils.isEmpty(String.valueOf(id))) {
						String maxid = commondao.getMaxValue("companymaster", "companyid", null);
		
						cmaster.setCompanyid(Long.parseLong(maxid));
						cmaster.setActive(1);
						cmaster.setIpaddress(getipmac.getIpAddress());
						cmaster.setMacaddress(getipmac.getMacAddress());
						cmaster.setCreatedby("A");
						cmaster.setCreatedon(new Timestamp(System.currentTimeMillis()));
						
					} else {
							
						 cmaster = companyservice.findById(id);
						 cmaster.setIpaddress(getipmac.getIpAddress());
						 cmaster.setMacaddress(getipmac.getMacAddress());
						 cmaster.setModifiedby("A");
						 cmaster.setModifiedon(new Timestamp(System.currentTimeMillis()));
					}
					if (null != companymaster.getCname() && companymaster.getCname().trim().length() > 0) {
						cmaster.setCname(companymaster.getCname().trim().toUpperCase());
					}
					if (null != companymaster.getCemail()	&& companymaster.getCemail().trim().length() > 0) {
						cmaster.setCemail(companymaster.getCemail().trim());
					}
					if (null != companymaster.getCfax() && companymaster.getCfax().trim().length() > 0) {
						cmaster.setCfax(companymaster.getCfax().trim());
					}
					else {
						cmaster.setCfax("");
					}
					if (null != companymaster.getCnoOfbranches() && companymaster.getCnoOfbranches() > 0) {
						cmaster.setCnoOfbranches(companymaster.getCnoOfbranches());
					}
					else {
						cmaster.setCnoOfbranches(companymaster.getCnoOfbranches());
					}
					if (null != companymaster.getCnoOfemployee() && companymaster.getCnoOfemployee() > 0) {
						cmaster.setCnoOfemployee(companymaster.getCnoOfemployee());
					}
					else {
						cmaster.setCnoOfemployee(companymaster.getCnoOfemployee());
					}
					if (null != companymaster.getCtelephonenumber() && companymaster.getCtelephonenumber().trim().length() > 0) {
						cmaster.setCtelephonenumber(companymaster.getCtelephonenumber().trim());
					}
					if (null != companymaster.getCwebsite() && companymaster.getCwebsite().trim().length() > 0) {
						cmaster.setCwebsite(companymaster.getCwebsite().trim());
					}
					if (null != companymaster.getWorkDetails() && companymaster.getWorkDetails().trim().length() > 0) {
						cmaster.setWorkDetails(companymaster.getWorkDetails().trim());
					}
				
					companyservice.save(cmaster);
					map.put("SAVE", "SUCCESSFULLY");
				}	
		} catch (Exception e) {
			map.put("ERRORMASSAGE", "Error occure during save data please contact IT.");
			e.printStackTrace();
		} finally {
			
		}
		return map;
	}
	
	@RequestMapping(value = "/deleteData", method = { RequestMethod.POST })
	public void deleteData(@RequestParam("companyid") String companyid)
	{
		try {
			
			if(!StringUtils.isEmpty(companyid))
			{	
			Companymaster cmaster=new Companymaster();
			cmaster= companyservice.findById(Long.parseLong(companyid));
			cmaster.setActive(0);
			cmaster.setIpaddress(getipmac.getIpAddress());
			cmaster.setMacaddress(getipmac.getMacAddress());
			cmaster.setModifiedby("A");
			cmaster.setModifiedon(new Timestamp(System.currentTimeMillis()));
			companyservice.save(cmaster);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}