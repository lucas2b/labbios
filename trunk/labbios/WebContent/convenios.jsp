<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manutenção de Convenios</title>
</head>
<body>
<f:view>
	<h:form>
	<h1><h:outputText value="Adicionar Novo Convênio"></h:outputText></h1>
	Nome: <h:inputText value="#{convenioBean.nome}"></h:inputText><br>
	<h:commandButton value="Adicionar" action="#{convenioBean.adicionarConvenio}"></h:commandButton>
	<br>
	<br>
	<br>
	<h1><h:outputText value="Listagem de Convênios"></h:outputText></h1>
	<h:dataTable border="1" value="#{convenioBean.convenios}" var="item">
		<h:column>
			<f:facet name="header">
				<h:outputText value="ID"></h:outputText>
			</f:facet>
			<h:outputText value="#{item.CONVENIO_ID}"></h:outputText>
		</h:column>
		<h:column>
			<f:facet name="header">
				<h:outputText value="Nome"></h:outputText>
			</f:facet>
			<h:outputText value="#{item.CONVENIO_NOME}"></h:outputText>
		</h:column>
	</h:dataTable>
	</h:form>

</f:view>
</body>
</html>