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
import com.efive.VisitorManagement.entity.Statemaster;
import com.efive.VisitorManagement.master.bean.StateMasterBean;
import com.efive.VisitorManagement.master.service.StateService;

@RestController
@RequestMapping(value="/reststatemaster")
public class StateMasterRestController extends StateMasterBean{

	@Autowired
	private StateService stateservice;	
	@Autowired
	private CommonDao commondao;	
	@Autowired
	private GetIpMac getipmac;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/searchStateMasterByAjax")
	public String searchStateMasterByAjax(Model model, Pageable pageable, HttpServletRequest request) {

		iDisplayLength = request.getParameter("iDisplayLength");
		iDisplayStart = request.getParameter("iDisplayStart");
		String sortcolumn = request.getParameter("iSortCol_0");
		String Direction1 = request.getParameter("sSortDir_0");
		String sSearch = request.getParameter("sSearch");
		
		if(null==sSearch.trim() &&  sSearch.trim().length()==0){
			sSearch="";
		}
		
		int page = Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength);
		totalrecords = stateservice.countStatedata();
		JSONObject returndata = new JSONObject();
		calculateDataTableVar();

		Direction dir = null;

		if (Direction1.equals("asc")) {
			dir = Direction.ASC;
		} else {
			dir = Direction.DESC;
		}
		returndata = generateJsonArray(stateservice.getStateByQuery( new PageRequest(page,  Integer.parseInt(iDisplayLength), dir, cols[Integer.parseInt(sortcolumn)]),sSearch.trim()));
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
				obj.put("statename",data[1]);
				
				strQuery = "<a class=\"mr-1 text-success\" href=\"#\" onclick=\"editData('" + data[0] + "','" + data[1] + "','" + data[2] + "')\">"
						+ "<i  class=\"fa fa-edit\"></i></a><a class=\"text-danger mr-1\"  href=\"#\" onclick=\"deleteData('" + data[0]	+ "')\">"
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
	
	@RequestMapping(value = "/saveData", method = { RequestMethod.POST } , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> saveData(@RequestBody Statemaster statemaster, HttpServletRequest req) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		
			try {
			
			Long id = statemaster.getStateid();
			//isExist Check
			String str="select statename from statemaster where active=1 ";
			if(id != null && !StringUtils.isEmpty(String.valueOf(id)))
			str += " AND stateid!= '"+id+"' ";
			if(!StringUtils.isEmpty(statemaster.getStatename()))
			str += "  AND countryid='"+(statemaster.getCountryid())+"' AND  upper(statename)= UPPER('"+statemaster.getStatename()+"')";
			List<?> isExist=commondao.getQueryData(str);
			if(isExist.size()>0)
			{
				map.put("DUPLICATE", "State Name Already Exist");
				return map;
			}	
			else {
			Statemaster smaster=new Statemaster();
			if (id == null || StringUtils.isEmpty(String.valueOf(id))) {
				String maxid = commondao.getMaxValue("statemaster", "stateid", null);

				smaster.setStateid((Long.parseLong(maxid)));
				smaster.setActive(1);
				smaster.setIpaddress(getipmac.getIpAddress());
				smaster.setMacaddress(getipmac.getMacAddress());
				smaster.setCreatedby("A");
				smaster.setCreatedon(new Timestamp(System.currentTimeMillis()));
				
			} else {
					
				 smaster = stateservice.findById(id);
				 smaster.setIpaddress(getipmac.getIpAddress());
				 smaster.setMacaddress(getipmac.getMacAddress());
				 smaster.setModifiedby("A");
				 smaster.setModifiedon(new Timestamp(System.currentTimeMillis()));
			}
			if (null != statemaster.getCountryid() && statemaster.getCountryid() > 0) {
				smaster.setCountryid(statemaster.getCountryid());
			}
			if (null != statemaster.getStatename()	&& statemaster.getStatename().trim().length() > 0) {
				smaster.setStatename((statemaster.getStatename()).toUpperCase().trim());
			}
			
			stateservice.save(smaster);
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
	public void deleteData(@RequestParam("stateid") String stateid)
	{
		try {
			
			if(!StringUtils.isEmpty(stateid))
			{	
				Statemaster smaster=new Statemaster();
				smaster= stateservice.findById(Long.parseLong(stateid));
				smaster.setActive(0);
				smaster.setIpaddress(getipmac.getIpAddress());
				smaster.setMacaddress(getipmac.getMacAddress());
				smaster.setModifiedby("A");
				smaster.setModifiedon(new Timestamp(System.currentTimeMillis()));
				stateservice.save(smaster);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}