<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edição de Grupo De Exames</title>
</head>
<body>
<f:view>
	<h:form>
	ID: <h:outputText value="#{grupoExameBean.grupoExameSelecionado.GRUPO_EXAME_ID}"></h:outputText><br>
	Nome: <h:inputText value="#{grupoExameBean.grupoExameSelecionado.GRUPO_EXAME_NOME}"></h:inputText><br>
	Abreviatura: <h:inputText value="#{grupoExameBean.grupoExameSelecionado.GRUPO_EXAME_ABREV}"></h:inputText>
	<h:commandButton value="Atualizar" action="#{grupoExameBean.finishEditarGrupoExame}"></h:commandButton>
	</h:form>
</f:view>
</body>
</html>