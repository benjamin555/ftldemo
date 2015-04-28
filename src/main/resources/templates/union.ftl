<#>
<#list 1..25 as m>
	<#if m != 1>
		union
	</#if>
	select t${m}.c1,t${m}.c6 from t${m}
</#list>
<#list 1..25 as m>1,</#list>