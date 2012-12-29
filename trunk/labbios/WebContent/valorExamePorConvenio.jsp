<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
<h1><h:outputText value="Manutenção de Valores De Exames"></h:outputText></h1>

<h:form>

Convenio:
<h:selectOneMenu id="grupo" value="#{cadastroDeExameBean.convenio}">
	<f:selectItems value="#{cadastroDeExameBean.convenios}"></f:selectItems>
	<f:converter converterId="ConversorConvenio"></f:converter>
</h:selectOneMenu><br></br>
Valor: 
<h:inputText value="#{cadastroDeExameBean.valorDoExame}">
<f:convertNumber type="number"></f:convertNumber>
</h:inputText><br></br>
<h:commandButton value="Adicionar Valor" action="#{cadastroDeExameBean.adicionarValorDeExame}"></h:commandButton><br></br>
<br>
<br>
<h1><h:outputText value="Listagem de valores por convênio"></h:outputText> </h1>
<h:dataTable border="1" value="#{cadastroDeExameBean.valoresDeExameSelecionado}" var="item">
<h:column>
	<f:facet name="header">
		<h:outputText value="Convenio"></h:outputText>
	</f:facet>
	<h:column ></h:column>
</h:column>
</h:dataTable>

</h:form>

</f:view>
</body>
</html>
