package com.example.androipapp.services;

public class WebServicesProperties {
	public static final String WSDL_URL_PONTO_ELETRONICO="http://172.16.23.32:8080/PontoEletronicoWeb/PontoEletronicoFacade?WSDL";
	public static final String NAMESPACE_PONTO_ELETRONICO="http://services.pontoeletronico.senac.sc.br/"; 
	public static final String SOAPACTION_PONTO_ELETRONICO="PontoEletronicoFacade";
	
	public static final String WSDL_URL_USUARIO="http://172.16.23.32:8080/PontoEletronicoWeb/UsuarioFacade?WSDL";
	public static final String NAMESPACE_USUARIO="http://services.pontoeletronico.senac.sc.br/"; 
	public static final String SOAPACTION_USUARIO="UsuarioFacade";
	
	public static final String WSDL_URL_PEDIDO="http://172.16.23.32:8080/SistemaPizzariaWebServices/PedidoFacade?WSDL";
	public static final String NAMESPACE_PEDIDO="http://services.pizzaria.senac.sc.br/"; 
	public static final String SOAPACTION_PEDIDO="PedidoFacade";

}
