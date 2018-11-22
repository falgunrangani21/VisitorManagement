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
import com.efive.VisitorManagement.entity.Countrymaster;
import com.efive.VisitorManagement.master.bean.CountryMasterBean;
import com.efive.VisitorManagement.master.service.CountryService;

@RestController

@RequestMapping(value="/restcountrymaster")
public class CountryMasterRestController extends CountryMasterBean{

	@Autowired
	private CountryService countryservice;	
	@Autowired
	private CommonDao commondao;	
	@Autowired
	private GetIpMac getipmac;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/searchCountryMasterByAjax")
	public String searchCountryMasterByAjax(Model model, Pageable pageable, HttpServletRequest request) {

		iDisplayLength = request.getParameter("iDisplayLength");
		iDisplayStart = request.getParameter("iDisplayStart");
		String sortcolumn = request.getParameter("iSortCol_0");
		String Direction1 = request.getParameter("sSortDir_0");
		String sSearch = request.getParameter("sSearch");
		
		if(null==sSearch.trim() &&  sSearch.trim().length()==0){
			sSearch="";
		}
		int page = Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength);
		totalrecords = countryservice.countCountrydata();
		JSONObject returndata = new JSONObject();
		calculateDataTableVar();

		Direction dir = null;

		if (Direction1.equals("asc")) {
			dir = Direction.ASC;
		} else {
			dir = Direction.DESC;
		}
		returndata = generateJsonArray(countryservice.getCountryByQuery( new PageRequest(page,  Integer.parseInt(iDisplayLength), dir, cols[Integer.parseInt(sortcolumn)]),sSearch.trim()));
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
				obj.put("countryname",setIfNull(data[1], ""));
				obj.put("isdcode", setIfNull(data[2], ""));
				
				strQuery = "<a class=\"mr-1 text-success\" href=\"#\" onclick=\"editData('" + data[0] + "','" + data[1] + "','"	+ setIfNull(data[2], "") + "')\">"
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
	public Map<String, Object> saveData(@RequestBody Countrymaster countrymaster, HttpServletRequest req) {
		
		Map<String, Object> map = new HashMap<String, Object>();
				
			try {
				
				Long id = countrymaster.getCountryid();
				String str="select countryname from countrymaster where active=1 ";
				if(id != null && !StringUtils.isEmpty(String.valueOf(id)))
				str += " AND countryid!= '"+id+"' ";
					if(!StringUtils.isEmpty(countrymaster.getCountryname()));
					str += "  and upper(countryname)= UPPER('"+countrymaster.getCountryname()+"')";
			
					List<?> isExist=commondao.getQueryData(str);
					if(isExist.size()>0)
					{
						map.put("DUPLICATE", "Country Name Already Exist");
						return map;
					}	
				else {
					
					Countrymaster country=new Countrymaster();
					if (id == null || StringUtils.isEmpty(String.valueOf(id))) {
						String maxid = commondao.getMaxValue("countrymaster", "countryid", null);
		
						country.setCountryid(Long.parseLong(maxid));
						country.setActive(1);
						country.setIpaddress(getipmac.getIpAddress());
						country.setMacaddress(getipmac.getMacAddress());
						country.setCreatedby("A");
						country.setCreatedon(new Timestamp(System.currentTimeMillis()));
					
				} else {
						
					country = countryservice.findById(id);
					country.setIpaddress(getipmac.getIpAddress());
					country.setMacaddress(getipmac.getMacAddress());
					country.setModifiedby("A");
					country.setModifiedon(new Timestamp(System.currentTimeMillis()));
				}
				if (null != countrymaster.getCountryname()	&& countrymaster.getCountryname().trim().length() > 0) {
					country.setCountryname(countrymaster.getCountryname().toUpperCase().trim());
				}
				if (null != countrymaster.getIsdcode()	&& countrymaster.getIsdcode().trim().length() > 0) {
					country.setIsdcode(countrymaster.getIsdcode().trim());
				}
				else{
					 country.setIsdcode("");
				}
				countryservice.save(country);
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
	public void deleteData(@RequestParam("countryid") String countryid)
	{
		try {
			
			if(!StringUtils.isEmpty(countryid))
			{	
				Countrymaster country=new Countrymaster();
				country= countryservice.findById(Long.parseLong(countryid));
				country.setActive(0);
				country.setIpaddress(getipmac.getIpAddress());
				country.setMacaddress(getipmac.getMacAddress());
				country.setModifiedby("A");
				country.setModifiedon(new Timestamp(System.currentTimeMillis()));
				countryservice.save(country);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
