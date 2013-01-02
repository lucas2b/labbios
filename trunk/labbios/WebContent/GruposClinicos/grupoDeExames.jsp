<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gerenciamento de grupo de Exames</title>
</head>
<body>
<f:view>
	<h:form>
	<h1>Adicionar Grupo De Exames</h1>
	Nome: <h:inputText value="#{grupoExameBean.nome}"></h:inputText><br>
	Abreviatura: <h:inputText value="#{grupoExameBean.abreviatura}"></h:inputText>
	
	<h:commandButton value="Adicionar Grupo" action="#{grupoExameBean.addGrupoExame}"></h:commandButton>
	<br>
	</h:form>
	
	<h:form>
	
	<h1>Listagem de Grupo De Exames</h1>
	<h:dataTable border="1" value="#{grupoExameBean.gruposDeExames}" var="item">
		<h:column>
			<f:facet name="header">
				<h:outputText value="ID"></h:outputText>
			</f:facet>
				<h:outputText value="#{item.GRUPO_EXAME_ID}"></h:outputText>
		</h:column>
		<h:column>
			<f:facet name="header">
				<h:outputText value="Nome"></h:outputText>
			</f:facet>
				<h:outputText value="#{item.GRUPO_EXAME_NOME}"></h:outputText>
		</h:column>
		<h:column>
			<f:facet name="header">
				<h:outputText value="Abreviatura"></h:outputText>
			</f:facet>
				<h:outputText value="#{item.GRUPO_EXAME_ABREV}"></h:outputText>
		</h:column>
		<h:column>
			<f:facet name="header">
				<h:outputText value="Ação"></h:outputText>
			</f:facet>
				<h:commandLink value="Editar" action="#{grupoExameBean.startEditarGrupoExame}">
					<f:setPropertyActionListener value="#{item}" target="#{grupoExameBean.grupoExameSelecionado}"/>
				</h:commandLink>	
		</h:column>		
	</h:dataTable>
	
	</h:form>

</f:view>
</body>
</html>