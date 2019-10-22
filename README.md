# SalesforceMetadataAPIPOC
POC to dynamically update profiles in Salesforce using Metadata API.

Prerequisites - https://developer.salesforce.com/docs/atlas.en-us.api_meta.meta/api_meta/meta_quickstart_prereqs.htm

To get the latest WSDL for the API - search for API in quick find box in Setup in Salesforce.

enterprise.jar generation: java -DcompileTarget=1.8 -classpath "/Users/z0019cp/workfolder/JavaFiles/java-tools-2.1.0.jar:/Users/z0019cp/workfolder/JavaFiles/force-wsc-46.1.0.jar:/Users/z0019cp/workfolder/JavaFiles/js-1.7R2.jar:/Users/z0019cp/workfolder/JavaFiles/ST4-4.0.7.jar:/Users/z0019cp/workfolder/JavaFiles/antlr-runtime-3.5.jar:/Users/z0019cp/workfolder/JavaFiles/antlr-2.7.7.jar" com.sforce.ws.tools.wsdlc /Users/z0019cp/workfolder/JavaFiles/enterprisewsdl.wsdl enterprise.jar

Metadata jar generation: java -DcompileTarget=1.8 -classpath "/Users/z0019cp/workfolder/JavaFiles/java-tools-2.1.0.jar:/Users/z0019cp/workfolder/JavaFiles/force-wsc-46.1.0.jar:/Users/z0019cp/workfolder/JavaFiles/js-1.7R2.jar:/Users/z0019cp/workfolder/JavaFiles/ST4-4.0.7.jar:/Users/z0019cp/workfolder/JavaFiles/antlr-runtime-3.5.jar:/Users/z0019cp/workfolder/JavaFiles/antlr-2.7.7.jar" com.sforce.ws.tools.wsdlc /Users/z0019cp/workfolder/JavaFiles/metadata.wsdl metadata.jar

Partner jar generation: java -DcompileTarget=1.8 -classpath "/Users/z0019cp/workfolder/JavaFiles/java-tools-2.1.0.jar:/Users/z0019cp/workfolder/JavaFiles/force-wsc-46.1.0.jar:/Users/z0019cp/workfolder/JavaFiles/js-1.7R2.jar:/Users/z0019cp/workfolder/JavaFiles/ST4-4.0.7.jar:/Users/z0019cp/workfolder/JavaFiles/antlr-runtime-3.5.jar:/Users/z0019cp/workfolder/JavaFiles/antlr-2.7.7.jar" com.sforce.ws.tools.wsdlc /Users/z0019cp/workfolder/JavaFiles/partner.wsdl partner.jar
