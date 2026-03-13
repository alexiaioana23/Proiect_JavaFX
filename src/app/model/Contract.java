package app.model;

import java.sql.Date;

public class Contract {

    private int idcontract;
    private int idformatie;
    private int idmembru;
    private Date data_inceput;
    private Date data_sfarsit;
    private String status_contract;

    private String formatie;
    private String membru;

    public Contract(int idcontract, int idformatie, int idmembru,
                    Date data_inceput, Date data_sfarsit,
                    String status_contract,
                    String formatie, String membru) {

        this.idcontract = idcontract;
        this.idformatie = idformatie;
        this.idmembru = idmembru;
        this.data_inceput = data_inceput;
        this.data_sfarsit = data_sfarsit;
        this.status_contract = status_contract;
        this.formatie = formatie;
        this.membru = membru;
    }

    public int getIdcontract() { return idcontract; }
    public int getIdformatie() { return idformatie; }
    public int getIdmembru() { return idmembru; }
    public Date getData_inceput() { return data_inceput; }
    public Date getData_sfarsit() { return data_sfarsit; }
    public String getStatus_contract() { return status_contract; }
    public String getFormatie() { return formatie; }
    public String getMembru() { return membru; }
}