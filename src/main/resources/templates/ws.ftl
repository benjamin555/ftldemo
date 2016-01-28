@XmlType(name = "", propOrder = { 
<#list sFields as sf>
	"${sf?lower_case}"<#if sf_index != sFields?size-1>,</#if>
</#list>
 })
 
 <#list sFields as sf>
 	@XmlElement(name = "${sf?upper_case}")
	protected String ${sf?lower_case};
</#list>


