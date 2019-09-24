/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importacao.model;

import java.time.LocalDate;
import java.util.Collections;

import importacao.annotations.Table;

/**
 *
 * @author admin
 */
@Table(name="produto")
public class Produto {

	// datacadastro
	// dataultimaaltercao
	private int idproduto;
	private int quantidadecaixa = 1;
	private int idfornecedor;
	private int origem = 0;
	private int piscst = 49;
	private int cofinscst = 49;
	private int idunidade = 7;
	private int iddeposito = 1;
	private int estoqueloja;
	private String cfopsat = null;
	private String codigocsosn;
	private String descricaoproduto;
	private String descricaocurta;
	private String codigobarra1;
	private String ncm;
	private String icms;
	private boolean statusproduto = true;
	private boolean controlaestoque = true;
	private boolean vendeproduto = true;
	private boolean tipoproduto = true;
	private double valorvenda1 = 1;
	private double margemvenda1;
	private double valorcustocaixa;
	private double valorcustounitario;
	private double custorealcaixa;
	private double custorealunitario;
	private int precousado = 0;
	private int idusuariocadastro = 1;
	private int margemperda = 0;
	private boolean utilizagrade = false;
	private boolean cadastradoscanntech = false;
	private boolean utilizareducaoicms = false;
	private boolean utilizafcp = false;
	private boolean enviaremailvendaprazo = false;
	private int idunidadetrib = 7;
	private String codigocsosnnfe;
	private int origemnfe;
	private String icmsnfe;
	private LocalDate datacadastro;
	private LocalDate dataultimaalteracao;
	private int idmarca = 0;
	private int idsetor = 0;
	private String localproduto;
	private int estoqueminimo = 0;
	private int estoquemaximo = 0;
	private String referencia;
	private boolean pesanabalanca = false;
	private boolean pesaporquilo = false;
	private boolean permitefracionar = true;

	public int getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}

	public int getQuantidadecaixa() {
		return quantidadecaixa;
	}

	public void setQuantidadecaixa(int quantidadecaixa) {
		this.quantidadecaixa = quantidadecaixa;
	}

	public int getIdfornecedor() {
		return idfornecedor;
	}

	public void setIdfornecedor(int idfornecedor) {
		this.idfornecedor = idfornecedor;
	}

	public int getOrigem() {
		return origem;
	}

	public void setOrigem(int origem) {
		this.origem = origem;
	}

	public int getPiscst() {
		return piscst;
	}

	public void setPiscst(int piscst) {
		this.piscst = piscst;
	}

	public int getCofinscst() {
		return cofinscst;
	}

	public void setCofinscst(int cofinscst) {
		this.cofinscst = cofinscst;
	}

	public int getIdunidade() {
		return idunidade;
	}

	public void setIdunidade(int idunidade) {
		this.idunidade = idunidade;
		setIdunidadetrib(idunidade);
	}

	public int getIddeposito() {
		return iddeposito;
	}

	public void setIddeposito(int iddeposito) {
		this.iddeposito = iddeposito;
	}

	public int getEstoqueloja() {
		return estoqueloja;
	}

	public void setEstoqueloja(int estoqueloja) {
		this.estoqueloja = estoqueloja;
	}

	public String getCfopsat() {
		return cfopsat;
	}

	public void setCfopsat(String cfopsat) {
		this.cfopsat = cfopsat;
	}

	public String getCodigocsosn() {
		return codigocsosn;
	}

	public void setCodigocsosn(String codigocsosn) {
		this.codigocsosn = codigocsosn;
	}

	public String getDescricaoproduto() {
		return descricaoproduto;
	}

	public void setDescricaoproduto(String descricaoproduto) {
		if (descricaoproduto != null) {
			this.descricaoproduto = descricaoproduto;
			if (descricaoproduto.length() >= 40) {
				this.setDescricaocurta(descricaoproduto.substring(0, 40));
			} else {
				this.setDescricaocurta(descricaoproduto);

			}
		}
	}

	public String getDescricaocurta() {
		return descricaocurta;
	}

	public void setDescricaocurta(String descricaocurta) {
		this.descricaocurta = descricaocurta;
	}

	public String getCodigobarra1() {
		return codigobarra1;
	}

	public void setCodigobarra1(String codigobarra1) {
		this.codigobarra1 = codigobarra1;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		// verifica se é nulo
		if (ncm != null) {
			ncm = ncm.trim();
			// Caso o ncm seja < 8, preenche o restante com 0 até completar 8 digitos
			if (ncm.length() < 8) {
				int ncp = 8 - ncm.length();
				this.ncm = ncm + String.join("", Collections.nCopies(ncp, "9"));
			} else {
				// Se o ncm for 8 digitos
				this.ncm = ncm;
			}
		}
	}

	public String getIcms() {
		return icms;
	}

	public void setIcms(String icms) {
		this.icms = icms;
	}

	public void setIcms(double icms) {
		this.icms = String.format("%.2f", icms);
	}

	public boolean isStatusproduto() {
		return statusproduto;
	}

	public void setStatusproduto(boolean statusproduto) {
		this.statusproduto = statusproduto;
	}

	public boolean isControlaestoque() {
		return controlaestoque;
	}

	public void setControlaestoque(boolean controlaestoque) {
		this.controlaestoque = controlaestoque;
	}

	public boolean isVendeproduto() {
		return vendeproduto;
	}

	public void setVendeproduto(boolean vendeproduto) {
		this.vendeproduto = vendeproduto;
	}

	public boolean isTipoproduto() {
		return tipoproduto;
	}

	public void setTipoproduto(boolean tipoproduto) {
		this.tipoproduto = tipoproduto;
	}

	public double getValorvenda1() {
		return valorvenda1;
	}

	public void setValorvenda1(double valorvenda1) {
		this.valorvenda1 = valorvenda1;
	}

	public double getMargemvenda1() {
		return margemvenda1;
	}

	public void setMargemvenda1(double margemvenda1) {
		this.margemvenda1 = margemvenda1;
	}

	public double getValorcustocaixa() {
		return valorcustocaixa;
	}

	public void setValorcustocaixa(double valorcustocaixa) {
		// verifica se for zero, caso sim preenche com 1
		valorcustocaixa = (valorcustocaixa <= 0) ? 1 : valorcustocaixa;

		this.valorcustocaixa = valorcustocaixa;
		this.setValorcustounitario(valorcustocaixa);
		this.setCustorealcaixa(valorcustocaixa);
		this.setCustorealunitario(valorcustocaixa);
	}

	public double getValorcustounitario() {
		return valorcustounitario;
	}

	public void setValorcustounitario(double valorcustounitario) {
		this.valorcustounitario = valorcustounitario;
	}

	public double getCustorealcaixa() {
		return custorealcaixa;
	}

	public void setCustorealcaixa(double custorealcaixa) {
		this.custorealcaixa = custorealcaixa;
	}

	public double getCustorealunitario() {
		return custorealunitario;
	}

	public void setCustorealunitario(double custorealunitario) {
		this.custorealunitario = custorealunitario;
	}

	public int getPrecousado() {
		return precousado;
	}

	public void setPrecousado(int precousado) {
		this.precousado = precousado;
	}

	public int getIdusuariocadastro() {
		return idusuariocadastro;
	}

	public void setIdusuariocadastro(int usuariocadastro) {
		this.idusuariocadastro = usuariocadastro;
	}

	public int getMargemperda() {
		return margemperda;
	}

	public void setMargemperda(int margemperda) {
		this.margemperda = margemperda;
	}

	public boolean isUtilizagrade() {
		return utilizagrade;
	}

	public void setUtilizagrade(boolean utilizagrade) {
		this.utilizagrade = utilizagrade;
	}

	public boolean isCadastradoscanntech() {
		return cadastradoscanntech;
	}

	public void setCadastradoscanntech(boolean cadastroscanntech) {
		this.cadastradoscanntech = cadastroscanntech;
	}

	public boolean isUtilizareducaoicms() {
		return utilizareducaoicms;
	}

	public void setUtilizareducaoicms(boolean utilizareducaoicms) {
		this.utilizareducaoicms = utilizareducaoicms;
	}

	public boolean isUtilizafcp() {
		return utilizafcp;
	}

	public void setUtilizafcp(boolean utilizafcp) {
		this.utilizafcp = utilizafcp;
	}

	public boolean isEnviaremailvendaprazo() {
		return enviaremailvendaprazo;
	}

	public void setEnviaremailvendaprazo(boolean enviaremailvendaprazo) {
		this.enviaremailvendaprazo = enviaremailvendaprazo;
	}

	public int getIdunidadetrib() {
		return idunidadetrib;
	}

	public void setIdunidadetrib(int idunidadetrib) {
		this.idunidadetrib = idunidadetrib;
	}

	public String getCodigocsosnnfe() {
		return codigocsosnnfe;
	}

	public void setCodigocsosnnfe(String codigocsosnnfe) {
		this.codigocsosnnfe = codigocsosnnfe;
	}

	public int getOrigemnfe() {
		return origemnfe;
	}

	public void setOrigemnfe(int origemnfe) {
		this.origemnfe = origemnfe;
	}

	public String getIcmsnfe() {
		return icmsnfe;
	}

	public void setIcmsnfe(String icmsnfe) {
		this.icmsnfe = icmsnfe;
	}

	public LocalDate getDatacadastro() {
		return LocalDate.now();
	}

	public void setDatacadastro(LocalDate datacadastro) {
		this.datacadastro = datacadastro;
	}

	public LocalDate getDataultimaalteracao() {
		return LocalDate.now();
	}

	public void setDataultimaalteracao(LocalDate dataultimaalteracao) {
		this.dataultimaalteracao = dataultimaalteracao;
	}

	public int getIdmarca() {
		return idmarca;
	}

	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
	}

	public int getIdsetor() {
		return idsetor;
	}

	public void setIdsetor(int idsetor) {
		this.idsetor = idsetor;
	}

	public String getLocalproduto() {
		return localproduto;
	}

	public void setLocalproduto(String localproduto) {
		this.localproduto = localproduto;
	}

	public int getEstoqueminimo() {
		return estoqueminimo;
	}

	public void setEstoqueminimo(int estoqueminimo) {
		this.estoqueminimo = estoqueminimo;
	}

	public int getEstoquemaximo() {
		return estoquemaximo;
	}

	public void setEstoquemaximo(int estoquemaximo) {
		this.estoquemaximo = estoquemaximo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public boolean isPesanabalanca() {
		return pesanabalanca;
	}

	public void setPesanabalanca(boolean pesanabalanca) {
		this.pesanabalanca = pesanabalanca;
	}

	public boolean isPesaporquilo() {
		return pesaporquilo;
	}

	public void setPesaporquilo(boolean pesaporquilo) {
		this.pesaporquilo = pesaporquilo;
	}

	public boolean isPermitefracionar() {
		return permitefracionar;
	}

	public void setPermitefracionar(boolean permitefracionar) {
		this.permitefracionar = permitefracionar;
	}

}
