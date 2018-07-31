package no.usn.kandidatnr1.eksamenapplikasjonsutvikling2018;


public class Kjøretur {

    private String dato,startTid,sluttTid,
            startSted,sluttSted,kjenteStopp,
            merke, modell, årsmodell,sjaaforNavn,
            sjaaforTlf, sjaaforEpost;
    private int kjøreturID,ledigPlass,sykkelfeste,takboks;



    public Kjøretur(int kjøreturID,String dato, String startTid,
                    String sluttTid, String startSted,
                    String sluttSted, String kjenteStopp,
                    int ledigPlass, String merke, String modell,
                    String årsmodell, int sykkelfeste, int takboks,
                    String sjaaforNavn, String sjaaforTlf,
                    String sjaaforEpost) {
        this.kjøreturID = kjøreturID;
        this.dato = dato;
        this.startTid = startTid;
        this.sluttTid = sluttTid;
        this.startSted = startSted;
        this.sluttSted = sluttSted;
        this.kjenteStopp = kjenteStopp;
        this.ledigPlass = ledigPlass;
        this.merke = merke;
        this.modell = modell;
        this.årsmodell = årsmodell;
        this.sykkelfeste = sykkelfeste;
        this.takboks = takboks;
        this.sjaaforNavn = sjaaforNavn;
        this.sjaaforTlf = sjaaforTlf;
        this.sjaaforEpost = sjaaforEpost;


    }
    public int getKjøreturID(){return kjøreturID;}

    public String getDato() {
        return dato;
    }

    public String getStartTid() {
        return startTid;
    }

    public String getSluttTid() {
        return sluttTid;
    }

    public String getStartSted() {
        return startSted;
    }

    public String getSluttSted() {
        return sluttSted;
    }

    public String getKjenteStopp() {
        return kjenteStopp;
    }

    public int getLedigPlass() {
        return ledigPlass;
    }

    public String getMerke() {
        return merke;
    }

    public String getModell() {
        return modell;
    }
    public String getÅrsmodell() {
        return årsmodell;
    }

    public String getSjaaforNavn() {
        return sjaaforNavn;
    }

    public String getSjaaforTlf() {
        return sjaaforTlf;
    }

    public String getSjaaforEpost() {
        return sjaaforEpost;
    }

    public int getSykkelfeste() {
        return sykkelfeste;
    }

    public int getTakboks() {
        return takboks;
    }


}
