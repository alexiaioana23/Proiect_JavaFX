package app.model;

public class Formatie {

    private int idformatie;
    private String nume;
    private String gen_muzical;
    private int an_infiintare;
    private String tara_origine;

    public Formatie(int idformatie, String nume, String gen_muzical,
                    int an_infiintare, String tara_origine) {
        this.idformatie = idformatie;
        this.nume = nume;
        this.gen_muzical = gen_muzical;
        this.an_infiintare = an_infiintare;
        this.tara_origine = tara_origine;
    }

    public int getIdformatie() { return idformatie; }
    public String getNume() { return nume; }
    public String getGen_muzical() { return gen_muzical; }
    public int getAn_infiintare() { return an_infiintare; }
    public String getTara_origine() { return tara_origine; }

    @Override
    public String toString() {
        return nume;
    }
}