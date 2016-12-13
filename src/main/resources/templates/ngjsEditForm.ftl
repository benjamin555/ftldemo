<#list list as m>

<div class="form-group" >
  <label class="control-label" for="${m.field}">${m.fieldDesc}</label>
  <input name="${m.field}" type="text" ng-model="vm.${m.model}.${m.field}" id="${m.field}" class="form-control" >
</div>
</#list>