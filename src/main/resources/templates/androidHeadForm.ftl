<#list list as m>
TextView ${m.fieldName} = (TextView) mRootView.findViewById(R.id.${m.fieldName});
			${m.fieldName}.setText(model.get${m.fieldName?cap_first}());
</#list>