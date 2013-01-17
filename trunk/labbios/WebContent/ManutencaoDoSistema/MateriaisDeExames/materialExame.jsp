<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página de manunteção de Material de Exame</title>
</head>
<body>
<f:view>
	<h:form>
	<h1>Cadastro de Novo Material de Exame</h1><br>
	Nome: <h:inputText value="#{materialExameBean.materialExameSelecionado.MATERIAL_EXAME_NOME}"></h:inputText><br>
	<br>
	<h:commandButton action="#{materialExameBean.adicionarMaterialExame}" value="Adicionar Material"></h:commandButton>
	<br>
	<br>
	<h:dataTable value="#{materialExameBean.materiaisExame}" border="1" var="item">
		<h:column>
			<f:facet name="header">
				<h:outputText value="Nome"></h:outputText>
			</f:facet>
			<h:outputText value="#{item.MATERIAL_EXAME_NOME}"></h:outputText>
		</h:column>
		<h:column>
			<f:facet name="header">
				<h:outputText value="Editar"></h:outputText>
			</f:facet>
			<h:commandLink value="Editar" action="#{materialExameBean.startEditarMaterialExame}">
				<f:setPropertyActionListener value="#{item}" target="#{materialExameBean.materialExameSelecionado}"/>
			</h:commandLink>
		</h:column>
	</h:dataTable>
	</h:form>

</f:view>
</body>
</html>