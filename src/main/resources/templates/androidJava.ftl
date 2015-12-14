<#list list as m>
	//${m.text}
	@Column(position=${m_index})
	private String ${m.id};
</#list>