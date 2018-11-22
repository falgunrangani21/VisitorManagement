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
import com.efive.VisitorManagement.entity.Visitorcompanymaster;
import com.efive.VisitorManagement.master.bean.VisitorCompanyMasterBean;
import com.efive.VisitorManagement.master.service.CommonUtilService;
import com.efive.VisitorManagement.master.service.VisitorCompanyService;

@RestController
@RequestMapping(value="/restvisitorcompanymaster")
public class VisitorCompanyRestController extends VisitorCompanyMasterBean{
	
	@Autowired
	private CommonUtilService service;
	@Autowired
	private CommonDao commondao;
	@Autowired
	private VisitorCompanyService vcompanyservice;
	@Autowired
	private GetIpMac getipmac;

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/searchVisitorCompanyByAjax")
	public String searchVisitorCompanyByAjax(Model model, Pageable pageable, HttpServletRequest request) {

		iDisplayLength = request.getParameter("iDisplayLength");
		iDisplayStart = request.getParameter("iDisplayStart");
		String sortcolumn = request.getParameter("iSortCol_0");
		String Direction1 = request.getParameter("sSortDir_0");
		String sSearch = request.getParameter("sSearch");
		
		if(null==sSearch.trim() &&  sSearch.trim().length()==0){
			sSearch="";
		}
		int page = Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength);
		totalrecords = vcompanyservice.countVisitorCompanydata();
		JSONObject returndata = new JSONObject();
		calculateDataTableVar();

		Direction dir = null;

		if (Direction1.equals("asc")) {
			dir = Direction.ASC;
		} else {
			dir = Direction.DESC;
		}
		returndata = generateJsonArray(vcompanyservice.getVisitorCompanyByQuery(new PageRequest(page,  Integer.parseInt(iDisplayLength), dir, cols[Integer.parseInt(sortcolumn)]),sSearch.trim()));
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
				
				obj.put("username", data[1]);
				obj.put("email", data[2]);
				obj.put("contactnumber", data[3]);
				obj.put("countryname", data[6]);
				obj.put("statename", data[8]);
				obj.put("cityname", data[10]);
		
				strQuery = "<a class=\"mr-1 text-success\" href=\"#\" onclick=\"editData('" + data[0] + "','" + data[1] + "','"+ data[2] +"','"+ setIfNull(data[3], "")+"'"
						+ ",'"+setIfNull(data[4], "")+"','"+setIfNull(data[5], "")+"','"+setIfNull(data[7], "")+"','"+setIfNull(data[9], "")+"')\"><i  class=\"fa fa-edit\"></i></a>"
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
	public Map<String, Object> saveData(@RequestBody Visitorcompanymaster vcompanymaster, HttpServletRequest req) {
		
		Map<String, Object> map = new HashMap<String, Object>();
	
		try {
			
			Long id = vcompanymaster.getVcompanyid();
			//isExist Check
			String str="select vcname from visitorcompanymaster where active=1 ";
			if(id != null && !StringUtils.isEmpty(String.valueOf(id))) {
				str += " AND vcompanyid!= '"+id+"' ";
				
			}
			if(!StringUtils.isEmpty(vcompanymaster.getVcname()))
				str += " AND (upper(vcname)= UPPER('"+vcompanymaster.getVcname()+"') AND vccountryid= ('"+vcompanymaster.getVccountryid()+"')"
						+ " AND vcstateid=('"+vcompanymaster.getVcstateid()+"') "
						+ " AND vccityid=('"+vcompanymaster.getVccityid()+"'))";
			
			List<?> isExist=commondao.getQueryData(str);
			if(isExist.size()>0)
			{
				map.put("DUPLICATE", "Company Name Already Exist");
				return map;
			}	
			else {
			
			Visitorcompanymaster vcompany=new Visitorcompanymaster();
			if (id == null || StringUtils.isEmpty(String.valueOf(id))) {
				String maxid = commondao.getMaxValue("visitorcompanymaster", "vcompanyid", null);
				vcompany.setVcompanyid((Long.parseLong(maxid)));
				vcompany.setActive(1);
				vcompany.setIpaddress(getipmac.getIpAddress());
				vcompany.setMacaddress(getipmac.getMacAddress());
				vcompany.setCreatedby("A");
				vcompany.setCreatedon(new Timestamp(System.currentTimeMillis()));
				
			} else {
					
				vcompany = vcompanyservice.findById(id);
				vcompany.setIpaddress(getipmac.getIpAddress());
				vcompany.setMacaddress(getipmac.getMacAddress());
				vcompany.setModifiedby("A");
				vcompany.setModifiedon(new Timestamp(System.currentTimeMillis()));
			}
			if (null != vcompanymaster.getVcname() && vcompanymaster.getVcname().trim().length() > 0) {
				vcompany.setVcname((vcompanymaster.getVcname()).trim().toUpperCase());
			}
			if (null != vcompanymaster.getVcemail()	&& vcompanymaster.getVcemail().trim().length() > 0) {
				vcompany.setVcemail(vcompanymaster.getVcemail());
			}
			if (null != vcompanymaster.getVccontactno() && vcompanymaster.getVccontactno().trim().length() > 0) {
				vcompany.setVccontactno(vcompanymaster.getVccontactno());
			}
			if (null != vcompanymaster.getVcfax() && vcompanymaster.getVcfax().trim().length() > 0) {
				vcompany.setVcfax(vcompanymaster.getVcfax().trim());
			}
			else {
				vcompany.setVcfax("");
			}
			if (null != vcompanymaster.getVccountryid() && vcompanymaster.getVccountryid() > 0) {
				vcompany.setVccountryid(vcompanymaster.getVccountryid());
			}
			if (null != vcompanymaster.getVcstateid() && vcompanymaster.getVcstateid() > 0) {
				vcompany.setVcstateid(vcompanymaster.getVcstateid());
			}
			if(null!=vcompanymaster.getVccityid() && vcompanymaster.getVccityid() > 0) {
				vcompany.setVccityid(vcompanymaster.getVccityid());
			}
			vcompanyservice.save(vcompany);
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
	public void deleteData(@RequestParam("vcompanyid") String vcompanyid)
	{
		try {
			
			if(!StringUtils.isEmpty(vcompanyid))
			{	
				Visitorcompanymaster vcompanymaster=new Visitorcompanymaster();
				vcompanymaster= vcompanyservice.findById(Long.parseLong(vcompanyid));
				vcompanymaster.setActive(0);
				vcompanymaster.setIpaddress(getipmac.getIpAddress());
				vcompanymaster.setMacaddress(getipmac.getMacAddress());
				vcompanymaster.setModifiedby("A");
				vcompanymaster.setModifiedon(new Timestamp(System.currentTimeMillis()));
				vcompanyservice.save(vcompanymaster);
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