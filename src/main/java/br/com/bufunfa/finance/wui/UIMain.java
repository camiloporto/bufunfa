/**
 * 
 */
package br.com.bufunfa.finance.wui;

import java.io.Serializable;

import org.springframework.roo.addon.javabean.RooJavaBean;

/**
 * UI do arquivo Main.xhtml
 * @author camilo
 *
 */
@RooJavaBean
public class UIMain implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7198941770126746141L;
	
	/**
	 * Referese ao "local" onde o usuario
	 * esta na aplicacao. Na pratica
	 * eh a tela onde ele esta
	 */
	private Place place = Place.Contas;
	
	public enum Place {
		
		Contas("/Conta.xhtml"),
		
		Transacoes("/Transacoes.xhtml");
		
		private String view;
		
		private Place(String view) {
			this.view = view;
		}
		
		public String getView() {
			return view;
		}
		
	}

	public UIMain() {
		
	}
	
	public String goTransacao() {
		setPlace(Place.Transacoes);
		return null;
	}
	
	public String goConta() {
		
		setPlace(Place.Contas);
		return null;
	}
	
	public String getActivePlace() {
		return place.getView();
	}
	
}
