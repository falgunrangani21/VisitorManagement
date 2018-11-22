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
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efive.VisitorManagement.common.CommonDao;
import com.efive.VisitorManagement.common.GetIpMac;
import com.efive.VisitorManagement.entity.Citymaster;
import com.efive.VisitorManagement.master.bean.CityMasterBean;
import com.efive.VisitorManagement.master.service.CityService;
import com.efive.VisitorManagement.master.service.CommonUtilService;

@RestController
@RequestMapping(value = "/visitors/restcitymaster")
public class CityMasterRestController extends CityMasterBean {

	@Autowired
	private CityService cityservice;	
	@Autowired
	private CommonDao commondao;	
	@Autowired
	private GetIpMac getipmac;
	@Autowired
	private CommonUtilService service;

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/searchCitymasterByAjax")
	public String searchCitymasterByAjax(Model model, Pageable pageable, HttpServletRequest request) {

		iDisplayLength = request.getParameter("iDisplayLength");
		iDisplayStart = request.getParameter("iDisplayStart");
		String sortcolumn = request.getParameter("iSortCol_0");
		String Direction1 = request.getParameter("sSortDir_0");
		String sSearch = request.getParameter("sSearch");
		
		if(null==sSearch.trim() &&  sSearch.trim().length()==0){
			sSearch="";
		}
		int page = Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength);
		totalrecords = cityservice.countCitydata();
		JSONObject returndata = new JSONObject();
		calculateDataTableVar();

		Direction dir = null;

		if (Direction1.equals("asc")) {
			dir = Direction.ASC;
		} else {
			dir = Direction.DESC;
		}
		returndata = generateJsonArray(cityservice.getCityByQuery( new PageRequest(page,  Integer.parseInt(iDisplayLength), dir, cols[Integer.parseInt(sortcolumn)]),sSearch.trim()));
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
				obj.put("cityname", data[1]);
				obj.put("stdcode", setIfNull(data[2], ""));
				obj.put("pincode", setIfNull(data[3], ""));
				 
				strQuery = "<a class=\"mr-1 text-success\" href=\"#\" onclick=\"editData('" + data[0] + "','" + data[1] + "','"+ setIfNull(data[2], "")+"','"+ setIfNull(data[3], "")+"'"
						+ ",'"+data[4]+"','"+data[5]+"')\"><i  class=\"fa fa-edit\"></i></a>"
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

	@RequestMapping(value = "/saveData", method = { RequestMethod.POST } , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> saveData(@RequestBody Citymaster citymaster, HttpServletRequest req) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		
			try {
			
			Long id = citymaster.getCityid();
			//isExist Check
			String str="select cityname from citymaster where active=1 ";
			if(id != null && !StringUtils.isEmpty(String.valueOf(id)))
			str += " AND cityid!= '"+id+"' ";
			if(!StringUtils.isEmpty(citymaster.getCityname()))
			str += " AND countryid=('"+citymaster.getCountryid()+"') AND stateid=('"+citymaster.getStateid()+"') AND upper(cityname)= UPPER('"+citymaster.getCityname()+"')";
			List<?> isExist=commondao.getQueryData(str);
			if(isExist.size()>0)
			{
				map.put("DUPLICATE", "City Name Already Exist");
				return map;
			}	
			else {
			
			Citymaster city=new Citymaster();
			if (id == null || StringUtils.isEmpty(String.valueOf(id))) {
				String maxid = commondao.getMaxValue("citymaster", "cityid", null);
				city.setCityid((Long.parseLong(maxid)));
				city.setActive(1);
				city.setIpaddress(getipmac.getIpAddress());
				city.setMacaddress(getipmac.getMacAddress());
				city.setCreatedby("A");
				city.setCreatedon(new Timestamp(System.currentTimeMillis()));
				
			} else {
					
				city = cityservice.findById(id);
				city.setIpaddress(getipmac.getIpAddress());
				city.setMacaddress(getipmac.getMacAddress());
				city.setModifiedby("A");
				city.setModifiedon(new Timestamp(System.currentTimeMillis()));
			}
			if (null != citymaster.getCountryid() && citymaster.getCountryid() > 0) {
				city.setCountryid(citymaster.getCountryid());
			}
			if (null != citymaster.getStateid()	&& citymaster.getStateid() > 0) {
				city.setStateid((citymaster.getStateid()));
			}
			if (null != citymaster.getCityname() && citymaster.getCityname().trim().length() > 0) {
				city.setCityname((citymaster.getCityname()).toUpperCase().trim());
			}
			if (null != citymaster.getStdcode()	&& citymaster.getStdcode().trim().length() > 0) {
				city.setStdcode((citymaster.getStdcode()));
			}
			else {
				city.setStdcode("");
			}
			if (null != citymaster.getPincode()	&& citymaster.getPincode().trim().length() > 0) {
				city.setPincode(citymaster.getPincode());
			}
			else {
				city.setPincode("");
			}
			cityservice.save(city);
			map.put("SAVE", "SUCCESSFULLY");
			}	
			
		} catch (Exception e) {
			map.put("ERRORMASSAGE", "Error occured during save data please contact IT.");
			e.printStackTrace();
		} finally {
			
		}
		return map;
	}
	
	@RequestMapping(value = "/visitors/deleteData", method = { RequestMethod.POST })
	public void deleteData(@RequestParam("cityid") String cityid)
	{
		try {
			
			if(!StringUtils.isEmpty(cityid))
			{	
				Citymaster city=new Citymaster();
				city= cityservice.findById(Long.parseLong(cityid));
				city.setActive(0);
				city.setIpaddress(getipmac.getIpAddress());
				city.setMacaddress(getipmac.getMacAddress());
				city.setModifiedby("A");
				city.setModifiedon(new Timestamp(System.currentTimeMillis()));
				cityservice.save(city);
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
}