package co.mlforex.forecast.model;

import java.io.Serializable;

public class Mensaje implements Serializable {

    private Boolean esPublico;
    private Boolean codigoVulnerable;
    private Boolean openAPIFileCorrect;
    private Boolean imagenGenerada;
    private String linkRepo;
    private String branchRepoName;
    private String idUsuario;

    public Boolean getEsPublico() {
        return esPublico;
    }

    public void setEsPublico(Boolean esPublico) {
        this.esPublico = esPublico;
    }

    public Boolean getCodigoVulnerable() {
        return codigoVulnerable;
    }

    public void setCodigoVulnerable(Boolean codigoVulnerable) {
        this.codigoVulnerable = codigoVulnerable;
    }

    public Boolean getOpenAPIFileCorrect() {
        return openAPIFileCorrect;
    }

    public void setOpenAPIFileCorrect(Boolean openAPIFileCorrect) {
        this.openAPIFileCorrect = openAPIFileCorrect;
    }

    public Boolean getImagenGenerada() {
        return imagenGenerada;
    }

    public void setImagenGenerada(Boolean imagenGenerada) {
        this.imagenGenerada = imagenGenerada;
    }

    public String getLinkRepo() {
        return linkRepo;
    }

    public void setLinkRepo(String linkRepo) {
        this.linkRepo = linkRepo;
    }

    public String getBranchRepoName() {
        return branchRepoName;
    }

    public void setBranchRepoName(String branchRepoName) {
        this.branchRepoName = branchRepoName;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
