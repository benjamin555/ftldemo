<#list map?keys as key> 
         viewHolder.${key} = (TextView) view.findViewById(R.id.${key});
</#list>

<#list map?keys as key> 
         viewHolder.${key}.setText(item.get${key?cap_first}());
</#list>
private class ViewHolder {
private TextView 
<#list map?keys as key> 
        ${key},
</#list>


<#list map?keys as key> 
        android:id="@+id/${key}"
</#list>
}