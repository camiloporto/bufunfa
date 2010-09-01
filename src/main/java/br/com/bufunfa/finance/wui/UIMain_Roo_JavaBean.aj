package br.com.bufunfa.finance.wui;

import br.com.bufunfa.finance.wui.UIMain.Place;

privileged aspect UIMain_Roo_JavaBean {
    
    public Place UIMain.getPlace() {
        return this.place;
    }
    
    public void UIMain.setPlace(Place place) {
        this.place = place;
    }
    
}
