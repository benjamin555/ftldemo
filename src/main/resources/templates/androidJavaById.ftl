<#list list as m>
	//${m.text}
	@Column(id=${m.id})
	private String ${m.id};
</#list>