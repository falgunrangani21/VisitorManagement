package com.efive.VisitorManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Visitorcard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "visitorcard", catalog = "visitormanagement")

public class Visitorcard implements java.io.Serializable {

	// Fields
	
		private Long vcardid;
		private Long companyid;
		private Long locationid;
		private String vpassnumber;

		// Constructors

		/** default constructor */
		public Visitorcard() {
		}

		/** minimal constructor */
		public Visitorcard(Long vcardid) {
			this.vcardid = vcardid;
		}

		/** full constructor */
		public Visitorcard(Long vcardid, Long companyid, Long locationid,
				String vpassnumber) {
			this.vcardid = vcardid;
			this.companyid = companyid;
			this.locationid = locationid;
			this.vpassnumber = vpassnumber;
		}

		// Property accessors
		@Id
		@Column(name = "vcardid", unique = true, nullable = false, precision = 8, scale = 0)
		public Long getVcardid() {
			return this.vcardid;
		}

		public void setVcardid(Long vcardid) {
			this.vcardid = vcardid;
		}

		@Column(name = "companyid", precision = 8, scale = 0)
		public Long getCompanyid() {
			return this.companyid;
		}

		public void setCompanyid(Long companyid) {
			this.companyid = companyid;
		}

		@Column(name = "locationid", precision = 8, scale = 0)
		public Long getLocationid() {
			return this.locationid;
		}

		public void setLocationid(Long locationid) {
			this.locationid = locationid;
		}

		@Column(name = "vpassnumber", length = 64)
		public String getVpassnumber() {
			return this.vpassnumber;
		}

		public void setVpassnumber(String vpassnumber) {
			this.vpassnumber = vpassnumber;
		}
}
