<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gerenciamento de Tipos De Laboratório</title>
</head>
<body>
<f:view>
	<h:form>
	<h1>Adicionar Tipo De Laboratorio</h1>
	Nome: <h:inputText value="#{tipoLaboratorioBean.nome}"></h:inputText><br>
	
	<h:commandButton value="Adicionar Laboratorio" action="#{tipoLaboratorioBean.addTipoDeLaboratorio}"></h:commandButton>
	<br>
	</h:form>
	
	<h:form>
	
	<h1>Listagem de Grupo De Exames</h1>
	<h:dataTable border="1" value="#{tipoLaboratorioBean.tiposDeLaboratorio}" var="item">
		<h:column>
			<f:facet name="header">
				<h:outputText value="Nome"></h:outputText>
			</f:facet>
				<h:outputText value="#{item.TIPO_LABORATORIO_NOME}"></h:outputText>
		</h:column>
		<h:column>
			<f:facet name="header">
				<h:outputText value="Ação"></h:outputText>
			</f:facet>
				<h:commandLink value="Editar" action="#{tipoLaboratorioBean.startEditarTipoLaboratorio}">
					<f:setPropertyActionListener value="#{item}" target="#{tipoLaboratorioBean.tipoLaboratorioSelecionado}"/>
				</h:commandLink>	
		</h:column>		
	</h:dataTable>
	
	</h:form>

</f:view>
</body>
</html>