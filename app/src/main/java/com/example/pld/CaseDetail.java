package com.example.pld;

public class CaseDetail {
    private String email;
    private String casetitle;
    private String courtname;
    private String casetype;
    private String casenumber;
    private String onbehalfof;
    private String partyname;
    private String contactnumber;
    private String respondantname;
    private String adversepartyadvocatename;
    private String advocatecontactnumber;
    private String feldundersection;
    private String adjourndate;


    public CaseDetail( String email,String casetitle, String courtname, String casetype, String casenumber, String onbehalfof, String partyname,
                       String contactnumber, String respondantname, String adversepartyadvocatename, String advocatecontactnumber,String feldundersection,
                       String adjourndate)
    {
        this.email=email;
        this.casetitle = casetitle;
        this.courtname = courtname;
        this.casetype = casetype;
        this.casenumber = casenumber;
        this.onbehalfof = onbehalfof;
        this.partyname = partyname;
        this.contactnumber = contactnumber;
        this.respondantname = respondantname;
        this.adversepartyadvocatename = adversepartyadvocatename;
        this.advocatecontactnumber=advocatecontactnumber;
        this.feldundersection=feldundersection;
        this. adjourndate=adjourndate;
    }
    public String getadjourndate() {
        return adjourndate;
    }
    public void setadjourndate(String ad) {
        this.adjourndate =ad;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email =email;
    }

    public String getfeldundersection() {
        return feldundersection;
    }
    public void setfeldundersection(String fus) {
        this.feldundersection =fus;
    }


    public  String getadvocatecontactnumber() {
        return advocatecontactnumber;
    }
    public void setadvocatecontactnumber( String acn) {
        this.advocatecontactnumber = acn;
    }

    public  String getcontactnumber() {
        return contactnumber;
    }
    public void setcontactnumber( String cn) {
        this.contactnumber = cn;
    }



    public String getrespondantname() {
        return respondantname;
    }
    public void setrespondantname(String rn) {
        this.respondantname = rn;
    }
    public String getadversepartyadvocatename() {
        return adversepartyadvocatename;
    }
    public void setadversepartyadvocatename(String an) {
        this.adversepartyadvocatename = an;
    }


    public String getcasenumber() {
        return casenumber;
    }
    public void setCasenumber(String cn) {
        this.casenumber = cn;
    }
    public String getpartyname() {
        return partyname;
    }
    public void setpartyname(String pn) {
        this.partyname = pn;
    }

    public String getcasetype() {
        return casetype;
    }
    public void setcasetype(String cty) {
        this.casetype = cty;
    }
    public String getonbehalfof() {
        return onbehalfof;
    }
    public void setonbehalfof(String bh) {
        this.onbehalfof = bh;
    }





    public String getcasetitle() {
        return casetitle;
    }
    public void setcasetitle(String ct) {
        this.casetitle = ct;
    }
    public String getcourtname() {
        return courtname;
    }
    public void setcourtname(String cn) {
        this.courtname = cn;
    }


}
