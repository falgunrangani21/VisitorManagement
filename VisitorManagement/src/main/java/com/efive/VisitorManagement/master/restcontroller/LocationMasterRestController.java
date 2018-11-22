package com.efive.VisitorManagement.master.restcontroller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
import com.efive.VisitorManagement.entity.ContactPersonMaster;
import com.efive.VisitorManagement.entity.Locationmaster;
import com.efive.VisitorManagement.entity.RequestWrapper;
import com.efive.VisitorManagement.master.bean.LocationMasterBean;
import com.efive.VisitorManagement.master.service.CommonUtilService;
import com.efive.VisitorManagement.master.service.LocationService;
import com.efive.VisitorManagement.master.service.PersonService;

@RestController
@RequestMapping(value = "/restlocationmaster")
public class LocationMasterRestController extends LocationMasterBean {
 
	@Autowired
	private CommonDao commondao;
	@Autowired
	private GetIpMac getipmac;
	@Autowired
	private EntityManagerFactory emf;
	@Autowired
	private CommonUtilService commonUtilService;
	@Autowired
	private PersonService personservice;
	@Autowired
	private LocationService locationService;


	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/searchBranchMasterByAjax")
	public String searchBranchMasterByAjax(Model model, Pageable pageable, HttpServletRequest request) {

		iDisplayLength = request.getParameter("iDisplayLength");
		iDisplayStart = request.getParameter("iDisplayStart");
		String sortcolumn = request.getParameter("iSortCol_0");
		String Direction1 = request.getParameter("sSortDir_0");
		String sSearch = request.getParameter("sSearch");
		
		if(null==sSearch.trim() &&  sSearch.trim().length()==0){
			sSearch="";
		}
		int page = Integer.parseInt(iDisplayStart) / Integer.parseInt(iDisplayLength);
		totalrecords = locationService.countBranchdata();
		JSONObject returndata = new JSONObject();
		calculateDataTableVar();

		Direction dir = null;

		if (Direction1.equals("asc")) {
			dir = Direction.ASC;
		} else {
			dir = Direction.DESC;
		}
		returndata = generateJsonArray(locationService.getBranchByQuery( new PageRequest(page,  Integer.parseInt(iDisplayLength), dir, cols[Integer.parseInt(sortcolumn)]),sSearch.trim()));
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
				obj.put("companyname", data[2].toString().toUpperCase());
				obj.put("branchname", data[1]);
				obj.put("email", data[4]);  
				obj.put("contactnumber", data[5]);
				obj.put("Address"," <a class=\"text-info\" data-toggle=\"popover\" data-content=\""+data[10]+", "+data[13]+", "+data[12]+" , "+data[11]+"\" data-trigger=\"hover\" data-original-title=\"Address\"><i class=\"fa fa-eye\" aria-hidden=\"true\"></i></a>");
				obj.put("fax", data[6]);
				obj.put("contactperson"," <a class=\"badge badge-success badge-square text-white pl-1 pr-1\" data-toggle=\"modal\" onclick=\"getPersonDetails("+ data[0] +");\" data-target=\"\">Person Details</a>");
				strQuery = "<a class=\"mr-1\" href=\"#\" onclick=\"editData('" + data[0] + "','" + data[1] + "','" + data[3] + "',"
						+ " '"+data[4]+ "','"+data[5]+ "','"+data[6]+ "','"+data[7]+ "','"+data[8]+ "','"+data[9]+"','"+data[10]+"' )\">"
						+ "<i  class=\"fa fa-edit\"></i></a>"
						+ " <a class=\"mr-1 text-danger\"  href=\"#\" onclick=\"deleteData('" + data[0]	+ "')\"><i class=\"fa fa-trash\"></i></a>";
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
	
	//save Complex Form
	@RequestMapping(value = "/saveData", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> saveData(@RequestBody RequestWrapper requestWrapper) {

		Map<String, Object> map = new HashMap<String, Object>();
		// Insert Contact Person data
		Transaction transaction = null;
		
		try(Session session = emf.createEntityManager().unwrap(Session.class);) {
			
			Long id = requestWrapper.getLocationmaster().getLocationid();

			// isExist Check
			String str = "select branchname from locationmaster where active=1 ";
			if (id != null && !StringUtils.isEmpty(String.valueOf(id)))
				str += " AND locationid!= '" + id + "' ";
			if (!StringUtils.isEmpty(requestWrapper.getLocationmaster().getBranchname()))
				str += " and companyid=" + requestWrapper.getLocationmaster().getCompanyid()
						+ "  and upper(branchname)= UPPER('" + requestWrapper.getLocationmaster().getBranchname()
						+ "')";
			System.out.println("isexist query"+str);
			List<?> isExist = commondao.getQueryData(str);
			if (isExist.size() > 0 && isExist!=null) {
				map.put("DUPLICATE", "Company Name Already Exist");
				
				return map;
			} else {

				// save code
				Locationmaster location = new Locationmaster();
				if (id == null || StringUtils.isEmpty(String.valueOf(id))) {
					String maxid = getMaxValue("locationmaster", "locationid", null,session);
					id=Long.parseLong(maxid);
					location.setLocationid(Long.parseLong(maxid));
					location.setActive(1);
					location.setIpaddress(getipmac.getIpAddress());
					location.setMacaddress(getipmac.getMacAddress());
					location.setCreatedby("A");
					location.setCreatedon(new Timestamp(System.currentTimeMillis()));
					
					if (null != requestWrapper.getLocationmaster().getBranchname()
							&& requestWrapper.getLocationmaster().getBranchname().trim().length() > 0)
						location.setBranchname(requestWrapper.getLocationmaster().getBranchname());

					if (null != requestWrapper.getLocationmaster().getCompanyid()
							&& requestWrapper.getLocationmaster().getCompanyid() > 0)
						location.setCompanyid(requestWrapper.getLocationmaster().getCompanyid());

					if (null != requestWrapper.getLocationmaster().getEmail()
							&& requestWrapper.getLocationmaster().getEmail().trim().length() > 0)
						location.setEmail(requestWrapper.getLocationmaster().getEmail());

					if (null != requestWrapper.getLocationmaster().getContactno()
							&& requestWrapper.getLocationmaster().getContactno().trim().length() > 0)
						location.setContactno(requestWrapper.getLocationmaster().getContactno());

					if (null != requestWrapper.getLocationmaster().getFax()
							&& requestWrapper.getLocationmaster().getFax().trim().length() > 0)
						location.setFax(requestWrapper.getLocationmaster().getFax());

					if (null != requestWrapper.getLocationmaster().getCountryid()
							&& requestWrapper.getLocationmaster().getCountryid() > 0)
						location.setCountryid(requestWrapper.getLocationmaster().getCountryid());

					if (null != requestWrapper.getLocationmaster().getStateid()
							&& requestWrapper.getLocationmaster().getStateid() > 0)
						location.setStateid(requestWrapper.getLocationmaster().getStateid());

					if (null != requestWrapper.getLocationmaster().getCityid()
							&& requestWrapper.getLocationmaster().getCityid() > 0)
						location.setCityid(requestWrapper.getLocationmaster().getCityid());

					if (null != requestWrapper.getLocationmaster().getAddress()
							&& requestWrapper.getLocationmaster().getAddress().trim().length() > 0)
						location.setAddress(requestWrapper.getLocationmaster().getAddress());
					session.save(location);
				} else {
					Locationmaster location1=new Locationmaster();
					location1 =  locationService.findById(id);
					System.out.println("location data"+location1.getLocationid()+""+location1.getAddress());
					location1.setActive(1);
					location1.setIpaddress(getipmac.getIpAddress());
					location1.setMacaddress(getipmac.getMacAddress());
					location1.setModifiedby("A");
					location1.setModifiedon(new Timestamp(System.currentTimeMillis()));
					
					if (null != requestWrapper.getLocationmaster().getBranchname()
							&& requestWrapper.getLocationmaster().getBranchname().trim().length() > 0)
						location1.setBranchname(requestWrapper.getLocationmaster().getBranchname());

					if (null != requestWrapper.getLocationmaster().getCompanyid()
							&& requestWrapper.getLocationmaster().getCompanyid() > 0)
						location1.setCompanyid(requestWrapper.getLocationmaster().getCompanyid());

					if (null != requestWrapper.getLocationmaster().getEmail()
							&& requestWrapper.getLocationmaster().getEmail().trim().length() > 0)
						location1.setEmail(requestWrapper.getLocationmaster().getEmail());

					if (null != requestWrapper.getLocationmaster().getContactno()
							&& requestWrapper.getLocationmaster().getContactno().trim().length() > 0)
						location1.setContactno(requestWrapper.getLocationmaster().getContactno());

					if (null != requestWrapper.getLocationmaster().getFax()
							&& requestWrapper.getLocationmaster().getFax().trim().length() > 0)
						location1.setFax(requestWrapper.getLocationmaster().getFax());

					if (null != requestWrapper.getLocationmaster().getCountryid()
							&& requestWrapper.getLocationmaster().getCountryid() > 0)
						location1.setCountryid(requestWrapper.getLocationmaster().getCountryid());

					if (null != requestWrapper.getLocationmaster().getStateid()
							&& requestWrapper.getLocationmaster().getStateid() > 0)
						location1.setStateid(requestWrapper.getLocationmaster().getStateid());

					if (null != requestWrapper.getLocationmaster().getCityid()
							&& requestWrapper.getLocationmaster().getCityid() > 0)
						location1.setCityid(requestWrapper.getLocationmaster().getCityid());

					if (null != requestWrapper.getLocationmaster().getAddress()
							&& requestWrapper.getLocationmaster().getAddress().trim().length() > 0)
						location1.setAddress(requestWrapper.getLocationmaster().getAddress());
					session.update(location1);
				}

				
				//session.flush();
				//session.close();
				for (ContactPersonMaster personmasters : requestWrapper.getPersonmaster()) {
					
					
					System.out.println("person"+personmasters.getPersonid());
					
					addPersonDetail(personmasters.getPersonid(),id, personmasters.getPersonname(),
							personmasters.getDesignation(), personmasters.getContactnumber(),session);
				}
				transaction = session.beginTransaction();
				session.flush();
				//session.close();
				transaction.commit();
				map.put("SAVE", "SUCCESSFULLY");
			}
			
			

		} catch (Exception e) {
			map.put("ERRORMASSAGE", "Error occure during save data please contact IT.");
			if(null!=transaction) {
				transaction.rollback();
			}
			
			e.printStackTrace();

		} finally {

		}
		return map;
	}

	public void addPersonDetail(Long personid, Long locationid, String personname, String designation,String contactnumber,Session session) {

		try {
			ContactPersonMaster contactperson = new ContactPersonMaster();
			if (personid != null && personid == -1 && !StringUtils.isEmpty(String.valueOf(personid))) {
				String maxid = getMaxValue("contact_person_master", "personid", null,session);
				System.out.println(maxid);
				contactperson.setPersonid(Long.parseLong(maxid));
				contactperson.setLocationid(locationid);
				contactperson.setActive(1);
				contactperson.setIpaddress(getipmac.getIpAddress());
				contactperson.setMacaddress(getipmac.getMacAddress());
				contactperson.setCreatedby("A");
				contactperson.setCreatedon(new Timestamp(System.currentTimeMillis()));
			} else {
				contactperson = personservice.findById(personid);
				System.out.println("location data"+contactperson.getLocationid()+""+contactperson.getPersonid());
				contactperson.setPersonid(personid);
				contactperson.setLocationid(locationid);
				contactperson.setIpaddress(getipmac.getIpAddress());
				contactperson.setMacaddress(getipmac.getMacAddress());
				contactperson.setModifiedby("A");
				contactperson.setModifiedon(new Timestamp(System.currentTimeMillis()));
			}
			if (null != locationid && locationid > 0)
				contactperson.setLocationid(locationid);
			if (null != personname && personname.trim().length() > 0)
				contactperson.setPersonname(personname);
			if (null != designation && designation.trim().length() > 0)
				contactperson.setDesignation(designation);
			if (null != contactnumber && contactnumber.trim().length() > 0)
				contactperson.setContactnumber(contactnumber);
				session.save(contactperson);
				//session.flush();
				//session.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/deleteData", method = { RequestMethod.POST })
	public void deleteData(@RequestParam("locationid") String locationid)
	{
		try {
			
			if(!StringUtils.isEmpty(locationid))
			{	
			Locationmaster location=new Locationmaster();
			location= locationService.findById(Long.parseLong(locationid));
			location.setActive(0);
			location.setIpaddress(getipmac.getIpAddress());
			location.setMacaddress(getipmac.getMacAddress());
			location.setModifiedby("A");
			location.setModifiedon(new Timestamp(System.currentTimeMillis()));
			locationService.save(location);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getStateData")
	public Map<String, List<?>> getStateData(@RequestParam("countryid") String countryid) {
		Map<String, List<?>> map = new HashMap<String, List<?>>();

		try {
			if (null != countryid && countryid.trim().length() > 0) {
				map.put("stateList", commonUtilService.getStateData(Long.parseLong(countryid)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@RequestMapping("/getCityData")
	public Map<String, List<?>> getCityData(@RequestParam("countryid") String countryid,@RequestParam("stateid") String stateid) {
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		try {
			if (null != countryid && countryid.trim().length() > 0 && null != stateid && stateid.trim().length() > 0) {
				map.put("cityList", commonUtilService.getCityData(Long.parseLong(stateid), Long.parseLong(countryid)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	//getPerson Details On modelOpen
	@RequestMapping("/getPersonDetails")
	public Map<String, Object> getPersonDetails(@RequestParam("locationid") String locationid)
	{
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (null != locationid && locationid.trim().length() > 0 ) {
				map.put("personList", commonUtilService.getPersonData(Long.parseLong(locationid)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	// get Max Value for Perticular Table
		public String getMaxValue(String tablename, String columnname,
				String whereClause,Session session) { // if where cluse is null then pass as -> ""
										// or null
			try {
			
				String strQuery = "SELECT  IFNULL(MAX(" + columnname
						+ ") ,0)+1 FROM " + tablename;
				if (null != whereClause && whereClause.trim().length() > 0)
					strQuery += " " + whereClause;
			//	q1 = entityManager.createNativeQuery(strQuery);
				
				List<?> q1=getDataFromQuery(strQuery,session);
				// q.executeUpdate();
				// createErrorHandlingTable();
				/* if(!isTableExist("logdetails")) */
				// createAuditLogdetailtable();
				//saveAuditLogDetails("page1", "viewpage", "ID", "1");
				// saveLog("Max Value", null, "loginStr");
				// printLog("log", null, strQuery);
				// String[] mailList={"nsefive@gmail.com","pkefive@gmail.com"};
				// MailAlerts.sendSimpleMessage(mailList, "second time test",
				// "second hand body", "efivetesting@gmail.com", "efive@12345",
				// "smtp.gmail.com", 587,"C:\\Users\\efive\\Downloads\\chart.png");
				// System.out.println("get Address======"+getIpMac.getIpAddress()+"  "+getIpMac.getMacAddress());

				return q1.get(0).toString();
			} catch (Exception e) {
				e.printStackTrace();

			}
			return "0";
		} 
		
	public List<?> getDataFromQuery(String strQuery,Session session) {
		
		try{
			//logger.error(strQuery);
			Query query = session.createNativeQuery(strQuery);
			List<?> dataList = query.getResultList();
			return dataList;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}