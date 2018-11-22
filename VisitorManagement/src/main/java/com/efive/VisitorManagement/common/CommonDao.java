package com.efive.VisitorManagement.common;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommonDao {
	
	@Autowired
	@PersistenceContext
	private EntityManager entityManager;
	/*
	 * @Autowired private SessionFactory factory;
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	Query q, q1, q2, q3, q4, q5, q6;

	@Autowired
	private GetIpMac getIpMac;

	// get Query Data in List
	public List<?> getQueryData(String str) {
		q = entityManager.createNativeQuery(str);
		return q.getResultList();
	}

	// get Max Value for Perticular Table
	public String getMaxValue(String tablename, String columnname,
			String whereClause) { // if where cluse is null then pass as -> ""
									// or null
		try {
		
			String strQuery = "SELECT  IFNULL(MAX(" + columnname
					+ ") ,0)+1 FROM " + tablename;
			if (null != whereClause && whereClause.trim().length() > 0)
				strQuery += " " + whereClause;
			q1 = entityManager.createNativeQuery(strQuery);
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

			return q1.getResultList().get(0).toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "0";
	}

	// execute Any Query for insert and update and delete
	public boolean executeQuery(String str) {
		try {
			jdbcTemplate.execute(str);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	// create Log Table
	public boolean createLogdetailtable() {
		try {
			String strCreatetable = "CREATE TABLE logdetails ("
					+ " txnnumber DECIMAL(20,0) NOT NULL, "
					+ " srno DECIMAL(8,0) DEFAULT NULL, "
					+ " logtype VARCHAR(32) DEFAULT NULL,"
					+ " varname VARCHAR(32) DEFAULT NULL, "
					+ " logstr VARCHAR(4000) DEFAULT NULL, "
					+ " userid VARCHAR(32) DEFAULT NULL,"
					+ " datetime DATETIME DEFAULT NULL, "
					+ " PRIMARY KEY (txnnumber) "
					+ ") ENGINE=INNODB DEFAULT CHARSET=latin1";

			jdbcTemplate.execute(strCreatetable);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// save Log Details

	public boolean saveLog(String logtype, String varName, String logStr) { // Log
																			// insert
																			// in
																			// table
		try {
			// Var Declaration
			String userid = "-1";
			long txnnumber = CommonUtils.getTxnnumberms();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			/*
			 * try{ // for get userid from session HttpSession session =
			 * request.getSession(); userid =
			 * session.getAttribute("userid").toString(); }catch(Exception e1){
			 * //e1.printStackTrace(); }
			 */

			String strQuery = "INSERT INTO logdetails(txnnumber,srno,logtype, varname,logstr,userid, datetime) "
					+ "VALUES("
					+ txnnumber
					+ ","
					+ "(SELECT IFNULL(MAX(ld.srno),0)+1  FROM logdetails ld  WHERE  txnnumber = "
					+ txnnumber
					+ " ) ,"
					+ "'"
					+ logtype
					+ "','"
					+ varName
					+ "','"
					+ logStr.replace("'", "''")
					+ "','"
					+ userid
					+ "','" + timestamp + "'" + ")";

			// Check 'logdeatils' table exist
			if (false == isTableExist("logdetails")) {
				if (true == createLogdetailtable()) {
					jdbcTemplate.execute(strQuery);
					return true;
				}
			} else {
				jdbcTemplate.execute(strQuery);
				return true;
			}

		} catch (Exception e) {
			// ErrorHandling_old.saveError(e);
			e.printStackTrace();
		}
		return false;

	}

	// for print and save log in db
	public void printLog(String logtype, String varName, String logStr) {
		System.out.println(logtype + " :  " + varName + " :=  " + logStr);
		saveLog(logtype, varName, logStr);
	}

	// check is table exist in db or not
	public boolean isTableExist(String tablename) { // This Method willl check
													// for given Table Name is
													// Exist or Not
		try {
			String strQuery = "SHOW TABLES LIKE '" + tablename.toLowerCase()
					+ "'";
			List isTableExitList = getQueryData(strQuery);
			if (null != isTableExitList && isTableExitList.size() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// create Table for Handling Error
	public boolean createErrorHandlingTable() {
		try {
			String strCreatetable = "CREATE TABLE errorhandling ( "
					+ " txnnumber decimal(20,0) NOT NULL, "
					+ " srno decimal(10,0) NOT NULL, "
					+ " type varchar(128) DEFAULT NULL,"
					+ " classname varchar(64) DEFAULT NULL, "
					+ " methodname varchar(32) DEFAULT NULL,"
					+ " lineno decimal(10,0) DEFAULT NULL, "
					+ " errormessage varchar(40000) DEFAULT NULL, "
					+ " userid varchar(32) DEFAULT NULL, "
					+ " createdatetime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
					+ " PRIMARY KEY (txnnumber,srno) "
					+ " ) ENGINE=InnoDB DEFAULT CHARSET=latin1";
			System.out.println("query=====" + strCreatetable);
			jdbcTemplate.execute(strCreatetable);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Save Error In DB
	public boolean saveError(Exception ex) {
		try {
			// Var Declaration
			String userid = "-1";
			long txnnumber = CommonUtils.getTxnnumberms();
			String type = ex.getClass().getCanonicalName();
			StackTraceElement[] stk = ex.getStackTrace();
			String className = stk[0].getClassName();
			String methodName = stk[0].getMethodName();
			int lineno = stk[0].getLineNumber();
			String errorMsg = ex.getMessage();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			/*
			 * try{ // for get userid from session //HttpSession session =
			 * request.getSession(); userid =
			 * session.getAttribute("userid").toString(); }catch(Exception e1){
			 * //e1.printStackTrace(); }
			 */

			String strQuery = "INSERT INTO errorhandling(txnnumber,srno,type, classname,methodname, lineno,errormessage,userid, createdatetime) "
					+ "VALUES("
					+ txnnumber
					+ ","
					+ "(SELECT IFNULL(MAX(eh.srno),0)+1  FROM errorhandling eh  WHERE  txnnumber = "
					+ txnnumber
					+ " ) ,"
					+ "'"
					+ type
					+ "','"
					+ className
					+ "','"
					+ methodName
					+ "',"
					+ lineno
					+ ",'"
					+ errorMsg
					+ "','" + userid + "','" + timestamp + "'" + ")";
			// Check 'logdeatils' table exist
			if (false == isTableExist("errorhandling")) {
				if (true == createErrorHandlingTable()) {
					jdbcTemplate.execute(strQuery);
					return true;
				}
			} else {
				jdbcTemplate.execute(strQuery);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// create table for Audit log

	// create Log Table
	public boolean createAuditLogdetailtable() {
		try {
			String strCreatetable = "CREATE TABLE auditlogdetails ("
					+ " txtnumber DECIMAL(20,0) NOT NULL, "
					+ " srno DECIMAL(8,0) DEFAULT NULL, "
					+ " userid VARCHAR(32) DEFAULT NULL, "
					+ " usertype VARCHAR(32) DEFAULT NULL,"
					+ " logtype VARCHAR(32) DEFAULT NULL,"
					+ " pagename VARCHAR(32) DEFAULT NULL, "
					+ " useractivity VARCHAR(128) DEFAULT NULL, "
					+ " datetime DATETIME DEFAULT NULL, "
					+ " active CHAR(1) DEFAULT NULL, "
					+ " createdby VARCHAR(32) DEFAULT NULL,"
					+ " createdon Timestamp DEFAULT CURRENT_TIMESTAMP,"
					+ " ipaddress VARCHAR(32) DEFAULT NULL,"
					+ " macaddress VARCHAR(32) DEFAULT NULL,"
					+ " PRIMARY KEY (txtnumber,srno)  "

					+ ") ENGINE=INNODB DEFAULT CHARSET=latin1";

			jdbcTemplate.execute(strCreatetable);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// add audit log in database

	public boolean saveAuditLogDetails(String pageName, String transType,
			String idName, String idVal) {
		try {
			/*
			 * session = request.getSession(true); String um_userid=
			 * session.getAttribute("useridSes").toString(); String usertype=
			 * session.getAttribute("usertypeSes").toString(); String ipaddress=
			 * session.getAttribute("ipaddressSes").toString(); String
			 * macaddress= session.getAttribute("macaddressSes").toString();
			 * String loginTxn =session.getAttribute("loginTxnSes").toString();
			 */Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			// String um_roleid="";
			/*
			 * if(null!=session.getAttribute("roleidSes")) um_roleid=
			 * session.getAttribute("roleidSes").toString();
			 */

			String loginTxn = String.valueOf(CommonUtils.getTxnnumber());
			String usertype = "";
			String um_userid = "-1";
			String ipaddress = getIpMac.getIpAddress();
			String macaddress = getIpMac.getMacAddress();

			String logStr = returnAuditLogStr(pageName, transType, idName,
					idVal);
			if (null != logStr) {

				String strQuery = "INSERT INTO auditlogdetails(txtnumber, srno, userid, usertype,  pagename, logtype,useractivity, datetime, active, createdby, createdon, ipaddress, macaddress) "
						+ " VALUES( "
						+ loginTxn
						+ ",(Select  IFNULL(max(ald.srno),0)+1 from auditlogdetails ald  where ald.txtnumber ="
						+ loginTxn
						+ "), "
						+ " "
						+ um_userid
						+ ", '"
						+ usertype
						+ "', '"
						+ pageName
						+ "', '"
						+ transType.replaceAll("'", "''")
						+ "','"
						+ logStr.replaceAll("'", "''")
						+ "',"
						+ "'"
						+ timestamp
						+ "','Y','"
						+ um_userid
						+ "',now() ,'"
						+ ipaddress
						+ "','" + macaddress + "')";

				jdbcTemplate.execute(strQuery);
				return true;
			}
		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return false;
	}

	// return Audit Log String
	public String returnAuditLogStr(String pageName, String transType,
			String idName, String idVal) { // if Multiple Id (composite) just
											// pass Main id like -> userid or
											// with comma
		try {
			String logStr = "";
			if (null != transType && transType.equalsIgnoreCase("login")) {
				logStr = "Login  : " + "Logged In Userid : " + idVal;
			} else if (null != transType
					&& transType.equalsIgnoreCase("logout")) {
				logStr = "Logged Out Userid : " + idVal;
			} else if (null != transType && transType.equalsIgnoreCase("view")
					|| null != transType
					&& transType.equalsIgnoreCase("search")) {
				logStr = pageName + " : View Records";
			} else if (null != transType && transType.equalsIgnoreCase("add")) {
				logStr = pageName + " : " + "Add new records of and Id : "
						+ idVal;
			} else if (null != transType && transType.equalsIgnoreCase("edit")
					|| transType.equalsIgnoreCase("update")) {
				logStr = pageName + " : " + "Update record and  Id : " + idVal;
			} else if (null != transType
					&& transType.equalsIgnoreCase("delete")) {
				logStr = pageName + " : " + "Delete record and  Id : " + idVal;
			} else if (null != transType
					&& transType.equalsIgnoreCase("click menu")) {
				logStr = "Clicked on " + pageName + " menu.";
			} else if (null != transType
					&& transType.equalsIgnoreCase("click tab")) {
				logStr = "Clicked on " + pageName + " tab.";
			}

			return logStr;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//insert jdbcTemplate  batch with SQL
	//for refrence this : https://www.logicbig.com/tutorials/spring-framework/spring-data-access-with-jdbc/spring-jdbc-batch-update.html
	public boolean insertBatchSQL(final String sql){
			
		jdbcTemplate.batchUpdate(new String[]{sql});
			
	return true;
	}

}
