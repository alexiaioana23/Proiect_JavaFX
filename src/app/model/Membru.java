package app.model;

public class Membru {

    private int idmembru;
    private String nume;
    private String prenume;
    private int varsta;
    private String instrument;
    private String nationalitate;

    public Membru(int idmembru, String nume, String prenume,
                  int varsta, String instrument, String nationalitate) {
        this.idmembru = idmembru;
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.instrument = instrument;
        this.nationalitate = nationalitate;
    }

    public int getIdmembru() { return idmembru; }
    public String getNume() { return nume; }
    public String getPrenume() { return prenume; }
    public int getVarsta() { return varsta; }
    public String getInstrument() { return instrument; }
    public String getNationalitate() { return nationalitate; }

    @Override
    public String toString() {
        return nume + " " + prenume;
    }
}