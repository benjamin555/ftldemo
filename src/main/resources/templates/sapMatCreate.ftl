<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:urn="urn:sap-com:document:sap:soap:functions:mc-style">
    <soapenv:Header/>
    <soapenv:Body>
        <urn:ZmdmMaterialSavedata>
        <#list dtos as d>
        <${d.tableName}>
            <#list d.fieldNames as fn>
                <${fn}>test</${fn}>
            </#list>
        </${d.tableName}>
        </#list>
        </urn:ZmdmMaterialSavedata>
    </soapenv:Body>
</soapenv:Envelope>


<#list dtos as d>
    ${d.tableName}
    <#list d.fieldNames as fn>
    private String    ${fn};
    </#list>
</#list>