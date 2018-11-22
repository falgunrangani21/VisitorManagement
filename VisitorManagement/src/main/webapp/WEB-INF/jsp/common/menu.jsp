<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<div class='main-menu menu-fixed menu-light menu-accordion menu-shadow' data-scroll-to-active='true'>
<div class='main-menu-content'>
<ul class='navigation navigation-main' id='main-menu-navigation' data-menu='menu-navigation'>
            
<li class='nav-item' id='visitorhome'>
<a href='/visitorManagement/visitor'>
<i class='fa fa-home'></i>
<span class='menu-title' data-i18n='nav.page_layouts.main'>Visitors</span>
</a>
</li>
            
<!-- <li class=' nav-item' id='masters'>
<a href='#'>
<i class='fa fa-cog'></i>
<span class='menu-title' data-i18n='nav.page_layouts.main'>Masters</span>
</a>
<ul class='menu-content'>
	<li id='citymaster'><a class='menu-item' href='city_master.html' data-i18n='nav.page_layouts.1_column'>City Master</a></li> 
</ul>
</li>
        -->        
        <c:forEach items="${sessionScope['menuList']}" var="main">
		<c:choose> 
			<c:when test="${main[4] eq '0'}">	 
				<li class='nav-item' id="${main[4]}">
					<a href="${main[2]}">
						<i class="fa fa-cog"></i>
						<span class='menu-title' data-i18n='nav.page_layouts.main'>${main[1]}</span>
					</a>
					<ul class="menu-content">
						<c:forEach items="${sessionScope['optionList']}" var="sub" varStatus="status">
							<c:if test="${main[0]==sub[4]}">
								 <li id="${sub[5]}">
								 	<a  href="/visitorManagement${sub[2]}"  class="menu-item" data-i18n="nav.page_layouts.1_column">${sub[1]}</a>
                   					 </li>
					 		</c:if>
					 	</c:forEach>		
					 </ul>
				</li> 
				
			</c:when>
			<c:otherwise>
					<li class="nav-item" id="${main[4]}">
						<a href="#"><i class="fa fa-cog"></i>
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
        <c:if test="#session.menuList.size() > 0">
        
	
		</c:if>     
</ul>
</div>
</div>