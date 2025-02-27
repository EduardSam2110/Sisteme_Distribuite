package beans;

public class StudentBean implements java.io.Serializable {
    private String nume = null;
    private String prenume = null;
    private int varsta = 0;

    public StudentBean() {
    }

    public StudentBean(String n, String p, int v) {
        nume = n;
        prenume = p;
        varsta = v;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString()
    {
        return nume + " " + prenume + " " + varsta;
    }
}