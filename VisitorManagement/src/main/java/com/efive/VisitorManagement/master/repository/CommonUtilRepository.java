package com.efive.VisitorManagement.master.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.common.CommonDao;

@Repository
public class CommonUtilRepository {

	@Autowired
	private CommonDao commondao;

	public List<?> getCountryData(Long id) {
		String str = "SELECT countryid,countryname FROM countrymaster WHERE active=1";
		if (id != null)
			str += " and countryid=" + id + "";
		return commondao.getQueryData(str);
	}

	public List<?> getStateData(Long id) {
		String str = "SELECT stateid,statename FROM statemaster WHERE active=1";
		if (id != null)
			str += " and countryid=" + id + "";
		return commondao.getQueryData(str);
	}

	public List<?> getCityData(Long stateid, Long countryid) {
		String str = "SELECT cityid,cityname FROM citymaster WHERE active=1";
		if (stateid != null && countryid != null)
			str += " and stateid=" + stateid + " and countryid=" + countryid + "";
		return commondao.getQueryData(str);
	}

	public List<?> getCompanyData(Long id) {
		String str = "SELECT companyid,cname FROM companymaster WHERE active =1";
		if (id != null)
			str += " and companyid=" + id + "";
		return commondao.getQueryData(str);
	}

	public List<?> getPersonData(Long locationid) {
		String str = "SELECT p.personid,p.personname,p.designation,p.contactnumber,p.active FROM contact_person_master AS p WHERE active =1";

		if (locationid != null)
			str += " and locationid=" + locationid + "";
		return commondao.getQueryData(str);
	}

	public List<?> getLocationData(Long companyid) {
		String str = "SELECT locationid,branchname FROM locationmaster WHERE active=1";
		if (companyid != null) {
			str += " and companyid=" + companyid + "";
		}
		return commondao.getQueryData(str);
	}

	public List<?> getDepartmentData(Long companyid, Long locationid) {
		String str = " SELECT departmentid,deptname FROM departmentmaster WHERE active=1";
		if (companyid != null && locationid != null) {
			str += " and companyid=" + companyid + " and locationid=" + locationid + "";
		}
		return commondao.getQueryData(str);
	}

	public List<?> getEmployeeData(Long companyid, Long locationid, Long departmentid) {
		String str = "SELECT employeeid,empname FROM employeemaster WHERE active=1";

		if (companyid != null && locationid != null && departmentid != null) {
			str += " AND companyid= '" + companyid + "' AND locationid= '" + locationid + "' AND departmentid = '"
					+ departmentid + "'";
		}
		return commondao.getQueryData(str);
	}

	public List<?> getVisitorCompanyData(Long vcompanyid) {
		String str = "SELECT vcompanyid,vcname FROM visitorcompanymaster WHERE active=1";
		return commondao.getQueryData(str);
	}

	public List<?> getVisitorLocation(Long vcompanyid) {
		String str = "SELECT vid,vlocation,vname,visitortype FROM visitor WHERE active=1"; 
		if (vcompanyid != null) {
			str += " AND vcompanyid= '" + vcompanyid + "'";
		}
		return commondao.getQueryData(str);
	}

	public List<?> getVisitorCard(Long companyid) {
		String str = "SELECT vcardid,vpassnumber,companyid,locationid FROM visitorcard ";
		if (companyid != null) {
			str += "  WHERE companyid= '" + companyid + "'";
		}
		return commondao.getQueryData(str);
	}

	public List<?> getCurrentVisitorByDate() { 
		String str = "SELECT v.vid,v.vlocation,v.vname,v.visitortype,v.vphoto,v.vemail,v.vcontactnumber,c.vpassnumber,"
				+ " SUBSTRING(checkedin, 1,10) AS checkedinDate,SUBSTRING(checkedin,12,8) AS checkedinTime,"
				+ " SUBSTRING(checkedout, 1,10) AS checkedoutDate,SUBSTRING(checkedout,12,8) AS checkedoutTime,"
				+ " vc.vcname,e.empname,v.createdon FROM visitor AS v,visitorcompanymaster AS vc,employeemaster AS e, visitorcard AS c"
				+ " WHERE v.active=1 AND v.vcompanyid=vc.vcompanyid AND v.employeeid=e.employeeid AND v.vpassid=c.vcardid "
				+ " AND DATE_FORMAT(v.createdon,'%Y-%m-%d') = DATE_FORMAT(CURDATE(),'%Y-%m-%d') ORDER BY v.vid DESC";
		return commondao.getQueryData(str);
	}
}