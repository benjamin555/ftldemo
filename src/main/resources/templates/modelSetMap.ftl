<#list tFields as tf>
	t.set${tf?cap_first}(s.get${sFields[tf_index]?cap_first}());
</#list>
