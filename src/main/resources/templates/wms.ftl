merge into ${table} a using
<foreach collection="list" open="(" close=")" separator="union all" item="item">
    select
    <#list ms as m>
        ${m},
    </#list>
    from
    dual
</foreach>
b on
(
<#list kss as ks>
    (
    <#list ks as m>
        a.${m} = b.${m} and
    </#list>
    ) or
</#list>
)
when matched then
update
set
<#list cols as m>
    ${m} = b.${m},
</#list>

when not matched then
INSERT
(
<#list cols as m>
    ${m},
</#list>
)
VALUES
(
<#list cols as m>
    b.${m},
</#list>
)
