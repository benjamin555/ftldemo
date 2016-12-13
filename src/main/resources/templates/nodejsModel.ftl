<#list list as m>

<div class="list-group-item">
  <h5>
    <label class="col-sm-2 control-label">${m.text}</label>
      <p class="form-control-static">           {{model.${m.text}}}
      </p>
  </h5>
</div>
</#list>