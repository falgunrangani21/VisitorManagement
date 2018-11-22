<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class='main-menu menu-fixed menu-light menu-accordion menu-shadow' data-scroll-to-active='true'>
<div class='main-menu-content'>
<ul class='navigation navigation-main' id='main-menu-navigation' data-menu='menu-navigation'>
       
<c:if test="#session.menuList.size() > 0">
	<c:forEach items="${sessionScope['menuList']}" var="main" varStatus="status">
		<c:choose> 
			<c:when test="${main[6] eq 0}">	 
				<li class='nav-item' id="${main[5]}">
					<a href="${main[2]}">
						<i class='fa fa-cog'></i>
						<span class='menu-title' data-i18n='nav.page_layouts.main'>${main[1]}</span>
					</a>
				</li> 
			</c:when>
			<c:otherwise>
					<li class="nav-item" id="${main[5]}">
						<a href="#"><i class="${main[8]}"></i>
							<span class="menu-title" data-i18n="nav.page_layouts.main">${main[1]}</span></a>
							<c:if test="#session.optionList.size() > 0">
							 	<ul class="menu-content">
									<c:forEach items="${sessionScope['optionList']}" var="sub" varStatus="status">
										<c:if test="${main[0]==sub[4]}">
											 <li id="${sub[5]}">
											 	<a  href="${sub[2]}"  class="menu-item" data-i18n="nav.page_layouts.1_column">${sub[1]}</a>
		                    					 </li>
								 		</c:if>
								 	</c:forEach>		
								 </ul>
							 </c:if>
						</li>
			</c:otherwise>				
		</c:choose>
	</c:forEach>
</c:if>
</ul>
</div>
</div> 
<!-- <li class=' nav-item' id='masters'>
<a href='#'>
<i class='fa fa-cog'></i>
<span class='menu-title' data-i18n='nav.page_layouts.main'>Masters</span>
</a>
<ul class='menu-content'>

<li id='citymaster'><a class='menu-item' href='city_master.html' data-i18n='nav.page_layouts.1_column'>City Master</a>
</li>

<li id='statemaster'><a class='menu-item' href='state_master.html' data-i18n='nav.page_layouts.1_column'>State Master</a>
</li>

<li id='countrymaster'><a class='menu-item' href='country_master.html' data-i18n='nav.page_layouts.1_column'>Country Master</a>
</li>

<li id='companymaster'><a class='menu-item' href='company_master.html' data-i18n='nav.page_layouts.1_column'>Company Master</a>
</li>

<li id='departmaster'><a class='menu-item' href='department_master.html' data-i18n='nav.page_layouts.1_column'>Department Master</a>
</li>

<li id='locationmaster'><a class='menu-item' href='location_master.html' data-i18n='nav.page_layouts.1_column'>Location Master</a>
</li>

<li id='employeemaster'><a class='menu-item' href='employee_master.html' data-i18n='nav.page_layouts.1_column'>Employee Master</a>
</li>

<li id='visitorcompany'><a class='menu-item' href='visitor_company.html' data-i18n='nav.page_layouts.1_column'>Visitor Company Master</a>
</li>

<li id='usermaster'><a class='menu-item' href='user_master.html' data-i18n='nav.page_layouts.1_column'>User Master</a>
</li>


</ul>
</li>
            
</ul>
</div>
</div> -->